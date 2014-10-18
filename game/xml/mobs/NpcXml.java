package game.xml.mobs;

import java.util.ArrayList;
import java.util.HashMap;

import game.entities.Entity;
import game.entities.mobs.Npc;
import game.util.Logger;
import game.util.interfaces.Instantiable;
import game.xml.Xml;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;

public class NpcXml extends Xml implements Instantiable {

	private String name;
	private String currentLevelName;

	private ArrayList<String> dialogNames;
	private HashMap<String, ArrayList<String>> dialogs;

	public NpcXml(Element root) {
		super(root);
		dialogNames = new ArrayList<String>();
		dialogs = new HashMap<String, ArrayList<String>>();
		name = root.getAttribute("name");
		this.getXmlChildren(root);
	}

	@Override
	public void getXmlChildren(Element root) {
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			//System.out.println(i + ") " + child.getName());
			parseXmlChild(child);
		}
	}

	private void parseXmlChild(Element child) {
		switch (child.getName()) {
			case ("data"):
				parseData(child);
				break;
			case ("dialogs"):
				parseDialogs(child);
				break;
			default:
				break;
		}
	}

	private void parseData(Element root) {
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			//Logger.log(child.getName());
			switch (child.getName()) {
				case ("level"):
					currentLevelName = child.get("name");
					break;
				case ("animations"):
					parseAnimations(child);
					break;
				default:
					break;
			}
		}
	}

	private void parseAnimations(Element root) {
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			parseAnimation(child, child.get("id"));
		}
	}
	
	private void parseAnimation(Element root, String id) {
		//Logger.log(id);
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			//Logger.log(child.getName());
			switch(child.getName()) {
				case("down"):
					//Logger.log(child.getText());
					break;
				case("right"):
					//Logger.log(child.getText());
					break;
				case("up"):
					//Logger.log(child.getText());
					break;
				case("left"):
					//Logger.log(child.getText());
					break;
				default:
					break;
			}
		}
	}

	private void parseDialogs(Element root) {
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			// System.out.println(i + ") " + child.getName());
			switch (child.getName()) {
				case ("dialog"):
					parseDialogData(child);
					break;
				default:
					break;
			}
		}
		//printDialogs(dialogNames);
	}

	private void parseDialogData(Element root) {
		String name = root.getAttribute("name");
		//Logger.log(name);
		ArrayList<String> messages = new ArrayList<String>();
		for (int i = 0; i < root.getChildCount(); i++) {
			Element child = root.getChild(i);
			switch (child.getName()) {
				case ("message"):
					messages.add(child.getAttribute("text"));
					break;
				default:
					break;
			}
		}
		dialogNames.add(name);
		dialogs.put(name, messages);
	}

	private void printDialogs(ArrayList<String> dialogNames) {
		for (String name : dialogNames) {
			printDialog(name);
		}
	}

	private void printDialog(String name) {
		System.out.println(name);
		for (String message : dialogs.get(name)) {
			System.out.println("\t" + message);
		}
	}

	@Override
	public Entity getInstance() {
		Logger.log("temp...");
		return new Npc(new Vector2(0, 0), "getInstance()");
	}

}
