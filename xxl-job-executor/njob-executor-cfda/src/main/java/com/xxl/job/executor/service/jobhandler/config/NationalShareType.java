package com.xxl.job.executor.service.jobhandler.config;

public enum NationalShareType {

    NATIONAL_SHARE_TYPE_YPJY("T_EXC_GJ_YPJY"),    //药品经营许可
    NATIONAL_SHARE_TYPE_YPSC("T_EXC_GJ_YPSC"),    //药品生产许可
    NATIONAL_SHARE_TYPE_YPGMP("T_EXC_GJ_YPGMP"),  //药品GMP认证
    NATIONAL_SHARE_TYPE_YPGSP("T_EXC_GJ_YPGSP"),  //药品GSP认证
    NATIONAL_SHARE_TYPE_YLQXSC("T_EXC_GJ_YLQXSC"), //医疗器械生产许可（备案）
    NATIONAL_SHARE_TYPE_YLQXJY("T_EXC_GJ_YLQXJY"), //医疗器械经营
    NATIONAL_SHARE_TYPE_YLQXCPBAZC("T_EXC_GJ_YLQXCPBAZC");  //国产器械产品（一、二类）
    private final String name;

    private NationalShareType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
