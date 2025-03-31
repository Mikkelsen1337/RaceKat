package com.example.racekat.application.controllers;

import com.example.racekat.application.services.PetService;
import com.example.racekat.infrastructure.repositories.UserRepository;
import com.example.racekat.domain.models.Pet;
import com.example.racekat.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final UserRepository userRepository;
    private final PetService petService;

    public PetController(PetService petService, UserRepository userRepository) {
        this.petService = petService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAllPets(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            model.addAttribute("pets", petService.getPetsByOwner(userOptional.get().getId()));
        } else {
            model.addAttribute("pets", null);
        }
        return "pets";
    }

    @GetMapping({"/form", "/form/{id}"})
    public String showPetForm(@PathVariable(required = false) Long id, Model model) {
       Pet pet = (id != null) ? petService.getPetById(id) : new Pet();
       model.addAttribute("pet", pet);
        return "add-pet";
    }

    @PostMapping("/save")
    public String savePet(@ModelAttribute Pet pet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
       userOptional.ifPresent(pet::setOwner);
       petService.createOrUpdatePet(pet);
        return "redirect:/pets";
    }
}


