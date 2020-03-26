package sonarqube.logcount.sonarqube_logcount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonarsource.api.sonarlint.SonarLintSide;

@SonarLintSide
public class LogCountRegistrar implements CheckRegistrar {

	public void register(RegistrarContext context) {

		context.registerClassesForRepository(LogCountDefinition.REPOSITORY_KEY, checkClasses(), testCheckClasses());
	  }

	
	  public static List<Class<? extends JavaCheck>> checkClasses() {
		  
		  List<Class<? extends JavaCheck>> ret = new ArrayList<Class<? extends JavaCheck>>();
		  ret.add(LogCountRule.class);
		  return Collections.unmodifiableList(ret);
		  
	  }
	  
	  public static List<Class<? extends JavaCheck>> testCheckClasses() {
		  List<Class<? extends JavaCheck>> ret = new ArrayList<Class<? extends JavaCheck>>();
		  ret.add(LogCountRule.class);
		  return Collections.unmodifiableList(ret);
	  }

}
