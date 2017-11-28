package it.alessandra.popolamentorestdipendenti;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utente7.academy on 28/11/2017.
 */

public class Azienda {

    public List<Dipendente> dipendenti;

    public Azienda(){
        this.dipendenti = new ArrayList<>();
    }

    public Azienda(List<Dipendente> dipendenti){
        this.dipendenti = dipendenti;
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    // metodo che ritrona un array di stringe contenente le matricole dei dipendenti
    public String [] listaMatricole(List<Dipendente> dipendenti){
        List<String> matricole = new ArrayList<>();
        String s = "";
        String arrayMatricole [] = new String[dipendenti.size()];
        for(Dipendente dipendente : dipendenti){
            s = dipendente.getMatricola();
            matricole.add(s);
        }
        arrayMatricole = matricole.toArray(arrayMatricole);
        return arrayMatricole;
    }
}
