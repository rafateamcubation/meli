package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.entitites.Anuncio;
import br.com.meli.anuncios.exceptions.BlankNameException;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnuncioServiceImpl implements AnuncioService {
    @Autowired
    private AnuncioRepository repository;

    @Override
    public AnuncioDto create(AnuncioDto anuncioDto) {
        checkIsNameBlank(anuncioDto);

        Anuncio entity = new Anuncio();
        entity.setName(anuncioDto.getName());

        Anuncio savedEntity = repository.save(entity);

        AnuncioDto returnDto = new AnuncioDto();
        returnDto.setId(savedEntity.getId());
        returnDto.setName(savedEntity.getName());

        return returnDto;
    }

    @Override
    public AnuncioDto findById(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        AnuncioDto returnDto = new AnuncioDto(entity.getId(), entity.getName());

        return returnDto;
    }

    @Override
    public List<AnuncioDto> findAll() {
        List<Anuncio> entities = repository.findAll();

        return entities.stream().map(e -> new AnuncioDto(e.getId(), e.getName())).collect(Collectors.toList());
    }

    @Override
    public AnuncioDto update(AnuncioDto anuncioDto) {
        checkIsNameBlank(anuncioDto);

        Anuncio entity = repository.findById(anuncioDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        entity.setName(anuncioDto.getName());

        entity = repository.save(entity);

        return new AnuncioDto(entity.getId(), entity.getName());
    }

    @Override
    public void delete(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        repository.delete(entity);
    }

    private void checkIsNameBlank(AnuncioDto anuncioDto) {
        if (Objects.isNull(anuncioDto.getName()) || anuncioDto.getName().isEmpty()) {
            throw new BlankNameException();
        }
    }
}
