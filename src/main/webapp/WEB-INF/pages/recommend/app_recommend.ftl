<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#assign _csss=['/upload/swfupload.css']>

<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<style type="text/css">

    .section {
        width: 1200px;
        border: 1px solid #808080;
        border-radius: 5px;
        padding: 10px;
        margin-bottom: 20px;
    }
    .section_head {
        clear: both;
        border-radius: 3px;
    }
    .section_head span {
        display: inline-block;
        width: 206px;
        margin-left: 20px;
        text-align: center;
    }
    .left_head {
        float: left;
        font-family: bold;
    }
    .right_head {
        float: right;
        font-family: bold;
    }

    .close {
        font-size: 30px;

    }
    a.close.block {
        top: 0px;
        right: 5px;
    }

    .btn-large {
        margin-left: 500px;
    }
</style>

<#if recommendList?has_content>
<script type="text/javascript">
    <#assign recommendKeys = recommendList?keys>
    <#list recommendKeys as key>
        <#if recommendList[key].getValue() != "">
        var ${key} = ${recommendList[key].getValue()}
        </#if>
    </#list>
</script>
</#if>

<div>
    <div style="margin-left: 100px;">
        <h3>推荐位管理</h3>
        <div id="destination" class="section">
            <div>
                <span class="left_head">目的地</span>
                <span class="right_head">展示在推荐位中的顺序：<input type="text" class="section_order" name="section_order" value="" placeholder="请填写数字"/></span>
            </div>
            <div class="section_head">
                <span>展示图片URL</span>
                <span>主标题</span>
                <span>副标题</span>
                <span>关联自由行ID(多个ID用逗号分隔)</span>
                <span><a class="btn btn-info add">+添加</a></span>
            </div>
            <ol class="recommend_item">

            </ol>
        </div>
        <div id="topic" class="section">
            <div>
                <span class="left_head">主题（比如：浪漫）</span>
                <span class="right_head">展示在推荐位中的顺序：<input type="text" class="section_order" name="section_order" value="" placeholder="请填写数字"/></span>
            </div>
            <div class="section_head">
                <span>展示图片URL</span>
                <span>主标题</span>
                <span>副标题</span>
                <span>关联自由行ID(多个ID用逗号分隔)</span>
                <span><a class="btn btn-info add">+添加</a></span>
            </div>
            <ol class="recommend_item">

            </ol>
        </div>
        <div id="activity" class="section">
            <div>
                <span class="left_head">活动（比如：春节预售）</span>
                <span class="right_head">展示在推荐位中的顺序：<input type="text" class="section_order" name="section_order" value="" placeholder="请填写数字"/></span>
            </div>
            <div class="section_head">
                <span>展示图片URL</span>
                <span>主标题</span>
                <span>副标题</span>
                <span>关联自由行ID(多个ID用逗号分隔)</span>
                <span><a class="btn btn-info add">+添加</a></span>
            </div>
            <ol class="recommend_item">

            </ol>
        </div>
        <div id="ad" class="section">
            <div>
                <span class="left_head">H5广告</span>
            </div>
            <div class="section_head">
                <span>展示图片URL</span>
                <span>跳转URL</span>
                <span>展示位置</span>
                <span><a class="btn btn-info add">+添加</a></span>
            </div>
            <ol class="recommend_item">

            </ol>
        </div>
        <div class="control-group info">
            <label class="control-label">请分类选择进行图片上传:</label>
            <select id="image_category">
                <option value="">请选择类别</option>
                <option value="destination">目的地</option>
                <option value="topic">主题</option>
                <option value="activity">活动</option>
                <option value="ad">H5广告</option>
            </select>
            <select id="image_id">
                <option value="">请选择图片的item_id</option>
            </select>
        </div>
        <div class="fieldset flash" style="margin-left: 120px;margin-top: 30px;height: 100px;" id="fsUploadProgress">
            <span class="legend">快速上传</span>
        </div>

        <div id="divStatus" style="margin-left: 120px;"><!--<span>0</span>&nbsp;个文件已上传--></div>
        <div style="margin-left: 120px;">
            <span id="spanButtonPlaceHolder"></span>
            <input id="btnCancel" type="button" value="取消上传" onclick="" disabled="disabled" style="margin-left: 2px; margin-top: 32px;font-size: 8pt; height: 29px;" />
        </div>
        <input type="button" id="save" value="保&nbsp;存" class="btn btn-info btn-large"/>
    </div>
</div>

<script type="text/javascript" src="/upload/swfupload.js"></script>
<script type="text/javascript" src="/upload/swfupload.queue.js"></script>
<script type="text/javascript" src="/upload/fileprogress.js"></script>
<script type="text/javascript" src="/upload/handlers.js"></script>
<script type="text/javascript">
    $(".recommend_item").sortable();

    var upload_params = {
        "USER" : "${_user}","wm":false
    };

    function fileQueued(file) {
        try {

            if (!image.hasSelectedCategory() || !image.hasSelectedItemId()) {
                var progress = new FileProgress(file, this.customSettings.progressTarget);
                progress.disappear();
                return ;
            }

            var progress = new FileProgress(file, this.customSettings.progressTarget);
            progress.setStatus("Pending...");
            progress.toggleCancel(true, this);
        } catch (ex) {
            this.debug(ex);
        }
    }

    function fileDialogStart() {
        if (!image.hasSelectedCategory() || !image.hasSelectedItemId()) {
            alert('请先选择类别或item的ID');
        }
    }

    function fileDialogComplete(numFilesSelected, numFilesQueued, numFilesInQueue) {
        try {
            if (!image.hasSelectedCategory() || !image.hasSelectedItemId()) {
                this.cancelUpload();
            } else {
                if (numFilesSelected > 0) {
                    document.getElementById(this.customSettings.cancelButtonId).disabled = false;
                }

                if (image.getCategory() == 'ad') {
                    upload_params["Type"] = 'APP_ADVERTISEMENT_RECOMMEND';
                } else {
                    upload_params["Type"] = 'APP_RECOMMEND';
                }

                this.setPostParams(upload_params);
                /* I want auto start the upload and I can do that here */
                this.startUpload();
            }
        } catch (ex)  {
            this.debug(ex);
        }
    }

    function uploadSuccess(file,serverdata,resp) {
        var uploadResult = $.parseJSON(serverdata);
        if (uploadResult['code'] != 0) {
            if (uploadResult['msg']) {
                alert(uploadResult['msg']);
            } else if (uploadResult['message']) {
                alert(uploadResult['message']);
            }
            this.cancelQueue();
        } else {
            image.setImage(uploadResult['image']);
        }
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.disappear();
    }

    function uploadComplete(file) {
    }

    // This event comes from the Queue Plugin
    function queueComplete(numFilesUploaded) {
        var status = document.getElementById("divStatus");
        status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
    }

    var image = (function() {
        var targets = {
            'imageCategory' : $('#image_category'),
            'imageId' : $('#image_id'),
            'destinationItemContainer' : $('#destination .recommend_item'),
            'topicItemContainer' : $('#topic .recommend_item'),
            'activityItemContainer' : $('#activity .recommend_item'),
            'adItemContainer' : $('#ad .recommend_item')
        };

        function initItemId() {
            var category = this.value;
            if (category == '') {
                return ;
            }
            targets.imageId.find('option:gt(0)').remove();
            var itemNum = targets[category + 'ItemContainer'].children('li').length;
            var itemArr = [];
            if (itemNum > 0) {
                for (var i=1;i <= itemNum;i++) {
                    itemArr.push('<option value="'+i+'">item_'+i+'</option>');
                }
                targets.imageId.append(itemArr.join(''));
            }
        }

        function setImageUrl(url) {
            var category = $.trim(targets.imageCategory.val());
            var id = $.trim(targets.imageId.val());

            if (category.length > 0 && id.length > 0) {
                targets[category + 'ItemContainer'].children().eq((id - 1)).children('input').eq(0).val(url);
            }
        }

        function hasSelectedCategory() {
            var category = $.trim(targets.imageCategory.val());
            if (category.length > 0) {
                return true;
            }
            return false;
        }

        function getCategory() {
            var category = $.trim(targets.imageCategory.val());
            if (category.length > 0) {
                return category;
            }
            return '';
        }

        function hasSelectedItemId() {
            var id = $.trim(targets.imageId.val());
            if (id.length > 0) {
                return true;
            }
            return false;
        }

        function resetCategory() {
            targets.imageCategory.val('');
        }

        targets.imageCategory.change(initItemId);

        return {
            'setImage' : setImageUrl,
            'hasSelectedCategory' : hasSelectedCategory,
            'hasSelectedItemId' : hasSelectedItemId,
            'resetCategory' : resetCategory,
            'getCategory' : getCategory
        };
    })();
    var swfu;
    $(function() {
        var settings = {
            flash_url : "/upload/swfupload.swf",
            upload_url: "/op/upload/",
            file_size_limit : "10 MB",
            file_types : "*.jpg;*.jpeg;*.JPG",
            file_types_description : "JPG",
            file_upload_limit : 10,  //配置上传个数
            file_queue_limit : 1,
            file_post_name : 'file',
            custom_settings : {
                progressTarget : "fsUploadProgress",
                cancelButtonId : "btnCancel"
            },
            debug: false,
            // Button settings
            button_image_url: "/upload/btnimage.png",
            button_width: "65",
            button_height: "29",
            button_placeholder_id: "spanButtonPlaceHolder",
            button_text: '<span class="theFont">上传</span>',
            button_text_style: ".theFont { font-size: 16; }",
            button_text_left_padding: 12,
            button_text_top_padding: 3,
            button_disabled:false,

            file_dialog_start_handler:fileDialogStart,
            file_queued_handler : fileQueued,
            file_queue_error_handler : fileQueueError,
            file_dialog_complete_handler : fileDialogComplete,
            upload_start_handler : uploadStart,
            upload_progress_handler : uploadProgress,
            upload_error_handler : uploadError,
            upload_success_handler : uploadSuccess,
            upload_complete_handler : uploadComplete,
            queue_complete_handler : queueComplete
        };
        swfu = new SWFUpload(settings);
    });

    var recommend = (function () {

        var commonItemNames = ['image','title','subtitle','supers'];
        var adItemNames = ['ads_image_url','ads_url','position'];

        var param = {};
        var sectionOrder = {};
        var sections = ['destination','topic','activity','ad'];
        var elements = {
            'destinationItemContainer' : $('#destination .recommend_item'),
            'topicItemContainer' : $('#topic .recommend_item'),
            'activityItemContainer' : $('#activity .recommend_item'),
            'adItemContainer' : $('#ad .recommend_item'),
            'save' : $('#save'),
            'destinationSectionOrder' : $('#destination .section_order'),
            'topicSectionOrder' : $('#topic .section_order'),
            'activitySectionOrder' : $('#activity .section_order'),
            'adSectionOrder' : $('#ad .section_order')
        };

        var reqOptions = {
            type : 'post',
            url : '/a/recommend/',
            dataType : 'json'
        };

        var sections = ['destination','topic','activity','ad'];
        var sectionNameMap = {
            'destination' : '目的地',
            'topic' : '主题',
            'activity' : '活动',
            'ad' : 'H5广告'
        };

        function imgPreview() {
            var image = $.trim($(this).parent().children().first().val());
            if (image != '') {
                window.open(image,'_blank');
            }
        }

        function newItem(type) {
            var itemArr = [];
            if (type == 'ad') {
                itemArr.push('<li class="tag ui-state-default">');
                itemArr.push('<input type="text" name="image" value="" disabled="disabled"/>&nbsp;');
                itemArr.push('<input type="text" name="forward_url" value=""/>&nbsp;');
                itemArr.push('<input type="text" name="position" value="" placeholder="请填写非负整数"/>&nbsp;');
                itemArr.push('<a href="javascript:void(0);" class="btn btn-link preview">图片预览</a>&nbsp;');
                itemArr.push('<a class="close block" data-dismiss="alert">×</a>&nbsp;');
                itemArr.push('</li>');
            } else {
                itemArr.push('<li class="tag ui-state-default">');
                itemArr.push('<input type="text" name="image" value="" disabled="disabled"/>&nbsp;');
                itemArr.push('<input type="text" name="main_title" value="" maxlength="10" placeholder="最多10个字符"/>&nbsp;');
                itemArr.push('<input type="text" name="sub_title" value="" maxlength="10" placeholder="最多10个字符"/>&nbsp;');
                itemArr.push('<input type="text" name="trips" value="" placeholder=""/>&nbsp;');
                itemArr.push('<a href="javascript:void(0);" class="btn btn-link preview">图片预览</a>&nbsp;');
                itemArr.push('<a class="close block" data-dismiss="alert">×</a>&nbsp;');
                itemArr.push('</li>');
            }
            return itemArr.join('');
        }

        function checkSupers(section,value) {
            if (value == '') {
                alert(sectionNameMap[section] + '的关联自由行ID不能为空');
                return false;
            }
            if (!/^(\d+)(,\d+)*$/.test(value)) {
                alert(sectionNameMap[section] + '的关联自由行ID格式不正确');
                return false;
            }
            return true;
        }

        function checkPosition(section,value) {
            if (value == '') {
                alert(sectionNameMap[section] + '展示位置不能为空');
                return false;
            }
            if (!/^[1-9][0-9]*$/.test(value)) {
                alert(sectionNameMap[section] + '展示位置必须是非负整数');
                return false;
            }
            return true;
        }

        function checkSectionOrder(section,value) {
            if (value == '') {
                alert(sectionNameMap[section] + '的推荐位展示顺序不能为空');
                return false;
            }
            if (!/^((-?[1-9]+)|0)$/.test(value)) {
                alert(sectionNameMap[section] + '的推荐位展示顺序格式不正确');
                return false;
            }
            return true;
        }

        function otherRecommendTemplate(data) {
            var template = [];
            template.push('<li class="tag ui-state-default">');
            template.push('<input type="text" name="image" value="'+(data['image'] ? data['image'] : '')+'" disabled="disabled"/>&nbsp;');
            template.push('<input type="text" name="main_title" value="'+(data['title'] ? data['title'] : '')+'" maxlength="10" placeholder="最多10个字符"/>&nbsp;');
            template.push('<input type="text" name="sub_title" value="'+(data['subtitle'] ? data['subtitle'] : '')+'" maxlength="10" placeholder="最多10个字符"/>&nbsp;');
            template.push('<input type="text" name="trips" value="'+(data['supers'] ? data['supers'] : '')+'" placeholder=""/>&nbsp;');
            template.push('<a href="javascript:void(0);" class="btn btn-link preview">图片预览</a>&nbsp;');
            template.push('<a class="close block" data-dismiss="alert">×</a>&nbsp;');
            template.push('</li>');
            return template.join('');
        }
        function adTemplate(data) {
            var template = [];
            template.push('<li class="tag ui-state-default">');
            template.push('<input type="text" name="image" value="'+(data['ads_image_url'] ? data['ads_image_url'] : '')+'" disabled="disabled"/>&nbsp;');
            template.push('<input type="text" name="forward_url" value="'+(data['ads_url'] ? data['ads_url'] : '')+'"/>&nbsp;');
            template.push('<input type="text" name="position" value="'+(data['position'] ? data['position'] : '')+'" placeholder="请填写非负整数"/>&nbsp;');
            template.push('<a href="javascript:void(0);" class="btn btn-link preview">图片预览</a>&nbsp;');
            template.push('<a class="close block" data-dismiss="alert">×</a>&nbsp;');
            template.push('</li>');
            return template.join('');
        }
        function init() {
            for (var i=0;i < sections.length;i++) {
                if (window['app_recommend_'+sections[i]]) {
                    var section = window['app_recommend_'+sections[i]];
                    var dataTemplate = [];
                    if (sections[i] == 'ad') {
                        for (var j=0;j < section.length;j++) {
                            dataTemplate.push(adTemplate(section[j]));
                        }
                    } else {
                        for (var j=0;j < section.length;j++) {
                            dataTemplate.push(otherRecommendTemplate(section[j]));
                        }
                    }
                    elements[sections[i] + 'ItemContainer'].html(dataTemplate.join(''));
                    if (app_recommend_section_order) {
                        elements[sections[i] + 'SectionOrder'].val(app_recommend_section_order['app_recommend_'+sections[i]]);
                    }
                }
            }
        }

        function splitStrToArray(str) {
            var supers = [];
            if (str != '') {
                supers = str.split(',');
            }
            return supers;
        }

        function getValue(section) {
            var data = [];
            var items = elements[section + 'ItemContainer'].children();
            if (items.length <= 0) {
                return true;
            }
            for (var i=0;i < items.length;i++) {
                var item = {};
                var values = items.eq(i).children('input');
                if ($.trim(values.get(0).value) == '') {
                    alert(sectionNameMap[section] + '展示图片不能为空');
                    return false;
                }

                if (section == 'ad') {
                    for (var j=0;j < values.length;j++) {
                        item[adItemNames[j]] = values.get(j).value;
                    }
                } else {
                    for (var j=0;j < values.length - 1;j++) {
                        item[commonItemNames[j]] = values.get(j).value;
                    }
                    item[commonItemNames[values.length - 1]] = splitStrToArray(values.get(values.length - 1).value);
                }

                if (section != 'ad') {
                    if (!checkSupers(section,values.get(3).value)) {
                        return false;
                    }
                }
                data.push(item);
            }

            if (section == 'ad') {
                if (!checkPosition('ad',values.get(2).value)) {
                    return false;
                }
            } else {
                if (!checkSectionOrder(section, $.trim(elements[section + 'SectionOrder'].val()))) {
                    return false;
                }
                sectionOrder['app_recommend_' + section] = $.trim(elements[section + 'SectionOrder'].val());
            }
            param[section] = JSON.stringify(data);
            return true;
        }

        function successHandle(data) {
            alert(data.msg);
        }

        function save() {
            for (var i=0;i < sections.length;i++) {
                if (!getValue(sections[i])) {
                    return ;
                }
            }

            var ownPropertyNum = 0;
            for (var key in param) {
                if (param.hasOwnProperty(key)) {
                    ownPropertyNum += 1;
                }
            }

            if (ownPropertyNum == 0) {
                return ;
            }
            param.sectionOrder = JSON.stringify(sectionOrder);
            reqOptions.data = param;
            reqOptions.success = successHandle;
            $.ajax(reqOptions);
        }

        $('.add').click(function(e) {
            e.preventDefault();
            var typeId = $(this).parents('.section').attr('id');
            var itemContainer = $(this).parents('.section_head').next();
            itemContainer.append(newItem(typeId));
            image.resetCategory();
        });

        init();

        elements.save.click(save);

        $('.preview').live('click',imgPreview);

    })();

</script>
<#include "commons/footer.ftl" />