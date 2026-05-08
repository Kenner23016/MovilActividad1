package io.github.kenner23016.actividadevaluada1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuarioLogin, edtPasswordLogin;
    Button btnIngresar, btnSalirLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarLogin = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbarLogin);

        edtUsuarioLogin = findViewById(R.id.edtUsuarioLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalirLogin = findViewById(R.id.btnSalirLogin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarIngreso();
            }
        });

        btnSalirLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    private void validarIngreso() {
        String usuario = edtUsuarioLogin.getText().toString().trim();
        String password = edtPasswordLogin.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Debe ingresar usuario y password", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE);

        String usuarioGuardado = preferences.getString(usuario + "_usuario", "");
        String passwordGuardado = preferences.getString(usuario + "_password", "");

        if (usuario.equals(usuarioGuardado) && password.equals(passwordGuardado)) {
            Toast.makeText(this, "Bienvenido " + usuario, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        } else {
            Toast.makeText(this, "error de usuario y clave invadidos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuRegistrar) {
            Intent intent = new Intent(MainActivity.this, RegistarActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.menuSalir) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}