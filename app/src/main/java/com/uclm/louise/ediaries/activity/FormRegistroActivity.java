package com.uclm.louise.ediaries.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.models.RegistroContext;
import com.uclm.louise.ediaries.data.requests.CreateUsuarioRequest;

import java.util.regex.Pattern;

public class FormRegistroActivity extends AppCompatActivity {

    private Button buttonNext;
    private MaterialToolbar topAppBar;

    private Button buttonAddPhoto;
    private ImageView imageView;
    private ActivityResultLauncher<String> imagePickerLauncher;
    private String fotoPath = null;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_registro);

        // -- SIGUIENTE (FormDPersonalesActivity) --
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep(fotoPath);
            }
        });

        // -- AÑADIR FOTO --
        buttonAddPhoto = findViewById(R.id.buttonAddPhoto);
        imageView = findViewById(R.id.imageView);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result != null){
                    fotoPath = result.toString();
                    Log.i("Info log", fotoPath);

                    // Mostar vista previa de la imagen seleccionada
                    Glide.with(FormRegistroActivity.this)
                            .load(result)
                            .diskCacheStrategy(DiskCacheStrategy.NONE) // Desactivar la caché
                            .skipMemoryCache(true) // No guardar en la memoria caché
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(imageView);
                }
            }
        });

        buttonAddPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openImagePicker();
            }
        });

        // -- VOLVER A MENU PRINCIPAL --
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormRegistroActivity.this, StartAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openImagePicker() {
        imagePickerLauncher.launch("image/*");
    }

    private void nextStep(String fotoPath) {

        TextInputEditText editTextName = findViewById(R.id.editTextName);
        TextInputEditText editTextLastName = findViewById(R.id.editTextLastName);
        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        // Obtener los datos introducidos en el formulario
        String nombre = editTextName.getText().toString();
        String apellidos = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Comprobar si se ha quedado algun campo obligatorio sin rellenar y que el email sea valido
        if(validFields(editTextName, editTextLastName, editTextEmail, editTextPassword) && validEmail(editTextEmail)){
            CreateUsuarioRequest usuarioRequest = new CreateUsuarioRequest(nombre, apellidos, email, password, fotoPath);

            RegistroContext registroContext = RegistroContext.getInstance();
            registroContext.setUsuarioRequest(usuarioRequest);

            Intent intent = new Intent(FormRegistroActivity.this, FormDpersonalesActivity.class);
            startActivity(intent);
            Log.i("Info log", "Siguiente");
        }
    }

    private boolean validEmail(TextInputEditText editTextEmail) {
        if(!Pattern.matches(EMAIL_REGEX, editTextEmail.getText().toString())){
            editTextEmail.setError("Email no válido");
            editTextEmail.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validFields(TextInputEditText... editTexts) {
        String messageError = "Este campo no puede estar vacío";

        for (TextInputEditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                editText.setError(messageError);
                editText.requestFocus();
                return false;
            }
        }
        return true;
    }


}
