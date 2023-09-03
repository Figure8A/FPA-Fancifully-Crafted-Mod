package com.figure8.util;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier GRASS_BLOCK_ID
            = new Identifier("minecraft", "blocks/grass");
    private static final Identifier imple_dungeon_CHEST_ID
            = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier simple_dungeon_CHEST_ID
            = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier ruin_port_CHEST_ID
            = new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier tresure_CHEST_ID
            = new Identifier("minecraft", "chests/shipwreck_supply");
    private static final Identifier CREEPER_ID
            = new Identifier("minecraft", "entities/creeper");
    private static final Identifier Warden_ID
            = new Identifier("minecraft", "entities/warden");
    private static final Identifier CHESTS_ID
            = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier fishing_ID
            = new Identifier("minecraft", "gameplay/fishing/treasure");


    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(GRASS_BLOCK_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.35f)) // Drops 35% of the time
                        .with(ItemEntry.builder(fpaore.squiggle))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(imple_dungeon_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) // Drops 100% of the time
                        .with(ItemEntry.builder(fpaore.lounge_inside_music_disk))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(simple_dungeon_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) // Drops 100% of the time
                        .with(ItemEntry.builder(fpaore.level_four_music_disk))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(ruin_port_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.20f)) // Drops 100% of the time
                        .with(ItemEntry.builder(fpaore.bradium))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 11.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(tresure_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.50f)) // Drops 100% of the time
                        .with(ItemEntry.builder(fpaore.icecream))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0f, 20.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(CREEPER_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.15f))
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                                new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                        .mainhand(ItemPredicate.Builder.create().items(fpaore.pencilsword).build()).build()).build()))
                        .with(ItemEntry.builder(fpaore.bradium))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if(Warden_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(fpaore.mayor_of_undying))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(CHESTS_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.8f)) // Drops 35% of the time
                        .with(ItemEntry.builder(fpaore.angry_baddie_music_disk))
                        .with(ItemEntry.builder(fpaore.mayor_theme_music_disk))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0f, 14.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(fishing_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.34f)) // Drops 35% of the time
                        .with(ItemEntry.builder(fpaore.bradoredsl))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

        });
    }
}