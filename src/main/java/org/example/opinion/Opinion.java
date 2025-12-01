package org.example.opinion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.pelicula.Pelicula;

import java.io.Serializable;

@Entity
@Table(name = "opinion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "pelicula")
public class Opinion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "usuario", nullable = false, length = 64)
    private String usuario;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;
}

