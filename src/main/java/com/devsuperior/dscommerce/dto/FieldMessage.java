package com.devsuperior.dscommerce.dto;

//classe que representa um objeto que contem o atributo em que ocorreu o erro de validação e o tipo do erro de validação
public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }
    public String getMessage() {
        return message;
    }
}
