package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class ExpressionCounter extends AbstractCheck {
	
  private int numExpressions;
  private int numOperators;
  private int numOperands;
  private int numUniqueOperators;
  private int numUniqueOperands;

 
	public void countExpressions(String code) {
		
	}
	
	public void countOperands(String code) {
		
	}
	
	public void countOperators(String code) {
		
	}
	
	public void countUniqueOperands(String code) {
	  
	}
	
	public void countUniqueOperators(String code) {
	  
	}
	
	public int getNumExpressions() {
		return numExpressions;
	}
	
	public int getNumOperators() {
		return numOperators;
	}
	
	public int getNumOperands() {
		return numOperands;
	}
	
	public int getNumUniqueOperators() {
	  return numUniqueOperators;
	}

	public int getNumUniqueOperands() {
	  return numUniqueOperands;
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
