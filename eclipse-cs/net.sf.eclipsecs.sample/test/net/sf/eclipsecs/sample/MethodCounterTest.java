package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.MethodCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.Random;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class MethodCounterTest {
  private MethodCounter methodCounter;
  private DetailAST astMock;
  
  @Before
  public void setUp() {
    methodCounter = new MethodCounter();
    astMock = PowerMockito.mock(DetailAST.class);
  }

  @Test
  public void testgetNumExternalReferences() {
    //getNumExternalReferences should return the number of external 
    //in this case, after initialization, NumExternalReferences is set to 0 so getNumExternalReferences should return 0
    assertEquals(0, methodCounter.getNumExternalReferences());
  }
  
  @Test
  public void testgetNumLocalReferences() {
    //getNumVariables should return the number of variables 
    //in this case, after initialization, numVariables is set to 0 so getNumVariables should return 0
    assertEquals(0, methodCounter.getNumLocalReferences());
  }
  
  @Test
  public void testBeginTree() {
    //when begin tree is called, both NumExternalReferencess and NumLocalReferences should be set to 0
    methodCounter.beginTree(astMock);
    assertEquals(0, methodCounter.getNumExternalReferences());
    assertEquals(0, methodCounter.getNumLocalReferences());
  }

  @Test
  public void testGetAcceptableTokens() {
    //the acceptable tokens array should only contain one entry referring to the VARIABLE_DEF token type
    int[] expected = {TokenTypes.METHOD_CALL, TokenTypes.DOT};
    int[] actual = methodCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
    
  }
  
  @Test
  public void testGetDefaultTokens() {
    //the default tokens array should only contain one entry referring to the VARIABLE_DEF token type
    int[] expected = {TokenTypes.METHOD_CALL, TokenTypes.DOT};
    int[] actual = methodCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
    
  }
  
  @Test
  public void testGetRequiredTokens() {
    //the required tokens array should only contain one entry referring to the VARIABLE_DEF token type
    int[] expected = {TokenTypes.METHOD_CALL,TokenTypes.DOT};
    int[] actual = methodCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
    
  }
  
  @Test
  public void testVisitToken() {
    DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
    //Mockito.doReturn(astMock.getType()).when(TokenTypes.METHOD_CALL);  
    //Mockito.doReturn(astMock.getType()).when(TokenTypes.DOT);
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.METHOD_CALL);  
    Mockito.when(astMock.getFirstChild()).thenReturn(astChildMock);
    Mockito.when(astChildMock.getType()).thenReturn(TokenTypes.DOT);
    methodCounter.visitToken(astMock);
    
    assertEquals(1, methodCounter.getNumExternalReferences());
    
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.METHOD_CALL);  
    Mockito.when(astMock.getFirstChild()).thenReturn(astChildMock);
    Mockito.when(astChildMock.getType()).thenReturn(TokenTypes.EMPTY_STAT);
    methodCounter.visitToken(astMock);
    
    assertEquals(1, methodCounter.getNumLocalReferences());
    
  }

}
