package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableCounter extends AbstractCheck {
	
	private int numVariables;
	
	public VariableCounter() {
		this.numVariables = 0;
	}
	
	public void countVariables(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		numVariables = objBlock.getChildCount(TokenTypes.VARIABLE_DEF);
		log(ast, "Number of Variables = " + numVariables);
	}
	
	public int getNumVariables() {
		return numVariables;
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		countVariables(ast);
	}

}
