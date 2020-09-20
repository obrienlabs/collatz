package cloud.obrienlabs.collatz.db;

import java.math.BigInteger;

public interface GraphNode {

	BigInteger getValue();
	void setValue();
	Long getPath();
	void setPath(Long path);
	BigInteger getMax();
	void setMax(BigInteger max);
	GraphNode next();
}
