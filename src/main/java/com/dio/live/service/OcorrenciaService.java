package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Ocorrencia;
import com.dio.live.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {

    private OcorrenciaRepository repository;

    @Autowired
    public OcorrenciaService(OcorrenciaRepository repository) {
        this.repository = repository;
    }

    public Ocorrencia save(Ocorrencia obj) throws DuplicateRegisterException {
        return repository.save(obj);
    }

    public List<Ocorrencia> findAll() {
       return repository.findAll();
    }

    public Ocorrencia findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Ocorrencia update(Long id, Ocorrencia obj) throws NotFoundRegisterException {
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
