package pe.edu.i202221113.cl2_crud_Cueto_Jose.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    private String  title;
    private String description;
    private Integer releaseYear;

    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double  rentalRate;
    private Integer length;
    private Double  replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;


    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;


}
