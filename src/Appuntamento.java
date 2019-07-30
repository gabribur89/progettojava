import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.*;
import java.time.LocalDate;

public class Appuntamento implements Evento{
	
	private LocalDate data;
	private LocalTime orario;
	private int durata;
	private String nome;
	private String luogo;
	
	public void setData(String gg, String mm, String aa){
		
		String data_completa = gg + "-" + mm + "-" + aa;
		data = LocalDate.parse(data_completa);
		
	}
	
	public LocalDate getData(){
		return data;
	}
	
	public void setOrario(int h, int m){
		
		orario = LocalTime.of(h, m);
	}
	
	public LocalTime getOrario(){
		return orario;
	}
	
	public void setDurata(int d){
		durata = d;
	}
	
	public int getDurata(){
		return durata;
	}
	
	public void setNome(String n){
		nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setLuogo(String l){
		luogo = l;
	}
	
	public String getLuogo(){
		return luogo;
	}
	
	public void stampaApp(){
		System.out.println("STAMPA APPUNTAMENTI");
		System.out.println("nome: "+getNome());
		System.out.println("data: "+getData());
		System.out.println("durata: "+getDurata());
		System.out.println("orario: "+getOrario());
		System.out.println("luogo: "+getLuogo());
	}
	
}
