package dev.hoot.api.packets;

import dev.hoot.api.game.Game;
import meteor.api.packets.ClientPackets;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.packets.ClientPacket;
import net.runelite.api.packets.PacketBufferNode;

import java.util.List;

public class NPCPackets
{
	public static void npcFirstOption(NPC npc, boolean ctrlDown)
	{
		queueNPCAction1Packet(npc.getIndex(), ctrlDown);
	}

	public static void npcSecondOption(NPC npc, boolean ctrlDown)
	{
		queueNPCAction2Packet(npc.getIndex(), ctrlDown);
	}

	public static void npcThirdOption(NPC npc, boolean ctrlDown)
	{
		queueNPCAction3Packet(npc.getIndex(), ctrlDown);
	}

	public static void npcFourthOption(NPC npc, boolean ctrlDown)
	{
		queueNPCAction4Packet(npc.getIndex(), ctrlDown);
	}

	public static void npcFifthOption(NPC npc, boolean ctrlDown)
	{
		queueNPCAction5Packet(npc.getIndex(), ctrlDown);
	}

	public static void npcAction(NPC npc, String action, boolean ctrlDown)
	{
		List<String> actions = npc.getActions();
		int index = actions.indexOf(action);
		switch (index)
		{
			case 0:
				npcFirstOption(npc, ctrlDown);
				break;
			case 1:
				npcSecondOption(npc, ctrlDown);
				break;
			case 2:
				npcThirdOption(npc, ctrlDown);
				break;
			case 3:
				npcFourthOption(npc, ctrlDown);
				break;
			case 4:
				npcFifthOption(npc, ctrlDown);
				break;
		}
	}

	public static void spellOnNpc(int widgetId, NPC npc, boolean b)
	{
		queueSpellOnNpcPacket(npc.getIndex(), widgetId, b);
	}

	public static void queueItemOnNpcPacket(int npcIndex, int itemWidgetId, int itemId, int itemSlot, boolean ctrlDown)
	{
		createItemOnNpcPacket(npcIndex, itemWidgetId, itemId, itemSlot, ctrlDown).send();
	}

	public static void queueSpellOnNpcPacket(int npcIndex, int spellWidgetId, boolean ctrlDown)
	{
		createSpellOnNpcPacket(npcIndex, spellWidgetId, ctrlDown).send();
	}

	public static void queueNPCAction1Packet(int npcIndex, boolean ctrlDown)
	{
		createNpcFirstActionPacket(npcIndex, ctrlDown).send();
	}

	public static void queueNPCAction2Packet(int npcIndex, boolean ctrlDown)
	{
		createNpcSecondActionPacket(npcIndex, ctrlDown).send();
	}

	public static void queueNPCAction3Packet(int npcIndex, boolean ctrlDown)
	{
		createNpcThirdActionPacket(npcIndex, ctrlDown).send();
	}

	public static void queueNPCAction4Packet(int npcIndex, boolean ctrlDown)
	{
		createNpcFourthActionPacket(npcIndex, ctrlDown).send();
	}

	public static void queueNPCAction5Packet(int npcIndex, boolean ctrlDown)
	{
		createNpcFifthActionPacket(npcIndex, ctrlDown).send();
	}

	public static PacketBufferNode createItemOnNpcPacket(int npcIndex, int itemWidgetId, int itemId, int itemSlot,
											  boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createItemOnNPCPacket("OPNPCU", npcIndex, itemWidgetId, itemId, itemSlot, ctrlDown);
	}

	public static PacketBufferNode createSpellOnNpcPacket(int npcIndex, int spellWidgetId, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createSpellOnNPCPacket("OPNPCT", npcIndex, spellWidgetId, ctrlDown);
	}

	public static PacketBufferNode createNpcFirstActionPacket(int npcIndex, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createNPCActionPacket("OPNPC1", npcIndex, ctrlDown);
	}

	public static PacketBufferNode createNpcSecondActionPacket(int npcIndex, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createNPCActionPacket("OPNPC2", npcIndex, ctrlDown);
	}

	public static PacketBufferNode createNpcThirdActionPacket(int npcIndex, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createNPCActionPacket("OPNPC3", npcIndex, ctrlDown);
	}

	public static PacketBufferNode createNpcFourthActionPacket(int npcIndex, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createNPCActionPacket("OPNPC4", npcIndex, ctrlDown);
	}

	public static PacketBufferNode createNpcFifthActionPacket(int npcIndex, boolean ctrlDown)
	{
		return ClientPackets.INSTANCE.createNPCActionPacket("OPNPC5", npcIndex, ctrlDown);
	}

	public static PacketBufferNode createWidgetOnNpc(int npcIndex, int itemWidgetId, int itemId, int itemSlot, boolean ctrlDown)
	{
		var client = Game.getClient();
		var clientPacket = Game.getClientPacket();
		var packetBufferNode = Game.getClient().preparePacket(clientPacket.OPNPCT(), client.getPacketWriter().getIsaacCipher());
		packetBufferNode.getPacketBuffer().writeByteNeg(ctrlDown ? 1 : 0);
		packetBufferNode.getPacketBuffer().writeShort(itemSlot);
		packetBufferNode.getPacketBuffer().writeShortLE(npcIndex);
		packetBufferNode.getPacketBuffer().writeIntLE(itemWidgetId);
		packetBufferNode.getPacketBuffer().writeShortAdd(itemId);
		return packetBufferNode;
	}
}