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

@GetMapping({"/form", "/form{id}"})
    public String showCreateForm(@PathVariable(required = false) Long id, Model model) {
   User user = (id != null) ? userService.getUserByid(id) : new User();
    model.addAttribute("user",  user);
    return "user-form";
}

@PostMapping("/save")
    public String saveUser(@ModelAttribute User user){
    userService.createOrUpdateUser(user);
    return "redirect:/users";
}

@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    return "redirect:/users";
}

}
