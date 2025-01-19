package com.example.bookcore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class DashboardActivity extends AppCompatActivity {
    private Context context = this;
    private String coverUrl;
    private String title;
    private String author;
    private ImageView coverImg;
    private TextView titleView;
    private TextView authorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        coverImg = (ImageView) findViewById(R.id.coverImageView);
        titleView = (TextView) findViewById(R.id.titleTextView);
        authorView = (TextView) findViewById(R.id.authorTextView);

        fetchData();

        findViewById(R.id.randomButton).setOnClickListener(v -> fetchData());


        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
    }

    private void fetchData(){
        String num = String.valueOf(new Random().nextInt(12));

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books").child(num);

        ValueEventListener bookListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title = dataSnapshot.child("title").getValue(String.class);
                author = dataSnapshot.child("author").getValue(String.class);
                coverUrl = dataSnapshot.child("cover_url").getValue(String.class);

                Log.d("Firebase", "Fetched: " + title);

                int width = 670;
                Picasso.get()
                        .load(coverUrl)
                        .resize(width, (int) (width * 1.6))
                        .into(coverImg);

                titleView.setText(title);
                authorView.setText(author);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "Error reading data", databaseError.toException());
            }
        };

        databaseRef.addListenerForSingleValueEvent(bookListener);
    }
}