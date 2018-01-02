package de.tobiasbielefeld.solitaire.helper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

import de.tobiasbielefeld.solitaire.classes.Person;

/**
 * Contains standard methods for mappers
 */

// @GN
public enum EntityMapper {
    UNIQUEMAPPER;

    /** Singleton design applied; use only one mapper for each entity. */
    private final PersonMapper pMapper = PersonMapper.UNIQUEMAPPER;
    private final GameMapper gMapper = GameMapper.UNIQUEMAPPER;

    private void initiateSingletons() {
        this.pMapper.setEntityMapper(this);
        this.gMapper.setEntityMapper(this);
    }
    private RequestQueue requestQueue;
    public RequestQueue getRequestQueue() { return this.requestQueue; }
    public void setRequestQueue(RequestQueue rq) { this.requestQueue = rq; }
    /** Singleton design applied; return the single mappers to other mappers that need them. */
    public PersonMapper getpMapper() { return pMapper; }

    /**
     * Prepare one of each entity, and one list for each of those entities.
     * Each time an entity or a list of them is requested from the DB, start
     * asynchronously requesting and parsing them, and fill the aforementioned
     * empty entities or lists with the parsed ones. As soon as they are filled,
     * set the dataReady field "true" to indicate the entities can be grabbed.
     */
    public Person person;
    public List<Person> persons;
    private boolean dataReady = false;
    public boolean dataReady() { return this.dataReady; }
    public void dataGrabbed() {
        this.dataReady = false;
        person = null;
        persons = new LinkedList<>();
    }

    public void queryEntity(final Object obj, String url) {
        JsonArrayRequest json = new JsonArrayRequest(url
                , new Response.Listener<JSONArray>() { @Override
        public void onResponse(JSONArray response) {
            if (response.length() >= 1) {
                mapToEntity(response, obj);
            } else {
                dataReady = true;
            }
        } }
                , new Response.ErrorListener() { @Override
        public void onErrorResponse(VolleyError error) { error.printStackTrace(); } });
        requestQueue.add(json);
    }

    public void queryEntities(final Object obj, String url) {
        JsonArrayRequest json = new JsonArrayRequest(url
                , new Response.Listener<JSONArray>() { @Override
        public void onResponse(JSONArray response) {
            if (response.length() >= 1) {
                mapToEntities(response, obj);
            } else {
                dataReady = true;
            }
        } }
                , new Response.ErrorListener() { @Override
        public void onErrorResponse(VolleyError error) { error.printStackTrace(); } });
        requestQueue.add(json);
    }

    private void mapToEntities(JSONArray json, Object obj) {
        for (int i=0;i<json.length();i++) {
            try {if (obj instanceof Person) {
                persons.add(new Person(json.getJSONObject(i)));
            }
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        this.dataReady = true;
    }

    private void mapToEntity(JSONArray json, Object obj) {
        try {if (obj instanceof Person) {
            person = new Person(json.getJSONObject(0));
        }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        this.dataReady = true;
    }

    // Easter egg
    @Override
    public String toString() { return "Up Up Down Down Left Right Left Right B A Start, I get infinite fans!"; }

    EntityMapper() {
        initiateSingletons();
        dataGrabbed();
    }
}