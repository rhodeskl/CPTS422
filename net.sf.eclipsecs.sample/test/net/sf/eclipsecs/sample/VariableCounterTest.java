package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.mockito.Mockito.spy;

import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableCounterTest {
  
  private VariableCounter variableCounter;
  private DetailAST spyAst;
  
  @Before
  public void setUp() {
    VariableCounter variableCounter = new VariableCounter();
    DetailAst spyAst = spy(new DetailAST());
  }
  
  @Test
  public void testGetNumVariables() {
    assertEquals(0, variableCounter.getNumVariables());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    assertEquals(expected, variableCounter.getAcceptableTokens());
  }
  
  @Test
  public void testGetDefaultTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    assertEquals(expected, variableCounter.getDefaultTokens());
  }
  
  @Test
  public void testGetRequiredTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    assertEquals(expected, variableCounter.getDefaultTokens());
  }
  
  @Test
  public void testVisitToken() {
    //visit token should increment numVariables if the token type is a variable definition
    doReturn(TokenTypes.VARIABLE_DEF).when(ast.getType());
    variableCounter.visitToken(ast);
    assertEquals(1, variableCounter.getNumVariables());
    //visit token should not increment numVariables if the token type is not a variable definition
    doReturn(TokenTypes.ABSTRACT).when(ast.getType());
    assertEquals(1, variableCounter.getNumVariables());
  }
  
  @Test
  @Ignore //don't know whether this should be tested or not
  public void testFinishTree() {
    
  }
}
