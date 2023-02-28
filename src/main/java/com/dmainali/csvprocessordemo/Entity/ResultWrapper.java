package com.dmainali.csvprocessordemo.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class ResultWrapper {
    Map<Product,String> faultyData;
    Integer insertCount;
    Long timeTaken;
    Integer excelDataCount;
}
