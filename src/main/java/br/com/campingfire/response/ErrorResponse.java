package br.com.campingfire.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String field;
    private String errorMessage;

}
