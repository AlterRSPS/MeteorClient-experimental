import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("dt")
public enum class90 implements class372 {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field892(0, -1),
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field893(1, 2),
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field890(2, 3),
	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field889(3, 4),
	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field891(4, 5),
	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "Ldt;"
	)
	field888(5, 6);

	@ObfuscatedName("aq")
	static int field886;
	@ObfuscatedName("lu")
	static int field887;
	@ObfuscatedName("ag")
	final int field894;
	@ObfuscatedName("az")
	final int field895;

	class90(int var3, int var4) {
		this.field894 = var3;
		this.field895 = var4;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "1644350448"
	)
	public int rsOrdinal() {
		return this.field895;
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(II)Lhb;",
		garbageValue = "-1281408867"
	)
	public static ObjectComposition getObjectDefinition(int var0) {
		ObjectComposition var1 = (ObjectComposition)ObjectComposition.ObjectDefinition_cached.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = ObjectComposition.ObjectDefinition_archive.takeFile(6, var0);
			var1 = new ObjectComposition();
			var1.id = var0;
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			var1.postDecode();
			if (var1.isSolid) {
				var1.interactType = 0;
				var1.boolean1 = false;
			}

			ObjectComposition.ObjectDefinition_cached.put(var1, (long)var0);
			return var1;
		}
	}
}
