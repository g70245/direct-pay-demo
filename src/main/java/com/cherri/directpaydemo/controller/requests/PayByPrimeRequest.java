package com.cherri.directpaydemo.controller.requests;

import lombok.Getter;
import lombok.Setter;

public class PayByPrimeRequest {
    @Getter @Setter private String prime;
    @Getter @Setter private boolean threeDomainSecure = false;
}
