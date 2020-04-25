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
	static final int MOD_OP = 25;
	static final int LEFT_PARE = 26;
	static final int RIGHT_PARE = 27;
	
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
		 case '%':
			 addChar();
			 nextToken = MOD_OP;
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
		} else {
		    System.out.println("Error - lexeme is too long \n");
		}
	}
	static void getChar(BufferedReader br) throws IOException{
		int nc;
		if ((nc = br.read()) != -1) { 
			nextChar = (char) nc;
			 if (Character.isLetter(nextChar)) {
			 	charClass = LETTER;
			 }
			 else if (Character.isDigit(nextChar)) {
			 	charClass = DIGIT;
			 }
			 else {
			 	charClass = UNKNOWN;
			 }
		 } else {
		 	charClass = EOF;
		 }
	}
	
	static void getNonBlank(BufferedReader br) throws IOException {
		while (Character.isWhitespace(nextChar))
		getChar(br);
	}
	static int lex(BufferedReader br) throws IOException {
		lexLen = 0;
		 getNonBlank(br);
		 switch (charClass) {
			/* Identifiers */
			 case LETTER:
				 addChar();
				 getChar(br);
				 while (charClass == LETTER || charClass == DIGIT) {
					 addChar();
					 getChar(br);
				 }
				 nextToken = IDENT;
				 break;
			/* Integer literals */
			 case DIGIT:
				 addChar();
				 getChar(br);
				 while (charClass == DIGIT) {
					 addChar();
					 getChar(br);
				 }
				 nextToken = INT_LIT;
			 	break;
			/* Parentheses and operators */
			 case UNKNOWN:
				 lookup(nextChar);
				 getChar(br);
				 break;
			/* EOF */
			case EOF:
				 nextToken = 0;
				 lexeme[0] = 0;
			 	break;
		 } /* End of switch */
		 System.out.print("Next token is: " + nextToken + " Next Lexeme is: ");
		 for(int i=0; i<lexeme.length;i++) {
		 System.out.print(lexeme[i]);
		 }
		 System.out.print("\n");
		 expr(br);
		 return nextToken;
	}
	
	 /* expr
	 Parses strings in the language generated by the rule:
	 <expr> -> <term> {(+ | -) <term>}
	 */
	static void expr(BufferedReader br) throws IOException {
		 System.out.println("Enter <expr>\n");
		/* Parse the first term */
		 term(br);
		/* As long as the next token is + or -, get
		 the next token and parse the next term */
		 while (nextToken == ADD_OP || nextToken == SUB_OP) {
			 lex(br);
			 term(br);
		 }
		 System.out.println("Exit <expr>\n");
	}

	/* term
	 Parses strings in the language generated by the rule:
	 <term> -> <factor> {(* | / | % ) <factor>}
	 */
	static void term(BufferedReader br) throws IOException {
		System.out.println("Enter <term>\n");
		/* Parse the first factor */
		 factor(br);
		/* As long as the next token is * or /, get the
		 next token and parse the next factor */
		 while (nextToken == MULT_OP || nextToken == DIV_OP || nextToken == MOD_OP ) {
			 lex(br);
			 factor(br);
		 }
		 System.out.println("Exit <term>\n");
	}

	/* factor
	 Parses strings in the language generated by the rule:
	 <factor> -> id | int_constant | ( <expr> )
	 */
	static void factor(BufferedReader br) throws IOException {
		System.out.println("Enter <factor>\n");
		/* Determine which RHS */
		 if (nextToken == IDENT || nextToken == INT_LIT)
			/* Get the next token */
			 lex(br);
			/* If the RHS is ( <expr> ), call lex to pass over the
			 left parenthesis, call expr, and check for the right
			 parenthesis */
		 else { if (nextToken == LEFT_PARE) {
			 lex(br);
			 expr(br);
			 if (nextToken == RIGHT_PARE)
			 	lex(br);
			 else
			 	error();
			 }
			/* It was not an id, an integer literal, or a left
			 parenthesis */
			 else
			 error();
		 }
		 System.out.println("Exit <factor>\n");
	}
	
	static void error() {
		System.out.println("Error = Symbol not found");
	}
	
	public static void main(String[]args) throws IOException{
		System.out.println("Trace of the parse of (sum + 47): ");
		if ((in_fp = new File("C:\\Users\\kuwan_000\\Documents"
		          + "\\Georgia State\\GA State Spring 2020\\Programming Language Concepts\\test1.txt")) == null) {
			System.out.println("ERROR - cannot open file \n");
		}
		 else {
			BufferedReader br = new BufferedReader(new FileReader(in_fp));
		 	getChar(br);
		 do {
		 	lex(br);
		 } while (nextToken != 0);
		 br.close();
		 }
	}
}
