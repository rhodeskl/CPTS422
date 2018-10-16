package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.MaintainabilityIndex;
import net.sf.eclipsecs.sample.checks.HalsteadMetrics;
import net.sf.eclipsecs.sample.checks.CyclomaticComplexity;
import net.sf.eclipsecs.sample.checks.ExecutableStatementCount;
import net.sf.eclipsecs.sample.checks.CommentCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class MaintainabilityIndexTest {
  private DetailAST astMock;
  
  @Mock
  private HalsteadMetrics mockHalstead;
  @Mock
  private CyclomaticComplexity mockCyclomatic;
  @Mock
  private ExecutableStatementCount mockExecutable;
  @Mock
  private CommentCounter mockComment;
  @InjectMocks
  private MaintainabilityIndex maintainabilityIndex;
  
  @Before
  public void setUp() {
    astMock = PowerMockito.mock(DetailAST.class);
  }
  
  @Test
  public void testGetMaintainabilityIndex()
  {
    assertEquals(0.0, maintainabilityIndex.getMaintainabilityIndex());
  }
  
  @Test
  public void testSetMaintainabilityIndex()
  {
    doReturn(10.0).when(mockHalstead).getHalsteadVolume();
    doReturn(10).when(mockCyclomatic).getCurrentValue();
    doReturn(10).when(mockExecutable).getNumLines();
    doReturn(10).doReturn(10).when(mockComment).getNumComments();
    
    maintainabilityIndex.setMaintainabilityIndex();
    
    assertEquals(142.06734097577464, maintainabilityIndex.getMaintainabilityIndex());
  }
  
  @Test
  public void testGetDefaultTokens()
  {
    when(mockHalstead.getDefaultTokens()).thenReturn(new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
      TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
      TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
      TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
      TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
      TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
      TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
      TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
      TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
      TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
      TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
      TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
      TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
      TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
      TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
      TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
      TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
      TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
      TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
      TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
      TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
      TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
      TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL});
    when(mockCyclomatic.getDefaultTokens()).thenReturn(new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
      TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
      TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION, TokenTypes.LAND,
      TokenTypes.LOR});
    when(mockExecutable.getDefaultTokens()).thenReturn(new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
      TokenTypes.STATIC_INIT, TokenTypes.SLIST});
    when(mockComment.getDefaultTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});
    
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
        TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
        TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
        TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
        TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
        TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
        TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
        TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
        TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
        TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
        TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
        TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
        TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
        TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    int[] actual = maintainabilityIndex.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetAcceptableTokens()
  {
    when(mockHalstead.getAcceptableTokens()).thenReturn(new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
        TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
        TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
        TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
        TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
        TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
        TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
        TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
        TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
        TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
        TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
        TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
        TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
        TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL});
    when(mockCyclomatic.getAcceptableTokens()).thenReturn(new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
        TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION, TokenTypes.LAND,
        TokenTypes.LOR});
    when(mockExecutable.getAcceptableTokens()).thenReturn(new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST});
    when(mockComment.getAcceptableTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});
    
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
        TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
        TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
        TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
        TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
        TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
        TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
        TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
        TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
        TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
        TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
        TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
        TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
        TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    int[] actual = maintainabilityIndex.getAcceptableTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testGetRequiredTokens()
  {
    when(mockHalstead.getRequiredTokens()).thenReturn(new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
        TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
        TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
        TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
        TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
        TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
        TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
        TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
        TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
        TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
        TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
        TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
        TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
        TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL});
    when(mockCyclomatic.getRequiredTokens()).thenReturn(new int[] {TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT,});
    when(mockExecutable.getRequiredTokens()).thenReturn(new int[] {TokenTypes.SLIST});
    when(mockComment.getRequiredTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});
    
    int[] expected = {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
        TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, 
        TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, 
        TokenTypes.DIV_ASSIGN, TokenTypes.DO_WHILE, TokenTypes.DOT, TokenTypes.ENUM, TokenTypes.EQUAL, 
        TokenTypes.EXPR, TokenTypes.FINAL,TokenTypes.GE, TokenTypes.GT, TokenTypes.IMPORT, TokenTypes.INC, 
        TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LCURLY, TokenTypes.LE, TokenTypes.LITERAL_ASSERT, 
        TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, 
        TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_ELSE,
        TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_FOR, 
        TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_INTERFACE,
        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL,
        TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RETURN,
        TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
        TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID,
        TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
        TokenTypes.LE, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
        TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, 
        TokenTypes.NUM_LONG, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, 
        TokenTypes.QUESTION, TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL,
        TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
        TokenTypes.UNARY_PLUS, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN,
        TokenTypes.SINGLE_LINE_COMMENT};
    int[] actual = maintainabilityIndex.getRequiredTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);
  }
  
  @Test
  public void testIsCommentNodesRequired()
  {
    doReturn(true).when(mockComment).isCommentNodesRequired();
    assertEquals(true, maintainabilityIndex.isCommentNodesRequired());
  }
  
  @Test
  public void testBeginTree()
  {
    maintainabilityIndex.beginTree(astMock);
    
    Mockito.verify(mockHalstead).beginTree(astMock);
    Mockito.verify(mockCyclomatic).beginTree(astMock);
    Mockito.verify(mockExecutable).beginTree(astMock);
    Mockito.verify(mockComment).beginTree(astMock);
  }
  
  @Test
  public void testVisitToken()
  {
    maintainabilityIndex.visitToken(astMock);
    
    Mockito.verify(mockHalstead).visitToken(astMock);
    Mockito.verify(mockCyclomatic).visitToken(astMock);
    Mockito.verify(mockExecutable).visitToken(astMock);
    Mockito.verify(mockComment).visitToken(astMock);
  }
  
  @Test
  public void testLeaveToken()
  {
    maintainabilityIndex.leaveToken(astMock);
    
    Mockito.verify(mockCyclomatic).leaveToken(astMock);
    Mockito.verify(mockExecutable).leaveToken(astMock);
  }
  
  @Test
  public void testFinishTree()
  {
    // Can't test due to log
    /*
    Mockito.doNothing().when(mockHalstead).setAllHalstead();
    
    maintainabilityIndex.finishTree(astMock);
    
    Mockito.verify(mockHalstead).setAllHalstead();
    Mockito.verify(maintainabilityIndex).setMaintainabilityIndex();
    */
  }
}
