package pl.teb.spring.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Person")
@Table(name = "person")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean deleted;

    public Person(String firstname, String lastname, Integer age, String uuid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.uuid = uuid;
        this.deleted = false;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
