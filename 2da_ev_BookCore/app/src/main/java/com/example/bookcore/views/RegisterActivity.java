package com.example.bookcore.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookcore.R;
import com.example.bookcore.viewModels.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {

    private final RegisterViewModel registerViewModel = new RegisterViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name = findViewById(R.id.editTextName);
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        EditText password2 = findViewById(R.id.editTextPassword2);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText address = findViewById(R.id.editTextAddress);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String password2Text = password2.getText().toString();
                String phoneText = phone.getText().toString();
                String addressText = address.getText().toString();

                registerViewModel.validateAndRegister(nameText, emailText, passwordText, password2Text, phoneText, addressText);
            }
        });

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


        registerViewModel.getValidationMessage().observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        registerViewModel.getRegistrationStatus().observe(this, isRegistered -> {
            if (isRegistered != null) {
                if (isRegistered == 's') {
                    Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else if (isRegistered == 'f') {
                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}





































/*
public class RegisterActivity extends AppCompatActivity {
    Context context = this;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.backButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(context,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndRegisterUser();
            }
        });

    }

    private void validateAndRegisterUser(){
        EditText name = (EditText) findViewById(R.id.editTextName);
        EditText email = (EditText) findViewById(R.id.editTextEmail);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        EditText password2 = (EditText) findViewById(R.id.editTextPassword2);
        EditText phone = (EditText) findViewById(R.id.editTextPhone);
        EditText address = (EditText) findViewById(R.id.editTextAddress);

        String nameString = name.getText().toString();
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String password2String = password2.getText().toString();
        String phoneString = phone.getText().toString();
        String addressString = address.getText().toString();

        if (nameString.isEmpty()){
            Toast.makeText(context, "Error: Name is missing", Toast.LENGTH_LONG).show();
            name.setText("");
            name.requestFocus();
            return;
        }

        //Patterns.EMAIL_ADDRESS

        if (!emailString.contains("@")){
            if (emailString.isEmpty())
                Toast.makeText(context, "Error: Email is missing", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Error: Email not valid", Toast.LENGTH_LONG).show();
            email.setText("");
            email.requestFocus();
            return;
        }

        if (!passwordString.equals(password2String) || passwordString.isEmpty()){
            Toast.makeText(context, "Error: Password don't match", Toast.LENGTH_LONG).show();
            password.setText("");
            password2.setText("");
            password.requestFocus();
            return;
        }

        if (phoneString.length() !=9){
            Toast.makeText(context, "Error: Phone number is missing", Toast.LENGTH_LONG).show();
            phone.setText("");
            phone.requestFocus();
            return;
        } else {
            try {
                Integer.parseInt(phoneString);
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Error: Phone number not valid", Toast.LENGTH_LONG).show();
                phone.setText("");
                phone.requestFocus();
                return;
            }
        }

        if (addressString.isEmpty()){
            Toast.makeText(context, "Error: Address is missing", Toast.LENGTH_LONG).show();
            address.setText("");
            address.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("name", nameString);
                        userMap.put("phone", phoneString);
                        userMap.put("address", addressString);

                        String userId = mAuth.getCurrentUser().getUid();
                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("users");

                        databaseRef.child(userId)
                                .setValue(userMap)
                                .addOnCompleteListener(databaseTask -> {
                                    if (databaseTask.isSuccessful()) {
                                        Toast.makeText(context, "User registered and data saved succesfully.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "User registered, error when saving data: " + databaseTask.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        Toast.makeText(context, "Error while registering: " + task.getException(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
        Intent loginIntent = new Intent(context, LoginActivity.class);
        startActivity(loginIntent);
    }


}
*/