package de.tobiasbielefeld.solitaire.helper;

import android.support.annotation.NonNull;
import android.util.Log;

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
		String url = getURLPersonByUserName(username, password);
		Log.d("DB",url);
		Person tmpPerson = new Person();
		eMapper.queryEntity(tmpPerson, url);
	}

	private String getURLPersonByUserName(String username, String password) {
		return "https://iiw.kuleuven.be/onderzoek/drSolitaire/getPerson.php?username=" + username + "&password=" + password;
	}

	/**
	 * Store a person in the database
	 *
	 * @param person The Person object that needs to be stored
	 */
	public void createPerson(Person person) {
		String url = getURLCreatePerson(person);
		Log.d("DB",url);
		Person tmpPerson = new Person();
		eMapper.queryEntity(tmpPerson, url);
	}

	@NonNull
	private String getURLCreatePerson(Person person) {
		int gender = 0;
		if (person.isGender()) gender=1;
		return "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertPerson.php?username=" + person.getUsername() + "&password=" + person.getPassword() + "&age=" + person.getAge() +
				"&gender=" + gender + "&level=" + person.getLevel();
	}
}