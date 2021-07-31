package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.JornadaTrabalho;
import com.dio.live.repository.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaService {

    private JornadaRepository repository;

    @Autowired
    public JornadaService(JornadaRepository repository) {
        this.repository = repository;
    }

    public JornadaTrabalho save(JornadaTrabalho obj) throws DuplicateRegisterException {
        Optional<JornadaTrabalho> jornadaExistente = repository
                .findByDescricaoContainingIgnoreCase(obj.getDescricao());

        if(jornadaExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<JornadaTrabalho> findAll() {
       return repository.findAll();
    }

    public JornadaTrabalho findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public JornadaTrabalho update(Long id, JornadaTrabalho obj) throws NotFoundRegisterException {
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
