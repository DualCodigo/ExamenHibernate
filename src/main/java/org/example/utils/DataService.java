package org.example.utils;



import org.example.opinion.Opinion;
import org.example.opinion.OpinionRepository;
import org.example.pelicula.Pelicula;
import org.example.pelicula.PeliculaRepository;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    private final PeliculaRepository peliculaRepository;
    private final OpinionRepository opinionRepository;

    public DataService(PeliculaRepository peliculaRepository, OpinionRepository opinionRepository) {
        this.peliculaRepository = peliculaRepository;
        this.opinionRepository = opinionRepository;
    }

    public Pelicula registrarPelicula(String titulo) {
        Pelicula p = new Pelicula();
        p.setTitulo(titulo);
        return peliculaRepository.save(p);
    }

    public List<Opinion> obtenerOpinionesDeUsuario(String correo) {
        List<Opinion> todas = opinionRepository.findAll();
        List<Opinion> resultado = new ArrayList<>();

        if (correo == null) {
            return resultado;
        }

        String correoLower = correo.toLowerCase();

        for (Opinion o : todas) {
            if (o.getUsuario() != null && o.getUsuario().toLowerCase().equals(correoLower)) {
                resultado.add(o);
            }
        }

        return resultado;
    }

    public Opinion anadirOpinionAPelicula(Integer peliculaId, String descripcion, String usuario, Integer puntuacion) {

        var peliculaObjetivo = peliculaRepository.findById(peliculaId.longValue());
        if (peliculaObjetivo.isEmpty()) {
            throw new IllegalArgumentException("No existe pelicula con id=" + peliculaId);
        }

        Opinion op = new Opinion();
        op.setDescripcion(descripcion);
        op.setUsuario(usuario);
        op.setPuntuacion(puntuacion);
        op.setPelicula(peliculaObjetivo.get());

        return opinionRepository.save(op);
    }

    public List<String> peliculasConBajaPuntuacion() {
        List<Pelicula> todas = peliculaRepository.findAll();
        List<String> titulos = new ArrayList<>();

        for (Pelicula p : todas) {
            List<Opinion> opiniones = p.getOpiniones();
            if (opiniones == null || opiniones.isEmpty()) {continue;}

            boolean tieneOpinionBaja = false;

            int i = 0;
            while (i < opiniones.size() && !tieneOpinionBaja) {
                Opinion o = opiniones.get(i);
                Integer puntuacion = o.getPuntuacion();
                if (puntuacion != null && puntuacion <= 3) {
                    tieneOpinionBaja = true;
                }
                i++;
            }

            if (tieneOpinionBaja) {
                titulos.add(p.getTitulo());
            }
        }

        return titulos;
    }

    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    public List<Opinion> listarOpiniones() {
        return opinionRepository.findAll();
    }
}
