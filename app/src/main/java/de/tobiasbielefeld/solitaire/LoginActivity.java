//@GN

package de.tobiasbielefeld.solitaire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import de.tobiasbielefeld.solitaire.classes.Person;
import de.tobiasbielefeld.solitaire.helper.EntityMapper;
import de.tobiasbielefeld.solitaire.ui.GameSelector;

import static de.tobiasbielefeld.solitaire.SharedData.PREFS_NAME;
import static de.tobiasbielefeld.solitaire.SharedData.PREF_PASSWORD;
import static de.tobiasbielefeld.solitaire.SharedData.PREF_USERNAME;
import static de.tobiasbielefeld.solitaire.SharedData.getEntityMapper;

// @GN
public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EntityMapper entityMapper = SharedData.getEntityMapper();
    private boolean isAutoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupContent();
        RequestQueue rq = Volley.newRequestQueue(this.getApplicationContext());
        SharedData.getEntityMapper().setRequestQueue(rq);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        Log.d("DB","Username: "+username);
        Log.d("DB","Password: "+password);

        if (username != null || password != null) {
            isAutoLogin=true;
            getEntityMapper().getpMapper().getPersonByUsernameAndPassword(username,password);
            new GetPerson().execute();
        }
    }

    public void loginApp(View view) {
        getEntityMapper().getpMapper().getPersonByUsernameAndPassword(username.getText().toString(),password.getText().toString()); //TODO bypassed login here
        //getEntityMapper().getpMapper().getPersonByUsernameAndPassword("Karsten","azerty");
        new GetPerson().execute();
    }

    public void skipApp(View view) {
        Intent intent = new Intent(LoginActivity.this, GameSelector.class);
        startActivity(intent);

    }

    public void registerApp(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    private void setupContent(){
        username = (EditText) findViewById(R.id.username);
        password  = (EditText) findViewById(R.id.password);
    }

    private class GetPerson extends AsyncTask<Void, Void, Person> {
        protected Person doInBackground(Void... voids) {
            Person person = new Person();
            while (!entityMapper.dataReady()) {
                if (isCancelled()) break;
            }
            if (entityMapper.dataReady()) {
                person = getEntityMapper().person;
                getEntityMapper().dataGrabbed();
            }
            return person;
        }

        protected void onPostExecute(Person person) {
            if (person != null) {
                if (password.getText().toString().equals(person.getPassword()) || isAutoLogin) {
                    Intent intent = new Intent(LoginActivity.this, GameSelector.class);
                    SharedData.user = person;
                    getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                            .edit()
                            .putString(PREF_USERNAME, person.getUsername())
                            .putString(PREF_PASSWORD, person.getPassword())
                            .commit();
                    startActivity(intent);
                    finish();
                    isAutoLogin = false;
                } else {
                    Toast.makeText(LoginActivity.this, "Login credentials are wrong. ", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(LoginActivity.this, "Login credentials are wrong. ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
