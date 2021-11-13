package com.cherri.directpaydemo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@Jacksonized
public class Refund {
    @Getter
    @JsonProperty("partner_key") private String partnerKey;
    @Getter @JsonProperty("rec_trade_id") private String recTradeId;
}
