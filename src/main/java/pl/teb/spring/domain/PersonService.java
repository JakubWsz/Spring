package pl.teb.spring.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.teb.spring.domain.exception.AppExceptionCode;
import pl.teb.spring.domain.exception.DomainException;
import pl.teb.spring.infrastructure.entity.Person;
import pl.teb.spring.infrastructure.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person create(String firstname, String lastname, Integer age) {
        return personRepository.save(new Person(firstname, lastname, age, UUID.randomUUID().toString()));
    }

    public Person getPersonByUuid(String uuid) {
        return findPerson(uuid);
    }

    public Person updatePerson(String uuid, String firstname, String lastname, Integer age) {
        Person person = findPerson(uuid);
        return personRepository.save(updatePerson(person, firstname, lastname, age));
    }

    public Person patchPerson(String uuid, String firstname, String lastname, Integer age) {
        Person person = findPerson(uuid);
        return personRepository.save(patchPerson(person, firstname, lastname, age));
    }

    public void softDeletePerson(String uuid) {
        Person person = findPerson(uuid);
        person.setDeleted(true);
        personRepository.save(person);
    }

    public void hardDeletePerson(String uuid) {
        Person person = findPerson(uuid);
        personRepository.deleteById(person.getId());
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Page<Person> getPeoplePaged(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    private Person findPerson(String uuid) {
        Person person = personRepository.findByUuid(uuid).orElseThrow(
                AppExceptionCode.NO_SUCH_PERSON::createException
        );
        if (person.isDeleted()) {
            throw new DomainException(AppExceptionCode.PERSON_DELETED);
        } else return person;
    }

    private Person updatePerson(Person person, String firstname, String lastname, Integer age) {
        return Person.builder()
                .id(person.getId())
                .firstname(firstname)
                .lastname(lastname)
                .age(age)
                .uuid(person.getUuid())
                .build();
    }

    private Person patchPerson(Person person, String firstname, String lastname, Integer age) {
        if (firstname != null) {
            person.setFirstname(firstname);
        }
        if (lastname != null) {
            person.setLastname(lastname);
        }
        if (age != null) {
            person.setAge(age);
        }
        return person;
    }

}
