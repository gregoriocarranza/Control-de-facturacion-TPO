package mainPackage.Documentos;

import java.time.LocalDate;
import java.util.Date;
import mainPackage.Entidades.Proveedor;

public class Factura extends Documentos {
    private int cuit;
    private String razonSocial;
    private String nombre;
    private String direccion;
    private int telefono;
    private String correo;
    private float ingresosBrutos;
    private LocalDate inicioActividades;

    public Factura(int id, LocalDate fechaDeEmision, float importeTotal, String cuentaCorriente, int cuit, String razonSocial, String nombre, String direccion, int telefono, String correo, float ingresosBrutos, LocalDate inicioActividades) {
        super(id, fechaDeEmision, importeTotal, cuentaCorriente);
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.ingresosBrutos = ingresosBrutos;
        this.inicioActividades = inicioActividades;
    }

    public int getCuit() {
        return cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public float getIngresosBrutos() {
        return ingresosBrutos;
    }

    public LocalDate getInicioActividades() {
        return inicioActividades;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIngresosBrutos(float ingresosBrutos) {
        this.ingresosBrutos = ingresosBrutos;
    }

    public void setInicioActividades(LocalDate inicioActividades) {
        this.inicioActividades = inicioActividades;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", fechaDeEmision=" + fechaDeEmision +
                ", importeTotal=" + importeTotal +
                ", cuentaCorriente='" + cuentaCorriente + '\'' +
                ", cuit=" + cuit +
                ", razonSocial='" + razonSocial + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", correo='" + correo + '\'' +
                ", ingresosBrutos=" + ingresosBrutos +
                ", inicioActividades=" + inicioActividades +
                '}';
    }

}
