package org.example.activity2unit4.service;

import org.example.activity2unit4.entity.Game;
import org.example.activity2unit4.entity.User;
import org.example.activity2unit4.repository.GameRepository;
import org.example.activity2unit4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GameService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public GameService(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Integer id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game no existe"));
    }

    public List<Game> findAllByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe");
        return gameRepository.findByUserIdOrderByIdAsc(userId);
    }

    public Game createGameForUser(Integer userId, Game game) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe"));

        game.setId(null);
        game.setUser(user);
        return gameRepository.save(game);
    }

    public Game update(Integer id, Game game) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game no existe"));

        existingGame.setTitulo(game.getTitulo());
        existingGame.setPlataforma(game.getPlataforma());
        existingGame.setGenero(game.getGenero());
        existingGame.setAnio(game.getAnio());
        existingGame.setDesarrollador(game.getDesarrollador());
        existingGame.setEditor(game.getEditor());
        existingGame.setEdicionEspecial(game.getEdicionEspecial());
        existingGame.setEstado(game.getEstado());
        existingGame.setValorEstimado(game.getValorEstimado());
        existingGame.setNotas(game.getNotas());

        return gameRepository.save(existingGame);
    }

    public void deleteGame(Integer id) {
        if (!gameRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game no existe");
        }
        gameRepository.deleteById(id);
    }
    public Game save(Game game) {
        return gameRepository.save(game);
    }
    public Game assignGameToUser(Integer userId, Integer gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User no existe"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game no existe"));

        game.setUser(user);
        return gameRepository.save(game);
    }

}
