package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.SysAcl;
import com.mfq.admin.web.bean.example.SysAclExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface SysAclMapper {
    int countByExample(SysAclExample example);

    int deleteByExample(SysAclExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    List<SysAcl> selectByExample(SysAclExample example);

    SysAcl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAcl record, @Param("example") SysAclExample example);

    int updateByExample(@Param("record") SysAcl record, @Param("example") SysAclExample example);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    public long findCount(@Param("type") Integer type, @Param("start") Long start, @Param("limit") Long limit);

    public List<SysAcl> findAll(@Param("type") Integer type, @Param("start") Long start, @Param("limit") Long limit);

    public List<SysAcl> findMenuChildren(@Param("pid") long pid, @Param("type") Integer type);

}