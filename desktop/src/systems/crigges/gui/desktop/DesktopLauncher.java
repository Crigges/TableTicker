package systems.crigges.gui.desktop;


import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import systems.crigges.gui.Ticker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Monitor[] monitors = config.getMonitors();
		for (int i = 0; i < monitors.length; i++) {
			System.out.println(monitors[i]);
		}
		new Lwjgl3Application(new Ticker(), config);
	}
}
