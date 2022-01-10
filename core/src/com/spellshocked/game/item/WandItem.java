package com.spellshocked.game.item;

import com.badlogic.gdx.math.Vector2;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.ProjectileEntity;
import com.spellshocked.game.world.Tile;

public class WandItem extends WeaponItem {
    public WandItem() {
        super("./json/Inventory/Item/Weapon/wand.json");
    }

    @Override
    public void onUse(PlayerEntity p) {
        ProjectileEntity entity = new ProjectileEntity(5);
        entity.setTile(p.getTile());
        entity.targetTile(p.getTile().findFromClick(Spellshocked.getInstance().world.mouse));
        Spellshocked.getInstance().world.addEntity(entity);
    }
}