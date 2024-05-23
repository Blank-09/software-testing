package com.priyanshu.automation.secret;

import org.springframework.beans.factory.annotation.Value;

public class Secret {
    @Value("${email}")
    public static String email;

    @Value("${password}")
    public static String password;
}
