import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import jbook.util.Input;
import java.util.*;

public class MainClass {

	public static void stampaMenu(Boolean giaesistente) {
		if (giaesistente) {
			System.out.println("\n\n Premi un tasto per tornare al menu");
			Input.readString();
		}
		System.out.println("\n\n GESTIONE RUBRICA DI APPUNTAMENTI");
		System.out.println("\n\n");
		System.out.println("1 - Aggiungi un appuntamento");
		System.out.println("2 - Elimina un appuntamento (tutti i dati)");
		System.out.println("3 - Modifica un appuntamento");
		System.out.println("4 - Cerca un appuntamento per data");
		System.out.println("5 - Cerca appuntamento per nome");
		System.out.println("6 - Elimina un appuntamento per data");
		System.out.println("7 - Elimina appuntamento per nome");
		System.out.println("8 - Ordina appuntamenti per data");
		System.out.println("9 - Stampa tutti gli appuntamenti");
		System.out.println("10 - Scrivi appuntamento su file");
		System.out.println("11 - Leggi appuntamenti da file");
		System.out.println("0 - Esci ");
		System.out.println("\n\n");
		System.out.print("\n Inserisci un comando: ");
		System.out.println("\n\n\n");
	}
	
	public static Appuntamento nuovoApp(){

		Appuntamento app = new Appuntamento();
		
		try {
		
			System.out.print("Inserisci il nome della persona:");
			app.setNome(Input.readString());
			
			System.out.print("Inserisci la data (PER ORA AAAA-MM-GG):");
			String da = Input.readString();
			String arr[] = da.split("-");
			app.setData(arr[0], arr[1], arr[2]);
			
			System.out.print("Inserisci la durata (in minuti):");
			app.setDurata(Input.readInt());
			
			System.out.print("Inserisci luogo:");
			app.setLuogo(Input.readString());
			
			System.out.print("Inserisci orario:");
			String o = Input.readString();
			String arr2[];
			arr2=o.split(":");
			app.setOrario(Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]));

		}
		catch (DateTimeParseException e) {
			System.out.print("Data non valida");
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.print("Formato Data oppure Ora invalido, data: AAAA-MM-GG , ora: HH:MM");
		}
		
	return app;

	}
	
	public static void main(String[] args){
			
			int scelta;
			// determina se è già stato prodotto il menu o no
			Boolean esistente = false;
			
			Agenda agenda = new Agenda();
			/*
			Appuntamento a = new Appuntamento();
			a.setNome("pippo");
			a.setData("1999", "02", "01");
			a.setDurata(45);
			a.setLuogo("milano");
			a.setOrario(13, 30);
			
			Appuntamento b = new Appuntamento();
			b.setNome("caio");
			b.setData("1990", "12", "22");
			b.setDurata(45);
			b.setLuogo("milano");
			b.setOrario(13, 30);
			
			agenda.inserisciApp(a);
			agenda.inserisciApp(b);
			*/
			do{
				MainClass.stampaMenu(esistente);
				esistente = true; 
				scelta=Input.readInt();

				switch(scelta){
				case 1: //aggiungi appuntamento
					Appuntamento appu = nuovoApp();
					// controllo che non esista un altro appuntemento nella stessa data e ora
					LocalTime ora = appu.getOrario();
					LocalDate data = appu.getData();
					
					// il giorno è disponibile non mi faccio problemi
					if( agenda.cercaPerData(data) == -1){
						agenda.inserisciApp(appu);
					// il giorno non è disponibile ma l'orario lo è		
					}else if(agenda.cercaPerOrarioDisponibile(ora) != -1){
						agenda.inserisciApp(appu);
					}else {
						System.out.print("Non è possibile inserire l'appuntamento in questa data\n");
					}
					
					break;
				case 2: //elimino tutti i dati
					//agenda.eliminaApp();
					break;
				case 3: //modifica appuntamento
					agenda.stampaAgenda();
					System.out.print("Schegli l'appuntamento da moficare:");
					int numeroapp = Input.readInt();
					
					Appuntamento nuovo = nuovoApp();
					// la funzione richiede un indice dell'arraylist
					agenda.sostituisciApp(numeroapp-1, nuovo);
					agenda.stampaAgenda();

					break;
				case 4: //ricerca di appuntamento per data
					System.out.print("Inserisci la data (PER ORA AAAA-MMM-GG):");
					String da = Input.readString();
					agenda.cercaPerData(LocalDate.parse(da));
					break;
				case 5: //ricerca di appuntamento per nome
					System.out.print("Inserisci il nome che vuoi cercare:");
					String n = Input.readString();
					//System.out.println(n);
					if (!agenda.dettagliNome(n))
					{
						System.out.println("Non ho trovato niente");
					}
					break;	
				case 6: //elimina un appuntamento per data
					System.out.print("");
					break;
				case 7: //elimina un appuntamento per nome
					System.out.print("Inserisci il nome per eliminare l'appuntamento:");
					String n1 = Input.readString();
					if(!agenda.eliminaPerNome(n1))
					{
						System.out.println("Nada de nada");
					}
					break;
				case 8: //ordinamento per data
					agenda.ordina();
					agenda.stampaAgenda();
					break;
				case 9: //stampa tutti gli appuntamenti correnti
					agenda.stampaAgenda();
					break;
				case 10: //scrivi su file
					try {
						agenda.scrivisufile();
					}catch (Exception e) {
						System.out.println("Errore nel salvataggio degli Appuntamenti su file");
					}
					System.out.println("Appuntamenti Salvati correttamente");
					
					break;
				case 11: //leggi da file
					try {
						agenda.leggidafile();
					}catch (Exception e) {
						System.out.println("Errore nella lettura dei dati da file");
					}
					System.out.println("Appuntamenti caricati correttamente");
					
					break;
				case 0: //esci
					System.out.println("\n ti saluto! Alla prossima! ");
				default:
					break;
				}
			}while(scelta!=0);
		}
}
