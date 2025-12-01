package org.example.pelicula;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.opinion.Opinion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelicula")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "opiniones")


public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @OneToMany(mappedBy = "pelicula", fetch = FetchType.LAZY)
    private List<Opinion> opiniones = new ArrayList<>();
}

