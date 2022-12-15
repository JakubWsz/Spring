package pl.teb.spring.api.dto.person;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class PersonRequest {
    @NotNull(message = "Firstname can't be null")
    @NotBlank(message = "Firstname can't be blank")
    @NotEmpty(message = "Firstname can't be empty")
    private String firstname;
    @NotNull(message = "Lastname can't be null")
    @NotBlank
    @NotBlank(message = "Lastname can't be blank")
    @NotEmpty(message = "Lastname can't be empty")
    private String lastname;
    @NotNull(message = "Age can't be null")
    @Min(value = 18,message = "Person can't be underage")
    private Integer age;
}
