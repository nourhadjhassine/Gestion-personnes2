package com.info.router;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.info.model.Person;
import com.info.service.PersonServiceImpl;

@Path("/users")
public class UserResource {
    PersonServiceImpl ps = new PersonServiceImpl();

    @GET
    @Path("/affiche")
    @Produces(MediaType.APPLICATION_JSON)
    public Person[] getAllUsers() {
        return ps.getAllPersons();
    }

    @PUT
    @Path("/add/{age}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> addUser(@PathParam("age") int age, @PathParam("name") String name) {
        Map<String, Object> response = new HashMap<>();
        Person user = new Person();
        user.setAge(age);
        user.setName(name);
        ps.addPerson(user);
        response.put("state", "ok");
        return response;
    }

    @PUT
    @Path("/update/{id}/{age}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> updateUser(@PathParam("id") int id,
                                          @PathParam("age") int age,
                                          @PathParam("name") String name) {
        Map<String, Object> response = new HashMap<>();
        Person user = new Person();
        user.setAge(age);
        user.setName(name);

        if (ps.updatePerson(id, user)) {
            response.put("state", "ok");
        } else {
            response.put("state", "ko: id not found");
        }
        return response;
    }

    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> deleteUser(@PathParam("id") int id) {
        Map<String, Object> response = new HashMap<>();
        if (ps.deletePerson(id)) {
            response.put("state", "ok");
        } else {
            response.put("state", "ko: id doesn't exist");
        }
        return response;
    }
}
