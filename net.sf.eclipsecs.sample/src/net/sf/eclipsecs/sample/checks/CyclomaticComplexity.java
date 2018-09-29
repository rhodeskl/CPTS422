package net.sf.eclipsecs.sample.checks;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CyclomaticComplexity extends AbstractCheck{
  private static final BigInteger INITIAL_VALUE = BigInteger.ONE;
  private final Deque<BigInteger> valueStack = new ArrayDeque<>();
  private boolean switchBlockAsSingleDecisionPoint;
  private BigInteger currentValue = INITIAL_VALUE;
  
  public void setSwitchBlockAsSingleDecisionPoint(boolean switchBlockAsSingleDecisionPoint) {
    this.switchBlockAsSingleDecisionPoint = switchBlockAsSingleDecisionPoint;
  }
  
  public int getCurrentValue()
  {
    return currentValue.intValue();
  }
  
  @Override
  public int[] getDefaultTokens() {
    return new int[] {
        TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT,
        TokenTypes.LITERAL_WHILE,
        TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR,
        TokenTypes.LITERAL_IF,
        TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION,
        TokenTypes.LAND,
        TokenTypes.LOR,
    };
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] {
        TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT,
        TokenTypes.LITERAL_WHILE,
        TokenTypes.LITERAL_DO,
        TokenTypes.LITERAL_FOR,
        TokenTypes.LITERAL_IF,
        TokenTypes.LITERAL_SWITCH,
        TokenTypes.LITERAL_CASE,
        TokenTypes.LITERAL_CATCH,
        TokenTypes.QUESTION,
        TokenTypes.LAND,
        TokenTypes.LOR,
    };
  }
  
  @Override
  public final int[] getRequiredTokens() {
    return new int[] {
        TokenTypes.CTOR_DEF,
        TokenTypes.METHOD_DEF,
        TokenTypes.INSTANCE_INIT,
        TokenTypes.STATIC_INIT,
    };
  }

  @Override
  public void visitToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.CTOR_DEF:
      case TokenTypes.METHOD_DEF:
      case TokenTypes.INSTANCE_INIT:
      case TokenTypes.STATIC_INIT:
        visitMethodDef();
        break;
      default:
        visitTokenHook(ast);
    }
  }

  @Override
  public void leaveToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.CTOR_DEF:
      case TokenTypes.METHOD_DEF:
      case TokenTypes.INSTANCE_INIT:
      case TokenTypes.STATIC_INIT:
        leaveMethodDef(ast);
        break;
      default:
        break;
    }
  }

  private void visitTokenHook(DetailAST ast) {
    if (switchBlockAsSingleDecisionPoint) {
      if (ast.getType() != TokenTypes.LITERAL_CASE) {
        incrementCurrentValue(BigInteger.ONE);
      }
    }
    else if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
      incrementCurrentValue(BigInteger.ONE);
    }
  }

  private void leaveMethodDef(DetailAST ast) {
    popValue();
  }

  private void incrementCurrentValue(BigInteger amount) {
    currentValue = currentValue.add(amount);
  }
  
  private void pushValue() {
    valueStack.push(currentValue);
    currentValue = INITIAL_VALUE;
  }

  private void popValue() {
    currentValue = valueStack.pop();
  }
  
  private void visitMethodDef() {
    pushValue();
  }
}
