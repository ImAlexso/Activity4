package org.example.activity2unit4.controller;

import org.example.activity2unit4.dto.UserDTO;
import org.example.activity2unit4.entity.Game;
import org.example.activity2unit4.entity.User;
import org.example.activity2unit4.service.GameService;
import org.example.activity2unit4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final GameService gameService;
    public UserController(UserService userService,  GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        return userService.getUserDtoById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
    @GetMapping("/{id}/games")
    public List<Game> getGamesByUser(@PathVariable Integer id) {
        return gameService.findAllByUserId(id);
    }

    @PostMapping("/{id}/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGameForUser(@PathVariable Integer id, @RequestBody Game game) {
        return gameService.createGameForUser(id, game);
    }
    @PutMapping("/{userId}/games/{gameId}")
    public Game assignGameToUser(@PathVariable Integer userId, @PathVariable Integer gameId) {
        return gameService.assignGameToUser(userId, gameId);
    }
}
