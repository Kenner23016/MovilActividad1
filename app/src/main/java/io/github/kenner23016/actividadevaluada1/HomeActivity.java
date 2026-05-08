package io.github.kenner23016.actividadevaluada1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbarHome = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbarHome);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        cargarFragment(new InicioFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navInicio) {
                cargarFragment(new InicioFragment());
                return true;
            }

            if (id == R.id.navProductos) {
                cargarFragment(new ProductosFragment());
                return true;
            }

            if (id == R.id.navPerfil) {
                cargarFragment(new PerfilFragment());
                return true;
            }

            return false;
        });
    }

    private void cargarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragment)
                .commit();
    }
}