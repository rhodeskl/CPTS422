package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HalsteadMetrics extends AbstractCheck{
	
  private int hLength;
	private int hVocab;
	private double hVolume;
	private double hDifficulty;
	private double hEffort;
	private ExpressionCounter expression_counter;
	
	public HalsteadMetrics()
	{
	  this.hLength = 0;
	  this.hVocab = 0;
	  this.hVolume = 0;
	  this.hDifficulty = 0;
	  this.hEffort = 0;
	  this.expression_counter = new ExpressionCounter();
	}
	
	
	//Calculate all the Halstead values
	public void setAllHalstead() {
	  setHalsteadLength();
	  setHalsteadVocab();
	  setHalsteadVolume();
	  setHalsteadDifficulty();
	  setHalsteadEffort();
	}
	
	public boolean setExpressionCounter(ExpressionCounter expC) {
	  this.expression_counter = expC;
	  return true;
	}
	public int getHalsteadLength() {
	  return hLength;
	}

  public int getHalsteadVocab() {
    return hVocab;
  }
  
  public double getHalsteadVolume() {
    return hVolume;
  }

  public double getHalsteadDifficulty() {
    return hDifficulty;
  }

  public double getHalsteadEffort() {
    return hEffort;
  }
	public void setHalsteadLength() {
		hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();
	}
	
	public void setHalsteadVocab() {
	  hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
	}
	
	public void setHalsteadVolume() {
		hVolume = hLength * (Math.log(hVocab)/Math.log(2));
	}
	
	public void setHalsteadDifficulty() {
		hDifficulty = (double)((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())
		        /expression_counter.getNumUniqueOperands();
	}
	
	public void setHalsteadEffort() {
		hEffort = hDifficulty * hVolume;
	}

	
	@Override
  public int[] getDefaultTokens() {
    return expression_counter.getDefaultTokens();
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return expression_counter.getAcceptableTokens();
  }
  
  @Override
  public int[] getRequiredTokens() {
    return expression_counter.getRequiredTokens();
  }
  
  @Override
  public void beginTree(DetailAST rootAST) {
    this.hLength = 0;
    this.hVocab = 0;
    this.hVolume = 0;
    this.hDifficulty = 0;
    this.hEffort = 0;
    expression_counter.beginTree(rootAST);
  }
  
  @Override
  public void visitToken(DetailAST ast) {
    expression_counter.visitToken(ast);
  }
	
	@Override
  public void finishTree(DetailAST rootAST) {
	  setAllHalstead();
    log(rootAST, "hLength", hLength);
    log(rootAST, "hVocab", hVocab);
    log(rootAST, "hVolume", hVolume);
    log(rootAST, "hDifficulty", hDifficulty);
    log(rootAST, "hEffort", hEffort);
  }
}
