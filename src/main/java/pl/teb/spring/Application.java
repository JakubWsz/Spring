package pl.teb.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository) {
        //zapisz obiekt car w reposytorium
        //pobierz z bazy car po id
        //stwórz listę składającą się z 10 obiektów car i zapisz je w repozytorum
        //pobierz listę obiektów car
        //pobierz spaginowaną listę posortowaną po modelu
        //pobierz spaginowaną listę posortowaną po marce malejąco i posortowaną po silniku
        return args -> {

        };
    }
}