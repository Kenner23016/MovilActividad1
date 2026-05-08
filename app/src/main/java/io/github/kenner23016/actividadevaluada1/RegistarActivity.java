package io.github.kenner23016.actividadevaluada1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegistarActivity extends AppCompatActivity {

    EditText edtNombreUsuario, edtEmail, edtPasswordRegistro, edtConfirmarPassword;
    Button btnGuardarUsuario, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        Toolbar toolbarRegistro = findViewById(R.id.toolbarRegistro);
        setSupportActionBar(toolbarRegistro);

        edtNombreUsuario = findViewById(R.id.edtNombreUsuario);
        edtEmail = findViewById(R.id.edtEmail);
        edtPasswordRegistro = findViewById(R.id.edtPasswordRegistro);
        edtConfirmarPassword = findViewById(R.id.edtConfirmarPassword);
        btnGuardarUsuario = findViewById(R.id.btnGuardarUsuario);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnGuardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarUsuario();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void guardarUsuario() {
        String usuario = edtNombreUsuario.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPasswordRegistro.getText().toString().trim();
        String confirmarPassword = edtConfirmarPassword.getText().toString().trim();

        if (usuario.length() < 3) {
            Toast.makeText(this, "El usuario debe tener al menos 3 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Ingrese un email correcto", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 5) {
            Toast.makeText(this, "El password debe tener al menos 5 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if (!esAlfanumerico(password)) {
            Toast.makeText(this, "El password debe ser alfanumérico", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmarPassword)) {
            Toast.makeText(this, "El password no fue confirmado correctamente", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(usuario + "_usuario", usuario);
        editor.putString(usuario + "_email", email);
        editor.putString(usuario + "_password", password);
        editor.apply();

        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();

        limpiarCampos();
    }

    private boolean esAlfanumerico(String texto) {
        return texto.matches("[a-zA-Z0-9]+");
    }

    private void limpiarCampos() {
        edtNombreUsuario.setText("");
        edtEmail.setText("");
        edtPasswordRegistro.setText("");
        edtConfirmarPassword.setText("");
        edtNombreUsuario.requestFocus();
    }
}