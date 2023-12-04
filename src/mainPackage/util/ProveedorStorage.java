package mainPackage.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mainPackage.Entidades.Proveedor;


import java.io.*;
import java.util.List;

public class ProveedorStorage {
    private String filePath;

    public ProveedorStorage(String filePath) {
        this.filePath = filePath;
    }

    public List<Proveedor> getAllProveedores() throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filePath);
        List<Proveedor> products = gson.fromJson(reader, new TypeToken<List<Proveedor>>() {}.getType());
        reader.close();
        return products;
    }

    public void saveProducts(List<Proveedor> products) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter(filePath);
        gson.toJson(products, writer);
        writer.close();
    }
}
