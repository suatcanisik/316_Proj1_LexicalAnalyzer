public class Token {

    String lexeme;
    TokenCodes tokenCode;

    Token(String lex, TokenCodes code){
        lexeme = lex;
        tokenCode = code;
    }

    void print (){
        System.out.print("Lexeme: '"+lexeme+"'" );
        for (int i=0 ; i<=12-lexeme.length();i++){System.out.print(" ");}
        System.out.print(" Reserved Word: " + tokenCode);
        System.out.println();
    }

}
