package de.tobiasbielefeld.solitaire.classes;

/**
 * Created by GianiPC on 26/02/2018.
 */

public enum MoveType {
    RELEASECARD("releaseCard"),
    TOUCHSTACK("touchStack");

    private String string;

    MoveType(String string) {
        this.string = string;
    }

    public String toString() {return this.string;}
}
