package br.com.meli.anuncios.repositories;

import br.com.meli.anuncios.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    //void deleteById(Long id);

}
