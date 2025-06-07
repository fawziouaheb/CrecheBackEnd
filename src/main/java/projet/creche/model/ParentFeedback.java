package projet.creche.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// ParentFeedback.java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parentName;

    private int rating;

    @Column(length = 1000)
    private String message;

    private LocalDate date;
}
