package com.xxl.job.executor.service.jobhandler.config;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 17:22
 **/
public enum MedicalInstrumentsType {

    MEDICAL_INSTRUMENTS_TYPE_YLQXSCXK("T_API_JQYLQXJG_YLQXSCXK"),
    MEDICAL_INSTRUMENTS_TYPE_YLQXJYXK("T_API_JQYLQXJG_YLQXJYXK"),
    MEDICAL_INSTRUMENTS_TYPE_YLQXWTSCBA("T_API_JQYLQXJG_YLQXWTSCBA"),
    MEDICAL_INSTRUMENTS_TYPE_YLQXWLJYFWDSF("T_API_JQYLQXJG_YLQXWLJYFWDSF"),
    MEDICAL_INSTRUMENTS_TYPE_YLQXWLXSBA("T_API_JQYLQXJG_YLQXWLXSBA"),
    MEDICAL_INSTRUMENTS_TYPE_YPCKXSZM("T_EXC_GJ_YPCKXSZM");
    private final String name;

    private MedicalInstrumentsType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}