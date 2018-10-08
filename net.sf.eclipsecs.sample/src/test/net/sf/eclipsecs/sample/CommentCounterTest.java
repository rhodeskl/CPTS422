package test.net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import static org.mockito.Mockito.spy;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import static junit.framework.TestCase.assertEquals;

import net.sf.eclipsecs.sample.checks.CommentCounter;

public class CommentCounterTest {
  CommentCounter commentCounter;
  DetailAST spyAst;
  
  @Before
  public void setUp() {
    commentCounter = new CommentCounter();
    spyAst = spy(new DetailAST());
  }
  
  @Test
  public void testGetNumComments() {
    
  }
  
  @Test
  public void testGetNumLines() {
    
  }
  
  @Test
  public void testIsCommentNodesRequired() {
    
  }
  
  @Test
  public void testBeginTree() {
    
  }
  
  @Test
  public void testGetAcceptableTokens() {
    
  }
  
  @Test
  public void testGetDefaultTokens() {
    
  }
  
  @Test
  public void testGetRequiredTokens() {
    
  }
  
  @Test
  public void testVisitToken() {
    
  }
  
  @Test
  public void testFinishTree() {
    
  }
}
