package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.errors.BadRequestException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.services.GameService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/games")
public class GameController {

    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Object> getGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> createGame(@Validated @RequestBody @Valid GameDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException("Invalid values!");
        }

        Optional<GameModel> game = gameService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}
