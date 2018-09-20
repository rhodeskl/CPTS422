package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableCounter extends AbstractCheck {
	
	private int numVariables;
	
	public VariableCounter() {
		this.numVariables = 0;
	}
	
	public int getNumVariables() {
		return numVariables;
	}
	
	@Override
	public void beginTree(DetailAST rootAST) {
		numVariables = 0;
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.VARIABLE_DEF};
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		if(ast.getType() == TokenTypes.VARIABLE_DEF) {
			numVariables++;
		}
	}
	
	@Override 
	public void finishTree(DetailAST rootAST) {
		log(rootAST, "Number of variables = " + numVariables);
	}

}
