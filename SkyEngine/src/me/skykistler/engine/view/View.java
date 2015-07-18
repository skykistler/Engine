package me.skykistler.engine.view;

public interface View {
	public void init(int width, int height);

	public void set(Viewport viewport);

	public void render();
}
