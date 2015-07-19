package me.skykistler.engine.model;

import java.util.ArrayList;

import me.skykistler.engine.view.View;

public class BaseModel implements Model {
	private ArrayList<Model> models;

	public void init() {
		models = new ArrayList<Model>();
	}

	public void update() {
		for (Model m : models) {
			m.update();
		}
	}

	public void add(Model model) {
		models.add(model);
	}

	public ArrayList<View> getViews() {
		ArrayList<View> views = new ArrayList<View>();

		for (Model m : models) {
			views.addAll(m.getViews());
		}

		return views;
	}

}
