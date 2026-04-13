package org.example;

import org.example.enums.Gender;
import org.example.models.Person;
import org.example.service.PersonService;
import org.example.service.serviceImpl.PersonIServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        PersonService personService = new PersonIServiceImpl();


//        personService.creteTablePerson();


        Person person1 = new Person("Rashid", 20, Gender.MALE, "rashid@gmail.com");
        Person person2 = new Person("Aigerim", 22, Gender.FEMALE, "aigerim@gmail.com");
//
//        System.out.println(personService.insertPerson(person1));
//        System.out.println(personService.insertPerson(person2));

//        System.out.println(personService.findAllPerson());

//        System.out.println(personService.findPersonById(1L));

//        System.out.println(personService.updatePerson(1L, new Person("Панзат",46,Gender.MALE,"p@gmail.com")));

        System.out.println(personService.deletePersonById(2L));

    }
}
