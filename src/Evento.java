import java.time.LocalDate;
import java.time.LocalTime;

public interface Evento {
	
	public void setData(String gg, String mm, String aa);
	public LocalDate getData();
	
	public void setOrario(int h, int m);
	public LocalTime getOrario();
	
	public void setDurata(int d);
	public int getDurata();
	
	public void setNome(String n);
	public String getNome();

	public void setLuogo(String l);
	public String getLuogo();
	
	default void stampa() {
		System.out.println("----------");
		System.out.println("data: "+getData());
		System.out.println("ora: "+getOrario());
		System.out.println("durata: "+getDurata());
		System.out.println("nome: "+getNome());
		System.out.println("luogo: "+getLuogo());
	}

}
