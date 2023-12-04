package mainPackage.Productos_y_detalles;

import java.io.IOException;


import mainPackage.DTOS.RubroDTO;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;


public class Producto  {
    private int id;
    private String nombre;
    private TipoDeUnidad tipoDeUnidad;
    private float precio;
    private TipoDeIva tipoDeIVA;
    private Rubro rubro;

    public Producto() throws IOException {

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



    public TipoDeUnidad getTipoDeUnidad() {
        return tipoDeUnidad;
    }

    public void setTipoDeUnidad(TipoDeUnidad tipoDeUnidad) {
        this.tipoDeUnidad = tipoDeUnidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoDeIva getTipoDeIVA() {
        return tipoDeIVA;
    }

    public void setTipoDeIVA(TipoDeIva tipoDeIVA) {
        this.tipoDeIVA = tipoDeIVA;
    }

    public RubroDTO getRubro() {
        return new RubroDTO(rubro);
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

}
