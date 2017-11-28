package it.alessandra.popolamentorestdipendenti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView item;
    private Azienda azienda;
    private String[] arrayMatr;
    private List<Dipendente> dipendenti;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        item = findViewById(R.id.item);

        azienda = new Azienda();

        callRest("Dipendenti.json");
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.itemlist,R.id.item,arrayMatr);
        listView.setAdapter(arrayAdapter);
    }

    public void callRest(String url) {
        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    try {
                        dipendenti = JsonParse.getList(text);
                        arrayMatr = azienda.listaMatricole(dipendenti);
                        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.itemlist,R.id.item,arrayMatr);
                        //listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("MAIN_ACTIVITY", text);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

}
