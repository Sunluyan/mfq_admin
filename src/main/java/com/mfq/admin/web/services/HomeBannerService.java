package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.admin.web.constants.BannerType;
import com.mfq.admin.web.dao.HomeBannerMapper;
import com.mfq.admin.web.models.HomeBanner;

@Service
public class HomeBannerService {

    @Resource
    HomeBannerMapper mapper;

    public List<HomeBanner> queryHomeBanners() {
        return mapper.queryAll();
    }
    
    
	public long insertBanner(long id, String img, String name, int type, long pId, String url) {
		BannerType btype = BannerType.fromId(type);
		if(btype == null){
			btype = BannerType.DEFAULT;
		}
		HomeBanner banner = mapper.findById(id);
		if(banner != null){
			banner = new HomeBanner(id, img, name, btype, pId,  url);
			return mapper.updateHomeBanner(banner);
		}
		banner = new HomeBanner(id, img, name, btype, pId, url);
		return mapper.insertHomeBanner(banner);
	}

	public long deleteBanner(long id) {
		
		return mapper.deleteHomeBanner(id);
	}

	public HomeBanner queryHomeBanner(long id) {
		return mapper.findById(id);
	}
}


