package cloud.obrienlabs.collatz.db;

import java.math.BigInteger;

public interface Graph {
	void putNext(GraphNode current, GraphNode next);
	GraphNode getNext(GraphNode current);
	BigInteger getMaxValue();
	int getMaxPath();
	void setMaxValue(BigInteger max);
	void setMaxPath(int max);

}
