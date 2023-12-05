package mainPackage.util;

import java.util.List;

public class Utils {

    public static <T> void imprimirLista(List<T> lista) {
        for (T elemento : lista) {
            System.out.println(elemento+"\n");
        }
    }
}
