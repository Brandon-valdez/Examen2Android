package sv.edu.itca.mp_piano;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class pianoinstrumentos extends AppCompatActivity implements View.OnClickListener {

    // Constantes para los instrumentos

    private static final int GUITARRA = 0;
    private static final int VIOLIN = 1;
    private static final int TROMPETA = 2;
    private static final int FLAUTA = 3;
    private static final int BAJO = 4;
    private static final int ORGANO = 5;
    private static final int SAXOFON = 6;
    private static final int XILOFONO = 7;



    private String nombreIns = "piano"; // Instrumento por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pianoinstrumentos);

        configurarSpinner();
        iniciarBotones();
    }

    private void iniciarBotones() {
        // Botones de teclas blancas
        findViewById(R.id.btnGuitar).setOnClickListener(this);
        findViewById(R.id.btntambor).setOnClickListener(this);
        findViewById(R.id.btnviolin).setOnClickListener(this);
        findViewById(R.id.btntompeta).setOnClickListener(this);
        findViewById(R.id.btnflauta).setOnClickListener(this);
        findViewById(R.id.btnArmonica).setOnClickListener(this);
        findViewById(R.id.btnharp).setOnClickListener(this);

        // Botón aplicar
        Button btnAplicar = findViewById(R.id.btnAplicar);
        btnAplicar.setOnClickListener(v -> aplicarInstrumento());
    }

    private void configurarSpinner() {
        Spinner spInstrumentos = findViewById(R.id.spInstrumentos);
        int itemSeleccionado = spInstrumentos.getSelectedItemPosition();

     if (itemSeleccionado == GUITARRA) {
            nombreIns = "guitarra";
        } else if (itemSeleccionado == VIOLIN) {
            nombreIns = "violin";
        } else if (itemSeleccionado == TROMPETA) {
            nombreIns = "trompeta";
        } else if (itemSeleccionado == FLAUTA) {
            nombreIns = "flauta";
        } else if (itemSeleccionado == BAJO) {
            nombreIns = "bajo";
        } else if (itemSeleccionado == ORGANO) {
            nombreIns = "organo";
        } else if (itemSeleccionado == SAXOFON) {
            nombreIns = "saxofon";
        } else if (itemSeleccionado == XILOFONO) {
            nombreIns = "xilofono";
        }
    }





    private void aplicarInstrumento() {
        configurarSpinner();
        Toast.makeText(this, "Instrumento: " + nombreIns, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnGuitar) playC(view);
        else if (id == R.id.btntambor) playD(view);
        else if (id == R.id.btnviolin) playE(view);
        else if (id == R.id.btntompeta) playF(view);
        else if (id == R.id.btnflauta) playG(view);
        else if (id == R.id.btnArmonica) playA(view);
        else if (id == R.id.btnharp) playB(view);

        if (view instanceof Button) aplicarEfectoVisual((Button) view);
    }

    // MÉTODOS PARA REPRODUCIR NOTAS
    public void playC(View view) {
        reproducirNota("c4"); //ya
        mostrarToast("DO");
    }

    public void playD(View view) {
        reproducirNota("d4");//
        mostrarToast("RE");
    }

    public void playE(View view) {
        reproducirNota("e4");
        mostrarToast("MI");
    }

    public void playF(View view) {
        reproducirNota("f4");
        mostrarToast("FA");
    }

    public void playG(View view) {
        reproducirNota("g4");
        mostrarToast("SOL");
    }

    public void playA(View view) {
        reproducirNota("a4");
        mostrarToast("LA");
    }

    public void playB(View view) {
        reproducirNota("b4");
        mostrarToast("SI");
    }

    // REPRODUCIR NOTA MÉTODO
    private void reproducirNota(String nota) {
        String nombreRecurso = nombreIns + "_" + nota;
        int idRecurso = getResources().getIdentifier(nombreRecurso, "raw", getPackageName());

        if (idRecurso != 0) {
            MediaPlayer mp = MediaPlayer.create(this, idRecurso);
            if (mp != null) {
                mp.start();
                // Liberar recursos al terminar
                mp.setOnCompletionListener(MediaPlayer::release);
            }
        } else {
            Toast.makeText(this, "Sonido no encontrado: " + nombreRecurso, Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarToast(String nota) {
        Toast.makeText(this, "♪ " + nota + " ♪", Toast.LENGTH_SHORT).show();
    }

    private void aplicarEfectoVisual(Button boton) {
        boton.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> boton.animate().scaleX(1f).scaleY(1f).setDuration(100).start())
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // -------------------- MENU --------------------
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
                    .setItems(new String[]{"Piano Principal","Piano  de Instrumentos", "Piano de Animales"}, (dialog, which) -> {
                        if (which == 0) {
                            startActivity(new Intent(this, MainActivity.class));
                            finish();

                        } else if (which == 1) {
                            mostrarMensaje("Se encuentra en ella");
                        }
                        else{
                            startActivity(new Intent(this, pianoselva.class));
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        } else if (itemId == R.id.grupo) {
            startActivity(new Intent(this, Participantes.class));
            finish();
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