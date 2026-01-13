package com.info.service;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.info.config.DB;
import com.info.model.Person;

public class PersonServiceImpl implements PersonService {
	
	Connection cn = DB.getConnexion();
	Statement st = null;

	@Override
	public boolean addPerson(Person p) {
	    String sql = "INSERT INTO `person` (`Name`, `Age`) VALUES ('" + p.getName() + "'," + p.getAge() + ")";
	    try {
	        st = (Statement) cn.createStatement();
	        st.executeUpdate(sql);
	        System.out.println("Ajout avec succés");
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Erreur add");
	        return false;
	    }
	}

	@Override
	public boolean deletePerson(int id) {

	    String sql = "DELETE FROM `person` WHERE id=" + id;

	    try {
	        st = cn.createStatement();
	        st.executeUpdate(sql);

	        return true;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

	@Override
	public Person getPerson(int id) {
	    Person person = null;

	    String sql = "SELECT `ID`, `Name`, `Age` FROM `person` WHERE id=" + id;

	    try {
	        st = cn.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            person = new Person();
	            person.setId(rs.getInt("id"));
	            person.setAge(rs.getInt("age"));
	            person.setName(rs.getString("name"));
	        }

	        return person;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        return null;
	    }
	}
	
	@Override
	public Person getPersonByNameId(String name) {

	    Person person = null;

	    String sql = "SELECT `ID`, `Name`, `Age` FROM `person` WHERE name='" + name + "'";

	    try {
	        st = cn.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            person = new Person();
	            person.setId(rs.getInt("id"));
	            person.setAge(rs.getInt("age"));
	            person.setName(rs.getString("name"));
	        }

	        return person;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        return null;
	    }
	}
	
	public Person[] getAllPersons() {
	    List<Person> persons = new ArrayList<>();
	    String sql = "SELECT * FROM `person`";
	    try {
	        st = (Statement) cn.createStatement();
	        ResultSet rs = (ResultSet) st.executeQuery(sql);
	        
	        while (rs.next()) {
	            Person person = new Person();
	            person.setId(rs.getInt("id"));
	            person.setName(rs.getString("Name"));
	            person.setAge(rs.getInt("Age"));
	            persons.add(person);
	        }

	        return persons.toArray(new Person[0]);

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        return null;
	    }
	}
	public boolean updatePerson(int id, Person p) {
	    String sql = "UPDATE `person` SET Name='" + p.getName() + "', Age=" + p.getAge() + " WHERE ID=" + id;
	    try {
	        st = cn.createStatement();
	        int rows = st.executeUpdate(sql);
	        return rows > 0; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
