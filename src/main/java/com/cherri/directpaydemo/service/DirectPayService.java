package com.cherri.directpaydemo.service;

import com.cherri.directpaydemo.AppConfig;
import com.cherri.directpaydemo.utils.ApiRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherri.directpaydemo.entities.PayByPrime;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DirectPayService {

    private AppConfig appConfig;
    private PayByPrime.ResultUrl resultUrl;
    private PayByPrime.PayByPrimeBuilder payByPrimeBuilder;

    @Autowired
    public DirectPayService(AppConfig appConfig) {
        this.appConfig = appConfig;
        resultUrl = PayByPrime.ResultUrl.builder()
                .frontendRedirectUrl(appConfig.FRONTEND_REDIRECT_URL)
                .backendNotifyUrl(appConfig.BACKEND_NOTIFY_URL)
                .build();

        payByPrimeBuilder = PayByPrime.builder()
                .partnerKey(appConfig.PARTNER_KEY)
                .merchantId(appConfig.MERCHANT_CTBC_ID);

        // hard-coded for demo
        PayByPrime.Cardholder cardholder = PayByPrime.Cardholder.builder()
                .phoneNumber("+886923456789")
                .name("123")
                .email("LittleMing@Wang.com")
                .build();

        payByPrimeBuilder = payByPrimeBuilder
                .cardholder(cardholder)
                .resultUrl(resultUrl)
                .details("A bag of oranges.");
    }

    public String payByPrime(String prime, boolean threeDomainSecure) throws IOException {
        PayByPrime payByPrime = payByPrimeBuilder
                .prime(prime)
                .amount(generateAmount())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String payByPrimeJsonStr = mapper.writeValueAsString(payByPrime);
        //return payByPrimeJsonStr;
        return ApiRequestUtil.post(payByPrimeJsonStr, appConfig.PAY_BY_PRIME_URL, appConfig.PARTNER_KEY);
    }

    private int generateAmount() {
        return ThreadLocalRandom.current().nextInt(1, 100 + 1);
    }
}
