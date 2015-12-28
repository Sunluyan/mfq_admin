package com.mfq.admin.web.services;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.ProductMapper;
import com.mfq.admin.web.dao.UserQuotaMapper;
import com.mfq.admin.web.models.Product;
import com.mfq.admin.web.models.user.UserQuota;

@Service
public class UserQuotaService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserQuotaService.class);

    @Resource
    UserQuotaMapper mapper;

    /**
     * 
     * @param uid
     * @param num
     *            可能为正，也可能为负数，若为负数，则是购物时减掉的余额！
     * @return
     * @throws Exception
     */
    public long updateUserBalance(long uid, BigDecimal num) throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || num == null) {
            logger.error("Notice: quota={}, operate num={}", quota, num);
            throw new Exception("用户余额配置信息不存在");
        }
        // 假如是减掉金额，需要判断原始余额与减掉金额的大小
        if (quota.getBalance().add(num).compareTo(new BigDecimal(0)) < 0) {
            throw new Exception("用于余额不足以支付购物抵扣额度");
        }
        long l = mapper.updateUserBalance(uid, num, quota.getBalance());
        logger.info("更新用户{}余额度由{}到{}", uid, quota.getBalance(),
                quota.getBalance().add(num));
        return l;
    }

    /**
     * 更新赠送余额
     * 
     * @param uid
     * @param num
     * @return
     * @throws Exception
     */
    public long updateUserPresent(long uid, BigDecimal num) throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || num == null) {
            logger.error("Notice: quota={}, operate num={}", quota, num);
            throw new Exception("用户余额配置信息不存在");
        }
        // 假如是减掉金额，需要判断原始余额与减掉金额的大小
        if (quota.getPresent().add(num).compareTo(new BigDecimal(0)) < 0) {
            throw new Exception("用于余额不足以支付购物抵扣额度");
        }
        long l = mapper.updateUserPresent(uid, num, quota.getPresent());
        logger.info("更新用户{}赠送金额由{}到{}", uid, quota.getPresent(),
                quota.getPresent().add(num));
        return l;
    }

    public long updateUserQuota(long uid, BigDecimal usedQuota)
            throws Exception {
        UserQuota quota = queryUserQuota(uid);
        if (quota == null || usedQuota == null) {
            logger.error("Notice: quota={}, operate num={}", quota, usedQuota);
            throw new Exception("用户额度配置信息不存在");
        }
        if (quota.getQuotaLeft().compareTo(usedQuota) < 0) {
            logger.error("Notice: Quota not enough, quotaLeft={}, willUse={}",
                    quota.getQuotaLeft(), usedQuota);
            throw new Exception("用户可用额度不足！");
        }
        quota.setQuotaLeft(quota.getQuotaLeft().subtract(usedQuota));
        return mapper.updateUserQuota(quota);
    }

    public long updateUserWish(long uid, int wish) {
        return mapper.updateUserWish(uid, wish);
    }

    public long insertUserQuota(UserQuota quota) {
        return mapper.insertUserQuota(quota);
    }

    public UserQuota queryUserQuota(long userId) {
        return mapper.queryUserQuota(userId);
    }
    
    /**
     * @author liuzhiguo1
     * @param uid
     * @return
     */
    public Map<String,Object> queryCertifyQuota(long uid){
    	return mapper.queryCertifyQuota(uid);
    }
    
   /**
    * 
    * @author liuzhiguo1
    * @param uid
    * @param remark
    * @param status 1已认证 3被驳回
    * @return
    */
    public Integer updateAuthStatus(long uid,String remark,Integer status){
    	
    	return mapper.updateAuthStatus(uid, remark, status);
    }
    
    public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		ProductMapper mapper = ac.getBean(ProductMapper.class);
		Product pro = mapper.findById(195);
		System.out.println(pro.toString());
	}

}
