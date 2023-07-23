import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ol")
@Implements("GrandExchangeEvent")
public class GrandExchangeEvent {
	@ObfuscatedName("aw")
	public final int world;
	@ObfuscatedName("ay")
	public final long age;
	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "Loc;"
	)
	public final GrandExchangeOffer grandExchangeOffer;
	@ObfuscatedName("am")
	String offerName;
	@ObfuscatedName("as")
	String previousOfferName;

	@ObfuscatedSignature(
		descriptor = "(Lty;BI)V"
	)
	GrandExchangeEvent(Buffer var1, byte var2, int var3) {
		this.offerName = var1.readStringCp1252NullTerminated();
		this.previousOfferName = var1.readStringCp1252NullTerminated();
		this.world = var1.readUnsignedShort();
		this.age = var1.readLong();
		int var4 = var1.readInt();
		int var5 = var1.readInt();
		this.grandExchangeOffer = new GrandExchangeOffer();
		this.grandExchangeOffer.method1924(2);
		this.grandExchangeOffer.method1925(var2);
		this.grandExchangeOffer.unitPrice = var4;
		this.grandExchangeOffer.totalQuantity = var5;
		this.grandExchangeOffer.currentQuantity = 0;
		this.grandExchangeOffer.currentPrice = 0;
		this.grandExchangeOffer.id = var3;
	}

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(I)Ljava/lang/String;",
		garbageValue = "1134198101"
	)
	public String getOfferName() {
		return this.offerName;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(I)Ljava/lang/String;",
		garbageValue = "1406199718"
	)
	public String getPreviousOfferName() {
		return this.previousOfferName;
	}
}
