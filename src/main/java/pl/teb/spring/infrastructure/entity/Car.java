package pl.teb.spring.infrastructure.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
public class Car {
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_sequence"
    )
    private Long id;
    private String carMake;
    private String model;
    private Integer engine;

    public Car(String carMake, String model, Integer engine) {
        this.carMake = carMake;
        this.model = model;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carMake='" + carMake + '\'' +
                ", model='" + model + '\'' +
                ", engine=" + engine +
                '}';
    }
}
