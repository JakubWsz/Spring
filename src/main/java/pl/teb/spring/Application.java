package pl.teb.spring;


import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.teb.spring.infrastructure.entity.Person;
import pl.teb.spring.infrastructure.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> personRepository.saveAll(generatePeople());
    }

    List<Person> generatePeople() {
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            people.add(new Person(faker.name().firstName(), faker.name().firstName(),
                    faker.number().numberBetween(18, 55), UUID.randomUUID().toString()));
        }
        return people;
    }
}