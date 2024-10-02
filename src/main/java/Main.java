public class Main {
    public static void main(String[] args) {

        // He tenido que poner la ruta absoluta de los archivos porque no me funcionaba con la relativa.

        String archivoCSV = "C:\\Users\\kokit\\Desktop\\CESUR 2º AÑO\\JAVA\\Acceso a Datos\\Reto 1 - Archivos - Acceso a Datos\\src\\main\\resources\\peliculas.csv";
        String plantillaHTML = "C:\\Users\\kokit\\Desktop\\CESUR 2º AÑO\\JAVA\\Acceso a Datos\\Reto 1 - Archivos - Acceso a Datos\\src\\main\\resources\\pelis.html";
        String carpetaSalida = "salida";

        GeneradorHTML.generarPaginas(archivoCSV, plantillaHTML, carpetaSalida);

        // He observado que con la película El Señor de los Anillos no se genera el HTML, y es muy
        // probable que se deba a que tiene en su nombre dos puntos (:) y puede que el método no sepa leerlo,
        // o no sea un caracter válido.
    }
}
