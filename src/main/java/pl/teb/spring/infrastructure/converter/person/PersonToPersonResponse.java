package pl.teb.spring.infrastructure.converter.person;

import org.springframework.core.convert.converter.Converter;
import pl.teb.spring.api.dto.person.PersonResponse;
import pl.teb.spring.infrastructure.entity.Person;

public class PersonToPersonResponse implements Converter<Person, PersonResponse> {

    @Override
    public PersonResponse convert(Person person) {
        return PersonResponse.builder()
                .firstname(person.getFirstname())
                .lastname(person.getLastname())
                .age(person.getAge())
                .uuid(person.getUuid())
                .build();
    }
}
