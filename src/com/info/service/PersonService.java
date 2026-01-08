package com.info.service;

import com.info.model.Person;

public interface PersonService {

    public boolean addPerson(Person p);

    public boolean deletePerson(int id);

    public Person getPersonByNameId(String name);

    public Person getPerson(int id);

    public Person[] getAllPersons();
    
   
    public boolean updatePerson(int id, Person p);
}
