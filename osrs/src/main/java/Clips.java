import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("jq")
public class Clips {
	@ObfuscatedName("aw")
	boolean field2241;
	@ObfuscatedName("ay")
	public boolean field2252;
	@ObfuscatedName("ar")
	int field2248;
	@ObfuscatedName("aj")
	public int field2253;
	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "Ljy;"
	)
	public TextureLoader Rasterizer3D_textureLoader;
	@ObfuscatedName("ah")
	int field2246;
	@ObfuscatedName("ax")
	int field2250;
	@ObfuscatedName("aa")
	int field2242;
	@ObfuscatedName("au")
	int field2249;
	@ObfuscatedName("ae")
	int clipNegativeMidX;
	@ObfuscatedName("ab")
	int field2243;
	@ObfuscatedName("ad")
	int clipNegativeMidY;
	@ObfuscatedName("ao")
	int field2247;
	@ObfuscatedName("ac")
	int[] Rasterizer3D_rowOffsets;

	Clips() {
		this.field2241 = false;
		this.field2252 = true;
		this.field2248 = 0;
		this.field2253 = 512;
		this.Rasterizer3D_rowOffsets = new int[1024];
	}

	@ObfuscatedName("aw")
	void method1333() {
		this.field2246 = this.field2242 / 2;
		this.field2250 = this.field2249 / 2;
		this.clipNegativeMidX = -this.field2246;
		this.field2243 = this.field2242 - this.field2246;
		this.clipNegativeMidY = -this.field2250;
		this.field2247 = this.field2249 - this.field2250;
	}

	@ObfuscatedName("ay")
	void method1334(int var1, int var2, int var3, int var4) {
		this.field2246 = var1 - var2;
		this.field2250 = var3 - var4;
		this.clipNegativeMidX = -this.field2246;
		this.field2243 = this.field2242 - this.field2246;
		this.clipNegativeMidY = -this.field2250;
		this.field2247 = this.field2249 - this.field2250;
	}

	@ObfuscatedName("ar")
	void method1335(int var1, int var2, int var3) {
		this.field2241 = var1 < 0 || var1 > this.field2242 || var2 < 0 || var2 > this.field2242 || var3 < 0 || var3 > this.field2242;
	}
}
