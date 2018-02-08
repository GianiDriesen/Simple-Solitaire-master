package de.tobiasbielefeld.solitaire.helper;

import android.util.Log;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.tobiasbielefeld.solitaire.SharedData;
import de.tobiasbielefeld.solitaire.classes.GamePlayed;
import de.tobiasbielefeld.solitaire.classes.Move;
import de.tobiasbielefeld.solitaire.classes.Person;

/**
 * Singleton to map Person entities to-and-from the database.
 * @author Koen Pelsmaekers
 */

// @GN
public enum GameMapper {
    UNIQUEMAPPER;

    private GamePlayed game = new GamePlayed();
    private EntityMapper eMapper;

    /**
     * Use only one entity mapper for all mappers
     *
     * @param entityMapper Applying the singleton design
     */
    public void setEntityMapper(EntityMapper entityMapper) {
        this.eMapper = entityMapper;
    }


    /**
     * Store a game in the database
     *
     * @param game The Person object that needs to be stored
     */
    public void createGame(GamePlayed game) {
        int solved = (game.isSolved() ? 1 : 0);
        String url = "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertGame.php?" +
                "pid=" + game.getPersonID() + "&playt=" + game.getGameTime() + "&solved=" + solved + "&throughpilectr=" + game.getCountTroughPile() +
                "&undoctr=" + game.getUndoButtonCount() + "&hintctr=" + game.getHintButtonCount() + "&bs1ctr=" + game.getBuildStack1() + "&bs2ctr=" + game.getBuildStack2() +
                "&bs3ctr=" + game.getBuildStack3() + "&bs4ctr=" + game.getBuildStack4() + "&bs5ctr=" + game.getBuildStack5() + "&bs6ctr=" + game.getBuildStack6() +
                "&bs7ctr=" + game.getBuildStack7() + "&ss1ctr=" + game.getSuitStack1() + "&ss2ctr=" + game.getSuitStack2() + "&ss3ctr=" + game.getSuitStack3() +
                "&ss4ctr=" + game.getSuitStack4() + "&tsctr=" + game.getTalonStack() + "&psctr=" + game.getPileStack() + "&colorerrctr=" + game.getMoveSameColorError() +
                "&numbererrctr=" + game.getMoveWrongNumberError() + "&avgmotort=" + game.getAvgMotorTime() + "&betaerrctr=" + game.getBetaError() + "&seedctr=" +game.getGameseed()
                + "&scorectr=" +game.getScore();
        Log.d("DB",url);
        GamePlayed tmpGame = new GamePlayed(); //@KG why is this object created here and not in the eMapper?
        eMapper.queryEntity(tmpGame, url);
    }


}
/*
    */
/**
     * Get a Person object by X
     * @param string The item of the Person to be found
     * @param column the column the item must be found
     * @return Person object (or null if it was not found)
     *//*

    public void getGameByX(String string, GameColumns column) {
        String select = "SELECT * FROM Game where "+column.getColName()+" = ?";
        System.out.println(select);
        GamePlayed game = null;
        try {
            PreparedStatement prepstat = Database.CONNECTION.getConnection().prepareStatement(select);
            prepstat.setString(1, string);
            game = queryGame(prepstat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EntityMapper.UNIQUEMAPPER.setTmpGame(game);
    }

    */
/**
     * Get all gamees where X
     * @param  string the field value you're looking for in the database
     * @param  column the Column this item belongs to
     * @return A Collection of all persons
     *//*

    public void getGamesByX(String string, PersonColumns column) {
        List<GamePlayed> games = new LinkedList<GamePlayed>();
        try {
            Statement stmt = Database.CONNECTION.getConnection().createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM Game WHERE "+column.getColName()+" = '"+string+"' ORDER BY username ");
            while (rset.next()) {
                games.add(new GamePlayed(rset.getInt("id"), rset.getInt("personID"), rset.getDate("startTime"),
                rset.getDate("endTime"), rset.getInt("isSolved"), rset.getInt("moves"), rset.getInt("countTroughPile"),
                rset.getInt("avgIdleTime"), rset.getInt("avgSwipeTime"), rset.getInt("buildStack1"), rset.getInt("buildStack2"),
                rset.getInt("buildStack3"), rset.getInt("buildStack4"), rset.getInt("buildStack5"), rset.getInt("buildStack6"),
                rset.getInt("buildStack7"), rset.getInt("suitStack1"), rset.getInt("suitStack2"), rset.getInt("suitStack3"),
                rset.getInt("suitStack4"), rset.getInt("talonStack"), rset.getInt("pileStack"), rset.getInt("randomTapCounter"),
                rset.getInt("movePileToSuitError"), rset.getInt("movePileToBuildError"), rset.getInt("moveBuildToBuildError"), rset.getInt("moveSameColorError"),
                rset.getInt("moveWrongNumberError"), rset.getInt("ignoreAceTime"), rset.getInt("hintButtonCount"), rset.getInt("undoButtonCount"),
                rset.getInt("badPrecisionCounter"), rset.getInt("betaError")));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EntityMapper.UNIQUEMAPPER.setTmpGames(games);
    }


    */
/**
     * Store a game in the database; new Java 7 try-with-resources used
     * @param game The GamePlayed object that needs to be stored
     * @return The ID for the game that is inserted, -1 if the insert did not succeed
     *//*

    public int createGame(GamePlayed game) {
        int id = -1;
        try {
            String sql = "INSERT INTO Game (personID, startTime, endTime, isSolved, moves, countTroughPile, avgIdleTime, avgSwipeTime, buildStack1, buildStack2, buildStack3," +
                    " buildStack4, buildStack5, buildStack6, buildStack7, suitStack1, suitStack2, suitStack3, suitStack4, talonStack, pileStack, randomTapCounter, movePileToSuitError," +
                    " movePileToBuildError, moveBuildToBuildError, moveSameColorError, moveWrongNumberError, ignoreAceTime, hintButtonCount, undoButtonCount, badPrecisionCounter, betaError)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement pstmt = Database.CONNECTION.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, SharedData.user.getId());
                pstmt.setDate(2, game.getStartTime());
                pstmt.setDate(3, game.getEndTime());
                pstmt.setInt(4, game.getIsSolved());
                pstmt.setInt(5, game.getMoves());
                pstmt.setInt(6, game.getCountTroughPile());
                pstmt.setInt(7, game.getAvgIdleTime());
                pstmt.setInt(8, game.getAvgSwipeTime());
                pstmt.setInt(9, game.getBuildStack1());
                pstmt.setInt(10, game.getBuildStack2());
                pstmt.setInt(11, game.getBuildStack3());
                pstmt.setInt(12, game.getBuildStack4());
                pstmt.setInt(13, game.getBuildStack5());
                pstmt.setInt(14, game.getBuildStack6());
                pstmt.setInt(15, game.getBuildStack7());
                pstmt.setInt(16, game.getSuitStack1());
                pstmt.setInt(17, game.getSuitStack2());
                pstmt.setInt(18, game.getSuitStack3());
                pstmt.setInt(19, game.getSuitStack4());
                pstmt.setInt(20, game.getTalonStack());
                pstmt.setInt(21, game.getPileStack());
                pstmt.setInt(22, game.getRandomTapCounter());
                pstmt.setInt(23, game.getMovePileToSuitError());
                pstmt.setInt(24, game.getMovePileToBuildError());
                pstmt.setInt(25, game.getMoveBuildToBuildError());
                pstmt.setInt(26, game.getMoveSameColorError());
                pstmt.setInt(27, game.getMoveWrongNumberError());
                pstmt.setInt(28, game.getIgnoreAceTime());
                pstmt.setInt(29, game.getHintButtonCount());
                pstmt.setInt(30, game.getUndoButtonCount());
                pstmt.setInt(31, game.getBadPrecisionCounter());
                pstmt.setInt(32,game.getBetaError());
                id = eMapper.executeCreate(pstmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return id;
    }

    */
/**
     * Get all games
     * @return A Collection of all persons
     *//*

    public void getAllGames() {
        List<GamePlayed> games = new LinkedList<GamePlayed>();
        try {
            Statement stmt = Database.CONNECTION.getConnection().createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM Game");
            while (rset.next()) {
                games.add(new GamePlayed(rset.getInt("id"), rset.getInt("personID"), rset.getDate("startTime"),
                        rset.getDate("endTime"), rset.getInt("isSolved"), rset.getInt("moves"), rset.getInt("countTroughPile"),
                        rset.getInt("avgIdleTime"), rset.getInt("avgSwipeTime"), rset.getInt("buildStack1"), rset.getInt("buildStack2"),
                        rset.getInt("buildStack3"), rset.getInt("buildStack4"), rset.getInt("buildStack5"), rset.getInt("buildStack6"),
                        rset.getInt("buildStack7"), rset.getInt("suitStack1"), rset.getInt("suitStack2"), rset.getInt("suitStack3"),
                        rset.getInt("suitStack4"), rset.getInt("talonStack"), rset.getInt("pileStack"), rset.getInt("randomTapCounter"),
                        rset.getInt("movePileToSuitError"), rset.getInt("movePileToBuildError"), rset.getInt("moveBuildToBuildError"), rset.getInt("moveSameColorError"),
                        rset.getInt("moveWrongNumberError"), rset.getInt("ignoreAceTime"), rset.getInt("hintButtonCount"), rset.getInt("undoButtonCount"),
                        rset.getInt("badPrecisionCounter"), rset.getInt("betaError")));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EntityMapper.UNIQUEMAPPER.setTmpGames(games);
    }

    */
/**
     * Delete a person from the database
     * @param id The id of the Person to be deleted
     * @return Number of rows affected (should be 1)
     *//*

    public int deletePerson(int id) {
        String sql = "DELETE FROM person WHERE id = ?";
        return eMapper.executeDelete(sql, id);
    }

    */
/**
     * Update the columns of a game in the database
     * @param game The gameplayed object with the new data
     * @return Number of rows affected (should be 1)
     *//*

    public int updatePerson(GamePlayed game) {
        int rowsAffected = 0;
        String sql = "UPDATE Game personID = ?, startTime= ?, endTime = ?, isSolved = ?, moves = ?, countTroughPile = ?, avgIdleTime = ?, avgSwipeTime = ?," +
                " buildStack1 = ?, buildStack2 = ?, buildStack3 = ?, buildStack4 = ?, buildStack5 = ?, buildStack6 = ?, buildStack7 = ?, suitStack1 = ?," +
                " suitStack2 = ?, suitStack3 = ?, suitStack4 = ?, talonStack = ?, pileStack = ?, randomTapCounter = ?, movePileToSuitError = ?, movePileToBuildError = ?," +
                " moveBuildToBuildError = ?, moveSameColorError = ?, moveWrongNumberError = ?, ignoreAceTime = ?, hintButtonCount = ?, undoButtonCount = ?, badPrecisionCounter = ?, betaError = ?";
        try {
            PreparedStatement pstmt = Database.CONNECTION.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, SharedData.user.getId());
            pstmt.setDate(2, game.getStartTime());
            pstmt.setDate(3, game.getEndTime());
            pstmt.setInt(4, game.getIsSolved());
            pstmt.setInt(5, game.getMoves());
            pstmt.setInt(6, game.getCountTroughPile());
            pstmt.setInt(7, game.getAvgIdleTime());
            pstmt.setInt(8, game.getAvgSwipeTime());
            pstmt.setInt(9, game.getBuildStack1());
            pstmt.setInt(10, game.getBuildStack2());
            pstmt.setInt(11, game.getBuildStack3());
            pstmt.setInt(12, game.getBuildStack4());
            pstmt.setInt(13, game.getBuildStack5());
            pstmt.setInt(14, game.getBuildStack6());
            pstmt.setInt(15, game.getBuildStack7());
            pstmt.setInt(16, game.getSuitStack1());
            pstmt.setInt(17, game.getSuitStack2());
            pstmt.setInt(18, game.getSuitStack3());
            pstmt.setInt(19, game.getSuitStack4());
            pstmt.setInt(20, game.getTalonStack());
            pstmt.setInt(21, game.getPileStack());
            pstmt.setInt(22, game.getRandomTapCounter());
            pstmt.setInt(23, game.getMovePileToSuitError());
            pstmt.setInt(24, game.getMovePileToBuildError());
            pstmt.setInt(25, game.getMoveBuildToBuildError());
            pstmt.setInt(26, game.getMoveSameColorError());
            pstmt.setInt(27, game.getMoveWrongNumberError());
            pstmt.setInt(28, game.getIgnoreAceTime());
            pstmt.setInt(29, game.getHintButtonCount());
            pstmt.setInt(30, game.getUndoButtonCount());
            pstmt.setInt(31, game.getBadPrecisionCounter());
            pstmt.setInt(32,game.getBetaError());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }


    */
/*
     * Private helper method to query a Person object based on database structure
     *//*

    private GamePlayed queryGame(PreparedStatement prepstat) {
        GamePlayed game = null;
        ResultSet rs = null;
        try {
            rs = prepstat.executeQuery();
            if (rs.next()) {
                game = mapToPersonEntity(rs);
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
        return game;
    }


    public GamePlayed mapToPersonEntity(ResultSet rset) {
        GamePlayed game = null;
        try {
            int id= rset.getInt("id");
            int personID= rset.getInt("personID");
            Date startTime = rset.getDate("startTime");
            Date endTime = rset.getDate("endTime");
            int isSolved = rset.getInt("isSolved");
            int moves = rset.getInt("moves");
            int countTroughPile = rset.getInt("countTroughPile");
            int avgIdleTime = rset.getInt("avgIdleTime");
            int avgSwipeTime = rset.getInt("avgSwipeTime");
            int buildStack1 = rset.getInt("buildStack1");
            int buildStack2 = rset.getInt("buildStack2");
            int buildStack3 = rset.getInt("buildStack3");
            int buildStack4 = rset.getInt("buildStack4");
            int buildStack5 = rset.getInt("buildStack5");
            int buildStack6 = rset.getInt("buildStack6");
            int buildStack7 = rset.getInt("buildStack7");
            int suitStack1 = rset.getInt("suitStack1");
            int suitStack2 = rset.getInt("suitStack2");
            int suitStack3 = rset.getInt("suitStack3");
            int suitStack4 = rset.getInt("suitStack4");
            int talonStack = rset.getInt("talonStack");
            int pileStack = rset.getInt("pileStack");
            int randomTapCounter = rset.getInt("randomTapCounter");
            int movePileToSuitError = rset.getInt("movePileToSuitError");
            int movePileToBuildError = rset.getInt("movePileToBuildError");
            int moveBuildToBuildError = rset.getInt("moveBuildToBuildError");
            int moveSameColorError = rset.getInt("moveSameColorError");
            int moveWrongNumberError = rset.getInt("moveWrongNumberError");
            int ignoreAceTime = rset.getInt("ignoreAceTime");
            int hintButtonCount = rset.getInt("hintButtonCount");
            int undoButtonCount = rset.getInt("undoButtonCount");
            int badPrecisionCounter = rset.getInt("badPrecisionCounter");
            int betaError = rset.getInt("betaError");
            game = new GamePlayed(id, personID, startTime, endTime, isSolved, moves, countTroughPile, avgIdleTime, avgSwipeTime,
            buildStack1, buildStack2, buildStack3, buildStack4, buildStack5, buildStack6, buildStack7, suitStack1,
            suitStack2, suitStack3, suitStack4, talonStack, pileStack, randomTapCounter, movePileToSuitError,
            movePileToBuildError, moveBuildToBuildError, moveSameColorError, moveWrongNumberError, ignoreAceTime,
            hintButtonCount, undoButtonCount, badPrecisionCounter, betaError);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }
}
*/
