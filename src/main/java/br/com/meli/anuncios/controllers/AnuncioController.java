package br.com.meli.anuncios.controllers;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<AnuncioDtoOut> create(@RequestBody AnuncioDtoIn anuncioDtoIn){
        return new ResponseEntity<>(anuncioService.create(anuncioDtoIn), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnuncioDtoOut> findById(@PathVariable Long id){
        return new ResponseEntity<>(anuncioService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDtoOut>> findAll() {
        List<AnuncioDtoOut> dtos = anuncioService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<AnuncioDtoOut> update(@PathVariable Long id, @RequestBody AnuncioDtoOut anuncioDtoOut) {
        anuncioDtoOut.setId(id);
        AnuncioDtoOut returnDto = anuncioService.update(anuncioDtoOut);
        return ResponseEntity.ok(returnDto);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<AnuncioDtoOut> deleteById(@PathVariable long id) {
        anuncioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
