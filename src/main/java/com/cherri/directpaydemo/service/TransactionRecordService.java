package com.cherri.directpaydemo.service;

import com.cherri.directpaydemo.AppConfig;
import com.cherri.directpaydemo.entities.GetTransactionRecords;
import com.cherri.directpaydemo.utils.ApiRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionRecordService {

    @Autowired
    private AppConfig appConfig;

    public String getRecords() throws IOException {
        Instant now = Instant.now();
        Map<String, Map<String, Long>> filters = new HashMap<>();
        Map<String, Long> timeFilter = new HashMap();
        timeFilter.put("start_time", now.minus(30, ChronoUnit.DAYS).toEpochMilli());
        timeFilter.put("end_time", now.toEpochMilli());
        filters.put("time", timeFilter);

        GetTransactionRecords getTransactionRecords = GetTransactionRecords.builder()
                .partnerKey(appConfig.PARTNER_KEY)
                .filters(filters)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String getTransactionRecordsJsonStr = mapper.writeValueAsString(getTransactionRecords);
        return ApiRequestUtil.post(getTransactionRecordsJsonStr,appConfig.GET_TRANSACTION_RECORDS_URL,appConfig.PARTNER_KEY);
    }
}
