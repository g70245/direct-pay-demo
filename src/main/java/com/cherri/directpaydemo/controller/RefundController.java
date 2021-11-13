package com.cherri.directpaydemo.controller;

import com.cherri.directpaydemo.controller.requests.RefundRequest;
import com.cherri.directpaydemo.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class RefundController {
    @Autowired
    private RefundService refundService;

    @PostMapping("/refund")
    public String payByPrime(@RequestBody RefundRequest req) throws IOException {
        return refundService.refund(req.getRecTradeId());
    }
}
