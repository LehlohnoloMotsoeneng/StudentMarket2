package za.ac.student_trade.service;

import za.ac.student_trade.domain.Residence;

import java.util.List;

public interface ResidenceService<T, ID> {



    Residence create(Residence residence);

    Residence read(Long id);

    Residence update(Residence residence);

    List<Residence> getAll();


}




