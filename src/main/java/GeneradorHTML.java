import java.io.*;
import java.util.*;

/**
 * La clase {@code GeneradorHTML} se encarga de generar archivos HTML a partir de un archivo CSV
 * que contiene información sobre películas y una plantilla HTML.
 *
 * Esta clase permite leer datos de un archivo CSV, procesar una plantilla HTML y generar
 * archivos HTML personalizados para cada película listada en el CSV.
 */

public class GeneradorHTML {

    /**
     * Método principal que genera los archivos HTML a partir del CSV y la plantilla.
     *
     * @param archivoCSV      El nombre del archivo CSV que contiene los datos de las películas.
     * @param plantillaHTML   El nombre del archivo de plantilla HTML que se utilizará para generar
     *                        los archivos HTML de las películas.
     * @param carpetaSalida   El nombre de la carpeta donde se guardarán los archivos HTML generados.
     */
    public static void generarPaginas(String archivoCSV, String plantillaHTML, String carpetaSalida) {
        List<String[]> peliculas = leerCSV(archivoCSV);
        String plantilla = leerPlantilla(plantillaHTML);

        for (String[] pelicula : peliculas) {
            String contenidoHTML = generarContenidoHTML(plantilla, pelicula);
            String nombreArchivo = pelicula[1] + " - " + pelicula[0] + ".html"; // Título - ID
            escribirArchivoHTML(carpetaSalida, nombreArchivo, contenidoHTML);
        }
    }

    /**
     * Método para leer el archivo CSV y devolver una lista de películas.
     *
     * @param archivoCSV El nombre del archivo CSV que contiene los datos de las películas.
     * @return Una lista de arreglos de cadenas, donde cada arreglo representa una película y sus
     *         atributos (ID, título, año, director, género).
     */
    private static List<String[]> leerCSV(String archivoCSV) {
        List<String[]> peliculas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                peliculas.add(linea.split(",")); // Divide cada línea por comas
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return peliculas;
    }

    /**
     * Método para leer la plantilla HTML desde un archivo.
     *
     * @param archivoPlantilla El nombre del archivo de plantilla HTML.
     * @return Una cadena que representa el contenido de la plantilla HTML.
     */
    private static String leerPlantilla(String archivoPlantilla) {
        StringBuilder plantilla = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoPlantilla))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                plantilla.append(linea).append("\n"); // Añade cada línea y un salto de línea
            }
        } catch (IOException e) {
            System.out.println("Error al leer la plantilla HTML: " + e.getMessage());
        }
        return plantilla.toString();
    }

    /**
     * Método para generar el contenido HTML reemplazando los marcadores en la plantilla.
     *
     * @param plantilla La plantilla HTML que se utilizará como base.
     * @param pelicula  Un arreglo de cadenas que contiene los atributos de la película.
     * @return Una cadena que representa el contenido HTML generado para la película.
     */
    private static String generarContenidoHTML(String plantilla, String[] pelicula) {
        String contenidoHTML = plantilla;
        for (int i = 0; i < pelicula.length; i++) {
            contenidoHTML = contenidoHTML.replace("%%" + (i + 1) + "%%", pelicula[i]);
        }
        return contenidoHTML;
    }

    /**
     * Método para crear la carpeta de salida y escribir en ella los archivos HTML generados.
     *
     * @param carpetaSalida El nombre de la carpeta donde se guardará el archivo HTML.
     * @param nombreArchivo El nombre del archivo HTML a crear.
     * @param contenidoHTML El contenido HTML que se escribirá en el archivo.
     */
    private static void escribirArchivoHTML(String carpetaSalida, String nombreArchivo, String contenidoHTML) {
        File carpeta = new File(carpetaSalida);

        carpeta.mkdirs(); // Aquí creo la carpeta

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(carpeta, nombreArchivo)))) {
            writer.write(contenidoHTML); // Escribo el contenido en el archivo HTML
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo HTML: " + e.getMessage());
        }
    }
}
