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

	private IExpressionCounter mockedCounter;
	
	
	@Before
	public void Setup()
	{
		mockedCounter = PowerMockito.mock(IExpressionCounter);
	} 
	
	@Test
	public int getHalsteadLength_NewObject_ReturnsExpectedLength() {
	  HalsteadMetrics hMetrics = new HalsteadMetrics();
	  assertEquals(hMetrics.getHalsteadLength(), 0);
	}
	
	@Test
	public int setHalsteadLength_ValidValue_SetsExpectedValue()
	{
	  //hLength = expression_counter.getNumOperators() + expression_counter.getNumOperands();

		when(mockedCounter.getNumOperators()).thenReturn(1);
		when(mockedCounter.getNumOperands()).thenReturn(2);
		
	  HalsteadMetrics hMetrics = new HalsteadMetrics(mockedCounter);
	  assertEquals(hMetrics.getHalsteadLength(), 0);
	  
	  hMetrics.setHalsteadLength();
	  
	  assertEquals(hMetrics.getHalsteadLength(), 3);
	}
	
	@Test
	public int getHalsteadVocab_DefaultConstructor_ReturnsExpectedVocab()
	{
	  HalsteadMetrics hMetrics = new HalsteadMetrics();
	  assertEquals(hMetrics.getHalsteadVocab(), 0);
	}
	
	@Test
	public void setHalsteadVocab_ValidValue_SetsExpectedValue() {
	  //hVocab = expression_counter.getNumUniqueOperators() + expression_counter.getNumUniqueOperands();
		when(mockedCounter.GetNumUniqueOperators()).thenReturn(1);
		when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);
		
	  HalsteadMetrics hMetrics = new HalsteadMetrics(mockedCounter);
	  assertEquals(hMetrics.getHalsteadVocab(), 0);
	  
	  hMetrics.setHalsteadVocab();
	  
	  assertEquals(hMetrics.getHalsteadVocab(), 3);
	}
	
	@Test
	public double getHalsteadVolume_NewObject_ReturnsExpectedVolume() {
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadVolume(), 0);
	}
	
	@Test
	public void setHalsteadVolume_ValidValue_SetsExpectedValue() {
		//hVolume = hLength * (Math.log(hVocab)/Math.log(2));

		when(mockedCounter.getNumOperators()).thenReturn(1);
		when(mockedCounter.getNumOperands()).thenReturn(2);
		when(mockedCounter.GetNumUniqueOperators()).thenReturn(1);
		when(mockedCounter.GetNumUniqueOperands()).thenReturn(31);
		
		HalsteadMetrics hMetrics = new HalsteadMetrics(mockedCounter);
		assertEquals(hMetrics.getHalsteadVolume(), 0);
		hMetrics.setHalsteadLength();
		hMetrics.setHalsteadVocab();
	  
		hMetrics.setHalsteadVolume();
	  
		assertEquals(hMetrics.getHalsteadVolume(), 5);
	}
	
	@Test
	public double getHalsteadDifficulty_NewObject_ReturnsExpectedDifficulty() {
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
	}
	
	@Test
	public void setHalsteadDifficulty_ValidValue_SetsExpectedValue() {
		//hDifficulty = ((expression_counter.getNumUniqueOperators()/2) * expression_counter.getNumOperands())/expression_counter.getNumUniqueOperands();
		
		when(mockedCounter.getNumOperands()).thenReturn(2);
		when(mockedCounter.GetNumUniqueOperators()).thenReturn(2);
		when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);
		
		
		HalsteadMetrics hMetrics = new HalsteadMetrics(mockedCounter);
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
	  
		hMetrics.setHalsteadDifficulty();
	  
		assertEquals(hMetrics.getHalsteadDifficulty(), 1);
	}
	
	@Test
	public double getHalsteadEffort_NewObject_ReturnsExpectedEffort() {
		HalsteadMetrics hMetrics = new HalsteadMetrics();
		assertEquals(hMetrics.getHalsteadEffort(), 0);
	}
	
	@Test
	public void setHalsteadEffort_ValidValue_SetsExpectedValue() {
		//hEffort = hDifficulty * hVolume;
		when(mockedCounter.getNumOperands()).thenReturn(2);
		when(mockedCounter.GetNumUniqueOperators()).thenReturn(2);
		when(mockedCounter.GetNumUniqueOperands()).thenReturn(2);
		
		
		HalsteadMetrics hMetrics = new HalsteadMetrics(mockedCounter);
		assertEquals(hMetrics.getHalsteadDifficulty(), 0);
		
		hMetrics.setHalsteadVolume();
		hMetrics.setHalsteadDifficulty();
		hMetrics.setHalsteadEffort();
		
		assertEquals(hMetrics.getHalsteadEffort(), 1);
	}
	
}