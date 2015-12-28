package com.mfq.admin.web.models;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail{
    public String name;
    public String status;
    public String postStatus;
    public String postNo;
    public boolean complete;
    public List<Refund> refunds=new ArrayList<>();
    public List<DayProduct> dayProducts=new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(final String postStatus) {
        this.postStatus = postStatus;
    }

    public String getPostNo() {
        return postNo;
    }

    public void setPostNo(final String postNo) {
        this.postNo = postNo;
    }

    public List<Refund> getRefunds() {
        return refunds;
    }

    public void setRefunds(final List<Refund> refunds) {
        this.refunds = refunds;
    }

    public List<DayProduct> getDayProducts() {
        return dayProducts;
    }

    public void setDayProducts(final List<DayProduct> dayProducts) {
        this.dayProducts = dayProducts;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(final boolean complete) {
        this.complete = complete;
    }

    public static class Refund{
        private String id;
        private String status;
        private String operator;

        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(final String status) {
            this.status = status;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(final String operator) {
            this.operator = operator;
        }
    }
    public static class DayProduct{
        public String day;
        public List<Product> buyProducts=new ArrayList<>();
        public List<Product> orderedProducts=new ArrayList<>();

        public String getDay() {
            return day;
        }

        public void setDay(final String day) {
            this.day = day;
        }

        public List<Product> getBuyProducts() {
            return buyProducts;
        }

        public void setBuyProducts(final List<Product> buyProducts) {
            this.buyProducts = buyProducts;
        }

        public List<Product> getOrderedProducts() {
            return orderedProducts;
        }

        public void setOrderedProducts(final List<Product> orderedProducts) {
            this.orderedProducts = orderedProducts;
        }
    }
    public static class Product{
        String orderId;
        String name;
        String date;
        String count;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(final String orderId) {
            this.orderId = orderId;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(final String date) {
            this.date = date;
        }

        public String getCount() {
            return count;
        }

        public void setCount(final String count) {
            this.count = count;
        }

    }


    public static String processPostStatus(String status){
        switch (status){
            case "0":
                return "未邮寄";
            case "1":
                return "无手册";
            case "2":
                return "已邮寄";
            case "3":
                return "已自取";
            default:
                return "";
        }
    }
    
    public static String processRefundStatus(String status){
        switch (status){
            case "0":
                return "正常订单";
            case "1":
                return "退款处理中";
            case "2":
                return "同意退款";
            case "3":
                return "拒绝退款";
            default:
                return "";
        }
    }



}