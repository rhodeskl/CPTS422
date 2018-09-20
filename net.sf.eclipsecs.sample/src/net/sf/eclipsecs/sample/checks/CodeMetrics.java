package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

public class CodeMetrics extends AbstractCheck {
	
	private SimpleMetrics simpleMetrics;
	private HalsteadMetrics halsteadMetrics;
	private float maintainability;
	
	public void acquireSimple(String code) {
		
	}
	
	public void getMaintainabilityIndex(HalsteadMetrics halsteadMetrics) {
		
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
