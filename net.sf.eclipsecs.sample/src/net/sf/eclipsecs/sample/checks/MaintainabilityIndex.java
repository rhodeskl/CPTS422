package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class MaintainabilityIndex extends AbstractCheck {
  
  private double maintainability;
  
  public void SetMaintainabilityIndex() {
    HalsteadMetrics halstead_metrics = new HalsteadMetrics();
    
    halstead_metrics.SetHalsteadVolume();
    
    double v = halstead_metrics.GetHalsteadVolume();
    int g = 0; //Cyclomatic Complexity
    double loc = 0; //Lines of Code
    double cm = 0; //Percent of lines of Comment
    
    
    maintainability = 171 - 
             (5.2 * (Math.log(v)/Math.log(2))) - 
             (0.23 * g) - 
             (16.2 * (Math.log(loc)/Math.log(2))) + 
             (50 * Math.sin(Math.sqrt(2.4 * cm)));
  }
  
  public double GetMaintainabilityIndex() {
    return maintainability;
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
    log(rootAST, "Maintainability Index", GetMaintainabilityIndex());
  }
}
