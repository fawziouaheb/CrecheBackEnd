package projet.creche;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Date;

@SpringBootApplication
public class CrecheApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Injecter les variables dans le système
        System.setProperty("OUTLOOK_USERNAME", dotenv.get("OUTLOOK_USERNAME"));
        System.setProperty("OUTLOOK_PASSWORD", dotenv.get("OUTLOOK_PASSWORD"));

        SpringApplication.run(CrecheApplication.class, args);

        System.out.println("Application a bien démarée");
        System.out.println(new Date().toString());
    }
}