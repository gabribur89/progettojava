import static org.junit.Assert.*;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;

import jbook.util.*;
import eccezioni.SceltaSbagliata;

public class AppuntamentoTest {
	
	Appuntamento app;
	
	@Before 
	public void inizializzaAppuntamento(){
		
		app = new Appuntamento();
	}
	
	@Test
	public void testSetData() {
		//fail("Not yet implemented");
		app.setData("1022", "02", "20");
		assertNotNull(app.getData());
	}
	
	@Test
	public void testGetData() {
		app.setData("1022", "02", "20");
		System.out.println("data getData: "+app.getData());
	}

	@Test
	public void testSetOrario() {
		//fail("Not yet implemented");
		app.setOrario(10, 59);
		assertNotNull(app.getOrario());
	}
	
	@Test
	public void testStampa(){
		app.setData("2019", "07", "04");
		app.setDurata(40);
		app.setLuogo("alessandria");
		app.setNome("pippo");
		app.setOrario(10, 20);
		app.stampa();
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testDataSbagliata() throws DateTimeParseException {
		//fail("Not yet implemented");
		app.setData("201s", "12", "12");
		assertNotNull(app.getData());
		//app.setData("2011","aa","3s");
		//assertEquals(0,app.getData());
	}
	
	@Test(expected = DateTimeException.class)
	public void testOrarioSbagliato() throws DateTimeException {
		//fail("Not yet implemented");
		app.setOrario(30,30);
		assertNotNull(app.getOrario());
		//app.setData("2011","aa","3s");
		//assertEquals(0,app.getData());
	}
	
	/*
	@Test
	public void testGetOrario() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDurata() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDurata() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNome() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLuogo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLuogo() {
		fail("Not yet implemented");
	}*/

}
