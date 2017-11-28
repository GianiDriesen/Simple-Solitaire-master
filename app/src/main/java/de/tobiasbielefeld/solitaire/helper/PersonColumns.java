package de.tobiasbielefeld.solitaire.helper;

/**
 * Created by GianiPC on 27/11/2017.
 */

// @GN
public enum PersonColumns {
    USERNAME ("username"),
    PASSWORD ("password"),
    AGE ("age"),
    GENDER("gender"),
    LEVEL("level"),
    AVGSCORE("avgScore"),
    AVGMOVES("avgTime"),
    GAMESSUCCES("gamesSucces"),
    GAMESFAILED("gamesFailed");


    private String colName;
    PersonColumns(String colName) {
        this.colName = colName;
    }

    public String getColName() {
        return this.colName;
    }
}
