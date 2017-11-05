package de.tobiasbielefeld.solitaire.classes;

/**
 * Class representation of Person.
 */
public class Person {

    private int id;
    private String username;
    private String password;
    private int age;
    private boolean gender; //1=male, 0=female
    private int level;
    private int avgScore;
    private int avgMoves;
    private int avgTime;
    private int gamesSucces;
    private int gamesFailed;

    public Person() {
    }

    /**
     * Constructor for persons new to the database
     */
    public Person(int id, String username, String password, int age, boolean gender, int level, int avgScore, int avgMoves, int avgTime, int gamesSucces, int gamesFailed) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.level = level;
        this.avgScore = avgScore;
        this.avgMoves = avgMoves;
        this.avgTime = avgTime;
        this.gamesSucces = gamesSucces;
        this.gamesFailed = gamesFailed;
    }

    /**
     * Constructor for Person objects already in the database
     */
    public Person(String username, String password, int age, boolean gender, int level, int avgScore, int avgMoves, int avgTime, int gamesSucces, int gamesFailed) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.level = level;
        this.avgScore = avgScore;
        this.avgMoves = avgMoves;
        this.avgTime = avgTime;
        this.gamesSucces = gamesSucces;
        this.gamesFailed = gamesFailed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }

    public int getAvgMoves() {
        return avgMoves;
    }

    public void setAvgMoves(int avgMoves) {
        this.avgMoves = avgMoves;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public int getGamesSucces() {
        return gamesSucces;
    }

    public void setGamesSucces(int gamesSucces) {
        this.gamesSucces = gamesSucces;
    }

    public int getGamesFailed() {
        return gamesFailed;
    }

    public void setGamesFailed(int gamesFailed) {
        this.gamesFailed = gamesFailed;
    }
}

