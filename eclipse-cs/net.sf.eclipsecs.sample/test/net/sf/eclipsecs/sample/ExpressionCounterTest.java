package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.ExpressionCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class ExpressionCounterTest {
  private ExpressionCounter expressionCounter;
  private DetailAST astMock;
  
  @Before
  public void setUp() {
    expressionCounter = new ExpressionCounter();
    astMock = PowerMockito.mock(DetailAST.class);
  }
  
  @Test
  public void testGetNumExpressions() {
    // getNumExpressions should return the number of expressions
    // numExpressions is set to 0 so getNumExpressions should return 0
    assertEquals(0, expressionCounter.getNumExpressions());
  }
  
  @Test
  public void testGetNumOperators() {
    // getNumOperators should return the number of operators
    // numOperators is set to 0, so getNumOperators should return 0
    assertEquals(0, expressionCounter.getNumOperators());
  }
  
  @Test
  public void testGetNumOperands() {
    // getNumOperands should return the number of operands
    // numOperands is set to 0, so getNumOperands should return 0
    assertEquals(0, expressionCounter.getNumOperands());
  }
  
  @Test 
  public void testGetNumUniqueOperators() {
    // getNumUnqiueOperators should return the number of unique operators
    // numUniqueOperators is set to 0, getNumUniqueOperators should return 0
    assertEquals(0, expressionCounter.getNumUniqueOperators());
  }
  
  @Test
  public void testGetNumUniqueOperands() {
    // getNumUniqueOperands should return the number of unique operands
    // numUniqueOperands is set to 0, getNumUniqueOperands should return 0
    assertEquals(0, expressionCounter.getNumUniqueOperands());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    // the acceptable token array should contain 106 entries
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
        TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, 
        TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, 
        TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, 
        TokenTypes.EQUAL, TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, 
        TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL};
    int[] actual = expressionCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    // the default token array should contain 106 entries
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
        TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, 
        TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, 
        TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, 
        TokenTypes.EQUAL, TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, 
        TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL};
    int[] actual = expressionCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test 
  public void testGetRequiredTokens() {
    // the required tokens array should only contain 106 entries
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
        TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, 
        TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, 
        TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, 
        TokenTypes.EQUAL, TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, 
        TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL};
    int[] actual = expressionCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test 
  public void testVistToken() {
    // visit token should increment numExpressions by 1 if the token type is a expression
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.EXPR);
    expressionCounter.visitToken(astMock);
    assertEquals(1, expressionCounter.getNumExpressions());
    
    // visit token should increment numOperators and numUniqueOperators by # if the token type is an operator 
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ABSTRACT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BAND);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BAND_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BNOT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BOR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BOR_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BSR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BSR_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BXOR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BXOR_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.COLON);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.COMMA);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DEC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DIV);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DIV_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DO_WHILE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DOT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ENUM);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.EQUAL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.FINAL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.GE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.GT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.IMPORT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.INC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.INDEX_OP);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LAND);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LCURLY);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_ASSERT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BOOLEAN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BREAK);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BYTE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CASE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CATCH);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CHAR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CLASS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CONTINUE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DEFAULT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DO);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DOUBLE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_ELSE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FALSE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FINALLY);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FLOAT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FOR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_IF);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INSTANCEOF);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INTERFACE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_LONG);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NATIVE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NEW);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NULL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PRIVATE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PROTECTED);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PUBLIC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_RETURN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SHORT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_STATIC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SUPER);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SWITCH);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SYNCHRONIZED);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THIS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THROW);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THROWS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRANSIENT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRUE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRY);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_VOID);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_VOLATILE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_WHILE);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LNOT);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LOR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LPAREN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.METHOD_CALL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MINUS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MINUS_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MOD);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MOD_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NOT_EQUAL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.PLUS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.PLUS_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.POST_DEC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.POST_INC);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.QUESTION);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RBRACK);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RCURLY);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RPAREN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SEMI);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SL);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SL_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SR_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STAR);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STAR_ASSIGN);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.UNARY_MINUS);
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.UNARY_PLUS);
    expressionCounter.visitToken(astMock);
    
    assertEquals(98, expressionCounter.getNumOperators());
    assertEquals(98, expressionCounter.getNumUniqueOperators());
    
    // visit token should increment numOperands and numUniqueOperands by # if the token falls under any of the token types
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_DOUBLE);
    Mockito.when(astMock.getText()).thenReturn("3.3");
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_FLOAT);
    Mockito.when(astMock.getText()).thenReturn("1.999");
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_INT);
    Mockito.when(astMock.getText()).thenReturn("1");
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_LONG);
    Mockito.when(astMock.getText()).thenReturn("4294967296");
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.IDENT);
    Mockito.when(astMock.getText()).thenReturn("hello");
    expressionCounter.visitToken(astMock);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STRING_LITERAL);
    Mockito.when(astMock.getText()).thenReturn("abc");
    expressionCounter.visitToken(astMock);
    
    assertEquals(6, expressionCounter.getNumOperands());
    assertEquals(6, expressionCounter.getNumUniqueOperands());
    
    // visit token should not increment numExpressions, numOperators, and numOperands
    boolean isNotAcceptableToken = false;
    int token = -1;
    Random random = new Random();
    // this loop will generate a random token that is not an operator, an operand, or an expression
    while(isNotAcceptableToken == false) {
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(token != TokenTypes.ABSTRACT && token != TokenTypes.ASSIGN && token != TokenTypes.BAND 
            && token != TokenTypes.BAND_ASSIGN && token != TokenTypes.BNOT && token != TokenTypes.BOR
            && token != TokenTypes.BOR_ASSIGN && token != TokenTypes.BSR && token != TokenTypes.BSR_ASSIGN 
            && token != TokenTypes.BXOR && token != TokenTypes.BXOR_ASSIGN && token != TokenTypes.COLON 
            && token != TokenTypes.COMMA && token != TokenTypes.DEC && token != TokenTypes.DIV 
            && token != TokenTypes.DIV_ASSIGN && token != TokenTypes.DO_WHILE && token != TokenTypes.DOT
            && token != TokenTypes.ENUM && token != TokenTypes.EQUAL &&  token != TokenTypes.FINAL 
            && token != TokenTypes.GE && token != TokenTypes.GT && token != TokenTypes.IMPORT 
            && token != TokenTypes.INC && token != TokenTypes.INDEX_OP && token != TokenTypes.LAND 
            && token != TokenTypes.LCURLY && token != TokenTypes.LE && token != TokenTypes.LITERAL_ASSERT
            && token != TokenTypes.LITERAL_BOOLEAN && token != TokenTypes.LITERAL_BREAK 
            && token != TokenTypes.LITERAL_BYTE && token != TokenTypes.LITERAL_CASE 
            && token != TokenTypes.LITERAL_CATCH && token != TokenTypes.LITERAL_CHAR 
            && token != TokenTypes.LITERAL_CLASS && token != TokenTypes.LITERAL_CONTINUE 
            && token != TokenTypes.LITERAL_DEFAULT && token != TokenTypes.LITERAL_DO 
            && token != TokenTypes.LITERAL_DOUBLE && token != TokenTypes.LITERAL_ELSE 
            && token != TokenTypes.LITERAL_FALSE && token != TokenTypes.LITERAL_FINALLY 
            && token != TokenTypes.LITERAL_FLOAT && token != TokenTypes.LITERAL_FOR && token != TokenTypes.LITERAL_IF 
            && token != TokenTypes.LITERAL_INSTANCEOF && token != TokenTypes.LITERAL_INT 
            && token != TokenTypes.LITERAL_INTERFACE && token != TokenTypes.LITERAL_LONG && token != TokenTypes.LITERAL_NATIVE
            && token != TokenTypes.LITERAL_NEW && token != TokenTypes.LITERAL_NULL && token != TokenTypes.LITERAL_PRIVATE
            && token != TokenTypes.LITERAL_PROTECTED && token != TokenTypes.LITERAL_PUBLIC && token !=  TokenTypes.LITERAL_RETURN
            && token != TokenTypes.LITERAL_SHORT && token != TokenTypes.LITERAL_STATIC && token != TokenTypes.LITERAL_SUPER
            && token != TokenTypes.LITERAL_SWITCH && token != TokenTypes.LITERAL_SYNCHRONIZED && token != TokenTypes.LITERAL_THIS
            && token != TokenTypes.LITERAL_THROW && token != TokenTypes.LITERAL_THROWS && token != TokenTypes.LITERAL_TRANSIENT
            && token != TokenTypes.LITERAL_TRUE && token != TokenTypes.LITERAL_TRY && token != TokenTypes.LITERAL_VOID 
            && token != TokenTypes.LITERAL_VOLATILE && token != TokenTypes.LITERAL_WHILE && token != TokenTypes.LNOT 
            && token != TokenTypes.LOR && token != TokenTypes.LPAREN && token != TokenTypes.METHOD_CALL && token != TokenTypes.MINUS 
            && token != TokenTypes.MINUS_ASSIGN && token != TokenTypes.MOD && token != TokenTypes.MOD_ASSIGN && token != TokenTypes.NOT_EQUAL
            && token != TokenTypes.PLUS && token != TokenTypes.PLUS_ASSIGN && token != TokenTypes.POST_DEC && token != TokenTypes.POST_INC 
            && token != TokenTypes.QUESTION && token != TokenTypes.RBRACK && token != TokenTypes.RCURLY && token != TokenTypes.RPAREN 
            && token != TokenTypes.SEMI && token != TokenTypes.SL && token != TokenTypes.SL_ASSIGN && token != TokenTypes.SR 
            && token != TokenTypes.SR_ASSIGN && token != TokenTypes.STAR && token != TokenTypes.STAR_ASSIGN && token != TokenTypes.UNARY_MINUS 
            && token != TokenTypes.UNARY_PLUS && token != TokenTypes.EXPR && token != TokenTypes.NUM_DOUBLE && token != TokenTypes.NUM_FLOAT 
            && token != TokenTypes.NUM_INT && token != TokenTypes.NUM_LONG && token != TokenTypes.IDENT && token != TokenTypes.STRING_LITERAL) {
        isNotAcceptableToken = true;
      }
    }
    
    Mockito.when(astMock.getType()).thenReturn(token);
    expressionCounter.visitToken(astMock);
    assertEquals(1, expressionCounter.getNumExpressions());
    assertEquals(98, expressionCounter.getNumOperators());
    assertEquals(6, expressionCounter.getNumOperands());
    
    // visit token should not increment numUniqueOperators
    isNotAcceptableToken = false;
    // this loop will generate a random token that matches on of the operators
    while(isNotAcceptableToken == false) {
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(token == TokenTypes.ABSTRACT || token == TokenTypes.ASSIGN || token == TokenTypes.BAND 
            || token == TokenTypes.BAND_ASSIGN || token == TokenTypes.BNOT || token == TokenTypes.BOR
            || token == TokenTypes.BOR_ASSIGN || token == TokenTypes.BSR || token == TokenTypes.BSR_ASSIGN 
            || token == TokenTypes.BXOR || token == TokenTypes.BXOR_ASSIGN || token == TokenTypes.COLON 
            || token == TokenTypes.COMMA || token == TokenTypes.DEC || token == TokenTypes.DIV 
            || token == TokenTypes.DIV_ASSIGN || token == TokenTypes.DO_WHILE || token == TokenTypes.DOT
            || token == TokenTypes.ENUM || token == TokenTypes.EQUAL ||  token == TokenTypes.FINAL 
            || token == TokenTypes.GE || token == TokenTypes.GT || token == TokenTypes.IMPORT 
            || token == TokenTypes.INC || token == TokenTypes.INDEX_OP || token == TokenTypes.LAND 
            || token == TokenTypes.LCURLY || token == TokenTypes.LE || token == TokenTypes.LITERAL_ASSERT
            || token == TokenTypes.LITERAL_BOOLEAN || token == TokenTypes.LITERAL_BREAK 
            || token == TokenTypes.LITERAL_BYTE || token == TokenTypes.LITERAL_CASE 
            || token == TokenTypes.LITERAL_CATCH || token == TokenTypes.LITERAL_CHAR 
            || token == TokenTypes.LITERAL_CLASS || token == TokenTypes.LITERAL_CONTINUE 
            || token == TokenTypes.LITERAL_DEFAULT || token == TokenTypes.LITERAL_DO 
            || token == TokenTypes.LITERAL_DOUBLE || token == TokenTypes.LITERAL_ELSE 
            || token == TokenTypes.LITERAL_FALSE || token == TokenTypes.LITERAL_FINALLY 
            || token == TokenTypes.LITERAL_FLOAT || token == TokenTypes.LITERAL_FOR || token == TokenTypes.LITERAL_IF 
            || token == TokenTypes.LITERAL_INSTANCEOF || token == TokenTypes.LITERAL_INT 
            || token == TokenTypes.LITERAL_INTERFACE || token == TokenTypes.LITERAL_LONG || token == TokenTypes.LITERAL_NATIVE
            || token == TokenTypes.LITERAL_NEW || token == TokenTypes.LITERAL_NULL || token == TokenTypes.LITERAL_PRIVATE
            || token == TokenTypes.LITERAL_PROTECTED || token == TokenTypes.LITERAL_PUBLIC || token ==  TokenTypes.LITERAL_RETURN
            || token == TokenTypes.LITERAL_SHORT || token == TokenTypes.LITERAL_STATIC || token == TokenTypes.LITERAL_SUPER
            || token == TokenTypes.LITERAL_SWITCH || token == TokenTypes.LITERAL_SYNCHRONIZED || token == TokenTypes.LITERAL_THIS
            || token == TokenTypes.LITERAL_THROW || token == TokenTypes.LITERAL_THROWS || token == TokenTypes.LITERAL_TRANSIENT
            || token == TokenTypes.LITERAL_TRUE || token == TokenTypes.LITERAL_TRY || token == TokenTypes.LITERAL_VOID 
            || token == TokenTypes.LITERAL_VOLATILE || token == TokenTypes.LITERAL_WHILE || token == TokenTypes.LNOT 
            || token == TokenTypes.LOR || token == TokenTypes.LPAREN || token == TokenTypes.METHOD_CALL || token == TokenTypes.MINUS 
            || token == TokenTypes.MINUS_ASSIGN || token == TokenTypes.MOD || token == TokenTypes.MOD_ASSIGN || token == TokenTypes.NOT_EQUAL
            || token == TokenTypes.PLUS || token == TokenTypes.PLUS_ASSIGN || token == TokenTypes.POST_DEC || token == TokenTypes.POST_INC 
            || token == TokenTypes.QUESTION || token == TokenTypes.RBRACK || token == TokenTypes.RCURLY || token == TokenTypes.RPAREN 
            || token == TokenTypes.SEMI || token == TokenTypes.SL || token == TokenTypes.SL_ASSIGN || token == TokenTypes.SR 
            || token == TokenTypes.SR_ASSIGN || token == TokenTypes.STAR || token == TokenTypes.STAR_ASSIGN || token == TokenTypes.UNARY_MINUS 
            || token == TokenTypes.UNARY_PLUS) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    expressionCounter.visitToken(astMock);
    assertEquals(98, expressionCounter.getNumUniqueOperators());
    
    // visit token should not increment numUniqueOperands
    token = random.nextInt(6);
    List<Integer> knownOperands = new ArrayList<>();
    List<String> knownElements = new ArrayList<>();
    
    knownOperands.add(TokenTypes.NUM_DOUBLE);
    knownOperands.add(TokenTypes.NUM_FLOAT);
    knownOperands.add(TokenTypes.NUM_INT);
    knownOperands.add(TokenTypes.NUM_LONG);
    knownOperands.add(TokenTypes.STRING_LITERAL);
    
    knownElements.add("3.3");
    knownElements.add("1.999");
    knownElements.add("1");
    knownElements.add("4294967296");
    knownElements.add("hello");
    knownElements.add("abc");
    
    int tokenType = knownOperands.get(token);
    String element = knownElements.get(token);
    
    Mockito.when(astMock.getType()).thenReturn(tokenType);
    Mockito.when(astMock.getText()).thenReturn(element);
    expressionCounter.visitToken(astMock);
    assertEquals(6, expressionCounter.getNumUniqueOperands());
  }
}

