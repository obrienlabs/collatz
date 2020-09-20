package cloud.obrienlabs.collatz.process;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ComputeImpl implements Compute {


	
	@Override
	public List<BigInteger> iterate(BigInteger start) {
		List<BigInteger> list = new CopyOnWriteArrayList<>();
		list.add(start);
		// until current is 1 - iterate
		list.add(start.multiply(TWO));
		list.add(BigInteger.valueOf(9232));
		return list;
	}

	public static void main(String[] args) {
		

	}

}
