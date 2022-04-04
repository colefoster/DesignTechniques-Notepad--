package notepad_app;

public class RestoreOldestCommand implements Command{
CustomTextArea customTextArea;
Caretaker caretaker;
	
	public RestoreOldestCommand(CustomTextArea t, Caretaker c) {
		customTextArea = t;
		caretaker = c;
	}
	
	@Override
	public void execute() {
		customTextArea.restore(caretaker.getLast());
	}

}
