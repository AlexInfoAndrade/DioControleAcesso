package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.NivelAcesso;
import com.dio.live.repository.NivelAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoService {

    private NivelAcessoRepository repository;

    @Autowired
    public NivelAcessoService(NivelAcessoRepository repository) {
        this.repository = repository;
    }

    public NivelAcesso save(NivelAcesso obj) throws DuplicateRegisterException {
        Optional<NivelAcesso> nivelExistente = repository
                .findByDescricaoContainingIgnoreCase(obj.getDescricao());

        if(nivelExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<NivelAcesso> findAll() {
       return repository.findAll();
    }

    public NivelAcesso findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public NivelAcesso update(Long id, NivelAcesso obj) throws NotFoundRegisterException {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        obj.setId(id);
        return repository.save(obj);
    }

    public void delete(Long id) throws NotFoundRegisterException {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        repository.deleteById(id);
    }
}
