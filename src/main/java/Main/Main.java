package Main;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

	// The window handle
	private Window window;
	private Renderer renderer;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		window = new Window("Dance Mat Calculator", 600, 480);
		window.init();
		renderer = new Renderer();
		renderer.init();

		loop();

		// Free the window callbacks and destroy the window


		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void loop() {

		// Set the clear color
		//window.setClearColor(1.0f, 1.0f, 0.0f, 0.0f);
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !window.windowShouldClose()) {
			if (window.isKeyPressed(GLFW_KEY_E)) {
				window.setClearColor(1.0f, 0.0f, 0.0f, 0.0f);
			} else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
				window.setClearColor(1.0f, 1.0f, 0.0f, 0.0f);
			} else {
				window.setClearColor(0.0f, 0.0f, 1.0f, 0.0f);
			}
			renderer.clear();
			window.update(); // swap the color buffers
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}

}