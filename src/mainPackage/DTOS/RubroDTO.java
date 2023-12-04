package mainPackage.DTOS;

import mainPackage.Productos_y_detalles.Rubro;

public class RubroDTO {
    private int id;
    private String nombre;
    private String descripcion;

    public RubroDTO(Rubro rubroDto) {
        this.id = rubroDto.getId();
        this.nombre = rubroDto.getNombre();
        this.descripcion = rubroDto.getDescripcion();
    }

    public String toString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' ;
    }

}
