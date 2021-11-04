package com.dbc.pessoaapi.service;



import com.dbc.pessoaapi.dto.ContatoCreateDTO;
import com.dbc.pessoaapi.dto.ContatoDTO;
import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;


    public List<ContatoDTO> list(){
        return contatoRepository.list().stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());

//        pessoaRepository.list().stream()
//                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
//                .collect(Collectors.toList());
    }

    public List<ContatoDTO> listPorIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        pessoaRepository.getById(idPessoa);
        return contatoRepository.listByIdPessoa(idPessoa).stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO create(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws Exception {
        ContatoEntity contatoEntity1 = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
        pessoaRepository.getById(idPessoa);
        ContatoEntity contatoCriado = contatoRepository.create(idPessoa, contatoEntity1);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado,ContatoDTO.class);
        return contatoDTO;

//        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO,PessoaEntity.class);
//        PessoaEntity pessoaCriada  = pessoaRepository.create(pessoaEntity);
//        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaCriada,PessoaDTO.class);
//        return pessoaDTO;
    }

    public ContatoDTO update(Integer idContato, ContatoCreateDTO contatoCreateDTO) throws Exception {
        ContatoEntity contatoEntity = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
        ContatoEntity contatoEntity1 = contatoRepository.update(idContato, contatoEntity);
        ContatoDTO dto = objectMapper.convertValue(contatoEntity1,ContatoDTO.class);
        return dto;
    }

    public void delete(Integer idContato) throws Exception {
        contatoRepository.delete(idContato);
    }
}
