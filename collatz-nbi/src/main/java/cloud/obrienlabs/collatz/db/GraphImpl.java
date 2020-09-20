package cloud.obrienlabs.collatz.db;

import java.math.BigInteger;

public class GraphImpl implements Graph {
	private int maxPath = 1;
	private BigInteger maxValue = BigInteger.ONE;
	

	public int getMaxPath() {
		return maxPath;
	}

	public void setMaxPath(int maxPath) {
		this.maxPath = maxPath;
	}

	public BigInteger getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigInteger maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public void putNext(GraphNode current, GraphNode next) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GraphNode getNext(GraphNode current) {
		// TODO Auto-generated method stub
		return null;
	}



}
