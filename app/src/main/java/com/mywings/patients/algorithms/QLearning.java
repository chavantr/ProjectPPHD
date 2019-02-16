
package com.mywings.patients.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class QLearning {

    public static String ProcessData(ArrayList Objarray, String gender) {
        int sysR = 0, sysP = 0;
        int dysR = 0, dysP = 0;
        int HrateR = 0, HrateP = 0;
        int chR = 0, chP = 0;
        int ldlR = 0, ldlP = 0;
        int hdlR = 0, hdlP = 0;
        int StressR = 0, StressP = 0;
        int sugarR = 0, sugarP = 0;
        int OxyR = 0, oxyP = 0;
        int HemoR = 0, HemoP = 0;
        int qtR = 0, qtP = 0;
        int prR = 0, prP = 0;
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
                sysP = sysR + 1;
            }
            if (Sysbp > 120 && Sysbp <= 130) {
                sysP = sysP + 1;
            }
            if (Sysbp > 130 && Sysbp < 160) {
                sysR = sysR + 1;
            }
            if (Sysbp < 80 && Sysbp > 160) {
                sysR = sysR + 1;
            }
            // --------------------- Dys BP --------------------------------------
            if (dysbp <= 90 && dysbp >= 60) {
                dysR = dysR + 1;
            }
            if (dysbp > 90 && dysbp >= 120) {
                dysR = dysR + 1;
            }
            if (dysbp < 120 && dysbp < 160) {
                dysP = dysP + 1;
            }
            if (dysbp < 80 && dysbp > 160) {
                dysP = dysP + 1;
            }
            // --------------------- Heart Rate --------------------------------------
            if (Hrate >= 60 && Hrate <= 110) {
                HrateR = HrateR + 1;
            }
            if (Hrate <= 120 && Hrate > 110) {
                HrateR = HrateR;
            }
            if (Hrate < 40 && Hrate > 30) {
                HrateP = HrateP + 1;
            }
            if (Hrate < 30 && Hrate > 120) {
                HrateP = HrateP + 1;
            }
            // --------------------- Cholestrol --------------------------------------
            if (chole <= 180) {
                chR = chR + 1;
            }
            if (chole <= 220 && chole > 180) {
                chR = chR + 1;
            }
            if (chole <= 250 && chole > 221) {
                chP = chP + 1;
            }
            if (chole > 250) {
                chP = chP + 1;
            }
            // --------------------- ldl --------------------------------------
            if (ldl <= 100) {
                ldlP = ldlR + 1;
            }
            if (ldl <= 132 && ldl > 100) {
                ldlR = ldlR + 1;
            }
            if (ldl <= 160 && ldl > 132) {
                ldlR = ldlR + 1;
            }
            if (ldl <= 190 && ldl > 160) {
                ldlP = ldlP + 1;
            }
            if (ldl > 190) {
                ldlP = ldlP + 1;
            }
            // --------------------- hdl --------------------------------------
            if (hdl <= 100) {
                hdlR = hdlR + 1;
            }
            if (hdl <= 130 && hdl > 100) {
                hdlR = hdlR + 1;
            }
            if (hdl <= 160 && hdl > 130) {
                hdlR = hdlR + 1;
            }
            if (hdl <= 190 && hdl > 160) {
                hdlP = hdlP + 1;
            }
            if (hdl > 190) {
                hdlP = hdlP + 1;
            }
            // --------------------- Stress --------------------------------------
            if (stress == 0) {
                StressR = StressR + 1;
            }
            if (stress == 1 || stress == 2 || stress == 3) {
                StressR = StressR + 1;
            }
            if (stress == 4 || stress == 5) {
                StressR = StressR + 1;
            }
            if (stress == 6 || stress == 7) {
                StressP = StressP + 1;
            }
            if (stress == 8 || stress == 9) {
                StressP = StressP + 1;
            }
            if (stress == 10) {
                StressP = StressP + 1;
            }
            // --------------------- Sugar --------------------------------------
            if (sugar <= 110 && sugar > 72) {
                sugarR = sugarR + 1;
            }
            if (sugar <= 180 && sugar > 110) {
                sugarR = sugarR + 1;
            }
            if (sugar > 181 && sugar <= 300) {
                sugarP = sugarP + 1;
            }
            if (sugar > 300 || sugar <= 60) {
                sugarP = sugarP + 1;
            }

            // --------------------- OS --------------------------------------
            if (oxy <= 110 && oxy >= 72) {
                OxyR = OxyR + 1;
            }
            if (oxy <= 180 && oxy > 110) {
                OxyR = OxyR + 1;
            }
            if (oxy > 180 || oxy < 72) {
                oxyP = oxyP + 1;
            }
            // --------------------- Hemoglobin --------------------------------------
            if (gender == "male") {
                if (hemo <= 17.7 && sugar >= 13.6) {
                    HemoP = HemoP + 1;
                }
                if (hemo <= 13.5 && sugar >= 10) {
                    HemoP = HemoP + 1;
                }
                if (hemo <= 9.9 && sugar >= 7) {
                    HemoP = HemoP + 1;
                }
                if (hemo <= 6.9) {
                    HemoP = HemoP + 1;
                }
            }
            if (gender == "male") {
                if (hemo <= 15.1 && sugar >= 12.1) {
                    HemoR = HemoR + 1;
                }
                if (hemo <= 12 && sugar >= 10) {
                    HemoR = HemoR + 1;
                }
                if (hemo <= 9.9 && sugar >= 7) {
                    HemoR = HemoR + 1;
                }
                if (hemo <= 6.9) {
                    HemoP = HemoP + 1;
                }
            }
            // --------------------- QT --------------------------------------
            if (qt <= 0.43 && qt >= 0.33) {
                qtR = qtR + 1;
            }
            if (qt > 0.43 || qt < 0.33) {
                qtP = qtP + 1;
            }
            // --------------------- PR --------------------------------------
            if (pr <= 0.20 && pr >= 0.12) {
                prR = prR + 1;
            }
            if (pr > 0.20 || pr < 0.12) {
                prP = prP + 1;
            }
            tot = tot + 1;
        }
        double sysNormalw1 = (sysR / tot);
        double sysNormalw2 = (sysP / tot);

        double systolicBp[] = new double[]{sysNormalw1, sysNormalw2};
        double sysMax = GenerateProbability(systolicBp);
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

        double dysNormalw1 = (dysR / tot);
        double dysNormalw2 = (dysP / tot);

        double dystolicBp[] = new double[]{dysNormalw1, dysNormalw2};
        double dysMax = GenerateProbability(systolicBp);
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

        double HrateNormal1 = (HrateR / tot);
        double HrateMildw2 = (HrateP / tot);

        double HPRate[] = new double[]{dysNormalw1, dysNormalw2};
        double HPMax = GenerateProbability(HPRate);
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
        double chNormalw1 = (chR / tot);
        double chMildw2 = (chP / tot);

        double CHRate[] = new double[]{chNormalw1, chMildw2};
        double CHMax = GenerateProbability(HPRate);
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
        double ldlw1 = (ldlR / tot);
        double ldlw2 = (ldlP / tot);
        double ldlRate[] = new double[]{ldlw1, ldlw2};
        double ldlMax = GenerateProbability(ldlRate);
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
        double hdlw1 = (hdlR / tot);
        double hdlw2 = (hdlP / tot);

        double hdlRate[] = new double[]{hdlw1, hdlw2};
        double hdlMax = GenerateProbability(hdlRate);
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
        double stressw1 = (StressR / tot);
        double stressw2 = (StressP / tot);

        double stressRate[] = new double[]{stressw1, stressw2};
        double stressMax = GenerateProbability(stressRate);
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
        double sugarw1 = (sugarR / tot);
        double sugarw2 = (sugarP / tot);

        double sugarRate[] = new double[]{sugarw1, sugarw2};
        double sugarMax = GenerateProbability(sugarRate);
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
        double oxyw1 = (OxyR / tot);
        double oxyw2 = (oxyP / tot);

        double oxyRate[] = new double[]{oxyw1, oxyw2};
        double oxyMax = GenerateProbability(oxyRate);
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
        double hemow1 = (HemoR / tot);
        double hemow2 = (HemoP / tot);

        double hemoRate[] = new double[]{hemow1, hemow2};
        double hemoMax = GenerateProbability(hemoRate);
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
        double prw1 = (prR / tot);
        double prw2 = (prP / tot);
        double prRate[] = new double[]{prw1, prw2};
        double prMax = GenerateProbability(prRate);
        String prFinalResult = "";
        if (prMax <= 0.20 && prMax >= 0.12) {
            prFinalResult = "Normal";
        }
        if (prMax > 0.20 || prMax < 0.12) {
            prFinalResult = "Abnormal";
        }
        //--------------------------------------------------
        double qtw1 = (qtR / tot);
        double qtw2 = (qtP / tot);
        double qtRate[] = new double[]{qtw1, qtw2};
        double qtMax = GenerateProbability(qtRate);
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
    public static double GenerateProbability(double[] FuzzySet) {
        double ProbableVal = FuzzySet[0];
        for (int i = 1; i < FuzzySet.length; i++) {
            if (FuzzySet[i] > ProbableVal) {
                ProbableVal = FuzzySet[i];
            }
        }
        return ProbableVal;
    }

}
