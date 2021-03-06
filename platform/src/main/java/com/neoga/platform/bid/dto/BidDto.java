package com.neoga.platform.bid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;

@Data
@Relation(collectionRelation = "bidList")
public class BidDto {
    @JsonProperty("bidId")
    private Long id;
    private Register member;
    private Long itemId;
    private int price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
}
