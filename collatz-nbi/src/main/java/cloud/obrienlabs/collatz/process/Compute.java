package cloud.obrienlabs.collatz.process;

import java.math.BigInteger;
import java.util.List;

public interface Compute {
	static BigInteger TWO = BigInteger.valueOf(2);

	List<BigInteger> iterate(BigInteger start);
}
