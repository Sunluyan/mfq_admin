package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.SysAcl;
import com.mfq.admin.web.bean.example.SysAclExample;
import com.mfq.admin.web.dao.SysAclMapper;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.constants.AclTypeEnum;

@Service
public class SysAclService {

    private static final long LIMIT = 20;
    
    @Resource
    SysAclMapper mapper;

    public SysAcl findById(long id){
        return mapper.selectByPrimaryKey(id);
    }
    
    
    public long findCount(AclTypeEnum type, Long page){
        Long start = null;
        Long limit = null;
        if(page != null){ // find所有
            limit = LIMIT;
            start = (page - 1) * LIMIT;
        }
        if(type == null || type == AclTypeEnum.ALL){ // 所有
            return mapper.findCount(null, start, limit);
        }else{
            return mapper.findCount(type.getId(), start, limit);
        }
    }
    
    /**
     * page == null 则取符合type条件的所有内容
     * @param type
     * @param page
     * @return
     */
    public List<SysAcl> findByType(AclTypeEnum type, Long page){
        Long start = null;
        Long limit = null;
        if(page != null){ // find所有
            limit = LIMIT;
            start = (page - 1) * LIMIT;
        }
        if(type == null || type == AclTypeEnum.ALL){ // 所有
            return mapper.findAll(null, start, limit);
        }else{
            return mapper.findAll(type.getId(), start, limit);
        }
    }

    public SysAcl findByAcl(SysAcl sysAcl){
        SysAclExample example = new SysAclExample();
        example.or().andNameEqualTo(sysAcl.getName()).andUrlEqualTo(sysAcl.getUrl());
        return mapper.selectByExample(example).get(0);
    }

    public List<SysAcl> findMenuChildren(long pid){
        return mapper.findMenuChildren(pid, AclTypeEnum.MENU.getId());
    }
    
    public long updateOne(SysAcl model){
        return mapper.updateByPrimaryKeySelective(model);
    }
    
    public long insertOne(SysAcl model){
        return mapper.insert(model);
    }
    
    public boolean deleteOne(long id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }
}