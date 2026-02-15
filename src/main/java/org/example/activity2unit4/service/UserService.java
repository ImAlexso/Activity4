package org.example.activity2unit4.service;

import org.example.activity2unit4.dto.UserDTO;
import org.example.activity2unit4.entity.User;
import org.example.activity2unit4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDTO getUserDtoById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe"));

        return new UserDTO(
                user.getId(),
                user.getNombre(),
                user.getEmail()
        );
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getNombre(),
                        user.getEmail()
                ))
                .toList();
    }

    public User create(User user) {
        return userRepository.save(user);
    }
    public User update(Integer id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe"));

        existing.setNombre(user.getNombre());
        existing.setEmail(user.getEmail());

        return userRepository.save(existing);
    }
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe");
        }
        userRepository.deleteById(id);
    }
}
