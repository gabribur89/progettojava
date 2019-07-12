import java.io.IOException;
import jbook.util.Input;

public class MainClass {
public static void main(String[] args) throws IOException  {
		
		int scelta;
		
		Agenda agenda=new Agenda();
		
		do{
			System.out.println("GESTIONE RUBRICA DI APPUNTAMENTI");
			System.out.println("\n\n");
			System.out.println("1 - Aggiungi un appuntamento");
			System.out.println("2 - Elimina un appuntamento (tutti i dati)");
			System.out.println("3 - Modifica un appuntamento");
			System.out.println("4 - Cerca un appuntamento per data");
			System.out.println("5 - Cerca appuntamento per nome");
			System.out.println("6 - Elimina un appuntamento per nome ");
			System.out.println("7 - Elimina appuntamento per data");
			System.out.println("8 - Ordina appuntamenti per data");
			System.out.println("9 - Stampa tutti gli appuntamenti");
			System.out.println("10 - Scrivi appuntamento su file");
			System.out.println("11 - Leggi appuntamento da file");
			System.out.println("-1 - Esci ");
			System.out.println("\n\n");
			System.out.print("\n Inserisci un comando: ");
			scelta=Input.readInt();
			System.out.println("\n\n\n");
			
			switch(scelta){
			case 1: //aggiungi appuntamento
				Appuntamento a = new Appuntamento();
				if(agenda.numEl()>=0){
					agenda.inserisciApp(a);
					break;
				}
			case 2: //
				break;
			case 3: //
				break;
			case 4: //
				break;
			case 5: //
				break;	
			case 6: //
				break;
			case 7: //
				break;
			case 8: //ordinamento per data
				agenda.ordina();
				break;
			case 9: //
				break;
			case 10: //
				break;
			case 11: //
				break;
			case -1: //esci
				System.out.println("\n ti saluto! Alla prossima! ");
			default:
				break;
			}			
		}while(scelta!=-1);

	}

}
