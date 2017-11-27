package de.tobiasbielefeld.solitaire.classes;

import java.sql.Date;

/**
 * Class representation of Games Played by Persons.
 */
public class GamePlayed {

    private int id;
    private int personID;
    private Date startTime;
    private Date endTime;
    private int isSolved;
    private int moves;
    private int countTroughPile;
    private int avgIdleTime;
    private int avgSwipeTime;
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
    private int randomTapCounter;
    private int movePileToSuitError;
    private int movePileToBuildError;
    private int moveBuildToBuildError;
    private int moveSameColorError;
    private int moveWrongNumberError;
    private int ignoreAceTime;
    private int hintButtonCount;
    private int undoButtonCount;
    private int badPrecisionCounter;
    private int betaError;


    public GamePlayed() {
    }

    public GamePlayed(int id, int personID, Date startTime, Date endTime, int isSolved, int moves, int countTroughPile, int avgIdleTime, int avgSwipeTime,
                      int buildStack1, int buildStack2, int buildStack3, int buildStack4, int buildStack5, int buildStack6, int buildStack7, int suitStack1,
                      int suitStack2, int suitStack3, int suitStack4, int talonStack, int pileStack, int randomTapCounter, int movePileToSuitError,
                      int movePileToBuildError, int moveBuildToBuildError, int moveSameColorError, int moveWrongNumberError, int ignoreAceTime,
                      int hintButtonCount, int undoButtonCount, int badPrecisionCounter, int betaError) {
        this.id = id;
        this.personID = personID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isSolved = isSolved;
        this.moves = moves;
        this.countTroughPile = countTroughPile;
        this.avgIdleTime = avgIdleTime;
        this.avgSwipeTime = avgSwipeTime;
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
        this.randomTapCounter = randomTapCounter;
        this.movePileToSuitError = movePileToSuitError;
        this.movePileToBuildError = movePileToBuildError;
        this.moveBuildToBuildError = moveBuildToBuildError;
        this.moveSameColorError = moveSameColorError;
        this.moveWrongNumberError = moveWrongNumberError;
        this.ignoreAceTime = ignoreAceTime;
        this.hintButtonCount = hintButtonCount;
        this.undoButtonCount = undoButtonCount;
        this.badPrecisionCounter = badPrecisionCounter;
        this.betaError = betaError;
    }

    public GamePlayed(int personID, Date startTime, Date endTime, int isSolved, int moves, int countTroughPile, int avgIdleTime, int avgSwipeTime,
                      int buildStack1, int buildStack2, int buildStack3, int buildStack4, int buildStack5, int buildStack6, int buildStack7,
                      int suitStack1, int suitStack2, int suitStack3, int suitStack4, int talonStack, int pileStack, int randomTapCounter,
                      int movePileToSuitError, int movePiltToBuildError, int moveBuildToBuildError, int moveSameColorError, int moveWrongNumberError,
                      int ignoreAceTime, int hintButtonCount, int undoButtonCount, int badPrecisionCounter, int betaError) {

        this.personID = personID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isSolved = isSolved;
        this.moves = moves;
        this.countTroughPile = countTroughPile;
        this.avgIdleTime = avgIdleTime;
        this.avgSwipeTime = avgSwipeTime;
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
        this.randomTapCounter = randomTapCounter;
        this.movePileToSuitError = movePileToSuitError;
        this.movePileToBuildError = movePiltToBuildError;
        this.moveBuildToBuildError = moveBuildToBuildError;
        this.moveSameColorError = moveSameColorError;
        this.moveWrongNumberError = moveWrongNumberError;
        this.ignoreAceTime = ignoreAceTime;
        this.hintButtonCount = hintButtonCount;
        this.undoButtonCount = undoButtonCount;
        this.badPrecisionCounter = badPrecisionCounter;
        this.betaError = betaError;
    }

    public int getId() {
        return id;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(int isSolved) {
        this.isSolved = isSolved;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getCountTroughPile() {
        return countTroughPile;
    }

    public void setCountTroughPile(int countTroughPile) {
        this.countTroughPile = countTroughPile;
    }

    public int getAvgIdleTime() {
        return avgIdleTime;
    }

    public void setAvgIdleTime(int avgIdleTime) {
        this.avgIdleTime = avgIdleTime;
    }

    public int getAvgSwipeTime() {
        return avgSwipeTime;
    }

    public void setAvgSwipeTime(int avgSwipeTime) {
        this.avgSwipeTime = avgSwipeTime;
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

    public int getRandomTapCounter() {
        return randomTapCounter;
    }

    public void setRandomTapCounter(int randomTapCounter) {
        this.randomTapCounter = randomTapCounter;
    }

    public int getMovePileToSuitError() {
        return movePileToSuitError;
    }

    public void setMovePileToSuitError(int movePileToSuitError) {
        this.movePileToSuitError = movePileToSuitError;
    }

    public int getMovePileToBuildError() {
        return movePileToBuildError;
    }

    public void setMovePileToBuildError(int movePiltToBuildError) {
        this.movePileToBuildError = movePiltToBuildError;
    }

    public int getMoveBuildToBuildError() {
        return moveBuildToBuildError;
    }

    public void setMoveBuildToBuildError(int moveBuildToBuildError) {
        this.moveBuildToBuildError = moveBuildToBuildError;
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

    public int getIgnoreAceTime() {
        return ignoreAceTime;
    }

    public void setIgnoreAceTime(int ignoreAceTime) {
        this.ignoreAceTime = ignoreAceTime;
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

    public int getBadPrecisionCounter() {
        return badPrecisionCounter;
    }

    public void setBadPrecisionCounter(int badPrecisionCounter) {
        this.badPrecisionCounter = badPrecisionCounter;
    }

    public int getBetaError() {
        return betaError;
    }

    public void setBetaError(int betaError) {
        this.betaError = betaError;
    }

}