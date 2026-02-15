package org.example.activity2unit4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@JsonPropertyOrder({
        "id", "titulo", "plataforma", "genero", "anio",
        "desarrollador", "editor", "edicionEspecial",
        "estado", "valorEstimado", "notas"
})
@Getter
@Setter
@Entity
@Table(name = "juegos", schema = "sbgames")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "plataforma", nullable = false, length = 50)
    private String plataforma;

    @Column(name = "genero", length = 50)
    private String genero;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "desarrollador", length = 100)
    private String desarrollador;

    @Column(name = "editor", length = 100)
    private String editor;

    @Column(name = "edicion_especial")
    private Boolean edicionEspecial;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "valor_estimado")
    private Double valorEstimado;

    @Lob
    @Column(name = "notas")
    private String notas;

}