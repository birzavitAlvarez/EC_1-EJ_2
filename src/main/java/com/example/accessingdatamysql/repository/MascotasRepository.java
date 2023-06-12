package com.example.accessingdatamysql.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.model.Mascotas;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MascotasRepository extends CrudRepository<Mascotas, Integer> {

}