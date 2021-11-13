package com.cherri.directpaydemo.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@Jacksonized
public class PayByPrime {
    @Getter private String prime;
    @Getter @JsonProperty("partner_key") private String partnerKey;
    @Getter @JsonProperty("merchant_id") private String merchantId;
    @Getter private int amount;
    @Getter @JsonProperty("order_number") @JsonInclude(JsonInclude.Include.NON_NULL) private String orderNumber;
    @Getter private String details;
    @Getter private Cardholder cardholder;
    @Getter @JsonProperty("three_domain_secure") @Builder.Default private boolean threeDomainSecure = false;
    @Getter @JsonProperty("result_url") @JsonInclude(JsonInclude.Include.NON_NULL) private ResultUrl resultUrl;


    @Builder
    @AllArgsConstructor
    @Jacksonized
    public static class Cardholder {
        @Getter @JsonProperty("phone_number") private String phoneNumber;
        @Getter private String name;
        @Getter private String email;
    }

    @Builder
    @AllArgsConstructor
    @Jacksonized
    public static class ResultUrl {
        @Getter @JsonProperty("frontend_redirect_url") private String frontendRedirectUrl;
        @Getter @JsonProperty("backend_notify_url") private String backendNotifyUrl;
    }
}
