package it.alessandra.popolamentorestdipendenti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements TaskDelegate{

    private ListView listView;
    private TextView item;
    private Azienda azienda;
    private List<String> arrayMatr;
    private List<Dipendente> dipendenti;
    private ArrayAdapter<String> arrayAdapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* String nomeFile = "File Azienda";
        InternalStorage.writeObject(this,nomeFile,azienda);*/


        listView = findViewById(R.id.listview);
        item = findViewById(R.id.item);

        azienda = new Azienda();
        dipendenti = new ArrayList<>();
        arrayMatr = azienda.listaMatricole();

        TaskDelegate delegate = this; //associo il contesto attuale all'oggetto interfaccia
        callRest(delegate);
        //callRest("Dipendenti.json");
/*      arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayMatr);
        listView.setAdapter(arrayAdapter);*/

        /*String nomeFile = "File Azienda";
         InternalStorage.writeObject(this,nomeFile,azienda);*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String matricola = listView.getItemAtPosition(position).toString();
                Intent i = new Intent(getApplicationContext(),DipendenteActivity.class);
                i.putExtra("MATRICOLA",matricola);
                startActivity(i);
            }
        });
    }

    public void callRest(/*String url*/ final TaskDelegate delegate) {
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading");
        dialog.show();
        FirebaseRestClient.get("Dipendenti.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    try {
                         dipendenti = JsonParse.getList(text);
                         //azienda.setDipendenti(dipendenti);
                        delegate.TaskCompletionResult("Caricamento completato");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("MAIN_ACTIVITY", text);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.TaskCompletionResult("Caricamento fallito");
            }
        });
    }

    @Override
    public void TaskCompletionResult(String result){
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        azienda.setDipendenti(dipendenti);
        arrayMatr = azienda.listaMatricole();
        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayMatr);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.itemlist,R.id.item,arrayMatr);
        listView.setAdapter(arrayAdapter);
        String nomeFile = "FileAzienda";
        InternalStorage.writeObject(this,nomeFile,azienda);
    }

}
