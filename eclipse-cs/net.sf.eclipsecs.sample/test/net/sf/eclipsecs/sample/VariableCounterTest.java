package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.junit.Ignore;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class VariableCounterTest {
  private VariableCounter variableCounter;
  private DetailAST astMock;
  
  @Before
  public void setUp() {
    variableCounter = new VariableCounter();
    astMock = PowerMockito.mock(DetailAST.class);
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
    //visit token should increment numVariables if the token type is a variable definition
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
    variableCounter.visitToken(astMock);
    assertEquals(1, variableCounter.getNumVariables());
    //visit token should not increment numVariables if the token type is not a variable definition
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.ABSTRACT);
    variableCounter.visitToken(astMock);
    assertEquals(1, variableCounter.getNumVariables());
  }
}
