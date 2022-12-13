package pl.teb.spring.api.dto.person;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonRequest {
    private String firstname;
    private String lastname;
    private Integer age;
}
