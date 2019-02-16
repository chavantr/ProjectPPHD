/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mywings.patients.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class Fuzzy {

    public static String GenerateProbability(ArrayList Objarray, String gender) {
        int sysNormal = 0, sysMild = 0, sysModerate = 0, sysSevere = 0;
        int dysNormal = 0, dysMild = 0, dysModerate = 0, dysSevere = 0;
        int HrateNormal = 0, HrateMild = 0, HrateModerate = 0, HrateSevere = 0;
        int chNormal = 0, chMild = 0, chModerate = 0, chSevere = 0;
        int ldlNormal = 0, ldlMild = 0, ldlModerate = 0, ldlSevere = 0, ldlcritical = 0;
        int hdlNormal = 0, hdlMild = 0, hdlModerate = 0, hdlSevere = 0, hdlcritical = 0;
        int StressNormal = 0, StressMild = 0, StressModerate = 0, StressSevere = 0, StressVerySevere = 0, StressWorst = 0;
        int sugarNormal = 0, sugarMild = 0, sugarHigh = 0, sugarDangroud = 0;
        int OxyNormal = 0, oxyBelowNormal = 0, oxyHypoxemia = 0;
        int HemoNormal = 0, HemoBelowNormal = 0, HemoModeratelyLow = 0, HemoCriticallyLow = 0;
        int qtnormal = 0, qtabnormal = 0;
        int prnormal = 0, prabnormal = 0;
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
            // --------------------- Sys BP --------------------------------------
            if (Sysbp <= 120 && Sysbp >= 80) {
                sysNormal = sysNormal + 1;
            } else if (Sysbp > 120 && Sysbp <= 130) {
                sysMild = sysMild + 1;
            }
            if (Sysbp > 130 && Sysbp < 160) {
                sysModerate = sysModerate + 1;
            }
            if (Sysbp < 80 && Sysbp > 160) {
                sysSevere = sysSevere + 1;
            }
            // --------------------- Dys BP --------------------------------------
            if (dysbp <= 90 && dysbp >= 60) {
                dysNormal = dysNormal + 1;
            }
            if (dysbp > 90 && dysbp >= 120) {
                dysMild = dysMild + 1;
            }
            if (dysbp < 120 && dysbp < 160) {
                dysModerate = dysModerate + 1;
            }
            if (dysbp < 80 && dysbp > 160) {
                dysSevere = dysSevere + 1;
            }
            // --------------------- Heart Rate --------------------------------------
            if (Hrate >= 60 && Hrate <= 110) {
                HrateNormal = HrateNormal + 1;
            }
            if (Hrate <= 120 && Hrate > 110) {
                HrateMild = HrateMild + 1;
            }
            if (Hrate < 40 && Hrate > 30) {
                HrateModerate = HrateModerate + 1;
            }
            if (Hrate < 30 && Hrate > 120) {
                HrateSevere = HrateSevere + 1;
            }
            // --------------------- Cholestrol --------------------------------------
            if (chole <= 180) {
                chNormal = chNormal + 1;
            }
            if (chole <= 220 && chole > 180) {
                chMild = chMild + 1;
            }
            if (chole <= 250 && chole > 221) {
                chModerate = chModerate + 1;
            }
            if (chole > 250) {
                chSevere = chSevere + 1;
            }
            // --------------------- ldl --------------------------------------
            if (ldl <= 100) {
                ldlNormal = ldlNormal + 1;
            }
            if (ldl <= 132 && ldl > 100) {
                ldlMild = ldlMild + 1;
            }
            if (ldl <= 160 && ldl > 132) {
                ldlModerate = ldlModerate + 1;
            }
            if (ldl <= 190 && ldl > 160) {
                ldlSevere = ldlSevere + 1;
            }
            if (ldl > 190) {
                ldlcritical = ldlcritical + 1;
            }
            // --------------------- hdl --------------------------------------
            if (hdl <= 100) {
                hdlNormal = hdlNormal + 1;
            }
            if (hdl <= 130 && hdl > 100) {
                hdlMild = hdlMild + 1;
            }
            if (hdl <= 160 && hdl > 130) {
                hdlModerate = hdlModerate + 1;
            }
            if (hdl <= 190 && hdl > 160) {
                hdlSevere = hdlSevere + 1;
            }
            if (hdl > 190) {
                hdlcritical = hdlcritical + 1;
            }
            // --------------------- Stress --------------------------------------
            if (stress == 0) {
                StressNormal = StressNormal + 1;
            }
            if (stress == 1 || stress == 2 || stress == 3) {
                StressMild = StressMild + 1;
            }
            if (stress == 4 || stress == 5) {
                StressModerate = StressModerate + 1;
            }
            if (stress == 6 || stress == 7) {
                StressSevere = StressSevere + 1;
            }
            if (stress == 8 || stress == 9) {
                StressVerySevere = StressVerySevere + 1;
            }
            if (stress == 10) {
                StressWorst = StressWorst + 1;
            }
            // --------------------- Sugar --------------------------------------
            if (sugar <= 110 && sugar > 72) {
                sugarNormal = sugarNormal + 1;
            }
            if (sugar <= 180 && sugar > 110) {
                sugarMild = sugarMild + 1;
            }
            if (sugar > 181 && sugar <= 300) {
                sugarHigh = sugarHigh + 1;
            }
            if (sugar > 300 || sugar <= 60) {
                sugarDangroud = sugarDangroud + 1;
            }

            // --------------------- OS --------------------------------------
            if (oxy <= 110 && oxy >= 72) {
                OxyNormal = OxyNormal + 1;
            }
            if (oxy <= 180 && oxy > 110) {
                oxyBelowNormal = oxyBelowNormal + 1;
            }
            if (oxy > 180 || oxy < 72) {
                oxyHypoxemia = oxyHypoxemia + 1;
            }
            // --------------------- Hemoglobin --------------------------------------
            if (gender == "male") {
                if (hemo <= 17.7 && sugar >= 13.6) {
                    HemoNormal = HemoNormal + 1;
                }
                if (hemo <= 13.5 && sugar >= 10) {
                    HemoBelowNormal = HemoBelowNormal + 1;
                }
                if (hemo <= 9.9 && sugar >= 7) {
                    HemoModeratelyLow = HemoModeratelyLow + 1;
                }
                if (hemo <= 6.9) {
                    HemoCriticallyLow = HemoCriticallyLow + 1;
                }
            }
            if (gender == "male") {
                if (hemo <= 15.1 && sugar >= 12.1) {
                    HemoNormal = HemoNormal + 1;
                }
                if (hemo <= 12 && sugar >= 10) {
                    HemoBelowNormal = HemoBelowNormal + 1;
                }
                if (hemo <= 9.9 && sugar >= 7) {
                    HemoModeratelyLow = HemoModeratelyLow + 1;
                }
                if (hemo <= 6.9) {
                    HemoCriticallyLow = HemoCriticallyLow + 1;
                }
            }
            // --------------------- QT --------------------------------------
            if (qt <= 0.43 && qt >= 0.33) {
                qtnormal = qtnormal + 1;
            }
            if (qt > 0.43 || qt < 0.33) {
                qtabnormal = qtabnormal + 1;
            }
            // --------------------- PR --------------------------------------
            if (pr <= 0.20 && pr >= 0.12) {
                prnormal = prnormal + 1;
            }
            if (pr > 0.20 || pr < 0.12) {
                prabnormal = prabnormal + 1;
            }
            tot = tot + 1;
        }
        double sysNormalw1 = (sysNormal / tot);
        double sysNormalw2 = (sysMild / tot);
        double sysNormalw3 = (sysModerate / tot);
        double sysNormalw4 = (sysSevere / tot);
        double systolicBp[] = new double[]{sysNormalw1, sysNormalw2, sysNormalw3, sysNormalw4};
        double sysMax = getMax(systolicBp);
        String sysFinalResult = "";
        if (sysMax <= 120 && sysMax >= 80) {
            sysFinalResult = "Normal";
        }
        if (sysMax > 120 && sysMax >= 130) {
            sysFinalResult = "Mild";
        }
        if (sysMax < 130 && sysMax < 160) {
            sysFinalResult = "Moderate";
        }
        if (sysMax < 80 && sysMax > 160) {
            sysFinalResult = "Severe";
        }

        //--------------------------------------------------

        double dysNormalw1 = (dysNormal / tot);
        double dysNormalw2 = (dysMild / tot);
        double dysNormalw3 = (dysModerate / tot);
        double dysNormalw4 = (dysSevere / tot);
        double dystolicBp[] = new double[]{dysNormalw1, dysNormalw2, dysNormalw3, dysNormalw4};
        double dysMax = getMax(systolicBp);
        String dysFinalResult = "";
        if (dysMax <= 90 && dysMax >= 60) {
            dysFinalResult = "Normal";
        }
        if (dysMax > 90 && dysMax >= 120) {
            dysFinalResult = "Mild";
        }
        if (dysMax < 120 && dysMax < 160) {
            dysFinalResult = "Moderate";
        }
        if (dysMax < 80 && dysMax > 160) {
            dysFinalResult = "Severe";
        }

        //--------------------------------------------------

        double HrateNormal1 = (HrateNormal / tot);
        double HrateMildw2 = (HrateMild / tot);
        double HrateMildw3 = (HrateModerate / tot);
        double HrateMildw4 = (HrateSevere / tot);
        double HPRate[] = new double[]{dysNormalw1, dysNormalw2, dysNormalw3, dysNormalw4};
        double HPMax = getMax(HPRate);
        String hpFinalResult = "";
        if (HPMax <= 90 && HPMax >= 60) {
            hpFinalResult = "Normal";
        }
        if (HPMax > 90 && HPMax >= 120) {
            hpFinalResult = "Mild";
        }
        if (HPMax < 120 && HPMax < 160) {
            hpFinalResult = "Moderate";
        }
        if (HPMax < 80 && HPMax > 160) {
            hpFinalResult = "Severe";
        }
        //--------------------------------------------------
        double chNormalw1 = (chNormal / tot);
        double chMildw2 = (chMild / tot);
        double chMildw3 = (chModerate / tot);
        double chMildw4 = (chSevere / tot);
        double CHRate[] = new double[]{chNormalw1, chMildw2, chMildw3, chMildw4};
        double CHMax = getMax(HPRate);
        String chFinalResult = "";
        if (CHMax <= 180) {
            chFinalResult = "Normal";
        }
        if (CHMax <= 220 && CHMax > 180) {
            chFinalResult = "Mild";
        }
        if (CHMax <= 250 && CHMax > 221) {
            chFinalResult = "Moderate";
        }
        if (CHMax > 250) {
            chFinalResult = "Severe";
        }
        //--------------------------------------------------
        double ldlw1 = (ldlNormal / tot);
        double ldlw2 = (ldlMild / tot);
        double ldlw3 = (ldlModerate / tot);
        double ldlw4 = (ldlSevere / tot);
        double ldlw5 = (ldlcritical / tot);
        double ldlRate[] = new double[]{ldlw1, ldlw2, ldlw3, ldlw4, ldlw5};
        double ldlMax = getMax(ldlRate);
        String ldlFinalResult = "";
        if (ldlMax <= 100) {
            ldlFinalResult = "Normal";
        }
        if (ldlMax <= 132 && ldlMax > 100) {
            ldlFinalResult = "Mild";
        }
        if (ldlMax <= 160 && ldlMax > 132) {
            ldlFinalResult = "Moderate";
        }
        if (ldlMax <= 190 && ldlMax > 160) {
            ldlFinalResult = "Sever";
        }
        if (ldlMax > 190) {
            ldlFinalResult = "critical";
        }
        //--------------------------------------------------
        double hdlw1 = (hdlNormal / tot);
        double hdlw2 = (hdlMild / tot);
        double hdlw3 = (hdlModerate / tot);
        double hdlw4 = (hdlSevere / tot);
        double hdlw5 = (hdlcritical / tot);
        double hdlRate[] = new double[]{hdlw1, hdlw2, hdlw3, hdlw4, hdlw5};
        double hdlMax = getMax(hdlRate);
        String hdlFinalResult = "";
        if (hdlMax <= 100) {
            hdlFinalResult = "Normal";
        }
        if (hdlMax <= 132 && hdlMax > 100) {
            hdlFinalResult = "Mild";
        }
        if (hdlMax <= 160 && hdlMax > 132) {
            hdlFinalResult = "Moderate";
        }
        if (hdlMax <= 190 && hdlMax > 160) {
            hdlFinalResult = "Sever";
        }
        if (hdlMax > 190) {
            hdlFinalResult = "critical";
        }
        //--------------------------------------------------
        double stressw1 = (StressNormal / tot);
        double stressw2 = (StressMild / tot);
        double stressw3 = (StressModerate / tot);
        double stressw4 = (StressSevere / tot);
        double stressw5 = (StressVerySevere / tot);
        double stressw6 = (StressWorst / tot);
        double stressRate[] = new double[]{stressw1, stressw2, stressw3, stressw4, stressw5, stressw6};
        double stressMax = getMax(stressRate);
        String stressFinalResult = "";
        if (stressMax == 0) {
            stressFinalResult = "Normal";
        }
        if (stressMax == 1 || stressMax == 2 || stressMax == 3) {
            stressFinalResult = "Mild";
        }
        if (stressMax == 4 || stressMax == 5) {
            stressFinalResult = "Moderate";
        }
        if (stressMax == 6 || stressMax == 7) {
            stressFinalResult = "Sever";
        }
        if (stressMax == 8 || stressMax == 9) {
            stressFinalResult = "VerySevere";
        }
        if (stressMax == 10) {
            stressFinalResult = "Worst";
        }
        //--------------------------------------------------
        double sugarw1 = (sugarNormal / tot);
        double sugarw2 = (sugarMild / tot);
        double sugarw3 = (sugarHigh / tot);
        double sugarw4 = (sugarDangroud / tot);
        double sugarRate[] = new double[]{sugarw1, sugarw2, sugarw3, sugarw4};
        double sugarMax = getMax(sugarRate);
        String sugarFinalResult = "";
        if (sugarMax <= 110 && sugarMax >= 72) {
            sugarFinalResult = "Normal";
        }
        if (sugarMax <= 180 && sugarMax > 110) {
            sugarFinalResult = "Mild";
        }
        if (sugarMax > 181 && sugarMax <= 300) {
            sugarFinalResult = "High";
        }
        if (sugarMax > 300 || sugarMax < 72) {
            sugarFinalResult = "Dangroud";
        }
        //--------------------------------------------------
        double oxyw1 = (OxyNormal / tot);
        double oxyw2 = (oxyBelowNormal / tot);
        double oxyw3 = (oxyHypoxemia / tot);
        double oxyRate[] = new double[]{oxyw1, oxyw2, oxyw3};
        double oxyMax = getMax(oxyRate);
        String oxyFinalResult = "";
        if (oxyMax <= 110 && oxyMax >= 72) {
            oxyFinalResult = "Normal";
        }
        if (oxyMax <= 180 && oxyMax > 110) {
            oxyFinalResult = "Below-Normal";
        }
        if (oxyMax > 181 || oxyMax < 72) {
            oxyFinalResult = "Hypoxemia";
        }
        //--------------------------------------------------
        double hemow1 = (HemoNormal / tot);
        double hemow2 = (HemoBelowNormal / tot);
        double hemow3 = (HemoModeratelyLow / tot);
        double hemow4 = (HemoCriticallyLow / tot);
        double hemoRate[] = new double[]{hemow1, hemow2, hemow3, hemow4};
        double hemoMax = getMax(hemoRate);
        String hemoFinalResult = "";
        if (hemoMax <= 17.7 && hemoMax >= 13.6) {
            hemoFinalResult = "Normal";
        }
        if (hemoMax <= 13.5 && hemoMax >= 10) {
            hemoFinalResult = "Below-Normal";
        }
        if (hemoMax <= 9.9 && hemoMax >= 7) {
            hemoFinalResult = "Moderately-Low";
        }
        if (hemoMax <= 6.9) {
            hemoFinalResult = "Critically-Low";
        }
        //--------------------------------------------------
        double prw1 = (prnormal / tot);
        double prw2 = (prabnormal / tot);
        double prRate[] = new double[]{prw1, prw2};
        double prMax = getMax(prRate);
        String prFinalResult = "";
        if (prMax <= 0.20 && prMax >= 0.12) {
            prFinalResult = "Normal";
        }
        if (prMax > 0.20 || prMax < 0.12) {
            prFinalResult = "Abnormal";
        }
        //--------------------------------------------------
        double qtw1 = (qtnormal / tot);
        double qtw2 = (qtabnormal / tot);
        double qtRate[] = new double[]{qtw1, qtw2};
        double qtMax = getMax(qtRate);
        String qtFinalResult = "";
        if (qtMax <= 0.43 && qtMax >= 0.33) {
            qtFinalResult = "Normal";
        }
        if (qtMax > 0.43 || qtMax < 0.33) {
            qtFinalResult = "Abnormal";
        }
        //--------------------------------------------------

        String s = sysFinalResult + "," + dysFinalResult + "," + hpFinalResult + "," + chFinalResult + "," + ldlFinalResult + "," + hdlFinalResult + "," + stressFinalResult + "," + sugarFinalResult + "," + oxyFinalResult + "," + hemoFinalResult + "," + prFinalResult + "," + qtFinalResult;
        System.out.println(s);
        return s;
    }


    // Function for sorting the weight
    public static double getMax(double[] inputArray) {
        double maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

}
