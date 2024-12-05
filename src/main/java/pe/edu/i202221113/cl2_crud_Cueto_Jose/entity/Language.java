package pe.edu.i202221113.cl2_crud_Cueto_Jose.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer languageId;
    private String name;
    private Date lastUpdate;



    @OneToMany(mappedBy = "language")
    private List<Film> films;

}
