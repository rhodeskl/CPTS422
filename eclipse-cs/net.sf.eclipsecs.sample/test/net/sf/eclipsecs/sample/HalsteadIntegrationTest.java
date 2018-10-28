package net.sf.eclipsecs.sample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.CastCounter;
import net.sf.eclipsecs.sample.checks.CommentCounter;
import net.sf.eclipsecs.sample.checks.Driver;
import net.sf.eclipsecs.sample.checks.ExpressionCounter;
import net.sf.eclipsecs.sample.checks.HalsteadMetrics;
import net.sf.eclipsecs.sample.checks.LoopingStatementCounter;
import net.sf.eclipsecs.sample.checks.MaintainabilityIndex;
import net.sf.eclipsecs.sample.checks.MethodCounter;
import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class HalsteadIntegrationTest {
  private ExpressionCounter expressionCounter;
  private HalsteadMetrics halsteadMetrics;
  private DetailAST ast;
  
  @Before
  public void setUp() {
    
    //ast = new DetailAST();
    halsteadMetrics = new HalsteadMetrics();
  }
  @Test
  /* stubs: None
   * real classes: ExpressionCounter
   */
  public void HalsteadInternalIntegration() {
    //REMOVE : public ExpressionCounter(int numexp,int numops,int numoperands,int uniqoperators,int uniqoperands)
    
    //hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();
    int expectedhlen = 0;
    halsteadMetrics.setHalsteadLength();
    assertEquals(expectedhlen,halsteadMetrics.getHalsteadLength());
    
    //hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
    int expectedhvoc = 0;
    halsteadMetrics.setHalsteadVocab();
    assertEquals(expectedhvoc,halsteadMetrics.getHalsteadVocab());
    
    //hDifficulty = ((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())
    //expression_counter.getNumUniqueOperands();
    
    double expectedhdif = 0.0;
    //halsteadMetrics.setHalsteadDifficulty();
    assertEquals(expectedhdif,halsteadMetrics.getHalsteadDifficulty());
    
    //alter hlength
    expectedhlen = 6;
    expectedhvoc = 12;
    expectedhdif = 4.0;
    
    halsteadMetrics.setExpressionCounter(new ExpressionCounter(0,2,4,8,4));
    
    halsteadMetrics.setHalsteadLength();
    assertEquals(expectedhlen,halsteadMetrics.getHalsteadLength());
    
    halsteadMetrics.setHalsteadVocab();
    assertEquals(expectedhvoc,halsteadMetrics.getHalsteadVocab()); 
    
    halsteadMetrics.setHalsteadDifficulty();
    assertEquals(expectedhdif,halsteadMetrics.getHalsteadDifficulty()); 
      
    int[] expected = new int[]{TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
    int[] actual = halsteadMetrics.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
    
    actual = halsteadMetrics.getAcceptableTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
    
    actual = halsteadMetrics.getRequiredTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);  
    
    //(numexp,numops,numoperands,uniqoperators,uniqoperands)
    halsteadMetrics.setExpressionCounter(new ExpressionCounter(0,0,0,0,0));
    expectedhlen = 15; //operators + operands
    expectedhvoc = 12; // uniqueOperators + uniqueOperands
    expectedhdif = 12.5;//((uniqueOperators/2 )*operands )uniqueOperands
    
    ast = new DetailAST();
    //Following 10 calls should give ExpressionCounter in HalsteadMetrics 10 numOperators and 10 numUniqueOperators 
    ast.setType(TokenTypes.ABSTRACT);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.BNOT);
    halsteadMetrics.visitToken(ast);
    
    ast.setType(TokenTypes.LITERAL_CASE);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.LAND);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_PRIVATE);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_WHILE);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.MOD_ASSIGN);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.NOT_EQUAL);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.QUESTION);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.STAR);
    halsteadMetrics.visitToken(ast);

    halsteadMetrics.setHalsteadLength();
    assertEquals(10,halsteadMetrics.getHalsteadLength());
    
    //Following 5 calls should give ExpressionCounter in HalsteadMetrics 5 numOperands and 2 numUniqueOperands
    ast.setText("Text");
    ast.setType(TokenTypes.NUM_DOUBLE);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.NUM_FLOAT);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.NUM_INT);
    halsteadMetrics.visitToken(ast);

    ast.setText("Text2");
    ast.setType(TokenTypes.NUM_LONG);
    halsteadMetrics.visitToken(ast);

    ast.setType(TokenTypes.IDENT);
    halsteadMetrics.visitToken(ast);
    
    
    
    halsteadMetrics.setHalsteadLength();
    assertEquals(expectedhlen,halsteadMetrics.getHalsteadLength());
    
    halsteadMetrics.setHalsteadVocab();
    assertEquals(expectedhvoc,halsteadMetrics.getHalsteadVocab()); 
    
    halsteadMetrics.setHalsteadDifficulty();
    assertEquals(expectedhdif,halsteadMetrics.getHalsteadDifficulty());
  }
}
