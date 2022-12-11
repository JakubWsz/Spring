package pl.teb.spring.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teb.spring.infrastructure.entity.Person;
import pl.teb.spring.infrastructure.repository.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {
private final PersonRepository personRepository;

public Person create(String firstname, String lastname, Integer age){
    return personRepository.save(new Person(firstname,lastname,age));
}
}
