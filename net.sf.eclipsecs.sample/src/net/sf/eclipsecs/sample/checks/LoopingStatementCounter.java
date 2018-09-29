package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingStatementCounter extends AbstractCheck {
	
	private int numLoopingStatements;
	
	public LoopingStatementCounter() {
    this.numLoopingStatements = 0;    
  }
	
	@Override
  public void beginTree(DetailAST rootAST) {
	  numLoopingStatements = 0;
  }
	
	/*public void countLoopingStatements(String code) {
		
	}*/
	
	@Override
  public void visitToken(DetailAST ast) {
    if((ast.getType() == TokenTypes.LITERAL_FOR)||
       (ast.getType() == TokenTypes.LITERAL_WHILE)||
       (ast.getType() == TokenTypes.LITERAL_DO))
    {
      numLoopingStatements++;
    }
  }
	
	@Override 
  public void finishTree(DetailAST rootAST) {
    log(rootAST, "loopingCounter", numLoopingStatements);
  }
	
	public int getNumLoopingStatements() {
		return numLoopingStatements;
	}

	@Override
	public int[] getAcceptableTokens() {
	  return getDefaultTokens();
	}

	@Override
	public int[] getDefaultTokens() {		
	  return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE,TokenTypes.LITERAL_DO };		
	}

	@Override
	public int[] getRequiredTokens() {
	  return getDefaultTokens();
	}

}
