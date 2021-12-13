package com.example.plwk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    private UserRepository userRepository;

    public MyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/add")

    public String add(@RequestParam(name = "imie", required = false) String name,
                      @RequestParam(name  = "nazwisko", required = false) String surname,
                      @RequestParam(name = "wiek", required = false) Integer age) {
        if (name == null || name.isEmpty()) {
            return "redirect:/err";
        } else {
            User user = new User(name, surname, age);
            userRepository.add(user);
            return "redirect:/success";
        }
    }

    @RequestMapping("/err")
    public String err() {
        return "err.html";
    }

    @RequestMapping("/success")
    public String success() {

        return "success.html";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }

    @ResponseBody
    @RequestMapping("/users")
    public String list() {
        StringBuilder result = new StringBuilder();
        for (User user1 : userRepository.getUserList()) {
            result.append(user1.getName()).append(" ").append(user1.getSurname()).append(" ").append(user1.getAge()).append("<br>");
        }
        return result.toString();
    }
}
