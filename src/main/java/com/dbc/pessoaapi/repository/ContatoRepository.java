package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.ContatoEntity;
import com.dbc.pessoaapi.entity.TipoContato;
import com.dbc.pessoaapi.exception.RegraDeNegocioException;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {
    private static List<ContatoEntity> listaContatoFinalEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    private AtomicInteger COUNTER2 = new AtomicInteger();

    public ContatoRepository() {

        listaContatoFinalEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.COMERCIAL, "71987130989", "trabalho"));
        listaContatoFinalEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.RESIDENCIAL, "7182759675", "casa"));
        listaContatoFinalEntities.add(new ContatoEntity(COUNTER.incrementAndGet(), COUNTER2.incrementAndGet(), TipoContato.COMERCIAL, "71986759796", "trabalho dois"));
    }

    public ContatoEntity create(Integer IdPessoa, ContatoEntity contatoEntity) throws Exception{
        contatoEntity.setIdPessoa(IdPessoa);
        contatoEntity.setIdContato(COUNTER.incrementAndGet());
        listaContatoFinalEntities.add(contatoEntity);
        return contatoEntity;
    }


    public List<ContatoEntity> list() {
        return listaContatoFinalEntities;
    }

    public List<ContatoEntity> listByIdPessoa(Integer id) {
        return listaContatoFinalEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public ContatoEntity update(Integer idContato, ContatoEntity contatoEntityAtualizar) throws Exception {
        ContatoEntity contatobackup = listaContatoFinalEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(idContato))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não  foi encontrado"));
        contatobackup.setIdPessoa(contatoEntityAtualizar.getIdPessoa());
        contatobackup.setTipoContato(contatoEntityAtualizar.getTipoContato());
        contatobackup.setNumero(contatoEntityAtualizar.getNumero());
        contatobackup.setDescricao(contatoEntityAtualizar.getDescricao());
        return contatobackup;
    }

    public void delete(Integer id) throws Exception {
        ContatoEntity contatobackup = listaContatoFinalEntities.stream()
                .filter(contatoEntity -> contatoEntity.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        listaContatoFinalEntities.remove(contatobackup);
    }
}