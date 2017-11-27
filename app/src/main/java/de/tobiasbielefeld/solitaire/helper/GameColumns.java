package de.tobiasbielefeld.solitaire.helper;

/**
 * Created by GianiPC on 20/11/2017.
 */

public enum GameColumns {
    ID ("id"),
    PERSONID ("personID"),
    STARTTIME ("startTime"),
    ENDTIME("endTime"),
    ISSOLVED("isSolved"),
    MOVES("moves"),
    COUNTTROUGHPILE("countTroughPile"),
    AVGIDLETIME("avgIdleTime"),
    AVGSWIPETIME("avgSwipeTime"),
    BUILDSTACK1("buildStack1"), BUILDSTACK2("buildStack2"), BUILDSTACK3("buildStack3"), BUILDSTACK4("buildStack4"), BUILDSTACK5("buildStack5"), BUILDSTACK6("buildStack6"), BUILDSTACK7("buildStack7"),
    SUITSTACK1("suitStack1"), SUITSTACK2("suitStack2"), SUITSTACK3("suitStack3"), SUITSTACK4("suitStack4"),
    TALONSTACK("talonStack"),
    PILESTACK("pileStack"),
    RANDOMTAPCOUNTER("randomTapCounter"),
    MOVEPILETOSUITERROR("movePileToSuitError"),
    MOVEPILETOBUILDERROR("movePileToBuildError"),
    MOVEBUILDTOBUILDERROR("moveBuildToBuildError"),
    MOVESAMECOLORERROR("moveSameColorError"),
    MOVEWRONGNUMBERERROR("moveWrongNumberError"),
    IGNOREACETIME("ignoreAceTime"),
    HINTBUTTONCOUNT("hintButtonCount"),
    UNDOBUTTONCOUNT("undoButtonCount"),
    BADPRECISIONCOUNTER("badPrecisionCounter"),
    BETAERROR("betaError");

    private String colName;
    GameColumns(String colName) {
        this.colName = colName;
    }

    public String getColName() {
        return this.colName;
    }
}
