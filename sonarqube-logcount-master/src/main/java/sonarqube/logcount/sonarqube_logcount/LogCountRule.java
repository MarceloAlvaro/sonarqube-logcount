package sonarqube.logcount.sonarqube_logcount;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
 
@Rule(
		key = "LogCountCheck",
		name = "Count the number of logged lines in code",
		description = "For a class there is a minimum number of logged lines.",
		priority = Priority.INFO,
		tags = {"logcount"})
public class LogCountRule extends IssuableSubscriptionVisitor {
	
	final static NumberFormat fmt = new DecimalFormat("##0.00");
	
	private float logCount = 0.0f;

	private float totalLines = 0.0f;

	@RuleProperty(
		    defaultValue = "10.0",
		description = "Minumum (%) of log lines per source .java file")
	private String minimumLogLine = "10.0";
	
  @Override
  public List<Kind> nodesToVisit() {
     return Collections.singletonList(Kind.CLASS);
  }
  
  @Override
	public void visitNode(Tree tree) {
	  ClassTree classTree = (ClassTree) tree;
	  
	  for (Tree m:classTree.members()) {
		  
		  if (m.is(Kind.METHOD)) {

			  MethodTree method = (MethodTree) m;
			  for (StatementTree st : method.block().body()) {
				  totalLines++;
				  System.out.println(st.firstToken().text());
				  if (st.firstToken().text().equals("log")) {
					  
					  logCount++;
					  
				  }
				  
			  }

		  }
	  }
	  
	  System.out.println("Total log = " + logCount);
	  
	  float percentual = (logCount / totalLines) * 100;
	  
	  if (percentual < Float.parseFloat(minimumLogLine)) {

		  reportIssue(classTree, "Numero de linhas de log invalido! Total linhas = " + totalLines + " - Total logs = " + logCount + " ("+ fmt.format(percentual) + "%) ");
		  
	  }

	  
	}
 
}