package de.tobiasbielefeld.solitaire.classes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class representation of Games Played by Persons.
 */

// @NG
public class GamePlayed {




    private int gameseed;
    private int id;
    private int personID;
    private int gameTime;
    private boolean isSolved;
    private int countTroughPile;
    private int avgMotorTime;
    private int buildStack1;
    private int buildStack2;
    private int buildStack3;
    private int buildStack4;
    private int buildStack5;
    private int buildStack6;
    private int buildStack7;
    private int suitStack1;
    private int suitStack2;
    private int suitStack3;
    private int suitStack4;
    private int talonStack;
    private int pileStack;
    private int moveSameColorError;
    private int moveWrongNumberError;
    private int hintButtonCount;
    private int undoButtonCount;
    private int betaError;
    private long score;

    public GamePlayed() {
    }

    public GamePlayed(JSONObject obj) {
        try {
            this.id = obj.getInt("id");
            this.personID = obj.getInt("personID");
            this.gameTime = obj.getInt("playTime");
            this.isSolved = (obj.getInt("isSolved") == 1); //@kg
            this.countTroughPile = obj.getInt("throughPileCounter");
            this.avgMotorTime = obj.getInt("avgMotorTime");
            this.buildStack1 = obj.getInt("buildStack1Counter");
            this.buildStack2 = obj.getInt("buildStack2Counter");
            this.buildStack3 = obj.getInt("buildStack3Counter");
            this.buildStack4 = obj.getInt("buildStack4Counter");
            this.buildStack5 = obj.getInt("buildStack5Counter");
            this.buildStack6 = obj.getInt("buildStack6Counter");
            this.buildStack7 = obj.getInt("buildStack7Counter");
            this.suitStack1 = obj.getInt("suitStack1Counter");
            this.suitStack2 = obj.getInt("suitStack2Counter");
            this.suitStack3 = obj.getInt("suitStack3Counter");
            this.suitStack4 = obj.getInt("suitStack4Counter");
            this.talonStack = obj.getInt("talonStackCounter");
            this.pileStack = obj.getInt("pileStackCounter");
            this.moveSameColorError = obj.getInt("colorErrorCounter");
            this.moveWrongNumberError = obj.getInt("numberErrorCounter");
            this.hintButtonCount = obj.getInt("hintCounter");
            this.undoButtonCount = obj.getInt("undoCounter");
            this.betaError = obj.getInt("betaErrorCounter");
            this.gameseed = obj.getInt("seed");
            this.score = obj.getLong("score");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public GamePlayed(int personID, int gameTime, boolean isSolved, int countTroughPile, int avgMotorTime, int buildStack1, int buildStack2, int buildStack3, int buildStack4, int buildStack5, int buildStack6, int buildStack7, int suitStack1, int suitStack2, int suitStack3, int suitStack4, int talonStack, int pileStack, int moveSameColorError, int moveWrongNumberError, int hintButtonCount, int undoButtonCount, int betaError, int gameseed, long score) {
        this.personID = personID;
        this.gameTime = gameTime;
        this.isSolved = isSolved;
        this.countTroughPile = countTroughPile;
        this.avgMotorTime = avgMotorTime;
        this.buildStack1 = buildStack1;
        this.buildStack2 = buildStack2;
        this.buildStack3 = buildStack3;
        this.buildStack4 = buildStack4;
        this.buildStack5 = buildStack5;
        this.buildStack6 = buildStack6;
        this.buildStack7 = buildStack7;
        this.suitStack1 = suitStack1;
        this.suitStack2 = suitStack2;
        this.suitStack3 = suitStack3;
        this.suitStack4 = suitStack4;
        this.talonStack = talonStack;
        this.pileStack = pileStack;
        this.moveSameColorError = moveSameColorError;
        this.moveWrongNumberError = moveWrongNumberError;
        this.hintButtonCount = hintButtonCount;
        this.undoButtonCount = undoButtonCount;
        this.betaError = betaError;
        this.gameseed = gameseed;
        this.score = score;
    }

    public GamePlayed(int id, int personID, int gameTime, boolean isSolved, int countTroughPile, int avgMotorTime, int buildStack1, int buildStack2, int buildStack3, int buildStack4, int buildStack5, int buildStack6, int buildStack7, int suitStack1, int suitStack2, int suitStack3, int suitStack4, int talonStack, int pileStack, int moveSameColorError, int moveWrongNumberError, int hintButtonCount, int undoButtonCount, int betaError) {
        this.id = id;
        this.personID = personID;
        this.gameTime = gameTime;
        this.isSolved = isSolved;
        this.countTroughPile = countTroughPile;
        this.avgMotorTime = avgMotorTime;
        this.buildStack1 = buildStack1;
        this.buildStack2 = buildStack2;
        this.buildStack3 = buildStack3;
        this.buildStack4 = buildStack4;
        this.buildStack5 = buildStack5;
        this.buildStack6 = buildStack6;
        this.buildStack7 = buildStack7;
        this.suitStack1 = suitStack1;
        this.suitStack2 = suitStack2;
        this.suitStack3 = suitStack3;
        this.suitStack4 = suitStack4;
        this.talonStack = talonStack;
        this.pileStack = pileStack;
        this.moveSameColorError = moveSameColorError;
        this.moveWrongNumberError = moveWrongNumberError;
        this.hintButtonCount = hintButtonCount;
        this.undoButtonCount = undoButtonCount;
        this.betaError = betaError;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public int getCountTroughPile() {
        return countTroughPile;
    }

    public void setCountTroughPile(int countTroughPile) {
        this.countTroughPile = countTroughPile;
    }

    public int getAvgMotorTime() {
        return avgMotorTime;
    }

    public void setAvgMotorTime(int avgMotorTime) {
        this.avgMotorTime = avgMotorTime;
    }

    public int getBuildStack1() {
        return buildStack1;
    }

    public void setBuildStack1(int buildStack1) {
        this.buildStack1 = buildStack1;
    }

    public int getBuildStack2() {
        return buildStack2;
    }

    public void setBuildStack2(int buildStack2) {
        this.buildStack2 = buildStack2;
    }

    public int getBuildStack3() {
        return buildStack3;
    }

    public void setBuildStack3(int buildStack3) {
        this.buildStack3 = buildStack3;
    }

    public int getBuildStack4() {
        return buildStack4;
    }

    public void setBuildStack4(int buildStack4) {
        this.buildStack4 = buildStack4;
    }

    public int getBuildStack5() {
        return buildStack5;
    }

    public void setBuildStack5(int buildStack5) {
        this.buildStack5 = buildStack5;
    }

    public int getBuildStack6() {
        return buildStack6;
    }

    public void setBuildStack6(int buildStack6) {
        this.buildStack6 = buildStack6;
    }

    public int getBuildStack7() {
        return buildStack7;
    }

    public void setBuildStack7(int buildStack7) {
        this.buildStack7 = buildStack7;
    }

    public int getSuitStack1() {
        return suitStack1;
    }

    public void setSuitStack1(int suitStack1) {
        this.suitStack1 = suitStack1;
    }

    public int getSuitStack2() {
        return suitStack2;
    }

    public void setSuitStack2(int suitStack2) {
        this.suitStack2 = suitStack2;
    }

    public int getSuitStack3() {
        return suitStack3;
    }

    public void setSuitStack3(int suitStack3) {
        this.suitStack3 = suitStack3;
    }

    public int getSuitStack4() {
        return suitStack4;
    }

    public void setSuitStack4(int suitStack4) {
        this.suitStack4 = suitStack4;
    }

    public int getTalonStack() {
        return talonStack;
    }

    public void setTalonStack(int talonStack) {
        this.talonStack = talonStack;
    }

    public int getPileStack() {
        return pileStack;
    }

    public void setPileStack(int pileStack) {
        this.pileStack = pileStack;
    }

    public int getMoveSameColorError() {
        return moveSameColorError;
    }

    public void setMoveSameColorError(int moveSameColorError) {
        this.moveSameColorError = moveSameColorError;
    }

    public int getMoveWrongNumberError() {
        return moveWrongNumberError;
    }

    public void setMoveWrongNumberError(int moveWrongNumberError) {
        this.moveWrongNumberError = moveWrongNumberError;
    }

    public int getHintButtonCount() {
        return hintButtonCount;
    }

    public void setHintButtonCount(int hintButtonCount) {
        this.hintButtonCount = hintButtonCount;
    }

    public int getUndoButtonCount() {
        return undoButtonCount;
    }

    public void setUndoButtonCount(int undoButtonCount) {
        this.undoButtonCount = undoButtonCount;
    }

    public int getBetaError() {
        return betaError;
    }

    public void setBetaError(int betaError) {
        this.betaError = betaError;
    }

    public int getGameseed() {
        return gameseed;
    }

    public void setGameseed(int gameseed) {
        this.gameseed = gameseed;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

}

