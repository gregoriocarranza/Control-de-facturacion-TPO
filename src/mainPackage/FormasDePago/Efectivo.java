package mainPackage.FormasDePago;

public class Efectivo extends FormaDePago {
    private String moneda;

    public Efectivo(float montoTotal, String moneda) {
        super(montoTotal);
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String toString() {
        return "{" +
                "montoTotal=" + montoTotal +
                ", moneda='" + moneda + '\'' +
                '}';
    }
}
