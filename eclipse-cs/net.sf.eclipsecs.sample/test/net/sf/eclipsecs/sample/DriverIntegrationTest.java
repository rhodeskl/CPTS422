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
    
  }
  
  @Test
  /*
   * stubs: HalsteadMetrics, LoopingStatementCounter, MaintainabilityIndex, MethodCounter
   * real classes: CastCounter, CommentCounter, ExpressionCounter, VariableCounter
   */
  public void expressionCounterIntegration() {
    
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
