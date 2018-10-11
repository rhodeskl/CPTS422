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

import net.sf.eclipsecs.sample.checks.CommentCounter;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class CommentCounterTest {
  private CommentCounter commentCounter;
  private DetailAST astMock;
  
  @Before
  public void setup() {
    commentCounter = new CommentCounter();
    astMock = PowerMockito.mock(DetailAST.class);
  }
  
  @Test
  public void testGetNumComments() {
    //getNumComments should return the number of comments
    //in this case, after initialization, numComments is set to 0 so getNumComments should return 0
    assertEquals(0, commentCounter.getNumComments());
  }
  
  @Test
  public void testGetNumLines() {
    //getNumLines should return the number of comment lines
    //in this case, after initialization, numLines is set to 0 go getNumLines should return 0
    assertEquals(0, commentCounter.getNumLines());
  }
  
  @Test
  public void testIsCommentNodesRequired() {
    //isCommentNodesRequired should always return true
    assertEquals(true, commentCounter.isCommentNodesRequired());
  }
  
  @Test
  public void testBeginTree() {
    //when begin is tree is called, both numComments and numLines should be set to 0
    commentCounter.beginTree(astMock);
    assertEquals(0, commentCounter.getNumComments());
    assertEquals(0, commentCounter.getNumLines());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    //the acceptable tokens array should only contain two entries: one for the BLOCK_COMMENT_BEGIN token type and one for the SINGLE_LINE_COMMENT token type
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    //the default tokens array should only contain two entries: one for the BLOCK_COMMENT_BEGIN token type and one for the SINGLE_LINE_COMMENT token type
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens() {
    //the required tokens array should only contain two entries: one for the BLOCK_COMMENT_BEGIN token type and one for the SINGLE_LINE_COMMENT token type
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testVisitToken() {
    //visit token should increment numComments and numCommentLines both by 1 if the token type is SINGLE_LINE_COMMENT
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
    commentCounter.visitToken(astMock);
    assertEquals(1, commentCounter.getNumComments());
    assertEquals(1, commentCounter.getNumLines());
    //visit token should increment numCommentLines by the number of comment lines a block comment covers if the token type is BLOCK_COMMENT_END
    //visit token should increment numComments by 1 when the token type is BLOCK_COMMENT_BEGIN
    DetailAST astChildMock = PowerMockito.mock(DetailAST.class);
    Mockito.when(astMock.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
    Random random = new Random();
    int lineNoStart = random.nextInt();
    int lineNoEnd = 0;
    while(lineNoEnd < lineNoStart) {
      lineNoEnd = random.nextInt();
    }
    assertEquals(true, lineNoEnd > lineNoStart);
    Mockito.when(astMock.getLineNo()).thenReturn(lineNoStart);
    Mockito.when(astChildMock.getLineNo()).thenReturn(lineNoEnd);
    Mockito.when(astMock.findFirstToken(TokenTypes.BLOCK_COMMENT_END)).thenReturn(astChildMock);
    int expectedNumLines = lineNoEnd - lineNoStart + 1 + commentCounter.getNumLines();
    commentCounter.visitToken(astMock);
    assertEquals(expectedNumLines, commentCounter.getNumLines());
    assertEquals(2, commentCounter.getNumComments());
    //visit token should not increment numComments or numCommentLines if the token type is not a BLOCK_COMMENT_BEGIN or SINGLE_LINE_COMMENT
    boolean isNotAcceptableToken = false;
    int token = -1;
    while(isNotAcceptableToken == false) { //this loop will generate a random token that is not a SINGLE_LINE_COMMENT or a BLOCK_COMMENT_BEGIN
      token = random.nextInt(TokenTypes.WILDCARD_TYPE);
      if(TokenTypes.SINGLE_LINE_COMMENT != token && TokenTypes.BLOCK_COMMENT_BEGIN != token) {
        isNotAcceptableToken = true;
      }
    }
    Mockito.when(astMock.getType()).thenReturn(token);
    commentCounter.visitToken(astMock);
    assertEquals(2, commentCounter.getNumComments());
    assertEquals(expectedNumLines, commentCounter.getNumLines());
  }

}
