package com.neivor.vertx.Models;

import com.google.gson.annotations.SerializedName;

public class Condominio {
    @SerializedName("idCondominio")
    private String idCondominio;
    @SerializedName("nombrePagador")
    private String nombrePagador;
    @SerializedName("documentoPagador")
    private String documentoPagador;
    @SerializedName("numeroCasaDep")
    private String numeroCasaDep;
    @SerializedName("valoraPagar")
    private String valoraPagar;

    public String getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(String idCondominio) {
        this.idCondominio = idCondominio;
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
