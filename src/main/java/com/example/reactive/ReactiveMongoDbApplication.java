package com.example.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoDbApplication {

    /*
        Delete all data and reinsert it each time the application is started
    */
    @Bean
    CommandLineRunner init(Repository repository) {
        return args -> repository
                .deleteAll()
                .subscribe(null, null, () ->
                        IntStream.range(1, 100)
                                .mapToObj(i -> new Person(UUID.randomUUID().toString(), "Person" + i))
                                .forEach(personne -> repository.save(personne).subscribe()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoDbApplication.class, args);
    }
}
