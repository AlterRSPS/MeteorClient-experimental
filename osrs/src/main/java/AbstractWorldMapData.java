import java.util.LinkedList;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("km")
@Implements("AbstractWorldMapData")
public abstract class AbstractWorldMapData {
	@ObfuscatedName("as")
	int field2408;
	@ObfuscatedName("aj")
	int field2405;
	@ObfuscatedName("ag")
	int regionX;
	@ObfuscatedName("az")
	int regionY;
	@ObfuscatedName("av")
	int field2410;
	@ObfuscatedName("ap")
	int planes;
	@ObfuscatedName("aq")
	int groupId;
	@ObfuscatedName("at")
	int fileId;
	@ObfuscatedName("ah")
	short[][][] floorUnderlayIds;
	@ObfuscatedName("ax")
	short[][][] floorOverlayIds;
	@ObfuscatedName("aa")
	byte[][][] field2402;
	@ObfuscatedName("au")
	byte[][][] field2403;
	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "[[[[Lkk;"
	)
	WorldMapDecoration[][][][] decorations;
	@ObfuscatedName("ab")
	boolean field2400;
	@ObfuscatedName("ad")
	boolean field2401;

	AbstractWorldMapData() {
		this.groupId = -1;
		this.fileId = -1;
		new LinkedList();
		this.field2400 = false;
		this.field2401 = false;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(Lty;B)V",
		garbageValue = "81"
	)
	abstract void readGeography(Buffer var1);

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "-4"
	)
	boolean isFullyLoaded() {
		return this.field2400 && this.field2401;
	}

	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "(Lnd;I)V",
		garbageValue = "-1020767316"
	)
	void loadGeography(AbstractArchive var1) {
		if (!this.isFullyLoaded()) {
			byte[] var2 = var1.takeFile(this.groupId, this.fileId);
			if (var2 != null) {
				this.readGeography(new Buffer(var2));
				this.field2400 = true;
				this.field2401 = true;
			}

		}
	}

	@ObfuscatedName("an")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "0"
	)
	void reset() {
		this.floorUnderlayIds = null;
		this.floorOverlayIds = null;
		this.field2402 = null;
		this.field2403 = null;
		this.decorations = null;
		this.field2400 = false;
		this.field2401 = false;
	}

	@ObfuscatedName("af")
	@ObfuscatedSignature(
		descriptor = "(IILty;B)V",
		garbageValue = "77"
	)
	void readTile(int var1, int var2, Buffer var3) {
		int var4 = var3.readUnsignedByte();
		if (var4 != 0) {
			if ((var4 & 1) != 0) {
				this.method1490(var1, var2, var3, var4);
			} else {
				this.method1491(var1, var2, var3, var4);
			}

		}
	}

	@ObfuscatedName("ai")
	@ObfuscatedSignature(
		descriptor = "(IILty;IB)V",
		garbageValue = "12"
	)
	void method1490(int var1, int var2, Buffer var3, int var4) {
		boolean var5 = (var4 & 2) != 0;
		if (var5) {
			this.floorOverlayIds[0][var1][var2] = (short)var3.readUnsignedShort();
		}

		this.floorUnderlayIds[0][var1][var2] = (short)var3.readUnsignedShort();
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(IILty;IB)V",
		garbageValue = "-51"
	)
	void method1491(int var1, int var2, Buffer var3, int var4) {
		int var5 = ((var4 & 24) >> 3) + 1;
		boolean var6 = (var4 & 2) != 0;
		boolean var7 = (var4 & 4) != 0;
		this.floorUnderlayIds[0][var1][var2] = (short)var3.readUnsignedShort();
		int var8;
		int var9;
		int var11;
		if (var6) {
			var8 = var3.readUnsignedByte();

			for (var9 = 0; var9 < var8; ++var9) {
				int var10 = var3.readUnsignedShort();
				if (var10 != 0) {
					this.floorOverlayIds[var9][var1][var2] = (short)var10;
					var11 = var3.readUnsignedByte();
					this.field2402[var9][var1][var2] = (byte)(var11 >> 2);
					this.field2403[var9][var1][var2] = (byte)(var11 & 3);
				}
			}
		}

		if (var7) {
			for (var8 = 0; var8 < var5; ++var8) {
				var9 = var3.readUnsignedByte();
				if (var9 != 0) {
					WorldMapDecoration[] var14 = this.decorations[var8][var1][var2] = new WorldMapDecoration[var9];

					for (var11 = 0; var11 < var9; ++var11) {
						int var12 = var3.method2543();
						int var13 = var3.readUnsignedByte();
						var14[var11] = new WorldMapDecoration(var12, var13 >> 2, var13 & 3);
					}
				}
			}
		}

	}

	@ObfuscatedName("bd")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "1822909116"
	)
	int getRegionX() {
		return this.regionX;
	}

	@ObfuscatedName("bb")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "-903586613"
	)
	int getRegionY() {
		return this.regionY;
	}
}
