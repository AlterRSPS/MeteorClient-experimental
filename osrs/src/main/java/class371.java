import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("oe")
public class class371 {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	static final class371 field3587;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	static final class371 field3588;
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	static final class371 field3586;
	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "Loe;"
	)
	static final class371 field3585;

	static {
		field3587 = new class371();
		field3588 = new class371();
		field3586 = new class371();
		field3585 = new class371();
	}

	class371() {
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(II)Lhm;",
		garbageValue = "238508398"
	)
	@Export("getEnum")
	public static EnumComposition getEnum(int var0) {
		EnumComposition var1 = (EnumComposition)EnumComposition.EnumDefinition_cached.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = EnumComposition.EnumDefinition_archive.takeFile(8, var0);
			var1 = new EnumComposition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			EnumComposition.EnumDefinition_cached.put(var1, (long)var0);
			return var1;
		}
	}
}
