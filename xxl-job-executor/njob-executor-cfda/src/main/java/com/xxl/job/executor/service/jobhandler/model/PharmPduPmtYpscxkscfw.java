package com.xxl.job.executor.service.jobhandler.model;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-04 10:01
 **/
public class PharmPduPmtYpscxkscfw implements java.io.Serializable{

    private String compId;
    private String addrDetail;
    private String productionScope;
    private String productionScopeF;
    private String throughput;
    private String calculationUnit;
    private String preparationNumber;
    private String getGmpCertificate;
    private String gmpRzScope;

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getProductionScope() {
        return productionScope;
    }

    public void setProductionScope(String productionScope) {
        this.productionScope = productionScope;
    }

    public String getProductionScopeF() {
        return productionScopeF;
    }

    public void setProductionScopeF(String productionScopeF) {
        this.productionScopeF = productionScopeF;
    }

    public String getThroughput() {
        return throughput;
    }

    public void setThroughput(String throughput) {
        this.throughput = throughput;
    }

    public String getCalculationUnit() {
        return calculationUnit;
    }

    public void setCalculationUnit(String calculationUnit) {
        this.calculationUnit = calculationUnit;
    }

    public String getPreparationNumber() {
        return preparationNumber;
    }

    public void setPreparationNumber(String preparationNumber) {
        this.preparationNumber = preparationNumber;
    }

    public String getGetGmpCertificate() {
        return getGmpCertificate;
    }

    public void setGetGmpCertificate(String getGmpCertificate) {
        this.getGmpCertificate = getGmpCertificate;
    }

    public String getGmpRzScope() {
        return gmpRzScope;
    }

    public void setGmpRzScope(String gmpRzScope) {
        this.gmpRzScope = gmpRzScope;
    }
}