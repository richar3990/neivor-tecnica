package com.neivor.vertx.Models;

import com.google.gson.annotations.SerializedName;


public class Ordenes_pago {
    @SerializedName("idordenes_pago")
    private String idordenes_pago;
    @SerializedName("idcondominio")
    private String idcondominio;
    @SerializedName("estadoOrdenPago")
    private String estadoOrdenPago;
    @SerializedName("servicios_idservicios")
    private String servicios_idservicios;
    @SerializedName("montoPago")
    private String montoPago;

    public String getIdordenes_pago() {
        return idordenes_pago;
    }

    public void setIdordenes_pago(String idordenes_pago) {
        this.idordenes_pago = idordenes_pago;
    }

    public String getIdcondominio() {
        return idcondominio;
    }

    public void setIdcondominio(String idcondominio) {
        this.idcondominio = idcondominio;
    }

    public String getEstadoOrdenPago() {
        return estadoOrdenPago;
    }

    public void setEstadoOrdenPago(String estadoOrdenPago) {
        this.estadoOrdenPago = estadoOrdenPago;
    }

    public String getServicios_idservicios() {
        return servicios_idservicios;
    }

    public void setServicios_idservicios(String servicios_idservicios) {
        this.servicios_idservicios = servicios_idservicios;
    }

    public String getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(String montoPago) {
        this.montoPago = montoPago;
    }
}
