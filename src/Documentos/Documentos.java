package Documentos;

import Entidades.Proveedor;

import java.util.Date;

public abstract class Documentos {
    protected int id;
    protected Date fechaEmision;
    protected float importeTotal;
    protected String cuentaCorriente;
    protected Proveedor proveedor;
}
