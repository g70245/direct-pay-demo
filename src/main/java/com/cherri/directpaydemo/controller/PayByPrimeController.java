package com.cherri.directpaydemo.controller;

import com.cherri.directpaydemo.controller.requests.PayByPrimeRequest;
import com.cherri.directpaydemo.service.DirectPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class PayByPrimeController {
    @Autowired
    private DirectPayService directPayService;

    @PostMapping("/pay-by-prime")
    public String payByPrime(@RequestBody PayByPrimeRequest req) throws IOException {
        return directPayService.payByPrime(req.getPrime(), req.isThreeDomainSecure());
    }
}
