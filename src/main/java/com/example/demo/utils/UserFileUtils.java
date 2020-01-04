package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserFileUtils {
    public static BigDecimal calculationKB(BigDecimal size){
        return size.divide(new BigDecimal(1024),2, RoundingMode.HALF_UP);
    }
    public static BigDecimal calculationMB(BigDecimal size){
        return size.divide(new BigDecimal(1048576),2, RoundingMode.HALF_UP);
    }
    public static BigDecimal calculationGB(BigDecimal size){
        return size.divide(new BigDecimal(1073741824),2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
    }
}
