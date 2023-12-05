package mainPackage.FormasDePago;

import java.util.Date;

public class Cheque extends FormaDePago {
    private Date fechaEmision;
    private Date fechaVencimiento;
    private String firmante;

    public Cheque(float montoTotal, Date fechaEmision, Date fechaVencimiento, String firmante) {
        super(montoTotal);
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.firmante = firmante;
    }


    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getFirmante() {
        return firmante;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }
    public String toString() {
        return "{" +
                "montoTotal=" + montoTotal +
                ", fechaEmision=" + fechaEmision +
                ", fechaVencimiento=" + fechaVencimiento +
                ", firmante='" + firmante + '\'' +
                '}';
    }
}
