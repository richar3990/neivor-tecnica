package com.neivor.vertx.Models;

import com.google.gson.annotations.SerializedName;

public class DetallesCuota {
    @SerializedName("IDDETALLECUOTA")
    private String IDDETALLECUOTA;
    @SerializedName("IDDEUDA_CONDOMINO")
    private String IDDEUDA_CONDOMINO;
    @SerializedName("NUMEROCUOTA")
    private String NUMEROCUOTA;
    @SerializedName("DETALLECUOTA")
    private String DETALLECUOTA;
    @SerializedName("FECHAVENCIMIENTO")
    private String FECHAVENCIMIENTO;
    @SerializedName("IMPORTECUOTA")
    private String IMPORTECUOTA;
    @SerializedName("IMPORTECOMISION")
    private String IMPORTECOMISION;

    public String getIDDETALLECUOTA() {
        return IDDETALLECUOTA;
    }

    public void setIDDETALLECUOTA(String IDDETALLECUOTA) {
        this.IDDETALLECUOTA = IDDETALLECUOTA;
    }

    public String getIDDEUDA_CONDOMINO() {
        return IDDEUDA_CONDOMINO;
    }

    public void setIDDEUDA_CONDOMINO(String IDDEUDA_CONDOMINO) {
        this.IDDEUDA_CONDOMINO = IDDEUDA_CONDOMINO;
    }

    public String getNUMEROCUOTA() {
        return NUMEROCUOTA;
    }

    public void setNUMEROCUOTA(String NUMEROCUOTA) {
        this.NUMEROCUOTA = NUMEROCUOTA;
    }

    public String getDETALLECUOTA() {
        return DETALLECUOTA;
    }

    public void setDETALLECUOTA(String DETALLECUOTA) {
        this.DETALLECUOTA = DETALLECUOTA;
    }

    public String getFECHAVENCIMIENTO() {
        return FECHAVENCIMIENTO;
    }

    public void setFECHAVENCIMIENTO(String FECHAVENCIMIENTO) {
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
    }

    public String getIMPORTECUOTA() {
        return IMPORTECUOTA;
    }

    public void setIMPORTECUOTA(String IMPORTECUOTA) {
        this.IMPORTECUOTA = IMPORTECUOTA;
    }

    public String getIMPORTECOMISION() {
        return IMPORTECOMISION;
    }

    public void setIMPORTECOMISION(String IMPORTECOMISION) {
        this.IMPORTECOMISION = IMPORTECOMISION;
    }
}
