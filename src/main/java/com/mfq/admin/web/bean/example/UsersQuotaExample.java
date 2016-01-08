package com.mfq.admin.web.bean.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersQuotaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsersQuotaExample() {
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

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNull() {
            addCriterion("school is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNotNull() {
            addCriterion("school is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolEqualTo(String value) {
            addCriterion("school =", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotEqualTo(String value) {
            addCriterion("school <>", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThan(String value) {
            addCriterion("school >", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("school >=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThan(String value) {
            addCriterion("school <", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThanOrEqualTo(String value) {
            addCriterion("school <=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLike(String value) {
            addCriterion("school like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotLike(String value) {
            addCriterion("school not like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolIn(List<String> values) {
            addCriterion("school in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotIn(List<String> values) {
            addCriterion("school not in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolBetween(String value1, String value2) {
            addCriterion("school between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotBetween(String value1, String value2) {
            addCriterion("school not between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andClassesIsNull() {
            addCriterion("classes is null");
            return (Criteria) this;
        }

        public Criteria andClassesIsNotNull() {
            addCriterion("classes is not null");
            return (Criteria) this;
        }

        public Criteria andClassesEqualTo(String value) {
            addCriterion("classes =", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotEqualTo(String value) {
            addCriterion("classes <>", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesGreaterThan(String value) {
            addCriterion("classes >", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesGreaterThanOrEqualTo(String value) {
            addCriterion("classes >=", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLessThan(String value) {
            addCriterion("classes <", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLessThanOrEqualTo(String value) {
            addCriterion("classes <=", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesLike(String value) {
            addCriterion("classes like", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotLike(String value) {
            addCriterion("classes not like", value, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesIn(List<String> values) {
            addCriterion("classes in", values, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotIn(List<String> values) {
            addCriterion("classes not in", values, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesBetween(String value1, String value2) {
            addCriterion("classes between", value1, value2, "classes");
            return (Criteria) this;
        }

        public Criteria andClassesNotBetween(String value1, String value2) {
            addCriterion("classes not between", value1, value2, "classes");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtIsNull() {
            addCriterion("startschool_at is null");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtIsNotNull() {
            addCriterion("startschool_at is not null");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtEqualTo(Date value) {
            addCriterion("startschool_at =", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtNotEqualTo(Date value) {
            addCriterion("startschool_at <>", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtGreaterThan(Date value) {
            addCriterion("startschool_at >", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtGreaterThanOrEqualTo(Date value) {
            addCriterion("startschool_at >=", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtLessThan(Date value) {
            addCriterion("startschool_at <", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtLessThanOrEqualTo(Date value) {
            addCriterion("startschool_at <=", value, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtIn(List<Date> values) {
            addCriterion("startschool_at in", values, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtNotIn(List<Date> values) {
            addCriterion("startschool_at not in", values, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtBetween(Date value1, Date value2) {
            addCriterion("startschool_at between", value1, value2, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andStartschoolAtNotBetween(Date value1, Date value2) {
            addCriterion("startschool_at not between", value1, value2, "startschoolAt");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIsNull() {
            addCriterion("idcard_front is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIsNotNull() {
            addCriterion("idcard_front is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontEqualTo(String value) {
            addCriterion("idcard_front =", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotEqualTo(String value) {
            addCriterion("idcard_front <>", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontGreaterThan(String value) {
            addCriterion("idcard_front >", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_front >=", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLessThan(String value) {
            addCriterion("idcard_front <", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLessThanOrEqualTo(String value) {
            addCriterion("idcard_front <=", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontLike(String value) {
            addCriterion("idcard_front like", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotLike(String value) {
            addCriterion("idcard_front not like", value, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontIn(List<String> values) {
            addCriterion("idcard_front in", values, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotIn(List<String> values) {
            addCriterion("idcard_front not in", values, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontBetween(String value1, String value2) {
            addCriterion("idcard_front between", value1, value2, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontNotBetween(String value1, String value2) {
            addCriterion("idcard_front not between", value1, value2, "idcardFront");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseIsNull() {
            addCriterion("idcard_reverse is null");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseIsNotNull() {
            addCriterion("idcard_reverse is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseEqualTo(String value) {
            addCriterion("idcard_reverse =", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseNotEqualTo(String value) {
            addCriterion("idcard_reverse <>", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseGreaterThan(String value) {
            addCriterion("idcard_reverse >", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_reverse >=", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseLessThan(String value) {
            addCriterion("idcard_reverse <", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseLessThanOrEqualTo(String value) {
            addCriterion("idcard_reverse <=", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseLike(String value) {
            addCriterion("idcard_reverse like", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseNotLike(String value) {
            addCriterion("idcard_reverse not like", value, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseIn(List<String> values) {
            addCriterion("idcard_reverse in", values, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseNotIn(List<String> values) {
            addCriterion("idcard_reverse not in", values, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseBetween(String value1, String value2) {
            addCriterion("idcard_reverse between", value1, value2, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardReverseNotBetween(String value1, String value2) {
            addCriterion("idcard_reverse not between", value1, value2, "idcardReverse");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andQuotaAllIsNull() {
            addCriterion("quota_all is null");
            return (Criteria) this;
        }

        public Criteria andQuotaAllIsNotNull() {
            addCriterion("quota_all is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaAllEqualTo(BigDecimal value) {
            addCriterion("quota_all =", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllNotEqualTo(BigDecimal value) {
            addCriterion("quota_all <>", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllGreaterThan(BigDecimal value) {
            addCriterion("quota_all >", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_all >=", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllLessThan(BigDecimal value) {
            addCriterion("quota_all <", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_all <=", value, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllIn(List<BigDecimal> values) {
            addCriterion("quota_all in", values, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllNotIn(List<BigDecimal> values) {
            addCriterion("quota_all not in", values, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_all between", value1, value2, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaAllNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_all not between", value1, value2, "quotaAll");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftIsNull() {
            addCriterion("quota_left is null");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftIsNotNull() {
            addCriterion("quota_left is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftEqualTo(BigDecimal value) {
            addCriterion("quota_left =", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftNotEqualTo(BigDecimal value) {
            addCriterion("quota_left <>", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftGreaterThan(BigDecimal value) {
            addCriterion("quota_left >", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_left >=", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftLessThan(BigDecimal value) {
            addCriterion("quota_left <", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quota_left <=", value, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftIn(List<BigDecimal> values) {
            addCriterion("quota_left in", values, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftNotIn(List<BigDecimal> values) {
            addCriterion("quota_left not in", values, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_left between", value1, value2, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andQuotaLeftNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quota_left not between", value1, value2, "quotaLeft");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdIsNull() {
            addCriterion("school_location_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdIsNotNull() {
            addCriterion("school_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdEqualTo(Integer value) {
            addCriterion("school_location_id =", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdNotEqualTo(Integer value) {
            addCriterion("school_location_id <>", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdGreaterThan(Integer value) {
            addCriterion("school_location_id >", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_location_id >=", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdLessThan(Integer value) {
            addCriterion("school_location_id <", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("school_location_id <=", value, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdIn(List<Integer> values) {
            addCriterion("school_location_id in", values, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdNotIn(List<Integer> values) {
            addCriterion("school_location_id not in", values, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("school_location_id between", value1, value2, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("school_location_id not between", value1, value2, "schoolLocationId");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIsNull() {
            addCriterion("school_location is null");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIsNotNull() {
            addCriterion("school_location is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationEqualTo(String value) {
            addCriterion("school_location =", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationNotEqualTo(String value) {
            addCriterion("school_location <>", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationGreaterThan(String value) {
            addCriterion("school_location >", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationGreaterThanOrEqualTo(String value) {
            addCriterion("school_location >=", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationLessThan(String value) {
            addCriterion("school_location <", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationLessThanOrEqualTo(String value) {
            addCriterion("school_location <=", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationLike(String value) {
            addCriterion("school_location like", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationNotLike(String value) {
            addCriterion("school_location not like", value, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationIn(List<String> values) {
            addCriterion("school_location in", values, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationNotIn(List<String> values) {
            addCriterion("school_location not in", values, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationBetween(String value1, String value2) {
            addCriterion("school_location between", value1, value2, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andSchoolLocationNotBetween(String value1, String value2) {
            addCriterion("school_location not between", value1, value2, "schoolLocation");
            return (Criteria) this;
        }

        public Criteria andOriginIsNull() {
            addCriterion("origin is null");
            return (Criteria) this;
        }

        public Criteria andOriginIsNotNull() {
            addCriterion("origin is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEqualTo(String value) {
            addCriterion("origin =", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotEqualTo(String value) {
            addCriterion("origin <>", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThan(String value) {
            addCriterion("origin >", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThanOrEqualTo(String value) {
            addCriterion("origin >=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThan(String value) {
            addCriterion("origin <", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThanOrEqualTo(String value) {
            addCriterion("origin <=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLike(String value) {
            addCriterion("origin like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotLike(String value) {
            addCriterion("origin not like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginIn(List<String> values) {
            addCriterion("origin in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotIn(List<String> values) {
            addCriterion("origin not in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginBetween(String value1, String value2) {
            addCriterion("origin between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotBetween(String value1, String value2) {
            addCriterion("origin not between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andHomesiteIsNull() {
            addCriterion("homesite is null");
            return (Criteria) this;
        }

        public Criteria andHomesiteIsNotNull() {
            addCriterion("homesite is not null");
            return (Criteria) this;
        }

        public Criteria andHomesiteEqualTo(String value) {
            addCriterion("homesite =", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteNotEqualTo(String value) {
            addCriterion("homesite <>", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteGreaterThan(String value) {
            addCriterion("homesite >", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteGreaterThanOrEqualTo(String value) {
            addCriterion("homesite >=", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteLessThan(String value) {
            addCriterion("homesite <", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteLessThanOrEqualTo(String value) {
            addCriterion("homesite <=", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteLike(String value) {
            addCriterion("homesite like", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteNotLike(String value) {
            addCriterion("homesite not like", value, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteIn(List<String> values) {
            addCriterion("homesite in", values, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteNotIn(List<String> values) {
            addCriterion("homesite not in", values, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteBetween(String value1, String value2) {
            addCriterion("homesite between", value1, value2, "homesite");
            return (Criteria) this;
        }

        public Criteria andHomesiteNotBetween(String value1, String value2) {
            addCriterion("homesite not between", value1, value2, "homesite");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNull() {
            addCriterion("auth_status is null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNotNull() {
            addCriterion("auth_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusEqualTo(Integer value) {
            addCriterion("auth_status =", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotEqualTo(Integer value) {
            addCriterion("auth_status <>", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThan(Integer value) {
            addCriterion("auth_status >", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_status >=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThan(Integer value) {
            addCriterion("auth_status <", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThanOrEqualTo(Integer value) {
            addCriterion("auth_status <=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIn(List<Integer> values) {
            addCriterion("auth_status in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotIn(List<Integer> values) {
            addCriterion("auth_status not in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusBetween(Integer value1, Integer value2) {
            addCriterion("auth_status between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_status not between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andPresentIsNull() {
            addCriterion("present is null");
            return (Criteria) this;
        }

        public Criteria andPresentIsNotNull() {
            addCriterion("present is not null");
            return (Criteria) this;
        }

        public Criteria andPresentEqualTo(BigDecimal value) {
            addCriterion("present =", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentNotEqualTo(BigDecimal value) {
            addCriterion("present <>", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentGreaterThan(BigDecimal value) {
            addCriterion("present >", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("present >=", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentLessThan(BigDecimal value) {
            addCriterion("present <", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("present <=", value, "present");
            return (Criteria) this;
        }

        public Criteria andPresentIn(List<BigDecimal> values) {
            addCriterion("present in", values, "present");
            return (Criteria) this;
        }

        public Criteria andPresentNotIn(List<BigDecimal> values) {
            addCriterion("present not in", values, "present");
            return (Criteria) this;
        }

        public Criteria andPresentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("present between", value1, value2, "present");
            return (Criteria) this;
        }

        public Criteria andPresentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("present not between", value1, value2, "present");
            return (Criteria) this;
        }

        public Criteria andWishPlasticIsNull() {
            addCriterion("wish_plastic is null");
            return (Criteria) this;
        }

        public Criteria andWishPlasticIsNotNull() {
            addCriterion("wish_plastic is not null");
            return (Criteria) this;
        }

        public Criteria andWishPlasticEqualTo(Integer value) {
            addCriterion("wish_plastic =", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticNotEqualTo(Integer value) {
            addCriterion("wish_plastic <>", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticGreaterThan(Integer value) {
            addCriterion("wish_plastic >", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticGreaterThanOrEqualTo(Integer value) {
            addCriterion("wish_plastic >=", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticLessThan(Integer value) {
            addCriterion("wish_plastic <", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticLessThanOrEqualTo(Integer value) {
            addCriterion("wish_plastic <=", value, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticIn(List<Integer> values) {
            addCriterion("wish_plastic in", values, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticNotIn(List<Integer> values) {
            addCriterion("wish_plastic not in", values, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticBetween(Integer value1, Integer value2) {
            addCriterion("wish_plastic between", value1, value2, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andWishPlasticNotBetween(Integer value1, Integer value2) {
            addCriterion("wish_plastic not between", value1, value2, "wishPlastic");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkIsNull() {
            addCriterion("school_remark is null");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkIsNotNull() {
            addCriterion("school_remark is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkEqualTo(String value) {
            addCriterion("school_remark =", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkNotEqualTo(String value) {
            addCriterion("school_remark <>", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkGreaterThan(String value) {
            addCriterion("school_remark >", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("school_remark >=", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkLessThan(String value) {
            addCriterion("school_remark <", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkLessThanOrEqualTo(String value) {
            addCriterion("school_remark <=", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkLike(String value) {
            addCriterion("school_remark like", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkNotLike(String value) {
            addCriterion("school_remark not like", value, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkIn(List<String> values) {
            addCriterion("school_remark in", values, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkNotIn(List<String> values) {
            addCriterion("school_remark not in", values, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkBetween(String value1, String value2) {
            addCriterion("school_remark between", value1, value2, "schoolRemark");
            return (Criteria) this;
        }

        public Criteria andSchoolRemarkNotBetween(String value1, String value2) {
            addCriterion("school_remark not between", value1, value2, "schoolRemark");
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelIsNull() {
            addCriterion("school_level is null");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelIsNotNull() {
            addCriterion("school_level is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelEqualTo(String value) {
            addCriterion("school_level =", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelNotEqualTo(String value) {
            addCriterion("school_level <>", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelGreaterThan(String value) {
            addCriterion("school_level >", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelGreaterThanOrEqualTo(String value) {
            addCriterion("school_level >=", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelLessThan(String value) {
            addCriterion("school_level <", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelLessThanOrEqualTo(String value) {
            addCriterion("school_level <=", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelLike(String value) {
            addCriterion("school_level like", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelNotLike(String value) {
            addCriterion("school_level not like", value, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelIn(List<String> values) {
            addCriterion("school_level in", values, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelNotIn(List<String> values) {
            addCriterion("school_level not in", values, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelBetween(String value1, String value2) {
            addCriterion("school_level between", value1, value2, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andSchoolLevelNotBetween(String value1, String value2) {
            addCriterion("school_level not between", value1, value2, "schoolLevel");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsIsNull() {
            addCriterion("scholastic_years is null");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsIsNotNull() {
            addCriterion("scholastic_years is not null");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsEqualTo(Integer value) {
            addCriterion("scholastic_years =", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsNotEqualTo(Integer value) {
            addCriterion("scholastic_years <>", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsGreaterThan(Integer value) {
            addCriterion("scholastic_years >", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsGreaterThanOrEqualTo(Integer value) {
            addCriterion("scholastic_years >=", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsLessThan(Integer value) {
            addCriterion("scholastic_years <", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsLessThanOrEqualTo(Integer value) {
            addCriterion("scholastic_years <=", value, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsIn(List<Integer> values) {
            addCriterion("scholastic_years in", values, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsNotIn(List<Integer> values) {
            addCriterion("scholastic_years not in", values, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsBetween(Integer value1, Integer value2) {
            addCriterion("scholastic_years between", value1, value2, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andScholasticYearsNotBetween(Integer value1, Integer value2) {
            addCriterion("scholastic_years not between", value1, value2, "scholasticYears");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(String value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(String value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(String value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(String value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(String value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(String value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLike(String value) {
            addCriterion("salary like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotLike(String value) {
            addCriterion("salary not like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<String> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<String> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(String value1, String value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(String value1, String value2) {
            addCriterion("salary not between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andFacultyIsNull() {
            addCriterion("faculty is null");
            return (Criteria) this;
        }

        public Criteria andFacultyIsNotNull() {
            addCriterion("faculty is not null");
            return (Criteria) this;
        }

        public Criteria andFacultyEqualTo(String value) {
            addCriterion("faculty =", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyNotEqualTo(String value) {
            addCriterion("faculty <>", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyGreaterThan(String value) {
            addCriterion("faculty >", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyGreaterThanOrEqualTo(String value) {
            addCriterion("faculty >=", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyLessThan(String value) {
            addCriterion("faculty <", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyLessThanOrEqualTo(String value) {
            addCriterion("faculty <=", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyLike(String value) {
            addCriterion("faculty like", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyNotLike(String value) {
            addCriterion("faculty not like", value, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyIn(List<String> values) {
            addCriterion("faculty in", values, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyNotIn(List<String> values) {
            addCriterion("faculty not in", values, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyBetween(String value1, String value2) {
            addCriterion("faculty between", value1, value2, "faculty");
            return (Criteria) this;
        }

        public Criteria andFacultyNotBetween(String value1, String value2) {
            addCriterion("faculty not between", value1, value2, "faculty");
            return (Criteria) this;
        }

        public Criteria andSpecialityIsNull() {
            addCriterion("speciality is null");
            return (Criteria) this;
        }

        public Criteria andSpecialityIsNotNull() {
            addCriterion("speciality is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialityEqualTo(String value) {
            addCriterion("speciality =", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotEqualTo(String value) {
            addCriterion("speciality <>", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityGreaterThan(String value) {
            addCriterion("speciality >", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityGreaterThanOrEqualTo(String value) {
            addCriterion("speciality >=", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLessThan(String value) {
            addCriterion("speciality <", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLessThanOrEqualTo(String value) {
            addCriterion("speciality <=", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityLike(String value) {
            addCriterion("speciality like", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotLike(String value) {
            addCriterion("speciality not like", value, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityIn(List<String> values) {
            addCriterion("speciality in", values, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotIn(List<String> values) {
            addCriterion("speciality not in", values, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityBetween(String value1, String value2) {
            addCriterion("speciality between", value1, value2, "speciality");
            return (Criteria) this;
        }

        public Criteria andSpecialityNotBetween(String value1, String value2) {
            addCriterion("speciality not between", value1, value2, "speciality");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Long value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Long value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Long value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Long value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Long value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Long> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Long> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Long value1, Long value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Long value1, Long value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdIsNull() {
            addCriterion("social_insurance_id is null");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdIsNotNull() {
            addCriterion("social_insurance_id is not null");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdEqualTo(String value) {
            addCriterion("social_insurance_id =", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdNotEqualTo(String value) {
            addCriterion("social_insurance_id <>", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdGreaterThan(String value) {
            addCriterion("social_insurance_id >", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdGreaterThanOrEqualTo(String value) {
            addCriterion("social_insurance_id >=", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdLessThan(String value) {
            addCriterion("social_insurance_id <", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdLessThanOrEqualTo(String value) {
            addCriterion("social_insurance_id <=", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdLike(String value) {
            addCriterion("social_insurance_id like", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdNotLike(String value) {
            addCriterion("social_insurance_id not like", value, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdIn(List<String> values) {
            addCriterion("social_insurance_id in", values, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdNotIn(List<String> values) {
            addCriterion("social_insurance_id not in", values, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdBetween(String value1, String value2) {
            addCriterion("social_insurance_id between", value1, value2, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andSocialInsuranceIdNotBetween(String value1, String value2) {
            addCriterion("social_insurance_id not between", value1, value2, "socialInsuranceId");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkIsNull() {
            addCriterion("work_remark is null");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkIsNotNull() {
            addCriterion("work_remark is not null");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkEqualTo(String value) {
            addCriterion("work_remark =", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkNotEqualTo(String value) {
            addCriterion("work_remark <>", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkGreaterThan(String value) {
            addCriterion("work_remark >", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("work_remark >=", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkLessThan(String value) {
            addCriterion("work_remark <", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkLessThanOrEqualTo(String value) {
            addCriterion("work_remark <=", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkLike(String value) {
            addCriterion("work_remark like", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkNotLike(String value) {
            addCriterion("work_remark not like", value, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkIn(List<String> values) {
            addCriterion("work_remark in", values, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkNotIn(List<String> values) {
            addCriterion("work_remark not in", values, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkBetween(String value1, String value2) {
            addCriterion("work_remark between", value1, value2, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkRemarkNotBetween(String value1, String value2) {
            addCriterion("work_remark not between", value1, value2, "workRemark");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIsNull() {
            addCriterion("work_years is null");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIsNotNull() {
            addCriterion("work_years is not null");
            return (Criteria) this;
        }

        public Criteria andWorkYearsEqualTo(String value) {
            addCriterion("work_years =", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotEqualTo(String value) {
            addCriterion("work_years <>", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsGreaterThan(String value) {
            addCriterion("work_years >", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsGreaterThanOrEqualTo(String value) {
            addCriterion("work_years >=", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLessThan(String value) {
            addCriterion("work_years <", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLessThanOrEqualTo(String value) {
            addCriterion("work_years <=", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLike(String value) {
            addCriterion("work_years like", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotLike(String value) {
            addCriterion("work_years not like", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIn(List<String> values) {
            addCriterion("work_years in", values, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotIn(List<String> values) {
            addCriterion("work_years not in", values, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsBetween(String value1, String value2) {
            addCriterion("work_years between", value1, value2, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotBetween(String value1, String value2) {
            addCriterion("work_years not between", value1, value2, "workYears");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
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