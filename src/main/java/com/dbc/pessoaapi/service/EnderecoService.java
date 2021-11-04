package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.EnderecoDTO;
import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> list() {
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco,EnderecoDTO.class))
                .collect(Collectors.toList());
    }


    public List<EnderecoDTO> listByIdEndereco(Integer idEndereco) {
        return enderecoRepository.listByIdEndereco(idEndereco).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity,EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public List<EnderecoDTO> listByIdPessoa(Integer idPessoa) {
        return enderecoRepository.listByIdPessoa(idPessoa).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity,EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        pessoaRepository.getById(idPessoa);
        enderecoCreateDTO.setIdPessoa(idPessoa);
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoCriado = enderecoRepository.create(enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado,EnderecoDTO.class);
        enderecoEntity1.setIdPessoa(idPessoa);
        return dto;
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        pessoaRepository.getById(enderecoCreateDTO.getIdPessoa());
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoCriado = enderecoRepository.update(idEndereco, enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado,EnderecoDTO.class);
        return dto;
    }

    public void delete(Integer idEndereco) throws Exception {
        enderecoRepository.delete(idEndereco);
    }
}
