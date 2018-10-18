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
    Mockito.when(astMock.getText()).thenReturn("a");
    expressionCounter.visitToken(astMock);
    
    assertEquals(6, expressionCounter.getNumOperands());
    assertEquals(6, expressionCounter.getNumUniqueOperands());
  }
}

