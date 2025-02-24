import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Comparator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("en")
@Implements("UserComparator4")
public class UserComparator4 implements Comparator {
	@ObfuscatedName("ag")
	public static int field1166;
	@ObfuscatedName("bh")
	@Export("garbageCollector")
	static GarbageCollectorMXBean garbageCollector;
	@ObfuscatedName("ji")
	@Export("regionMapArchiveIds")
	static int[] regionMapArchiveIds;
	@ObfuscatedName("aw")
	@Export("reversed")
	final boolean reversed;

	public UserComparator4(boolean var1) {
		this.reversed = var1;
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(Lqa;Lqa;B)I",
		garbageValue = "95"
	)
	@Export("compare_bridged")
	int compare_bridged(Buddy var1, Buddy var2) {
		return this.reversed ? var1.int2 - var2.int2 : var2.int2 - var1.int2;
	}

	@Export("compare")
	@ObfuscatedName("compare")
	public int compare(Object var1, Object var2) {
		return this.compare_bridged((Buddy)var1, (Buddy)var2);
	}

	@Export("equals")
	@ObfuscatedName("equals")
	public boolean equals(Object var1) {
		return super.equals(var1);
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(Ljava/util/ArrayList;IIIIZB)V",
		garbageValue = "-72"
	)
	public static void method663(ArrayList var0, int var1, int var2, int var3, int var4, boolean var5) {
		if (var5 || !class306.field2758.isEmpty() && !var0.isEmpty()) {
			class306.field2758.clear();
			class306.field2760.clear();
		}

		if (!var0.isEmpty()) {
			class144.method764(var0, var5);
			if (!class306.field2758.isEmpty()) {
				ArchiveLoader.method464(var1, var2, var3, var4);
				class306.field2760.add(new AddRequestTask((SongTask)null));
				class306.field2760.add(new LoadSongTask((SongTask)null, class306.musicPatchesArchive, class306.musicSamplesArchive, class306.soundEffectsArchive));
				if (!class306.musicSongs.isEmpty()) {
					ArrayList var6 = new ArrayList();
					var6.add(new DelayFadeTask(new class404(new FadeInTask((SongTask)null, 0, true, class306.field2751)), class306.field2754));
					ArrayList var7 = class162.method847();
					var6.add(new DelayFadeTask(new FadeOutTask(new class402((SongTask)null, var7), 0, false, class306.field2753), class306.musicPlayerStatus));
					class306.field2760.add(new ConcurrentMidiTask((SongTask)null, var6));
				} else {
					class306.field2760.add(new DelayFadeTask((SongTask)null, class306.field2754));
					class306.field2760.add(new class404((SongTask)null));
					class306.field2760.add(new FadeInTask((SongTask)null, 0, true, class306.field2751));
				}

			}
		}
	}
}
