package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CastCounter extends AbstractCheck {
	
	private int numCasts;
	
	public CastCounter() {
	  this.numCasts = 0;
	}
	
	public int getNumCasts() {
		return numCasts;
	}

	@Override
	public void beginTree(DetailAST rootAST) {
	  numCasts = 0;
	}
	
	@Override
	public int[] getAcceptableTokens() {
	  return getDefaultTokens();
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.TYPECAST};
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	@Override
	public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.TYPECAST) {
	    numCasts++;
	  }
	}
	
	@Override 
	public void finishTree(DetailAST rootAST) {
	  log(rootAST, "castCounter", numCasts);
	}
	
}
