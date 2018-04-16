/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.util;

import java.text.DecimalFormat;

/**
 *
 * @author tomko
 */
public class DoubleUtil {

    public static String toString(double d) {
        if (d % 1 == 0) {
            return Integer.toString((int) d);
        }
        //add into a config
        DecimalFormat df = new DecimalFormat("#.######");
        return df.format(d);
    }

}
