import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("es")
@Implements("UserComparator8")
public class UserComparator8 extends AbstractUserComparator {
	@ObfuscatedName("aw")
	final boolean reversed;

	public UserComparator8(boolean var1) {
		this.reversed = var1;
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(Lqa;Lqa;I)I",
		garbageValue = "315336460"
	)
	int compareBuddy(Buddy var1, Buddy var2) {
		if (Client.worldId == var1.world) {
			if (var2.world != Client.worldId) {
				return this.reversed ? -1 : 1;
			}
		} else if (var2.world == Client.worldId) {
			return this.reversed ? 1 : -1;
		}

		return this.compareUser(var1, var2);
	}

	@ObfuscatedName("compare")
	public int compare(Object var1, Object var2) {
		return this.compareBuddy((Buddy)var1, (Buddy)var2);
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(Ldi;III)V",
		garbageValue = "-574325390"
	)
	static void runScript(ScriptEvent var0, int var1, int var2) {
		Object[] var3 = var0.args;
		Script var4;
		if (class345.isWorldMapEvent(var0.type)) {
			ScriptFrame.worldMapEvent = (WorldMapEvent)var3[0];
			WorldMapElement var5 = SequenceDefinition.WorldMapElement_get(ScriptFrame.worldMapEvent.mapElement);
			var4 = GameEngine.getWorldMapScript(var0.type, var5.objectId, var5.category);
		} else {
			int var6 = (Integer)var3[0];
			var4 = Interpreter.getScript(var6);
		}

		if (var4 != null) {
			class18.runScriptLogic(var0, var4, var1, var2);
		}

	}

	@ObfuscatedName("nt")
	@ObfuscatedSignature(
		descriptor = "(Lmt;B)I",
		garbageValue = "-68"
	)
	static int getWidgetFlags(Widget var0) {
		IntegerNode var1 = (IntegerNode)Client.widgetFlags.get((long)var0.childIndex + ((long)var0.id << 32));
		return var1 != null ? var1.integer : var0.flags;
	}
}
