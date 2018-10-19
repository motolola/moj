package com.motolola.moj.controller;

import com.motolola.moj.model.User;
import com.motolola.moj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("test")
    public String myTest()
    {
        return "this is test!";
    }


    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll()
    {
    return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable int id)
    {
        return userService.getOne(id);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user)
    {
        return userService.create(user);
    }

    @PutMapping("user")
    public User update(@RequestBody User user)
    {
        return userService.update(user);
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id);
        return "User id "+id+" deleted";
    }

}
