package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.HomeClassifyMapper;
import com.mfq.admin.web.models.HomeClassify;

@Service
public class HomeClassifyService {

    @Resource
    HomeClassifyMapper mapper;

    public HomeClassify findById(long id) {
        return mapper.findById(id);
    }


    public long insertHomeClassify(HomeClassify h) {
        return mapper.insertHomeClassify(h);
    }
    
    public long updateHomeClassify(HomeClassify classify){
    	return mapper.updateHomeClassify(classify);
    }

	public List<HomeClassify> queryAll() {
		return mapper.queryAll();
	}


	public long delHomeClassify(int id) {
		return mapper.deleteHomeClassify(id);
	}


	public long saveHomeClassify(int id, String name, int index) {
		HomeClassify classify = null;
		if(id < 1){
			classify = new HomeClassify();
			classify.setName(name);
			classify.setIndex(index);
			classify.setUrl("");
			return insertHomeClassify(classify);
		}
		classify = findById(id);
		if(classify != null){
			classify.setName(name);
			classify.setIndex(index);
			classify.setUrl("");
			return updateHomeClassify(classify);
		}
		return 0;
	}

}
