package it.epicode.archivio;


import it.epicode.catalogoBibliografico.ElementoCatalogo;
import it.epicode.catalogoBibliografico.Libro;
import it.epicode.catalogoBibliografico.Rivista;
import it.epicode.catalogoBibliografico.enums.Periodicita;

import java.util.*;
import java.util.stream.*;

public class Archivio {
    private Map<String, ElementoCatalogo> catalogo = new HashMap<>();

    public void caricaEsempi() {
        catalogo.put("12345", new Libro("12345", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy"));
        catalogo.put("67890", new Rivista("67890", "National Geographic", 2025, 100, Periodicita.MENSILE));
        catalogo.put("54321", new Libro("54321", "1984", 1949, 328, "George Orwell", "Fantascienza"));
        catalogo.put("98765", new Rivista("98765", "Time", 2023, 80, Periodicita.SETTIMANALE));
    }

    public void aggiungiElemento(ElementoCatalogo elemento) throws CustomException {
        if (catalogo.containsKey(elemento.getCodiceIsbn())) {
            throw new CustomException("Elemento con ISBN già presente nel catalogo!");
        }
        catalogo.put(elemento.getCodiceIsbn(), elemento);
    }


    public ElementoCatalogo ricercaPerIsbn(String isbn) throws CustomException {
        ElementoCatalogo elemento = catalogo.get(isbn);
        if (elemento == null) {
            throw new CustomException("Nessun elemento trovato con ISBN: " + isbn);
        }
        return elemento;
    }


    public void rimuoviElemento(String isbn) throws CustomException {
        if (catalogo.remove(isbn) == null) {
            throw new CustomException("Nessun elemento trovato con ISBN: " + isbn);
        }
    }


    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }


    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equalsIgnoreCase(autore))
                .map(e -> (Libro) e)
                .collect(Collectors.toList());
    }


    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws CustomException {
        if (!catalogo.containsKey(isbn)) {
            throw new CustomException("Nessun elemento trovato con ISBN: " + isbn);
        }
        catalogo.put(isbn, nuovoElemento);
    }


    public void statisticheCatalogo() {
        long numeroLibri = catalogo.values().stream().filter(e -> e instanceof Libro).count();
        long numeroRiviste = catalogo.values().stream().filter(e -> e instanceof Rivista).count();
        ElementoCatalogo elementoMaxPagine = catalogo.values().stream().max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine)).orElse(null);
        double mediaPagine = catalogo.values().stream().mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0);

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        System.out.println("Elemento con maggior numero di pagine: " + (elementoMaxPagine != null ? elementoMaxPagine : "Nessuno"));
        System.out.println("Media delle pagine: " + mediaPagine);
    }
}
