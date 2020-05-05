package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue
    private Long Id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}
