package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.CastCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.Random;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class CastCounterTest {
  private CastCounter castCounter;
  private DetailAST astMock;
  
  @Before
  public void setUp() {
    castCounter = new CastCounter();
    astMock = PowerMockito.mock(DetailAST.class);
  }
  
  @Test
  public void testGetNumCasts() {
    // getNumCasts should return the number of casts
    // after initialization, numCasts is set to 0, so it should return 0
    assertEquals(0, castCounter.getNumCasts());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    // should only contain one entry referring to the TYPECAST token type
    int[] expected = {TokenTypes.TYPECAST};
    int[] actual = castCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    // should only contain one entry referring to the TYPECAST token type
    int[] expected = {TokenTypes.TYPECAST};
    int[] actual = castCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testVisitToken() {
    // visit token should increment numCasts if the token type is a cast
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.TYPECAST);
    castCounter.visitToken(astMock);
    assertEquals(1, castCounter.getNumCasts());
    // visit token should not increment numVariables if the token type is not a cast
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
    castCounter.visitToken(astMock);
    assertEquals(1, castCounter.getNumCasts());
  }
}
