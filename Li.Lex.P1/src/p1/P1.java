package p1;

/**
 * Title:			p1
 * Files:			DuplicateSymException.java
 * 					EmptySymTableException.java
 * 					Sym.java
 * 					SymTable.java
 * 					P1.java
 * Semester:		Fall 2019
 * 
 * Author:			Lex Li
 * Email:			xli2242@wisc.edu
 * CS Login:		lex
 * Lecturer's name:	Aws Albarghouthi
 * Lab Section:		001
 * 
 * This is a class whose purpose is to test the SymTable and Sym class, which
 * provides the following operations:
 * class Sym:
 * 		 no-arg constructor		--create the Sym with the given type
 * 		 String getType()		--return the type of Sym
 * class SymTable: 
 *       no-arg constructor   					-- create an list contains a single, empty list
 *       void addScope()						-- add a new, empty HashMap to the front of the list
 *       void removeScope()						-- remove the HashMap from the front of the list. If the list is empty, throw an EmptySymTableException
 *       Sym  lookupLocal(String name)			-- If the SymTable is empty, throw an EmptySymTableException; 
 *       										   Return the associating sym name to the key name if the first hashmap in the list contains the key name;
 *       										   otherwise, return null
 *       Sym  lookupGlobal(String name)			-- If this SymTable's list is empty, throw an EmptySymTableException. 
 *       										   If any HashMap in the list contains name as a key, return the first associated Sym (i.e., the one from the HashMap that is closest to the front of the list); 
 * 											       otherwise, return null.
 *       void addDecl(String name, Sym sym)     -- If this SymTable's list is empty, throw an EmptySymTableException. 
 *  											   If either name or sym (or both) is null, throw a NullPointerException. 
 * 												   If the first HashMap in the list already contains the given name as a key, throw a DuplicateSymException. 
 * 												   Otherwise, add the given name and sym to the first HashMap in the list.
 *       void print()							-- print each HashMap in the list in a certain format
 * This code tests every Sym and SymTableoperation, including both correct and
 * bad calls to the operation that can throw an exception.
 * If it produces output test finish, it pass the test, or the err occur in that block.
 *
 */




public class P1 {
	public static void main(String[] args) {
		
		System.out.println("test begin...");
		
		System.out.println("testing class Sym");
		
		System.out.println("testing getType()...");
	
		//test the construction of class Sym
		Sym sym_int = new Sym("int");
		Sym sym_string = new Sym("string");
		//test the method getType() of class Sym
		if (!sym_int.getType().equals("int")) {
			System.out.println("err in class sym");
		}else {
			System.out.println("checked! method getType() returns the type of class Sym");
		}
		
		System.out.println("testing for class SymTable");
		
		SymTable symTable_test = new SymTable();
		
		
		//there is one single, empty hashmap in the list, to test the addDecl() function,
		//we firstly remove it by using removeScope
		
		//we define a int cal_time to calculate the times
		int cal_time = 0;
		
		System.out.println("testing removeScope()...");
		
		do {
			cal_time++;
			System.out.println("method removeScope() the " + cal_time + " time...");
			
			try {
				symTable_test.removeScope();
				
			} catch (EmptySymTableException e) {
				System.out.println("checked! removeScope() throws an EmptySymTableException when the list is empty");
				if (cal_time == 2) {
					//at the second time, throws an EmptySymTabelException, so the list is empty and the list only contains a single hashmap at first
					System.out.println("Because the method have been used once and now the list is empty, we check the list contain a single hashmap at first");
				}
			}
		} while (cal_time < 2);
			
		//now the list is empty
		System.out.println("now the list of SymTable is empty");
		System.out.println("testing addDecl(String name, Sym sym)...");
		//reset the cal_time as 0
		cal_time = 0;
		do {
			cal_time++;
			try {
				System.out.println("we attempt to use method addDecl(2,sym_int) for the " + cal_time + " time...");
				symTable_test.addDecl("2", sym_int);
				if (cal_time == 2) {
					
				}
			} catch (EmptySymTableException E_e) {
				System.out.println("checked! addDecl() throws EmptySymTableException when the list is empty.");
				//then we add a hashmap in the list so as to test whether it would add a decl in it
				System.out.println("and we use method addScope() to add an empty hashmap in the list");
				symTable_test.addScope();
			}catch (DuplicateSymException D_e) {
				//when the cal_time == 2, it should have added a decl. Now the cal_time is 3, adding a same one would throw this exception
				System.out.println("checked! addDecl() throws DuplicateSymException when the first hashmap in the list already contains the given name as a key");
			}
		} while (cal_time < 3);
		// when cal_time == 3, quit
		
		//to test whether addDecl() would throws NullPointerExceptions
		Sym sym_null = new Sym(null);
		String string_null = null;
		cal_time = 0;
		System.out.println("if we attempt to add decl contains any null...");
		//we test 3 times by adding something either name or sym is null and both are null
		do {
			try {
				switch (cal_time) {
				case 0:
					cal_time++;
					System.out.println("addDecl(string_null, sym_null)...");
					symTable_test.addDecl(string_null, sym_int);
					break;
				case 1:
					cal_time++;
					System.out.println("addDecl(string_not_null, sym_null)...");
					symTable_test.addDecl("a", sym_null);
					break;
				case 2:
					cal_time++;
					System.out.println("addDecl(string_null, sym_null)...");
					symTable_test.addDecl(string_null, sym_null);
					break;

				}
			} catch (NullPointerException e) {
				switch (cal_time) {
				case 1:
					System.out.println("cheched! addDecl() throws a NullPointerException when name is null");
					break;
				case 2:
					System.out.println("cheched! addDecl() throws a NullPointerException when sym is null");
					break;
				case 3:
					System.out.println("cheched! addDecl() throws a NullPointerException when both sym and name are null");
					break;

				}
			}catch (EmptySymTableException E_e) {

			}catch (DuplicateSymException D_e) {

			}
		} while (cal_time < 3);
		
		cal_time = 0;
		//empty the list
		try {
			System.out.println("we remove hashmap in the list using removeScope() and now the list is empty...");
			symTable_test.removeScope();
		} catch (EmptySymTableException e) {
			//
		}
		
		
		System.out.println("testing lookupLocal(String name)...");
		//add a <2,int> into the hashmap in the list
		do {
			cal_time++;
			try {
				System.out.println("method lookupLocal for the " + cal_time + " time...");
				
				symTable_test.lookupLocal("2");
				if (cal_time == 2) {
					if (symTable_test.lookupLocal("2").getType().equals("int")) {
						System.out.println("check! Using method lookupLocal('2') that 2 is in the first hashmap of the list, return the associated Sym"  );
					}
				}
				if (cal_time == 3) {
					if (symTable_test.lookupLocal("3") == null) {
						System.out.println("check! Using method lookupLocal('3') that 3 is not in the first hashmap of the list, return null");
					}
				}
				
			} catch (EmptySymTableException e) {
				if (cal_time == 1) {
					System.out.println("check! lookupLocal() throws an EmptySymTableException when the list is empty.");
					symTable_test.addScope();
					try {
						System.out.println("now we use method addScope() and addDecl('2',sym_int) so the list contain a name as '2' and associated Sym sym_int...");
						symTable_test.addDecl("2", sym_int);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		} while (cal_time < 3);
		
		//empty the list again to test lookupGlobal(Stirng name)
		System.out.println("now we empty the list again");
		
		while (true) {
			
			try {
				symTable_test.removeScope();
			} catch (EmptySymTableException e) {
				break;
			}
			
		}
		
		
		System.out.println("testing lookupGlobal(String name)...");
		
		try {
			symTable_test.lookupGlobal("2");
		} catch ( EmptySymTableException e) {
			// TODO: handle exception
			System.out.println("checked! lookupGlobal throws an EmptySymTableException when the list is empty.");
		}
		//we add 2 hashmap in the list, the first one contains <2,int> and the second one contains <2,string>
		System.out.println("we add 2 hashmap in the list, the first one contains <2,int> and the second one contains <2,string>");
		symTable_test.addScope();
		try {
			symTable_test.addDecl("2", sym_string);
		} catch (Exception e) {

		}
		symTable_test.addScope();
		try {
			symTable_test.addDecl("2", sym_int);
		} catch (Exception e) {

		}
		
		
				
		try {
			System.out.println("we look up 2 in global...");
			if (symTable_test.lookupGlobal("2").getType().equals("int")) {
				System.out.println("check! method lookupGlobal() would return the first associated Sym if any HashMap in the list contains name as a key ");
			}else {
				System.out.println("errors in lookupGlobal(String name)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			System.out.println("we look up 3 in global, which is actually not in the list... ");
			if (symTable_test.lookupGlobal("3") == null) {
				System.out.println("checked! method lookupGlobal() would return null if no name matches");
			}else {
				System.out.println("errors in lookupGlobal(String name)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("test finish");

		

	}

}
