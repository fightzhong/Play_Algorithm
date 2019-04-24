package weightgraph;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>>{
	// 对于from, to来说, 只有在有向图的时候其意义才会不同
	private int from;			//	表示边的第一端
	private int to;			// 表示边的第二端
	private T weight;			// 边的权重, 用泛型表示, 因为可以是整型, 可以是浮点型  
	
	public Edge (int from, int to, T weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public void setWeight (T weight) {
		this.weight = weight;
	}
	
	public T getWeight () {
		return weight;
	}
	
	public int getToPeak () {
		return to;
	}
	
	public int getFromPeak () {
		return from;
	}
	
	@Override 
	public String toString () {
		return "{from: "+ from + ", to: "+ to + ", weight: " + weight +"}";
	}

	@Override
	public int compareTo(Edge<T> o) {
		if (o == null) {
			throw new IllegalArgumentException("Edge of o does not exist");
		}
		return weight.compareTo(o.weight);
	}
}
