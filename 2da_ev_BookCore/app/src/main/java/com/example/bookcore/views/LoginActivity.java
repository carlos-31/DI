package com.example.bookcore.views;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bookcore.R;
import com.example.bookcore.viewModels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new LoginViewModel();

        Button loginButton = findViewById(R.id.buttonLogIn);
        loginButton.setOnClickListener(v -> {
            String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else {
                loginViewModel.login(email, password);
            }
        });



        TextView myTextView = findViewById(R.id.textViewRegister);
        myTextView.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        loginViewModel.getWasSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                Log.d(TAG,"aaaaaaaa");
                Intent dashboardIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(dashboardIntent);
                finish();
            }
            else
                Toast.makeText(this, "Error while loging in", Toast.LENGTH_SHORT).show();
        });

        SharedPreferences sharedPref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        boolean darkMode = sharedPref.getBoolean("darkMode", false);
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //recreate(); // recrea la activity para que se aplique el tema sin reiniciar.
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //recreate(); // recrea la activity para que se aplique el tema sin reiniciar.
        }

    }
}















/*
public class LoginActivity extends AppCompatActivity {
    Context context = this;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mAuth = FirebaseAuth.getInstance();


        Button loginButton = findViewById(R.id.buttonLogIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        TextView myTextView = findViewById(R.id.textViewRegister);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(context, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    }

    private void loginUser() {
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        if (!email.isEmpty() && !password.isEmpty())
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Intent dashboardIntent = new Intent(context, DashboardActivity.class);
                            startActivity(dashboardIntent);
                        } else {
                            Toast.makeText(context, "Authentication error.", Toast.LENGTH_SHORT).show();
                        }
                    });
        else
            Toast.makeText(context, "Missing required information", Toast.LENGTH_SHORT).show();
    }



}

 */