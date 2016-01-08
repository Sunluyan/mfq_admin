package com.mfq.admin.web.bean.example;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductDetailExample() {
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

        public Criteria andConsumeStepIsNull() {
            addCriterion("consume_step is null");
            return (Criteria) this;
        }

        public Criteria andConsumeStepIsNotNull() {
            addCriterion("consume_step is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeStepEqualTo(String value) {
            addCriterion("consume_step =", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepNotEqualTo(String value) {
            addCriterion("consume_step <>", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepGreaterThan(String value) {
            addCriterion("consume_step >", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepGreaterThanOrEqualTo(String value) {
            addCriterion("consume_step >=", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepLessThan(String value) {
            addCriterion("consume_step <", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepLessThanOrEqualTo(String value) {
            addCriterion("consume_step <=", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepLike(String value) {
            addCriterion("consume_step like", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepNotLike(String value) {
            addCriterion("consume_step not like", value, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepIn(List<String> values) {
            addCriterion("consume_step in", values, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepNotIn(List<String> values) {
            addCriterion("consume_step not in", values, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepBetween(String value1, String value2) {
            addCriterion("consume_step between", value1, value2, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andConsumeStepNotBetween(String value1, String value2) {
            addCriterion("consume_step not between", value1, value2, "consumeStep");
            return (Criteria) this;
        }

        public Criteria andReserveIsNull() {
            addCriterion("reserve is null");
            return (Criteria) this;
        }

        public Criteria andReserveIsNotNull() {
            addCriterion("reserve is not null");
            return (Criteria) this;
        }

        public Criteria andReserveEqualTo(String value) {
            addCriterion("reserve =", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotEqualTo(String value) {
            addCriterion("reserve <>", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveGreaterThan(String value) {
            addCriterion("reserve >", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveGreaterThanOrEqualTo(String value) {
            addCriterion("reserve >=", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveLessThan(String value) {
            addCriterion("reserve <", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveLessThanOrEqualTo(String value) {
            addCriterion("reserve <=", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveLike(String value) {
            addCriterion("reserve like", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotLike(String value) {
            addCriterion("reserve not like", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveIn(List<String> values) {
            addCriterion("reserve in", values, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotIn(List<String> values) {
            addCriterion("reserve not in", values, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveBetween(String value1, String value2) {
            addCriterion("reserve between", value1, value2, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotBetween(String value1, String value2) {
            addCriterion("reserve not between", value1, value2, "reserve");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteIsNull() {
            addCriterion("special_note is null");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteIsNotNull() {
            addCriterion("special_note is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteEqualTo(String value) {
            addCriterion("special_note =", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteNotEqualTo(String value) {
            addCriterion("special_note <>", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteGreaterThan(String value) {
            addCriterion("special_note >", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteGreaterThanOrEqualTo(String value) {
            addCriterion("special_note >=", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteLessThan(String value) {
            addCriterion("special_note <", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteLessThanOrEqualTo(String value) {
            addCriterion("special_note <=", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteLike(String value) {
            addCriterion("special_note like", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteNotLike(String value) {
            addCriterion("special_note not like", value, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteIn(List<String> values) {
            addCriterion("special_note in", values, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteNotIn(List<String> values) {
            addCriterion("special_note not in", values, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteBetween(String value1, String value2) {
            addCriterion("special_note between", value1, value2, "specialNote");
            return (Criteria) this;
        }

        public Criteria andSpecialNoteNotBetween(String value1, String value2) {
            addCriterion("special_note not between", value1, value2, "specialNote");
            return (Criteria) this;
        }

        public Criteria andCureMeansIsNull() {
            addCriterion("cure_means is null");
            return (Criteria) this;
        }

        public Criteria andCureMeansIsNotNull() {
            addCriterion("cure_means is not null");
            return (Criteria) this;
        }

        public Criteria andCureMeansEqualTo(String value) {
            addCriterion("cure_means =", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansNotEqualTo(String value) {
            addCriterion("cure_means <>", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansGreaterThan(String value) {
            addCriterion("cure_means >", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansGreaterThanOrEqualTo(String value) {
            addCriterion("cure_means >=", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansLessThan(String value) {
            addCriterion("cure_means <", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansLessThanOrEqualTo(String value) {
            addCriterion("cure_means <=", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansLike(String value) {
            addCriterion("cure_means like", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansNotLike(String value) {
            addCriterion("cure_means not like", value, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansIn(List<String> values) {
            addCriterion("cure_means in", values, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansNotIn(List<String> values) {
            addCriterion("cure_means not in", values, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansBetween(String value1, String value2) {
            addCriterion("cure_means between", value1, value2, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureMeansNotBetween(String value1, String value2) {
            addCriterion("cure_means not between", value1, value2, "cureMeans");
            return (Criteria) this;
        }

        public Criteria andCureDurIsNull() {
            addCriterion("cure_dur is null");
            return (Criteria) this;
        }

        public Criteria andCureDurIsNotNull() {
            addCriterion("cure_dur is not null");
            return (Criteria) this;
        }

        public Criteria andCureDurEqualTo(String value) {
            addCriterion("cure_dur =", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurNotEqualTo(String value) {
            addCriterion("cure_dur <>", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurGreaterThan(String value) {
            addCriterion("cure_dur >", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurGreaterThanOrEqualTo(String value) {
            addCriterion("cure_dur >=", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurLessThan(String value) {
            addCriterion("cure_dur <", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurLessThanOrEqualTo(String value) {
            addCriterion("cure_dur <=", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurLike(String value) {
            addCriterion("cure_dur like", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurNotLike(String value) {
            addCriterion("cure_dur not like", value, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurIn(List<String> values) {
            addCriterion("cure_dur in", values, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurNotIn(List<String> values) {
            addCriterion("cure_dur not in", values, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurBetween(String value1, String value2) {
            addCriterion("cure_dur between", value1, value2, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureDurNotBetween(String value1, String value2) {
            addCriterion("cure_dur not between", value1, value2, "cureDur");
            return (Criteria) this;
        }

        public Criteria andCureHospitalIsNull() {
            addCriterion("cure_hospital is null");
            return (Criteria) this;
        }

        public Criteria andCureHospitalIsNotNull() {
            addCriterion("cure_hospital is not null");
            return (Criteria) this;
        }

        public Criteria andCureHospitalEqualTo(String value) {
            addCriterion("cure_hospital =", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalNotEqualTo(String value) {
            addCriterion("cure_hospital <>", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalGreaterThan(String value) {
            addCriterion("cure_hospital >", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalGreaterThanOrEqualTo(String value) {
            addCriterion("cure_hospital >=", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalLessThan(String value) {
            addCriterion("cure_hospital <", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalLessThanOrEqualTo(String value) {
            addCriterion("cure_hospital <=", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalLike(String value) {
            addCriterion("cure_hospital like", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalNotLike(String value) {
            addCriterion("cure_hospital not like", value, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalIn(List<String> values) {
            addCriterion("cure_hospital in", values, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalNotIn(List<String> values) {
            addCriterion("cure_hospital not in", values, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalBetween(String value1, String value2) {
            addCriterion("cure_hospital between", value1, value2, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andCureHospitalNotBetween(String value1, String value2) {
            addCriterion("cure_hospital not between", value1, value2, "cureHospital");
            return (Criteria) this;
        }

        public Criteria andRecoverDurIsNull() {
            addCriterion("recover_dur is null");
            return (Criteria) this;
        }

        public Criteria andRecoverDurIsNotNull() {
            addCriterion("recover_dur is not null");
            return (Criteria) this;
        }

        public Criteria andRecoverDurEqualTo(String value) {
            addCriterion("recover_dur =", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurNotEqualTo(String value) {
            addCriterion("recover_dur <>", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurGreaterThan(String value) {
            addCriterion("recover_dur >", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurGreaterThanOrEqualTo(String value) {
            addCriterion("recover_dur >=", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurLessThan(String value) {
            addCriterion("recover_dur <", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurLessThanOrEqualTo(String value) {
            addCriterion("recover_dur <=", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurLike(String value) {
            addCriterion("recover_dur like", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurNotLike(String value) {
            addCriterion("recover_dur not like", value, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurIn(List<String> values) {
            addCriterion("recover_dur in", values, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurNotIn(List<String> values) {
            addCriterion("recover_dur not in", values, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurBetween(String value1, String value2) {
            addCriterion("recover_dur between", value1, value2, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andRecoverDurNotBetween(String value1, String value2) {
            addCriterion("recover_dur not between", value1, value2, "recoverDur");
            return (Criteria) this;
        }

        public Criteria andCureNumIsNull() {
            addCriterion("cure_num is null");
            return (Criteria) this;
        }

        public Criteria andCureNumIsNotNull() {
            addCriterion("cure_num is not null");
            return (Criteria) this;
        }

        public Criteria andCureNumEqualTo(String value) {
            addCriterion("cure_num =", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumNotEqualTo(String value) {
            addCriterion("cure_num <>", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumGreaterThan(String value) {
            addCriterion("cure_num >", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumGreaterThanOrEqualTo(String value) {
            addCriterion("cure_num >=", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumLessThan(String value) {
            addCriterion("cure_num <", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumLessThanOrEqualTo(String value) {
            addCriterion("cure_num <=", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumLike(String value) {
            addCriterion("cure_num like", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumNotLike(String value) {
            addCriterion("cure_num not like", value, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumIn(List<String> values) {
            addCriterion("cure_num in", values, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumNotIn(List<String> values) {
            addCriterion("cure_num not in", values, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumBetween(String value1, String value2) {
            addCriterion("cure_num between", value1, value2, "cureNum");
            return (Criteria) this;
        }

        public Criteria andCureNumNotBetween(String value1, String value2) {
            addCriterion("cure_num not between", value1, value2, "cureNum");
            return (Criteria) this;
        }

        public Criteria andAnesMethodIsNull() {
            addCriterion("anes_method is null");
            return (Criteria) this;
        }

        public Criteria andAnesMethodIsNotNull() {
            addCriterion("anes_method is not null");
            return (Criteria) this;
        }

        public Criteria andAnesMethodEqualTo(String value) {
            addCriterion("anes_method =", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodNotEqualTo(String value) {
            addCriterion("anes_method <>", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodGreaterThan(String value) {
            addCriterion("anes_method >", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodGreaterThanOrEqualTo(String value) {
            addCriterion("anes_method >=", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodLessThan(String value) {
            addCriterion("anes_method <", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodLessThanOrEqualTo(String value) {
            addCriterion("anes_method <=", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodLike(String value) {
            addCriterion("anes_method like", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodNotLike(String value) {
            addCriterion("anes_method not like", value, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodIn(List<String> values) {
            addCriterion("anes_method in", values, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodNotIn(List<String> values) {
            addCriterion("anes_method not in", values, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodBetween(String value1, String value2) {
            addCriterion("anes_method between", value1, value2, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andAnesMethodNotBetween(String value1, String value2) {
            addCriterion("anes_method not between", value1, value2, "anesMethod");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelIsNull() {
            addCriterion("doctor_level is null");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelIsNotNull() {
            addCriterion("doctor_level is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelEqualTo(String value) {
            addCriterion("doctor_level =", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelNotEqualTo(String value) {
            addCriterion("doctor_level <>", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelGreaterThan(String value) {
            addCriterion("doctor_level >", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelGreaterThanOrEqualTo(String value) {
            addCriterion("doctor_level >=", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelLessThan(String value) {
            addCriterion("doctor_level <", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelLessThanOrEqualTo(String value) {
            addCriterion("doctor_level <=", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelLike(String value) {
            addCriterion("doctor_level like", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelNotLike(String value) {
            addCriterion("doctor_level not like", value, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelIn(List<String> values) {
            addCriterion("doctor_level in", values, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelNotIn(List<String> values) {
            addCriterion("doctor_level not in", values, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelBetween(String value1, String value2) {
            addCriterion("doctor_level between", value1, value2, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andDoctorLevelNotBetween(String value1, String value2) {
            addCriterion("doctor_level not between", value1, value2, "doctorLevel");
            return (Criteria) this;
        }

        public Criteria andCureCycleIsNull() {
            addCriterion("cure_cycle is null");
            return (Criteria) this;
        }

        public Criteria andCureCycleIsNotNull() {
            addCriterion("cure_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCureCycleEqualTo(String value) {
            addCriterion("cure_cycle =", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleNotEqualTo(String value) {
            addCriterion("cure_cycle <>", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleGreaterThan(String value) {
            addCriterion("cure_cycle >", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleGreaterThanOrEqualTo(String value) {
            addCriterion("cure_cycle >=", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleLessThan(String value) {
            addCriterion("cure_cycle <", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleLessThanOrEqualTo(String value) {
            addCriterion("cure_cycle <=", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleLike(String value) {
            addCriterion("cure_cycle like", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleNotLike(String value) {
            addCriterion("cure_cycle not like", value, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleIn(List<String> values) {
            addCriterion("cure_cycle in", values, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleNotIn(List<String> values) {
            addCriterion("cure_cycle not in", values, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleBetween(String value1, String value2) {
            addCriterion("cure_cycle between", value1, value2, "cureCycle");
            return (Criteria) this;
        }

        public Criteria andCureCycleNotBetween(String value1, String value2) {
            addCriterion("cure_cycle not between", value1, value2, "cureCycle");
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