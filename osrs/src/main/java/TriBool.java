import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("qj")
@Implements("TriBool")
public class TriBool {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Lqj;"
	)
	public static final TriBool TriBool_unknown;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Lqj;"
	)
	public static final TriBool TriBool_true;
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Lqj;"
	)
	public static final TriBool TriBool_false;

	static {
		TriBool_unknown = new TriBool();
		TriBool_true = new TriBool();
		TriBool_false = new TriBool();
	}

	TriBool() {
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(II)I",
		garbageValue = "2136311618"
	)
	public static int method2225(int var0) {
		return class465.field3896[var0 & 16383];
	}
}
