package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.TipoData;
import com.dio.live.repository.TipoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDataService {

    private TipoDataRepository repository;

    @Autowired
    public TipoDataService(TipoDataRepository repository) {
        this.repository = repository;
    }

    public TipoData save(TipoData obj) throws DuplicateRegisterException {
        Optional<TipoData> tipoExistente = findByDescricao(obj.getDescricao());

        if(tipoExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<TipoData> findAll() {
       return repository.findAll();
    }

    public TipoData findById(Long id) throws NotFoundRegisterException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public Optional<TipoData> findByDescricao(String descricao) {
         return repository
                .findByDescricaoContainingIgnoreCase(descricao);
    }

    public TipoData update(Long id, TipoData obj) throws NotFoundRegisterException {
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
