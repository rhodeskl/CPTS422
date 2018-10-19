package net.sf.eclipsecs.sample;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

import net.sf.eclipsecs.sample.checks.HalsteadMetrics;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExpressionCounter.class)
public class HalsteadMetricsTests{

	private ExpressionCounter mockedCounter;
	
	
	@Before
	public void Setup()
	{
		mockedCounter = PowerMockito.mock(ExpressionCounter);
	} 
	
	@Test
	public int testGetHalsteadLength() {
		//should return expected length (0)
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadLength(), 0);
	}
	
	@Test
	public int testSetHalsteadLength()
	{
	  //hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();
		//should set getNumOperator to 1 and getNumOperands to 2, so should set Length to 3
		Mockito.when(mockedCounter.getNumOperators()).thenReturn(1);
		Mockito.when(mockedCounter.getNumOperands()).thenReturn(2);
		
		HalsteadMetrics hMetrics = new HalsteadMetrics();
	  	hMetrics.setExpressionCounter(mockedCounter);
	  	assertEquals(hMetrics.getHalsteadLength(), 0);
	  
	  	hMetrics.setHalsteadLength();
	  
	  	assertEquals(hMetrics.getHalsteadLength(), 3);
	}
	
	@Test
	public int testGetHalsteadVocab()
	{
		//should return expected vocab (0)
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadVocab(), 0);
	}
	
	@Test
	public void testSetHalsteadVocab() {
	  //hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
		//should set GetNumUniqueOperators to 1 and GetNumUniqueOperands to 2, sets vocab to 3
		Mockito.when(mockedCounter.GetNumUniqueOperators()).thenReturn(1);
		Mockito.when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);

		HalsteadMetrics hMetrics = new HalsteadMetrics();
		hMetrics.setExpressionCounter(mockedCounter);
		  
		assertEquals(hMetrics.getHalsteadVocab(), 0);
	  
		hMetrics.setHalsteadVocab();
	  
		assertEquals(hMetrics.getHalsteadVocab(), 3);
	}
	
	@Test
	public double testGetHalsteadVolume() {
		//should return expected volume (0)
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadVolume(), 0);
	}
	
	@Test
	public void testSetHalsteadVolume() {
		//hVolume = hLength * (Math.log(hVocab)/Math.log(2));
		//sets getNumOperators to 1 and getNumOperands to 2
		//sets GetNumUniqueOperators to 1 and GetNumUniqueOperands to 31 
		//so volume should be set to 5
		Mockito.when(mockedCounter.getNumOperators()).thenReturn(1);
		Mockito.when(mockedCounter.getNumOperands()).thenReturn(2);
		Mockito.when(mockedCounter.GetNumUniqueOperators()).thenReturn(1);
		Mockito.when(mockedCounter.GetNumUniqueOperands()).thenReturn(31);

		HalsteadMetrics hMetrics = new HalsteadMetrics();
		hMetrics.setExpressionCounter(mockedCounter);
		  
		assertEquals(hMetrics.getHalsteadVolume(), 0);
		hMetrics.setHalsteadLength();
		hMetrics.setHalsteadVocab();
	  
		hMetrics.setHalsteadVolume();
	  
		assertEquals(hMetrics.getHalsteadVolume(), 5);
	}
	
	@Test
	public double testGetHalsteadDifficulty() {
		//should set expected difficulty (0)
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
	}
	
	@Test
	public void testSetHalsteadDifficulty() {
		//hDifficulty = ((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())/expression_counter.getNumUniqueOperands();
		//sets getNumOperands to 2, sets GetNumUniqueOperators to 2, GetNumUniqueOperands to 2
		//so difficulty should be set to 1
		Mockito.when(mockedCounter.getNumOperands()).thenReturn(2);
		Mockito.when(mockedCounter.GetNumUniqueOperators()).thenReturn(2);
		Mockito.when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);
		
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		hMetrics.setExpressionCounter(mockedCounter);
		  
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
	  
		hMetrics.setHalsteadDifficulty();
	  
		assertEquals(hMetrics.getHalsteadDifficulty(), 1);
	}
	
	@Test
	public double testGetHalsteadEffort() {
		//should return expected effort (0)
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadEffort(), 0);
	}
	
	@Test
	public void testSetHalsteadEffort() {
		//hEffort = hDifficulty * hVolume;
		//sets getNumOperands to 2, sets GetNumUniqueOperators to 2, GetNumUniqueOperands to 2
		//so sets effort to 1
		Mockito.when(mockedCounter.getNumOperands()).thenReturn(2);
		Mockito.when(mockedCounter.GetNumUniqueOperators()).thenReturn(2);
		Mockito.when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);

		HalsteadMetrics hMetrics = new HalsteadMetrics();
		hMetrics.setExpressionCounter(mockedCounter);
		  
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
		
		hMetrics.setHalsteadVolume();
		hMetrics.setHalsteadDifficulty();
		hMetrics.setHalsteadEffort();
		
		assertEquals(hMetrics.getHalsteadEffort(), 1);
	}
	
}