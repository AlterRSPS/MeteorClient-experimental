import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("uc")
public enum FillMode implements class372 {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Luc;"
	)
	SOLID(0, 0),
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Luc;"
	)
	field4185(1, 1),
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Luc;"
	)
	field4184(2, 2);

	@ObfuscatedName("am")
	public final int field4187;
	@ObfuscatedName("as")
	final int field4186;

	FillMode(int var3, int var4) {
		this.field4187 = var3;
		this.field4186 = var4;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "1644350448"
	)
	public int rsOrdinal() {
		return this.field4186;
	}
}
