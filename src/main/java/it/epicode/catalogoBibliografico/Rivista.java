package it.epicode.catalogoBibliografico;

import it.epicode.enums1.Periodicita;

public class Rivista extends ElementoCatalogo {
    private Periodicita periodicita;

    public Rivista(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;

    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicita: " + periodicita;
    }
}
