package core.maidscc.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    // WebMvcConfigurer is an interface that provides methods to customize the Java-based configuration for Spring MVC.

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "HEAD","PATCH","OPTIONS")
                .allowedHeaders("*");
        //    .allowCredentials(true);
    }
}
