package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.UserRepository;
import com.zinko.time_tracker.service.UserMapper;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserCreateDto;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.exception.BadCredentialsException;
import com.zinko.time_tracker.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new BadCredentialsException("login " + userCreateDto.getEmail() + " is already exist");
        }
        User user = userMapper.toUser(userCreateDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("Not found user with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto update(UserDto userDto) {
        emailValidate(userDto);
        String password;
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            password = optionalUser.get().getPassword();
        } else {
            throw new NotFoundException();
        }
        User user = userMapper.toUser(userDto);
        user.setPassword(password);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private void emailValidate(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getId().equals(userDto.getId())) {
                throw new BadCredentialsException("User with email: " + userDto.getEmail() + " is already exist");
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Not found user with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
