package game.managers;

import game.Project;

public abstract class Manager {

	protected Project project;

	public Manager(Project project) {
		this.project = project;
	}
	
	public abstract void create();

	public abstract void dispose();

}
