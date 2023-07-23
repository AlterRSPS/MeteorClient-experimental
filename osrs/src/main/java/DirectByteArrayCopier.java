import java.nio.ByteBuffer;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("np")
@Implements("DirectByteArrayCopier")
public class DirectByteArrayCopier extends AbstractByteArrayCopier {
	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "Luu;"
	)
	static IndexedSprite field3096;
	@ObfuscatedName("ix")
	static int field3095;
	@ObfuscatedName("aw")
	ByteBuffer directBuffer;

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(I)[B",
		garbageValue = "-2134030911"
	)
	byte[] get() {
		byte[] var1 = new byte[this.directBuffer.capacity()];
		this.directBuffer.position(0);
		this.directBuffer.get(var1);
		return var1;
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "([BI)V",
		garbageValue = "-379413774"
	)
	public void set(byte[] var1) {
		this.directBuffer = ByteBuffer.allocateDirect(var1.length);
		this.directBuffer.position(0);
		this.directBuffer.put(var1);
	}
}
