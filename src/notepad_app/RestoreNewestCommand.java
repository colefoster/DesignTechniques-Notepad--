package notepad_app;

public class RestoreNewestCommand implements Command{
CustomTextArea customTextArea;
Caretaker caretaker;
	
	public RestoreNewestCommand(CustomTextArea t, Caretaker c) {
		customTextArea = t;
		caretaker = c;
	}
	
	@Override
	public void execute() {
		customTextArea.restore(caretaker.getFirst());
	}

}
