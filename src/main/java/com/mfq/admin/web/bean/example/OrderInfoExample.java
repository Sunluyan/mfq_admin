package com.mfq.admin.web.bean.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class OrderInfoExample {

    protected Integer start;

    protected Integer size;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPeriodPayIsNull() {
            addCriterion("period_pay is null");
            return (Criteria) this;
        }

        public Criteria andPeriodPayIsNotNull() {
            addCriterion("period_pay is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodPayEqualTo(BigDecimal value) {
            addCriterion("period_pay =", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayNotEqualTo(BigDecimal value) {
            addCriterion("period_pay <>", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayGreaterThan(BigDecimal value) {
            addCriterion("period_pay >", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("period_pay >=", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayLessThan(BigDecimal value) {
            addCriterion("period_pay <", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("period_pay <=", value, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayIn(List<BigDecimal> values) {
            addCriterion("period_pay in", values, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayNotIn(List<BigDecimal> values) {
            addCriterion("period_pay not in", values, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_pay between", value1, value2, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_pay not between", value1, value2, "periodPay");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Integer value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Integer value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Integer value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Integer value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Integer> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Integer> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Integer value1, Integer value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andOnlinePayIsNull() {
            addCriterion("online_pay is null");
            return (Criteria) this;
        }

        public Criteria andOnlinePayIsNotNull() {
            addCriterion("online_pay is not null");
            return (Criteria) this;
        }

        public Criteria andOnlinePayEqualTo(BigDecimal value) {
            addCriterion("online_pay =", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayNotEqualTo(BigDecimal value) {
            addCriterion("online_pay <>", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayGreaterThan(BigDecimal value) {
            addCriterion("online_pay >", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("online_pay >=", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayLessThan(BigDecimal value) {
            addCriterion("online_pay <", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("online_pay <=", value, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayIn(List<BigDecimal> values) {
            addCriterion("online_pay in", values, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayNotIn(List<BigDecimal> values) {
            addCriterion("online_pay not in", values, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_pay between", value1, value2, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andOnlinePayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("online_pay not between", value1, value2, "onlinePay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayIsNull() {
            addCriterion("hospital_pay is null");
            return (Criteria) this;
        }

        public Criteria andHospitalPayIsNotNull() {
            addCriterion("hospital_pay is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalPayEqualTo(BigDecimal value) {
            addCriterion("hospital_pay =", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayNotEqualTo(BigDecimal value) {
            addCriterion("hospital_pay <>", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayGreaterThan(BigDecimal value) {
            addCriterion("hospital_pay >", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hospital_pay >=", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayLessThan(BigDecimal value) {
            addCriterion("hospital_pay <", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hospital_pay <=", value, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayIn(List<BigDecimal> values) {
            addCriterion("hospital_pay in", values, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayNotIn(List<BigDecimal> values) {
            addCriterion("hospital_pay not in", values, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hospital_pay between", value1, value2, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andHospitalPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hospital_pay not between", value1, value2, "hospitalPay");
            return (Criteria) this;
        }

        public Criteria andUseBalanceIsNull() {
            addCriterion("use_balance is null");
            return (Criteria) this;
        }

        public Criteria andUseBalanceIsNotNull() {
            addCriterion("use_balance is not null");
            return (Criteria) this;
        }

        public Criteria andUseBalanceEqualTo(BigDecimal value) {
            addCriterion("use_balance =", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceNotEqualTo(BigDecimal value) {
            addCriterion("use_balance <>", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceGreaterThan(BigDecimal value) {
            addCriterion("use_balance >", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("use_balance >=", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceLessThan(BigDecimal value) {
            addCriterion("use_balance <", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("use_balance <=", value, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceIn(List<BigDecimal> values) {
            addCriterion("use_balance in", values, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceNotIn(List<BigDecimal> values) {
            addCriterion("use_balance not in", values, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_balance between", value1, value2, "useBalance");
            return (Criteria) this;
        }

        public Criteria andUseBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_balance not between", value1, value2, "useBalance");
            return (Criteria) this;
        }

        public Criteria andCouponNumIsNull() {
            addCriterion("coupon_num is null");
            return (Criteria) this;
        }

        public Criteria andCouponNumIsNotNull() {
            addCriterion("coupon_num is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNumEqualTo(String value) {
            addCriterion("coupon_num =", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotEqualTo(String value) {
            addCriterion("coupon_num <>", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumGreaterThan(String value) {
            addCriterion("coupon_num >", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_num >=", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumLessThan(String value) {
            addCriterion("coupon_num <", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumLessThanOrEqualTo(String value) {
            addCriterion("coupon_num <=", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumLike(String value) {
            addCriterion("coupon_num like", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotLike(String value) {
            addCriterion("coupon_num not like", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumIn(List<String> values) {
            addCriterion("coupon_num in", values, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotIn(List<String> values) {
            addCriterion("coupon_num not in", values, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumBetween(String value1, String value2) {
            addCriterion("coupon_num between", value1, value2, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotBetween(String value1, String value2) {
            addCriterion("coupon_num not between", value1, value2, "couponNum");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIsNull() {
            addCriterion("security_code is null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIsNotNull() {
            addCriterion("security_code is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeEqualTo(String value) {
            addCriterion("security_code =", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotEqualTo(String value) {
            addCriterion("security_code <>", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeGreaterThan(String value) {
            addCriterion("security_code >", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("security_code >=", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLessThan(String value) {
            addCriterion("security_code <", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLessThanOrEqualTo(String value) {
            addCriterion("security_code <=", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLike(String value) {
            addCriterion("security_code like", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotLike(String value) {
            addCriterion("security_code not like", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIn(List<String> values) {
            addCriterion("security_code in", values, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotIn(List<String> values) {
            addCriterion("security_code not in", values, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeBetween(String value1, String value2) {
            addCriterion("security_code between", value1, value2, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotBetween(String value1, String value2) {
            addCriterion("security_code not between", value1, value2, "securityCode");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIsNull() {
            addCriterion("policy_status is null");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIsNotNull() {
            addCriterion("policy_status is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusEqualTo(Integer value) {
            addCriterion("policy_status =", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotEqualTo(Integer value) {
            addCriterion("policy_status <>", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusGreaterThan(Integer value) {
            addCriterion("policy_status >", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("policy_status >=", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusLessThan(Integer value) {
            addCriterion("policy_status <", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("policy_status <=", value, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusIn(List<Integer> values) {
            addCriterion("policy_status in", values, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotIn(List<Integer> values) {
            addCriterion("policy_status not in", values, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusBetween(Integer value1, Integer value2) {
            addCriterion("policy_status between", value1, value2, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andPolicyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("policy_status not between", value1, value2, "policyStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNull() {
            addCriterion("refund_type is null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNotNull() {
            addCriterion("refund_type is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeEqualTo(Integer value) {
            addCriterion("refund_type =", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotEqualTo(Integer value) {
            addCriterion("refund_type <>", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThan(Integer value) {
            addCriterion("refund_type >", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_type >=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThan(Integer value) {
            addCriterion("refund_type <", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThanOrEqualTo(Integer value) {
            addCriterion("refund_type <=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIn(List<Integer> values) {
            addCriterion("refund_type in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotIn(List<Integer> values) {
            addCriterion("refund_type not in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeBetween(Integer value1, Integer value2) {
            addCriterion("refund_type between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_type not between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeIsNull() {
            addCriterion("service_start_time is null");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeIsNotNull() {
            addCriterion("service_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeEqualTo(Date value) {
            addCriterion("service_start_time =", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeNotEqualTo(Date value) {
            addCriterion("service_start_time <>", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeGreaterThan(Date value) {
            addCriterion("service_start_time >", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("service_start_time >=", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeLessThan(Date value) {
            addCriterion("service_start_time <", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("service_start_time <=", value, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeIn(List<Date> values) {
            addCriterion("service_start_time in", values, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeNotIn(List<Date> values) {
            addCriterion("service_start_time not in", values, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeBetween(Date value1, Date value2) {
            addCriterion("service_start_time between", value1, value2, "serviceStartTime");
            return (Criteria) this;
        }

        public Criteria andServiceStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("service_start_time not between", value1, value2, "serviceStartTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}