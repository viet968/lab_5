package com.example.lab5_2.Utils;

import java.text.DecimalFormat;

public class FormatUtils {
    static DecimalFormat decimalFormatn = new DecimalFormat("#,##0");

    public static String formatNumber(int num){
        return decimalFormatn.format(num);
    }
}
