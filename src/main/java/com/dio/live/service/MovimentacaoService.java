package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Movimentacao;
import com.dio.live.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    private MovimentacaoRepository repository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    public Movimentacao save(Movimentacao obj) throws DuplicateRegisterException {
        Optional<Movimentacao> movimentoExistente = repository
                .findById(
                        new Movimentacao.MovimentacaoId(
                                obj.getMovimentacaoId().getIdMovimento(),
                                obj.getMovimentacaoId().getIdUsuario()
                        )
                );

        if(movimentoExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<Movimentacao> findAll() {
       return repository.findAll();
    }

    public Movimentacao findById(Long idMovimento, Long idUsuario)
            throws NotFoundRegisterException{
        
        return repository.findById(
                new Movimentacao.MovimentacaoId(
                    idMovimento, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Movimentacao update(Long idMovimento, Long idUsuario, Movimentacao obj)
            throws NotFoundRegisterException {
        
        repository.findById(
                new Movimentacao.MovimentacaoId(
                    idMovimento, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        obj.setMovimentacaoId(
                new Movimentacao.MovimentacaoId(
                    idMovimento, idUsuario
                )
        );
        return repository.save(obj);
    }

    public void delete(Long idMovimento, Long idUsuario)
            throws NotFoundRegisterException {
        
        repository.findById(
                new Movimentacao.MovimentacaoId(
                    idMovimento, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        repository.deleteById(new Movimentacao.MovimentacaoId(
                idMovimento, idUsuario
        ));
    }
}
