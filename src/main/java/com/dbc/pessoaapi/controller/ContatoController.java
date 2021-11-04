package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.ContatoCreateDTO;
import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import com.dbc.pessoaapi.service.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/contato")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ContatoController {
    private final ContatoService contatoService;

    @GetMapping
    @ApiOperation(value = "Lista os contatos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contatos listados com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ContatoDTO> list() {
        return contatoService.list();
    }

    @GetMapping("/{idPessoa}")
    @ApiOperation(value = "Lista os contatos pelo id da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato criado com sucesso"),
            @ApiResponse(code = 400, message = "Contato da pessoa não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ContatoDTO> listByName(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        return contatoService.listPorIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    @ApiOperation(value = "Cria os contatos com o id da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato criado com sucesso"),
            @ApiResponse(code = 400, message = "Contato com  dados inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ContatoDTO create(@PathVariable ("idPessoa") Integer idPessoa,
                             @RequestBody @Valid ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.info("começcando a criar contato");
        ContatoDTO contatocriar = contatoService.create(idPessoa, contatoCreateDTO);
        log.info("Contato criado com sucesso");
        return contatocriar;
    }

    @PutMapping("/{idContato}")
    @ApiOperation(value = "Altera os contatos pelo id do contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato alterado com sucesso"),
            @ApiResponse(code = 400, message = "Contato com dados inconsistentes ou não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ContatoDTO update(@PathVariable("idContato") Integer idContato,
                                @RequestBody @Valid ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.info("iniciando alteração do contato");
        ContatoDTO contatoalter = contatoService.update(idContato, contatoCreateDTO);
        log.info("contato alterado com sucesso");
        return contatoalter;
    }

    @DeleteMapping("/{idContato}")
    @ApiOperation(value = "Exclui os contatos pelo o id do contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato excluido com sucesso"),
            @ApiResponse(code = 400, message = "Contato não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public void delete(@PathVariable("idContato") Integer idContato) throws Exception {
        log.info("iniciando delete contato");
        contatoService.delete(idContato);
        log.info("contato delatado com sucesso!s");
    }
}
