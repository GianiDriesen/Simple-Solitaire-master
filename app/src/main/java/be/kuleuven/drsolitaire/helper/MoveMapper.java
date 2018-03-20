package be.kuleuven.drsolitaire.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import be.kuleuven.drsolitaire.classes.Move;

/**
 * Singleton to map Move entities to-and-from the database.
 */

public enum MoveMapper {
    UNIQUEMAPPER;

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
        String url = getURL(move);
        Log.d("DB",url);
        eMapper.queryAnswerless(move, url); //TODO fix this at switchcase emapper
    }

    @NonNull
    private String getURL(Move move) {
        return "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertMove.php?" +
                    "type=" + move.getType().toString() + "&time=" + move.getTime()+"&gid=" + move.getGameid();
    }


}