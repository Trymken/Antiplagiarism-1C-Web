package web.antiplagiarism.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"web.antiplagiarism"})
public class AntiplagiarismApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntiplagiarismApplication.class, args);
    }

}
