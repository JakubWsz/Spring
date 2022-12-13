package pl.teb.spring.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teb.spring.domain.exception.AppExceptionCode;
import pl.teb.spring.infrastructure.entity.Person;
import pl.teb.spring.infrastructure.repository.PersonRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person create(String firstname, String lastname, Integer age) {
        return personRepository.save(new Person(firstname, lastname, age, UUID.randomUUID().toString()));
    }

    public Person getPersonByUuid(String uuid) {
        return personRepository.findByUuid(uuid).orElseThrow(
                AppExceptionCode.NO_SUCH_Person::createException
        );
    }
}
