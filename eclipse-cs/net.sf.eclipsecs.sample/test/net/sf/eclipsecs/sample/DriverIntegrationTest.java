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
public class DriverIntegrationTest {
  
  private DetailAST astMock;
  private CastCounter castCounterMock;
  private CastCounter castCounter;
  private CommentCounter commentCounterMock;
  private CommentCounter commentCounter;
  private ExpressionCounter expressionCounterMock;
  private ExpressionCounter expressionCounter;
  private HalsteadMetrics halsteadMetricsMock;
  private HalsteadMetrics halsteadMetrics;
  private LoopingStatementCounter loopingStatementCounterMock;
  private LoopingStatementCounter loopingStatementCounter;
  private MaintainabilityIndex maintainabilityIndexMock;
  private MaintainabilityIndex maintainabilityIndex;
  private MethodCounter methodCounterMock;
  private MethodCounter methodCounter;
  private VariableCounter variableCounter;
  
  @Before
  public void setUp() {
    astMock = PowerMockito.mock(DetailAST.class);
    castCounterMock = Mockito.mock(CastCounter.class);
    castCounter = new CastCounter();
    commentCounterMock = Mockito.mock(CommentCounter.class);
    commentCounter = new CommentCounter();
    expressionCounterMock = Mockito.mock(ExpressionCounter.class);
    expressionCounter = new ExpressionCounter();
    halsteadMetricsMock = Mockito.mock(HalsteadMetrics.class);
    halsteadMetrics = new HalsteadMetrics();
    loopingStatementCounterMock = Mockito.mock(LoopingStatementCounter.class);
    loopingStatementCounter = new LoopingStatementCounter();
    maintainabilityIndexMock = Mockito.mock(MaintainabilityIndex.class);
    maintainabilityIndex = new MaintainabilityIndex();
    methodCounterMock = Mockito.mock(MethodCounter.class);
    methodCounter = new MethodCounter();
    variableCounter = new VariableCounter();
  }
  
  @Test
  /*
   * stubs: CastCounter, CommentCounter, ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: VariableCounter
   */
  public void variableCounterIntegration() {
    Driver driver = new Driver(castCounterMock, commentCounterMock, expressionCounterMock, halsteadMetricsMock, loopingStatementCounterMock, maintainabilityIndexMock, methodCounterMock, variableCounter);
    //if the driver passes a DetailAST that isn't type variable def to the run variable counter function, then the function should return 0 because the variable counter's number of variables should still be its default value
    boolean isNotAcceptableToken = false;
    int token = -1;
    Random random = new Random();
    while(isNotAcceptableToken == false) { //this loop will generate a random token that is not a VARIABLE_DEF
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(TokenTypes.VARIABLE_DEF != token) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    int result = driver.runVariableCounter(astMock);
    assertEquals(0, result);
    //if the driver passes a DetailAST of type variable def to the run variable counter function, then the function should return 1 because the variable counter's number of variables should be incremented
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
    result = driver.runVariableCounter(astMock);
    assertEquals(1, result);
  }
  
  @Test
  /*
   * stubs: CastCounter, ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: CommentCounter, VariableCounter
   */
  public void commentCounterIntegration() {
    Driver driver = new Driver(castCounterMock, commentCounter, expressionCounterMock, halsteadMetricsMock, loopingStatementCounterMock, maintainabilityIndexMock, methodCounterMock, variableCounter);
    //if the driver passes a DetailAST that is not one of the comment types to the run comment counter function, then none of the comment counter's variables should be incremented
    Random random = new Random();
    boolean isNotAcceptableToken = false;
    int token = -1;
    while(isNotAcceptableToken == false) { //this loop will generate a random token that is not a SINGLE_LINE_COMMENT or a BLOCK_COMMENT_BEGIN
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(TokenTypes.SINGLE_LINE_COMMENT != token && TokenTypes.BLOCK_COMMENT_BEGIN != token) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    int[] result = driver.runCommentCounter(astMock);
    int[] expected1 = {0, 0};
    boolean isEqual = Arrays.equals(expected1, result);
    assertEquals(true, isEqual);
    //if the driver passes a single line comment DetailAST, then the comment counter's num comments and num comment lines should be incremented
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
    result = driver.runCommentCounter(astMock);
    int[] expected2 = {1, 1};
    isEqual = Arrays.equals(expected2, result);
    assertEquals(true, isEqual);
    //if the driver passes a block comment DetailAST, then the comment counter's num comments and num comment lines should be incremented
    DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
    int lineNoStart = random.nextInt();
    int lineNoEnd = 0;
    while(lineNoEnd < lineNoStart) {
      lineNoEnd = random.nextInt();
    }
    assertEquals(true, lineNoEnd > lineNoStart);
    Mockito.when(astMock.getLineNo()).thenReturn(lineNoStart);
    Mockito.when(astChildMock.getLineNo()).thenReturn(lineNoEnd);
    Mockito.when(astMock.findFirstToken(TokenTypes.BLOCK_COMMENT_END)).thenReturn(astChildMock);
    int expectedNumLines = lineNoEnd - lineNoStart + driver.commentCounter.getNumLines();
    result = driver.runCommentCounter(astMock);
    int[] expected3 = {1, expectedNumLines};
    isEqual = Arrays.equals(expected3, result);
    assertEquals(true, isEqual);
  }
  
  @Test
  /*
   * stubs: ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: CastCounter, CommentCounter, VariableCounter
   */
  public void castCounterIntegration() {
    Driver driver = new Driver(castCounter, commentCounter, expressionCounterMock, halsteadMetricsMock, loopingStatementCounterMock, maintainabilityIndexMock, methodCounterMock, variableCounter);
    
    // if the driver passes a DetailAST that is not type TYPECAST to the run cast counter function, then the function should return 0 because the cast counter's number of casts should still be its default value
    boolean isNotAcceptableToken = false;
    int token = -1;
    Random random = new Random();
    
    // This loop will generate a random token that is not a TYPECAST
    while(isNotAcceptableToken == false) {
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(TokenTypes.TYPECAST != token) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    int result = driver.runCastCounter(astMock);
    assertEquals(0, result);
    
    // if the driver passes a DetailAST of type TYPECAST to the run cast counter function, then the function should return 1 because the cast counter's numCasts should be incremented  
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.TYPECAST);
    result = driver.runCastCounter(astMock);
    assertEquals(1, result);
  }
  
  @Test
  /*
   * stubs: HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: CastCounter, CommentCounter, ExpressionCounter, VariableCounter
   */
  public void expressionCounterIntegration() {
    Driver driver = new Driver(castCounter, commentCounter, expressionCounter, halsteadMetricsMock, loopingStatementCounterMock, maintainabilityIndexMock, methodCounterMock, variableCounter);
    
    // if the driver passes a DetailAST that is not a type expression, operation, or operand to the run expression function, then none of the expression counter's variables should be incremented
    boolean isNotAcceptableToken = false;
    int token = -1;
    Random random = new Random();
    
    // This loop will generate a random token that is not an expression, operation, or operand
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
    int[] result = driver.runExpressionCounter(astMock);
    int[] expected1 = {0, 0, 0, 0, 0};
    boolean isEqual = Arrays.equals(expected1, result);
    assertEquals(true, isEqual);
   
    // if the driver passes a DetailAST of type EXPR to the expression counter function, then the expression counter's numExpression should be incremented
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.EXPR);
    result = driver.runExpressionCounter(astMock);
    int[] expected2 = {1, 0, 0, 0, 0};
    isEqual = Arrays.equals(expected2, result);
    assertEquals(true, isEqual);
    
    // if the driver passes a DetailAST of type operator to the expression counter function, then the expression counter's numOperators and numUniqueOperators should be incremented
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ABSTRACT);
    result = driver.runExpressionCounter(astMock);
    int[] expected3 = {0, 0, 1, 0, 1};
    isEqual = Arrays.equals(expected3, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ASSIGN);
    result = driver.runExpressionCounter(astMock);
    int[] expected4 = {0, 0, 1, 0, 2};
    isEqual = Arrays.equals(expected4, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BAND);
    result = driver.runExpressionCounter(astMock);
    int[] expected5 = {0, 0, 1, 0, 3};
    isEqual = Arrays.equals(expected5, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BAND_ASSIGN);
    result = driver.runExpressionCounter(astMock);
    int[] expected6 = {0, 0, 1, 0, 4};
    isEqual = Arrays.equals(expected6, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BNOT);
    result = driver.runExpressionCounter(astMock);
    int[] expected7 = {0, 0, 1, 0, 5};
    isEqual = Arrays.equals(expected7, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BOR);
    result = driver.runExpressionCounter(astMock);
    int[] expected8 = {0, 0, 1, 0, 6};
    isEqual = Arrays.equals(expected8, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BOR_ASSIGN);
    result = driver.runExpressionCounter(astMock);
    int[] expected9 = {0, 0, 1, 0, 7};
    isEqual = Arrays.equals(expected9, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BSR);
    result = driver.runExpressionCounter(astMock);
    int[] expected10 = {0, 0, 1, 0, 8};
    isEqual = Arrays.equals(expected10, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BSR_ASSIGN);
    result = driver.runExpressionCounter(astMock);
    int[] expected11 = {0, 0, 1, 0, 9};
    isEqual = Arrays.equals(expected11, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BXOR);
    result = driver.runExpressionCounter(astMock);
    int[] expected12 = {0, 0, 1, 0, 10};
    isEqual = Arrays.equals(expected12, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BXOR_ASSIGN);
    result = driver.runExpressionCounter(astMock);
    int[] expected13 = {0, 0, 1, 0, 11};
    isEqual = Arrays.equals(expected13, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.COLON);    
    result = driver.runExpressionCounter(astMock);
    int[] expected14 = {0, 0, 1, 0, 12};
    isEqual = Arrays.equals(expected14, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.COMMA);    
    result = driver.runExpressionCounter(astMock);
    int[] expected15 = {0, 0, 1, 0, 13};
    isEqual = Arrays.equals(expected15, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DEC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected16 = {0, 0, 1, 0, 14};
    isEqual = Arrays.equals(expected16, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DIV);   
    result = driver.runExpressionCounter(astMock);
    int[] expected17 = {0, 0, 1, 0, 15};
    isEqual = Arrays.equals(expected17, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DIV_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected18 = {0, 0, 1, 0, 16};
    isEqual = Arrays.equals(expected18, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DO_WHILE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected19 = {0, 0, 1, 0, 17};
    isEqual = Arrays.equals(expected19, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.DOT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected20 = {0, 0, 1, 0, 18};
    isEqual = Arrays.equals(expected20, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ENUM);    
    result = driver.runExpressionCounter(astMock);
    int[] expected21 = {0, 0, 1, 0, 19};
    isEqual = Arrays.equals(expected21, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.EQUAL);   
    result = driver.runExpressionCounter(astMock);
    int[] expected22 = {0, 0, 1, 0, 20};
    isEqual = Arrays.equals(expected22, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.FINAL);    
    result = driver.runExpressionCounter(astMock);
    int[] expected23 = {0, 0, 1, 0, 21};
    isEqual = Arrays.equals(expected23, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.GE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected24 = {0, 0, 1, 0, 22};
    isEqual = Arrays.equals(expected24, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.GT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected25 = {0, 0, 1, 0, 23};
    isEqual = Arrays.equals(expected25, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.IMPORT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected26 = {0, 0, 1, 0, 24};
    isEqual = Arrays.equals(expected26, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.INC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected27 = {0, 0, 1, 0, 25};
    isEqual = Arrays.equals(expected27, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.INDEX_OP);
    result = driver.runExpressionCounter(astMock);
    int[] expected28 = {0, 0, 1, 0, 26};
    isEqual = Arrays.equals(expected28, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LAND);   
    result = driver.runExpressionCounter(astMock);
    int[] expected29 = {0, 0, 1, 0, 27};
    isEqual = Arrays.equals(expected29, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LCURLY);    
    result = driver.runExpressionCounter(astMock);
    int[] expected30 = {0, 0, 1, 0, 28};
    isEqual = Arrays.equals(expected30, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected31 = {0, 0, 1, 0, 29};
    isEqual = Arrays.equals(expected31, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_ASSERT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected32 = {0, 0, 1, 0, 30};
    isEqual = Arrays.equals(expected32, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BOOLEAN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected33 = {0, 0, 1, 0, 31};
    isEqual = Arrays.equals(expected33, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BREAK);    
    result = driver.runExpressionCounter(astMock);
    int[] expected34 = {0, 0, 1, 0, 32};
    isEqual = Arrays.equals(expected34, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_BYTE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected35 = {0, 0, 1, 0, 33};
    isEqual = Arrays.equals(expected35, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CASE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected36 = {0, 0, 1, 0, 34};
    isEqual = Arrays.equals(expected36, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CATCH);    
    result = driver.runExpressionCounter(astMock);
    int[] expected37 = {0, 0, 1, 0, 35};
    isEqual = Arrays.equals(expected37, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CHAR);    
    result = driver.runExpressionCounter(astMock);
    int[] expected38 = {0, 0, 1, 0, 36};
    isEqual = Arrays.equals(expected38, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CLASS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected39 = {0, 0, 1, 0, 37};
    isEqual = Arrays.equals(expected39, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_CONTINUE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected40 = {0, 0, 1, 0, 38};
    isEqual = Arrays.equals(expected40, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DEFAULT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected41 = {0, 0, 1, 0, 39};
    isEqual = Arrays.equals(expected41, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DO);   
    result = driver.runExpressionCounter(astMock);
    int[] expected42 = {0, 0, 1, 0, 40};
    isEqual = Arrays.equals(expected42, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DOUBLE);   
    result = driver.runExpressionCounter(astMock);
    int[] expected43 = {0, 0, 1, 0, 41};
    isEqual = Arrays.equals(expected43, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_ELSE);   
    result = driver.runExpressionCounter(astMock);
    int[] expected44 = {0, 0, 1, 0, 42};
    isEqual = Arrays.equals(expected44, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FALSE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected45 = {0, 0, 1, 0, 43};
    isEqual = Arrays.equals(expected45, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FINALLY);    
    result = driver.runExpressionCounter(astMock);
    int[] expected46 = {0, 0, 1, 0, 44};
    isEqual = Arrays.equals(expected46, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FLOAT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected47 = {0, 0, 1, 0, 45};
    isEqual = Arrays.equals(expected47, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FOR);    
    result = driver.runExpressionCounter(astMock);
    int[] expected48 = {0, 0, 1, 0, 46};
    isEqual = Arrays.equals(expected48, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_IF);    
    result = driver.runExpressionCounter(astMock);
    int[] expected49 = {0, 0, 1, 0, 47};
    isEqual = Arrays.equals(expected49, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INSTANCEOF);    
    result = driver.runExpressionCounter(astMock);
    int[] expected50 = {0, 0, 1, 0, 48};
    isEqual = Arrays.equals(expected50, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected51 = {0, 0, 1, 0, 49};
    isEqual = Arrays.equals(expected51, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_INTERFACE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected52 = {0, 0, 1, 0, 50};
    isEqual = Arrays.equals(expected52, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_LONG);    
    result = driver.runExpressionCounter(astMock);
    int[] expected53 = {0, 0, 1, 0, 51};
    isEqual = Arrays.equals(expected53, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NATIVE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected54 = {0, 0, 1, 0, 52};
    isEqual = Arrays.equals(expected54, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NEW);    
    result = driver.runExpressionCounter(astMock);
    int[] expected55 = {0, 0, 1, 0, 53};
    isEqual = Arrays.equals(expected55, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_NULL);   
    result = driver.runExpressionCounter(astMock);
    int[] expected56 = {0, 0, 1, 0, 54};
    isEqual = Arrays.equals(expected56, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PRIVATE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected57 = {0, 0, 1, 0, 55};
    isEqual = Arrays.equals(expected57, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PROTECTED);    
    result = driver.runExpressionCounter(astMock);
    int[] expected58 = {0, 0, 1, 0, 56};
    isEqual = Arrays.equals(expected58, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_PUBLIC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected59 = {0, 0, 1, 0, 57};
    isEqual = Arrays.equals(expected59, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_RETURN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected60 = {0, 0, 1, 0, 58};
    isEqual = Arrays.equals(expected60, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SHORT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected61 = {0, 0, 1, 0, 59};
    isEqual = Arrays.equals(expected61, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_STATIC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected62 = {0, 0, 1, 0, 60};
    isEqual = Arrays.equals(expected62, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SUPER);   
    result = driver.runExpressionCounter(astMock);
    int[] expected63 = {0, 0, 1, 0, 61};
    isEqual = Arrays.equals(expected63, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SWITCH);    
    result = driver.runExpressionCounter(astMock);
    int[] expected64 = {0, 0, 1, 0, 62};
    isEqual = Arrays.equals(expected64, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_SYNCHRONIZED);    
    result = driver.runExpressionCounter(astMock);
    int[] expected65 = {0, 0, 1, 0, 63};
    isEqual = Arrays.equals(expected65, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THIS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected66 = {0, 0, 1, 0, 64};
    isEqual = Arrays.equals(expected66, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THROW);    
    result = driver.runExpressionCounter(astMock);
    int[] expected67 = {0, 0, 1, 0, 65};
    isEqual = Arrays.equals(expected67, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_THROWS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected68 = {0, 0, 1, 0, 66};
    isEqual = Arrays.equals(expected68, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRANSIENT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected69 = {0, 0, 1, 0, 67};
    isEqual = Arrays.equals(expected69, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRUE);
    result = driver.runExpressionCounter(astMock);
    int[] expected70 = {0, 0, 1, 0, 68};
    isEqual = Arrays.equals(expected70, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_TRY);   
    result = driver.runExpressionCounter(astMock);
    int[] expected71 = {0, 0, 1, 0, 69};
    isEqual = Arrays.equals(expected71, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_VOID);    
    result = driver.runExpressionCounter(astMock);
    int[] expected72 = {0, 0, 1, 0, 70};
    isEqual = Arrays.equals(expected72, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_VOLATILE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected73 = {0, 0, 1, 0, 71};
    isEqual = Arrays.equals(expected73, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_WHILE);    
    result = driver.runExpressionCounter(astMock);
    int[] expected74 = {0, 0, 1, 0, 72};
    isEqual = Arrays.equals(expected74, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LNOT);    
    result = driver.runExpressionCounter(astMock);
    int[] expected75 = {0, 0, 1, 0, 73};
    isEqual = Arrays.equals(expected75, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LOR);    
    result = driver.runExpressionCounter(astMock);
    int[] expected76 = {0, 0, 1, 0, 74};
    isEqual = Arrays.equals(expected76, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LPAREN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected77 = {0, 0, 1, 0, 75};
    isEqual = Arrays.equals(expected77, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.METHOD_CALL);    
    result = driver.runExpressionCounter(astMock);
    int[] expected78 = {0, 0, 1, 0, 76};
    isEqual = Arrays.equals(expected78, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MINUS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected79 = {0, 0, 1, 0, 77};
    isEqual = Arrays.equals(expected79, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MINUS_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected80 = {0, 0, 1, 0, 78};
    isEqual = Arrays.equals(expected80, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MOD);    
    result = driver.runExpressionCounter(astMock);
    int[] expected81 = {0, 0, 1, 0, 79};
    isEqual = Arrays.equals(expected81, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.MOD_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected82 = {0, 0, 1, 0, 80};
    isEqual = Arrays.equals(expected82, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NOT_EQUAL);
    result = driver.runExpressionCounter(astMock);
    int[] expected83 = {0, 0, 1, 0, 81};
    isEqual = Arrays.equals(expected83, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.PLUS);   
    result = driver.runExpressionCounter(astMock);
    int[] expected84 = {0, 0, 1, 0, 82};
    isEqual = Arrays.equals(expected84, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.PLUS_ASSIGN);  
    result = driver.runExpressionCounter(astMock);
    int[] expected85 = {0, 0, 1, 0, 83};
    isEqual = Arrays.equals(expected85, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.POST_DEC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected86 = {0, 0, 1, 0, 84};
    isEqual = Arrays.equals(expected86, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.POST_INC);    
    result = driver.runExpressionCounter(astMock);
    int[] expected87 = {0, 0, 1, 0, 85};
    isEqual = Arrays.equals(expected87, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.QUESTION);    
    result = driver.runExpressionCounter(astMock);
    int[] expected88 = {0, 0, 1, 0, 86};
    isEqual = Arrays.equals(expected88, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RBRACK);    
    result = driver.runExpressionCounter(astMock);
    int[] expected89 = {0, 0, 1, 0, 87};
    isEqual = Arrays.equals(expected89, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RCURLY);    
    result = driver.runExpressionCounter(astMock);
    int[] expected90 = {0, 0, 1, 0, 88};
    isEqual = Arrays.equals(expected90, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.RPAREN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected91 = {0, 0, 1, 0, 89};
    isEqual = Arrays.equals(expected91, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SEMI);    
    result = driver.runExpressionCounter(astMock);
    int[] expected92 = {0, 0, 1, 0, 90};
    isEqual = Arrays.equals(expected92, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SL);    
    result = driver.runExpressionCounter(astMock);
    int[] expected93 = {0, 0, 1, 0, 91};
    isEqual = Arrays.equals(expected93, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SL_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected94 = {0, 0, 1, 0, 92};
    isEqual = Arrays.equals(expected94, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SR);    
    result = driver.runExpressionCounter(astMock);
    int[] expected95 = {0, 0, 1, 0, 93};
    isEqual = Arrays.equals(expected95, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SR_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected96 = {0, 0, 1, 0, 94};
    isEqual = Arrays.equals(expected96, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STAR);    
    result = driver.runExpressionCounter(astMock);
    int[] expected97 = {0, 0, 1, 0, 95};
    isEqual = Arrays.equals(expected97, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STAR_ASSIGN);    
    result = driver.runExpressionCounter(astMock);
    int[] expected98 = {0, 0, 1, 0, 96};
    isEqual = Arrays.equals(expected98, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.UNARY_MINUS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected99 = {0, 0, 1, 0, 97};
    isEqual = Arrays.equals(expected99, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.UNARY_PLUS);    
    result = driver.runExpressionCounter(astMock);
    int[] expected100 = {0, 0, 1, 0, 98};
    isEqual = Arrays.equals(expected100, result);
    assertEquals(true, isEqual);
    
    // if the driver passes a DetailAST that is type operator to the run expression function, then numOperators should increment and numUniqueOperators should not increment
    isNotAcceptableToken = false;
    
    // This loop will generate a random token that is not an expression, operation, or operand
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
    result = driver.runExpressionCounter(astMock);
    int[] expected101 = {0, 0, 1, 0, 98};
    isEqual = Arrays.equals(expected101, result);
    assertEquals(true, isEqual);
    
    // if the driver passes a DetailAST of type operand to the expression counter function, then the expression counter's numOperands and numUniqueOperands should be incremented
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_DOUBLE);
    Mockito.when(astMock.getText()).thenReturn("3.3");
    result = driver.runExpressionCounter(astMock);
    int[] expected102 = {0, 1, 0, 1, 0};
    isEqual = Arrays.equals(expected102, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_FLOAT);
    Mockito.when(astMock.getText()).thenReturn("1.999");
    result = driver.runExpressionCounter(astMock);
    int[] expected103 = {0, 1, 0, 2, 0};
    isEqual = Arrays.equals(expected103, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_INT);
    Mockito.when(astMock.getText()).thenReturn("1");
    result = driver.runExpressionCounter(astMock);
    int[] expected104 = {0, 1, 0, 3, 0};
    isEqual = Arrays.equals(expected104, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.NUM_LONG);
    Mockito.when(astMock.getText()).thenReturn("4294967296");
    result = driver.runExpressionCounter(astMock);
    int[] expected105 = {0, 1, 0, 4, 0};
    isEqual = Arrays.equals(expected105, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.IDENT);
    Mockito.when(astMock.getText()).thenReturn("hello");
    result = driver.runExpressionCounter(astMock);
    int[] expected106 = {0, 1, 0, 5, 0};
    isEqual = Arrays.equals(expected106, result);
    assertEquals(true, isEqual);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.STRING_LITERAL);
    Mockito.when(astMock.getText()).thenReturn("abc");
    result = driver.runExpressionCounter(astMock);
    int[] expected107 = {0, 1, 0, 6, 0};
    isEqual = Arrays.equals(expected107, result);
    assertEquals(true, isEqual);
    
    // if the driver passes a DetailAST that is type operand to the run expression function, then numOperands should increment and numUniqueOperands should not increment 
    token = random.nextInt(5);
    List<Integer> knownOperands = new ArrayList<>();
    List<String> knownElements = new ArrayList<>();
    
    knownOperands.add(TokenTypes.NUM_DOUBLE);
    knownOperands.add(TokenTypes.NUM_FLOAT);
    knownOperands.add(TokenTypes.NUM_INT);
    knownOperands.add(TokenTypes.NUM_LONG);
    knownOperands.add(TokenTypes.IDENT);
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
    result = driver.runExpressionCounter(astMock);
    int[] expected108 = {0, 1, 0, 6, 0};
    isEqual = Arrays.equals(expected108, result);
    assertEquals(true, isEqual);
  }
  
  @Test
  /*
   * stubs: LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: CastCounter, CommentCounter, ExpressionCounter, HalsteadMetrics, VariableCounter
   */
  public void halsteadMetricsIntegration() {
    
  }
  
  @Test
  /*
   * stubs: MaintainabilityIndex, MethodCounter
   * real classes: CastCounter, CommentCounter, ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, VariableCounter
   */
  public void loopingStatementCounterIntegration() {
    
  }
  
  @Test
  /*
   * stubs: MethodCounter
   * real classes: CastCounter, CommentCounter, ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, VariableCounter
   */
  public void maintainabilityIndexIntegration() {
    
  }
  
  @Test
  /*
   * stubs: none
   * real classes: CastCounter, CommentCounter, ExpressionCounter, HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter, VariableCounter
   */
  public void methodCounterIntegration() {
    
  }
  

}
