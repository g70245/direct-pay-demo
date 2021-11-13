package com.cherri.directpaydemo.controller;

import com.cherri.directpaydemo.controller.requests.TransactionRecordsRequest;
import com.cherri.directpaydemo.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class TransactionRecordController {
    @Autowired
    private TransactionRecordService transactionRecordService;

    @PostMapping("/transactions")
    public String payByPrime(@RequestBody TransactionRecordsRequest req) throws IOException {
        return transactionRecordService.getRecords();
    }
}
