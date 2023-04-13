package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioDtoIn;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.entities.Anuncio;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnuncioServiceImpl implements AnuncioService {
    @Autowired
    private AnuncioRepository repository;

    @Override
    public AnuncioDtoOut create(AnuncioDtoIn anuncioDtoIn) {
        Anuncio entity = new Anuncio();
        entity.setName(anuncioDtoIn.getName());

        Anuncio savedEntity = repository.save(entity);

        AnuncioDtoOut returnDto = new AnuncioDtoOut();
        returnDto.setId(savedEntity.getId());
        returnDto.setName(savedEntity.getName());

        return returnDto;
    }

    @Override
    public AnuncioDtoOut findById(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        AnuncioDtoOut returnDto = new AnuncioDtoOut(entity.getId(), entity.getName());

        return returnDto;
    }

    @Override
    public List<AnuncioDtoOut> findAll() {
        List<Anuncio> entities = repository.findAll();

        return entities.stream().map(e -> new AnuncioDtoOut(e.getId(), e.getName())).collect(Collectors.toList());
    }

    @Override
    public AnuncioDtoOut update(AnuncioDtoOut anuncioDtoOut) {
        Anuncio entity = repository.findById(anuncioDtoOut.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        entity.setName(anuncioDtoOut.getName());

        entity = repository.save(entity);

        return new AnuncioDtoOut(entity.getId(), entity.getName());
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
        }
}
