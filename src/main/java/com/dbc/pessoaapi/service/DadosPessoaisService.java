package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadosPessoaisService {
    private final ObjectMapper objectMapper;
    private final DadosPessoaisClient dadosPessoaisClient;


    public List<DadosPessoaisDTO> list() {
        return dadosPessoaisClient.listar();
    }

    public DadosPessoaisDTO create(DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
       return dadosPessoaisClient.post(dadosPessoaisDTO);
    }

    public DadosPessoaisDTO getByCpf(String cpf) throws Exception {
        return dadosPessoaisClient.getPorCpf(cpf);

    }

}
