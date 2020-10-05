package cloud.obrienlabs.collatz.process;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

import cloud.obrienlabs.collatz.db.Graph;
import cloud.obrienlabs.collatz.db.GraphImpl;


/**
 * 

-Djava.util.concurrent.ForkJoinPool.common.parallelism=128
*/

public class ComputeImpl implements Compute {
	private static Logger log = Logger.getLogger(ComputeImpl.class.getCanonicalName());
	private static Graph graph = new GraphImpl();
	private BigInteger counter;


	public ComputeImpl() {
		counter = BigInteger.ONE;
	}
	
	public void iterate(BigInteger start, BigInteger end) {
		BigInteger current = start;
		//while(current.compareTo(end) < 0) {
		Stream.iterate(start, i -> i.add(TWO))
			//.takeWhile(i -> i.compareTo(end) < 0);
			.limit(Integer.parseInt(end.toString()))
			.reduce(start, ComputeImpl::hailstoneList);

	}
	
	//@Override
	//public static List<BigInteger> hailstoneList(BigInteger current, BigInteger end) {
	public static BigInteger hailstoneList(BigInteger current, BigInteger end) {
		List<BigInteger> list = iterate(current);
		BigInteger max = list.stream()
				//.parallel()
				.max(Comparator.comparing(x -> x))
				.get();
		// check maxes
		if(graph.getMaxPath() < list.size()) {
			graph.setMaxPath(list.size());
			log.info("path: " + graph.getMaxPath() + " for " + current);
		}
		if(graph.getMaxValue().compareTo(max) < 0) {
			graph.setMaxValue(max);
			log.info("max: " + max + " for " + current);
		}
		return max;
	}
	
	//@Override
	public static BigInteger hailstone(BigInteger current) {
		if(current.testBit(0)) {
			return current.multiply(THREE).add(BigInteger.ONE);
		} else {
			return current.divide(TWO);
		}	
	}
	
	//@Override
	public static List<BigInteger> iterate(BigInteger start) {
		BigInteger current = start;	
		List<BigInteger> list = new CopyOnWriteArrayList<>();
		list.add(start);
		// until current is 1 - iterate
		while(!current.equals(BigInteger.ONE)) {
			current = hailstone(current);
			list.add(current);
		}
		return list;
	}

	public static void main(String[] args) {
		ComputeImpl impl = new ComputeImpl();
		//impl.iterate(BigInteger.valueOf(27));
		long start = System.currentTimeMillis();

		impl.iterate(BigInteger.valueOf(1 << 0), BigInteger.valueOf(1 << 23));
		log.info("duration: " + (System.currentTimeMillis() - start));		

	}

}
