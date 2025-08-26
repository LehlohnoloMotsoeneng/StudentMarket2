package za.ac.student_trade.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.repository.AddressRepository;
import za.ac.student_trade.service.IService;

import java.util.List;

@Service
public class AddressServiceImpl implements IService<Address, Long> {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address read(Long Long) {
        return addressRepository.findById(Long).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {return addressRepository.findAll();}



}
