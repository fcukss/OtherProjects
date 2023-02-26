package io.projWarehouse.SpringBot.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MicroscopeRepository  extends CrudRepository<Microscope, Long> {
    @Override
    List<Microscope> findAll();

}
