package com.neivor.vertx.Models;

import com.google.gson.annotations.SerializedName;

public class Condominio {
    @SerializedName("idCondomino")
    private String idCondomino;
    @SerializedName("nombrePagador")
    private String nombrePagador;
    @SerializedName("documentoPagador")
    private String documentoPagador;
    @SerializedName("numeroCasaDep")
    private String numeroCasaDep;
    @SerializedName("valoraPagar")
    private String valoraPagar;
    @SerializedName("servicios_idservicios")
    private String servicios_idservicios;

    public String getIdCondomino() {
        return idCondomino;
    }

    public void setIdCondomino(String idCondomino) {
        this.idCondomino = idCondomino;
    }

    public String getServicios_idservicios() {
        return servicios_idservicios;
    }

    public void setServicios_idservicios(String servicios_idservicios) {
        this.servicios_idservicios = servicios_idservicios;
    }

    public String getNombrePagador() {
        return nombrePagador;
    }

    public void setNombrePagador(String nombrePagador) {
        this.nombrePagador = nombrePagador;
    }

    public String getDocumentoPagador() {
        return documentoPagador;
    }

    public void setDocumentoPagador(String documentoPagador) {
        this.documentoPagador = documentoPagador;
    }

    public String getNumeroCasaDep() {
        return numeroCasaDep;
    }

    public void setNumeroCasaDep(String numeroCasaDep) {
        this.numeroCasaDep = numeroCasaDep;
    }

    public String getValoraPagar() {
        return valoraPagar;
    }

    public void setValoraPagar(String valoraPagar) {
        this.valoraPagar = valoraPagar;
    }


}
