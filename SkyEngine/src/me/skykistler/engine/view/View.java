package me.skykistler.engine.view;

import me.skykistler.engine.model.Model;

public interface View {
	public void init(int width, int height);

	public void set(Viewport viewport);

	public void render(Model model);
}
