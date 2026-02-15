package org.example.activity2unit4.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="usuarios", schema = "sbgames")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name="nombre", nullable = false, length = 25)
    private String nombre;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Game> games;
}
