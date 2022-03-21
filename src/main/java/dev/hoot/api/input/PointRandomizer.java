package dev.hoot.api.input;

import dev.hoot.api.game.Game;
import dev.hoot.api.util.Randomizer;
import net.runelite.api.Actor;
import net.runelite.api.Item;
import net.runelite.api.Perspective;
import net.runelite.api.TileItem;
import net.runelite.api.TileObject;
import net.runelite.api.widgets.Widget;

import java.awt.*;

public class PointRandomizer
{

    public static Point getRandomPointIn(Widget widget)
    {
        return Randomizer.getRandomPointIn(getBoundsFor(widget));
    }

    public static Rectangle getBoundsFor(Widget widget)
    {
        return widget.getBounds();
    }

    public static Point getRandomPointIn(TileItem item)
    {
        return Randomizer.getRandomPointIn(getBoundsFor(item));
    }

    public static Rectangle getBoundsFor(TileItem item)
    {
        Shape shape = Perspective.getClickbox(Game.getClient(), item.getModel(), 0, item.getLocalLocation());
        if (shape != null)
        {
            return shape.getBounds();
        }
        else
        {
            net.runelite.api.Point screenCoords = Perspective.localToCanvas(Game.getClient(), item.getLocalLocation(), Game.getClient().getPlane());
            if (screenCoords != null)
            {
                return new Rectangle(screenCoords.getX(), screenCoords.getY(), 0, 0);
            }
            return new Rectangle(-1, -1, 0, 0);
        }
    }

    public static Point getRandomPointIn(Item item)
    {
        return Randomizer.getRandomPointIn(getBoundsFor(item));
    }

    public static Rectangle getBoundsFor(Item item)
    {
        Widget widget = Game.getClient().getWidget(item.getWidgetId());
        if (widget == null)
        {
            return new Rectangle(-1, -1, 0, 0);
        }

        if (item.getType() != Item.Type.EQUIPMENT)
        {
            Widget slot = widget.getChild(item.getSlot());
            if (slot != null)
            {
                return slot.getBounds() != null ? slot.getBounds() : new Rectangle(-1, -1, 0, 0);
            }
        }

        Rectangle bounds = widget.getBounds();
        if (bounds != null)
        {
            Rectangle itemBounds = widget.getWidgetItem(item.getSlot()).getCanvasBounds();
            return itemBounds != null ? itemBounds : new Rectangle(-1, -1, 0, 0);
        }
        return new Rectangle(-1, -1, 0, 0);
    }

    public static Point getRandomPointIn(Actor npc)
    {
        return Randomizer.getRandomPointIn(getBoundsFor(npc));
    }

    public static Rectangle getBoundsFor(Actor npc)
    {
        Shape shape = Perspective.getClickbox(Game.getClient(), npc.getModel(), 0, npc.getLocalLocation());
        if (shape != null)
        {
            return shape.getBounds();
        }
        else
        {
            return npc.getCanvasTilePoly().getBounds();
        }
    }

    public static Point getRandomPointIn(TileObject object)
    {
        return Randomizer.getRandomPointIn(getBoundsFor(object));
    }

    public static Rectangle getBoundsFor(TileObject object)
    {
        Shape shape = object.getClickbox();
        if (shape != null)
        {
            return shape.getBounds();
        }
        else
        {
            net.runelite.api.Point screenCoords = Perspective.localToCanvas(Game.getClient(), object.getLocalLocation(), Game.getClient().getPlane());
            if (screenCoords != null)
            {
                return new Rectangle(screenCoords.getX(), screenCoords.getY(), 0, 0);
            }
            return new Rectangle(-1, -1, 0, 0);
        }
    }

}
