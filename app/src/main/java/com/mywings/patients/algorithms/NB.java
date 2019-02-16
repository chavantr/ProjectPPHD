/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mywings.patients.algorithms;

import java.util.ArrayList;

public class NB {

    public static String ProcessData(ArrayList Objarray, String gender) {
        int sysbpC = 0;
        int dysbpC = 0;
        int HrateC = 0;
        int chC = 0;
        int ldlC = 0;
        int hdlC = 0;
        int StressC = 0;
        int sugarC = 0;
        int OxyC = 0;
        int HemoC = 0;
        int qtC = 0;
        int prC = 0;
        int tot = 0;

        for (int StartVal = 0; StartVal < Objarray.size(); StartVal++) {
            String[] Parts = Objarray.get(StartVal).toString().split(",");
            int Sysbp = Integer.parseInt(Parts[1]);
            int dysbp = Integer.parseInt(Parts[2]);
            int Hrate = Integer.parseInt(Parts[3]);
            int chole = Integer.parseInt(Parts[4]);
            int ldl = Integer.parseInt(Parts[5]);
            int hdl = Integer.parseInt(Parts[6]);
            int stress = Integer.parseInt(Parts[7]);
            int sugar = Integer.parseInt(Parts[8]);
            double qt = Double.parseDouble(Parts[9]);
            double pr = Double.parseDouble(Parts[10]);
            ;
            int oxy = Integer.parseInt(Parts[11]);
            double hemo = Double.parseDouble(Parts[12]);
            String timestamp = Parts[13];

            //Calculate average for each interval

            sysbpC = sysbpC + 1;
            dysbpC = dysbp + 1;
            HrateC = Hrate + 1;
            chC = chC + 1;
            ldlC = ldlC + 1;
            hdlC = hdlC + 1;
            StressC = StressC + 1;
            sugarC = sugarC + 1;
            OxyC = OxyC + 1;
            HemoC = HemoC + 1;
            qtC = qtC + 1;
            prC = prC + 1;
            tot = tot + 1;
        }
        double sysweight = (sysbpC / tot);
        String sysFinalResult = "";
        if (sysweight <= 120 && sysweight >= 80) {
            sysFinalResult = "Normal";
        }
        if (sysweight > 120 && sysweight >= 130) {
            sysFinalResult = "Mild";
        }
        if (sysweight < 130 && sysweight < 160) {
            sysFinalResult = "Moderate";
        }
        if (sysweight < 80 && sysweight > 160) {
            sysFinalResult = "Severe";
        }

        //--------------------------------------------------
        double dysweight = (dysbpC / tot);
        String dysFinalResult = "";
        if (dysweight <= 90 && dysweight >= 60) {
            dysFinalResult = "Normal";
        }
        if (dysweight > 90 && dysweight >= 120) {
            dysFinalResult = "Mild";
        }
        if (dysweight < 120 && dysweight < 160) {
            dysFinalResult = "Moderate";
        }
        if (dysweight < 80 && dysweight > 160) {
            dysFinalResult = "Severe";
        }

        //--------------------------------------------------

        double Heartweight = (HrateC / tot);
        String hpFinalResult = "";
        if (Heartweight <= 90 && Heartweight >= 60) {
            hpFinalResult = "Normal";
        }
        if (Heartweight > 90 && Heartweight >= 120) {
            hpFinalResult = "Mild";
        }
        if (Heartweight < 120 && Heartweight < 160) {
            hpFinalResult = "Moderate";
        }
        if (Heartweight < 80 && Heartweight > 160) {
            hpFinalResult = "Severe";
        }
        //--------------------------------------------------
        double Chweight = (chC / tot);
        String chFinalResult = "";
        if (Chweight <= 180) {
            chFinalResult = "Normal";
        }
        if (Chweight <= 220 && Chweight > 180) {
            chFinalResult = "Mild";
        }
        if (Chweight <= 250 && Chweight > 221) {
            chFinalResult = "Moderate";
        }
        if (Chweight > 250) {
            chFinalResult = "Severe";
        }
        //--------------------------------------------------
        double ldlweight = (ldlC / tot);

        String ldlFinalResult = "";
        if (ldlweight <= 100) {
            ldlFinalResult = "Normal";
        }
        if (ldlweight <= 132 && ldlweight > 100) {
            ldlFinalResult = "Mild";
        }
        if (ldlweight <= 160 && ldlweight > 132) {
            ldlFinalResult = "Moderate";
        }
        if (ldlweight <= 190 && ldlweight > 160) {
            ldlFinalResult = "Sever";
        }
        if (ldlweight > 190) {
            ldlFinalResult = "critical";
        }
        //--------------------------------------------------
        double hdlweight = (hdlC / tot);
        String hdlFinalResult = "";
        if (hdlweight <= 100) {
            hdlFinalResult = "Normal";
        }
        if (hdlweight <= 132 && hdlweight > 100) {
            hdlFinalResult = "Mild";
        }
        if (hdlweight <= 160 && hdlweight > 132) {
            hdlFinalResult = "Moderate";
        }
        if (hdlweight <= 190 && hdlweight > 160) {
            hdlFinalResult = "Sever";
        }
        if (hdlweight > 190) {
            hdlFinalResult = "critical";
        }
        //--------------------------------------------------
        double stressweight = (StressC / tot);
        String stressFinalResult = "";
        if (stressweight == 0) {
            stressFinalResult = "Normal";
        }
        if (stressweight == 1 || stressweight == 2 || stressweight == 3) {
            stressFinalResult = "Mild";
        }
        if (stressweight == 4 || stressweight == 5) {
            stressFinalResult = "Moderate";
        }
        if (stressweight == 6 || stressweight == 7) {
            stressFinalResult = "Sever";
        }
        if (stressweight == 8 || stressweight == 9) {
            stressFinalResult = "VerySevere";
        }
        if (stressweight == 10) {
            stressFinalResult = "Worst";
        }
        //--------------------------------------------------
        double sugarweight = (sugarC / tot);

        String sugarFinalResult = "";
        if (sugarweight <= 110 && sugarweight >= 72) {
            sugarFinalResult = "Normal";
        }
        if (sugarweight <= 180 && sugarweight > 110) {
            sugarFinalResult = "Mild";
        }
        if (sugarweight > 181 && sugarweight <= 300) {
            sugarFinalResult = "High";
        }
        if (sugarweight > 300 || sugarweight < 72) {
            sugarFinalResult = "Dangroud";
        }
        //--------------------------------------------------
        double oxyweight = (OxyC / tot);
        String oxyFinalResult = "";
        if (oxyweight <= 110 && oxyweight >= 72) {
            oxyFinalResult = "Normal";
        }
        if (oxyweight <= 180 && oxyweight > 110) {
            oxyFinalResult = "Below-Normal";
        }
        if (oxyweight > 181 || oxyweight < 72) {
            oxyFinalResult = "Hypoxemia";
        }
        //--------------------------------------------------
        double hemoweight = (HemoC / tot);

        String hemoFinalResult = "";
        if (hemoweight <= 17.7 && hemoweight >= 13.6) {
            hemoFinalResult = "Normal";
        }
        if (hemoweight <= 13.5 && hemoweight >= 10) {
            hemoFinalResult = "Below-Normal";
        }
        if (hemoweight <= 9.9 && hemoweight >= 7) {
            hemoFinalResult = "Moderately-Low";
        }
        if (hemoweight <= 6.9) {
            hemoFinalResult = "Critically-Low";
        }
        //--------------------------------------------------
        double prweight = (prC / tot);
        String prFinalResult = "";
        if (prweight <= 0.20 && prweight >= 0.12) {
            prFinalResult = "Normal";
        }
        if (prweight > 0.20 || prweight < 0.12) {
            prFinalResult = "Abnormal";
        }
        //--------------------------------------------------
        double qtweight = (qtC / tot);
        String qtFinalResult = "";
        if (qtweight <= 0.43 && qtweight >= 0.33) {
            qtFinalResult = "Normal";
        }
        if (qtweight > 0.43 || qtweight < 0.33) {
            qtFinalResult = "Abnormal";
        }
        //--------------------------------------------------

        String s = sysFinalResult + "," + dysFinalResult + "," + hpFinalResult + "," + chFinalResult + "," + ldlFinalResult + "," + hdlFinalResult + "," + stressFinalResult + "," + sugarFinalResult + "," + oxyFinalResult + "," + hemoFinalResult + "," + prFinalResult + "," + qtFinalResult;
        System.out.println(s);
        return s;
    }

}
