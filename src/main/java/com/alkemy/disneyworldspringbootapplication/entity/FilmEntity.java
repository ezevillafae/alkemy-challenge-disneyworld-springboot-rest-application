package com.alkemy.disneyworldspringbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@Data
public class FilmEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    private Double rating;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "film_character",
            joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_character", referencedColumnName = "id")
    )
    private List<CharacterEntity> characters = new ArrayList<>();


    public void addCharacter(CharacterEntity entity) {
        characters.add(entity);
    }

    public void removeCharacter(CharacterEntity entity) {
        characters.remove(entity);
    }
}
