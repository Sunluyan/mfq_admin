/*package com.mfq.admin.web.controllers;

import com.mfq.admin.web.constants.AdminConstants;
import com.mfq.admin.web.dao.OperationDao;
import com.mfq.admin.web.models.WebFile;
import com.shijiebang.filemanipulate.ManipulateConstants;
import com.shijiebang.filemanipulate.image.*;
import com.shijiebang.filemanipulate.store.ftp.FtpDeployFileWorker;
import com.shijiebang.pack.PackImageType;
import com.shijiebang.promotion.PromotionProtoType;
import com.shijiebang.promotion.PromotionService;
import com.shijiebang.staff.adapter.ImageUploadHelper;
import com.shijiebang.staff.adapter.UploadResult;
import com.shijiebang.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.*;

*//**
 * 图像上传.
 *
 * @author Felix Zhang  Date 2013-02-18 15:56
 * @version 1.0.0
 *//*
@Controller
public class ImageUploadController extends BaseController {

    static Log log = LogFactory.getLog(ImageUploadController.class);

    @Autowired
    ImageManipulater imageManipulater;
    @Autowired
    FtpDeployFileWorker ftpDeployFileWorker;
    @Autowired
    PromotionService.Iface promotionService;
    @Autowired
    ImageUploadHelper imageUploadHelper;

    @Autowired
    private OperationDao operationDao;
    //首页用户评价区域
    private final static IImageSize[] USER_COMMENT = new IImageSize[]{new DefaultImageSize("un",285,330),
                                                     new DefaultImageSize("us",190,330)};
    //首页信任模块区域
    private final static IImageSize[] TRUST_MODULE = new IImageSize[]{new DefaultImageSize("tn",180,150),
                                                     new DefaultImageSize("ts",120,100)};

    //工作室评论朋友圈截图
    private final static IImageSize[] STAR_SHOP_COMMENT_IMG = new IImageSize[]{new DefaultImageSize("scb",960,540),new DefaultImageSize("scm",480,270),new DefaultImageSize("scs",240,135)};

    @RequestMapping(value = "/op/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, //
            @RequestParam("USER") String user,//
            @RequestParam("Filename") String filename,//
            @RequestParam(value = "Type",defaultValue="") String type,//
            @RequestParam(value = "wm", defaultValue = "false") String wm,
            HttpServletRequest request,Model model) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");
        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
//            File tmpFile = new File("c:\\" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);
            IImageSize[] sizes =  AdminConstants.THUMBNAIL_SIZE ;

            if ("STAR_SHOP_COMMENT_IMG".equals(type)) {
                sizes = STAR_SHOP_COMMENT_IMG;
            } else if ("STAR_TRAVEL".equals(type)) {
                sizes = AdminConstants.STAR_TRAVEL;   //明星出行
            } else if ("STAR_TRAVEL_WEIBO".equals(type)) {
                sizes = AdminConstants.STAR_TRAVEL_WEIBO;  //明星出行微博评论
            } else if ("APP_RECOMMEND".equals(type) || "APP_ADVERTISEMENT_RECOMMEND".equals(type)) {
                sizes = AdminConstants.APP_RECOMMEND;
            }

            ResultMessage<ImageProcessData> imageResult = null;
            final long start = System.currentTimeMillis();

            final boolean isThumbnail = "thumbnail".equals(type);

            String watermarkConfig = "";
            if(StringUtils.isNotBlank(wm) && !wm.equalsIgnoreCase("false")){
                watermarkConfig = "wm1.png|BOTTOM_RIGHT";
            }

            if(isThumbnail) { //封面类型
                imageResult = imageManipulater.processGeneralImageFile(tmpFile,sizes,user,AdminConstants.PICTURE_SMALLSIZE, watermarkConfig);
            }
            else {
                imageResult = imageManipulater.processGeneralImageFile(tmpFile, sizes, "00", watermarkConfig);
            }

            //should check result
            if (!imageResult.isSuccess()) {
                result.put("code", -3);
                result.put("message", "文件上传失败: 文件格式不支持或文件大于10MB, 请检查,或者重新上传!");
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {

                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());

                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();

                ImageProcessData ipd = uploadRM.getData();
                WebFile image = new WebFile();
                image.setSrcUrl(ipd.getSourceImageUrl());
                image.setUploader(user);
                image.setUploadName(filename);
                image.setCreatedAt(new Date());

                String desc = filename;
                if (filename != null && filename.lastIndexOf(".") > 0) {
                    desc = filename.substring(0, filename.lastIndexOf("."));
                }
                image.setDescription(desc);
                image.setBaseUrl(resultUrls[0].getUrl());

                //dimension, size
                image.setDimension(ipd.getDimension());
                image.setSize((int)ipd.getSize());


                result.put("message", "");
                if ("STAR_TRAVEL".equals(type) || "STAR_TRAVEL_WEIBO".equals(type)) {
                    result.put("image",resultUrls[0].getUrl());
                } else if ("APP_RECOMMEND".equals(type)) {
                    result.put("image",resultUrls[0].getUrl());
                } else {
                    result.put("image", isThumbnail ? resultUrls[4].getUrl() : resultUrls[1].getUrl());
                }
                result.put("code", 0);
            }
            else {
                result.put("code", -2);
                result.put("message", "文件上传失败: 文件格式不支持或文件大于10MB, 请检查!");
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson2(result);
    }

    @RequestMapping(value = "/op/upload2/", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPic(@RequestParam("file") MultipartFile file,
                            @RequestParam("USER") String user,
                            @RequestParam("Filename") String filename) throws IOException {
        if (!file.isEmpty()) {
            String tmpDirectoryPath = FileUtils.getTempDirectoryPath();
            File tmpFile = new File(tmpDirectoryPath + UUID.randomUUID().toString());
            file.transferTo(tmpFile);

            UploadResult uploadResult = upload("", tmpFile, user, "b");

            // should check result
            if (!uploadResult.isSuccess()) {
                return getResultJson(-1, uploadResult.getMessage());
            } else {
                Map<String, Object> data = new HashMap<>();
                data.put("image", uploadResult.getUrl());
                data.put("source", uploadResult.getData().getSourceImageUrl());
                data.put("code", 0);
                data.put("message", "");
                return JsonUtils.toJson(data);
            }
        }

        return getResultJson(-2, "没有选择上传的文件");
    }

    @RequestMapping(value = "/trip/cover/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String updateTripCover(@RequestParam("file") MultipartFile file,
                                  @RequestParam("USER") String user,
                                  @RequestParam("Filename") String filename) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");

        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
//            File tmpFile = new File("c:\\" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);

            ResultMessage<ImageProcessData> imageResult = null;

            imageResult = imageManipulater.processGeneralImageFile(tmpFile,new IImageSize[]{AdminConstants.PICTURE_TRIPCOVER},user,AdminConstants.PICTURE_TRIPCOVER);

            //should check result
            if (!imageResult.isSuccess()) {

                if(imageResult.getCode() == ManipulateConstants.IMAGE_TOOSMALL){ //图像太小时, 清除图像
                    //clean
                    imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());
                }

                result.put("code", -3);
                result.put("message", imageResult.getMessage());
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {

                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());

                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();

                ImageProcessData ipd = uploadRM.getData();
                WebFile image = new WebFile();
                image.setSrcUrl(ipd.getSourceImageUrl());
                image.setUploader(user);
                image.setUploadName(filename);
                image.setCreatedAt(new Date());

                String desc = filename;
                if (filename != null && filename.lastIndexOf(".") > 0) {
                    desc = filename.substring(0, filename.lastIndexOf("."));
                }
                image.setDescription(desc);
                image.setBaseUrl(resultUrls[0].getUrl());

                //dimension, size
                image.setDimension(ipd.getDimension());
                image.setSize((int)ipd.getSize());


                result.put("message", "");
                result.put("image", resultUrls[0].getUrl());
                result.put("code", 0);
            }
            else {
                result.put("code", -2);
                result.put("message", uploadRM.getMessage());
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson2(result);
    }

    @RequestMapping(value = "/prom/pic/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String uploadMobilePromotionPic(@RequestParam("file") MultipartFile file,
                                           @RequestParam("USER") String user,
                                           @RequestParam("Filename") String filename,
                                           @RequestParam("type") int type) throws Exception {
        PromotionProtoType promotionProtoType = promotionService.queryPromotionProtoType(type);
        if (promotionProtoType == null) {
            Map<String, Object> result = getResultMap();
            result.put("code", -3);
            result.put("message", "错误的广告类型");
            return JsonUtils.toJson2(result);
        }
        return processImageDatas(promotionProtoType, user, filename, file);
    }

    private UploadResult upload(String type, File imageFile, String uid, String appendix) {
        IImageSize[] imageSizes = null;
        IImageSize minImageSize = null;

        if ("".equals(type)) {
            imageSizes = AdminConstants.THUMBNAIL_SIZE;
            minImageSize = AdminConstants.PICTURE_SMALLSIZE;
        }

        if (minImageSize == null || imageSizes == null) {
            return unsupportedType();
        }
        return imageUploadHelper.doUploadImageFile(imageFile, imageSizes, null,
                appendix, uid, false, "wm1.png|BOTTOM_RIGHT");
    }

    private UploadResult unsupportedType() {
        UploadResult uploadResult = new UploadResult();
        uploadResult.setSuccess(false);
        uploadResult.setCode(ManipulateConstants.FAIL);
        uploadResult.setMessage("不支持的图片上传规则");

        return uploadResult;
    }

    private String processImageDatas(PromotionProtoType promotionProtoType, String user, String filename, MultipartFile file) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");

        String tmpPath = FileUtils.getTempDirectoryPath();
        if (!file.isEmpty()) {
            List<Map<String, String>> imageMeta = promotionProtoType.getImageSizeList();
            File tmpFile = new File(tmpPath + "/" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);

            List<IImageSize> images = new ArrayList<IImageSize>();
            int minWidth = 0;
            int minHeight = 0;
            String minSuffix = "";
            for (Map<String, String> image : imageMeta) {
                String suffix = image.get("suffix");
                String width = image.get("width");
                String height = image.get("height");

                if (StringUtils.isEmpty(suffix) || StringUtils.isEmpty(width) || StringUtils.isEmpty(height)) {
                    log.error(String.format("Image of promotion type error.(suffix: %s, width: %s, height: %s)", suffix, width, height));
                    continue;
                }
                int w = NumberUtils.toInt(width, 0);
                int h = NumberUtils.toInt(height, 0);
                if (minWidth < w) {
                    minWidth = w;
                    minHeight = h;
                    minSuffix = suffix;
                }
                IImageSize iImageSize = new DefaultImageSize(suffix, w, h);
                images.add(iImageSize);
            }

            IImageSize minImageSize = new DefaultImageSize(minSuffix, minWidth, minHeight);
            ResultMessage<ImageProcessData> imageResult = imageManipulater.processGeneralImageFile(tmpFile, images.toArray(new IImageSize[images.size()]),
                    user, minImageSize);

            //should check result
            if (imageResult != null && !imageResult.isSuccess()) {

                if(imageResult.getCode() == ManipulateConstants.IMAGE_TOOSMALL){ //图像太小时, 清除图像
                    //clean
                    imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());
                }

                result.put("code", -3);
                result.put("message", imageResult.getMessage());
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {

                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());

                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();

                ImageProcessData ipd = uploadRM.getData();
                WebFile image = new WebFile();
                image.setSrcUrl(ipd.getSourceImageUrl());
                image.setUploader(user);
                image.setUploadName(filename);
                image.setCreatedAt(new Date());

                String desc = filename;
                if (filename != null && filename.lastIndexOf(".") > 0) {
                    desc = filename.substring(0, filename.lastIndexOf("."));
                }
                image.setDescription(desc);
                image.setBaseUrl(resultUrls[0].getUrl());

                //dimension, size
                image.setDimension(ipd.getDimension());
                image.setSize((int)ipd.getSize());


                result.put("message", "");
                result.put("image", resultUrls[0].getUrl());
                result.put("code", 0);
            }
            else {
                result.put("code", -2);
                result.put("message", uploadRM.getMessage());
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson2(result);
    }

    *//**
     * 首页用户评论图片上传
     * @param file 上传的文件
     * @param user 后台上传的用户
     * @param filename
     * @param type
     * @param wm
     * @param model
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value = "/comment_trust/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String commentImgUpload(@RequestParam("file") MultipartFile file, //
                         @RequestParam("user") String user,//
                         @RequestParam("Filename") String filename,//
                         @RequestParam(value = "type",defaultValue="") String type,//
                         @RequestParam(value = "wm", defaultValue = "false") String wm,
                         Model model) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");
        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);

            IImageSize[] sizes = null;
            if ("user_comment".equals(type)) {
                sizes = USER_COMMENT;
            } else if ("trust_module".equals(type)) {
                sizes = TRUST_MODULE;
            } else if ("trustCarousel".equals(type)) {
                sizes = new IImageSize[]{new DefaultImageSize("ftcab",990,280)};
            }

            ResultMessage<ImageProcessData> imageResult = null;
            String watermarkConfig = "";
            if(StringUtils.isNotBlank(wm) && !wm.equalsIgnoreCase("false")){
                watermarkConfig = "wm1.png|BOTTOM_RIGHT";
            }

            imageResult = imageManipulater.processGeneralImageFile(tmpFile, sizes, "00", sizes[0],watermarkConfig);
            //should check result
            if (imageResult.getCode() != 0) {
                result.put("code", -3);
                if (imageResult.getCode() == ManipulateConstants.IMAGE_BADFORMAT) {
                    result.put("message", "图像格式错误, 仅支持JPG/JPEG格式.");
                } else {
                    result.put("message",imageResult.getMessage());
                }
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {
                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());

                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();

                ImageProcessData ipd = uploadRM.getData();
                WebFile image = new WebFile();
                image.setSrcUrl(ipd.getSourceImageUrl());
                image.setUploader(user);
                image.setUploadName(filename);
                image.setCreatedAt(new Date());

                String desc = filename;
                if (filename != null && filename.lastIndexOf(".") > 0) {
                    desc = filename.substring(0, filename.lastIndexOf("."));
                }
                image.setDescription(desc);
                image.setBaseUrl(resultUrls[0].getUrl());

                //dimension, size
                image.setDimension(ipd.getDimension());
                image.setSize((int)ipd.getSize());


                result.put("message", "");
                result.put("image", resultUrls[0].getUrl());
                result.put("code", 0);
            } else {
                result.put("code", -2);
                result.put("message", "文件上传失败: 文件格式不支持或文件大于10MB, 请检查!");
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson(result);
    }

    @RequestMapping(value = "/op/pack/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String packImageUpload(@RequestParam("file") MultipartFile file, //
                         @RequestParam("USER") String user,//
                         @RequestParam("Filename") String filename,//
                         @RequestParam(value = "Type",defaultValue="") String type,//
                         @RequestParam(value = "wm", defaultValue = "false") String wm,
                         HttpServletRequest request,Model model) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");
        int packImageType = get(request, "packImageType", 0);
        String extraImageType = get(request,"extraImageType","");

        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
            PackImageType imageType = PackImageType.findByValue(packImageType);

            file.transferTo(tmpFile);
            IImageSize[] sizes = null;

            if (packImageType == 0 && extraImageType.isEmpty()) {
                result.put("code", -4);
                result.put("message", "请选择图片类型");
                return JsonUtils.toJson2(result);
            }

            if (imageType != null) {
                switch (imageType) {
                    case Cover:
                        sizes = AdminConstants.PACK_COVER;
                        break;
                    case TitlePic:
                        sizes = new IImageSize[]{ManipulateConstants.PACK_TITLEPIC,ManipulateConstants.PACK_H5_TITLEPIC};
                        break;
                    case H5TitlePic:
                        sizes = new IImageSize[]{ManipulateConstants.PACK_H5_TITLEPIC};
                        break;
                    case CartoonMap:
                        sizes = new IImageSize[]{ManipulateConstants.PACK_CARTOONMAP};
                        break;
                    case H5CartoonMap:
                        sizes = new IImageSize[]{ManipulateConstants.PACK_H5_CARTOONMAP};
                        break;
                }
            }

            //旅游局
            if ("tourOffice".equals(extraImageType)) {
                sizes = new IImageSize[]{new DefaultImageSize("pts",0,28)};
            } else if ("benefitPoint_icon".equals(extraImageType)) { //利益点
                sizes = new IImageSize[]{new DefaultImageSize("pbs",52,52)};
            } else if ("benefitPoint_bigicon".equals(extraImageType)) {
                sizes = new IImageSize[]{new DefaultImageSize("pbd",0,270)};
            } else if ("routeCategory".equals(extraImageType)) { //线路属性
                String iconCategory = get(request,"iconCategory","");
                if ("category_icon".equals(iconCategory)) {
                    sizes = new IImageSize[]{new DefaultImageSize("fprs",54,42)};
                } else if ("calendar_icon".equals(iconCategory)) {
                    sizes = new IImageSize[]{new DefaultImageSize("fprcs",16,16)};
                }
            } else if ("newsMediaLogo".equals(extraImageType)) {
                sizes = new IImageSize[]{new DefaultImageSize("fqm",100,40)};
            }

            if (sizes == null || sizes.length <=0) {
                result.put("code", -5);
                result.put("message", "参数异常");
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> imageResult = null;
            String watermarkConfig = "";
            IImageSize minSize = sizes[0];
            if ("benefitPoint_bigicon".equals(extraImageType)) {
                imageResult = imageManipulater.processGeneralImageFile(tmpFile,sizes,"00");
            } else {
                imageResult = imageManipulater.processGeneralImageFile(tmpFile,sizes,"00",minSize, watermarkConfig);
            }

            if (imageResult.getCode() != 0) {
                result.put("code", -3);
                if (imageResult.getCode() == ManipulateConstants.IMAGE_BADFORMAT) {
                    result.put("message", "图像格式错误, 仅支持JPG/JPEG格式.");
                } else {
                    result.put("message",imageResult.getMessage());
                }
                return JsonUtils.toJson2(result);
            } 
            //should check result

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {
                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());
                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();
                result.put("message", "");
                result.put("code", 0);
                if (imageType == PackImageType.Cover) {
                    result.put("image",resultUrls[1].getUrl());
                } else {
                    result.put("image",resultUrls[0].getUrl());
                }
            }
            else {
                result.put("code", -2);
                result.put("message", "文件上传失败: 文件格式不支持或文件大于10MB, 请检查!");
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson2(result);
    }

    *//**
     * 专题模版化图片上传
     * @param file
     * @param user
     * @param filename
     * @param type
     * @param wm
     * @param request
     * @param model
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value = "/op/topics/upload/", method = RequestMethod.POST)
    @ResponseBody
    public String topicsImgUpload(@RequestParam("file") MultipartFile file, //
                         @RequestParam("USER") String user,//
                         @RequestParam("Filename") String filename,//
                         @RequestParam(value = "Type",defaultValue="") String type,//
                         @RequestParam(value = "wm", defaultValue = "false") String wm,
                         HttpServletRequest request,Model model) throws Exception {
        final Map<String, Object> result = getResultMap();
        result.put("code", -1);
        result.put("message", "没有选择文件");
        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);

            boolean keepimageformat = false;

            IImageSize[] sizes = AdminConstants.THUMBNAIL_SIZE;
            String imgModuleType = get(request,"img_module_type","");
            if ("pc_head_pic".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_PC_HEAD_PIC;  //PC头图
            } if ("h5_head_pic".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_H5_HEAD_PIC;  //H5头图
            } else if ("tab_imgblock".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_TAB_IMGBLOCK;  //tab-图文块
            } else if ("tab_texttag".equals(imgModuleType)) {
                String backPicType = get(request,"backPicType","");
                if ("h5".equals(backPicType)) {
                    sizes = AdminConstants.TOPIC_TAGTAB_H5_BACKIMG; //tab-标签块背景图
                } else if ("pc".equals(backPicType)) {
                    sizes = AdminConstants.TOPIC_TAGTAB_BACKIMG;
                }
            } else if ("pc_ad".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_PC_AD;   //pc广告位
            } else if ("h5_ad".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_H5_AD;   //h5广告位
            } else if ("picture_shuffling".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_TEXT_PIC;  //三图文区域
            } else if ("pc_small_route_show".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_PC_SMALL_ROUTE;  //pc小线路
            } else if ("h5_small_route_show".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_H5_SMALL_ROUTE;  //h5小线路
            } else if ("big_route_show".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_BIG_ROUTE;  //大线路
            } else if ("prize_config".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_PRIZE_CONFIG; //奖品配置
            } else if ("travel_picture".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_TRAVEL_PIC;  //旅行照片
            } else if ("logo_wall".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_LOGO;     //合作商家品牌logo
            } else if ("mobile_alert".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_MOBILE_LOGO;  //留电话logo
                keepimageformat = true;
            } else if ("wx_pic".equals(imgModuleType)) {
                sizes = AdminConstants.TOPIC_WX;      //微信宣传图
            } else if ("count_down".equals(imgModuleType)) { //倒计时
                String platformType = get(request,"platform_type","");
                if ("pc".equals(platformType)) {
                    sizes = AdminConstants.TOPIC_PC_COUNT_DOWN;
                } else if ("h5".equals(platformType)) {
                    sizes = AdminConstants.TOPIC_H5_COUNT_DOWN;
                }
            }

            ResultMessage<ImageProcessData> imageResult = null;
            String watermarkConfig = "";
            if(StringUtils.isNotBlank(wm) && !wm.equalsIgnoreCase("false")){
                watermarkConfig = "wm1.png|BOTTOM_RIGHT";
            }

            imageResult = imageManipulater.processGeneralImageFile(tmpFile, sizes, "00", keepimageformat, sizes[0], watermarkConfig);
            //imageResult = imageManipulater.processGeneralImageFile(tmpFile,sizes,"00",sizes[0], watermarkConfig);

            //should check result
            if (!imageResult.isSuccess()) {
                result.put("code", -3);
                result.put("message", imageResult.getMessage());
                return JsonUtils.toJson2(result);
            }

            ResultMessage<ImageProcessData> uploadRM = ftpDeployFileWorker.deployImages(imageResult.getData());
            if (uploadRM.isSuccess()) {
                //clean
                imageManipulater.cleanTemporaryFiles(tmpFile, imageResult.getData());
                ImageNameSize[] resultUrls = uploadRM.getData().getThumbnailImages();
                ImageProcessData ipd = uploadRM.getData();
                WebFile image = new WebFile();
                image.setSrcUrl(ipd.getSourceImageUrl());
                image.setUploader(user);
                image.setUploadName(filename);
                image.setCreatedAt(new Date());

                String desc = filename;
                if (filename != null && filename.lastIndexOf(".") > 0) {
                    desc = filename.substring(0, filename.lastIndexOf("."));
                }
                image.setDescription(desc);
                image.setBaseUrl(resultUrls[0].getUrl());

                //dimension, size
                image.setDimension(ipd.getDimension());
                image.setSize((int)ipd.getSize());

                result.put("message", "");
                result.put("image", resultUrls[0].getUrl());
                result.put("code", 0);
            } else {
                result.put("code", -2);
                result.put("message", "文件上传失败: 文件格式不支持或文件大于10MB, 请检查!");
                log.warn("uploadrm is fail");
            }
        }
        return JsonUtils.toJson2(result);
    }
}
*/