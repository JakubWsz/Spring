package pl.teb.spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.teb.spring.infrastructure.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByFirstnameAndAge(String firstname, Integer age);

    List<Person> findPersonByAgeGreaterThan(Integer greaterThan);

    List<Person> findPersonByLastnameStartingWith(char character);
}