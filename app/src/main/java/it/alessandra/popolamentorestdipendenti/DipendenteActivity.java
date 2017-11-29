package it.alessandra.popolamentorestdipendenti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DipendenteActivity extends AppCompatActivity {

    TextView infoDip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dipendente);

        infoDip = findViewById(R.id.dipendente);
        Azienda azienda = new Azienda();
        azienda = (Azienda) InternalStorage.readObject(this,"FileAzienda");
        Dipendente dipendente = new Dipendente();
        Intent i = getIntent();
        String matricola = i.getStringExtra("MATRICOLA");
        dipendente = azienda.getDipendenteFromMatricola(matricola);
        String dettagli = "Nome: " + dipendente.getNome() + "\nCognome: " + dipendente.getCognome();
        infoDip.setText(dettagli);
    }
}
