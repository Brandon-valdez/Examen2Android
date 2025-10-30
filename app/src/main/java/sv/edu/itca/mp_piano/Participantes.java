package sv.edu.itca.mp_piano;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Participantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_participantes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.piano) {
            new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert)
                    .setTitle("Selecciona un modo")
                    .setItems(new String[]{"Piano  de Instrumentos", "Piano de Animales"}, (dialog, which) -> {
                        if (which == 0) {
                            startActivity(new Intent(this, pianoinstrumentos.class));
                            finish();
                        } else {
                            startActivity(new Intent(this, pianoselva.class));
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        } else if (itemId == R.id.grupo) {
            mostrarMensaje("Se encuentra en ella");
            return true;
        } else if (itemId == R.id.salir) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
