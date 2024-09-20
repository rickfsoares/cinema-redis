package br.edu.ifpb.tsi.pdist.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.edu.ifpb.tsi.pdist.cinema.DTO.Movie;
import br.edu.ifpb.tsi.pdist.cinema.service.CinemaService;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public List<String> getInformacoes(@RequestParam("key") String key) {
        return cinemaService.getInformacoes(key);
    }

    @PostMapping
    public void criarInformacoes(@RequestBody Movie movie) {
        cinemaService.criar(movie.getKey(), movie.getName());
    }

    @DeleteMapping
    //public void deletarInformacoes(@RequestParam("key") String key, @RequestParam("movie") String movie) {
    public ResponseEntity deletarInformacoes(@RequestBody Movie movie) {
        long quantityDeleted = cinemaService.deletarElemento(movie.getKey(), movie.getName());
        if (quantityDeleted > 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    public ResponseEntity atualizarInformacoes(@RequestBody Movie movie) {
        boolean res = cinemaService.atualizarElemento(movie.getKey(), movie.getName(), movie.getNewName());
        if (res) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
