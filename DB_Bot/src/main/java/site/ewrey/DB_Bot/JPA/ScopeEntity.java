package site.ewrey.DB_Bot.JPA;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "microscope")
public class ScopeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer serialNumber;
    private String modelName;
    private String baseName;
    private String status;



    @Override
    public String toString() {
        return "id =" + id +
                " | model =" + modelName +
                " | base =" + baseName +
                " | status=" + status + '\n';
    }


}
