package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.dbc.pessoaapi.service.DadosPessoaisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dados-pessoais")
@Validated
@RequiredArgsConstructor
@Slf4j
public class DadosPessoaisController {

        private final DadosPessoaisClient dadosPessoaisClient;
        private final DadosPessoaisService dadosPessoaisService;

        @PostMapping

        public DadosPessoaisDTO create(@Valid @RequestBody DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
            DadosPessoaisDTO pessoaCriada = dadosPessoaisService.create(dadosPessoaisDTO);
            return pessoaCriada;
        }

        @GetMapping
        public List<DadosPessoaisDTO> list() {
            return dadosPessoaisService.list();
        }

    @GetMapping("/{cpf}")
    public DadosPessoaisDTO getPorCpf(@PathVariable("cpf") String cpf) throws Exception {
        return dadosPessoaisService.getByCpf(cpf);
    }


}
