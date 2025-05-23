package us.metabolomics.sirius.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("msms_str")
    private String msms_str;
    @JsonProperty("pcm_str")
    private String pcm_str;
    @JsonProperty("charge")
    private boolean charge;

    // Custom constructor
    public Request(String msms, String pcm, boolean charge) {
        this.msms_str = msms;
        this.pcm_str = pcm;
        this.charge = charge;
    }

    // GETTERS
    public String getMsms() {
        return msms_str;
    }

    public String getPcm() {
        return pcm_str;
    }

    public Boolean getCharge() {
        return charge;
    }

    // SETTERS
    public void setMsms(String new_msms) {
        this.msms_str = new_msms;
    }

    public void setPcm(String new_pcm) {
        this.pcm_str = new_pcm;
    }

    public void setCharge(boolean new_charge) {
        this.charge = new_charge;
    }
}
