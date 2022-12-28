package com.example.project_spring_boot.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "8y/B?E(H+MbQeThWmZq4t6w9z$C&F)J@NcRfUjXn2r5u8x/A%D*G-KaPdSgVkYp3";
    public static final String REGISTRATION_PATH = "/user";
    public static final int TOKEN_EXPIRATION = 7200000;
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String GETITEMS_PATH = "/item";
    public static final String GETBIDS_PATH = "/bid";
    public static final String GETAUCTIONEVENT_PATH = "/auctionevent/active/**";
}
