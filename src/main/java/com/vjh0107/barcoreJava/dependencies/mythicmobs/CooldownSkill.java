package com.vjh0107.barcoreJava.dependencies.mythicmobs;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@MythicMechanic(
        author = "vjh0107",
        name = "cooldown",
        description = "set cooldown of item by specific material"
)
public class CooldownSkill extends SkillMechanic implements ITargetedEntitySkill {

    protected Material material;
    protected Integer cooldown;

    public CooldownSkill(String skill, MythicLineConfig mlc) {
        super(skill, mlc);

        this.cooldown = mlc.getInteger("vjh0107.c", 10);
        this.cooldown = mlc.getInteger("vjh0107.cooldown", 10);

        String materialKey = mlc.getString("vjh0107.m", "STONE");
        if (Material.matchMaterial(materialKey) == null) {
            System.out.println("Material not found named " + materialKey + " setting default \"STONE\" ");
        }
        this.material = Material.matchMaterial(materialKey);

        this.ASYNC_SAFE = false;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (!target.isPlayer()) return true;


        Player castPlayer = (Player) data.getCaster();
        castPlayer.setCooldown(material, cooldown);

        return true;
    }
}
