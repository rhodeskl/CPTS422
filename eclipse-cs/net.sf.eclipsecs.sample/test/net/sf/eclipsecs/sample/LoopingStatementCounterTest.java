package net.sf.eclipsecs.sample;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.LoopingStatementCounter;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class LoopingStatementCounterTest {
  private LoopingStatementCounter lsCounter;
  private DetailAST astMock;
  
  @Before
  public void setup() {
    lsCounter = new LoopingStatementCounter();
    astMock = PowerMockito.mock(DetailAST.class);
  }
  
  @Test
  public void testGetNumLoops() {
    //getNumLoops should return the number of looping statements
    //in this case, after initialization, numLoopingStatements is set to 0 so getNumLoopingStatements
    //should return 0
    assertEquals(0, lsCounter.getNumLoopingStatements());
  }
  
  @Test
  public void testBeginTree() {
    //when begin is tree is called, numLoopingStatements should be set to 0
    lsCounter.beginTree(astMock);
    assertEquals(0, lsCounter.getNumLoopingStatements());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    //the acceptable tokens array should only contain three entries : LITERAL_FOR,LITERAL_WHILE,LITERAL_DO
    int[] expected = { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE,TokenTypes.LITERAL_DO };
    int[] actual = lsCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    //the default tokens array should only contain three entries : LITERAL_FOR,LITERAL_WHILE,LITERAL_DO
    int[] expected = { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE,TokenTypes.LITERAL_DO  };
    int[] actual = lsCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens() {
  //the required tokens array should only contain three entries : LITERAL_FOR,LITERAL_WHILE,LITERAL_DO
    int[] expected = { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE,TokenTypes.LITERAL_DO};
    int[] actual = lsCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testVisitToken() {
    //visit token should increment numLoopingStatements by 1 if the token type is LITERAL_FOR
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_FOR);
    lsCounter.visitToken(astMock);
    assertEquals(1, lsCounter.getNumLoopingStatements());
    
  //visit token should increment numLoopingStatements by 1 if the token type is LITERAL_WHILE
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_WHILE);
    lsCounter.visitToken(astMock);
    assertEquals(2, lsCounter.getNumLoopingStatements());
    
  //visit token should increment numLoopingStatements by 1 if the token type is LITERAL_DO
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.LITERAL_DO);
    lsCounter.visitToken(astMock);
    assertEquals(3, lsCounter.getNumLoopingStatements());
        
    //visit token should not increment numLoopingStatements if token type is not LITERAL_FOR,LITERAL_WHILE or LITERAL_DO
    boolean isNotAcceptableToken = false;
    Random random = new Random();
    int token = -1;
    while(isNotAcceptableToken == false) { //this loop will generate a random token that is not a LITERAL_FOR,LITERAL_WHILE or LITERAL_DO
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(TokenTypes.LITERAL_FOR != token && TokenTypes.LITERAL_WHILE != token&& TokenTypes.LITERAL_DO != token) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    lsCounter.visitToken(astMock);
    assertEquals(3, lsCounter.getNumLoopingStatements());
  }


}
