package com.example.racekat.application.controllers;

import com.example.racekat.application.services.PetService;
import com.example.racekat.domain.models.Pet;
import com.example.racekat.domain.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping
    public String getAllPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "pets/pets";
    }

    @GetMapping("/add")
    public String showAddPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pets/add-pet";
    }

    @PostMapping("/add")
    public String addPet(@ModelAttribute Pet pet, @RequestParam Long userId) {
        User user = new User();
        pet.setOwner(user);
        petService.savePet(pet);
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


