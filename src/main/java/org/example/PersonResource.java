package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> listAll() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person findById(@PathParam("id") Long id) {
        return Person.findById(id);
    }

    @POST
    @Transactional
    public Person create(Person person) {
        // não persistir a entidade recebida (pode já ter id). crie uma nova instância.
        Person p = new Person();
        p.name = person.name;
        p.age = person.age;
        p.persist();
        return p;
        // alternativa rápida: person.id = null; person.persist();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, Person updated) {
        Person person = Person.findById(id);
        person.name = updated.name;
        person.age = updated.age;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Person.deleteById(id);
    }
}
