package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/project/{id}/all")
    public List<UserDto> getAllByProjectId(@PathVariable Long id) {
        return userService.getByProjectId(id);
    }

    @DeleteMapping("/account")
    public void delete(@AuthenticationPrincipal UserDetails userDetails) {
        userService.delete(userDetails);
    }

    @PutMapping("/update")
    public UserDto update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid UserDto userDto, Errors errors) {
        errorService.checkErrors(errors);
        return userService.update(userDto, userDetails);
    }
}
