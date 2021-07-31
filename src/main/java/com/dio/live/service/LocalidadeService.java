package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Localidade;
import com.dio.live.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {

    private LocalidadeRepository repository;

    @Autowired
    public LocalidadeService(LocalidadeRepository repository) {
        this.repository = repository;
    }

    public Localidade save(Localidade obj) throws DuplicateRegisterException {
        Optional<Localidade> localidadeExistente = repository
                .findByDescricaoContainingIgnoreCase(obj.getDescricao());

        if(localidadeExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<Localidade> findAll() {
       return repository.findAll();
    }

    public Localidade findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Localidade update(Long id, Localidade obj) throws NotFoundRegisterException {
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
