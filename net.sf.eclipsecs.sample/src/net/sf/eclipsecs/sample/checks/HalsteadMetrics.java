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
	
	public void SetAllHalstead() {
	  SetHalsteadLength();
	  SetHalsteadVocab();
	  SetHalsteadVolume();
	  SetHalsteadDifficulty();
	  SetHalsteadEffort();
	}
	
	public int GetHalsteadLength() {
	  return hLength;
	}

  public int GetHalsteadVocab() {
    return hVocab;
  }
  
  public double GetHalsteadVolume() {
    return hVolume;
  }

  public double GetHalsteadDifficulty() {
    return hDifficulty;
  }

  public double GetHalsteadEffort() {
    return hEffort;
  }
	public void SetHalsteadLength() {
	  /*
	  ExpressionCounter expressionCounter = new ExpressionCounter();
		hLength = expressionCounter.getNumOperators() + expressionCounter.getNumOperands();
		
		CommentCounter commentCounter = new CommentCounter();
		temp = commentCounter.getNumComments();
		*/
	  hLength = 0;
	  temp = 0;
	}
	
	public void SetHalsteadVocab() {
	  /*
    ExpressionCounter expressionCounter = new ExpressionCounter();
	  hVocab = expressionCounter.getNumUniqueOperators() + expressionCounter.getNumUniqueOperands();
	  */
    hVocab = 0;
	}
	
	public void SetHalsteadVolume() {
		hVolume = hLength * (Math.log(hVocab)/Math.log(2));
	}
	
	public void SetHalsteadDifficulty() {
	  /*
    ExpressionCounter expressionCounter = new ExpressionCounter();
		hDifficulty = ((expressionCounter.getNumUniqueOperators()/2) * expressionCounter.getNumOperands())
                  /expressionCounter.getNumUniqueOperands();
                   * 
                   */
    hDifficulty = 0;
	}
	
	public void SetHalsteadEffort() {
		hEffort = hDifficulty * hVolume;
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
	
	@Override
  public void finishTree(DetailAST rootAST) {
    log(rootAST, "Halstead Length", hLength);
    log(rootAST, "Halstead Vocab", hVocab);
    log(rootAST, "Halstead Volume", hVolume);
    log(rootAST, "Halstead Difficulty", hDifficulty);
    log(rootAST, "Halstead Effort", hEffort);
    log(rootAST, "Testing New Instance", temp);
  }
}
