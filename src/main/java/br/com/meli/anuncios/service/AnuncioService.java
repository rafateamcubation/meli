package br.com.meli.anuncios.service;

import br.com.meli.anuncios.dto.AnuncioDto;

public interface AnuncioService {


    public AnuncioDto create(AnuncioDto anuncioDto);

    public AnuncioDto findById(Long id);

}
