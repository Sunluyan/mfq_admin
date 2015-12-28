package com.mfq.admin.web.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.user.UserQuota;

@MQMDao
public interface UserQuotaMapper {

    public long insertUserQuota(UserQuota quota);

    public UserQuota queryUserQuota(@Param("uid") long uid);
    
    public Map<String,Object> queryCertifyQuota(@Param("uid") long uid);

    /**
     * 
     * @param uid 用户ID
     * @param num 要更新剩余余额是多少，变数部分，可为负数！
     * @param balance 旧的余额额度是多少
     * @return
     */
    public long updateUserBalance(@Param("uid") long uid,
            @Param("num") BigDecimal num, @Param("balance") BigDecimal balance);

    
    /**
     * 更新用户剩余赠送额度
     * @param uid
     * @param num
     * @param present
     * @return
     */
    public long updateUserPresent(@Param("uid") long uid,
            @Param("num") BigDecimal num, @Param("present") BigDecimal present);
    
    
    public Integer updateAuthStatus(@Param("uid") long uid,@Param("remark") String remark,@Param("status") Integer status);
    
    /**
     * 更新用户分期额度
     * @param quota
     * @return
     */
    public long updateUserQuota(UserQuota quota);

	public long updateUserWish(@Param("uid")long uid, @Param("wish")int wish);
}