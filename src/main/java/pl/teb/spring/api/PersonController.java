package pl.teb.spring.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.domain.PersonService;
import pl.teb.spring.infrastructure.entity.Person;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
private final PersonService personService;

    @PostMapping("/create")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200")
            })
   public Person createPerson(@RequestParam String firstname, String lastname, Integer age){
       return personService.create(firstname,lastname,age);
    }
}
