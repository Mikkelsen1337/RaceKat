package com.example.racekat.application.services;

import com.example.racekat.domain.models.Pet;
import com.example.racekat.infrastructure.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
    public List<Pet> getPetsByUserId(Long userId) {
        return petRepository.findByOwnerId(userId);
    }
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }
    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
