package it.epicode;

import it.epicode.archivio.Archivio;
import it.epicode.exceptions.CustomException;
import it.epicode.catalogoBibliografico.ElementoCatalogo;
import it.epicode.catalogoBibliografico.Libro;
import it.epicode.catalogoBibliografico.Rivista;
import it.epicode.enums1.Periodicita;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        archivio.caricaEsempi();
        Scanner scanner = new Scanner(System.in);

        boolean continua = true;
        while (continua) {
            System.out.println("Seleziona un'operazione:");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi elemento");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Statistiche catalogo");
            System.out.println("8. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:

                        System.out.println("Inserisci ISBN:");
                        String isbn = scanner.nextLine();
                        System.out.println("Inserisci titolo:");
                        String titolo = scanner.nextLine();
                        System.out.println("Inserisci anno di pubblicazione:");
                        int anno = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Inserisci numero di pagine:");
                        int pagine = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("E' un libro o una rivista? (L/R):");
                        String tipo = scanner.nextLine();

                        try {
                            if (tipo.equalsIgnoreCase("L")) {
                                System.out.println("Inserisci autore:");
                                String autore = scanner.nextLine();
                                System.out.println("Inserisci genere:");
                                String genere = scanner.nextLine();

                                archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                            } else if (tipo.equalsIgnoreCase("R")) {
                                System.out.println("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                                String periodicita = scanner.nextLine();

                                archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, Periodicita.valueOf(periodicita.toUpperCase())));
                            } else {
                                System.out.println("Tipo non riconosciuto!");
                            }
                        } catch (CustomException e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Inserisci l'ISBN da cercare:");
                        String isbnDaCercare = scanner.nextLine();
                        try {

                            ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbnDaCercare);
                            System.out.println("Elemento trovato: " + elemento);
                        } catch (CustomException e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Inserisci l'ISBN dell'elemento da rimuovere:");
                        String isbnDaRimuovere = scanner.nextLine();
                        try {
                            archivio.rimuoviElemento(isbnDaRimuovere);
                            System.out.println("Elemento rimosso correttamente!");
                        } catch (CustomException e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Inserisci l'anno di pubblicazione da cercare:");
                        int annoDaCercare = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            List<ElementoCatalogo> risultati = archivio.ricercaPerAnno(annoDaCercare);
                            if (risultati.isEmpty()) {
                                System.out.println("Nessun elemento trovato per l'anno: " + annoDaCercare);
                            } else {
                                System.out.println("Elementi trovati per l'anno " + annoDaCercare + ":");
                                risultati.forEach(System.out::println);
                            }
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Inserisci il nome dell'autore da cercare:");
                        String autoreDaCercare = scanner.nextLine();
                        try {
                            List<Libro> risultati = archivio.ricercaPerAutore(autoreDaCercare);
                            if (risultati.isEmpty()) {
                                System.out.println("Nessun libro trovato per l'autore: " + autoreDaCercare);
                            } else {
                                System.out.println("Libri trovati per l'autore " + autoreDaCercare + ":");
                                risultati.forEach(System.out::println);
                            }
                        } catch (Exception e) {
                            System.out.println("Errore: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Inserisci l'ISBN dell'elemento da aggiornare:");
                        String isbnDaAggiornare = scanner.nextLine();

                        try {

                            ElementoCatalogo elementoEsistente = archivio.ricercaPerIsbn(isbnDaAggiornare);
                            System.out.println("Elemento trovato: " + elementoEsistente);


                            System.out.println("Inserisci nuovo titolo:");
                            String nuovoTitolo = scanner.nextLine();
                            System.out.println("Inserisci nuovo anno di pubblicazione:");
                            int nuovoAnno = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Inserisci nuovo numero di pagine:");
                            int nuovoNumeroPagine = scanner.nextInt();
                            scanner.nextLine();

                            if (elementoEsistente instanceof Libro) {
                                System.out.println("Inserisci nuovo autore:");
                                String nuovoAutore = scanner.nextLine();
                                System.out.println("Inserisci nuovo genere:");
                                String nuovoGenere = scanner.nextLine();

                                Libro libroAggiornato = new Libro(isbnDaAggiornare, nuovoTitolo, nuovoAnno, nuovoNumeroPagine, nuovoAutore, nuovoGenere);
                                archivio.aggiornaElemento(isbnDaAggiornare, libroAggiornato);

                            } else if (elementoEsistente instanceof Rivista) {
                                System.out.println("Inserisci nuova periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                                String nuovaPeriodicita = scanner.nextLine();

                                Rivista rivistaAggiornata = new Rivista(isbnDaAggiornare, nuovoTitolo, nuovoAnno, nuovoNumeroPagine, Periodicita.valueOf(nuovaPeriodicita.toUpperCase()));
                                archivio.aggiornaElemento(isbnDaAggiornare, rivistaAggiornata);
                            }

                            System.out.println("Elemento aggiornato correttamente!");
                        } catch (CustomException e) {
                            System.out.println("Errore: " + e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Errore: Valore inserito non valido. " + e.getMessage());
                        }
                        break;
                    case 7:
                        archivio.statisticheCatalogo();
                        break;
                    case 8:
                        continua = false;
                        break;
                    default:
                        System.out.println("Scelta non valida!");
                }
            } catch (CustomException e) {
                System.out.println("Errore: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Errore imprevisto: " + e.getMessage());
            }
        }
        scanner.close();
    }
}