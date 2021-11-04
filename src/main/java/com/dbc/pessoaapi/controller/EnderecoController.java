package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import com.dbc.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    @ApiOperation(value = "Lista os endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço listado com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public List<EnderecoDTO> list() {
        return enderecoService.list();
    }

    @GetMapping("/{idEndereco}")
    @ApiOperation(value = "Lista os endereços pelo id do endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço listado com sucesso"),
            @ApiResponse(code = 400, message = "Endereço não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public List<EnderecoDTO> listByIdEndereco(@PathVariable("idEndereco") Integer idEndereco) {
                return enderecoService.listByIdEndereco(idEndereco);
    }

    @GetMapping("/{idPessoa}/pessoa")
    @ApiOperation(value = "Lista os endereços pelo id da pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço listado com sucesso"),
            @ApiResponse(code = 400, message = "Endereço da pessoa não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public List<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) {
        return enderecoService.listByIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    @ApiOperation(value = "Cria um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço criado com sucesso"),
            @ApiResponse(code = 400, message = "Endereço com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public EnderecoDTO create(@PathVariable ("idPessoa") Integer idPessoa,
                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("criando endereço");
        EnderecoDTO enderecoEntityCriar = enderecoService.create(idPessoa, enderecoCreateDTO);
        log.info("Endereço criado com sucesso!");
        return enderecoEntityCriar;
    }

    @PutMapping("/{idEndereco}")
    @ApiOperation(value = "Altera o endereço pelo id do endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço alterado com sucesso"),
            @ApiResponse(code = 400, message = "Endereço com dados inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public EnderecoDTO update(@PathVariable("idEndereco") Integer idEndereco,
                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.info("alterando endereço");
        EnderecoDTO enderecoEntityAlter = enderecoService.update(idEndereco, enderecoCreateDTO);
        log.info("Endereço alterado com sucesso");
        return enderecoEntityAlter;
    }

    @DeleteMapping("/{idEndereco}")
    @ApiOperation(value = "Exclui o endereço pelo id do endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço excluido com sucesso"),
            @ApiResponse(code = 400, message = "Endereço não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    public void delete(@PathVariable("idEndereco") Integer idEndereco) throws Exception {
        log.info("começando a deletar endereço");
        enderecoService.delete(idEndereco);
        log.info("endereço deletado");
    }

}
