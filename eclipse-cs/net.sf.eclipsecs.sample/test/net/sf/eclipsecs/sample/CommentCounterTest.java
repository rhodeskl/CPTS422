package net.sf.eclipsecs.sample;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.CommentCounter;

public class CommentCounterTest {
  CommentCounter commentCounter;
  
  @Before
  public void setup() {
    commentCounter = new CommentCounter();
  }
  
  @Test
  public void testGetNumComments() {
    
  }
  
  @Test
  public void testGetNumLines() {
    
  }
  
  @Test
  public void testIsCommentNodesRequired() {
    assertEquals(true, commentCounter.isCommentNodesRequired());
  }
  
  @Test
  public void testBeginTree() {
    DetailAST ast = new DetailAST(); //replace with mock
    commentCounter.beginTree(ast);
    assertEquals(0, commentCounter.getNumComments());
    assertEquals(0, commentCounter.getNumLines());
  }
  
  @Test
  public void testGetAcceptableTokens() {
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetDefaultTokens() {
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens() {
    int[] expected = { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
    int[] actual = commentCounter.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testVisitToken() {
    
  }
  
  @Test
  public void testFinishTree() {
    
  }

}
