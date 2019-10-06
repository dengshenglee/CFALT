///////////////////////////////////////////////////////////////////////////////
// Title:            P2.java
// Files:            badinput.txt, cflat.jlex, allTokens.in, Num.in, eof.txt,ErrMsg.java, Makefile, sym.java
// Semester:         CS536 Fall 2019
//
// Author:           Lex Li, 
// Email:            xli2242@wisc.edu
// CS Login:         lex
// Lecturer's Name:  Aws Albarghouthi
// Lab Section:      001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Ruokun Xu
// Email:            rxu83@wisc.edu
// CS Login:         ruokun
// Lecturer's Name:  Aws Albarghouthi
///////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the cflat scanner.
 * It set up to all the tokens, including valid reserve words,
 * identifiers, String literals, digits, and comments. It also 
 * tests the badinput and over large numbers, row and line number
 * of the tokens, whether is a eof at end of file.
 * 
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex


        //check eof
        System.out.println("Check eof:");
        eofCheck();
        CharNum.num = 1;
        
        // test the line and row number
        System.out.println("test line and row number");
        RowsCols_Num();
        CharNum.num = 1;

        // test all the bad input
        System.out.println("test badinput");
        badInput();
        CharNum.num = 1; 

        // test all tokens
        System.out.println("test all tokens:");
        testAllTokens();
        CharNum.num = 1;
        
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.in
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex my_scanner = new Yylex(inFile);
        Symbol my_token = my_scanner.next_token();
        while (my_token.sym != sym.EOF) {
            switch (my_token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)my_token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)my_token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)my_token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            my_token = my_scanner.next_token();
        } // end while
        outFile.close();
    }
    /**
     * eofCheck()
     * 
     * open and read from file eof
     * 
     * check that eof is reached and output message to eof.out
     * 
     * if input file contains eof, then it would print 
     * "Checked! Exsit EOF at at end of the file."
     * otherwise, nothing prints out.
     * 
     */
    private static void eofCheck() throws IOException{
        FileReader inFile = null;
        try {
            inFile = new FileReader("eof.txt");

        } catch (FileNotFoundException e) {
            System.err.println("File eof not found.");
            System.exit(-1);
        } 
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while(token.sym != sym.EOF){
            token = scanner.next_token();
        }
        if( token.sym == sym.EOF){
            System.out.println("Checked! Exsit EOF at at end of the file.");
        }
    }

    /**
     * badInput()
     *  
     * open and read the badinput.txt
     * 
     * using method from ErrMsg.java to print the warnings or errors
     * in the badinput.txt
     * 
     * 
     */
    private static void badInput() throws IOException{
        FileReader inFile = null;
        
        try {
            inFile = new FileReader("badinput.txt");

        } catch (FileNotFoundException e) {
            System.err.println("File badinput.txt not found.");
            System.exit(-1);
        }  

        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while(token.sym != sym.EOF){
            token = scanner.next_token();
        }
    }

    /**
     * RowsCols_Num()
     * 
     * open and read from file Num.in
     * 
     * write the position information in the Num.out
     * with formation as charnum(linenum) + sym:numberRepresentItself
     * 
     * we can verify correctness of the position information by comparing the input and output files
     * 
     */
    private static void RowsCols_Num() throws IOException{
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("Num.in");
            outFile = new PrintWriter(new FileWriter("Num.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex my_scanner = new Yylex(inFile);
        Symbol my_token = my_scanner.next_token();
        while (my_token.sym != sym.EOF) {
            outFile.println(((TokenVal)my_token.value).charnum + "(" 
	    + ((TokenVal)my_token.value).linenum + ")    sym # : " + my_token.sym);
            my_token = my_scanner.next_token();
        } // end while
        outFile.close(); 
    }

}
