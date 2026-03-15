package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

//    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        //convert UserDto into User JPA Entity
//        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());

//        User user = UserMapper.maptoUser(userDto);

//        User user = modelMapper.map(userDto, User.class);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }
        User user = AutoUserMapper.MAPPER.maptoUser(userDto);
        User savedUser = userRepository.save(user);

        //Convert User KPA entity to UserDto
//        UserDto savedUserDto = new UserDto(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        UserDto savedUserDto = AutoUserMapper.MAPPER.maptoUserDto(savedUser);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.maptoUserDto(user);
    }


    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
//        return users.stream().map(user -> UserMapper::mapToUserDto).collect(Collectors.toList());

//        return users.stream().map((user -> modelMapper.map(user, UserDto.class))).collect(Collectors.toList());
        return users.stream().map(user -> (AutoUserMapper.MAPPER.maptoUserDto(user))).collect(Collectors.toList());


    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName((user.getLastName()));
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {


        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));

        userRepository.deleteById(id);
    }
}
