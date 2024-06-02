package com.example.dao;

import com.example.model.Person;
import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PersonDAO {
    
    private final List<Person> all_persons = new ArrayList<>();

    // Method to add a person to the list
    public void add_Person(Person person) {
        all_persons.add(person);
    }
    
    // Method to get a person by ID
    public Person get_Person(int id) throws NotFoundException {
        for (Person person : all_persons) {
            if (person.getID()== id) {
                return person;
            }
        }
        // Throw NotFoundException if person with given ID is not found
        throw new NotFoundException("Person with Id " + id + " not found");
    }

    // Method to update a person by ID
    public void update_Person(int id, Person updatedPerson) throws NotFoundException {
        Person person = get_Person(id);

        // Update person's details
        person.setName(updatedPerson.getName());
        person.setContactInformation(updatedPerson.getContactInformation());
        person.setAddress(updatedPerson.getAddress());
    }

    // Method to delete a person by ID
    public void delete_Person(int id) throws NotFoundException {
        Person person = get_Person(id);
        all_persons.remove(person);
    }
    
}

