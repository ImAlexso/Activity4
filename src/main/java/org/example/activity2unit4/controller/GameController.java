package org.example.activity2unit4.controller;

import org.example.activity2unit4.entity.Game;
import org.example.activity2unit4.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable Integer id) {
        return gameService.findById(id);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Integer id, @RequestBody Game game) {
        return gameService.update(id, game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
    }
}
