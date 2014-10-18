package game.managers;

import game.Project;
import game.entities.Entity;
import game.entities.mobs.Npc;
import game.util.Logger;
import game.xml.Xml;
import game.xml.mobs.NpcXml;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class MyXmlManager extends Manager {

	private XmlReader xmlReader;
	
	//private NpcXml npcXml;
	
	private Xml tempXml;

	public MyXmlManager(Project project) {
		super(project);
		xmlReader = new XmlReader();
		
		//For all scripts, parseXmlFile
		
		try {
			parseXmlFile("resources/scripts/test.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void parseXmlFile(String path) throws IOException {
		Element root = xmlReader.parse(Gdx.files.internal(path));
		String name = root.getName();
		switch(name) {
			case "NPC":
				tempXml = new NpcXml(root);
				break;
			default:
				break;
		}
		//create(npcXml);
		
		//Add all Xml objects to a data structure so that they can be instantiated
		Entity entity = getInstance(tempXml);
		
	}
	
	private Entity getInstance(Xml xml) {
		Entity entity = new Npc(new Vector2(0,0), "temp!");
		
		return entity;
	}
	
//	private void create(Xml xml) {
//		if (xml instanceof NpcXml) {
//			Logger.log("sweet");
//		}
//	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
