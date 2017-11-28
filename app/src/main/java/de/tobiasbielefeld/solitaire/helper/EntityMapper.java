package de.tobiasbielefeld.solitaire.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import de.tobiasbielefeld.solitaire.classes.GamePlayed;
import de.tobiasbielefeld.solitaire.classes.Person;

/**
 * Contains standard methods for mappers
 */

// @GN
public enum EntityMapper {
    UNIQUEMAPPER;

    public Person person;
    public List<Person> persons;
    public GamePlayed game;
    public List<GamePlayed> games;
    public boolean dataReceived;
    public void dataGrabbed() { // allow to reset the flag and all entities.
        this.dataReceived = false;
        person = null;
        persons = new LinkedList<>();
    }
    private PersonMapper personMapper = PersonMapper.UNIQUEMAPPER;
    private GameMapper gameMapper = GameMapper.UNIQUEMAPPER;

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

    public GameMapper getGameMapper() {return gameMapper;}



    public void setTmpPerson(Person person){
        this.person = person;
        this.dataReceived = true;
    }

    public void setTmpPersons(List<Person> persons) {
        this.persons = persons;
        this.dataReceived = true;
    }

    public void setTmpGame(GamePlayed game) {
        this.game = game;
        this.dataReceived = true;
    }

    public void setTmpGames (List<GamePlayed> games) {
        this.games = games;
        this.dataReceived = true;
    }
}
