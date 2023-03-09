package com.dmainali.csvprocessordemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    String department;
    String category;
    String subcategory;
    String sku;
    String productName;
    String brand;
    String gender;
    double priceRetail;
    double priceCurrent;
    double reviewRating;
    int reviewCount;
    String promotion;
    int color;
    LocalDateTime runDate;
    LocalDateTime insertUpdateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductName().equals(product.getProductName());
    }

    @Override
    public int hashCode() {
        final int prime = 1;
        int result = 1;
        result = result * prime + getProductName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    public String calculateColor(){
        if (color == 0) {
            return "white";
        } else if (color == 1) {
            return "black";
        } else if (color % 7 == 0) {
            return "maroon";
        } else if (color % 6 == 0) {
            return "yellow";
        } else if (color % 5 == 0) {
            return "red";
        } else if (color % 4 == 0) {
            return "green";
        } else if (color % 3 == 0) {
            return "purple";
        } else if (color % 2 == 0) {
            return "blue";
        }
        return "grey";
    }

    public String calculateSize(){
        if (reviewRating < 1) {
            return "XS";
        } else if (reviewRating < 2) {
            return "S";
        } else if (reviewRating < 3) {
            return "M";
        } else if (reviewRating < 4) {
            return "L";
        }
        return "XL";
    }
}
