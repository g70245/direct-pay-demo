package com.cherri.directpaydemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${merchant.partnerKey}")
    public String PARTNER_KEY;

    @Value("${merchant.id.ctbc}")
    public String MERCHANT_CTBC_ID;

    @Value("${api.payByPrime.url}")
    public   String PAY_BY_PRIME_URL;

    @Value("${api.payByPrime.frontendRedirectUrl}")
    public String FRONTEND_REDIRECT_URL;

    @Value("${api.payByPrime.backendNotifyUrl}")
    public String BACKEND_NOTIFY_URL;

    @Value("${api.transaction.url}")
    public String GET_TRANSACTION_RECORDS_URL;

    @Value("${api.refund.url}")
    public String REFUND_URL;
}
