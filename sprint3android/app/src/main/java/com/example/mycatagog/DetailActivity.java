package com.example.mycatagog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // diseño para que la interfaz ocupe toda la pantalla, incluyendo áreas debajo de las barras del sistema
        EdgeToEdge.enable(this);

        //el layout principal de la actividad
        setContentView(R.layout.activity_detail);

        // listener para manejar los márgenes de la ventana y ajustarlos
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // obtener los márgenes de las barras del sistema.
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // establecer los márgenes de la vista principal
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;  // Devolver los insets modificados.
        });

        // Obtener la orientación actual de la pantalla
        int orientation = getResources().getConfiguration().orientation;

        // si es horizontal cambiar el layout a "detail_activity.xml".
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.detail_activity);
        else
            // si es vertical usar "activity_detail.xml".
            setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.doggo_img);

        // usar Picasso para cargar la imagen
        Picasso.get()
                .load(R.drawable.doggo)  // cargar la imagen
                .transform(new CircleTransformImg())  // aplicar la transformación circular
                .into(imageView);  // colocar la imagen en el ImageView.
    }
}
