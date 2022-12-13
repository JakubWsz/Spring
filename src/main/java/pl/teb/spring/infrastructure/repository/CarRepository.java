package pl.teb.spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.teb.spring.infrastructure.entity.Car;

public interface CarRepository extends JpaRepository<Car,Long> {

}
