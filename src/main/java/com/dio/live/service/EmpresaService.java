package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Empresa;
import com.dio.live.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private EmpresaRepository repository;

    @Autowired
    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public Empresa save(Empresa obj) throws DuplicateRegisterException {
        Optional<Empresa> empresaExistente = repository
                .findByCnpjContainingIgnoreCase(obj.getCnpj());

        if(empresaExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<Empresa> findAll() {
       return repository.findAll();
    }

    public Empresa findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Empresa update(Long id, Empresa obj) throws NotFoundRegisterException {
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
