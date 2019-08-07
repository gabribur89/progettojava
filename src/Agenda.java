import java.util.*;

import eccezioni.InputError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Agenda implements Iterable<Appuntamento>{
	
	private ArrayList<Appuntamento> agenda = new ArrayList<Appuntamento>();
	private File out = new File("salvato.csv");
	
	public Agenda() {}
	
	public Agenda(int dimensione) {
		agenda = new ArrayList<Appuntamento>(dimensione);
	}
	
	@Override
    public Iterator<Appuntamento> iterator() {
        return agenda.iterator();
    }
	
	public boolean inserisciApp(Appuntamento nuovo){
		return agenda.add(nuovo);
		
	}
	
	public void leggidafile() throws FileNotFoundException, IOException, NumeroCampiException, InputError {
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("leggimi.csv")); 
		    //StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	System.out.println(line);
		    	String[] campi = line.split(",");
		    	String[] campodata = campi[1].split("-");
		    	String[] campoora = campi[3].split(":");
		    	if(campi.length == 5)
		    	{
		    	Appuntamento a = new Appuntamento();
		    	a.setNome(campi[0]);
		    	a.setData(campodata[0], campodata[1], campodata[2]);
		    	a.setDurata(Integer.parseInt(campi[2]));
		    	a.setLuogo(campi[4]);
		    	a.setOrario(Integer.parseInt(campoora[0]),Integer.parseInt(campoora[1]));
		    	inserisciApp(a);
		    	System.out.println(Arrays.toString(campi));
		    	}
		    	else throw new NumeroCampiException();
		        //sb.append(line);
		        //sb.append(System.lineSeparator());
		        line = br.readLine();
		        
		        //Agenda.inserisciApp(a);
		    }
		    br.close();
		    //String everything = sb.toString();
		}catch(FileNotFoundException e)
		{
			throw new InputError("Leggi da file InputError",e);
		}
	}
	
	public void scrivisufile(){
		
		try {
	           FileWriter fw = new FileWriter(this.out,true);
	           //String date = new Date().toString();
	           //fw.write(date+" : "+s);
	           Iterator<Appuntamento> iter = agenda.iterator(); //metodo restituente iteratore per uso cicli
	    	   while (iter.hasNext()){
	    		   Appuntamento a = iter.next();
	    		   //System.out.print(a.getOrario().toString());
	    		   fw.write(a.getNome()+"," + a.getData().toString()+"," + String.valueOf(a.getDurata())+"," +a.getOrario().toString()+","
	    				   +a.getLuogo()+"\n");
	    	   }
	           fw.close();
	       } catch (IOException ex) {
	           System.err.println("Non posso salvarlo, mi dispiace");
	       }
		
	}
	
	public Appuntamento recuperaApp(int i){
		return agenda.get(i);
	}
	
	public int cercaPerNome(String n){
		for(int i=0;i<agenda.size();i++){
			String nome = agenda.get(i).getNome();
						
			if (nome.compareTo(n) == 0){
				return i; //restituisco indice se ho trovato il nome
			}
		}
		return -1; //se non ho trovato un nome, ritorno -1
	}
	
	public Appuntamento eliminaApp(int i){
		return agenda.remove(i);
	}

	public boolean sostituisciApp(int i, Appuntamento nuovo) throws InputError {
		
		assert nuovo != null;
		
		try {
			agenda.remove(i);
			agenda.add(i, nuovo);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			throw new InputError("errore, indice appuntamento inesistente", e);
			
		} catch (Exception e) {
			System.out.print("Errore durante la modifica");
			return false;
		} 
		
		return true;
	}
	
	public boolean eliminaPerNome(String n){
		int trovato = cercaPerNome(n);
		
		if(trovato != -1)
		{
			if (eliminaApp(trovato) != null)
			{
			return true;
			}
		}
		return false;
	}
	
	
	public boolean dettagliNome(String n){
		
		int trovato = cercaPerNome(n);
		
		if(trovato != -1)
		{
			Appuntamento a = recuperaApp(trovato);
			if(a != null)
			{
				a.stampa();
				return true;
			}
		}
		return false;
	}
	
	
	public int cercaPerData(LocalDate d) throws InputError{
		try{	
			for(int i=0;i<agenda.size();i++)
				{
					LocalDate data = agenda.get(i).getData();
					if(data.isEqual(d)){
						return i; //restituisco indice se ho trovato la data
					}
				}
				return -1; //se non l'ho trovata, restituisco -1
		   }catch(DateTimeParseException e)
		   	{
			   throw new InputError("Cerca per data InputError",e);
		   	}
		  }
	
	
	public int cercaPerOrarioDisponibile(LocalTime ora) throws InputError{
		try{
				for(int i=0;i<agenda.size();i++)
				{
					// differenza in minuti da 00:00 + la durata
					int delta_minuti = agenda.get(i).getOrario().get(ChronoField.MINUTE_OF_DAY) + agenda.get(i).getDurata();
					// differenza in minuti per l'ora validare
					int delta_minuti_input = ora.get(ChronoField.MINUTE_OF_DAY);
					
					if(delta_minuti <= delta_minuti_input){
					return i; 
					}
				}
				return -1; 
			}catch(DateTimeException e)
				{
					throw new InputError("Cerca per orario InputError",e);
				}
		   }
	

	public int size() {
		return agenda.size();
	}
	
	public Agenda clonaAgenda(Agenda ag) {
	    Agenda clone = new Agenda(ag.size());
	    for (Appuntamento item : ag) clone.inserisciApp(new Appuntamento(item));
	    return clone;
	}
	
	public boolean dettagliData(LocalDate d) throws InputError{
		int trovato = cercaPerData(d);
		
		if(trovato != -1)
		{
			Appuntamento a = recuperaApp(trovato);
			if(a != null)
			{
				a.stampa();
				return true;
			}
		}
		return false;
	}
	
	public void stampa() {
		for(Appuntamento a: this.agenda) {
		    a.stampa();
	    }
	}

	/*ordinamento per data, creo una copia dell'agenda
	 * e lavoro su questa, per poi poterla stampare correttamente*/
	public void ordina(){
	    
		 Appuntamento first;
	     Appuntamento next;
	     int i = agenda.size();
	     while(i>0) 
	       {
	       for(int j=0; j < i-1; j++) 
	         {
	         first = (Appuntamento) agenda.get(j);
	         next = (Appuntamento) agenda.get(j+1);
	         if(first.getData().compareTo(next.getData())>0)
	         {
	        	 //scambiare il '<' con '>' per ottenere un ordinamento decrescente
	           exchange(agenda,j,j+1);
	         }
	         }
	       i--;
	     }
	     
	     
	     //return a;
	}
	
	
   public void exchange(ArrayList<Appuntamento> a, int i, int j) 
     {
     Appuntamento tmp = a.get(i);
     a.set(i, a.get(j));
     a.set(j, tmp);
     }

   
	public int numEl(){
		return agenda.size();
	}
}
