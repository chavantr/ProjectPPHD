
package com.mywings.patients.algorithms;

import java.util.ArrayList;

public class iHeart {

    public static String sysFinalResult = "";
    public static String dysFinalResult = "";
    public static String hpFinalResult = "";
    public static String chFinalResult = "";
    public static String ldlFinalResult = "";
    public static String hdlFinalResult = "";
    public static String sugarFinalResult = "";
    public static String oxyFinalResult = "";
    public static String hemoFinalResult = "";
    public static String prFinalResult = "";
    public static String qtFinalResult = "";
    public static String stressFinalResult = "";
    public static String ci = "";
    public static String VAH = "";
    public static String CVD = "";
    public static int itrCount = 6;

    public static int sysNormal = 0, sysModerate = 0, sysSevere = 0;
    public static int dysNormal = 0, dysModerate = 0, dysSevere = 0;
    public static int HrateNormal = 0, HrateModerate = 0, HrateSevere = 0;
    public static int chNormal = 0, chModerate = 0, chSevere = 0;
    public static int ldlNormal = 0, ldlSevere = 0, ldlcritical = 0;
    public static int hdlNormal = 0, hdlSevere = 0, hdlcritical = 0;
    public static int StressNormal = 0, StressSevere = 0, StressWorst = 0;
    public static int sugarNormal = 0, sugarHigh = 0, sugarDangrous = 0;
    public static int OxyNormal = 0, oxyBelowNormal = 0, oxyHypoxemia = 0;
    public static int HemoNormal = 0, HemoModeratelyLow = 0, HemoCriticallyLow = 0;
    public static int qtnormal = 0, qtabnormal = 0;
    public static int prnormal = 0, prabnormal = 0;
    public static int weight1 = 1, weight2 = 1, weight3 = 1, weight4 = 1, weight5 = 1, weight6 = 1, weight7 = 1, weight8 = 1, weight9 = 1, weight10 = 1;
    public static int weight11 = 1, weight12 = 1, weight13 = 1, weight14 = 1, weight15 = 1, weight16 = 1, weight17 = 1, weight18 = 1, weight19 = 1, weight20 = 1;
    public static int weight21 = 1, weight22 = 1, weight23 = 1, weight24 = 1, weight25 = 1, weight26 = 1, weight27 = 1, weight28 = 1, weight29 = 1, weight30 = 1;
    public static int weight31 = 1, weight32 = 1, weight33 = 1, weight34 = 0;

    public static String ProcessData(ArrayList Listdata) {
        for (int j = 0; j < Listdata.size(); j++) {
            String FResult = Listdata.get(j).toString();
            String[] parts = FResult.split(",");

            String[] subparts = parts[0].split("#");
            double Currentval = Double.valueOf(subparts[1].toString());
            if (Currentval <= 120 && Currentval >= 80) {
                sysNormal = sysNormal + (int) Currentval;
                weight1 = weight1 + 1;
            }
            if (Currentval > 120 && Currentval >= 130) {
                sysNormal = sysNormal + (int) Currentval;
                weight1 = weight1 + 1;
            }
            if (Currentval < 130 && Currentval < 160) {
                sysModerate = sysModerate + (int) Currentval;
                weight2 = weight2 + 1;
            }
            if (Currentval < 80 || Currentval > 160) {
                sysSevere = sysSevere + (int) Currentval;
                weight3 = weight3 + 1;
            }

            String[] subparts1 = parts[1].split("#");
            double Currentval1 = Double.valueOf(subparts1[1].toString());
            if (Currentval1 <= 90 && Currentval1 >= 60) {
                dysNormal = dysNormal + (int) Currentval1;
                weight4 = weight4 + 1;
            }
            if (Currentval1 > 90 && Currentval1 >= 120) {
                dysNormal = dysNormal + (int) Currentval1;
                weight4 = weight4 + 1;
            }
            if (Currentval1 < 120 && Currentval1 < 160) {
                dysModerate = dysModerate + (int) Currentval1;
                weight5 = weight5 + 1;
            }
            if (Currentval1 < 80 || Currentval1 > 160) {
                dysSevere = dysSevere + (int) Currentval1;
                weight6 = weight6 + 1;
            }

            String[] subparts2 = parts[2].split("#");
            double Currentval2 = Double.valueOf(subparts1[1].toString());
            if (Currentval2 <= 90 && Currentval2 >= 60) {
                HrateNormal = HrateNormal + (int) Currentval2;
                weight7 = weight7 + 1;
            }
            if (Currentval2 > 90 && Currentval2 >= 120) {
                HrateNormal = HrateNormal + (int) Currentval2;
                weight7 = weight7 + 1;
            }
            if (Currentval2 < 120 && Currentval2 < 160) {
                HrateModerate = HrateModerate + (int) Currentval2;
                weight8 = weight8 + 1;
            }
            if (Currentval2 < 80 || Currentval2 > 160) {
                HrateSevere = HrateSevere + (int) Currentval2;
                weight9 = weight9 + 1;
            }

            String[] subparts3 = parts[3].split("#");
            double Currentval3 = Double.valueOf(subparts1[1].toString());
            if (Currentval3 <= 180) {
                chNormal = chNormal + (int) Currentval3;
                weight10 = weight10 + 1;
            }
            if (Currentval3 <= 220 && Currentval3 > 180) {
                chNormal = chNormal + (int) Currentval3;
                weight10 = weight10 + 1;
            }
            if (Currentval3 <= 250 && Currentval3 > 221) {
                chModerate = chModerate + (int) Currentval3;
                weight11 = weight11 + 1;
            }
            if (Currentval3 > 250) {
                chSevere = chSevere + (int) Currentval3;
                weight12 = weight12 + 1;
            }

            String[] subparts4 = parts[4].split("#");
            double Currentval4 = Double.valueOf(subparts1[1].toString());
            if (Currentval4 <= 100) {
                ldlNormal = ldlNormal + (int) Currentval4;
                weight13 = weight13 + 1;
            }
            if (Currentval4 <= 132 && Currentval4 > 100) {
                ldlNormal = ldlNormal + (int) Currentval4;
                weight13 = weight13 + 1;
            }
            if (Currentval4 <= 160 && Currentval4 > 132) {
                ldlSevere = ldlSevere + (int) Currentval4;
                weight14 = weight14 + 1;
            }
            if (Currentval4 <= 190 && Currentval4 > 160) {
                ldlSevere = ldlSevere + (int) Currentval4;
                weight15 = weight15 + 1;
            }
            if (Currentval4 > 190) {
                ldlcritical = ldlcritical + (int) Currentval4;
                weight15 = weight15 + 1;
            }

            String[] subparts5 = parts[5].split("#");
            double Currentval5 = Double.valueOf(subparts1[1].toString());
            if (Currentval5 <= 100) {
                hdlNormal = hdlNormal + (int) Currentval5;
                weight16 = weight16 + 1;
            }
            if (Currentval5 <= 132 && Currentval5 > 100) {
                hdlNormal = hdlNormal + (int) Currentval5;
                weight16 = weight16 + 1;
            }
            if (Currentval5 <= 160 && Currentval5 > 132) {
                hdlSevere = hdlSevere + (int) Currentval5;
                weight17 = weight17 + 1;
            }
            if (Currentval5 <= 190 && Currentval5 > 160) {
                hdlSevere = hdlSevere + (int) Currentval5;
                weight17 = weight17 + 1;
            }
            if (Currentval5 > 190) {
                hdlcritical = hdlcritical + (int) Currentval5;
                weight18 = weight18 + 1;
            }

            String[] subparts6 = parts[6].split("#");
            double Currentval6 = Double.valueOf(subparts1[1].toString());
            if (Currentval6 == 0) {
                StressNormal = StressNormal + (int) Currentval6;
                weight19 = weight19 + 1;
            }
            if (Currentval6 == 1 || Currentval6 == 2 || Currentval == 3) {
                StressNormal = StressNormal + (int) Currentval6;
                weight19 = weight19 + 1;
            }
            if (Currentval6 == 4 || Currentval6 == 5) {
                StressSevere = StressSevere + (int) Currentval6;
                weight20 = weight20 + 1;
            }
            if (Currentval6 == 6 || Currentval6 == 7) {
                StressSevere = StressSevere + (int) Currentval6;
                weight20 = weight20 + 1;
            }
            if (Currentval6 == 8 || Currentval6 == 9) {
                StressWorst = StressWorst + (int) Currentval6;
                weight21 = weight21 + 1;
            }
            if (Currentval6 == 10) {
                StressWorst = StressWorst + (int) Currentval6;
                weight21 = weight21 + 1;
            }

            String[] subparts7 = parts[7].split("#");
            double Currentval7 = Double.valueOf(subparts1[1].toString());
            if (Currentval7 <= 110 && Currentval7 >= 72) {
                sugarNormal = sugarNormal + (int) Currentval7;
                weight22 = weight22 + 1;
            }
            if (Currentval7 <= 180 && Currentval7 > 110) {
                sugarNormal = sugarNormal + (int) Currentval7;
                weight22 = weight22 + 1;
            }
            if (Currentval7 > 181 && Currentval7 <= 300) {
                sugarHigh = sugarHigh + (int) Currentval7;
                weight23 = weight23 + 1;
            }
            if (Currentval7 > 300 || Currentval7 < 72) {
                sugarDangrous = sugarDangrous + (int) Currentval7;
                weight24 = weight24 + 1;
            }

            String[] subparts8 = parts[8].split("#");
            double Currentval8 = Double.valueOf(subparts1[1].toString());
            if (Currentval8 <= 110 && Currentval8 >= 72) {
                OxyNormal = OxyNormal + (int) Currentval8;
                weight25 = weight25 + 1;
            }
            if (Currentval8 <= 180 && Currentval8 > 110) {
                oxyBelowNormal = oxyBelowNormal + (int) Currentval8;
                weight26 = weight26 + 1;
            }
            if (Currentval8 > 181 || Currentval8 < 72) {
                oxyHypoxemia = oxyHypoxemia + (int) Currentval8;
                weight27 = weight27 + 1;
            }

            String[] subparts9 = parts[9].split("#");
            double Currentval9 = Double.valueOf(subparts1[1].toString());
            if (Currentval9 <= 17.7 && Currentval9 >= 13.6) {
                HemoNormal = HemoNormal + (int) Currentval9;
                weight28 = weight28 + 1;
            }
            if (Currentval9 <= 13.5 && Currentval9 >= 10) {
                HemoModeratelyLow = HemoModeratelyLow + (int) Currentval9;
                weight29 = weight29 + 1;
            }
            if (Currentval9 <= 9.9 && Currentval9 >= 7) {
                HemoModeratelyLow = HemoModeratelyLow + (int) Currentval9;
                weight29 = weight29 + 1;
            }
            if (Currentval9 <= 6.9) {
                HemoCriticallyLow = HemoCriticallyLow + (int) Currentval9;
                weight30 = weight30 + 1;
            }

            String[] subparts10 = parts[10].split("#");
            double Currentval10 = Double.valueOf(subparts1[1].toString());
            if (Currentval10 <= 0.20 && Currentval10 >= 0.12) {
                prnormal = prnormal + (int) Currentval10;
                weight31 = weight31 + 1;
            }
            if (Currentval10 > 0.20 || Currentval10 < 0.12) {
                prabnormal = prabnormal + (int) Currentval10;
                weight32 = weight32 + 1;
            }

            String[] subparts11 = parts[11].split("#");
            double Currentval11 = Double.valueOf(subparts1[1].toString());
            if (Currentval11 <= 0.43 && Currentval11 >= 0.33) {
                qtnormal = qtnormal + (int) Currentval11;
                weight33 = weight33 + 1;
            }
            if (Currentval11 > 0.43 || Currentval11 < 0.33) {
                qtabnormal = qtabnormal + (int) Currentval11;
                weight34 = weight34 + 1;
            }

            ci = String.valueOf(parts[12]);
            VAH = String.valueOf(parts[13]);
            CVD = String.valueOf(parts[14]);

        }

        double w1 = sysNormal / weight1;
        double w2 = sysModerate / weight2;
        double w3 = sysSevere / weight3;
        double w4 = dysNormal / weight4;
        double w5 = dysModerate / weight5;
        double w6 = dysSevere / weight6;
        double w7 = HrateNormal / weight7;
        double w8 = HrateModerate / weight8;
        double w9 = HrateSevere / weight9;

        double w10 = chNormal / weight10;
        double w11 = chModerate / weight11;
        double w12 = chSevere / weight12;
        double w13 = ldlNormal / weight13;
        double w14 = ldlSevere / weight14;
        double w15 = ldlcritical / weight15;
        double w16 = hdlNormal / weight16;
        double w17 = hdlSevere / weight17;
        double w18 = hdlcritical / weight18;

        double w19 = StressNormal / weight19;
        double w20 = StressSevere / weight20;
        double w21 = StressWorst / weight21;
        double w22 = sugarNormal / weight22;
        double w23 = sugarHigh / weight23;
        double w24 = sugarDangrous / weight24;
        double w25 = OxyNormal / weight25;
        double w26 = oxyBelowNormal / weight26;
        double w27 = oxyHypoxemia / weight27;

        double w28 = HemoNormal / weight28;
        double w29 = HemoModeratelyLow / weight29;
        double w30 = HemoCriticallyLow / weight30;
        double w31 = qtnormal / weight31;
        double w32 = qtabnormal / weight32;
        double w33 = prnormal / weight33;
        double w34 = prabnormal / weight34;

        if (w1 >= w2 && w1 >= w3) sysFinalResult = w1 + "#Normal";
        if (w2 > w1 && w2 > w3) sysFinalResult = w2 + "#Moderate";
        else sysFinalResult = w3 + "#Severe";
        if (w4 >= w5 && w4 >= w6) dysFinalResult = w4 + "#Normal";
        if (w5 > w4 && w5 > w6) dysFinalResult = w5 + "#Moderate";
        else dysFinalResult = w6 + "#Severe";
        if (w7 >= w8 && w7 >= w9) hpFinalResult = w7 + "#Normal";
        if (w8 > w7 && w8 > w9) hpFinalResult = w8 + "#Moderate";
        else hpFinalResult = w9 + "#Severe";

        if (w10 >= w11 && w10 > w12) chFinalResult = w10 + "#Normal";
        if (w11 > w10 && w11 > w12) chFinalResult = w11 + "#Moderate";
        else chFinalResult = w12 + "#Severe";
        if (w13 >= w14 && w13 > w15) ldlFinalResult = w13 + "#Normal";
        if (w14 > w13 && w14 > w15) ldlFinalResult = w14 + "#Moderate";
        else ldlFinalResult = w15 + "#Severe";
        if (w16 >= w17 && w16 > w18) hdlFinalResult = w16 + "#Normal";
        if (w17 > w16 && w17 > w18) hdlFinalResult = w17 + "#Moderate";
        else hdlFinalResult = w18 + "#Severe";

        if (w19 >= w20 && w19 >= w21) stressFinalResult = w19 + "#Normal";
        if (w20 > w19 && w20 > w21) stressFinalResult = w20 + "#Moderate";
        else stressFinalResult = w21 + "#Severe";
        if (w22 >= w23 && w22 >= w24) sugarFinalResult = w22 + "#Normal";
        if (w23 > w22 && w23 > w24) sugarFinalResult = w23 + "#High";
        else sugarFinalResult = w24 + "#dangerous";
        if (w25 >= w26 && w25 >= w27) hemoFinalResult = w25 + "#Normal";
        if (w26 > w25 && w26 > w27) hemoFinalResult = w26 + "#Below-Normal";
        else hemoFinalResult = w27 + "#Hypoxemia";

        if (w28 >= w29 && w28 >= w30) oxyFinalResult = w28 + "#Normal";
        if (w29 > w28 && w29 > w30) oxyFinalResult = w29 + "#Moderately-Low";
        else oxyFinalResult = w30 + "#critically-low";
        if (w31 >= w32) prFinalResult = w31 + "#Normal";
        else prFinalResult = w32 + "#Abnormal";
        if (w33 >= w34) qtFinalResult = w33 + "#Normal";
        else qtFinalResult = w34 + "#Abnormal";


        String s = sysFinalResult +
                "," + dysFinalResult +
                "," + hpFinalResult +
                "," + chFinalResult +
                "," + ldlFinalResult +
                "," + hdlFinalResult +
                "," + stressFinalResult +
                "," + sugarFinalResult +
                "," + oxyFinalResult +
                "," + hemoFinalResult +
                "," + prFinalResult +
                "," + qtFinalResult +
                "," + ci +
                "," + VAH +
                "," + CVD;
        System.out.println(s);
        return s;
    }
}