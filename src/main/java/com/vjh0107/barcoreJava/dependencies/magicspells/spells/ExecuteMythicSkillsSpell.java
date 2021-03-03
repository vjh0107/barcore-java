package com.vjh0107.barcoreJava.dependencies.magicspells.spells;


import com.nisovin.magicspells.spells.InstantSpell;
import com.nisovin.magicspells.util.MagicConfig;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.commands.CommandHelper;
import io.lumine.xikage.mythicmobs.util.MythicUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExecuteMythicSkillsSpell extends InstantSpell {
    String skillName;
    public ExecuteMythicSkillsSpell(MagicConfig config, String spellName) {
        super(config, spellName);
        skillName = getConfigString("skillName", "Fireball");

    }

    @Override
    public PostCastAction castSpell(LivingEntity sender, SpellCastState state, float power, String[] args) {
        Player player = (Player)sender;
        LivingEntity target = MythicUtil.getTargetedEntity(player);
        String spell = skillName;
        List<Entity> targets = new ArrayList();
        targets.add(target);
        MythicMobs.inst().getAPIHelper().castSkill(player, spell, player, player.getLocation(), targets, (Collection)null, 1.0F);
        return PostCastAction.HANDLE_NORMALLY;
    }

    @Override
    public boolean castFromConsole(CommandSender sender, String[] args) {
        return true;
    }

}