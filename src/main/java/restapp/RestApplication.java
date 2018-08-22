package restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication  {

//TODO 1)fix issue with time zone = post new expence created with prev date
//TODO 2) add user's authorisation - create default user admin/125040
//TODO 3) choose deployment's place
//TODO 4) deploy it with db backup.( just json backup for first month
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }


    }

