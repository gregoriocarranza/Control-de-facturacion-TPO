package mainPackage.Productos_y_detalles;

import mainPackage.Enum.TipoDeUnidad;

public class Rubro {
    private int id;
    private String nombre;
    private String descripcion;

    public Rubro(int id, String name, String descripcion) {
        this.id= id;
        this.nombre= name;
        this.descripcion=descripcion ;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return "{" +"id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
