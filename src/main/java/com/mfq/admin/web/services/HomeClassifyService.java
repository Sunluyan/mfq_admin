package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.HomeClassify;
import com.mfq.admin.web.bean.example.HomeClassifyExample;
import com.mfq.admin.web.dao.HomeClassifyMapper;
import org.springframework.stereotype.Service;


@Service
public class HomeClassifyService {

    @Resource
	HomeClassifyMapper mapper;

    public HomeClassify findById(long id)
	{
        return mapper.selectByPrimaryKey((int)id);
    }


    public long insertHomeClassify(HomeClassify h)
	{
        return mapper.insert(h);
    }
    
    public long updateHomeClassify(HomeClassify classify)
	{
    	return mapper.updateByPrimaryKey(classify);
    }

	public List<HomeClassify> queryAll()
	{
		HomeClassifyExample example = new HomeClassifyExample();
		return mapper.selectByExample(example);
	}


	public long delHomeClassify(int id) {
		return mapper.deleteByPrimaryKey(id);
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
