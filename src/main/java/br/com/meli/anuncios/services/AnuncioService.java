package br.com.meli.anuncios.services;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.dto.AnuncioDtoOut;

import java.util.List;

public interface AnuncioService {
    AnuncioDtoOut create(AnuncioDto anuncioDto);

    AnuncioDtoOut findById(Long id);

    List<AnuncioDtoOut> findAll();

    AnuncioDtoOut update(AnuncioDto anuncioDto);

    void deleteById(Long id);
}
