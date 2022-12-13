package pl.teb.spring;

import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.teb.spring.infrastructure.entity.Car;
import pl.teb.spring.infrastructure.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository) {
        //zapisz listę do repozytorium
        //pobierz spaginowaną listę samochodów z pojemnością slilnika mniejszą niż 3
        //pobierz spaginowaną listę samochodów z pojemnością slilnika mniejszą większą niż 2 ale mniejszą niż 5 posortowaną maloejąco po modelu
        //pobierz spaginowaną listę samochodów z modelem kończącym się na "a" posortowaną po marce i pojemności silnika
        //pobierz spaginowaną listę samochodów z markami zaczynającymi się na litery w przedziale od "G" do "Z"
        PageRequest exampleRequest = PageRequest.of(0, 10,Sort.by("example"));
        return args -> {

        };
    }

    List<Car> generateCars() {
        Faker faker = new Faker();
        Random r = new Random();
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            cars.add(new Car(faker.vehicle().make(), faker.vehicle().model(),
                    r.nextInt(7 - 1) + 1));
        }
        return cars;
    }
}