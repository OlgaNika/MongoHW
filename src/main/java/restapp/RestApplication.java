package restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication  {


    //TODO 1)fix issue with time zone = post new expence created with prev date - fixed with WA +4 hours added
    // the issue with TZ sync - needs to be fixed permanantly
//TODO 2) add user's authorisation - create default user admin/3
    //now particular user is able to login - this is fine for initial version

    //rest api supports this via GET /expence call.
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }


    }

