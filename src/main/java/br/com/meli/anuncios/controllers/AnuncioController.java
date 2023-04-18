package br.com.meli.anuncios.controllers;

import br.com.meli.anuncios.dto.AnuncioDto;
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

    @GetMapping("{id}")
    public ResponseEntity<AnuncioDtoOut> findById(@PathVariable Long id){
        return new ResponseEntity<>(anuncioService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDtoOut>> findAll() {
        List<AnuncioDtoOut> dtos = anuncioService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<AnuncioDtoOut> create(@RequestBody AnuncioDto anuncioDto){
        return new ResponseEntity<>(anuncioService.create(anuncioDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AnuncioDtoOut> update(@RequestBody AnuncioDto anuncioDto) {
        AnuncioDtoOut returnDto = anuncioService.update(anuncioDto);
        return ResponseEntity.ok(returnDto);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<AnuncioDtoOut> deleteById(@PathVariable long id) {
        anuncioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
