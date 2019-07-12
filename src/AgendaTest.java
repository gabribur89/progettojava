import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AgendaTest {
	
	public void inserisciAppuntamenti(Agenda ag){
		Appuntamento a = new Appuntamento();
		Appuntamento b = new Appuntamento();
		Appuntamento c = new Appuntamento();
		a.setNome("pippo");
		a.setData("1999", "02", "01");
		a.setDurata(45);
		a.setLuogo("milano");
		a.setOrario(13, 30);
		b.setNome("caio");
		b.setData("1990", "12", "22");
		b.setDurata(45);
		b.setLuogo("milano");
		b.setOrario(13, 30);
		c.setNome("aldo");
		c.setData("2012", "04", "15");
		c.setDurata(45);
		c.setLuogo("milano");
		c.setOrario(13, 30);
		ag.inserisciApp(a);
		ag.inserisciApp(c);
		ag.inserisciApp(b);
	}
	
	@Test
	public void testInserimento() {
		Agenda ag = new Agenda();
		//Appuntamento a = new Appuntamento();
		//fail("Not yet implemented");
		//System.out.println(ag.numEl());
		ag.inserisciApp(new Appuntamento());
		assertTrue(ag.numEl()==1);
		//ag.eliminaApp(0);
	}
	
	@Test
	public void eliminaApp(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		ag.inserisciApp(a);
		//System.out.println("2. test dopo inserisciApp: " + ag.numEl());
		ag.eliminaApp(0);
		//System.out.println("2. test dopo eliminaApp: " + ag.numEl());
		assertTrue(ag.numEl()==0);
	}
	
	@Test
	public void recuperaAppuntamento(){
		Agenda ag = new Agenda();
		ag.inserisciApp(new Appuntamento());
		//System.out.println("3. test dopo inserisciApp: "+ ag.numEl());
		ag.recuperaApp(0);
		assertNotNull(ag.recuperaApp(0));
		//ag.eliminaApp(0);
		//System.out.println("3. test dopo eliminaApp: "+ ag.numEl());
	}
	
	@Test
	public void recuperaNome(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		a.setNome("pippo");
		ag.inserisciApp(a);
		//System.out.println("4. test dopo inserisciApp: "+ ag.numEl());
		ag.cercaPerNome("pippo");
		assertNotEquals(-1,ag.cercaPerNome("pippo"));
		//ag.eliminaApp(0);
		//System.out.println("4. test dopo eliminaApp: "+ag.numEl());
	}
	
	@Test
	public void eliminaPerNome(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		a.setNome("pippo");
		ag.inserisciApp(a);
		//System.out.println("5. test dopo inserisciApp: "+ ag.numEl());
		ag.eliminaPerNome("pippo");
		assertTrue(ag.numEl()==0);
		//System.out.println("5. test dopo eliminaPerNome: "+ ag.numEl());
	}
	
	@Test
	public void dettaglioNome(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		a.setNome("pippo");
		ag.inserisciApp(a);
		//System.out.println("6. test dopo inserisciApp: "+ ag.numEl());
		ag.dettagliNome("pippo");
		assertTrue(ag.dettagliNome("pippo"));
		//System.out.println("6. test dopo recuperaPerNome: "+ ag.numEl());
		//il test restituisce 1 perch� effettivamente ha trovato il nome
	}
	
	@Test
	public void recuperaData(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		a.setData("2018", "02", "01");
		ag.inserisciApp(a);
		//System.out.println("7. test dopo inserisciApp: "+ ag.numEl());
		assertNotEquals(-1,ag.cercaPerData(a.getData()));
		//come argomento passo getData, contiene data
		//ag.eliminaApp(0);
		//System.out.println("7. test dopo cercaPerData: "+ag.numEl());
	}
	
	@Test
	public void dettaglioData(){
		Agenda ag = new Agenda();
		Appuntamento a = new Appuntamento();
		a.setData("1999","05","15");
		ag.inserisciApp(a);
		//System.out.println("8. test dopo inserisciApp: "+ ag.numEl());
		//ag.dettagliData(a.getData());
		assertTrue(ag.dettagliData(a.getData()));
		//System.out.println("8. test dopo dettagliData: "+ ag.numEl());
		//il test restituisce 1 perch� effettivamente ha trovato la data
	}
	
	@Test
	public void testordinamento(){
		Agenda ag = new Agenda();
		this.inserisciAppuntamenti(ag);
		ag.stampaAgenda();
		System.out.print("ordino...\n\n");
		ag.ordina();
		ag.stampaAgenda();
		//ag.eliminaApp(0);
		//ag.eliminaApp(0);
		//ag.eliminaApp(0);
		//ag.eliminaApp(1);
		//ag.eliminaApp(2);
		
	}
	
	@Test
	public void testleggifile() throws FileNotFoundException, IOException, NumeroCampiException{
		Agenda ag = new Agenda();
		ag.leggidafile();
		assertTrue(ag.numEl()==2);
		
	}
	
	@Test
	public void testscrivifile() throws IOException{
		Agenda ag = new Agenda();
		this.inserisciAppuntamenti(ag);
		ag.scrivisufile();
		assertTrue(ag.numEl()==3);
		
	}
}