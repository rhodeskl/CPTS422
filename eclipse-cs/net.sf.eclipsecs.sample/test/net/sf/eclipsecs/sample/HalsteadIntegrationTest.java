package net.sf.eclipsecs.sample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.CastCounter;
import net.sf.eclipsecs.sample.checks.CommentCounter;
import net.sf.eclipsecs.sample.checks.Driver;
import net.sf.eclipsecs.sample.checks.ExpressionCounter;
import net.sf.eclipsecs.sample.checks.HalsteadMetrics;
import net.sf.eclipsecs.sample.checks.LoopingStatementCounter;
import net.sf.eclipsecs.sample.checks.MaintainabilityIndex;
import net.sf.eclipsecs.sample.checks.MethodCounter;
import net.sf.eclipsecs.sample.checks.VariableCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)

public class HalsteadIntegrationTest {
  private ExpressionCounter expressionCounter;
  private HalsteadMetrics halsteadMetrics;
  private DetailAST astMock;
  
  @Before
  public void setUp() {
    
    astMock = PowerMockito.mock(DetailAST.class);   
    halsteadMetrics = new HalsteadMetrics();
  }
  @Test
  /* stubs: None
   * real classes: ExpressionCounter
   */
  public void HalsteadInternalIntegration() {
    //REMOVE : public ExpressionCounter(int numexp,int numops,int numopers,int numuop,int numuopers)
    
    //hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();
    int expectedhlen = 0;
    halsteadMetrics.setHalsteadLength();
    assertEquals(expectedhlen,halsteadMetrics.getHalsteadLength());
    
    //hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
    int expectedhvoc = 0;
    halsteadMetrics.setHalsteadVocab();
    assertEquals(expectedhvoc,halsteadMetrics.getHalsteadVocab());
    
    //hDifficulty = ((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())
    //expression_counter.getNumUniqueOperands();
    
    double expectedhdif = 0.0;
    //halsteadMetrics.setHalsteadDifficulty();
    assertEquals(expectedhdif,halsteadMetrics.getHalsteadDifficulty());
    
    //alter hlength
    expectedhlen = 6;
    expectedhvoc = 12;
    expectedhdif = 4.0;
    
    halsteadMetrics.setExpressionCounter(new ExpressionCounter(0,2,4,8,4));
    
    halsteadMetrics.setHalsteadLength();
    assertEquals(expectedhlen,halsteadMetrics.getHalsteadLength());
    
    halsteadMetrics.setHalsteadVocab();
    assertEquals(expectedhvoc,halsteadMetrics.getHalsteadVocab()); 
    
    halsteadMetrics.setHalsteadDifficulty();
    assertEquals(expectedhdif,halsteadMetrics.getHalsteadDifficulty()); 
       
    
  }
}
