package com.example.resource;

import com.example.dao.PersonDAO;
import com.example.model.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private PersonDAO personDAO = new PersonDAO();

    @POST
    public Response addPerson(Person person) {
        personDAO.add_Person(person);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{ID}")
    public Response getPerson(@PathParam("ID") int id) {
        try {
            Person person = personDAO.get_Person(id);
            return Response.ok(person).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{ID}")
    public Response updatePerson(@PathParam("ID") int id, Person updatedPerson) {
        try {
            personDAO.update_Person(id, updatedPerson);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{ID}")
    public Response deletePerson(@PathParam("ID") int id) {
        try {
            personDAO.delete_Person(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

