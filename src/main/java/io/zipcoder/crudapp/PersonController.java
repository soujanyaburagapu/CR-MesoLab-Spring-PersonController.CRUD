package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);

    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);

    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);

    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person){
        Person personUpdate = personRepository.findOne(id);

        personUpdate.setLastName(person.getLastName());
        personUpdate.setFirstName(person.getFirstName());

        // return new ResponseEntity<>(this.muffinRepository.save(foundMuffin), HttpStatus.OK);
        return new ResponseEntity<>(this.personRepository.save(personUpdate),HttpStatus.OK );

    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.delete(id);

    }
}
