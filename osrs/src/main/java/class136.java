import java.util.concurrent.Callable;
import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("fp")
class class136 implements Callable {
	@ObfuscatedName("at")
	static int field1286;
	@ObfuscatedName("ma")
	@ObfuscatedSignature(
		descriptor = "Ldf;"
	)
	@Export("localPlayer")
	static Player localPlayer;
	// $FF: synthetic field
	@ObfuscatedSignature(
		descriptor = "Lfz;"
	)
	@Export("this$0")
	@ObfuscatedName("this$0")
	final class137 this$0;
	// $FF: synthetic field
	@Export("val$workStart")
	@ObfuscatedName("val$workStart")
	final int val$workStart;
	// $FF: synthetic field
	@Export("val$workEnd")
	@ObfuscatedName("val$workEnd")
	final int val$workEnd;
	// $FF: synthetic field
	@ObfuscatedSignature(
		descriptor = "[Lep;"
	)
	@Export("val$curveLoadJobs")
	@ObfuscatedName("val$curveLoadJobs")
	final class129[] val$curveLoadJobs;

	@ObfuscatedSignature(
		descriptor = "(Lfz;II[Lep;)V"
	)
	class136(class137 var1, int var2, int var3, class129[] var4) {
		this.this$0 = var1;
		this.val$workStart = var2;
		this.val$workEnd = var3;
		this.val$curveLoadJobs = var4;
	}

	@Export("call")
	@ObfuscatedName("call")
	public Object call() {
		for (int var1 = this.val$workStart; var1 < this.val$workEnd; ++var1) {
			this.val$curveLoadJobs[var1].call();
		}

		return null;
	}
}
