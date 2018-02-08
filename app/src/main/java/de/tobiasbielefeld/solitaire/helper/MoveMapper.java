package de.tobiasbielefeld.solitaire.helper;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import de.tobiasbielefeld.solitaire.classes.Move;
import de.tobiasbielefeld.solitaire.classes.Person;

/**
 * Singleton to map Move entities to-and-from the database.
 * @author Koen Pelsmaekers
 */

public enum MoveMapper {
    UNIQUEMAPPER;


    private Move move = new Move();
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
     * Store a move in the DB
     *
     * @param move The Move object that needs to be stored
     */
    public void createMove(Move move) {
        String url = "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertMove.php?" +
                "pid=" + move.getPersonID() + "&gseed=" + move.getGameseed()+"&time=" + move.getTime()+"&gid=" + move.getGameid();
        Log.d("DB",url);
        Move tmpMove = new Move();
        eMapper.queryEntity(tmpMove, url); //TODO fix this at switchcase emapper
    }


}