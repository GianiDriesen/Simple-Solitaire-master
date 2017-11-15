package de.tobiasbielefeld.solitaire;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.tobiasbielefeld.solitaire.classes.Person;
import de.tobiasbielefeld.solitaire.helper.Database;
import de.tobiasbielefeld.solitaire.helper.EntityMapper;
import de.tobiasbielefeld.solitaire.ui.GameSelector;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EntityMapper entityMapper = SharedData.getEntityMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupContent();
    }

    public void loginApp(View view) {
        new DatabaseLogin().execute();
    }

    public void registerApp(View view) {
        Intent intent = new Intent(LoginActivity.this, GameSelector.class);
        startActivity(intent);

    }

    private void setupContent(){
        username = (EditText) findViewById(R.id.username);
        password  = (EditText) findViewById(R.id.password);
    }

    private class DatabaseLogin extends AsyncTask<Void,Void,Void> {
        protected Void doInBackground(Void... voids) {
            Database.CONNECTION.makeConnection();
            return null;
        }

        protected void onPostExecute(Void Void) {
            new CheckPerson().execute();
        }
    }

    private class CheckPerson extends AsyncTask<Void, Void, Person> {
        protected Person doInBackground(Void... voids) {
            entityMapper.getPersonMapper().getPersonByUsername(username.getText().toString());
            Person person = new Person();
            while (!entityMapper.dataReceived) {
                if (isCancelled()) break;}
            if (entityMapper.dataReceived) {
                person = entityMapper.person;
                entityMapper.dataGrabbed();
            }
            return person;
        }

        protected void onPostExecute(Person person) {
            if (person.getPassword().equals(password.getText().toString())) {
                Intent intent = new Intent(LoginActivity.this, GameSelector.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this,"Login credentials are wrong. ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
