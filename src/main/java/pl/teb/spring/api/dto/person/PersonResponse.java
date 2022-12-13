package pl.teb.spring.api.dto.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {
    private String firstname;
    private String lastname;
    private Integer age;
    private String uuid;
}
