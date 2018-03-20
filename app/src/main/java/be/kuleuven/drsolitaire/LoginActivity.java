//@GN

package be.kuleuven.drsolitaire;

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

import be.kuleuven.drsolitaire.classes.Person;
import be.kuleuven.drsolitaire.helper.EntityMapper;
import be.kuleuven.drsolitaire.ui.GameSelector;

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
        SharedPreferences pref = getSharedPreferences(SharedData.PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(SharedData.PREF_USERNAME, null);
        String password = pref.getString(SharedData.PREF_PASSWORD, null);

        Log.d("DB","Username: "+username);
        Log.d("DB","Password: "+password);

        if (username != null || password != null) {
            isAutoLogin=true;
            SharedData.getEntityMapper().getpMapper().getPersonByUsernameAndPassword(username,password);
            new GetPerson().execute();
        }
    }

    public void loginApp(View view) {
        SharedData.getEntityMapper().getpMapper().getPersonByUsernameAndPassword(username.getText().toString(),password.getText().toString()); //TODO bypassed login here
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
                person = SharedData.getEntityMapper().person;
                SharedData.getEntityMapper().dataGrabbed();
            }
            return person;
        }

        protected void onPostExecute(Person person) {
            if (person != null) {
                if (password.getText().toString().equals(person.getPassword()) || isAutoLogin) {
                    Intent intent = new Intent(LoginActivity.this, GameSelector.class);
                    SharedData.user = person;
                    getSharedPreferences(SharedData.PREFS_NAME,MODE_PRIVATE)
                            .edit()
                            .putString(SharedData.PREF_USERNAME, person.getUsername())
                            .putString(SharedData.PREF_PASSWORD, person.getPassword())
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
