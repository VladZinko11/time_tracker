package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.UserRepository;
import com.zinko.time_tracker.service.UserMapper;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserDtoCreate;
import com.zinko.time_tracker.service.exception.InvalidIdException;
import com.zinko.time_tracker.service.exception.InvalidLoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserDtoCreate userDtoCreate) {
        if (userRepository.existsByEmail(userDtoCreate.getEmail())) {
            throw new InvalidLoginException("login " + userDtoCreate.getEmail() + " is already exist");
        }
        User user = userMapper.toUser(userDtoCreate);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto get(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new InvalidIdException("Not found user with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserDto> getByProjectId(Long id) {
        List<User> users = userRepository.findByProjectId(id);
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
            throw new InvalidIdException();
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
                throw new InvalidLoginException("User with email: " + userDto.getEmail() + " is already exist");
            }
        }
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new InvalidIdException("Not found user with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
