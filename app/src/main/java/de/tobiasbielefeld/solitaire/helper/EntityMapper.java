package de.tobiasbielefeld.solitaire.helper;

import de.tobiasbielefeld.solitaire.classes.Person;
import de.tobiasbielefeld.solitaire.helper.Database;

import java.security.Permission;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Contains standard methods for mappers
 */
public enum EntityMapper {
    UNIQUEMAPPER;

    public Person person;
    public LinkedList<Person> persons;
    public boolean dataReceived;
    public void dataGrabbed() { // allow to reset the flag and all entities.
        this.dataReceived = false;
        person = null;
        persons = new LinkedList<>();
    }
    public PersonMapper personMapper = PersonMapper.UNIQUEMAPPER;

    EntityMapper() {}

    public int executeDelete(String sql, int id) {
        int rowsAffected = 0;
        try {
            PreparedStatement prepstat = Database.CONNECTION.getConnection().prepareStatement(sql);
            prepstat.setInt(1, id);
            rowsAffected = prepstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int executeCreate(PreparedStatement preparedStatement) {
        int id = -1;
        try {
            // executeUpdate() should be called to change something in the database
        	int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs != null) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public PersonMapper getPersonMapper() {
        return personMapper;
    }

    public void setTmpPerson(Person person){
        this.person = person;
        this.dataReceived = true;
    }
}
