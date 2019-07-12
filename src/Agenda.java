import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Agenda {
	
	private ArrayList<Appuntamento> agenda = new ArrayList<Appuntamento>();
	private File out = new File("salvato.csv");
	
	public boolean inserisciApp(Appuntamento nuovo){
		
		return agenda.add(nuovo);
		
	}
	
	
	public void leggidafile() throws FileNotFoundException, IOException, NumeroCampiException {
		try(BufferedReader br = new BufferedReader(new FileReader("leggimi.csv"))) {
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
	           System.err.println("Couldn't log this: ");
	       }
		
	}
	
	public Appuntamento recuperaApp(int i){
		return agenda.get(i);
	}
	
	public int cercaPerNome(String n){
		for(int i=0;i<agenda.size();i++){
			if (agenda.get(i).getNome() == n){
				return i; //restituisco indice se ho trovato il nome
			}
		}
		return -1; //se non ho trovato un nome, ritorno -1
	}
	
	public Appuntamento eliminaApp(int i){
		return agenda.remove(i);
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
				a.stampaApp();
				return true;
			}
		}
		return false;
	}
	
	public int cercaPerData(LocalDate d){
		
		for(int i=0;i<agenda.size();i++)
		{
			if(agenda.get(i).getData() == d){
			return i; //restituisco indice se ho trovato la data
			}
		}
		return -1; //se non l'ho trovata, restituisco -1
	}
	
	public boolean dettagliData(LocalDate d){
		int trovato = cercaPerData(d);
		
		if(trovato != -1)
		{
			Appuntamento a = recuperaApp(trovato);
			if(a != null)
			{
				a.stampaApp();
				return true;
			}
		}
		return false;
	}
	
	/*ordinamento per data, creo una copia dell'agenda
	 * e lavoro su questa, per poi poterla stampare correttamente*/
	public static boolean ordinaPerData(LocalDate d){
		
		ArrayList<Appuntamento> a = new ArrayList<Appuntamento>();

		return true;
	}
	
	public void ordina(){
	    
		 //ArrayList<Appuntamento> a = new ArrayList<Appuntamento>(agenda);
		 //copia dell'agenda
		 
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
   
   public void stampaAgenda(){
	   System.out.println("STAMPA AGENDA");
	   Iterator<Appuntamento> iter = agenda.iterator(); //metodo restituente iteratore per uso cicli
	   while (iter.hasNext()){
		   iter.next().stampaApp();
	   }
   }
	
	
	public int numEl(){
		return agenda.size();
	}
}
