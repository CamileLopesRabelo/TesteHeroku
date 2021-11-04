package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.EnderecoEntity;
import com.dbc.pessoaapi.entity.TipoEndereco;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<EnderecoEntity> listaEnderecoFinalEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    public EnderecoRepository() {

        listaEnderecoFinalEntities.add(new EnderecoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoEndereco.RESIDENCIAL,"Rua Mandacaru",50,null,"54768-90","Porto Alegre","RS","BR"));
    }

    public List<EnderecoEntity> list() {
        return listaEnderecoFinalEntities;
    }

    public List<EnderecoEntity> listByIdEndereco(Integer idEndereco) {
        return listaEnderecoFinalEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .collect(Collectors.toList());
    }

    public List<EnderecoEntity> listByIdPessoa(Integer idPessoa) {
        return listaEnderecoFinalEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public EnderecoEntity create(EnderecoEntity enderecoEntity) {
        enderecoEntity.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecoFinalEntities.add(enderecoEntity);
        return enderecoEntity;
    }

    public EnderecoEntity update(Integer idEndereco, EnderecoEntity enderecoEntityAtual) throws Exception {
        EnderecoEntity enderecoEntityBackup = listaEnderecoFinalEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não  foi encontrado"));
        enderecoEntityBackup.setIdPessoa(enderecoEntityAtual.getIdPessoa());
        enderecoEntityBackup.setTipo(enderecoEntityAtual.getTipo());
        enderecoEntityBackup.setLogradouro(enderecoEntityAtual.getLogradouro());
        enderecoEntityBackup.setNumero(enderecoEntityAtual.getNumero());
        enderecoEntityBackup.setComplemento(enderecoEntityAtual.getComplemento());
        enderecoEntityBackup.setCep(enderecoEntityAtual.getCep());
        enderecoEntityBackup.setCidade(enderecoEntityAtual.getCidade());
        enderecoEntityBackup.setEstado(enderecoEntityAtual.getEstado());
        enderecoEntityBackup.setPais(enderecoEntityAtual.getPais());
        return enderecoEntityBackup;
    }

    public void delete(Integer idEndereco) throws Exception {
        EnderecoEntity enderecoEntityRec = listaEnderecoFinalEntities.stream()
                .filter(enderecoEntity -> enderecoEntity.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        listaEnderecoFinalEntities.remove(enderecoEntityRec);
    }

}
