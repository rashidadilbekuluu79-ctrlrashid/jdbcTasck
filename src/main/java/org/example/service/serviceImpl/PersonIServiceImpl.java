package org.example.service.serviceImpl;

import org.example.dao.PersonDao;
import org.example.dao.daoImpl.PersonImpl;
import org.example.models.Person;
import org.example.service.PersonService;

import java.util.List;

public class PersonIServiceImpl implements PersonService {
    private final PersonDao personDao = new PersonImpl();
    @Override
    public void creteTablePerson() {
        personDao.creteTablePerson();
    }

    @Override
    public String insertPerson(Person person) {
        return personDao.insertPerson(person);
    }

    @Override
    public Person findPersonById(Long id) {
        return personDao.findPersonById(id);
    }

    @Override
    public List<Person> findAllPerson() {
        return personDao.findAllPerson();
    }

    @Override
    public String updatePerson(Long id, Person person) {
        return personDao.updatePerson(id, person);
    }

    @Override
    public String deletePersonById(Long id) {
        return personDao.deletePersonById(id);
    }
}
