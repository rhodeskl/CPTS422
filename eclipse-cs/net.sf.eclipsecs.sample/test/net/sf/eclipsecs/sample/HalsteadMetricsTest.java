package net.sf.eclipsecs.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.HalsteadMetrics;
import net.sf.eclipsecs.sample.checks.ExpressionCounter;

import static junit.framework.TestCase.assertEquals;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DetailAST.class)
public class HalsteadMetricsTest{
  private DetailAST astMock;
  
  @Mock
  private ExpressionCounter mockExpression;
  @InjectMocks
	private HalsteadMetrics mockHalstead;
	
	
	@Before
	public void Setup()
	{
		DetailAST astMock = PowerMockito.mock(DetailAST.class);
	} 
	
	@Test
	public void testGetHalsteadLength() {
		//should return expected length (0)
		assertEquals(0, mockHalstead.getHalsteadLength());
	}
	
	@Test
	public void testSetHalsteadLength()
	{
	  //hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();
		//should set getNumOperator to 1 and getNumOperands to 2, so should set Length to 3
		Mockito.when(mockExpression.getNumOperators()).thenReturn(1);
		Mockito.when(mockExpression.getNumOperands()).thenReturn(2);
		
	  assertEquals(mockHalstead.getHalsteadLength(), 0);
	  
	  mockHalstead.setHalsteadLength();
	  
	  assertEquals(mockHalstead.getHalsteadLength(), 3);
	}
	
	@Test
	public void testGetHalsteadVocab()
	{
		//should return expected vocab (0)
		assertEquals(0, mockHalstead.getHalsteadVocab());
	}
	
	@Test
	public void testSetHalsteadVocab() {
	  //hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
		//should set GetNumUniqueOperators to 1 and GetNumUniqueOperands to 2, sets vocab to 3
		Mockito.when(mockExpression.getNumUniqueOperators()).thenReturn(1);
		Mockito.when(mockExpression.getNumUniqueOperands()).thenReturn(2);

		  
		assertEquals(0, mockHalstead.getHalsteadVocab());
	  
		mockHalstead.setHalsteadVocab();
	  
		assertEquals(3, mockHalstead.getHalsteadVocab());
	}
	
	@Test
	public void testGetHalsteadVolume() {
		//should return expected volume (0)
		assertEquals(0.0, mockHalstead.getHalsteadVolume());
	}
	
	@Test
	public void testSetHalsteadVolume() {
		//hVolume = hLength * (Math.log(hVocab)/Math.log(2));
		//sets getNumOperators to 1 and getNumOperands to 2
		//sets GetNumUniqueOperators to 1 and GetNumUniqueOperands to 31 
		//so volume should be set to 5
		Mockito.when(mockExpression.getNumOperators()).thenReturn(1);
		Mockito.when(mockExpression.getNumOperands()).thenReturn(2);
		Mockito.when(mockExpression.getNumUniqueOperators()).thenReturn(1);
		Mockito.when(mockExpression.getNumUniqueOperands()).thenReturn(31);
		  
		assertEquals(0.0, mockHalstead.getHalsteadVolume());
		mockHalstead.setHalsteadLength();
		mockHalstead.setHalsteadVocab();
	  
		mockHalstead.setHalsteadVolume();
	  
		assertEquals(15.0, mockHalstead.getHalsteadVolume());
	}
	
	@Test
	public void testGetHalsteadDifficulty() {
		//should set expected difficulty (0)
		assertEquals(0.0, mockHalstead.getHalsteadDifficulty());
	}

	@Test
	public void testSetHalsteadDifficulty() {
		//hDifficulty = ((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())/expression_counter.getNumUniqueOperands();
		//sets getNumOperands to 2, sets GetNumUniqueOperators to 2, GetNumUniqueOperands to 2
		//so difficulty should be set to 1
    Mockito.when(mockExpression.getNumUniqueOperators()).thenReturn(2);
		Mockito.when(mockExpression.getNumOperands()).thenReturn(2);
		Mockito.when(mockExpression.getNumUniqueOperands()).thenReturn(2);
		
		mockHalstead.setHalsteadDifficulty();		  
		assertEquals(1.0, mockHalstead.getHalsteadDifficulty());
	}

	@Test
	public void testGetHalsteadEffort() {
		//should return expected effort (0)
		assertEquals(0.0, mockHalstead.getHalsteadDifficulty());
		
	}
	
	@Test
	public void testSetHalsteadEffort() {
		//hEffort = hDifficulty * hVolume;
		//sets getNumOperands to 2, sets GetNumUniqueOperators to 2, GetNumUniqueOperands to 2
		//so sets effort to 1
    Mockito.when(mockExpression.getNumOperators()).thenReturn(2).thenReturn(2);
    Mockito.when(mockExpression.getNumOperands()).thenReturn(2).thenReturn(2);
    Mockito.when(mockExpression.getNumUniqueOperators()).thenReturn(2);
    Mockito.when(mockExpression.getNumUniqueOperands()).thenReturn(2);
		assertEquals(0.0, mockHalstead.getHalsteadDifficulty());

		mockHalstead.setHalsteadLength();
		mockHalstead.setHalsteadVocab();
		mockHalstead.setHalsteadVolume();
		mockHalstead.setHalsteadDifficulty();

    mockHalstead.setHalsteadEffort();
		assertEquals(8.0, mockHalstead.getHalsteadEffort());
	}
	
	@Test
	public void testBeginTree() {
    mockHalstead.beginTree(astMock);
	  assertEquals(0, mockHalstead.getHalsteadLength());
	  assertEquals(0, mockHalstead.getHalsteadVocab());
	  assertEquals(0.0, mockHalstead.getHalsteadVolume());
	  assertEquals(0.0, mockHalstead.getHalsteadDifficulty());
	  assertEquals(0.0, mockHalstead.getHalsteadEffort());
	  
	  Mockito.verify(mockExpression).beginTree(astMock);
  }
	
	@Test
	public void testVisitToken() {
    mockHalstead.visitToken(astMock);
    Mockito.verify(mockExpression).visitToken(astMock);
  }
}