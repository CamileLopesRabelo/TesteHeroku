package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.entity.TipoContato;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContatoDTO extends ContatoCreateDTO {
    @ApiModelProperty(value = "id contato")
    private Integer idContato;

}
