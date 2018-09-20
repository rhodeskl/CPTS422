package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class MethodCounter extends AbstractCheck {
	
	private int numExternalReferences;
	private int numLocalReferences;
	
	public void countExternalMethodRef(String code) {
		
	}
	
	public void countLocalMethodRef(String code) {
		
	}
	
	public int getNumExternalReferences( ) {
		return numExternalReferences;
	}
	
	public int getNumLocalReferences() {
		return numLocalReferences;
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
