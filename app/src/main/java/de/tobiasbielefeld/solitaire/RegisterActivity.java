//@GN

package de.tobiasbielefeld.solitaire;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.tobiasbielefeld.solitaire.classes.Person;
import de.tobiasbielefeld.solitaire.helper.EntityMapper;
import de.tobiasbielefeld.solitaire.ui.GameSelector;

import static de.tobiasbielefeld.solitaire.SharedData.getEntityMapper;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText age;
    private EditText gender;
    private EditText level;
    private EntityMapper entityMapper = SharedData.getEntityMapper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupContent();

    }

    private void setupContent(){
        username = (EditText) findViewById(R.id.username);
        password  = (EditText) findViewById(R.id.password);
        password2  = (EditText) findViewById(R.id.password2);
        age = (EditText) findViewById(R.id.age);
        gender = (EditText) findViewById(R.id.gender);
        level = (EditText) findViewById(R.id.level);
    }

    public void registerApp(View view) {
        if (password.getText().toString().equals(password2.getText().toString())) {
            getEntityMapper().getpMapper().getPersonByUsernameAndPassword(username.getText().toString(),password.getText().toString());
            new GetPerson().execute();
        }
        else {
            Toast.makeText(RegisterActivity.this, "Password doesn't match, please try again.", Toast.LENGTH_SHORT).show();
        }
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
                Toast.makeText(RegisterActivity.this, "This username already exists!", Toast.LENGTH_SHORT).show();
            }
            else {
                if (isInteger(age.getText().toString()) && isInteger(level.getText().toString()) && genderInRightFormat(gender.getText().toString().toLowerCase())) {
                    Toast.makeText(RegisterActivity.this, "Registration ok. Have fun!", Toast.LENGTH_SHORT).show();
                    person = new Person(username.getText().toString(),
                            password.getText().toString(),
                            Integer.parseInt(age.getText().toString()),
                            genderToBool(gender.getText().toString().toLowerCase()),
                            Integer.parseInt(level.getText().toString()), 0, 0, 0, 0, 0);
                    System.out.println("Person push to db: "+person);
                    entityMapper.getpMapper().createPerson(person);
                    new RegisterActivity.CreatePerson().execute();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Some fields are not correctly filled in", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private boolean genderInRightFormat(String gender) {
        if (gender.equals("man") || gender.equals("woman")) return true;
        else return false;
    }
    private boolean genderToBool(String gender) {
        if (gender.equals("man")) return true;
        else return false;
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private class CreatePerson extends AsyncTask<Void, Void, Person> {
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
                Intent intent = new Intent(RegisterActivity.this, GameSelector.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
