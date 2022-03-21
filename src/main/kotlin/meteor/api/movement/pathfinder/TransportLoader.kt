package meteor.api.movement.pathfinder

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import meteor.api.commons.Worlds
import meteor.api.entities.NPCs
import meteor.api.entities.Players
import meteor.api.game.Skills
import meteor.api.game.Vars
import meteor.api.items.Inventory
import meteor.api.movement.Movement
import meteor.api.movement.Reachable
import meteor.api.widgets.Dialog
import meteor.api.widgets.Widgets
import net.runelite.api.*
import net.runelite.api.coords.Direction
import net.runelite.api.coords.WorldArea
import net.runelite.api.coords.WorldPoint
import net.runelite.api.widgets.Widget
import net.runelite.api.widgets.WidgetInfo
import java.io.InputStream
import java.net.MalformedURLException
import java.time.Instant
import java.util.*
import java.util.stream.Collectors

object TransportLoader {
    private const val TREE_GNOME_VILLAGE_VARBIT = 111
    private const val GRAND_TREE_VARBIT = 150
    private const val RFD_VARBIT = 1850
    private const val BUILD_DELAY_SECONDS = 5
    private var lastBuild: Instant = Instant.now().minusSeconds(6)
    private val STATIC_TRANSPORTS: List<Transport> = ArrayList()
    private var LAST_TRANSPORT_LIST = emptyList<Transport>()
    private val MLM: WorldArea = WorldArea(3714, 5633, 60, 62, 0)
    val SPIRIT_TREES = java.util.List.of(
        SpiritTree(WorldPoint(2542, 3170, 0), "Tree gnome Village"),
        SpiritTree(WorldPoint(2461, 3444, 0), "Gnome Stronghold"),
        SpiritTree(WorldPoint(2555, 3259, 0), "Battlefield of Khazard"),
        SpiritTree(WorldPoint(3185, 3508, 0), "Grand Exchange"),
        SpiritTree(WorldPoint(2488, 2850, 0), "Feldip Hills")
    )
    val MUSHTREES = java.util.List.of(
        MagicMushtree(WorldPoint(3676, 3871, 0), WidgetInfo.FOSSIL_MUSHROOM_MEADOW),
        MagicMushtree(WorldPoint(3764, 3879, 0), WidgetInfo.FOSSIL_MUSHROOM_HOUSE),
        MagicMushtree(WorldPoint(3676, 3755, 0), WidgetInfo.FOSSIL_MUSHROOM_SWAMP),
        MagicMushtree(WorldPoint(3760, 3758, 0), WidgetInfo.FOSSIL_MUSHROOM_VALLEY)
    )

    init {
        // Try to initialize the static transports before usage
        loadStaticTransports()
    }

    private fun loadStaticTransports(): List<Transport> {
        if (!STATIC_TRANSPORTS.isEmpty()) {
            return STATIC_TRANSPORTS
        }
        try {
            //println(URL(RegionManager.API_URL + "/transports").toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        val newTransports: InputStream? = Main.javaClass.getResourceAsStream("/trans.txt")
/*        val remoteTransports: Array<RemoteTransport>
        try {
            assert(newTransports != null)
            remoteTransports = gson.fromJson<Array<RemoteTransport>>(
                String(newTransports!!.readAllBytes()),
                Array<RemoteTransport>::class.java as Type
            )
            for (rt in remoteTransports) {

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }*/
        return STATIC_TRANSPORTS
    }

    fun buildTransports(): List<Transport> {
        if (lastBuild.plusSeconds(BUILD_DELAY_SECONDS.toLong()).isAfter(Instant.now())) {
            return java.util.List.copyOf(LAST_TRANSPORT_LIST)
        }
        lastBuild = Instant.now()
        val transports: MutableList<Transport> = ArrayList(loadStaticTransports())
        val gold = if (Inventory.getFirst(995) != null) Inventory.getFirst(995)?.quantity else 0
        if (gold != null) {
            if (gold >= 10) {
                transports.add(
                    objectTransport(
                        WorldPoint(3267, 3228, 0),
                        WorldPoint(3268, 3228, 0),
                        2883,
                        "Pay-toll(10gp)"
                    )
                )
                transports.add(
                    objectTransport(
                        WorldPoint(3268, 3228, 0),
                        WorldPoint(3267, 3228, 0),
                        2883,
                        "Pay-toll(10gp)"
                    )
                )
                transports.add(
                    objectTransport(
                        WorldPoint(3267, 3227, 0),
                        WorldPoint(3268, 3227, 0),
                        2882,
                        "Pay-toll(10gp)"
                    )
                )
                transports.add(
                    objectTransport(
                        WorldPoint(3268, 3227, 0),
                        WorldPoint(3267, 3227, 0),
                        2882,
                        "Pay-toll(10gp)"
                    )
                )
            }
        }

        // Lumbridge castle dining room, ignore if RFD is in progress.
        if (Vars.getBit(RFD_VARBIT) == -1) {
            transports.add(objectTransport(WorldPoint(3213, 3221, 0), WorldPoint(3212, 3221, 0), 12349, "Open"))
            transports.add(objectTransport(WorldPoint(3212, 3221, 0), WorldPoint(3213, 3221, 0), 12349, "Open"))
            transports.add(objectTransport(WorldPoint(3213, 3222, 0), WorldPoint(3212, 3222, 0), 12350, "Open"))
            transports.add(objectTransport(WorldPoint(3212, 3222, 0), WorldPoint(3213, 3222, 0), 12350, "Open"))
            transports.add(objectTransport(WorldPoint(3207, 3218, 0), WorldPoint(3207, 3217, 0), 12348, "Open"))
            transports.add(objectTransport(WorldPoint(3207, 3217, 0), WorldPoint(3207, 3218, 0), 12348, "Open"))
        }
        if (Worlds.inMembersWorld()) {
            // Edgeville
            if (Skills.getBoostedLevel(Skill.AGILITY) >= 21) {
                transports.add(
                    objectTransport(
                        WorldPoint(3142, 3513, 0),
                        WorldPoint(3137, 3516, 0),
                        16530,
                        "Climb-into"
                    )
                )
                transports.add(
                    objectTransport(
                        WorldPoint(3137, 3516, 0),
                        WorldPoint(3142, 3513, 0),
                        16529,
                        "Climb-into"
                    )
                )
            }

            // Glarial's tomb
            transports.add(itemUseTransport(WorldPoint(2557, 3444, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2557, 3445, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2558, 3443, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2559, 3443, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2560, 3444, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2560, 3445, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2558, 3446, 0), WorldPoint(2555, 9844, 0), 294, 1992))
            transports.add(itemUseTransport(WorldPoint(2559, 3446, 0), WorldPoint(2555, 9844, 0), 294, 1992))

            // Waterfall Island
            transports.add(itemUseTransport(WorldPoint(2512, 3476, 0), WorldPoint(2513, 3468, 0), 954, 1996))
            transports.add(itemUseTransport(WorldPoint(2512, 3466, 0), WorldPoint(2511, 3463, 0), 954, 2020))

            // Crabclaw island
            if (gold != null) {
                if (gold >= 10000) {
                    transports.add(npcTransport(WorldPoint(1782, 3458, 0), WorldPoint(1778, 3417, 0), 7483, "Travel"))
                }
            }
            transports.add(npcTransport(WorldPoint(1779, 3418, 0), WorldPoint(1784, 3458, 0), 7484, "Travel"))

            // Port sarim
            if (Vars.getBit(4897) == 0) {
                if (Vars.getBit(8063)!! >= 7) {
                    transports.add(
                        npcDialogTransport(
                            WorldPoint(3054, 3245, 0),
                            WorldPoint(1824, 3691, 0),
                            8484,
                            "Can you take me to Great Kourend?"
                        )
                    )
                } else {
                    transports.add(
                        npcDialogTransport(
                            WorldPoint(3054, 3245, 0),
                            WorldPoint(1824, 3691, 0),
                            8484,
                            "That's great, can you take me there please?"
                        )
                    )
                }
            } else {
                transports.add(
                    npcTransport(
                        WorldPoint(3054, 3245, 0),
                        WorldPoint(1824, 3695, 1),
                        10724,
                        "Port Piscarilius"
                    )
                )
            }

            // Paterdomus
            transports.add(trapDoorTransport(WorldPoint(3405, 3506, 0), WorldPoint(3405, 9906, 0), 1579, 1581))
            transports.add(trapDoorTransport(WorldPoint(3423, 3485, 0), WorldPoint(3440, 9887, 0), 3432, 3433))
            transports.add(trapDoorTransport(WorldPoint(3422, 3484, 0), WorldPoint(3440, 9887, 0), 3432, 3433))

            // Port Piscarilius
            transports.add(npcTransport(WorldPoint(1824, 3691, 0), WorldPoint(3055, 3242, 1), 10727, "Port Sarim"))

            // Spirit Trees
            if (Vars.getVarp(TREE_GNOME_VILLAGE_VARBIT) === 9) {
                for (source in SPIRIT_TREES) {
                    if (source.location == "Gnome Stronghold" && Vars.getVarp(GRAND_TREE_VARBIT) < 160) {
                        continue
                    }
                    for (target in SPIRIT_TREES) {
                        transports.add(spritTreeTransport(source.position, target.position, target.location))
                    }
                }
            }

            // Magic Mushtrees
            for (source in MUSHTREES) {
                for (target in MUSHTREES) {
                    transports.add(mushtreeTransport(source.position, target.position, target.widget))
                }
            }

            // Gnome stronghold
            transports.add(
                objectDialogTransport(
                    WorldPoint(2461, 3382, 0),
                    WorldPoint(2461, 3385, 0),
                    190,
                    "Open",
                    "Sorry, I'm a bit busy."
                )
            )

            // Tree Gnome Village
            if (Vars.getVarp(111) > 0) {
                transports.add(npcTransport(WorldPoint(2504, 3192, 0), WorldPoint(2515, 3159, 0), 4968, "Follow"))
                transports.add(npcTransport(WorldPoint(2515, 3159, 0), WorldPoint(2504, 3192, 0), 4968, "Follow"))
            }
        }

        // Entrana
        transports.add(npcTransport(WorldPoint(3041, 3237, 0), WorldPoint(2834, 3331, 1), 1166, "Take-boat"))
        transports.add(npcTransport(WorldPoint(2834, 3335, 0), WorldPoint(3048, 3231, 1), 1170, "Take-boat"))
        transports.add(
            npcDialogTransport(
                WorldPoint(2821, 3374, 0),
                WorldPoint(2822, 9774, 0),
                1164,
                "Well that is a risk I will have to take."
            )
        )

        // Fossil Island
        transports.add(
            npcTransport(
                WorldPoint(3362, 3445, 0),
                WorldPoint(3724, 3808, 0),
                8012,
                "Quick-Travel"
            )
        )
        transports.add(
            objectDialogTransport(
                WorldPoint(3724, 3808, 0),
                WorldPoint(3362, 3445, 0),
                30914,
                "Travel",
                "Row to the barge and travel to the Digsite."
            )
        )

        // Motherload Mine
        if (MLM.contains(Players.local)) {
            transports.addAll(motherloadMineTransport(WorldPoint(3726, 5643, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3726, 5654, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3727, 5652, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3727, 5683, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3728, 5651, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3728, 5688, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3731, 5683, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3733, 5680, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3745, 5689, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3748, 5684, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3748, 5689, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3755, 5640, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3756, 5639, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3757, 5677, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3759, 5690, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3762, 5652, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3762, 5668, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3765, 5688, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3766, 5639, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3766, 5647, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3768, 5674, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3768, 5679, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3769, 5642, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3769, 5658, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3769, 5680, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3770, 5659, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3771, 5638, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3762, 5687, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3766, 5670, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3719, 5664, 0)))
            transports.addAll(motherloadMineTransport(WorldPoint(3720, 5665, 0)))
        }
        return java.util.List.copyOf(transports.also { LAST_TRANSPORT_LIST = it })
    }

    fun parseTransportLine(line: String): Transport {
        val split = line.split(" ").toTypedArray()
        return objectTransport(
            WorldPoint(split[0].toInt(), split[1].toInt(), split[2].toInt()),
            WorldPoint(split[3].toInt(), split[4].toInt(), split[5].toInt()), split[split.size - 1].toInt(), split[6]
        )
    }

    fun trapDoorTransport(
        source: WorldPoint,
        destination: WorldPoint,
        closedId: Int,
        openedId: Int
    ): Transport {
        return Transport(source, destination, Int.MAX_VALUE, 0, label@{
            val openedTrapdoor: TileObject? = meteor.api.entities.Objects.getFirstSurrounding(source, 5, openedId)
            if (openedTrapdoor != null) {
                openedTrapdoor.interact(0)
                return@label
            }
            val closedTrapDoor: TileObject? = meteor.api.entities.Objects.getFirstSurrounding(source, 5, closedId)
            if (closedTrapDoor != null) {
                closedTrapDoor.interact(0)
            }
        }, "")
    }

    fun itemUseTransport(
        source: WorldPoint,
        destination: WorldPoint,
        itemId: Int,
        objId: Int
    ): Transport {
        return Transport(source, destination, Int.MAX_VALUE, 0, label@{
            val item: Item = Inventory.getFirst(itemId) ?: return@label
            val transport: TileObject? = meteor.api.entities.Objects.getFirstSurrounding(source, 5, objId)
            if (transport != null) {
                item.useOn(transport)
            }
        }, "")
    }

    fun npcTransport(
        source: WorldPoint,
        destination: WorldPoint,
        npcId: Int,
        action: String
    ): Transport {
        return Transport(source, destination, 10, 0, {
            NPCs.getNearest { x -> x.worldLocation.distanceTo(source) <= 10 && x.id == npcId }
                ?.interact(action)
        }, action)
    }

    fun npcDialogTransport(
        source: WorldPoint,
        destination: WorldPoint,
        npcId: Int,
        vararg chatOptions: String
    ): Transport {
        return Transport(source, destination, 10, 0, label@{
            if (Dialog.isViewingOptions) {
                if (Dialog.canContinue()) {
                    Dialog.continueSpace()
                    return@label
                }
                if (Dialog.chooseOption(*chatOptions)) {
                    return@label
                }
                return@label
            }
            NPCs.getNearest { x -> x.worldLocation.distanceTo(source) <= 10 && x.id == npcId }?.interact(0)
        }, "")
    }

    fun motherloadMineTransport(
        rockfall: WorldPoint
    ): List<Transport> {
        return Arrays.stream(Direction.values()).map { dir: Direction? ->
            val neighbor: WorldPoint = Reachable.getNeighbour(dir, rockfall)!!
            if (Reachable.isWalkable(neighbor)) {
                var dest: WorldPoint? = null
                when (dir) {
                    Direction.NORTH -> dest = rockfall.dy(-1)
                    Direction.SOUTH -> dest = rockfall.dy(1)
                    Direction.WEST -> dest = rockfall.dx(1)
                    Direction.EAST -> dest = rockfall.dx(-1)
                }
                if (dest != null) {
                    val finalDest: WorldPoint = dest
                    return@map Transport(neighbor, finalDest, Int.MAX_VALUE, 0, {
                        meteor.api.entities.Objects.getAt(rockfall) { x -> (x.name as java.lang.String).equalsIgnoreCase("Rockfall") }
                            .stream()
                            .findFirst()
                            .ifPresentOrElse({ obj -> obj!!.interact("Mine") }) { Movement.walk(finalDest) }
                    }, "Mine")
                }
            }
            null
        }.filter { obj: Transport? -> Objects.nonNull(obj) }.collect(Collectors.toList<Transport>())
    }

    fun objectTransport(
        source: WorldPoint,
        destination: WorldPoint,
        objId: Int,
        action: String
    ): Transport {
        return Transport(source, destination, Int.MAX_VALUE, 0, {
            meteor.api.entities.Objects.getSurrounding(source, 5) { x -> x.id == objId }
                .stream()
                .findFirst()
                .ifPresent { obj -> obj.interact(action) }
        }, action)
    }

    fun objectDialogTransport(
        source: WorldPoint,
        destination: WorldPoint,
        objId: Int,
        action: String,
        vararg chatOptions: String?
    ): Transport {
        return Transport(source, destination, Int.MAX_VALUE, 0, label@{
            if (Dialog.isViewingOptions) {
                if (Dialog.canContinue()) {
                    Dialog.continueSpace()
                    return@label
                }
                if (Dialog.chooseOption(*chatOptions)) {
                    return@label
                }
                return@label
            }
            meteor.api.entities.Objects.getFirstSurrounding(source, 5, objId)?.interact(action)
        }, action)
    }

    private fun spritTreeTransport(source: WorldPoint, target: WorldPoint, location: String): Transport {
        return Transport(
            source,
            target,
            5,
            0, label@
            {
                val treeWidget: Widget = Widgets[187, 3]!!
                if (Widgets.isVisible(treeWidget)) {
                    Arrays.stream(treeWidget.dynamicChildren)
                        .filter { child: Widget -> child.text.contains(location) }
                        .findFirst()
                        .ifPresent { child: Widget ->
                            child.interact(
                                0,
                                MenuAction.WIDGET_TYPE_6.id,
                                child.index,
                                child.id
                            )
                        }
                    return@label
                }
                meteor.api.entities.Objects.getNearest("Spirit tree")?.interact("Travel")
            }, ""
        )
    }

    private fun mushtreeTransport(source: WorldPoint, target: WorldPoint, widget: WidgetInfo): Transport {
        return Transport(
            source,
            target,
            5,
            0, label@
            {
                val treeWidget: Widget = Widgets[widget]!!
                if (Widgets.isVisible(treeWidget)) {
                    treeWidget.interact(0, MenuAction.WIDGET_TYPE_6.id, treeWidget.index, treeWidget.id)
                    return@label
                }
                meteor.api.entities.Objects.getNearest("Magic Mushtree")?.interact("Use")
            }, "Use"
        )
    }

    class MagicMushtree(position: WorldPoint, widget: WidgetInfo) {
        val position: WorldPoint
        val widget: WidgetInfo

        init {
            this.position = position
            this.widget = widget
        }
    }

    class SpiritTree(position: WorldPoint, location: String) {
        val position: WorldPoint
        val location: String

        init {
            this.position = position
            this.location = location
        }
    }
}