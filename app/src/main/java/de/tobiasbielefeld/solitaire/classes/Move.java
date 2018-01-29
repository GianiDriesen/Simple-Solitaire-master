package de.tobiasbielefeld.solitaire.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

/**
 * Created by karst on 15-Jan-18.
 * Containerclass for a move.
 */

public class Move {
    private int id;
    private String personID;
    private int time; //@KG TODO at the moment an integer need to change this
    private int gameseed;
    private int gameid;

    public Move(){

    }

    /*

     */
    public Move(String personID, int time, int gameseed, int gameid)
    {
        this.personID = personID;
        this.time = time;
        this.gameseed = gameseed;
        this.gameid = gameid;
    }

    public Move(JSONObject obj) {
        try {
            this.id = obj.getInt("id");
            this.personID = obj.getString("username");
            this.time = obj.getInt("time");
            this.gameseed = obj.getInt("age");
            this.gameid = obj.getInt("gameid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPersonID() {
        return personID;
    }

    public int getTime() {
        return time;
    }

    public int getGameseed() {
        return gameseed;
    }

    public int getGameid() {
        return gameid;
    }
}
