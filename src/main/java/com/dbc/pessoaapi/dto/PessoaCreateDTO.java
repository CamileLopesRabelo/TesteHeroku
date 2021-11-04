package com.dbc.pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome da pessoa")
    private String nome;
    @NotNull
    @Past
    @ApiModelProperty(value = "Data de nascimento")
    private LocalDate dataNascimento;
    @NotNull
    @NotEmpty
    @Size(max = 11, min = 11,message = "Deve ter 11 caracteres")
    @ApiModelProperty(value = "CPF")
    private String cpf;
    @NotNull
    @Email
    @ApiModelProperty(value = "Email")
    private String email;
}
