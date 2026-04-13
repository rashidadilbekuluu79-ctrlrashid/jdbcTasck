package org.example.dao;

import org.example.models.Person;

import java.util.List;

public interface PersonDao {
    void creteTablePerson();

    String insertPerson(Person person);

    Person findPersonById(Long id);

    List<Person> findAllPerson();

    String updatePerson(Long id,Person person);

    String deletePersonById(Long id);
}
