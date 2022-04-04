package notepad_app;

public class SnapshotCommand implements Command{
CustomTextArea customTextArea;
Caretaker caretaker;
	
	public SnapshotCommand(CustomTextArea t, Caretaker c) {
		customTextArea = t;
		caretaker = c;
	}
	
	@Override
	public void execute() {
		caretaker.addMemento(customTextArea.takeSnapshot());
	}

}
