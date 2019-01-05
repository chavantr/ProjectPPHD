package com.mywings.patients;

public class SensorData {

    private int id;

    private int systolic_bp;

    private int diastolic_bp;

    private int heart_rate;

    private int total_cholesterol;

    private int cholesterol_LDL;

    private int cholesterol_HDL;

    private int stress;

    private int random_sugar;

    private float QT_interval;

    private float PR_interval;

    private int oxy_saturation;

    private float HB;

    private String time_stamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSystolic_bp() {
        return systolic_bp;
    }

    public void setSystolic_bp(int systolic_bp) {
        this.systolic_bp = systolic_bp;
    }

    public int getDiastolic_bp() {
        return diastolic_bp;
    }

    public void setDiastolic_bp(int diastolic_bp) {
        this.diastolic_bp = diastolic_bp;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public int getTotal_cholesterol() {
        return total_cholesterol;
    }

    public void setTotal_cholesterol(int total_cholesterol) {
        this.total_cholesterol = total_cholesterol;
    }

    public int getCholesterol_LDL() {
        return cholesterol_LDL;
    }

    public void setCholesterol_LDL(int cholesterol_LDL) {
        this.cholesterol_LDL = cholesterol_LDL;
    }

    public int getCholesterol_HDL() {
        return cholesterol_HDL;
    }

    public void setCholesterol_HDL(int cholesterol_HDL) {
        this.cholesterol_HDL = cholesterol_HDL;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getRandom_sugar() {
        return random_sugar;
    }

    public void setRandom_sugar(int random_sugar) {
        this.random_sugar = random_sugar;
    }

    public float getQT_interval() {
        return QT_interval;
    }

    public void setQT_interval(float QT_interval) {
        this.QT_interval = QT_interval;
    }

    public float getPR_interval() {
        return PR_interval;
    }

    public void setPR_interval(float PR_interval) {
        this.PR_interval = PR_interval;
    }

    public int getOxy_saturation() {
        return oxy_saturation;
    }

    public void setOxy_saturation(int oxy_saturation) {
        this.oxy_saturation = oxy_saturation;
    }

    public float getHB() {
        return HB;
    }

    public void setHB(float HB) {
        this.HB = HB;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
