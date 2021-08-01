package com.dio.live.service;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import com.dio.live.model.Calendario;
import com.dio.live.model.TipoData;
import com.dio.live.repository.CalendarioRepository;
import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CalendarioService {

    private CalendarioRepository repository;
    private TipoDataService tipoDataService;
    private HolidayManager holidayManager;

    private static final String FERIADO = "Feriado";

    @Autowired
    public CalendarioService(CalendarioRepository repository, TipoDataService tipoDataService) {
        this.repository = repository;
        this.tipoDataService = tipoDataService;
        this.holidayManager = HolidayManager.getInstance(
                ManagerParameters.create(HolidayCalendar.BRAZIL)
        );
    }

    private TipoData getTipoDataFeriado() {
        Optional<TipoData> tipoData = tipoDataService.findByDescricao(FERIADO);

        if(tipoData.isPresent()) {
            return tipoData.get();
        }

        return tipoDataService.save(new TipoData(null, FERIADO));
    }

    public void carregarFeriados(int ano) {
        Set<Holiday> feriados = holidayManager.getHolidays(ano);
        TipoData tipoData = getTipoDataFeriado();

        for(Holiday f : feriados){
            try {
                save(new Calendario(
                        null,
                        tipoData,
                        f.getDescription(),
                        f.getDate().atStartOfDay()
                ));
            }catch(DuplicateRegisterException dre) { }
        }
    }

    public void carregarFeriados(int ano, String uf) {
        Set<Holiday> feriados = holidayManager.getHolidays(ano, uf);
        TipoData tipoData = getTipoDataFeriado();

        for(Holiday f : feriados){
            try {
                save(new Calendario(
                        null,
                        tipoData,
                        f.getDescription(),
                        f.getDate().atStartOfDay()
                ));
            }catch(DuplicateRegisterException dre) { }
        }
    }

    public Calendario save(Calendario obj) throws DuplicateRegisterException {
        Optional<Calendario> calendario = repository
                .findByTipoDataIdAndDataEspecial(obj.getTipoData().getId(), obj.getDataEspecial());

        if(calendario.isPresent()) {
            throw new DuplicateRegisterException("Duplicate registration");
        }

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
