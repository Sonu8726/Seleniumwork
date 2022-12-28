package data;

import base.ConfigManager;

public class TestData {

	static ConfigManager app = new ConfigManager("App");

	public final String applicationUrl = app.getProperty("applicationUrl");
	public final String username = app.getProperty("username");
	public final String password = app.getProperty("password");
}
