package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.Random;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(PowerMockRunner.class)
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
    //getNumVariables should return the number of variables 
    //in this case, after initialization, numVariables is set to 0 so getNumVariables should return 0
    assertEquals(0, variableCounter.getNumVariables());
  }
  
  // Add test case for Deliverable 3 to test beginTree()
  @Test
  public void testBeginTree() {
    //when begin is tree is called numVariables should be set to 0
    variableCounter.beginTree(astMock);
    assertEquals(0, variableCounter.getNumVariables());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    //the acceptable tokens array should only contain one entry referring to the VARIABLE_DEF token type
    int[] expected = {TokenTypes.VARIABLE_DEF};
    int[] actual = variableCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    //the default tokens array should only contain one entry referring to the VARIABLE_DEF token type
    int[] expected = {TokenTypes.VARIABLE_DEF};
    int[] actual = variableCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens() {
    //the required tokens array should only contain one entry referring to the VARIABLE_DEF token type
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
    variableCounter.visitToken(astMock);
    assertEquals(1, variableCounter.getNumVariables());
  }
}
