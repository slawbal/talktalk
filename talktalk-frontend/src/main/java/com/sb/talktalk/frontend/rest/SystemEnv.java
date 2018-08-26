package com.sb.talktalk.frontend.rest;

import org.apache.commons.lang3.StringUtils;

public class SystemEnv {

    public static String getServerAddress(final String envName, final String defaultValue){
        String envVal = System.getenv(envName);
        return StringUtils.isEmpty(envVal) ? defaultValue : envVal;
    }
}
