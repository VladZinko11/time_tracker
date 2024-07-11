package com.zinko.time_tracker.web.rest;

import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserDtoCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping("/create")
    public UserDto create(UserDtoCreate userDtoCreate) {
        return userService.create(userDtoCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @GetMapping("/project/{id}")
    public List<UserDto> getByProject(@PathVariable Long id) {
        return userService.getByProjectId(id);
    }
}
