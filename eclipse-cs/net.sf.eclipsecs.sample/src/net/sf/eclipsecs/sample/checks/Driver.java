package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class Driver 
{
    public static void main( String[] args )
    {
      Driver driver = new Driver();
      CastCounter castCounter = new CastCounter();
      CommentCounter commentCounter = new CommentCounter();
      ExpressionCounter expressionCounter = new ExpressionCounter();
      HalsteadMetrics halsteadMetrics = new HalsteadMetrics();
      LoopingStatementCounter loopingStatementCounter = new LoopingStatementCounter();
      MaintainabilityIndex maintainabilityIndex = new MaintainabilityIndex();
      MethodCounter methodCounter = new MethodCounter();
      VariableCounter variableCounter = new VariableCounter();
      DetailAST detailAST = new DetailAST();
      
      detailAST.setType(TokenTypes.TYPECAST);
      int numCasts = driver.runCastCounter(castCounter, detailAST);
      System.out.println("Number of casts = " + numCasts);
      
      detailAST.setType(TokenTypes.SINGLE_LINE_COMMENT);
      int[] commentCounterResults = driver.runCommentCounter(commentCounter, detailAST);
      System.out.println("Number of comments = " + commentCounterResults[0]);
      System.out.println("Number of comment lines = " + commentCounterResults[1]);
      
      detailAST.setType(TokenTypes.ABSTRACT);
      int[] expressionCounterResults = driver.runExpressionCounter(expressionCounter, detailAST);
      System.out.println("Number of expressions = " + expressionCounterResults[0]);
      System.out.println("Number of operators = " + expressionCounterResults[1]);
      System.out.println("Number of operands = " + expressionCounterResults[2]);
      System.out.println("Number of unique operators = " + expressionCounterResults[3]);
      System.out.println("Number of unique operands = " + expressionCounterResults[4]);
      
      double[] halsteadMetricsResults = driver.runHalsteadMetrics(halsteadMetrics, detailAST);
      System.out.println("Halstead length = " + halsteadMetricsResults[0]);
      System.out.println("Halstead vocab = " + halsteadMetricsResults[1]);
      System.out.println("Halstead volume = " + halsteadMetricsResults[2]);
      System.out.println("Halstead difficulty = " + halsteadMetricsResults[3]);
      System.out.println("Halstead effort = " + halsteadMetricsResults[4]);
      
      detailAST.setType(TokenTypes.LITERAL_FOR);
      int numLooping = driver.runLoopingStatementCounter(loopingStatementCounter, detailAST);
      System.out.println("Number of looping statements = " + numLooping);
      
      detailAST.setType(TokenTypes.ABSTRACT);
      double mIndex = driver.runMaintainabilityIndex(maintainabilityIndex, detailAST);
      System.out.println("Maintainability index = " + mIndex);
      
      detailAST.setType(TokenTypes.METHOD_CALL);
      DetailAST child = new DetailAST();
      child.setType(TokenTypes.DOT);
      detailAST.setFirstChild(child);
      int[] methodCounterResults = driver.runMethodCounter(methodCounter, detailAST);
      System.out.println("Number of external references = " + methodCounterResults[0]);
      System.out.println("Number of local references = " + methodCounterResults[1]);
      
      detailAST.setType(TokenTypes.VARIABLE_DEF);
      int numVariables = driver.runVariableCounter(variableCounter, detailAST);
      System.out.println("Number of variables = " + numVariables);
    }
    
    public int runCastCounter(CastCounter castCounter, DetailAST detailAST) {
      castCounter.beginTree(detailAST);
      castCounter.visitToken(detailAST);
      return castCounter.getNumCasts();
    }
    
    public int[] runCommentCounter(CommentCounter commentCounter, DetailAST detailAST) {
      commentCounter.beginTree(detailAST);
      commentCounter.visitToken(detailAST);
      int[] results = new int[2];
      results[0] = commentCounter.getNumComments();
      results[1] = commentCounter.getNumLines();
      return results;
    }
    
    public int[] runExpressionCounter(ExpressionCounter expressionCounter, DetailAST detailAST) {
      expressionCounter.beginTree(detailAST);
      expressionCounter.visitToken(detailAST);
      int[] results = new int[6];
      results[0] = expressionCounter.getNumExpressions();
      results[1] = expressionCounter.getNumOperands();
      results[2] = expressionCounter.getNumOperators();
      results[3] = expressionCounter.getNumUniqueOperands();
      results[4] = expressionCounter.getNumUniqueOperators();
      return results;
    }
    
    public double[] runHalsteadMetrics(HalsteadMetrics halsteadMetrics, DetailAST detailAST) {
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
    
    public int runLoopingStatementCounter(LoopingStatementCounter loopingStatementCounter, DetailAST detailAST) {
      loopingStatementCounter.beginTree(detailAST);
      loopingStatementCounter.visitToken(detailAST);
      return loopingStatementCounter.getNumLoopingStatements();
    }
    
    public double runMaintainabilityIndex(MaintainabilityIndex maintainabilityIndex, DetailAST detailAST) {
      maintainabilityIndex.beginTree(detailAST);
      maintainabilityIndex.visitToken(detailAST);
      return maintainabilityIndex.getMaintainabilityIndex();
    }
    
    public int[] runMethodCounter(MethodCounter methodCounter, DetailAST detailAST) {
      methodCounter.beginTree(detailAST);
      methodCounter.visitToken(detailAST);
      int[] results = new int[3];
      results[0] = methodCounter.getNumExternalReferences();
      results[1] = methodCounter.getNumLocalReferences();
      return results;
    }
    
    public int runVariableCounter(VariableCounter variableCounter, DetailAST detailAST) {
      variableCounter.beginTree(detailAST);
      variableCounter.visitToken(detailAST);
      return variableCounter.getNumVariables();
    }
}
