package adam.machima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MachimaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MachimaApplication.class, args);
    }

    @GetMapping
    public String upload() {
        return "Uploaded?";
    }
}
