class MyClass {
  MyClass(MyClass mc) { }
  
  int     foo1() { 
	  
	  int x = 0; 
	
	  log.info("Teste");
	  
	  return 0; 
	  
  }
  void    foo2(int value) { }
  int     foo3(int value) { log.info("teste");return 0; } // Noncompliant
  Object  foo4(int value) { return null; }
  MyClass foo5(MyClass value) {return null; } // Noncompliant
  
  int     foo6(int value, String name) { return 0; }
  int     foo7(int ... values) { return 0;}
}