package com.example.mycatagog;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogFragment extends Fragment {

    public CatalogFragment() {

    }

    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_catalog, container, false);


        Button button = view.findViewById(R.id.buttonCatalogFragment);
        // esto "apila" el otro fragment. y al pisar de nuevo en menu se muestra detail insted de catalog
        //        button.setOnClickListener(v -> {
        //            // Navegamos directamente al fragmento "About" usando el NavController
        //            NavHostFragment.findNavController(CatalogFragment.this)
        //                    .navigate(R.id.page_detail);  // Usamos el ID del fragmento de destino
        //        });


        button.setOnClickListener(v -> {
            // usar popUpTo para evitar que se apile
            NavHostFragment.findNavController(CatalogFragment.this)
                    .navigate(R.id.page_detail, null,
                            new NavOptions.Builder()
                                    .setPopUpTo(R.id.page_catalogo, true)  // elimina el fragmento CatalogFragment de la pila
                                    .build());
        });

//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(requireContext(), DetailActivity.class);
//                //se usa requireContext() al ser un fragment y no una activity
//            startActivity(intent);
//            requireActivity().finish();
//        });      este no me sirve que detail lo cambie a un fragment y no es una actividad

        return view;
    }

}
