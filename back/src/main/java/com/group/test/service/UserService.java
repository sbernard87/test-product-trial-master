package com.group.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.test.model.UserAccountDTO;
import com.group.test.repository.UserRepository;

@Service
public class UserService {

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

//    public List<UserDTO> getAllUsers(final Integer page, final Integer size) {
//        List<UserDTO> usersDto = new ArrayList<>();
//        for (User user : userRepository.findAll(PageRequest.of(page, size))) {
//            usersDto.add(objectMapper.convertValue(user, UserDTO.class));
//        }
//
//        return usersDto;
//    }
//
//    public UserDTO getUserById(final Integer id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            UserDTO product = objectMapper.convertValue(optionalUser.get(), UserDTO.class);
//            return product;
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    }

    public void createUser(final UserAccountDTO userAccount) {
//        Optional<User> optionalUser = UserRepository cannot be resolved to a type
        userRepository.findByEmail(userAccount.getEmail());
//        if (optionalUser.isPresent()) {
//            throw new RuntimeException("User already exists");
//        } else {
//            User user = objectMapper.convertValue(userAccount, User.class);
//            userRepository.save(user);
//        }
    }

//    public void updateUser(final Integer id, final UserDTO userDTO) {
//        // Logic to update user details
//    }
//
//    public void deleteUser(final Integer id) {
//        // Logic to delete a user
//    }
}