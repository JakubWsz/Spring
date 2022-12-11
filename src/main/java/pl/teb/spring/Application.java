package pl.teb.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    stwórz encje Car o polach: id, carMake, model, engine
//    stwórz CarRepository
//    stwórz klasę CarService, któa zapisze samochód
//    stwórz CarController, który wystawi metody z serwisu
}