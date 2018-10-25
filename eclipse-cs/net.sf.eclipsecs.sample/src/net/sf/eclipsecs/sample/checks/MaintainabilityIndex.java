package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class MaintainabilityIndex extends AbstractCheck{
  
  private double maintainability_index;
  private HalsteadMetrics halstead_metrics;
  private CyclomaticComplexity cyclomatic_complexity;
  private ExecutableStatementCount executable_counter;
  private CommentCounter comment_counter;
  
  public MaintainabilityIndex()
  {
    this.maintainability_index = 0;
    this.halstead_metrics = new HalsteadMetrics();
    this.cyclomatic_complexity = new CyclomaticComplexity();
    this.executable_counter = new ExecutableStatementCount();
    this.comment_counter = new CommentCounter();
  }
  
  public void setMaintainabilityIndex() {
    double v = halstead_metrics.getHalsteadVolume();
    int g = cyclomatic_complexity.getCurrentValue();
    int loc = executable_counter.getNumLines();
    double cm = (double)comment_counter.getNumComments()/(comment_counter.getNumComments()+loc);
    
    //Maintainability Index formula. Have to use log(x)/log(2) to get log base 2.
    maintainability_index = 171 -
             (5.2 * (Math.log(v)/Math.log(2))) - 
             (0.23 * g) - 
             (16.2 * (Math.log(loc))/(Math.log(2))) + 
             (50 * Math.sin(Math.sqrt(2.4 * cm)));
  }
  
  public double getMaintainabilityIndex() 
  {
    return maintainability_index;
  }
  
  //Used to merge all the token arrays into one array
  private int[] merge(int[] arr1, int[] arr2)
  {
    int[] merged_arr = new int[arr1.length + arr2.length];
    int i = 0;
    for (int n : arr1) merged_arr[i++] = n;
    for (int n : arr2) merged_arr[i++] = n;
    
    return merged_arr;
  }
  
  
  @Override
  public int[] getDefaultTokens() {
    return merge(merge(merge(halstead_metrics.getDefaultTokens(),
            cyclomatic_complexity.getDefaultTokens()),
            executable_counter.getDefaultTokens()),
            comment_counter.getDefaultTokens());
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return merge(merge(merge(halstead_metrics.getAcceptableTokens(),
            cyclomatic_complexity.getAcceptableTokens()),
            executable_counter.getAcceptableTokens()),
            comment_counter.getAcceptableTokens());
  }
  
  @Override
  public int[] getRequiredTokens() {
    return merge(merge(merge(halstead_metrics.getRequiredTokens(),
            cyclomatic_complexity.getRequiredTokens()),
            executable_counter.getRequiredTokens()),
            comment_counter.getRequiredTokens());
  }

  @Override
  public boolean isCommentNodesRequired() {
    return comment_counter.isCommentNodesRequired();
  }

  @Override
  public void beginTree(DetailAST rootAST) {
    halstead_metrics.beginTree(rootAST);
    cyclomatic_complexity.beginTree(rootAST);
    executable_counter.beginTree(rootAST);
    comment_counter.beginTree(rootAST);
  }
  
  @Override
  public void visitToken(DetailAST ast) {
    halstead_metrics.visitToken(ast);
    cyclomatic_complexity.visitToken(ast);
    executable_counter.visitToken(ast);
    comment_counter.visitToken(ast);
  }
  
  @Override
  public void leaveToken(DetailAST ast) {
    cyclomatic_complexity.leaveToken(ast);
    executable_counter.leaveToken(ast);
  }
  
  @Override
  public void finishTree(DetailAST rootAST) {
    halstead_metrics.setAllHalstead();
    setMaintainabilityIndex();
    log(rootAST, "maintainability", maintainability_index);
  }
}

