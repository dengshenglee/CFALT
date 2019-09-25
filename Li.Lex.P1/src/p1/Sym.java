/**
 * Main Class File:		P1.java
 * File:				Sym.java
 * Semester:			Fall 2019
 * 
 * Author:				Lex Li
 * CS Login:			lex
 * Lecturer's name:		Aws Albarghouthi
 * Lab Section:			001
 * 
 * class Sym acts as type of the identifier.
 * For now, the only information in a Sym will be the type of the identifier, represented using a String (such as int, double).
 * @author Lex Li
 *
 */
package p1;

public class Sym {

	private String type;
	
	/**
	 * This is the constructor; it initialize the Sym to have the given type.
	 * @param type
	 */
	public Sym(String type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	/**
	 * Return this Sym's type.
	 * @return type The type of Sym
	 */
	public String getType() {
		return type;
	}

	/**
	 * Return a String representation of Sym's type. 
	 * (This method will be changed later in a future project when more information is stored in a Sym.)
	 */
	@Override
	public String toString() {
		return type;
	}
	
	
	
}
