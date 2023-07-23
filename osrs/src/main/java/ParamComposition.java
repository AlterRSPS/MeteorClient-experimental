import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("hq")
@Implements("ParamComposition")
public class ParamComposition extends DualNode {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Lnd;"
	)
	public static AbstractArchive ParamDefinition_archive;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Lld;"
	)
	public static EvictingDualNodeHashTable ParamDefinition_cached;
	@ObfuscatedName("ar")
	char type;
	@ObfuscatedName("am")
	public int defaultInt;
	@ObfuscatedName("as")
	public String defaultStr;
	@ObfuscatedName("aj")
	boolean autoDisable;

	static {
		ParamDefinition_cached = new EvictingDualNodeHashTable(64);
	}

	ParamComposition() {
		this.autoDisable = true;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(S)V",
		garbageValue = "-17467"
	)
	void postDecode() {
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(Lty;I)V",
		garbageValue = "1931541536"
	)
	void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(Lty;II)V",
		garbageValue = "243375101"
	)
	void decodeNext(Buffer var1, int var2) {
		if (var2 == 1) {
			this.type = class17.method54(var1.readByte());
		} else if (var2 == 2) {
			this.defaultInt = var1.readInt();
		} else if (var2 == 4) {
			this.autoDisable = false;
		} else if (var2 == 5) {
			this.defaultStr = var1.readStringCp1252NullTerminated();
		}

	}

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-791513096"
	)
	public boolean isString() {
		return this.type == 's';
	}

	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "(FFFFLfm;I)V",
		garbageValue = "-1881059806"
	)
	static void method1001(float var0, float var1, float var2, float var3, class130 var4) {
		float var5 = var1 - var0;
		float var6 = var2 - var1;
		float var7 = var3 - var2;
		float var8 = var6 - var5;
		var4.field1240 = var7 - var6 - var8;
		var4.field1239 = var8 + var8 + var8;
		var4.field1238 = var5 + var5 + var5;
		var4.field1242 = var0;
	}
}
