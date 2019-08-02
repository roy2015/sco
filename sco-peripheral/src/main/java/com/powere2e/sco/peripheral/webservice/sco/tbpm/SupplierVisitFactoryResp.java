package com.powere2e.sco.peripheral.webservice.sco.tbpm;

/**
 * 巡厂巡检接口返回VO
 */
public class SupplierVisitFactoryResp {
    private int status;
    private String msg;
    private boolean success;

    private SupplierVisitFactoryInnerVO[] data;

    public static class SupplierVisitFactoryInnerVO {
        private String supplycode;//供应商编码
        private String factoryArea;//占地面积
        private String workshopArea;//车间面积
        private String numberOfCompany;//企业总人数
        private String annualAsset;//年总产值
        private String visitTime;//巡厂日期,格式为(yyyy-MM-dd HH:mm:ss)
        private String fullScore;//满分
        private String standScore;//合格份
        private String visitScore;//供应商巡厂得分
        private String visitOpinion;//巡检意见
        private String visitor;//巡检负责人

        public String getSupplycode() {
            return supplycode;
        }

        public void setSupplycode(String supplycode) {
            this.supplycode = supplycode;
        }

        public String getFactoryArea() {
            return factoryArea;
        }

        public void setFactoryArea(String factoryArea) {
            this.factoryArea = factoryArea;
        }

        public String getWorkshopArea() {
            return workshopArea;
        }

        public void setWorkshopArea(String workshopArea) {
            this.workshopArea = workshopArea;
        }

        public String getNumberOfCompany() {
            return numberOfCompany;
        }

        public void setNumberOfCompany(String numberOfCompany) {
            this.numberOfCompany = numberOfCompany;
        }

        public String getAnnualAsset() {
            return annualAsset;
        }

        public void setAnnualAsset(String annualAsset) {
            this.annualAsset = annualAsset;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public String getFullScore() {
            return fullScore;
        }

        public void setFullScore(String fullScore) {
            this.fullScore = fullScore;
        }

        public String getStandScore() {
            return standScore;
        }

        public void setStandScore(String standScore) {
            this.standScore = standScore;
        }

        public String getVisitScore() {
            return visitScore;
        }

        public void setVisitScore(String visitScore) {
            this.visitScore = visitScore;
        }

        public String getVisitOpinion() {
            return visitOpinion;
        }

        public void setVisitOpinion(String visitOpinion) {
            this.visitOpinion = visitOpinion;
        }

        public String getVisitor() {
            return visitor;
        }

        public void setVisitor(String visitor) {
            this.visitor = visitor;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SupplierVisitFactoryInnerVO[] getData() {
        return data;
    }

    public void setData(SupplierVisitFactoryInnerVO[] data) {
        this.data = data;
    }
}
