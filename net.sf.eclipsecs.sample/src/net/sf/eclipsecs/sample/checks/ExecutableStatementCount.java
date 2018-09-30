package net.sf.eclipsecs.sample.checks;

import java.util.ArrayDeque;
import java.util.Deque;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExecutableStatementCount extends AbstractCheck{
  private final Deque<Context> contextStack = new ArrayDeque<>();
  private Context context;
  
  public ExecutableStatementCount()
  {
    
  }
  
  @Override
  public int[] getDefaultTokens() {
    return new int[] {
        TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT,
        TokenTypes.SLIST,
    };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[] {TokenTypes.SLIST};
  }

  @Override
  public int[] getAcceptableTokens() {
    return new int[] {
        TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT,
        TokenTypes.SLIST,
    };
  }

  @Override
  public void beginTree(DetailAST rootAST) {
    context = new Context(null);
    contextStack.clear();
  }

  @Override
  public void visitToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.CTOR_DEF:
      case TokenTypes.METHOD_DEF:
      case TokenTypes.INSTANCE_INIT:
      case TokenTypes.STATIC_INIT:
        visitMemberDef(ast);
        break;
      case TokenTypes.SLIST:
            visitSlist(ast);
            break;
    }
  }

  @Override
  public void leaveToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.CTOR_DEF:
      case TokenTypes.METHOD_DEF:
      case TokenTypes.INSTANCE_INIT:
      case TokenTypes.STATIC_INIT:
        leaveMemberDef(ast);
        break;
      case TokenTypes.SLIST:
        // Do nothing
        break;
    }
  }
  
  public int getNumLines()
  {
    return context.getCount();
  }

  private void visitMemberDef(DetailAST ast) {
    contextStack.push(context);
    context = new Context(ast);
  }

  private void leaveMemberDef(DetailAST ast) {
    context = contextStack.pop();
  }

  private void visitSlist(DetailAST ast) {
    if (context.getAST() != null) {
      // find member AST for the statement list
      final DetailAST contextAST = context.getAST();
      DetailAST parent = ast.getParent();
      int type = parent.getType();
      while (type != TokenTypes.CTOR_DEF
              && type != TokenTypes.METHOD_DEF
              && type != TokenTypes.INSTANCE_INIT
              && type != TokenTypes.STATIC_INIT) {
        parent = parent.getParent();
        type = parent.getType();
      }
      if (parent == contextAST) {
        context.addCount(ast.getChildCount() / 2);
      }
    }
  }

  private static class Context {
    private final DetailAST ast;
    private int count;
  
    Context(DetailAST ast) {
      this.ast = ast;
      count = 0;
    }
    
    public void addCount(int addition) {
      count += addition;
    }
  
    public DetailAST getAST() {
      return ast;
    }
    
    public int getCount() {
      return count;
    }
  }
}
