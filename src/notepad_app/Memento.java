package notepad_app;

public class Memento{
	private String data;
	
	public Memento(String s) {
		data = s;
	}
	
	public String getData() {
		return data;
	}
	
}