package de.tobiasbielefeld.solitaire.helper;

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
	UNIQUEMAPPER();

	/** Private constructor for Singleton design pattern */
	PersonMapper() {}

    private EntityMapper eMapper = EntityMapper.UNIQUEMAPPER;

	/**
	 * Get a Person object by X
	 * @param string The item of the Person to be found
	 * @param column the column the item must be found
	 * @return Person object (or null if it was not found)
	 */
	public void getPersonByX(String string, PersonColumns column) {
		String select = "SELECT * FROM person where "+column.getColName()+" = ?";
		System.out.println(select);
		Person person = null;
		try {
			PreparedStatement prepstat = Database.CONNECTION.getConnection().prepareStatement(select);
			prepstat.setString(1, string);
			person = queryPerson(prepstat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EntityMapper.UNIQUEMAPPER.setTmpPerson(person);
	}

	/**
	 * Get all persons where X
	 * @param  string the field value you're looking for in the database
	 * @param  column the Column this item belongs to
	 * @return A Collection of all persons
	 */
	public void getPersonsByX(String string, PersonColumns column) {
		List<Person> persons = new LinkedList<Person>();
		try {
			Statement stmt = Database.CONNECTION.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM person WHERE "+column.getColName()+" = '"+string+"' ORDER BY username ");
			while (rset.next()) {
				persons.add(new Person(rset.getString("username"), rset.getString("password") , rset.getInt("age"),
						rset.getBoolean("gender"), rset.getInt("level"), rset.getInt("avgScore"), rset.getInt("avgMoves"),
						rset.getInt("avgTime"), rset.getInt("gamesSucces"), rset.getInt("gamesFailed")));
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EntityMapper.UNIQUEMAPPER.setTmpPersons(persons);
	}


	/**
	 * Store a person in the database; new Java 7 try-with-resources used
	 * @param person The Person object that needs to be stored
	 * @return The ID for the person that is inserted, -1 if the insert did not succeed
	 */
	public int createPerson(Person person) {
        int id = -1;
        try {
            String sql = "INSERT INTO Person (username, password, age, gender, level,avgScore,avgMoves,avgTime,gamesSucces,gamesFailed) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
            	PreparedStatement pstmt = Database.CONNECTION.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, person.getUsername());
                pstmt.setString(2, person.getPassword());
                pstmt.setInt(3,person.getAge());
                pstmt.setBoolean(4, person.isGender());
                pstmt.setInt(5, person.getLevel());
                pstmt.setInt(6, 0);
                pstmt.setInt(7,0);
                pstmt.setInt(8,0);
                pstmt.setInt(9,0);
                pstmt.setInt(10,0);
                id = eMapper.executeCreate(pstmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		return id;
	}
	
	/**
     * Get all persons
     * @return A Collection of all persons
     */
    public void getPersons() {
        List<Person> persons = new LinkedList<Person>();
        try {
            Statement stmt = Database.CONNECTION.getConnection().createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM person ORDER BY rnummer");
            while (rset.next()) {
                persons.add(new Person(rset.getString("username"), rset.getString("password") , rset.getInt("age"),
								rset.getBoolean("gender"), rset.getInt("level"), rset.getInt("avgScore"), rset.getInt("avgMoves"),
								rset.getInt("avgTime"), rset.getInt("gamesSucces"), rset.getInt("gamesFailed")));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EntityMapper.UNIQUEMAPPER.setTmpPersons(persons);
    }
    
	/**
	 * Delete a person from the database
	 * @param id The id of the Person to be deleted
	 * @return Number of rows affected (should be 1)
	 */
	public int deletePerson(int id) {
		String sql = "DELETE FROM person WHERE id = ?";
		return eMapper.executeDelete(sql, id);
	}

	/**
	 * Update the columns of a person in the database
	 * @param person The Person object with the new data
	 * @return Number of rows affected (should be 1)
	 */
	public int updatePerson(Person person) {
		int rowsAffected = 0;
		String sql = "UPDATE person SET password = ?, age = ?, gender = ?,  level = ?, avgScore = ?, avgMoves = ?, avgTime = ?, gamesSucces = ?, gamesFailed =?, WHERE username = ?";
		try {
			PreparedStatement pstmt = Database.CONNECTION.getConnection().prepareStatement(sql);
			pstmt.setString(10, person.getUsername());
			pstmt.setString(1, person.getPassword());
			pstmt.setInt(2,person.getAge());
			pstmt.setBoolean(3, person.isGender());
			pstmt.setInt(4, person.getLevel());
			pstmt.setInt(5, person.getAvgScore());
			pstmt.setInt(6, person.getAvgMoves());
			pstmt.setInt(7, person.getAvgTime());
			pstmt.setInt(8, person.getGamesSucces());
			pstmt.setInt(9, person.getGamesFailed());
			// executeUpdate() should be called to change something in the database
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	

	/*
	 * Private helper method to query a Person object based on database structure
	 */
	private Person queryPerson(PreparedStatement prepstat) {
		Person person = null;
		ResultSet rs = null;
		try {
			rs = prepstat.executeQuery();
			if (rs.next()) {
			person = mapToPersonEntity(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				prepstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return person;
	}

	
	public Person mapToPersonEntity(ResultSet rset) {
		Person person = null;
		try {
			int id = rset.getInt("id");
			String username = rset.getString("username");
			String password = rset.getString("password");
			int age = rset.getInt("age");
			boolean gender = rset.getBoolean("gender");
			int level = rset.getInt("level");
			int avgScore = rset.getInt("avgScore");
			int avgMoves = rset.getInt("avgMoves");
			int avgTijd = rset.getInt("avgTime");
			int gamesSucces = rset.getInt("gamesSucces");
			int gamesFailed = rset.getInt("gamesFailed");
			person = new Person(id, username, password, age, gender, level, avgScore, avgMoves, avgTijd, gamesSucces,gamesFailed);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
}
