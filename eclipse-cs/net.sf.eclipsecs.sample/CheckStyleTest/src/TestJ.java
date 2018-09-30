
public class TestJ {
	void m1() 
	{}
	void m2() 
	{}
	void m3() 
	{
		TestC t = new TestC();
		for(int i = 0; i < 5; i++)
		{
			m1();
			m2();
			m4(t.tm());
		}
	}
	void m4(int v) {};
	
	//Hello world.
	//hello me
}

final class TestC {
	int tm() {return 0;}
}
