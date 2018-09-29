package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HalsteadMetrics extends AbstractCheck{
	
  private int hLength;
	private int hVocab;
	private double hVolume;
	private double hDifficulty;
	private double hEffort;
	private int temp;
	
	public HalsteadMetrics()
	{
	  this.hLength = 0;
	  this.hVocab = 0;
	  this.hVolume = 0;
	  this.hDifficulty = 0;
	  this.hEffort = 0;
	  this.temp = 0;
	}
	
	public void setAllHalstead() {
	  setHalsteadLength();
	  setHalsteadVocab();
	  setHalsteadVolume();
	  setHalsteadDifficulty();
	  setHalsteadEffort();
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
	  /*
	  ExpressionCounter expressionCounter = new ExpressionCounter();
		hLength = expressionCounter.getNumOperators() + expressionCounter.getNumOperands();
		
		CommentCounter commentCounter = new CommentCounter();
		temp = commentCounter.getNumComments();
		*/
	}
	
	public void setHalsteadVocab() {
	  /*
    ExpressionCounter expressionCounter = new ExpressionCounter();
	  hVocab = expressionCounter.getNumUniqueOperators() + expressionCounter.getNumUniqueOperands();
	  */
	}
	
	public void setHalsteadVolume() {
		hVolume = hLength * (Math.log(hVocab)/Math.log(2));
	}
	
	public void setHalsteadDifficulty() {
	  /*
    ExpressionCounter expressionCounter = new ExpressionCounter();
		hDifficulty = ((expressionCounter.getNumUniqueOperators()/2) * expressionCounter.getNumOperands())
                  /expressionCounter.getNumUniqueOperands();
                   * 
                   */
	}
	
	public void setHalsteadEffort() {
		hEffort = hDifficulty * hVolume;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

  @Override
  public int[] getRequiredTokens() {
    // TODO Auto-generated method stub
    return null;
  }
  
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
  public void finishTree(DetailAST rootAST) {
    log(rootAST, "hLength", hLength);
    log(rootAST, "hVocab", hVocab);
    log(rootAST, "hVolume", hVolume);
    log(rootAST, "hdifficulty", hDifficulty);
    log(rootAST, "hEffort", hEffort);
    log(rootAST, "temp", temp);
  }
}
