package mainPackage.FormasDePago;

public abstract class FormaDePago {
    protected float montoTotal;

    public FormaDePago(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }
}
