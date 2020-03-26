package sonarqube.logcount.sonarqube_logcount;

import org.sonar.api.Plugin;

public class LogCountPlugin implements Plugin {

	public void define(Context context) {

		context.addExtension(LogCountDefinition.class);
	    context.addExtension(LogCountRegistrar.class);
		
	}

}