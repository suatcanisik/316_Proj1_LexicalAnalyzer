import org.w3c.dom.ls.LSOutput;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    List<String> lines = new ArrayList<String>();
    int currentLine;
    int currentLocation;

    public LexicalAnalyzer(File file) throws FileNotFoundException {
        currentLine = 0;
        currentLocation = 0;

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println("added line: "+ line);
            lines.add(line);
        }
        scanner.close();
    }

    //return
    public Token getNextToken(){
        String line = lines.get(currentLine);
        String lexeme = findNextToken(line);

        Token token = new Token(lexeme,getTokenCode(lexeme));
        return token;
    }

    //return next lexeme
    public String findNextToken(String line){
        //System.out.println("findNextToken()   = "+ line);
        String token = "";

        while(true) {

            //assign letter and next_letter
            String letter, next_letter = "null";
            letter = line.substring(currentLocation, currentLocation + 1);
            if (currentLocation < line.length() - 1) {
                next_letter = line.substring(currentLocation + 1, currentLocation + 2);
            }
            //System.out.println("======"+token+", " +letter+", "+next_letter);

            //go next line
            if(next_letter.equals("null")){
                currentLocation = 0;
                currentLine++;
                return token + letter;
            }

            if(     letter.equals(" ")||
                    letter.equals("+")||
                    letter.equals("-")||
                    letter.equals("*")||
                    letter.equals(".")||
                    letter.equals("%")||
                    letter.equals("(")||
                    letter.equals(")")||
                    letter.equals(",")||
                    letter.equals(";")){
                currentLocation++;
                return letter;
            }else if (letter.equals("&")){
                currentLocation++;
                if(next_letter.equals("&")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("|")){
                currentLocation++;
                if(next_letter.equals("|")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("/")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals(":")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("=")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("<")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals(">")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("&")){
                currentLocation++;
                if(next_letter.equals("&")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }else if (letter.equals("!")){
                currentLocation++;
                if(next_letter.equals("=")){
                    currentLocation++;
                    return letter+next_letter;
                }
                return letter;
            }

            if(     next_letter.equals(" ")||
                    next_letter.equals("+")||
                    next_letter.equals("-")||
                    next_letter.equals("=")||
                    next_letter.equals("*")||
                    next_letter.equals(".")||
                    next_letter.equals(",")||
                    next_letter.equals("%")||
                    next_letter.equals("(")||
                    next_letter.equals(")")||
                    next_letter.equals(";")||
                    next_letter.equals("/")||
                    next_letter.equals(":")||
                    next_letter.equals("<")||
                    next_letter.equals(">")||
                    next_letter.equals("&")||
                    next_letter.equals("!")
            ){
                currentLocation++;
                return token+letter;
            }

            currentLocation++;
            token = token + letter;

        }

    }

    //find the TokenCode for the lexeme string
    private TokenCodes getTokenCode(String lexeme){

        TokenCodes token = TokenCodes.NULL;

        if(lexeme.equals(" ")){
            token = TokenCodes.NAL;
        }else if(lexeme.equals("&")){
            token = TokenCodes.NAL;
        }else if(lexeme.equals("|")){
            token = TokenCodes.NAL;
        }else if(lexeme.equals("+")){
            token = TokenCodes.PLUS;
        }else if(lexeme.equals("-")){
            token = TokenCodes.MINUS;
        }else if(lexeme.equals("*")){
            token = TokenCodes.TIMES;
        }else if(lexeme.equals("/")){
            token = TokenCodes.SLASH;
        }else if(lexeme.equals("=")){
            token = TokenCodes.EQL;
        }else if(lexeme.equals("==")){
            token = TokenCodes.DEQL;
        }else if(lexeme.equals("(")){
            token = TokenCodes.LPAREN;
        }else if(lexeme.equals(")")){
            token = TokenCodes.RPAREN;
        }else if(lexeme.equals(".")){
            token = TokenCodes.PERIOD;
        }else if(lexeme.equals(",")){
            token = TokenCodes.COMMA;
        }else if(lexeme.equals(";")){
            token = TokenCodes.SEMICOLON;
        }else if(lexeme.equals(":")){
            token = TokenCodes.COLON;
        }else if(lexeme.equals("!=")){
            token = TokenCodes.NEQ;
        }else if(lexeme.equals(":=")){
            token = TokenCodes.BECOMES;
        }else if(lexeme.equals("<")){
            token = TokenCodes.LSS;
        }else if(lexeme.equals("<=")){
            token = TokenCodes.LEQ;
        }else if(lexeme.equals(">")){
            token = TokenCodes.GTR;
        }else if(lexeme.equals(">=")){
            token = TokenCodes.GEQ;
        }else if(lexeme.equals("&&")){
            token = TokenCodes.AND;
        }else if(lexeme.equals("array")){
            token = TokenCodes.ARRAY;
        }else if(lexeme.equals("begin")){
            token = TokenCodes.BEGIN;
        }else if(lexeme.equals("case")){
            token = TokenCodes.CASE;
        }else if(lexeme.equals("const")){
            token = TokenCodes.CONST;
        }else if(lexeme.equals("%")){
            token = TokenCodes.DIV;
        }else if(lexeme.equals("do")){
            token = TokenCodes.DO;
        }else if(lexeme.equals("downto")){
            token = TokenCodes.DOWNTO;
        }else if(lexeme.equals("else")){
            token = TokenCodes.ELSE;
        }else if(lexeme.equals("end")){
            token = TokenCodes.END;
        }else if(lexeme.equals("file")){
            token = TokenCodes.FILE;
        }else if(lexeme.equals("for")){
            token = TokenCodes.FOR;
        }else if(lexeme.equals("function")){
            token = TokenCodes.FUNCTION;
        }else if(lexeme.equals("goto")){
            token = TokenCodes.GOTO;
        }else if(lexeme.equals("if")){
            token = TokenCodes.IF;
        }else if(lexeme.equals("in")){
            token = TokenCodes.IN;
        }else if(lexeme.equals("label")){
            token = TokenCodes.LABEL;
        }else if(lexeme.equals("mod")){
            token = TokenCodes.MOD;
        }else if(lexeme.equals("nil")){
            token = TokenCodes.NIL;
        }else if(lexeme.equals("not")){
            token = TokenCodes.NOT;
        }else if(lexeme.equals("of")){
            token = TokenCodes.OF;
        }else if(lexeme.equals("||")){
            token = TokenCodes.OR;
        }else if(lexeme.equals("packed")){
            token = TokenCodes.PACKED;
        }else if(lexeme.equals("procedure")){
            token = TokenCodes.PROCEDURE;
        }else if(lexeme.equals("program")){
            token = TokenCodes.PROGRAM;
        }else if(lexeme.equals("record")){
            token = TokenCodes.RECORD;
        }else if(lexeme.equals("repeat")){
            token = TokenCodes.REPEAT;
        }else if(lexeme.equals("set")){
            token = TokenCodes.SET;
        }else if(lexeme.equals("then")){
            token = TokenCodes.THEN;
        }else if(lexeme.equals("to")){
            token = TokenCodes.TO;
        }else if(lexeme.equals("type")){
            token = TokenCodes.TYPE;
        }else if(lexeme.equals("until")){
            token = TokenCodes.UNTIL;
        }else if(lexeme.equals("var")){
            token = TokenCodes.VAR;
        }else if(lexeme.equals("while")){
            token = TokenCodes.WHILE;
        }else if(lexeme.equals("with")){
            token = TokenCodes.WITH;
        }else if(isInteger(lexeme)){
            token = TokenCodes.NUMLIT;
        } else if(!notUnknown(lexeme)) {
            token = TokenCodes.UNKNKOWN;
        }else {
            token = TokenCodes.IDENT;
        }
        return token;
    }

    private boolean notUnknown(String lexeme) {

        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letter.matcher(lexeme);
        Matcher hasDigit = digit.matcher(lexeme);
        Matcher hasSpecial = special.matcher(lexeme);

        return hasLetter.find() && hasDigit.find() && hasSpecial.find();
    }

    public static boolean isInteger(String i) {
        try {
            Integer.parseInt(i);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public void output (){

        while (lines.size() != currentLine){
            Token token = getNextToken();
            token.print();
        }
        Token token = new Token("End of File",TokenCodes.EOF);
        token.print();
    }


}
