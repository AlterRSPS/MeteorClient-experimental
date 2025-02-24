import java.util.Collection;
import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("or")
@Implements("IterableNodeDeque")
public class IterableNodeDeque implements Iterable, Collection {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Lsb;"
	)
	@Export("sentinel")
	Node sentinel;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Lsb;"
	)
	Node field3627;

	public IterableNodeDeque() {
		this.sentinel = new Node();
		this.sentinel.previous = this.sentinel;
		this.sentinel.next = this.sentinel;
	}

	@ObfuscatedName("aw")
	@Export("rsClear")
	public void rsClear() {
		while (this.sentinel.previous != this.sentinel) {
			this.sentinel.previous.remove();
		}

	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(Lsb;)V"
	)
	@Export("addFirst")
	public void addFirst(Node var1) {
		if (var1.next != null) {
			var1.remove();
		}

		var1.next = this.sentinel.next;
		var1.previous = this.sentinel;
		var1.next.previous = var1;
		var1.previous.next = var1;
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(Lsb;)V"
	)
	@Export("addLast")
	public void addLast(Node var1) {
		if (var1.next != null) {
			var1.remove();
		}

		var1.next = this.sentinel;
		var1.previous = this.sentinel.previous;
		var1.next.previous = var1;
		var1.previous.next = var1;
	}

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "()Lsb;"
	)
	@Export("last")
	public Node last() {
		return this.method1968((Node)null);
	}

	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "(Lsb;)Lsb;"
	)
	Node method1968(Node var1) {
		Node var2;
		if (var1 == null) {
			var2 = this.sentinel.previous;
		} else {
			var2 = var1;
		}

		if (var2 == this.sentinel) {
			this.field3627 = null;
			return null;
		} else {
			this.field3627 = var2.previous;
			return var2;
		}
	}

	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "()Lsb;"
	)
	@Export("previous")
	public Node previous() {
		Node var1 = this.field3627;
		if (var1 == this.sentinel) {
			this.field3627 = null;
			return null;
		} else {
			this.field3627 = var1.previous;
			return var1;
		}
	}

	@ObfuscatedName("az")
	int method1970() {
		int var1 = 0;

		for (Node var2 = this.sentinel.previous; var2 != this.sentinel; var2 = var2.previous) {
			++var1;
		}

		return var1;
	}

	@ObfuscatedName("av")
	public boolean method1971() {
		return this.sentinel.previous == this.sentinel;
	}

	@ObfuscatedName("ap")
	@ObfuscatedSignature(
		descriptor = "()[Lsb;"
	)
	Node[] method1972() {
		Node[] var1 = new Node[this.method1970()];
		int var2 = 0;

		for (Node var3 = this.sentinel.previous; var3 != this.sentinel; var3 = var3.previous) {
			var1[var2++] = var3;
		}

		return var1;
	}

	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "(Lsb;)Z"
	)
	boolean method1973(Node var1) {
		this.addFirst(var1);
		return true;
	}

	@Export("iterator")
	@ObfuscatedName("iterator")
	public Iterator iterator() {
		return new IterableNodeDequeDescendingIterator(this);
	}

	@Export("size")
	@ObfuscatedName("size")
	public int size() {
		return this.method1970();
	}

	@Export("isEmpty")
	@ObfuscatedName("isEmpty")
	public boolean isEmpty() {
		return this.method1971();
	}

	public boolean contains(Object var1) {
		throw new RuntimeException();
	}

	@Export("toArray")
	@ObfuscatedName("toArray")
	public Object[] toArray() {
		return this.method1972();
	}

	@Export("toArray")
	@ObfuscatedName("toArray")
	public Object[] toArray(Object[] var1) {
		int var2 = 0;

		for (Node var3 = this.sentinel.previous; var3 != this.sentinel; var3 = var3.previous) {
			var1[var2++] = var3;
		}

		return var1;
	}

	public boolean remove(Object var1) {
		throw new RuntimeException();
	}

	public boolean containsAll(Collection var1) {
		throw new RuntimeException();
	}

	public boolean addAll(Collection var1) {
		throw new RuntimeException();
	}

	public boolean removeAll(Collection var1) {
		throw new RuntimeException();
	}

	public boolean retainAll(Collection var1) {
		throw new RuntimeException();
	}

	@Export("clear")
	@ObfuscatedName("clear")
	public void clear() {
		this.rsClear();
	}

	@Export("add")
	@ObfuscatedName("add")
	public boolean add(Object var1) {
		return this.method1973((Node)var1);
	}

	@Export("equals")
	@ObfuscatedName("equals")
	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	@Export("hashCode")
	@ObfuscatedName("hashCode")
	public int hashCode() {
		return super.hashCode();
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(Lsb;Lsb;)V"
	)
	@Export("IterableNodeDeque_addBefore")
	public static void IterableNodeDeque_addBefore(Node var0, Node var1) {
		if (var0.next != null) {
			var0.remove();
		}

		var0.next = var1;
		var0.previous = var1.previous;
		var0.next.previous = var0;
		var0.previous.next = var0;
	}
}
