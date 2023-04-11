package br.com.meli.anuncios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnuncioDto {

    private Long id;

    private String titulo;

    private List<String> ips;

}
