package br.com.youtube.customer.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CustomerRequest {

    @NotBlank
    @ApiModelProperty(value = "Nome do Cliente, não pode ser vázio")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "CPF/CNPJ do Cliente, não pode ser vázio")
    private String document;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
