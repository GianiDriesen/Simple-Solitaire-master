package be.kuleuven.drsolitaire.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import be.kuleuven.drsolitaire.classes.GamePlayed;

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
        String url = getURL(game);
        Log.d("DB",url);
        GamePlayed tmpGame = new GamePlayed(); //@KG why is this object created here and not in the eMapper?
        eMapper.queryEntity(tmpGame, url);
    }

    @NonNull
    public String getURL(GamePlayed game) {
        int solved = (game.isSolved() ? 1 : 0);
        return "https://iiw.kuleuven.be/onderzoek/drSolitaire/insertGame.php?" +
                "pid=" + game.getPersonID() + "&playt=" + game.getGameTime() + "&solved=" + solved + "&throughpilectr=" + game.getCountTroughPile() +
                "&undoctr=" + game.getUndoButtonCount() + "&hintctr=" + game.getHintButtonCount() + "&bs1ctr=" + game.getBuildStack1() + "&bs2ctr=" + game.getBuildStack2() +
                "&bs3ctr=" + game.getBuildStack3() + "&bs4ctr=" + game.getBuildStack4() + "&bs5ctr=" + game.getBuildStack5() + "&bs6ctr=" + game.getBuildStack6() +
                "&bs7ctr=" + game.getBuildStack7() + "&ss1ctr=" + game.getSuitStack1() + "&ss2ctr=" + game.getSuitStack2() + "&ss3ctr=" + game.getSuitStack3() +
                "&ss4ctr=" + game.getSuitStack4() + "&tsctr=" + game.getTalonStack() + "&psctr=" + game.getPileStack() + "&colorerrctr=" + game.getMoveSameColorError() +
                "&numbererrctr=" + game.getMoveWrongNumberError() + "&avgmotort=" + game.getAvgMotorTime() + "&betaerrctr=" + game.getBetaError() + "&seedctr=" +game.getGameseed()
                + "&scorectr=" +game.getScore();
    }


}