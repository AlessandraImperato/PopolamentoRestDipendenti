package it.alessandra.popolamentorestdipendenti;

import java.io.Serializable;

/**
 * Created by utente7.academy on 28/11/2017.
 */

public class Dipendente implements Serializable {
    public String nome;
    public String cognome;
    public String matricola;

    public Dipendente(){
        this.nome = null;
        this.cognome = null;
        this.matricola = null;
    }

    public Dipendente(String nome, String cognome, String matricola){
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
