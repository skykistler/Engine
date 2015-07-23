package me.skykistler.engine.controller;

import me.skykistler.engine.model.Model;
import me.skykistler.engine.view.View;

public class BaseController implements Controller {

	private Model model;
	private View view;
	private Input input;

	public void init(Model model, View view) {
		this.model = model;
		this.view = view;

		input = new BaseInput();

		// TODO init and pass viewport
		view.init(model, null);

		model.init();

		input.init(view);
	}

	public void update() {
		input.update();

		model.update();

		// TODO time rendering
		view.render();
	}

}
