package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Calendario;
import com.dio.live.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {

    private CalendarioRepository repository;

    @Autowired
    public CalendarioService(CalendarioRepository repository) {
        this.repository = repository;
    }

    public Calendario save(Calendario obj) throws DuplicateRegisterException {
        return repository.save(obj);
    }

    public List<Calendario> findAll() {
       return repository.findAll();
    }

    public Calendario findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Calendario update(Long id, Calendario obj) throws NotFoundRegisterException {
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
