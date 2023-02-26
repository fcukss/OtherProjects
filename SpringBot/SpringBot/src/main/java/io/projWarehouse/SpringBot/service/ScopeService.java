package io.projWarehouse.SpringBot.service;

import io.projWarehouse.SpringBot.model.Microscope;
import io.projWarehouse.SpringBot.model.MicroscopeRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ScopeService {

    @Autowired
   private MicroscopeRepository microscopeRepository;


    public List<Microscope> listOfAll() {
        return microscopeRepository.findAll();
    }

    public List<Microscope> listOfFreeScopes() {
        List<Microscope> listFree = new ArrayList<>();
        List<Microscope> list = microscopeRepository.findAll();

        for (Microscope e : list) {
            if (e.getStatus().equals("free"))
                listFree.add(e);
        }
        return listFree;
    }


}
