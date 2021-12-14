package com.frasson.prova10dec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceAlreadyExistsException extends RuntimeException {

  public ResourceAlreadyExistsException(String message) {
    super(message);
  }
}
