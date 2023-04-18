package br.com.meli.anuncios.dto;

import br.com.meli.anuncios.entities.Anuncio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnuncioDto {

    private Long id;
    private String name;
    private String descricao;
    private double precoNormal;
    private double precoPromo;


    public Anuncio toModel(){
        return Anuncio.builder()
                .id(this.id)
                .name(this.name)
                .descricao(this.descricao)
                .precoNormal(this.precoNormal)
                .precoPromo(this.precoPromo)
                .build();
    }
}
