package com.cherri.directpaydemo.service;

import com.cherri.directpaydemo.AppConfig;
import com.cherri.directpaydemo.entities.Refund;
import com.cherri.directpaydemo.utils.ApiRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class RefundService {
    @Autowired
    AppConfig appConfig;

    public String refund(String recTradeId) throws IOException {
        Refund refund = Refund.builder()
                .partnerKey(appConfig.PARTNER_KEY)
                .recTradeId(recTradeId)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String refundJsonStr = mapper.writeValueAsString(refund);
        return ApiRequestUtil.post(refundJsonStr, appConfig.REFUND_URL, appConfig.PARTNER_KEY);
    }
}
