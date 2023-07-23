import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("tk")
@Implements("DbTableType")
public class DbTableType extends DualNode {
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Lnd;"
	)
	static AbstractArchive field4098;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Lld;"
	)
	static EvictingDualNodeHashTable DBTableType_cache;
	@ObfuscatedName("kr")
	@ObfuscatedSignature(
		descriptor = "[Lud;"
	)
	static SpritePixels[] headIconPkSprites;
	@ObfuscatedName("ar")
	public int[][] types;
	@ObfuscatedName("am")
	public Object[][] defaultValues;

	static {
		DBTableType_cache = new EvictingDualNodeHashTable(64);
	}

	DbTableType() {
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(Lty;I)V",
		garbageValue = "871558389"
	)
	void method2480(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.method2481(var1, var2);
		}
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(Lty;IB)V",
		garbageValue = "-2"
	)
	void method2481(Buffer var1, int var2) {
		if (var2 == 1) {
			int var3 = var1.readUnsignedByte();
			if (this.types == null) {
				this.types = new int[var3][];
			}

			for (int var4 = var1.readUnsignedByte(); var4 != 255; var4 = var1.readUnsignedByte()) {
				int var5 = var4 & 127;
				boolean var6 = (var4 & 128) != 0;
				int[] var7 = new int[var1.readUnsignedByte()];

				for (int var8 = 0; var8 < var7.length; ++var8) {
					var7[var8] = var1.readUShortSmart();
				}

				this.types[var5] = var7;
				if (var6) {
					if (this.defaultValues == null) {
						this.defaultValues = new Object[this.types.length][];
					}

					this.defaultValues[var5] = class10.method30(var1, var7);
				}
			}
		}

	}

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-1574876599"
	)
	void method2482() {
	}

	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1773514467"
	)
	public static void method2483() {
		class492.SpriteBuffer_xOffsets = null;
		class134.SpriteBuffer_yOffsets = null;
		class172.SpriteBuffer_spriteWidths = null;
		class528.SpriteBuffer_spriteHeights = null;
		class528.SpriteBuffer_spritePalette = null;
		ArchiveDiskAction.SpriteBuffer_pixels = null;
	}
}
