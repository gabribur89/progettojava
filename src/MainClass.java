import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import jbook.util.Input;
import eccezioni.InputError;
import eccezioni.SceltaSbagliata;


/**
 *
 * @author Buratto Gabriele 10025213
*/
public class MainClass {

	public static void stampaMenu(boolean giaesistente) {
		if (giaesistente) {
			System.out.println("\n\n Premi un tasto per tornare al menu");
			Input.readString();
		}
		System.out.println("\n\n GESTIONE RUBRICA DI APPUNTAMENTI");
		System.out.println("\n\n");
		System.out.println("1 - Aggiungi un appuntamento");
		System.out.println("2 - Modifica un appuntamento");
		System.out.println("3 - Cerca un appuntamento per data");
		System.out.println("4 - Cerca appuntamento per nome");
		System.out.println("5 - Elimina appuntamento per nome");
		System.out.println("6 - Ordina appuntamenti per data");
		System.out.println("7 - Stampa tutti gli appuntamenti");
		System.out.println("8 - Scrivi appuntamento su file");
		System.out.println("9 - Leggi appuntamenti da file");
		System.out.println("0 - Esci ");
		System.out.println("\nInserisci un comando: ");
		System.out.println("\n\n\n");
	}
	
	public static Appuntamento nuovoApp() throws SceltaSbagliata, InputError{

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
			throw new InputError("Data non valida",e);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw new InputError("Formato Data oppure Ora invalido, data: AAAA-MM-GG , ora: HH:MM",e);
		}
		catch(NumberFormatException e){
			throw new InputError("Accetto solo numeri, non altri caratteri!",e);
		}
		catch (DateTimeException e){
			throw new InputError("Data, ora o minuti sbagliati",e);
		}
		
	return app;

	}
	
	public static void main(String[] args) throws SceltaSbagliata, InputError{
			
			int scelta = 0;
			
			// determina se è già stato prodotto il menu o no
			boolean esistente = false;
			Agenda agenda = new Agenda();

			do{
				MainClass.stampaMenu(esistente);
				esistente = true; 
				
				try { 
					scelta=Input.readInt();
					System.out.println(scelta);
					if((scelta<0) || (scelta>9))
					{
						throw new SceltaSbagliata("Devi inserire un numero tra 0 e 9!");
					}
					
				} catch (NumberFormatException e){
					  System.out.print("Devi inserire solo numeri interi!");
				} catch(SceltaSbagliata e){
					  scelta = 99;
					  System.out.println("Devi inserire un numero tra 0 e 9!");
				};
				
				switch(scelta){
				case 1: //aggiungi appuntamento
					try{
						Appuntamento appu = nuovoApp();
						// controllo che non esista un altro appuntemento nella stessa data e ora
						LocalTime ora = appu.getOrario();
						LocalDate data = appu.getData();
						
						// il giorno e' disponibile non mi faccio problemi
						if( agenda.cercaPerData(data) == -1){
							agenda.inserisciApp(appu);
						// il giorno non e' disponibile ma l'orario lo e'		
						}else if(agenda.cercaPerOrarioDisponibile(ora) != -1){
							agenda.inserisciApp(appu);
						}else {
							System.out.print("Non e' possibile inserire l'appuntamento in questa data\n");
						}
					}catch(InputError e){
						System.out.println(e.getMessage());
						break;
					}
					break;
				case 2: //modifica appuntamento
					try{
						agenda.stampa();
						System.out.print("Scegli l'appuntamento da modificare:");
						
						int numeroapp = Input.readInt();
						Appuntamento nuovo = nuovoApp();
						// la funzione richiede un indice dell'arraylist
						agenda.sostituisciApp(numeroapp-1, nuovo);
						agenda.stampa();
					}
					catch (NumberFormatException e){
						System.out.println("Devi inserire solo numeri interi!");
				    }
					catch (InputError e){
						throw new InputError("Errore nella modifica dell'appuntamento", e);
						
					}

				     break;
				case 3: //ricerca appuntamento per data
					try{
						System.out.print("Inserisci la data (AAAA-MMM-GG):");
						String da = Input.readString();
						agenda.cercaPerData(LocalDate.parse(da));
						if(!agenda.dettagliData(LocalDate.parse(da)))
						{
							System.out.println("Non ho trovato niente");
						}
					}catch (DateTimeParseException e){
						System.out.println("Guarda che il formato della data non � corretto!");
					}
					break;
				case 4: //ricerca di appuntamento per nome
					System.out.print("Inserisci il nome che vuoi cercare:");
					String n = Input.readString();
					//System.out.println(n);
					if (!agenda.dettagliNome(n))
					{
						System.out.println("Non ho trovato niente");
					}
					break;	
				case 5: //elimina un appuntamento per nome
					System.out.print("Inserisci il nome per eliminare l'appuntamento:");
					String n1 = Input.readString();
					if(!agenda.eliminaPerNome(n1))
					{
						System.out.println("Non ho trovato niente che corrisponda.");
					}
					break;
				case 6: //ordinamento per data
					Agenda tempag = agenda.clonaAgenda(agenda);
					tempag.ordina();
					tempag.stampa();
					break;
				case 7: //stampa tutti gli appuntamenti correnti
					System.out.println("STAMPA AGENDA");
  				    agenda.stampa();
					break;
				case 8: //scrivi su file
					try {
						agenda.scrivisufile();
					}catch (Exception e) {
						System.out.println("Errore nel salvataggio degli Appuntamenti su file");
					}
					System.out.println("Appuntamenti Salvati correttamente");
					break;
				case 9: //leggi da file
					try {
						agenda.leggidafile();
					}catch(InputError e){
						System.out.println("Non ho trovato il file....");
					}
					catch (Exception e) {
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
