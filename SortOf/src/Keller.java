import sun.misc.Version;

/**
 * @author OmegaRogue
 * @version 1.0.0.0
 * @since 1.2.2019 12:40
 * LIFO List
 */
public interface Keller {
	public boolean isEmpty();
	public void push(int i);
	public Plate pull();
	public Plate poll();
}
