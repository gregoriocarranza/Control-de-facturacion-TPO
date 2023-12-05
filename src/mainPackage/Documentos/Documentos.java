package mainPackage.Documentos;

import java.time.LocalDate;


public abstract class Documentos {
    protected int id;
    protected LocalDate fechaDeEmision;
    protected float importeTotal;
    protected String cuentaCorriente;


    public Documentos(int id, LocalDate fechaDeEmision, float importeTotal, String cuentaCorriente) {
        this.id = id;
        this.fechaDeEmision = fechaDeEmision;
        this.importeTotal = importeTotal;
        this.cuentaCorriente = cuentaCorriente;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }



    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }


}
