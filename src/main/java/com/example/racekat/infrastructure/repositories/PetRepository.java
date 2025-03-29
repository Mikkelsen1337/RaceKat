package com.example.racekat.infrastructure.repositories;

import com.example.racekat.domain.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
List<Pet> findByOwnerId(Long ownderId);
}
