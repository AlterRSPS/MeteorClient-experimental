import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("dw")
@Implements("AttackOption")
public enum AttackOption implements class372 {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Ldw;"
	)
	AttackOption_dependsOnCombatLevels(0),
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Ldw;"
	)
	AttackOption_alwaysRightClick(1),
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Ldw;"
	)
	field1089(2),
	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "Ldw;"
	)
	AttackOption_hidden(3),
	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "Ldw;"
	)
	field1090(4);

	@ObfuscatedName("aj")
	final int id;

	AttackOption(int var3) {
		this.id = var3;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "1644350448"
	)
	public int rsOrdinal() {
		return this.id;
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(IS)Lfj;",
		garbageValue = "-25003"
	)
	static class138 method610(int var0) {
		class138 var1 = (class138)ClientPreferences.findEnumerated(GZipDecompressor.method2679(), var0);
		if (var1 == null) {
			var1 = class138.field1300;
		}

		return var1;
	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "1"
	)
	static final boolean method609() {
		return ViewportMouse.ViewportMouse_isInViewport;
	}

	@ObfuscatedName("kv")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "-57"
	)
	static final boolean method611() {
		return Client.isMenuOpen;
	}
}
