package pl.teb.spring;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.teb.spring.infrastructure.entity.Person;
import pl.teb.spring.infrastructure.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        Faker faker = new Faker();
        Person person = new Person(faker.name().firstName(), faker.name().lastName(),
                faker.number().numberBetween(17, 55));
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("firstname").descending());
        Person jan = new Person("Jan", "Kowalski", 50);
        Person jan2 = new Person("Jan", "Zalewski", 50);
        return args -> {
            personRepository.save(person);
            personRepository.save(jan);
            personRepository.save(jan2);
            personRepository.findById(1L).ifPresent(System.out::println);
            personRepository.saveAll(generatePersonList());
            Page<Person> page = personRepository.findAll(pageRequest);
            System.out.println(personRepository.findPeopleByFirstnameAndAge("Jan", 50));
            System.out.println(personRepository.findPersonByAgeGreaterThan(35));
            System.out.println(personRepository.findPersonByLastnameStartingWith('S'));
        };
    }

    private List<Person> generatePersonList() {
        List<Person> personList = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            personList.add(new Person(faker.name().firstName(), faker.name().lastName(),
                    faker.number().numberBetween(17, 55)));
        }
        return personList;
    }
}