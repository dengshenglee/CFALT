/**
 * Main Class File:		P1.java
 * File:				SymTable.java
 * Semester:			Fall 2019
 * 
 * Author:				Lex Li
 * CS Login:			lex
 * Lecturer's name:		Aws Albarghouthi
 * Lab Section:			001
 * class SymTable will be used by the compiler to represent a symbol table: 
 * a data structure that stores the identifiers declared in the program being compiled (e.g., function and variable names) 
 * and information about each identifier (e.g., its type, where it will be stored at runtime). 
 * @author Lex Li
 *
 */
package p1;
import java.util.*;


public class SymTable {
	/**
	 * 
	 * The symbol table is implemented as a List of HashMaps.
	 * The HashMap keys will be Strings (the declared identifier names) and the associated information will be Syms (the Sym class). 
	 * For now, the only information in a Sym will be the type of the identifier, represented using a String (such as int and double). 
	 */
	private List< HashMap<String, Sym> > typeList;
	
	/**
	 * This is the constructor; it initialize the SymTable's List field to contain a single, empty HashMap.
	 */
	public SymTable() {
		this.typeList = new ArrayList<HashMap< String,Sym> >();
		typeList.add(new HashMap<String,Sym>());
	}
	
	/**
	 * If this SymTable's list is empty, throw an EmptySymTableException. 
	 * If either name or sym (or both) is null, throw a NullPointerException. 
	 * If the first HashMap in the list already contains the given name as a key, throw a DuplicateSymException. 
	 * Otherwise, add the given name and sym to the first HashMap in the list.
	 * @param name Name as the adding key
	 * @param sym  Sym as the association to the key name  
	 * @throws DuplicateSymException
	 * @throws EmptySymTableException
	 */
	public void addDecl(String name, Sym sym)
	throws DuplicateSymException, EmptySymTableException
	{
		if (typeList.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		if (name.equals(null) || sym.getType().equals(null)) {
			throw new NullPointerException();
		}
		
		if (typeList.get(0).containsKey(name)) {
			throw new DuplicateSymException();
		}else {
			typeList.get(0).put(name, sym);
		}
		
	}
	
	/**
	 * Add a new, empty HashMap to the front of the list.
	 */
	public void addScope() {
		typeList.add(0,new HashMap<String, Sym>());
	}
	
	/**
	 * If this SymTable's list is empty, throw an EmptySymTableException. 
	 * Otherwise, if the first HashMap in the list contains name as a key, return the associated Sym; 
	 * otherwise, return null.
	 * @param name The name in search of the first hashmap in the list
	 * @return Sym 	The association to the key name in searching if the first HashMap in the list contains name as a key
	 * @throws EmptySymTableException
	 */
	public Sym lookupLocal(String name) throws EmptySymTableException {
		if (typeList.isEmpty()) {
			throw new EmptySymTableException();
		}else if (typeList.get(0).containsKey(name)) {
			return typeList.get(0).get(name);
		}else {
			return null;
		}
	}
	
	/**
	 * If this SymTable's list is empty, throw an EmptySymTableException. 
	 * If any HashMap in the list contains name as a key, return the first associated Sym (i.e., the one from the HashMap that is closest to the front of the list); 
	 * otherwise, return null.
	 * @param name in search all the hashmaps in the list
	 * @return Sym The assciation to the key name in searching if any the hashmap the list contains name as a key
	 * @throws EmptySymTableException
	 */
	public Sym lookupGlobal(String name) throws EmptySymTableException{
		if (typeList.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		for (int i = 0; i < typeList.size(); i++) {
			Iterator<HashMap.Entry<String, Sym> > iterator = typeList.get(i).entrySet().iterator();
			while (iterator.hasNext()) {
				HashMap.Entry<String,Sym> entry = iterator.next();
				if (entry.getKey().equals(name)) {
					return entry.getValue();
				}
			}
		}
		//if no key of name contains
		return null;
		
	}
	
	/**
	 * If this SymTable's list is empty, throw an EmptySymTableException; 
	 * otherwise, remove the HashMap from the front of the list. 
	 * To clarify, throw an exception only if before attempting to remove, the list is empty (i.e. there are no HashMaps to remove).
	 * @throws EmptySymTableException
	 */
	public void removeScope() throws EmptySymTableException {
		if (typeList.isEmpty()) {
			throw new EmptySymTableException();
		}else {
			typeList.remove(0);
		}
	}
	
	/**
	 * This method is for debugging. 
	 * First, print "\nSym Table\n". 
	 * Then, for each HashMap M in the list, print M.toString() followed by a newline. 
	 * Finally, print one more newline. 
	 * 
	 */
	public void print() {
		System.out.println("\n" + "SymTable\n");
		for (HashMap<String, Sym> hashMap : typeList) {
			System.out.println(hashMap.toString() + "\n");
		}
		System.out.println("\n");
	}


}


