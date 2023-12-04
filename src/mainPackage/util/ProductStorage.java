package mainPackage.util;

import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import mainPackage.DTOS.ProductoDTO;
import mainPackage.Productos_y_detalles.Producto;

public class ProductStorage {
    private String filePath;

    public ProductStorage(String filePath) {
        this.filePath = filePath;
    }

    public List<Producto> getAllProducts() throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filePath);

        List<Producto> products = gson.fromJson(reader, new TypeToken<List<Producto>>() {}.getType());
        reader.close();
        return products;
    }

    public Producto getById(int id) throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filePath);
        List<Producto> listaDeProductos = gson.fromJson(reader, new TypeToken<List<Producto>>() {}.getType());
        reader.close();
        for (Producto producto:listaDeProductos){
            if (producto.getId()==id){
                return producto;

            }
        }
        return null;
    }

    public void saveProducts(List<Producto> products) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter(filePath);
        gson.toJson(products, writer);
        writer.close();
    }
}
