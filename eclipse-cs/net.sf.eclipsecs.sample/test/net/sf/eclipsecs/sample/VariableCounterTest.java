package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableCounterTest {
  private VariableCounter variableCounter;
  
  @Before
  public void setUp() {
    variableCounter = new VariableCounter();
  }
  
  @Test
  public void testGetNumVariables() {
    assertEquals(0, variableCounter.getNumVariables());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    int[] actual = variableCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    int[] actual = variableCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens() {
    int[] expected = {TokenTypes.VARIABLE_DEF};
    int[] actual = variableCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testVisitToken() {
    DetailAST ast = new DetailAST();
    //visit token should increment numVariables if the token type is a variable definition
    ast.setType(TokenTypes.VARIABLE_DEF);
    assertEquals(TokenTypes.VARIABLE_DEF, ast.getType());
    variableCounter.visitToken(ast);
    assertEquals(1, variableCounter.getNumVariables());
    //visit token should not increment numVariables if the token type is not a variable definition
    ast.setType(TokenTypes.ABSTRACT);
    assertEquals(TokenTypes.ABSTRACT, ast.getType());
    variableCounter.visitToken(ast);
    assertEquals(1, variableCounter.getNumVariables());
  }
  
  @Test
  @Ignore //don't know whether this should be tested or not
  public void testFinishTree() {
    
  }
}
