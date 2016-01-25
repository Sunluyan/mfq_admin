package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.UserFeedback;
import com.mfq.admin.web.bean.UserFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MQMDao
public interface UserFeedbackMapper {
    int countByExample(UserFeedbackExample example);

    int deleteByExample(UserFeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    List<UserFeedback> selectByExample(UserFeedbackExample example);

    UserFeedback selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserFeedback record, @Param("example") UserFeedbackExample example);

    int updateByExample(@Param("record") UserFeedback record, @Param("example") UserFeedbackExample example);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);
}