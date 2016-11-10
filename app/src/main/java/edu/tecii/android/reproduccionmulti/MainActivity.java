package edu.tecii.android.reproduccionmulti;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button bGrabar, bDetener, bReproducir;
   private static final String LOG_TAG = "Grabadora"; // etiqueta para la gravadora
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String fichero = Environment.// obtener la ruta del archivo, crear el archivo temporal 3gp
            getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bGrabar = (Button) findViewById(R.id.bGrabar);
        bDetener = (Button) findViewById(R.id.bDetener);
        bReproducir = (Button) findViewById(R.id.bReproducir);
        bDetener.setEnabled(false);
        bReproducir.setEnabled(false);
    }

    public void grabar(View view) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOutputFile(fichero);// utiliza la ruta a reproducir
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.
                OutputFormat.THREE_GPP);// formato del archivo 3gp
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.
                AMR_NB);
        try {
            mediaRecorder.prepare();// preparar el dispositivo para empezar a gravar
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fallo en grabación");
        }
        mediaRecorder.start();// empieza la gravacion
        bGrabar.setEnabled(false);// boton de grabar deshabilitado
        bDetener.setEnabled(true);
        bReproducir.setEnabled(false);
    }

    public void detenerGrabacion(View view) {
        mediaRecorder.stop();
        mediaRecorder.release();//liberar recursos
        bGrabar.setEnabled(true);
        bDetener.setEnabled(false);
        bReproducir.setEnabled(true);
    }

    public void reproducir(View view) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fichero);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fallo en reproducción");
        }
    }

}
