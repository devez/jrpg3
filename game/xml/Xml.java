package game.xml;

import game.entities.Entity;

import com.badlogic.gdx.utils.XmlReader.Element;

public abstract class Xml {
	
	protected Element root;
	
	public Xml(Element root) {
		this.root = root;
	}
	
	public abstract void getXmlChildren(Element root);
	
}
