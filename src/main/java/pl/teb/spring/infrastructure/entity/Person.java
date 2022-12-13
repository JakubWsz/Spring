package pl.teb.spring.infrastructure.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @SequenceGenerator(
            name = "person_id_sequence",
            sequenceName = "person_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_id_sequence"
    )
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String uuid;

    public Person() {}

    public Person(String firstname, String lastname, Integer age, String uuid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }
}
