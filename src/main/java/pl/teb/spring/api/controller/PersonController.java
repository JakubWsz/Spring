package pl.teb.spring.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.api.dto.person.PersonRequest;
import pl.teb.spring.api.dto.person.PersonResponse;
import pl.teb.spring.api.exception.AppExceptionHandler;
import pl.teb.spring.domain.PersonService;
import pl.teb.spring.infrastructure.entity.Person;

import java.util.Objects;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final ConversionService conversionService;
    private final PersonService personService;

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest) {
        Person person = personService.create(personRequest.getFirstname(),
                personRequest.getLastname(), personRequest.getAge());
        return ResponseEntity.ok(Objects.requireNonNull(conversionService.convert(person, PersonResponse.class)));
    }

    @GetMapping("/{uuid}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<PersonResponse> getPersonByUuid(@PathVariable String uuid) {
      return ResponseEntity.ok(Objects.requireNonNull(conversionService.convert(
              personService.getPersonByUuid(uuid), PersonResponse.class)));
    }


}
