package com.example.jsonapp;

import com.example.jsonapp.domain.User;
import com.example.jsonapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class JsonAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            //read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<User>> typeRef = new TypeReference<List<User>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/students.json");
            try{
                List<User> users = mapper.readValue(inputStream, typeRef);
                userService.save(users);
                System.out.println("Users saved!");
            }catch (IOException e){
                System.out.println("Unable to save! ");
                System.err.println(e.getMessage());
            }
        };
    }

}
