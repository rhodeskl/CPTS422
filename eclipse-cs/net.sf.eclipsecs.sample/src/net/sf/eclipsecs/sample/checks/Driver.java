package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class Driver 
{
  
  public CastCounter castCounter;
  public CommentCounter commentCounter;
  public ExpressionCounter expressionCounter;
  public HalsteadMetrics halsteadMetrics;
  public LoopingStatementCounter loopingStatementCounter;
  public MaintainabilityIndex maintainabilityIndex;
  public MethodCounter methodCounter;
  public VariableCounter variableCounter;
    public static void main( String[] args )
    {
      Driver driver = new Driver(new CastCounter(), new CommentCounter(), new ExpressionCounter(), new HalsteadMetrics(), new LoopingStatementCounter(), new MaintainabilityIndex(), new MethodCounter(), new VariableCounter());
      DetailAST detailAST = new DetailAST();
      
      detailAST.setType(TokenTypes.TYPECAST);
      int numCasts = driver.runCastCounter(detailAST);
      System.out.println("Number of casts = " + numCasts);
      
      detailAST.setType(TokenTypes.SINGLE_LINE_COMMENT);
      int[] commentCounterResults = driver.runCommentCounter(detailAST);
      System.out.println("Number of comments = " + commentCounterResults[0]);
      System.out.println("Number of comment lines = " + commentCounterResults[1]);
      
      detailAST.setType(TokenTypes.ABSTRACT);
      int[] expressionCounterResults = driver.runExpressionCounter(detailAST);
      System.out.println("Number of expressions = " + expressionCounterResults[0]);
      System.out.println("Number of operators = " + expressionCounterResults[1]);
      System.out.println("Number of operands = " + expressionCounterResults[2]);
      System.out.println("Number of unique operators = " + expressionCounterResults[3]);
      System.out.println("Number of unique operands = " + expressionCounterResults[4]);
      
      double[] halsteadMetricsResults = driver.runHalsteadMetrics(detailAST);
      System.out.println("Halstead length = " + halsteadMetricsResults[0]);
      System.out.println("Halstead vocab = " + halsteadMetricsResults[1]);
      System.out.println("Halstead volume = " + halsteadMetricsResults[2]);
      System.out.println("Halstead difficulty = " + halsteadMetricsResults[3]);
      System.out.println("Halstead effort = " + halsteadMetricsResults[4]);
      
      detailAST.setType(TokenTypes.LITERAL_FOR);
      int numLooping = driver.runLoopingStatementCounter(detailAST);
      System.out.println("Number of looping statements = " + numLooping);
      
      detailAST.setType(TokenTypes.ABSTRACT);
      double mIndex = driver.runMaintainabilityIndex(detailAST);
      System.out.println("Maintainability index = " + mIndex);
      
      detailAST.setType(TokenTypes.METHOD_CALL);
      DetailAST child = new DetailAST();
      child.setType(TokenTypes.DOT);
      detailAST.setFirstChild(child);
      int[] methodCounterResults = driver.runMethodCounter(detailAST);
      System.out.println("Number of external references = " + methodCounterResults[0]);
      System.out.println("Number of local references = " + methodCounterResults[1]);
      
      detailAST.setType(TokenTypes.VARIABLE_DEF);
      int numVariables = driver.runVariableCounter(detailAST);
      System.out.println("Number of variables = " + numVariables);
    }
    
    public Driver(CastCounter castCounter, CommentCounter commentCounter, ExpressionCounter expressionCounter, HalsteadMetrics halsteadMetrics, LoopingStatementCounter loopingStatementCounter, MaintainabilityIndex maintainabilityIndex, MethodCounter methodCounter, VariableCounter variableCounter) {
      this.castCounter = castCounter;
      this.commentCounter = commentCounter;
      this.expressionCounter = expressionCounter;
      this.halsteadMetrics = halsteadMetrics;
      this.loopingStatementCounter = loopingStatementCounter;
      this.maintainabilityIndex = maintainabilityIndex;
      this.methodCounter = methodCounter;
      this.variableCounter = variableCounter;
    }
    
    public int runCastCounter(DetailAST detailAST) {
      castCounter.beginTree(detailAST);
      castCounter.visitToken(detailAST);
      return castCounter.getNumCasts();
    }
    
    public int[] runCommentCounter(DetailAST detailAST) {
      commentCounter.beginTree(detailAST);
      commentCounter.visitToken(detailAST);
      int[] results = new int[2];
      results[0] = commentCounter.getNumComments();
      results[1] = commentCounter.getNumLines();
      return results;
    }
    
    public int[] runExpressionCounter(DetailAST detailAST) {
      expressionCounter.beginTree(detailAST);
      expressionCounter.visitToken(detailAST);
      int[] results = new int[5];
      results[0] = expressionCounter.getNumExpressions();
      results[1] = expressionCounter.getNumOperands();
      results[2] = expressionCounter.getNumOperators();
      results[3] = expressionCounter.getNumUniqueOperands();
      results[4] = expressionCounter.getNumUniqueOperators();
      return results;
    }
    
    public double[] runHalsteadMetrics(DetailAST detailAST) {
      halsteadMetrics.beginTree(detailAST);
      halsteadMetrics.visitToken(detailAST);
      double[] results = new double[6];
      results[0] = halsteadMetrics.getHalsteadLength();
      results[1] = halsteadMetrics.getHalsteadVocab();
      results[2] = halsteadMetrics.getHalsteadVolume();
      results[3] = halsteadMetrics.getHalsteadDifficulty();
      results[4] = halsteadMetrics.getHalsteadEffort();
      return results;
     }
    
    public int runLoopingStatementCounter(DetailAST detailAST) {
      loopingStatementCounter.beginTree(detailAST);
      loopingStatementCounter.visitToken(detailAST);
      return loopingStatementCounter.getNumLoopingStatements();
    }
    
    public double runMaintainabilityIndex(DetailAST detailAST) {
      maintainabilityIndex.beginTree(detailAST);
      maintainabilityIndex.visitToken(detailAST);
      return maintainabilityIndex.getMaintainabilityIndex();
    }
    
    public int[] runMethodCounter(DetailAST detailAST) {
      methodCounter.beginTree(detailAST);
      methodCounter.visitToken(detailAST);
      int[] results = new int[2];
      results[0] = methodCounter.getNumExternalReferences();
      results[1] = methodCounter.getNumLocalReferences();
      return results;
    }
    
    public int runVariableCounter(DetailAST detailAST) {
      variableCounter.beginTree(detailAST);
      variableCounter.visitToken(detailAST);
      return variableCounter.getNumVariables();
    }
    
}
