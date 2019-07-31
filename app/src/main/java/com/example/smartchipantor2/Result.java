package com.example.smartchipantor2;

public class Result {
    public String Blower;
    public Float Humidity;
    public Float PH;
    public String P_Asam;
    public String P_Basa;
    public String P_Hama;
    public String P_Kapur;
    public Float Temperature;

    public Result(String blower, Float humidity, Float PH, String p_Asam, String p_Basa, String p_Hama, String p_Kapur, Float temperature) {
        Blower = blower;
        Humidity = humidity;
        this.PH = PH;
        P_Asam = p_Asam;
        P_Basa = p_Basa;
        P_Hama = p_Hama;
        P_Kapur = p_Kapur;
        Temperature = temperature;
    }

    public Result() {
    }
}
