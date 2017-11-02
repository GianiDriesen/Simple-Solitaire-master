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
import de.tobiasbielefeld.solitaire.ui.GameSelector;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupContent();
    }

    public void loginApp(View view) {
        SharedData.getEntityMapper().getPersonMapper().getPersonByUsername(username.getText().toString());
        new CheckPerson().execute();

    }

    public void registerApp(View view) {
        Intent intent = new Intent(LoginActivity.this, GameSelector.class);
        startActivity(intent);

    }

    private void setupContent(){
        username = (EditText) findViewById(R.id.username);
        password  = (EditText) findViewById(R.id.password);
    }

    private class CheckPerson extends AsyncTask<Void, Void, Person> {
        protected Person doInBackground(Void... voids) {
            Person person = new Person();
            while (!SharedData.getEntityMapper().dataReceived) {
                if (isCancelled()) break;}
            if (SharedData.getEntityMapper().dataReceived) {
                person = SharedData.getEntityMapper().person;
                SharedData.getEntityMapper().dataGrabbed();
            }
            return person;
        }

        protected void onPostExecute(Person person) {
            if (person.getPassword() == password.getText().toString()) {
                Intent intent = new Intent(LoginActivity.this, GameSelector.class);
                startActivity(intent);
            }
            else {
                System.out.println("Login failed.");
            }
        }
    }
}
