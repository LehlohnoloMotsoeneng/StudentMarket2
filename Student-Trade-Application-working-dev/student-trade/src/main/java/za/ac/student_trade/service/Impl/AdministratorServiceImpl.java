package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.repository.AdministratorRepository;
import za.ac.student_trade.service.IAdministratorService;

import java.util.List;

@Service
public class AdministratorServiceImpl implements IAdministratorService {


    private AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator create(Administrator administrator) {
        return this.administratorRepository.save(administrator);
    }

    @Override
    public Administrator read(Long id) {
        return  administratorRepository.findById(id).orElse(null);
    }

    @Override
    public Administrator update(Administrator administrator) {
        return administratorRepository.save(administrator);
    }
    @Override
    public List<Administrator> getAll() {
        return administratorRepository.findAll();
    }

    @Override
    public List<Administrator> findByEmailAndPassword(String email, String password) {
        System.out.println("Searching for email: '" + email + "' and password: '" + password + "'");

        List<Administrator> result = this.administratorRepository.findByEmailAndPassword(email, password);
        System.out.println("Found " + result.size() + " administrator");

        return result;
    }
}



