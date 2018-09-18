package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class VariableCounter extends AbstractCheck {
	
	private int numVariables;
	
	public void countVariables(String code) {
		
	}
	
	public int getNumVariables() {
		return numVariables;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}

}
