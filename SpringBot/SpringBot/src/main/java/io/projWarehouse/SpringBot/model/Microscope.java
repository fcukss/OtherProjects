package io.projWarehouse.SpringBot.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "microscope")
public class Microscope {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer serialNumber;
    private String modelName;
    private String baseName;
    private String status;
    private Long managerId;


    @Override
    public String toString() {
        return "id =" + id +
                " | model =" + modelName +
                " | base =" + baseName +
                " | status=" + status + '\n';
    }


}
