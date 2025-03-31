package com.example.racekat.application.controllers;

import com.example.racekat.application.services.PetService;
import com.example.racekat.infrastructure.repositories.UserRepository;
import com.example.racekat.application.services.UserService;
import com.example.racekat.domain.models.Pet;
import com.example.racekat.domain.models.User;
import com.example.racekat.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    UserRepository userRepository;
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pets")
    public String getAllPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "pets";
    }

    @GetMapping("/pets/add")
    public String showAddPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "add-pet";
    }

    @PostMapping("/pets/add")
    public String addPet(@ModelAttribute Pet pet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            pet.setOwner(userOptional.get());
            petService.savePet(pet);
        }
        return "redirect:/pets";
    }

    @GetMapping("/edit/{id}")
    public String showEditPetForm(@PathVariable Long id, Model model) {
        model.addAttribute("pet", petService.getPetById(id));
        return "pets/edit-pet";
    }

    @PostMapping("/edit/{id}")
    public String editPet(@ModelAttribute Pet pet, @RequestParam Long id, Model model) {
        pet.setId(id);
        petService.savePet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets";
    }
}


