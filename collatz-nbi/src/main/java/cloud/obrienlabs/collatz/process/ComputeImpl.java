package cloud.obrienlabs.collatz.process;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import cloud.obrienlabs.collatz.db.Graph;
import cloud.obrienlabs.collatz.db.GraphImpl;

public class ComputeImpl implements Compute {
	private static Logger log = Logger.getLogger(ComputeImpl.class.getCanonicalName());
	private Graph graph = new GraphImpl();


	public void iterate(BigInteger start, BigInteger end) {
		BigInteger current = start;
		while(current.compareTo(end) < 0) {
			List<BigInteger> list = iterate(current);
			BigInteger max = list.parallelStream()
					.max(Comparator.comparing(x -> x))
					.get();
			int path = list.size();
			// check maxes
			if(graph.getMaxPath() < path) {
				graph.setMaxPath(path);
				log.info("path: " + path + " for " + current);
			}
			if(graph.getMaxValue().compareTo(max) < 0) {
				graph.setMaxValue(max);
				log.info("max: " + max + " for " + current);
			}
			current = current.add(TWO);
		}
	}
	
	@Override
	public List<BigInteger> iterate(BigInteger start) {
		List<BigInteger> list = new CopyOnWriteArrayList<>();
		BigInteger current = start;	
		list.add(start);
		// until current is 1 - iterate
		while(!current.equals(BigInteger.ONE)) {
			if(current.testBit(0)) {
				current = current.multiply(THREE).add(BigInteger.ONE);
			} else {
				current = current.divide(TWO);
			}
			//log.info(current.toString());
			list.add(current);
		}
		return list;
	}

	public static void main(String[] args) {
		ComputeImpl impl = new ComputeImpl();
		//impl.iterate(1024L, 256L);
		//impl.iterate(BigInteger.valueOf(27));
		impl.iterate(BigInteger.valueOf(27L), BigInteger.valueOf(1024L));
		

	}

}
