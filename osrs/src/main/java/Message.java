import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("cx")
@Implements("Message")
public class Message extends DualNode {
	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "Luu;"
	)
	static IndexedSprite field348;
	@ObfuscatedName("dw")
	static boolean field347;
	@ObfuscatedName("aw")
	int count;
	@ObfuscatedName("ay")
	int cycle;
	@ObfuscatedName("ar")
	int type;
	@ObfuscatedName("am")
	String sender;
	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "Lui;"
	)
	Username senderUsername;
	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "Lqj;"
	)
	TriBool isFromFriend0;
	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "Lqj;"
	)
	TriBool isFromIgnored0;
	@ObfuscatedName("az")
	String prefix;
	@ObfuscatedName("av")
	String text;

	Message(int var1, String var2, String var3, String var4) {
		this.isFromFriend0 = TriBool.TriBool_unknown;
		this.isFromIgnored0 = TriBool.TriBool_unknown;
		this.set(var1, var2, var3, var4);
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V",
		garbageValue = "936252808"
	)
	void set(int var1, String var2, String var3, String var4) {
		this.count = class425.method2153();
		this.cycle = Client.cycle;
		this.type = var1;
		this.sender = var2;
		this.fillSenderUsername();
		this.prefix = var3;
		this.text = var4;
		this.clearIsFromFriend();
		this.clearIsFromIgnored();
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "53"
	)
	void clearIsFromFriend() {
		this.isFromFriend0 = TriBool.TriBool_unknown;
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1790809365"
	)
	final boolean isFromFriend() {
		if (this.isFromFriend0 == TriBool.TriBool_unknown) {
			this.fillIsFromFriend();
		}

		return this.isFromFriend0 == TriBool.TriBool_true;
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1483847830"
	)
	void fillIsFromFriend() {
		this.isFromFriend0 = class177.friendSystem.friendsList.contains(this.senderUsername) ? TriBool.TriBool_true : TriBool.TriBool_false;
	}

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-45"
	)
	void clearIsFromIgnored() {
		this.isFromIgnored0 = TriBool.TriBool_unknown;
	}

	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-1800868921"
	)
	final boolean isFromIgnored() {
		if (this.isFromIgnored0 == TriBool.TriBool_unknown) {
			this.fillIsFromIgnored();
		}

		return this.isFromIgnored0 == TriBool.TriBool_true;
	}

	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-106"
	)
	void fillIsFromIgnored() {
		this.isFromIgnored0 = class177.friendSystem.ignoreList.contains(this.senderUsername) ? TriBool.TriBool_true : TriBool.TriBool_false;
	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1553597211"
	)
	final void fillSenderUsername() {
		if (this.sender != null) {
			this.senderUsername = new Username(ClanMate.method2185(this.sender), class457.loginType);
		} else {
			this.senderUsername = null;
		}

	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(IB)I",
		garbageValue = "18"
	)
	public static int method337(int var0) {
		return var0 >>> 4 & class509.field4109;
	}

	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1851914192"
	)
	public static void method347() {
		DbTableType.DBTableType_cache.clear();
	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(Lty;J)V"
	)
	static void method345(Buffer var0, long var1) {
		var1 /= 10L;
		if (var1 < 0L) {
			var1 = 0L;
		} else if (var1 > 65535L) {
			var1 = 65535L;
		}

		var0.writeShort((int)var1);
	}
}
