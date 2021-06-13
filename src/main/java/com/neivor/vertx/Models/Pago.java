package com.neivor.vertx.Models;

public class Pago {
    private String IDTRANSACCION;
    private String IDUSUARIOS;
    private String IDORDENES_PAGO;
    private String MONTOTOTAL;
    private String FECHAPAGO;
    private String NOMBREFACTURA;
    private String NITFACTURA;

    public String getIDTRANSACCION() {
        return IDTRANSACCION;
    }

    public void setIDTRANSACCION(String IDTRANSACCION) {
        this.IDTRANSACCION = IDTRANSACCION;
    }

    public String getIDUSUARIOS() {
        return IDUSUARIOS;
    }

    public void setIDUSUARIOS(String IDUSUARIOS) {
        this.IDUSUARIOS = IDUSUARIOS;
    }

    public String getIDORDENES_PAGO() {
        return IDORDENES_PAGO;
    }

    public void setIDORDENES_PAGO(String IDORDENES_PAGO) {
        this.IDORDENES_PAGO = IDORDENES_PAGO;
    }

    public String getMONTOTOTAL() {
        return MONTOTOTAL;
    }

    public void setMONTOTOTAL(String MONTOTOTAL) {
        this.MONTOTOTAL = MONTOTOTAL;
    }

    public String getFECHAPAGO() {
        return FECHAPAGO;
    }

    public void setFECHAPAGO(String FECHAPAGO) {
        this.FECHAPAGO = FECHAPAGO;
    }

    public String getNOMBREFACTURA() {
        return NOMBREFACTURA;
    }

    public void setNOMBREFACTURA(String NOMBREFACTURA) {
        this.NOMBREFACTURA = NOMBREFACTURA;
    }

    public String getNITFACTURA() {
        return NITFACTURA;
    }

    public void setNITFACTURA(String NITFACTURA) {
        this.NITFACTURA = NITFACTURA;
    }
}
