package com.example.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface Repository extends ReactiveMongoRepository<Person, String> {
}