/**
 * @author OmegaRogue
 * @version 1.0.0.0
 * @since 1.2.2019 12:40
 * LIFO List
 */
@SuppressWarnings("ALL")
interface Keller {
	@SuppressWarnings("unused")
	boolean isEmpty();

	@SuppressWarnings("unused")
	void push(int i);

	@SuppressWarnings("unused")
	Plate pull();

	@SuppressWarnings("unused")
	Plate poll();
}
