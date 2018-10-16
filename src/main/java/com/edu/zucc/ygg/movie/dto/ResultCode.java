package com.edu.zucc.ygg.movie.dto;

public final class ResultCode {

    public static final String ACK = "ACK";

    public static final String NACK = "NACK";

    public static final String REDIRECT = "REDIRECT";

    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    public static final String COMMON_ERROR = "COMMON_ERROR";

    public static final String SESSION_TIME_OUT = "SESSION_TIME_OUT";

    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";

    private ResultCode() {

    }
}
