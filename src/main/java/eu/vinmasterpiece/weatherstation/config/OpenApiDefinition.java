package eu.vinmasterpiece.weatherstation.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title="Weather API",
                version = "1.0",
                contact = @Contact(
                        name = "Vincenzo Pimpinella",
                        url = "https://www.vinmasterpiece.eu",
                        email = "vincenzo.pimpinella@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
@Configuration
public class OpenApiDefinition {
}
