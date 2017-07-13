import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        /** UNIR PALABRAS FALTANTES */
        /*Map<String, Integer> vocabularioGlobal = new TreeMap<>(); // Key: palabra, Value: cantidad de documentos donde aparece
        Archivo archivo = new Archivo();
        archivo.crearArchivo("vocabularioCompleto", ".txt");*/

        /** Carga el archivo de vocabulario */
        /*BufferedReader vocabularioViejo = new BufferedReader(new InputStreamReader(new FileInputStream("vocabulario.txt"), "utf-8"));
        String palabraVieja = vocabularioViejo.readLine();

        while(palabraVieja != null) {
            String[] entrada = palabraVieja.split("\\s");
            if(!vocabularioGlobal.containsKey(entrada[0]))
                vocabularioGlobal.put(entrada[0], Integer.parseInt(entrada[1]));
            palabraVieja = vocabularioViejo.readLine();
        }

        vocabularioViejo.close();

        System.out.println("\nPalabras viejas listas!");


        BufferedReader vocabularioNuevo = new BufferedReader(new InputStreamReader(new FileInputStream("vocabularioNuevo.txt"), "utf-8"));
        String palabraNueva = vocabularioNuevo.readLine();

        while(palabraNueva != null) {
            String[] entrada = palabraNueva.split("\\s");

            if (vocabularioGlobal.containsKey(entrada[0]))
                vocabularioGlobal.put(entrada[0], vocabularioGlobal.get(entrada[0]) + Integer.parseInt(entrada[1]));
            else
                vocabularioGlobal.put(entrada[0], Integer.parseInt(entrada[1]));
            palabraNueva = vocabularioNuevo.readLine();
        }

        vocabularioNuevo.close();

        System.out.println("\nPalabras nuevas listas!");

        Map<String, Integer> vocabularioOrdenado = ordenarDCPorValor(vocabularioGlobal, true);

        for (Map.Entry<String, Integer> palabra : vocabularioOrdenado.entrySet()) {
            archivo.escribirEnArchivo(palabra.getKey() + " " + palabra.getValue(), true);
        }

        System.out.println("\nFinish!");*/

        /** ORDENAR VOCABULARIO ALFABETICAMENTE */
        Map<String, Integer> vocabularioGlobal = new TreeMap<>(); // Key: palabra, Value: cantidad de documentos donde aparece
        Archivo archivo = new Archivo();
        archivo.crearArchivo("vocabularioOrdenado", ".txt");

        BufferedReader vocabulario = new BufferedReader(new InputStreamReader(new FileInputStream("vocabularioCortado.txt"), "utf-8"));
        String linea = vocabulario.readLine();

        while(linea != null) {
            String[] entrada = linea.split("\\s");
            if(!vocabularioGlobal.containsKey(entrada[0]))
                vocabularioGlobal.put(entrada[0], Integer.parseInt(entrada[1]));
            linea = vocabulario.readLine();
        }

        vocabulario.close();

        for (Map.Entry<String, Integer> palabra : vocabularioGlobal.entrySet()) {
            archivo.escribirEnArchivo(palabra.getKey() + " " + palabra.getValue(), true);
        }

        System.out.println("\nListo!");
    }

    public static Map<String, Integer> ordenarDCPorValor(Map<String, Integer> map, Boolean mayorAMenor )
    {
        List<Map.Entry<String, Integer>> lista = new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );

        Collections.sort( lista, new Comparator<Map.Entry<String, Integer>>() {
            public int compare( Map.Entry<String, Integer> palabra1, Map.Entry<String, Integer> palabra2 )
            {
                if(mayorAMenor)
                    return palabra2.getValue().compareTo(palabra1.getValue());
                else
                    return palabra1.getValue().compareTo(palabra2.getValue());
            }
        } );

        Map<String, Integer> resultado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : lista)
            resultado.put( entry.getKey(), entry.getValue() );

        return resultado;
    }
}