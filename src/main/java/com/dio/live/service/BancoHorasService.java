package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.BancoHoras;
import com.dio.live.repository.BancoHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasService {

    private BancoHorasRepository repository;

    @Autowired
    public BancoHorasService(BancoHorasRepository repository) {
        this.repository = repository;
    }

    public BancoHoras save(BancoHoras obj) throws DuplicateRegisterException {
        Optional<BancoHoras> bancoExistente = repository
                .findById(
                        new BancoHoras.BancoHorasId(
                                obj.getBancoHorasId().getIdBancoHoras(),
                                obj.getBancoHorasId().getIdMovimentacao(),
                                obj.getBancoHorasId().getIdUsuario()
                        )
                );

        if(bancoExistente.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

        return repository.save(obj);
    }

    public List<BancoHoras> findAll() {
       return repository.findAll();
    }

    public BancoHoras findById(Long idBanco, Long idMovimentacao, Long idUsuario)
            throws NotFoundRegisterException{

        return repository.findById(
                new BancoHoras.BancoHorasId(
                    idBanco, idMovimentacao, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));
    }

    public BancoHoras update(Long idBanco, Long idMovimentacao, Long idUsuario, BancoHoras obj)
            throws NotFoundRegisterException {

        repository.findById(
                new BancoHoras.BancoHorasId(
                    idBanco, idMovimentacao, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        obj.setBancoHorasId(
                new BancoHoras.BancoHorasId(
                    idBanco, idMovimentacao, idUsuario
                )
        );
        return repository.save(obj);
    }

    public void delete(Long idBanco, Long idMovimentacao, Long idUsuario)
            throws NotFoundRegisterException {

        repository.findById(
                new BancoHoras.BancoHorasId(
                    idBanco, idMovimentacao, idUsuario
                )
        ).orElseThrow(() -> new NotFoundRegisterException("Not found!"));

        repository.deleteById(new BancoHoras.BancoHorasId(
                idBanco, idMovimentacao, idUsuario
        ));
    }
}
