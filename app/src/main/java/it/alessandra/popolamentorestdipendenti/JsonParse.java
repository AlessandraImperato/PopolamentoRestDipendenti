package it.alessandra.popolamentorestdipendenti;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by utente7.academy on 28/11/2017.
 */

public class JsonParse {
    public static List<Dipendente> getList(String json) throws JSONException {
        List<Dipendente> dipendenti = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            Iterator<String> utenti = jsonObject.keys();


            while (utenti.hasNext()) {
                Dipendente dipendente = new Dipendente();
                String oneKey = utenti.next();
                JSONObject oneDip = jsonObject.getJSONObject(oneKey); // mi da dip01 al primo giro , dip02 al secondo e dip03 al terzo

                Iterator<String> dettagli = oneDip.keys();
                while (dettagli.hasNext()){
                    String oneKey2 = dettagli.next();
                    if(oneKey2.equals("nome")){
                        String nome = oneDip.getString(oneKey2);
                        dipendente.setNome(nome);
                    }
                    else if(oneKey2.equals("cognome")){
                        String cognome = oneDip.getString(oneKey2);
                        dipendente.setCognome(cognome);
                    }
                    else if(oneKey2.equals("matricola")){
                        String matricola = oneDip.getString(oneKey2);
                        dipendente.setMatricola(matricola);
                    }
                }
                dipendenti.add(dipendente);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
        return dipendenti;
    }

 /*   public static Dipendente genericoDipendente(String json){ // json del singolo dipendente: dipendente01,02 ecc
        Dipendente dipendente = new Dipendente();
        try {
            JSONObject jsonObject = new JSONObject(json); //{cognome: ..., matricola: ..., nome: .. }
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String urlKey = (String)iterator.next(); //contiene una volta il cognome,una la matricola e una il nome
                if(urlKey.equals("nome")){
                    String nome = jsonObject.getString(urlKey);
                    dipendente.setNome(nome);
                }
                else if(urlKey.equals("cognome")){
                    String cognome = jsonObject.getString(urlKey);
                    dipendente.setCognome(cognome);
                }
                else if(urlKey.equals("matricola")){
                    String matricola = jsonObject.getString(urlKey);
                    dipendente.setMatricola(matricola);
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
            }

        return dipendente;
    }*/
}
