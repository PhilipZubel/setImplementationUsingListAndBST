package assessedExercise;

public abstract class Set<Item> {
	public abstract boolean add(Item item);
	public abstract boolean remove(Item item);
	public abstract boolean isElement(Item item);
	public abstract boolean setEmpty();
	public abstract int setSize();

}
