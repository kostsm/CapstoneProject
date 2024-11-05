package capstoneProject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.*;

public class MultipleExceptionsTest {

	@Test
	public void testExceptionsCount() {
		MultipleExceptions me = new MultipleExceptions();
		me.addException(new IOException("Reading error"));
		me.addException(new Exception("Something goes wtong"));
		
		assertEquals(2, me.getExceptions().size());
	}
	
	@Test
	public void testExceptionThrowing() throws IOException {
		Battery mainBattery = new Battery("mainBattery", 1000);
		EnergySource es = new EnergySource("Test", "Wind", 500,mainBattery);
		es.logs = null;
		
		Exception e = assertThrows(MultipleExceptions.class, () -> {
			es.dataExchange();		
		});
	}
	
	@Test
	public void testDefaultMessage() throws IOException {
		MultipleExceptions me = new MultipleExceptions();
		assertEquals("Multiple exceptions happened", me.getMessage());
	}
	
	
}
