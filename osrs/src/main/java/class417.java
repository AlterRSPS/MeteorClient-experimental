import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("qw")
public class class417 {
	@ObfuscatedName("jo")
	@ObfuscatedSignature(
		descriptor = "(S)V",
		garbageValue = "-8022"
	)
	static void method2121() {
		for (class212 var0 = (class212)Client.field625.last(); var0 != null; var0 = (class212)Client.field625.previous()) {
			var0.remove();
		}

	}
}
