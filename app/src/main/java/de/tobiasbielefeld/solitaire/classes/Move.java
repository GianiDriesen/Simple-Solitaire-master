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
    private MoveType type;
    private int time;
    private int gameid;

    public Move(){

    }

    public Move(MoveType type, int time, int gameid)
    {
        this.type = type;
        this.time = time;
        this.gameid = gameid;
    }

    public Move(JSONObject obj) {
        try {
            this.id = obj.getInt("id");
            this.type = MoveType.valueOf(obj.getString("type"));
            this.time = obj.getInt("time");
            this.gameid = obj.getInt("gameid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MoveType getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public int getGameid() {
        return gameid;
    }
}
