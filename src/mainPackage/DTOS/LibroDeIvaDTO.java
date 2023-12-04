package mainPackage.DTOS;
import java.util.Date;

import mainPackage.Documentos.Documentos;

public class LibroDeIvaDTO {
    private String cuit;
    private Date fecha;
    private int tipoDeIvaDelProveedor;
    private String nombreDelProveedor;
    private int total;
    private Documentos tipoDeDocumento;

    public LibroDeIvaDTO(String cuit, Date fecha, int tipoDeIvaDelProveedor,
                         String nombreDelProveedor, int total, Documentos tipoDeDocumento) {
        this.cuit = cuit;
        this.fecha = fecha;
        this.tipoDeIvaDelProveedor = tipoDeIvaDelProveedor;
        this.nombreDelProveedor = nombreDelProveedor;
        this.total = total;
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public static LibroDeIvaDTO createLibroDeIvaDTO(String cuit, Date fecha, int tipoDeIvaDelProveedor,
                                                    String nombreDelProveedor, int total, Documentos tipoDeDocumento) {
        return new LibroDeIvaDTO(cuit, fecha, tipoDeIvaDelProveedor, nombreDelProveedor, total, tipoDeDocumento);
    }
}