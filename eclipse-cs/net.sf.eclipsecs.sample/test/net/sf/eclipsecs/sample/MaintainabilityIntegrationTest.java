package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.MaintainabilityIndex;
import net.sf.eclipsecs.sample.checks.CyclomaticComplexity;
import net.sf.eclipsecs.sample.checks.ExecutableStatementCount;
import net.sf.eclipsecs.sample.checks.CommentCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class MaintainabilityIntegrationTest {

  @Spy
  private CyclomaticComplexity mockCyclomatic = new CyclomaticComplexity();
  @Spy
  private ExecutableStatementCount mockExecutable = new ExecutableStatementCount();
  @Spy
  private CommentCounter mockComment = new CommentCounter();
  @InjectMocks
  private MaintainabilityIndex maintainabilityIndex;

  @Before
  public void setUp()
  {
  }

  @Test
  /*
   * Stubs: CommentCounter, CyclomaticComplexity, ExecutableStatementCount
   * Real: HalsteadMetrics
   */
  public void testMaintainabilityAndHalsteadIntegration()
  {
    when(mockComment.getDefaultTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});
    
    int[] expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    int[] actual = maintainabilityIndex.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);

    
    when(mockComment.getAcceptableTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});

    expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    actual = maintainabilityIndex.getAcceptableTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);

    
    when(mockComment.getRequiredTokens()).thenReturn(new int[] {TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT});
    
    expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN,
        TokenTypes.SINGLE_LINE_COMMENT};
    actual = maintainabilityIndex.getRequiredTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);

    doReturn(true).when(mockComment).isCommentNodesRequired();
    assertEquals(true, maintainabilityIndex.isCommentNodesRequired());


    DetailAST rootAST = new DetailAST();
    maintainabilityIndex.beginTree(rootAST);
    Mockito.verify(mockComment).beginTree(rootAST);

    DetailAST ast = new DetailAST();

    //Following 10 calls should give ExpressionCounter in HalsteadMetrics 10 numOperators and 10 numUniqueOperators 
    ast.setType(TokenTypes.ABSTRACT);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.BNOT);
    maintainabilityIndex.visitToken(ast);
    
    ast.setType(TokenTypes.LITERAL_CASE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LAND);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_PRIVATE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_WHILE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.MOD_ASSIGN);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NOT_EQUAL);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.QUESTION);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.STAR);
    maintainabilityIndex.visitToken(ast);

    //Following 5 calls should give ExpressionCounter in HalsteadMetrics 5 numOperands and 2 numUniqueOperands
    ast.setText("Text");
    ast.setType(TokenTypes.NUM_DOUBLE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NUM_FLOAT);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NUM_INT);
    maintainabilityIndex.visitToken(ast);

    ast.setText("Text2");
    ast.setType(TokenTypes.NUM_LONG);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.IDENT);
    maintainabilityIndex.visitToken(ast);
    
    //Following 5 calls should give ExpressionCounter in HalsteadMetrics 5 numExpressions
    ast.setType(TokenTypes.EXPR);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    
    //Following 2 calls should give CommentCounter 2 numComments and 8 numLines
    ast.setType(TokenTypes.SINGLE_LINE_COMMENT);
    maintainabilityIndex.visitToken(ast);
    ast.setLineNo(0);
    ast.setType(TokenTypes.BLOCK_COMMENT_BEGIN);
    DetailAST ast2 = new DetailAST();
    ast2.setLineNo(7);
    ast2.setType(TokenTypes.BLOCK_COMMENT_END);
    ast.addChild(ast2);
    maintainabilityIndex.visitToken(ast);
    
    Mockito.verify(mockComment, times(22)).visitToken(ast);

    maintainabilityIndex.leaveToken(ast);

    doReturn(10).when(mockCyclomatic).getCurrentValue();
    doReturn(10).when(mockExecutable).getNumLines();
    doReturn(10).doReturn(10).when(mockComment).getNumComments();
    maintainabilityIndex.setMaintainabilityIndex();
    assertEquals(129.44735422545663, maintainabilityIndex.getMaintainabilityIndex());
  }
  

  
  @Test
  /*
   * Stubs: CyclomaticComplexity, ExecutableStatementCount
   * Real: HalsteadMetrics, CommentCounter
   */
  public void testFullMaintainabilityIntegration()
  {
    int[] expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    int[] actual = maintainabilityIndex.getDefaultTokens();
    boolean isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);


    expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION, TokenTypes.LAND, TokenTypes.LOR, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT};
    actual = maintainabilityIndex.getAcceptableTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);


    expected = new int[] {TokenTypes.ABSTRACT, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, 
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
        TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
        TokenTypes.NOT_EQUAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG, 
        TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN,TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
        TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.SEMI, TokenTypes.SL, TokenTypes.SL_ASSIGN, 
        TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
        TokenTypes.IDENT, TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT, TokenTypes.SLIST, TokenTypes.BLOCK_COMMENT_BEGIN,
        TokenTypes.SINGLE_LINE_COMMENT};
    actual = maintainabilityIndex.getRequiredTokens();
    isEqual = Arrays.equals(expected, actual);
    assertEquals(true, isEqual);

    assertEquals(true, maintainabilityIndex.isCommentNodesRequired());


    DetailAST rootAST = new DetailAST();
    maintainabilityIndex.beginTree(rootAST);


    DetailAST ast = new DetailAST();

    //Following 10 calls should give ExpressionCounter in HalsteadMetrics 10 numOperators and 10 numUniqueOperators 
    ast.setType(TokenTypes.ABSTRACT);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.BNOT);
    maintainabilityIndex.visitToken(ast);
    
    ast.setType(TokenTypes.LITERAL_CASE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LAND);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_PRIVATE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.LITERAL_WHILE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.MOD_ASSIGN);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NOT_EQUAL);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.QUESTION);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.STAR);
    maintainabilityIndex.visitToken(ast);

    //Following 5 calls should give ExpressionCounter in HalsteadMetrics 5 numOperands and 2 numUniqueOperands
    ast.setText("Text");
    ast.setType(TokenTypes.NUM_DOUBLE);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NUM_FLOAT);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.NUM_INT);
    maintainabilityIndex.visitToken(ast);

    ast.setText("Text2");
    ast.setType(TokenTypes.NUM_LONG);
    maintainabilityIndex.visitToken(ast);

    ast.setType(TokenTypes.IDENT);
    maintainabilityIndex.visitToken(ast);
    
    //Following 5 calls should give ExpressionCounter in HalsteadMetrics 5 numExpressions
    ast.setType(TokenTypes.EXPR);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    maintainabilityIndex.visitToken(ast);
    
    //Following 2 calls should give CommentCounter 2 numComments and 9 numLines
    ast.setType(TokenTypes.SINGLE_LINE_COMMENT);
    maintainabilityIndex.visitToken(ast);
    ast.setLineNo(0);
    ast.setType(TokenTypes.BLOCK_COMMENT_BEGIN);
    DetailAST ast2 = new DetailAST();
    ast2.setLineNo(7);
    ast2.setType(TokenTypes.BLOCK_COMMENT_END);
    ast.addChild(ast2);
    maintainabilityIndex.visitToken(ast);

    maintainabilityIndex.leaveToken(ast);

    doReturn(10).when(mockCyclomatic).getCurrentValue();
    doReturn(10).when(mockExecutable).getNumLines();
    maintainabilityIndex.setMaintainabilityIndex();
    assertEquals(114.54710787985707, maintainabilityIndex.getMaintainabilityIndex());
  }
}
