package ru.stomprf.discount51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stomprf.discount51.domain.User;
import ru.stomprf.discount51.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());
    private final UserService userService;

    @Autowired
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        model.addAttribute("pageTitle", "Новый пользователь");
        return "user-add";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttr) {
        logger.log(Level.INFO, "Save user request: " + user);
        try {
            User savedUser = userService.save(user);
            logger.log(Level.INFO, "User saved: " + user);
            return String.format("redirect:/verification/page/%d", savedUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttr.addFlashAttribute("message", "Пользователь НЕ добавлен. Возможно, такой номер уже существует.");
        }
        return "redirect:/users";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user) {
        logger.log(Level.INFO, "Update user request: " + user);
        userService.update(user);

        return String.format("redirect:/users/page/%d", user.getId());
    }

    @GetMapping("/users/{id}")
    public String updateUserForm(@PathVariable Integer id, Model model) {
        logger.log(Level.INFO, String.format("User update request(id: %d)", id));
        try {
            User user = userService.findById(id);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Изменить (ID: " + id + ")");

            return "user-update-form";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users";
        }
    }

    @GetMapping("/users/page/{id}")
    public String userPage(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        return "user-page";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        logger.log(Level.INFO, "Delete user with id: " + id);
        return "redirect:/users";
    }
}
