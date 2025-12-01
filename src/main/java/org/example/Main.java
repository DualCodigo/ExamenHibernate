package org.example;

import org.example.utils.DataService;
import org.example.opinion.Opinion;
import org.example.pelicula.Pelicula;
import org.example.opinion.OpinionRepository;
import org.example.pelicula.PeliculaRepository;
import org.example.utils.DataProvider;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataService ds = new DataService(
                new PeliculaRepository(DataProvider.getSessionFactory()),
                new OpinionRepository(DataProvider.getSessionFactory())
        );

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Registrar nueva película");
            System.out.println("2. Listar películas");
            System.out.println("3. Añadir opinión a película");
            System.out.println("4. Listar opiniones");
            System.out.println("5. Obtener opiniones de un usuario");
            System.out.println("6. Películas con baja puntuación (<3)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título de la película: ");
                    String titulo = sc.nextLine();
                    Pelicula p = ds.registrarPelicula(titulo);
                    System.out.println("Película registrada: " + p);
                }
                case 2 -> {
                    List<Pelicula> peliculas = ds.listarPeliculas();
                    System.out.println("Películas registradas:");
                    peliculas.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID de la película: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Descripción de la opinión: ");
                    String desc = sc.nextLine();
                    System.out.print("Correo del usuario: ");
                    String correo = sc.nextLine();
                    System.out.print("Puntuación (0-10): ");
                    int puntuacion = sc.nextInt();
                    sc.nextLine();

                    Opinion op = ds.anadirOpinionAPelicula(id, desc, correo, puntuacion);
                    System.out.println("Opinión añadida: " + op);
                }
                case 4 -> {
                    List<Opinion> opiniones = ds.listarOpiniones();
                    System.out.println("Opiniones registradas:");
                    opiniones.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Correo del usuario: ");
                    String correo = sc.nextLine();
                    List<Opinion> opinionesUsuario = ds.obtenerOpinionesDeUsuario(correo);
                    System.out.println("Opiniones de " + correo + ":");
                    opinionesUsuario.forEach(System.out::println);
                }
                case 6 -> {
                    List<String> bajas = ds.peliculasConBajaPuntuacion();
                    System.out.println("Películas con puntuación baja (<3):");
                    bajas.forEach(System.out::println);
                }
                case 0 -> {
                    salir = true;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        }

        sc.close();
    }
}
