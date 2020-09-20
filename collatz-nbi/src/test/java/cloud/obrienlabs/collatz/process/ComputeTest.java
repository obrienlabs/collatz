package cloud.obrienlabs.collatz.process;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeTest {
	
	@Test
	public void testSequence27() {
		Compute compute = new ComputeImpl();
		List<BigInteger> sequence = compute.iterate(BigInteger.valueOf(27));
		Assertions.assertNotNull(sequence);
		BigInteger expectedMax = BigInteger.valueOf(9232);
		// Get the highest sequence using the max function on an optional
		BigInteger actualMax = sequence.stream()
				.max(Comparator.comparing(x -> x))
				.get();
		Assertions.assertTrue(actualMax.equals(expectedMax));
		
		
	}

}
