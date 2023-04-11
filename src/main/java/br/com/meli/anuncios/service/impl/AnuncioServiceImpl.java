package br.com.meli.anuncios.service.impl;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.service.AnuncioService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AnuncioServiceImpl implements AnuncioService {


    @Override
    public AnuncioDto create(AnuncioDto anuncioDto) {
        anuncioDto.setId(1L);
        anuncioDto.setIps(Arrays.asList("123","321"));
        return anuncioDto;
    }

    @Override
    public AnuncioDto findById(Long id) {
        return new AnuncioDto(id, "teste", null);
    }
}
