package com.mfq.admin.web.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.example.UsersQuotaExample;
import com.mfq.admin.web.constants.AuthStatus;
import com.mfq.admin.web.constants.Status;
import com.mfq.admin.web.dao.ProductMapper;
import com.mfq.admin.web.dao.UserFeedbackMapper;
import com.mfq.admin.web.dao.UsersMapper;
import com.mfq.admin.web.dao.UsersQuotaMapper;
import com.mfq.admin.web.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class UserQuotaService {

    private static final Logger logger = LoggerFactory
            .getLogger(UserQuotaService.class);

    @Resource
    UsersQuotaMapper mapper;
    @Resource
    UsersMapper usersMapper;
    @Resource
    UserFeedbackMapper userFeedbackMapper;

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
        num = quota.getBalance().add(num);
        UserQuota record = new UserQuota();
        record.setBalance(num);
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(uid);
        long l = mapper.updateByExampleSelective(record,example);
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

        num = quota.getBalance().add(num);
        UserQuota record = new UserQuota();
        record.setPresent(num);
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(uid);
        long l = mapper.updateByExampleSelective(record,example);
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
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(uid);
        return mapper.updateByExampleSelective(quota,example);
    }

    public long updateUserWish(long uid, int wish) {
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(uid);
        UserQuota userQuota = new UserQuota();
        userQuota.setWishPlastic(wish);
        return mapper.updateByExampleSelective(userQuota,example);
    }

    public long insertUserQuota(UserQuota quota)
    {
        return mapper.insert(quota);
    }

    public UserQuota queryUserQuota(long userId) {
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(userId);
        List<UserQuota> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        return null;
    }
    
    /**
     * @author liuzhiguo1
     * @param uid
     * @return
     */
    public Map<String,Object> queryCertifyQuota(long uid){
        try{

            User user = usersMapper.selectByPrimaryKey(uid);
            UsersQuotaExample quotaExample = new UsersQuotaExample();
            quotaExample.or().andUidEqualTo(uid);
            UserQuota userQuota = mapper.selectByExample(quotaExample).get(0);
            UserFeedbackExample example = new UserFeedbackExample();
            example.or().andUidEqualTo(uid);
            List<UserFeedback> userFeedbacks = userFeedbackMapper.selectByExampleWithBLOBs(example);
            UserFeedback userFeedback = new UserFeedback();
            if(!CollectionUtils.isEmpty(userFeedbacks)){
                userFeedback = userFeedbacks.get(0);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("uid",user.getUid());
            map.put("nick",user.getNick());
            map.put("mobile",user.getMobile());
            map.put("realname",userQuota.getRealname());
            map.put("id_card", userQuota.getIdCard());
            map.put("origin",userQuota.getOrigin());
            map.put("location",userQuota.getLocation());
            map.put("contact",userQuota.getContact());
            map.put("idcard_front",userQuota.getIdcardFront());
            map.put("idcard_reverse",userQuota.getIdcardReverse());
            map.put("remark",userFeedback.getRemark());
            return map;
        }catch(Exception e){
            logger.error(e+e.toString());
            return null;
        }

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
        UserQuota userQuota = new UserQuota();
        userQuota.setSchoolRemark(remark);
        userQuota.setAuthStatus(status);
        UsersQuotaExample example = new UsersQuotaExample();
        example.or().andUidEqualTo(uid);
    	return mapper.updateByExampleSelective(userQuota,example);
    }
    
    public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		ProductMapper mapper = ac.getBean(ProductMapper.class);
		Product pro = mapper.selectByPrimaryKey(195l);
		System.out.println(pro.toString());
	}

    public String endowCredit(long uid, long loginUid, BigDecimal credit) {

        long result = mapper.endowCredit(uid, credit, AuthStatus.ALREADYINTERVIEW.getId());

        return JSONUtil.successResultJson(result);
    }

    public String refuseCredit(long uid, long loginUid, String remark) {

        UserQuota quota = mapper.queryUserQuota(uid);
        if(quota ==null || quota.getUid() <1){
            return JSONUtil.toJson(1000,"用户不存在...",null);
        }

        long type = quota.getUserType();
        if(type == 1){
            quota.setSchoolRemark(remark);
        }else if(type == 2){
            quota.setWorkRemark(remark);
        }else{
            quota.setSchoolRemark(remark);
            quota.setWorkRemark(remark);
        }
        quota.setAuthStatus(AuthStatus.PASSINTERVIEW.getId());

        UsersQuotaExample example = new UsersQuotaExample();
        example.createCriteria().andUidEqualTo(uid);

        long result = mapper.updateByExampleSelective(quota, example);

        return JSONUtil.successResultJson(result);
    }
}
