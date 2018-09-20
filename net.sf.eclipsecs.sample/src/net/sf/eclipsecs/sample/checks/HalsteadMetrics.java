package cpts422.structural_metrics;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class HalsteadMetrics extends AbstractCheck {
	
	private int hVocab;
	private int hVolume;
	private int hDifficulty;
	private int hEffort;
	
	public int getHalsteadLength(ExpressionCounter expressionCounter) {
		int hLength = expressionCounter.getNumOperators() + expressionCounter.getNumOperands();
		return hLength;
	}
	
	public void getHalsteadVocab(ExpressionCounter expressionCounter) {
	}
	
	public void getHalsteadVolume() {
		
	}
	
	public void getHalsteadDifficulty() {
		
	}
	
	public void getHalsteadEffort() {
		
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
