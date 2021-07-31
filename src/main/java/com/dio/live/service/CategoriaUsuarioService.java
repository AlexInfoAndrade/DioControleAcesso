package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.CategoriaUsuario;
import com.dio.live.repository.CategoriaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaUsuarioService {

    private CategoriaUsuarioRepository repository;

    @Autowired
    public CategoriaUsuarioService(CategoriaUsuarioRepository repository) {
        this.repository = repository;
    }

    public CategoriaUsuario save(CategoriaUsuario obj) throws DuplicateRegisterException {
        Optional<CategoriaUsuario> categoriaExistente = repository
                .findByDescricaoContainingIgnoreCase(obj.getDescricao());

        if(categoriaExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<CategoriaUsuario> findAll() {
       return repository.findAll();
    }

    public CategoriaUsuario findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public CategoriaUsuario update(Long id, CategoriaUsuario obj) throws NotFoundRegisterException {
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
