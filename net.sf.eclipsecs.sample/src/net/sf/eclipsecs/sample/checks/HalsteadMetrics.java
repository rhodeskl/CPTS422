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
	  ExpressionCounter expressionCounter = new ExpressionCounter();
		hLength = expressionCounter.getNumOperators() + expressionCounter.getNumOperands();
		
		CommentCounter commentCounter = new CommentCounter();
		temp = commentCounter.getNumComments();
	}
	
	public void SetHalsteadVocab() {
    ExpressionCounter expressionCounter = new ExpressionCounter();
	  //hVocab = expressionCounter.getUniqueOperators() + expressionCounter.getUniqueOperands();
    hVocab = 0;
	}
	
	public void SetHalsteadVolume() {
		hVolume = hLength * (Math.log(hVocab)/Math.log(2));
	}
	
	public void SetHalsteadDifficulty() {
    ExpressionCounter expressionCounter = new ExpressionCounter();
		//hDifficulty = ((expressionCounter.getUniqueOperators()/2) * expressionCounter.getNumOperands())
    //              /expressionCounter.getUniqueOperands();
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
	  SetAllHalstead();
    log(rootAST, "Halstead Length", GetHalsteadLength());
    log(rootAST, "Halstead Vocab", GetHalsteadVocab());
    log(rootAST, "Halstead Volume", GetHalsteadVolume());
    log(rootAST, "Halstead Difficulty", GetHalsteadDifficulty());
    log(rootAST, "Halstead Effort", GetHalsteadEffort());
    log(rootAST, "Testing New Instance", temp);
  }
}
