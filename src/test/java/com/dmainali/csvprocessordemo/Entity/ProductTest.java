package com.dmainali.csvprocessordemo.Entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product p = new Product();


//    //Test method for calculateSize Method
//    @Test
//    public void calculateSizeNegative(){
//        p.setReviewRating(-1.55);
//        String result = p.calculateSize();
//        assertThat(result).isEqualTo("XS");
//    }
//
    @ParameterizedTest(name="calculateSize{2}")
    @MethodSource("sizeData")
    public void calculateSizeTest(double rating, String expectedResult, String label){
        p.setReviewRating(rating);
        String result = p.calculateSize();
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> sizeData(){
        return Stream.of(
                Arguments.of(-1.55, "XS", "negativeRating"),
                Arguments.of(0.99, "XS", "ratingLessThanOne"),
                Arguments.of(0, "XS", "ratingEqualsZero"),
                Arguments.of(1, "S", "ratingEqualsOne"),
                Arguments.of(1.01, "S", "ratingGreaterThanOne"),
                Arguments.of(1.99, "S", "ratingLessThanTwo"),
                Arguments.of(2, "M", "ratingEqualsTwo"),
                Arguments.of(2.01, "M", "ratingGreaterThanTwo"),
                Arguments.of(2.99, "M", "ratingLessThanThree"),
                Arguments.of(3, "L", "ratingEqualsThree"),
                Arguments.of(3.01, "L", "ratingGreaterThanThree"),
                Arguments.of(3.99, "L", "ratingLessThanFour"),
                Arguments.of(4, "XL", "ratingEqualsFour"),
                Arguments.of(4.01, "XL", "ratingGreaterThanFour"),
                Arguments.of(1007853034.099999999, "XL", "ratingLargeDecimalValue")
        );
    }

    //Test method for calculateColor Method
//    @Test
//    public void calculateColorNegative(){
//        p.setColor(-1);
//        String result = p.calculateColor();
//        assertThat(result).isEqualTo("grey");
//    }
    @ParameterizedTest(name = "calculateColor{2}")
    @MethodSource("colorData")
    public void calculateColorTest(int color, String expectedResult, String label){
        p.setColor(color);
        String result = p.calculateColor();
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> colorData(){
        return Stream.of(
                Arguments.of(-1,"grey","colorNegative"),
                Arguments.of(0,"white","colorZero"),
                Arguments.of(1,"black","colorOne"),
                Arguments.of(7,"maroon","colorSeven"),
                Arguments.of(63,"maroon","colorSevenSixAndThree"),
                Arguments.of(35,"maroon","colorSevenAndFive"),
                Arguments.of(28,"maroon","colorSevenFourAndTwo"),
                Arguments.of(21,"maroon","colorSevenAndThree"),
                Arguments.of(14,"maroon","colorSevenAndTwo"),
                Arguments.of(6,"yellow","colorSix"),
                Arguments.of(30,"yellow","colorSixFiveAndTwo"),
                Arguments.of(24,"yellow","colorSixFourAndTwo"),
                Arguments.of(18,"yellow","colorSixThreeAndTwo"),
                Arguments.of(12,"yellow","colorSixAndTwo"),
                Arguments.of(5,"red","colorFive"),
                Arguments.of(20,"red","colorFiveAndFourAndTwo"),
                Arguments.of(15,"red","colorFiveAndThree"),
                Arguments.of(10,"red","colorFiveAndTwo"),
                Arguments.of(4,"green","colorFour"),
                Arguments.of(12,"yellow","colorFourAndThreeAndTwo"),
                Arguments.of(8,"green","colorFourAndTwo"),
                Arguments.of(3,"purple","colorThree"),
                Arguments.of(6,"yellow","colorThreeAndTwo"),
                Arguments.of(2,"blue","colorTwo"),
                Arguments.of(1111111111,"grey","ColorLargeNumber")
        );
    }

}
