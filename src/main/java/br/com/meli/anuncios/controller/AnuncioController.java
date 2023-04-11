package br.com.meli.anuncios.controller;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {


    @Autowired
    private AnuncioService anuncioService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnuncioDto anuncioDto){
        return new ResponseEntity<>(anuncioService.create(anuncioDto), HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(anuncioService.findById(id), HttpStatus.OK);
    }

}
