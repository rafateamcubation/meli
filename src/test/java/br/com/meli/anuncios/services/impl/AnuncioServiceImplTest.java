package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioDto;
import br.com.meli.anuncios.dto.AnuncioDtoOut;
import br.com.meli.anuncios.entities.Anuncio;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
class AnuncioServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Panela";
    public static final String DESCRICAO = "Metal";
    public static final double PRECO_NORMAL = 20.50;
    public static final double PRECO_PROMO = 19.50;
    @InjectMocks
    private AnuncioServiceImpl service;
    @Mock
    private AnuncioRepository repository;

    private Anuncio anuncio;

    private AnuncioDto anuncioDto;

    private Optional<Anuncio> optionalAnuncio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAnuncio();;
    }


    @Test
    @DisplayName("Busca anuncio por ID")
    void searchAnuncioByIdTest() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalAnuncio);

        AnuncioDtoOut response = service.findById(anuncio.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(AnuncioDtoOut.class, response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(PRECO_NORMAL, response.getPrecoNormal());
        Assertions.assertEquals(PRECO_PROMO, response.getPrecoPromo());
    }

    @Test
    @DisplayName("Busca todos anuncios")
    void searchAllAnunciosTest() {
    }

    @Test
    @DisplayName("Cria anuncio")
    void createAnuncioTest() {
    }

    @Test
    @DisplayName("Atualiza anuncio")
    void updateAnuciosTest() {
    }

    @Test
    @DisplayName("Exclui anuncio por ID")
    void deleteByIdTest() {
    }


    private void startAnuncio (){
        anuncio = Anuncio.builder().id(ID).name(NAME).descricao(DESCRICAO).precoNormal(PRECO_NORMAL).precoPromo(PRECO_PROMO)
                .build();
        anuncioDto = AnuncioDto.builder().id(ID).name(NAME).descricao(DESCRICAO).precoNormal(PRECO_NORMAL).precoPromo(PRECO_PROMO)
                .build();
        optionalAnuncio = Optional.of(Anuncio.builder().id(ID).name(NAME).descricao(DESCRICAO).precoNormal(PRECO_NORMAL).precoPromo(PRECO_PROMO)
                .build());
    }
}