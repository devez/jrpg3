package game.story;

import java.util.ArrayList;
import java.util.HashMap;

public class Dialog {
	
	private ArrayList<String> dialogNames;
	private HashMap<String, ArrayList<String>> dialogs;
	
	public Dialog(ArrayList<String> dialogNames, HashMap<String, ArrayList<String>> dialogs) {
		this.dialogNames = dialogNames;
		this.dialogs = dialogs;
	}
	
	public ArrayList<String> getDialogNames() {
		return dialogNames;
	}
	
	public HashMap<String, ArrayList<String>> getDialogs() {
		return dialogs;
	}

}
