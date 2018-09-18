package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class LoopingStatementCounter extends AbstractCheck {
	
	private int numLoopingStatements;
	
	public void countLoopingStatements(String code) {
		
	}
	
	public int getNumLoopingStatements() {
		return numLoopingStatements;
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
