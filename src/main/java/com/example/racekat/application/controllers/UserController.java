package com.example.racekat.application.controllers;


import com.example.racekat.application.services.UserService;
import com.example.racekat.domain.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/users")
public class UserController {

private final UserService userService;
public UserController(UserService userService) {
    this.userService = userService;
}

@GetMapping
    public String getUsers(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "user-list";
}

@GetMapping("/create")
    public String showCreateForm(Model model) {
    model.addAttribute("user", new User());
    return "user-form";
}

@PostMapping("/create")
    public String createUser(@ModelAttribute User user){
    userService.createUser(user);
    return "redirect:/users";
}

@GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
    model.addAttribute("user", userService.getUserByid(id));
    return "user-form";
}

@PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user){
    userService.updateUser(id, user);
    return "redirect:/users";
}

@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    return "redirect:/users";
}


}
