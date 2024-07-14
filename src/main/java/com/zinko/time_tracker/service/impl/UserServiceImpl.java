package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.UserRepository;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.exception.BadCredentialsException;
import com.zinko.time_tracker.service.exception.NotAuthorityException;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.exception.ServerErrorException;
import com.zinko.time_tracker.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadCredentialsException("login " + user.getEmail()
                    + " is already exist");
        }
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(
                () -> new NotFoundException("Not found user with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getByProjectId(Long id) {
        List<User> users = userRepository.findByProjectId(id);
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto update(UserDto userDto, UserDetails userDetails) {
        emailValidate(userDto);
        User user = userMapper.toUser(userDto);
        if (userDto.getEmail().equals(userDetails.getUsername())) {
            user.setPassword(userDetails.getPassword());
        } else {
            throw new NotAuthorityException("You can not do update foreign accounts");
        }
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private void emailValidate(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getId().equals(userDto.getId())) {
                throw new BadCredentialsException("User with email: "
                        + userDto.getEmail() + " is already exist");
            }
        }
    }

    @Override
    public void delete(UserDetails userDetails) {
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        User user = optionalUser.orElseThrow(ServerErrorException::new);
        userRepository.delete(user);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(
                () -> new NotFoundException("Not found user by email" + email));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }
}
