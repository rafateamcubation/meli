package br.com.meli.anuncios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValuesNotValidException extends RuntimeException{
    public ValuesNotValidException(String message){
        super(message);
    }
}
