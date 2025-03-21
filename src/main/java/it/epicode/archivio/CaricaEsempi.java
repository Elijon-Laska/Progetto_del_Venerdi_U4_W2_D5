package it.epicode.archivio;

import it.epicode.catalogoBibliografico.ElementoCatalogo;
import it.epicode.catalogoBibliografico.Libro;
import it.epicode.catalogoBibliografico.Rivista;
import it.epicode.enums1.Periodicita;

import java.util.Map;

public class CaricaEsempi {

    public static void caricaEsempi(Map<String, ElementoCatalogo> catalogo) {
        catalogo.put("12345", new Libro("12345", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", null));
        catalogo.put("67890", new Rivista("67890", "National Geographic", 2025, 100, Periodicita.MENSILE));
        catalogo.put("54321", new Libro("54321", "1984", 1949, 328, "George Orwell", null));
        catalogo.put("98765", new Rivista("98765", "Time", 2023, 80, Periodicita.SETTIMANALE));
    }
}