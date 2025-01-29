
package com.example.bookcore.views;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookcore.R;
import com.example.bookcore.databinding.ActivityDetailBinding;
import com.example.bookcore.models.Book;
import com.example.bookcore.repositories.UserRepository;
import com.example.bookcore.viewModels.DetailViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private DetailViewModel detailViewModel;
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
//        binding = ActivityDetailBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        binding.setViewModel(detailViewModel);
        binding.setLifecycleOwner(this);

        String bookId = getIntent().getStringExtra("bookId");

//        UserRepository user = new UserRepository();
//        if (user.checkFav(bookId)){
//            Toast.makeText(DetailActivity.this, "esta en favs", Toast.LENGTH_SHORT).show();
//        } else Toast.makeText(DetailActivity.this, "no esta en favs", Toast.LENGTH_SHORT).show();

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(DetailActivity.this, DashboardActivity.class);
                startActivity(dashboardIntent);
            }
        });

        findViewById(R.id.favButton).setOnClickListener(v -> {
            detailViewModel.addFavourite(bookId);
            Toast.makeText(DetailActivity.this, "Added to Favorites! id: " + bookId, Toast.LENGTH_SHORT).show();
        });


        if (bookId != null) {
            detailViewModel.loadBook(bookId);

            ImageView coverImg = findViewById(R.id.coverImageView);

            detailViewModel.getDetailLiveData().observe(this, new Observer<Book>() {
                @Override
                public void onChanged(Book book) {
                    Log.d(TAG,"AAAAAAAAAAAAAAAAAA detail book id: " + book.getId());
                    int width = 670;
                    Picasso.get().load(book.getCover_url()).resize(width, (int) (width * 1.6)).into(coverImg);
                }
            });
        }

    }
}















//public class DetailActivity extends AppCompatActivity {
//    private Context context = this;
//    private ImageView coverImg;
//    private TextView titleView;
//    private TextView authorView;
//    private TextView synopsisView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_detail);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        coverImg = findViewById(R.id.coverImageView);
//        titleView = findViewById(R.id.titleTextView);
//        authorView = findViewById(R.id.authorTextView);
//        synopsisView = findViewById(R.id.synopsisTextView);
//
//        titleView.setText(getIntent().getStringExtra("title"));
//        authorView.setText(getIntent().getStringExtra("author"));
//        synopsisView.setText(getIntent().getStringExtra("synopsis"));
//        int width = 670;
//        Picasso.get()
//                .load(getIntent().getStringExtra("url"))
//                .resize(width, (int) (width * 1.6))
//                .into(coverImg);
//
//
////        Button logoutButton = findViewById(R.id.logoutButton);
////        logoutButton.setOnClickListener(view -> {
////            FirebaseAuth.getInstance().signOut();
////            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
////            startActivity(new Intent(context, LoginActivity.class));
////        });
//    }
//
//}






/*

public class DetailActivity extends AppCompatActivity {
    private Context context = this;
    private String coverUrl;
    private String title;
    private String author;
    private String synopsis;
    private ImageView coverImg;
    private TextView titleView;
    private TextView authorView;
    private TextView synopsisView;


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
        synopsisView = (TextView) findViewById(R.id.synopsisTextView);

        fetchData();

        //findViewById(R.id.randomButton).setOnClickListener(v -> fetchData());


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
                synopsis = dataSnapshot.child("synopsis").getValue(String.class);

                Log.d("Firebase", "Fetched: " + title);

                int width = 670;
                Picasso.get()
                        .load(coverUrl)
                        .resize(width, (int) (width * 1.6))
                        .into(coverImg);

                titleView.setText(title);
                authorView.setText(author);
                synopsisView.setText(synopsis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "Error reading data", databaseError.toException());
            }
        };

        databaseRef.addListenerForSingleValueEvent(bookListener);
    }
}

*/