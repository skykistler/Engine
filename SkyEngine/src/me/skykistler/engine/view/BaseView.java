package me.skykistler.engine.view;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

import me.skykistler.engine.model.Model;

public class BaseView implements View {
	private String windowTitle;
	private int windowWidth;
	private int windowHeight;

	private Model model;
	private Viewport viewport;

	private long window;

	public BaseView(String title, int width, int height) {
		windowTitle = title;
		windowWidth = width;
		windowHeight = height;
	}

	public void init(Model model, Viewport viewport) {
		this.model = model;
		this.viewport = viewport;

		glfwSetErrorCallback(errorCallbackPrint(System.err));

		if (glfwInit() != GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");

		window = glfwCreateWindow(windowWidth, windowHeight, windowTitle, NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (GLFWvidmode.width(vidmode) - windowWidth) / 2, (GLFWvidmode.height(vidmode) - windowHeight) / 2);

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		glfwShowWindow(window);

		viewport.init();
	}

	public void render() {
		GLContext.createFromCurrent();

		viewport.start();
		for (View view : model.getViews()) {
			view.render();
		}
		viewport.finish();
	}

	public long getWindow() {
		return window;
	}

}
