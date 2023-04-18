package br.com.meli.anuncios.dto;

import br.com.meli.anuncios.entities.Anuncio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnuncioDtoOut {

    private Long id;
    private String name;
    private String descricao;
    private double precoNormal;
    private double precoPromo;

    public static AnuncioDtoOut fromModel(Anuncio anuncio){
        if (anuncio == null){
            return null;
        }

        return AnuncioDtoOut.builder()
                .id(anuncio.getId())
                .name(anuncio.getName())
                .descricao(anuncio.getDescricao())
                .precoNormal(anuncio.getPrecoNormal())
                .precoPromo(anuncio.getPrecoPromo())
                .build();
    }

}
