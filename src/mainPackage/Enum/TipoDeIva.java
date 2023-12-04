package mainPackage.Enum;

public enum TipoDeIva {
    IVA_2_5(2.5f),
    IVA_5(5f),
    IVA_10_5(10.5f),
    IVA_21(21f),
    IVA_27(27f);

    private final float valor;

    TipoDeIva(float valor) {
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }
}
