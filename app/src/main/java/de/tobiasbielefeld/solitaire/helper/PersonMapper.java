package de.tobiasbielefeld.solitaire.helper;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.tobiasbielefeld.solitaire.classes.Person;

/**
 * Singleton to map Person entities to-and-from the database.
 * @author Koen Pelsmaekers
 */

public enum PersonMapper {
	UNIQUEMAPPER;

	private Person person = new Person();
	private EntityMapper eMapper;

	/**
	 * Use only one entity mapper for all mappers
	 *
	 * @param entityMapper Applying the singleton design
	 */
	public void setEntityMapper(EntityMapper entityMapper) {
		this.eMapper = entityMapper;
	}

	public void getPersonByUsernameAndPassword(String username, String password) {
		String url = "https://iiw.kuleuven.be/onderzoek/drSolitaire/getPerson.php?username=" + username + "&password=" + password;
		Log.d("DB",url);
		Person tmpPerson = new Person();
		eMapper.queryEntity(tmpPerson, url);
	}

	/**
	 * Store a person in the database
	 *
	 * @param person The Person object that needs to be stored
	 */
	public void createPerson(Person person) {
		int gender = 0;
		if (person.isGender()) gender=1;
		String url = "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertPerson.php?username=" + person.getUsername() + "&password=" + person.getPassword() + "&age=" + person.getAge() +
				"&gender=" + gender + "&level=" + person.getLevel();
		Log.d("DB",url);
		Person tmpPerson = new Person();
		eMapper.queryEntity(tmpPerson, url);
	}

	/**
	 * Update all non-nullable columns of a Person in the database
	 *
	 * @param person The Person object with the new data
	 */
	public void updatePerson(Person person) {
		int gender = 0;
		if (person.isGender()) gender=1;
		String url = "https://iiw.kuleuven.be/onderzoek/drSolitaire/updatePerson.php?username=" + person.getUsername() + "&password=" + person.getPassword() + "&age=" + person.getAge() +
				"&gender=" + gender + "&level=" + person.getLevel() + "&avgScore=" + person.getAvgScore() + "&avgMoves=" + person.getAvgMoves() + "&avgTime=" + person.getAvgTime() +
				"&gamesSucces=" + person.getGamesSucces() + "&gamesFailed=" + person.getGamesFailed();
		Log.d("DB",url);
		Person tmpPerson = new Person();
		eMapper.queryEntity(tmpPerson, url);
	}
}