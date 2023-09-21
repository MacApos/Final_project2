package pl.coderslab.final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class FinalProjectApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(FinalProjectApplication.class, args);
//    }
//
//}
@SpringBootApplication
public class FinalProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FinalProjectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class);
    }
}