package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.entities.Anuncio;
import br.com.meli.anuncios.exceptions.BlankNameException;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.exceptions.ValuesNotValidException;
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
    public AnuncioDtoOut findById(Long id) {
        Anuncio anuncio = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        return AnuncioDtoOut.fromModel(anuncio);
    }

    @Override
    public List<AnuncioDtoOut> findAll() {
        List<Anuncio> entities = repository.findAll();

        if (entities.isEmpty()){
            throw new ResourceNotFoundException("Não existem anúncios!");
        } else {
            return entities.stream().map(e -> AnuncioDtoOut.fromModel(e))
                    .collect(Collectors
                            .toList());
        }
    }

    @Override
    public AnuncioDtoOut create(AnuncioDto anuncioDto) {

        checks(anuncioDto);

        anuncioDto.setId(null);

        Anuncio anuncio = anuncioDto.toModel();
        repository.save(anuncio);

        return AnuncioDtoOut.fromModel(anuncio);
    }

    @Override
    public AnuncioDtoOut update(AnuncioDto anuncioDto) {
        checks(anuncioDto);

        Anuncio anuncio =  anuncioDto.toModel();

                repository.findById(anuncio.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado"));


        repository.save(anuncio);

        return AnuncioDtoOut.fromModel(anuncio);
    }

    @Override
    public void deleteById(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anúcio não encontrado!"));

        repository.save(entity);
    }

    private void checks(AnuncioDto anuncioDto){
        checkIsBlank(anuncioDto.getName());
        checkIsBlank(anuncioDto.getDescricao());
        checkIsValueNotValid(anuncioDto.getPrecoNormal(), anuncioDto.getPrecoPromo());
    }

    private void checkIsBlank(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new BlankNameException("Valores de entrada não podem ser nullos ou vázios!");
        }
    }

    private void checkIsValueNotValid(double precoNormal, double precoPromo){
        if (precoPromo >= precoNormal){
            throw new ValuesNotValidException("Valores de entradas inválidos!");
        }
    }

}