package com.mfq.admin.web.services;



import javax.annotation.Resource;

import com.mfq.admin.web.bean.HomeBanner;
import com.mfq.admin.web.bean.example.HomeBannerExample;
import com.mfq.admin.web.dao.HomeBannerMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.constants.BannerType;

import java.util.List;


@Service
public class HomeBannerService {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeBannerService.class);

    @Resource
	HomeBannerMapper mapper;

    public List<HomeBanner> queryHomeBanners()
	{
		HomeBannerExample example = new HomeBannerExample();
        return mapper.selectByExample(example);
    }

    
	public long insertBanner(long id, String img, String name, int type, long pId, String url) {
		BannerType btype = BannerType.fromId(type);
		if(btype == null){
			btype = BannerType.DEFAULT;
		}
		HomeBanner banner = mapper.selectByPrimaryKey(id);
		if(banner != null){
			logger.info(" update banner .{}",banner.getId());
			banner.setName(name);
			if(StringUtils.isNotBlank(img)) {
				banner.setImg(img);
			}
			banner.setpType(btype);
			banner.setpId(pId);
			banner.setUrl(url);
			return mapper.updateByPrimaryKeySelective(banner);
		}
		banner = new HomeBanner(id, img, name, btype, pId, url);
		return mapper.insert(banner);
	}

	public long deleteBanner(long id) {
		
		return mapper.deleteByPrimaryKey(id);
	}

	public HomeBanner queryHomeBanner(long id)
	{
		return mapper.selectByPrimaryKey(id);
	}
}


