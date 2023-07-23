import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("cl")
@Implements("GraphicsObject")
public class GraphicsObject extends Renderable {
	@ObfuscatedName("aw")
	int id;
	@ObfuscatedName("ay")
	int cycleStart;
	@ObfuscatedName("ar")
	int plane;
	@ObfuscatedName("am")
	int x;
	@ObfuscatedName("as")
	int y;
	@ObfuscatedName("aj")
	int z;
	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "Lig;"
	)
	SequenceDefinition sequenceDefinition;
	@ObfuscatedName("az")
	int frame;
	@ObfuscatedName("av")
	int frameCycle;
	@ObfuscatedName("ap")
	boolean isFinished;

	GraphicsObject(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		this.frame = 0;
		this.frameCycle = 0;
		this.isFinished = false;
		this.id = var1;
		this.plane = var2;
		this.x = var3;
		this.y = var4;
		this.z = var5;
		this.cycleStart = var7 + var6;
		int var8 = NPCComposition.SpotAnimationDefinition_get(this.id).sequence;
		if (var8 != -1) {
			this.isFinished = false;
			this.sequenceDefinition = class135.SequenceDefinition_get(var8);
		} else {
			this.isFinished = true;
		}

	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(IB)V",
		garbageValue = "-1"
	)
	final void advance(int var1) {
		if (!this.isFinished) {
			this.frameCycle += var1;
			if (!this.sequenceDefinition.isCachedModelIdSet()) {
				while (this.frameCycle > this.sequenceDefinition.frameLengths[this.frame]) {
					this.frameCycle -= this.sequenceDefinition.frameLengths[this.frame];
					++this.frame;
					if (this.frame >= this.sequenceDefinition.frameIds.length) {
						this.isFinished = true;
						break;
					}
				}
			} else {
				this.frame += var1;
				if (this.frame >= this.sequenceDefinition.method1067()) {
					this.isFinished = true;
				}
			}

		}
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)Ljo;",
		garbageValue = "-983173466"
	)
	protected final Model getModel() {
		SpotAnimationDefinition var1 = NPCComposition.SpotAnimationDefinition_get(this.id);
		Model var2;
		if (!this.isFinished) {
			var2 = var1.getModel(this.frame);
		} else {
			var2 = var1.getModel(-1);
		}

		return var2 == null ? null : var2;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-30148371"
	)
	public static boolean method430() {
		ReflectionCheck var0 = (ReflectionCheck)class36.reflectionChecks.last();
		return var0 != null;
	}

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(Lmt;II)V",
		garbageValue = "762775056"
	)
	static final void Widget_setKeyIgnoreHeld(Widget var0, int var1) {
		if (var0.field2960 == null) {
			throw new RuntimeException();
		} else {
			if (var0.field3037 == null) {
				var0.field3037 = new int[var0.field2960.length];
			}

			var0.field3037[var1] = Integer.MAX_VALUE;
		}
	}

	public GraphicsObject() {
	}
}
