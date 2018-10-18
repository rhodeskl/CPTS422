package net.sf.eclipsecs.sample.checks;

import java.util.ArrayList;
import java.util.List;

//import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionCounter extends AbstractCheck {
  
  private int numExpressions;
  private int numOperators;
  private int numOperands;
  private int numUniqueOperators;
  private int numUniqueOperands;
  private List<Integer> foundOperators = new ArrayList<>();
  private List<String> foundOperands = new ArrayList<>();


  public ExpressionCounter() {
    this.numExpressions = 0;
    this.numOperators = 0;
    this.numOperands = 0;
    this.numUniqueOperators = 0;
    this.numUniqueOperands = 0;
  }
  
  public int getNumExpressions() {
    return numExpressions;
  }
  
  public int getNumOperators() {
    return numOperators;
  }
  
  public int getNumOperands() {
    return numOperands;
  }
  
  public int getNumUniqueOperators() {
    return numUniqueOperators;
  }

  public int getNumUniqueOperands() {
    return numUniqueOperands;
  }
  
  @Override
  public void beginTree(DetailAST rootAST) {
    numExpressions = 0;
    numOperators = 0;
    numOperands = 0;
    numUniqueOperators = 0;
    numUniqueOperands = 0;
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return getDefaultTokens();
  }

  @Override
  public int[] getDefaultTokens() {
    return new int [] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
                      TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
                      TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
                      TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
                      TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
                      TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
                      TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
                      TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
                      TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
                      TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
                      TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
                      TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
                      TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
                      TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
                      TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
                      TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
                      TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
                      TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
                      TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
                      TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
                      TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
                      TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
                      TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL};
  }

  @Override
  public int[] getRequiredTokens() {
    return getDefaultTokens();
  }

  @Override
  public void visitToken(DetailAST ast) {    
    if (ast.getType() == TokenTypes.ABSTRACT || ast.getType() == TokenTypes.ASSIGN || ast.getType() == TokenTypes.BAND 
            || ast.getType() == TokenTypes.BAND_ASSIGN || ast.getType() == TokenTypes.BNOT || ast.getType() == TokenTypes.BOR
            || ast.getType() == TokenTypes.BOR_ASSIGN || ast.getType() == TokenTypes.BSR || ast.getType() == TokenTypes.BSR_ASSIGN 
            || ast.getType() == TokenTypes.BXOR || ast.getType() == TokenTypes.BXOR_ASSIGN || ast.getType() == TokenTypes.COLON 
            || ast.getType() == TokenTypes.COMMA || ast.getType() == TokenTypes.DEC || ast.getType() == TokenTypes.DIV 
            || ast.getType() == TokenTypes.DIV_ASSIGN || ast.getType() == TokenTypes.DO_WHILE || ast.getType() == TokenTypes.DOT
            || ast.getType() == TokenTypes.ENUM || ast.getType() == TokenTypes.EQUAL ||  ast.getType() == TokenTypes.FINAL 
            || ast.getType() == TokenTypes.GE || ast.getType() == TokenTypes.GT || ast.getType() == TokenTypes.IMPORT 
            || ast.getType() == TokenTypes.INC || ast.getType() == TokenTypes.INDEX_OP || ast.getType() == TokenTypes.LAND 
            || ast.getType() == TokenTypes.LCURLY || ast.getType() == TokenTypes.LE || ast.getType() == TokenTypes.LITERAL_ASSERT
            || ast.getType() == TokenTypes.LITERAL_BOOLEAN || ast.getType() == TokenTypes.LITERAL_BREAK 
            || ast.getType() == TokenTypes.LITERAL_BYTE || ast.getType() == TokenTypes.LITERAL_CASE 
            || ast.getType() == TokenTypes.LITERAL_CATCH || ast.getType() == TokenTypes.LITERAL_CHAR 
            || ast.getType() == TokenTypes.LITERAL_CLASS || ast.getType() == TokenTypes.LITERAL_CONTINUE 
            || ast.getType() == TokenTypes.LITERAL_DEFAULT || ast.getType() == TokenTypes.LITERAL_DO 
            || ast.getType() == TokenTypes.LITERAL_DOUBLE || ast.getType() == TokenTypes.LITERAL_ELSE 
            || ast.getType() == TokenTypes.LITERAL_FALSE || ast.getType() == TokenTypes.LITERAL_FINALLY 
            || ast.getType() == TokenTypes.LITERAL_FLOAT || ast.getType() == TokenTypes.LITERAL_FOR || ast.getType() == TokenTypes.LITERAL_IF 
            || ast.getType() == TokenTypes.LITERAL_INSTANCEOF || ast.getType() == TokenTypes.LITERAL_INT 
            || ast.getType() == TokenTypes.LITERAL_INTERFACE || ast.getType() == TokenTypes.LITERAL_LONG || ast.getType() == TokenTypes.LITERAL_NATIVE
            || ast.getType() == TokenTypes.LITERAL_NEW || ast.getType() == TokenTypes.LITERAL_NULL || ast.getType() == TokenTypes.LITERAL_PRIVATE
            || ast.getType() == TokenTypes.LITERAL_PROTECTED || ast.getType() == TokenTypes.LITERAL_PUBLIC || ast.getType() ==  TokenTypes.LITERAL_RETURN
            || ast.getType() == TokenTypes.LITERAL_SHORT || ast.getType() == TokenTypes.LITERAL_STATIC || ast.getType() == TokenTypes.LITERAL_SUPER
            || ast.getType() == TokenTypes.LITERAL_SWITCH || ast.getType() == TokenTypes.LITERAL_SYNCHRONIZED || ast.getType() == TokenTypes.LITERAL_THIS
            || ast.getType() == TokenTypes.LITERAL_THROW || ast.getType() == TokenTypes.LITERAL_THROWS || ast.getType() == TokenTypes.LITERAL_TRANSIENT
            || ast.getType() == TokenTypes.LITERAL_TRUE || ast.getType() == TokenTypes.LITERAL_TRY || ast.getType() == TokenTypes.LITERAL_VOID 
            || ast.getType() == TokenTypes.LITERAL_VOLATILE || ast.getType() == TokenTypes.LITERAL_WHILE || ast.getType() == TokenTypes.LNOT 
            || ast.getType() == TokenTypes.LOR || ast.getType() == TokenTypes.LPAREN || ast.getType() == TokenTypes.METHOD_CALL || ast.getType() == TokenTypes.MINUS 
            || ast.getType() == TokenTypes.MINUS_ASSIGN || ast.getType() == TokenTypes.MOD || ast.getType() == TokenTypes.MOD_ASSIGN || ast.getType() == TokenTypes.NOT_EQUAL
            || ast.getType() == TokenTypes.PLUS || ast.getType() == TokenTypes.PLUS_ASSIGN || ast.getType() == TokenTypes.POST_DEC || ast.getType() == TokenTypes.POST_INC 
            || ast.getType() == TokenTypes.QUESTION || ast.getType() == TokenTypes.RBRACK || ast.getType() == TokenTypes.RCURLY || ast.getType() == TokenTypes.RPAREN 
            || ast.getType() == TokenTypes.SEMI || ast.getType() == TokenTypes.SL || ast.getType() == TokenTypes.SL_ASSIGN || ast.getType() == TokenTypes.SR 
            || ast.getType() == TokenTypes.SR_ASSIGN || ast.getType() == TokenTypes.STAR || ast.getType() == TokenTypes.STAR_ASSIGN || ast.getType() == TokenTypes.UNARY_MINUS 
            || ast.getType() == TokenTypes.UNARY_PLUS) {
      numOperators++;
      if (!foundOperators.contains(ast.getType())) {
      foundOperators.add(ast.getType());
      }
      numUniqueOperators = foundOperators.size();
    } else if (ast.getType() == TokenTypes.EXPR) {
      numExpressions++;
    } else if (ast.getType() == TokenTypes.NUM_DOUBLE || ast.getType() == TokenTypes.NUM_FLOAT || ast.getType() == TokenTypes.NUM_INT || ast.getType() == TokenTypes.NUM_LONG 
            || ast.getType() == TokenTypes.IDENT || ast.getType() == TokenTypes.STRING_LITERAL) {
      numOperands++;
      if (!foundOperands.contains(ast.getText())) {
        foundOperands.add(ast.getText());
      }
      numUniqueOperands = foundOperands.size();
    }
  }
    
  @Override
  public void finishTree(DetailAST rootAST){
      log(rootAST, "expressionCounter", numExpressions);
      log(rootAST, "numberOperators", numOperators);
      log(rootAST, "numberOperands", numOperands);
      log(rootAST, "numberUniqueOperators", numUniqueOperators);
      log(rootAST, "numberUniqueOperands", numUniqueOperands);
    }
}

