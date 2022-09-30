package meteor.plugins.wintertodtfletcher

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem
import net.runelite.client.config.Range

@ConfigGroup("wintertodthelper")
interface WintertodtHelperConfig : Config {
    @Range(min = 5, max = 89)
    @ConfigItem(
        position = 1,
        keyName = "healAt",
        name = "Heal at",
        description = "Automatically eat any available food when health hits this value"
    )
    fun healAt(): Int {
        return 7
    }
}