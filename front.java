import java.io.*;
import java.util.*;
public class front {
	
	static int charClass;
	static char lexeme[] = new char[100];
	static char nextChar;
	static int lexLen;
	static int token;
	static int nextToken;
	static File in_fp;
	
	
	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	static final int EOF = -1;
	
	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ASSIGN_OP = 20;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PARE = 25;
	static final int RIGHT_PARE = 26;
	
	static int lookup(char ch){
		switch (ch) {
		 case '(':
			 addChar();
			 nextToken = LEFT_PARE;
			 break;
		 case ')':
			 addChar();
			 nextToken = RIGHT_PARE;
			 break;
		 case '+':
			 addChar();
			 nextToken = ADD_OP;
			 break;
		 case '-':
			 addChar();
			 nextToken = SUB_OP;
			 break;
		 case '*':
			 addChar();
			 nextToken = MULT_OP;
			 break;
		 case '/':
			 addChar();
			 nextToken = DIV_OP;
			 break;
		 default:
			 addChar();
			 nextToken = 0;
			 break;
	 }
	 return nextToken;
	}
	
	static void addChar(){
		if (lexLen <= 98) {
			lexeme[lexLen++] = nextChar;
			lexeme[lexLen] = 0;
		} else
		System.out.println("Error - lexeme is too long \n");
	}
	static void getChar() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(in_fp));
		int nc;
		if ((nc = br.read()) != -1) { 
			nextChar = (char) nc;
			 if (Character.isLetter(nextChar))
			 	charClass = LETTER;
			 else if (Character.isDigit(nextChar))
			 	charClass = DIGIT;
			 else
			 	charClass = UNKNOWN;
		 } else
		 	charClass = -1;
		br.close();
	}
	
	static void getNonBlank() throws IOException {
		while (Character.isWhitespace(nextChar))
		getChar();
	}
	static int lex() throws IOException {
		lexLen = 0;
		 getNonBlank();
		 switch (charClass) {
			/* Identifiers */
			 case LETTER:
				 addChar();
				 getChar();
				 while (charClass == LETTER || charClass == DIGIT) {
					 addChar();
					 getChar();
				 }
				 nextToken = IDENT;
				 break;
			/* Integer literals */
			 case DIGIT:
				 addChar();
				 getChar();
				 while (charClass == DIGIT) {
					 addChar();
					 getChar();
				 }
				 nextToken = INT_LIT;
			 	break;
			/* Parentheses and operators */
			 case UNKNOWN:
				 lookup(nextChar);
				 getChar();
				 break;
				/* EOF */
			case EOF:
				 nextToken = EOF;
				 lexeme[0] = 'E';
				 lexeme[1] = 'O';
				 lexeme[2] = 'F';
				 lexeme[3] = '\0';
			 	break;
		 } /* End of switch */
		 
		 System.out.println("Next token is: " + nextToken + "Next lexeme is: " + lexeme + "\n");
		 return nextToken;
	}
	
	public static void main(String[]args) throws IOException{
		if ((in_fp = new File("C:\\Users\\kuwan_000\\Documents"
		          + "\\Georgia State\\GA State Spring 2020\\Programming Language Concepts\\test.txt")) == null)
		 	System.out.println("ERROR - cannot open file \n");
		 else {
		 	getChar();
		 do {
		 	lex();
		 } while (nextToken != 0);
		 }		
	}
}
