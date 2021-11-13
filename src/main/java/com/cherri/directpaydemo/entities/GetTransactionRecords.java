package com.cherri.directpaydemo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Builder
@AllArgsConstructor
@Jacksonized
public class GetTransactionRecords {
    @Getter @JsonProperty("partner_key") private String partnerKey;
    @Getter private Map<String, Map<String, Long>> filters;
}
