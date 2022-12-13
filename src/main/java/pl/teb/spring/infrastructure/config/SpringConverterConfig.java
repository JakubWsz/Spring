package pl.teb.spring.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.teb.spring.infrastructure.converter.person.PersonToPersonResponse;

@Configuration
public class SpringConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PersonToPersonResponse());
    }
}
