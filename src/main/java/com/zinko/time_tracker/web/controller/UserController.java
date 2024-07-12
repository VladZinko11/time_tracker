package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ErrorService errorService;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto, Errors errors) {
        errorService.checkErrors(errors);
        return userService.create(userCreateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody @Valid UserDto userDto, Errors errors) {
        errorService.checkErrors(errors);
        return userService.update(userDto);
    }

}
