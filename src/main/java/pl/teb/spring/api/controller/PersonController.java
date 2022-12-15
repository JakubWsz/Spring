package pl.teb.spring.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teb.spring.api.dto.person.PersonRequest;
import pl.teb.spring.api.dto.person.PersonResponse;
import pl.teb.spring.api.exception.AppExceptionHandler;
import pl.teb.spring.domain.PersonService;
import pl.teb.spring.infrastructure.entity.Person;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest personRequest) {
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

    @PutMapping("/{uuid}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable String uuid,
                                                       @RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(Objects.requireNonNull(conversionService.convert(
                personService.updatePerson(uuid, personRequest.getFirstname(),
                        personRequest.getLastname(), personRequest.getAge()), PersonResponse.class)));
    }

    @PatchMapping("/{uuid}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<PersonResponse> patchPerson(@PathVariable String uuid,
                                                      @RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(Objects.requireNonNull(conversionService.convert(
                personService.patchPerson(uuid, personRequest.getFirstname(),
                        personRequest.getLastname(), personRequest.getAge()), PersonResponse.class)));
    }

    @DeleteMapping("/{uuid}/soft")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<Void> softDeletePerson(@PathVariable String uuid) {
        personService.softDeletePerson(uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}/hard")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<Void> hardDeletePerson(@PathVariable String uuid) {
        personService.hardDeletePerson(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<List<PersonResponse>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople().stream()
                .map(p -> conversionService.convert(p, PersonResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(name = "/page", produces = "application/json")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation =
                            AppExceptionHandler.Error.class)))
            })
    public ResponseEntity<Page<PersonResponse>> getPeoplePaged(@RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam(defaultValue = "lastname")
                                                               String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(personService.getPeoplePaged(pageable)
                .map(p -> conversionService.convert(p, PersonResponse.class)));
    }
}
