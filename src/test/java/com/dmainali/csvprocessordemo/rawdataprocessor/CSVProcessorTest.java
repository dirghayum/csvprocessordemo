package com.dmainali.csvprocessordemo.rawdataprocessor;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVProcessorTest {

    CSVProcessor cvp = new CSVProcessor();

    @ParameterizedTest(name="{2}")
    @MethodSource("rawData")
    public void cleanDataTest(String data, String expectedResult, String label){
        String result = cvp.cleanUpData(data);
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> rawData(){
        return Stream.of(
                Arguments.of("\"Collection Monogram, Lace Blouse\"","Collection Monogram Lace Blouse", "withquotes"),
                Arguments.of("Oversize Detail, Denim Dress","Oversize Detail, Denim Dress", "withoutQuotes")
        );
    }


}
