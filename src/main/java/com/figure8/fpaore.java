package com.figure8;

import com.figure8.Networktests.ModNetworkRegisters;
import com.figure8.blocks.*;


import com.figure8.blocks.signstuffs.*;
import com.figure8.blocks.woodtype.ModBlockSetType;
import com.figure8.blocks.woodtype.ModWoodType;
import com.figure8.effects.ModEffects;
import com.figure8.entity.*;
import com.figure8.item.*;

import com.figure8.sound.ModSounds;
import com.figure8.util.ModLootTableModifiers;
import com.figure8.util.PlayerCopyHandler;
import com.figure8.world.gen.ModWorldGeneration;
import com.figure8.world.tree.fwoodSaplingGenerator;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.*;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.figure8.world.ModPlacedFeatures.FWOOD_PLACED_KEY;
import static net.minecraft.block.Blocks.*;
import static net.minecraft.entity.effect.StatusEffects.*;

// The messiest code you've ever seen in your life:

public class fpaore implements ModInitializer {


	public static final String MOD_ID = "fpaore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);






	public static final Item lounge_inside_music_disk = new MusicDiscItem(6, ModSounds.lounge_inside, new FabricItemSettings().maxCount(1), 19);
	public static final Item angry_baddie_music_disk = new MusicDiscItem(7, ModSounds.angry_baddie_music_disk, new FabricItemSettings().maxCount(1), 39);
	public static final Item mayor_theme_music_disk = new MusicDiscItem(8, ModSounds.mayor_theme_music_disk, new FabricItemSettings().maxCount(1), 68);
	public static final Item level_four_music_disk = new MusicDiscItem(9, ModSounds.level_four_music_disk, new FabricItemSettings().maxCount(1), 158);




	public static ToolItem pencilsword = new SwordItem(fpaoreMaterial.INSTANCE, 6, -3.4F, new Item.Settings().rarity(Rarity.UNCOMMON));


	public static final Block BradOre = new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(ModSounds.BRADBLOCK_BLOCK_SOUNDS).strength(40f).requiresTool(), UniformIntProvider.create(5, 10));
	public static final Block bradoredsl = new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).sounds(ModSounds.BRADBLOCK_BLOCK_SOUNDS).strength(55f).requiresTool(), UniformIntProvider.create(5, 10));

	public static final Block sketch = new sketch(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN).luminance(state -> 122).sounds(BlockSoundGroup.SMALL_AMETHYST_BUD).nonOpaque().strength(3f).requiresTool());

	public static final Block fpagrounda = new fpagrounda(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).requiresTool());

	public static final Block fpagrounda_verticalslab = new fpagrounda_verticalslab(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).nonOpaque().requiresTool());

	public static final Block fpagrounda_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).requiresTool());

	public static final Block fpagrounda_stair = new ModStairsBlock(fpagrounda.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).requiresTool());

	public static final Block fpagroundatrimed = new fpagroundatrimed(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(5f).requiresTool());

	public static final Block fpagroundatrimed_slab = new SlabBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(5f).requiresTool());

	public static final Block fpagroundatrimed_verticalslab = new fpagroundatrimed_verticalslab(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).nonOpaque().strength(5f).requiresTool());

	public static final Block fpagroundatrimed_stair = new ModStairsBlock(fpagroundatrimed.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(5f).requiresTool());

	public static final Block toughf = new toughf(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).requiresTool()) {
	};

	public static final Block toughf_wall = new WallBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CONCRETE).strength(3f).requiresTool());


	public static final Block coursegrasblockbutgood = new coursegrasblockbutgood(FabricBlockSettings.create().sounds(BlockSoundGroup.GRAVEL).ticksRandomly().strength(1f));

	public static final Block grasblockbutgood = new grasblockbutgood(AbstractBlock.Settings.create().ticksRandomly().sounds(BlockSoundGroup.GRASS).strength(1f));


	public static final Block grasspop = new FlowerBlock(StatusEffects.HASTE, 8,
					FabricBlockSettings.copy(Blocks.DANDELION).sounds(ModSounds.GRASSPOP_BLOCK_SOUNDS));

	public static final Block potted_grasspop = registerBlockWithoutItem("potted_grasspop",
			new FlowerPotBlock(fpaore.grasspop, FabricBlockSettings.copy(POTTED_DANDELION)));

	public static final Block fgrass = new FlowerBlock(StatusEffects.HASTE, 8,
			FabricBlockSettings.copy(Blocks.DANDELION));

	public static final Block potted_fgrass = registerBlockWithoutItem("potted_fgrass",
			new FlowerPotBlock(fpaore.fgrass, FabricBlockSettings.copy(POTTED_DANDELION)));



	public static final Block spring = new spring(FabricBlockSettings.create().sounds(ModSounds.SPRING_BLOCK_SOUNDS).nonOpaque().strength(2f));

	public static final Block mayorglass = new mayorglass(AbstractBlock.Settings.create().instrument(Instrument.HAT).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(fpaore::never).solidBlock(fpaore::never).suffocates(fpaore::never).blockVision(fpaore::never));

	public static final Block fcactus = new PillarBlock(FabricBlockSettings.copyOf(Blocks.BASALT).strength(20f).sounds(BlockSoundGroup.CALCITE));



	public static final Block fwood_log = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(1.0f));
	public static final Block fwood_wood = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(1.0f));
	public static final Block stripped_fwood_log = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).strength(1.0f));
	public static final Block stripped_fwood_wood = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(1.0f));
	public static final Block fwood_planks = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(1.0f));
	public static final Block fwood_slab = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).strength(1f));
	public static final Block fwood_stair = new ModStairsBlock(fwood_planks.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).strength(1f));

	public static final Block fwood_door = new DoorBlock(AbstractBlock.Settings.copy(OAK_DOOR).mapColor(fwood_planks.getDefaultMapColor()).instrument(Instrument.BASS).strength(1.0f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY).strength(1.0f).sounds(BlockSoundGroup.WOOD), ModBlockSetType.FWOOD);

	public static final Block fwood_gate = new FenceGateBlock(AbstractBlock.Settings.create().mapColor(fwood_planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).strength(2.0f, 3.0f).burnable(), ModWoodType.FWOOD);

	public static final Block fwood_plate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.create().mapColor(fwood_planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(0.5f).burnable().pistonBehavior(PistonBehavior.DESTROY), ModBlockSetType.FWOOD);

	public static final Block fwood_fence = new FenceBlock(AbstractBlock.Settings.copy(OAK_FENCE_GATE).strength(1.0f));

	public static final Block fwood_trapdoor = new TrapdoorBlock(AbstractBlock.Settings.create().instrument(Instrument.BASS).strength(3.0f).nonOpaque().burnable(), ModBlockSetType.FWOOD);

	public static final Block fwood_button = new ButtonBlock(AbstractBlock.Settings.copy(OAK_BUTTON).noCollision().strength(0.5f), ModBlockSetType.FWOOD, 30, true);

	public static final Block fwood_sign = registerBlockWithoutBlockItem("fwood_sign",
			new ModStandingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN), ModWoodType.FWOOD));
	public static final Block fwood_wall_sign = registerBlockWithoutBlockItem("fwood_wall_sign",
			new ModWallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), ModWoodType.FWOOD));
	public static final Item fwood_sign_item = registerItem("fwood_sign_item",
			new SignItem(new FabricItemSettings().maxCount(16), fpaore.fwood_sign, fpaore.fwood_wall_sign));

	public static final Block stripped_fwood_sign = registerBlockWithoutBlockItem("stripped_fwood_sign",
			new StrippedModStandingSignBlock(FabricBlockSettings.copyOf(OAK_SIGN), ModWoodType.STRIPPED_FWOOD));
	public static final Block stripped_fwood_wall_sign = registerBlockWithoutBlockItem("stripped_fwood_wall_sign",
			new StrippedModWallSignBlock(FabricBlockSettings.copyOf(OAK_WALL_SIGN), ModWoodType.STRIPPED_FWOOD));
	public static final Item stripped_fwood_sign_item = registerItem("stripped_fwood_sign_item",
			new SignItem(new FabricItemSettings().maxCount(16), fpaore.stripped_fwood_sign, fpaore.stripped_fwood_wall_sign));

	public static final Block fwood_hanging_sign = registerBlockWithoutBlockItem("fwood_hanging_sign",
			new ModHangingSignBlock(FabricBlockSettings.copyOf(OAK_HANGING_SIGN), ModWoodType.FWOOD));
	public static final Block fwood_hanging_wall_sign = registerBlockWithoutBlockItem("fwood_hanging_wall_sign",
			new ModWallHangingSignBlock(FabricBlockSettings.copyOf(OAK_WALL_HANGING_SIGN).dropsLike(fwood_hanging_sign), ModWoodType.FWOOD));
	public static final Item fwood_hanging_sign_item = registerItem("fwood_hanging_sign_item",
			new HangingSignItem(fpaore.fwood_hanging_sign, fpaore.fwood_hanging_wall_sign, new FabricItemSettings().maxCount(16)));

	public static final Block fwood_leaves = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES).nonOpaque().strength(0.1f));

	public static final Block fwood_sapling = new SaplingBlock(new fwoodSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).strength(0.2f));

	public static final Block potted_fwood_sapling = registerBlockWithoutItem("potted_fwood_sapling",
			new FlowerPotBlock(fpaore.fwood_sapling, FabricBlockSettings.copy(POTTED_BIRCH_SAPLING)));


	public static final Block fpvground = new fpvground(FabricBlockSettings.copyOf(GRANITE).strength(10.0f).requiresTool());
	public static final Block packed_fpvground = new PillarBlock(FabricBlockSettings.copyOf(GRANITE).strength(12.0f).requiresTool());
	public static final Block packed_fpvground_column = new PillarBlock(FabricBlockSettings.copyOf(GRANITE).strength(12.0f).requiresTool());
	public static final Block inkblot = new inkblot(FabricBlockSettings.copyOf(SEA_LANTERN).sounds(BlockSoundGroup.MUDDY_MANGROVE_ROOTS).nonOpaque().strength(0.2f).notSolid().pistonBehavior(PistonBehavior.DESTROY).luminance(state -> 8).slipperiness(1.1f));

	public static final Block spike = new spike(9, 4, FabricBlockSettings.create().strength(65.0f).requiresTool().nonOpaque(), ParticleTypes.PORTAL);

	public static final Block spikebuth = new spikebuth(9, 4, FabricBlockSettings.copy(OBSIDIAN).strength(65.0f).requiresTool().nonOpaque());

	public static final Block fchiseled_sandstone = new Block(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8f));

	public static final Block smoothsandstone_verticalslab = new smoothsandstone_verticalslab(AbstractBlock.Settings.create().nonOpaque().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASEDRUM).requiresTool().strength(0.8f));

	public static final Block sandstonebedrock = new Block(AbstractBlock.Settings.copy(BEDROCK).mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).strength(-1.0f, 3600000.0f).dropsNothing().allowsSpawning(fpaore::never));

	public static final Item bradium = new Item(new FabricItemSettings().rarity(Rarity.RARE).fireproof());

	public static final EntityType<ThrowableBlobEntity> THROWABLE_BLOB_ENTITY = registerThrowableBlob("throwable_blob");

	public static final Item inkblob = new inkblob(new Item.Settings(), THROWABLE_BLOB_ENTITY);

	public static final Block inkblot_wall = new Wallinkblot(FabricBlockSettings.create().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.MUDDY_MANGROVE_ROOTS).nonOpaque().strength(0.2f).luminance(state -> 8));



	public static final Item mayor_of_undying = new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1));


	public static final Item icecream = new Item(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(SPEED, 200, 3), 0.1f).statusEffect(new StatusEffectInstance(JUMP_BOOST, 250, 3), 0.25f).statusEffect(new StatusEffectInstance(STRENGTH, 150, 3), 0.10f).statusEffect(new StatusEffectInstance(RESISTANCE, 300, 3), 0.25f).hunger(4).saturationModifier(2.5F).build()));

	public static final Item squiggle = new SquiggleItem(new FabricItemSettings());

	public static final Block stripped_fwood_planks = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(1.0f));

	public static final Block stripped_fwood_planksvar = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(1.0f));
	public static final Block stripped_fwood_slab = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).strength(1f));
	public static final Block stripped_fwood_stair = new ModStairsBlock(stripped_fwood_planks.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).strength(1f));

	public static final Block stripped_fwood_door = new DoorBlock(AbstractBlock.Settings.copy(OAK_DOOR).mapColor(stripped_fwood_planks.getDefaultMapColor()).instrument(Instrument.BASS).strength(3.0f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY).strength(1.0f).sounds(BlockSoundGroup.WOOD), ModBlockSetType.FWOOD);

	public static final Block stripped_fwood_gate = new FenceGateBlock(AbstractBlock.Settings.create().mapColor(stripped_fwood_planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).strength(2.0f, 3.0f).burnable(), ModWoodType.STRIPPED_FWOOD);

	public static final Block stripped_fwood_plate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.create().mapColor(fwood_planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(0.5f).burnable().pistonBehavior(PistonBehavior.DESTROY), ModBlockSetType.FWOOD);

	public static final Block stripped_fwood_fence = new FenceBlock(AbstractBlock.Settings.copy(OAK_FENCE_GATE).strength(1.0f));

	public static final Block stripped_fwood_trapdoor = new TrapdoorBlock(AbstractBlock.Settings.create().instrument(Instrument.BASS).strength(1.0f).nonOpaque().burnable(), ModBlockSetType.FWOOD);

	public static final Block stripped_fwood_button = new ButtonBlock(AbstractBlock.Settings.copy(OAK_BUTTON).noCollision().strength(0.5f), ModBlockSetType.FWOOD, 30, true);

	public static final Block squiggleblock = new squiggleblock(AbstractBlock.Settings.create().noCollision().strength(0.1f).nonOpaque().allowsSpawning(fpaore::never).noBlockBreakParticles());


	public static final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("fpaore","custombore"));

	public static final RegistryKey<PlacedFeature> FPVGROUND_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("fpaore","fpvgroundspawn"));


	public static final Item pbradium = new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE));


	public static final Block bradblock = new bradblock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).sounds(ModSounds.BRADBLOCK_BLOCK_SOUNDS).strength(60f).requiresTool());

	public static final Item FWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.FWOOD_BOAT_ID, ModBoats.FWOOD_BOAT_KEY, false);
	public static final Item FWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.FWOOD_CHEST_BOAT_ID, ModBoats.FWOOD_BOAT_KEY, true);
	public static final Item STRIPPED_FWOOD_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.STRIPPED_FWOOD_BOAT_ID, ModBoats.STRIPPED_FWOOD_BOAT_KEY, false);
	public static final Item STRIPPED_FWOOD_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.STRIPPED_FWOOD_CHEST_BOAT_ID, ModBoats.STRIPPED_FWOOD_BOAT_KEY, true);
	private static boolean always(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}

	/**
	 * A shortcut to always return {@code false} a context predicate, used as
	 * {@code settings.solidBlock(Blocks::never)}.
	 */
	private static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}
	private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/**
	 * A shortcut to always return {@code true} in a typed context predicate with an
	 * {@link EntityType}, used like {@code settings.allowSpawning(Blocks::always)}.
	 */
	private static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return true;
	}

	private static EntityType<ThrowableBlobEntity> registerThrowableBlob(final String id) {
		return Registry.register(Registries.ENTITY_TYPE, new Identifier(fpaore.MOD_ID, id),
				FabricEntityTypeBuilder.<ThrowableBlobEntity>create(SpawnGroup.MISC, ThrowableBlobEntity::new)
						.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
						.trackRangeBlocks(4)
						.trackedUpdateRate(10)
						.build());
	}
	public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(fpaore.MOD_ID, "mod_sign_entity"),
			FabricBlockEntityTypeBuilder.create(ModSignBlockEntity::new,
					fpaore.fwood_sign, fpaore.fwood_wall_sign).build());
	public static final BlockEntityType<StrippedModSignBlockEntity> STRIPPED_MOD_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(fpaore.MOD_ID, "stripped_mod_sign_entity"),
			FabricBlockEntityTypeBuilder.create(StrippedModSignBlockEntity::new,
					fpaore.stripped_fwood_sign, fpaore.stripped_fwood_wall_sign).build());
	public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			new Identifier(fpaore.MOD_ID, "mod_hanging_sign_entity"),
			FabricBlockEntityTypeBuilder.create(ModHangingSignBlockEntity::new,
					fpaore.fwood_hanging_sign, fpaore.fwood_hanging_wall_sign).build(null));
	public static final Item BRADIUM_HELMET = registerItem("bradium_helmet",
			new ModArmorItem(ModArmorMaterials.BRADIUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item BRADIUM_CHESTPLATE = registerItem("bradium_chestplate",
			new ModArmorItem(ModArmorMaterials.BRADIUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item BRADIUM_LEGGINGS = registerItem("bradium_leggings",
			new ModArmorItem(ModArmorMaterials.BRADIUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item BRADIUM_BOOTS = registerItem("bradium_boots",
			new ModArmorItem(ModArmorMaterials.BRADIUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	private static Block registerBlockWithoutItem(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(fpaore.MOD_ID, name), block);
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(fpaore.MOD_ID, name), item);
	}
	private static Block registerBlockWithoutBlockItem(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(fpaore.MOD_ID, name), block);
	}



	public static final DefaultParticleType SQUIGGLETHING = FabricParticleTypes.simple();
	public static final DefaultParticleType SQUIGGLETHINGM = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {

		ModNetworkRegisters.registerC2SPackets();
		ModLootTableModifiers.modifyLootTables();
		ModItemGroup.registerItemGroups();
		ModWorldGeneration.generateModWorldGen();
		ServerPlayerEvents.COPY_FROM.register(new PlayerCopyHandler());
		dispenser.registerDefaults();

		ModFlammableBlockRegistry.registerFlammableBlocks();
		StrippableBlockRegistry.register(fpaore.fwood_log, fpaore.stripped_fwood_log);
		StrippableBlockRegistry.register(fpaore.fwood_wood, fpaore.stripped_fwood_wood);
		StrippableBlockRegistry.register(fpaore.packed_fpvground, fpaore.packed_fpvground_column);
		StrippableBlockRegistry.register(fpaore.packed_fpvground_column, fpaore.packed_fpvground);
		StrippableBlockRegistry.register(fpaore.stripped_fwood_planks, fpaore.stripped_fwood_planksvar);
		ModEffects.registerEffects();

		Registry.register(Registries.PARTICLE_TYPE, new Identifier("fpaore", "squigglething"), SQUIGGLETHING);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier("fpaore", "squigglethingm"), SQUIGGLETHINGM);

		Registry.register(Registries.BLOCK, new Identifier("fpaore", "bradore"), BradOre);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "bradoredsl"), bradoredsl);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "bradblock"), bradblock);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "spring"), spring);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "sketch"), sketch);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagrounda"), fpagrounda);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "toughf"), toughf);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "grasspop"), grasspop);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fgrass"), fgrass);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "grasblockbutgood"), grasblockbutgood);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "coursegrasblockbutgood"), coursegrasblockbutgood);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "inkblot"), inkblot);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "mayorglass"), mayorglass);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagroundatrimed"), fpagroundatrimed);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagroundatrimed_slab"), fpagroundatrimed_slab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fcactus"), fcactus);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagrounda_slab"), fpagrounda_SLAB);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagrounda_stair"), fpagrounda_stair);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagroundatrimed_stair"), fpagroundatrimed_stair);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "toughf_wall"), toughf_wall);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagrounda_verticalslab"), fpagrounda_verticalslab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpagroundatrimed_verticalslab"), fpagroundatrimed_verticalslab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fpvground"), fpvground);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "packed_fpvground"), packed_fpvground);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "packed_fpvground_column"), packed_fpvground_column);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "sandstonebedrock"), sandstonebedrock);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_log"), fwood_log);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_wood"), fwood_wood);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_log"), stripped_fwood_log);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_wood"), stripped_fwood_wood);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_planks"), fwood_planks);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_leaves"), fwood_leaves);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_sapling"), fwood_sapling);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_stair"), fwood_stair);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_slab"), fwood_slab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_door"), fwood_door);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_gate"), fwood_gate);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_plate"), fwood_plate);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_fence"), fwood_fence);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_trapdoor"), fwood_trapdoor);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fwood_button"), fwood_button);

		Registry.register(Registries.BLOCK, new Identifier("fpaore", "spike"), spike);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "fchiseled_sandstone"), fchiseled_sandstone);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "smoothsandstone_verticalslab"), smoothsandstone_verticalslab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "inkblot_wall"), inkblot_wall);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_stair"), stripped_fwood_stair);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_slab"), stripped_fwood_slab);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_door"), stripped_fwood_door);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_gate"), stripped_fwood_gate);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_plate"), stripped_fwood_plate);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_fence"), stripped_fwood_fence);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_trapdoor"), stripped_fwood_trapdoor);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_button"), stripped_fwood_button);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_planks"), stripped_fwood_planks);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "stripped_fwood_planksvar"), stripped_fwood_planksvar);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "squiggleblock"), squiggleblock);
		Registry.register(Registries.BLOCK, new Identifier("fpaore", "spikebuth"), spikebuth);

		Registry.register(Registries.ITEM, new Identifier("fpaore", "bradore"), new BlockItem(BradOre, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "bradoredsl"), new BlockItem(bradoredsl, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "bradblock"), new BlockItem(bradblock, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "spring"), new BlockItem(spring, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagrounda"), new BlockItem(fpagrounda, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "toughf"), new BlockItem(toughf, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "grasspop"), new BlockItem(grasspop, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "sketch"), new BlockItem(sketch, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fgrass"), new BlockItem(fgrass, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "grasblockbutgood"), new BlockItem(grasblockbutgood, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "coursegrasblockbutgood"), new BlockItem(coursegrasblockbutgood, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "inkblot"), new BlockItem(inkblot, new FabricItemSettings()));

		Registry.register(Registries.ITEM, new Identifier("fpaore", "mayorglass"), new BlockItem(mayorglass, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagroundatrimed"), new BlockItem(fpagroundatrimed, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fcactus"), new BlockItem(fcactus, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagrounda_slab"), new BlockItem(fpagrounda_SLAB, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagroundatrimed_slab"), new BlockItem(fpagroundatrimed_slab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagroundatrimed_stair"), new BlockItem(fpagroundatrimed_stair, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagrounda_stair"), new BlockItem(fpagrounda_stair, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "toughf_wall"), new BlockItem(toughf_wall, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagrounda_verticalslab"), new BlockItem(fpagrounda_verticalslab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpagroundatrimed_verticalslab"), new BlockItem(fpagroundatrimed_verticalslab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fpvground"), new BlockItem(fpvground, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_log"), new BlockItem(fwood_log, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_wood"), new BlockItem(fwood_wood, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_log"), new BlockItem(stripped_fwood_log, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_wood"), new BlockItem(stripped_fwood_wood, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_planks"), new BlockItem(fwood_planks, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_leaves"), new BlockItem(fwood_leaves, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_sapling"), new BlockItem(fwood_sapling, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_stair"), new BlockItem(fwood_stair, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_slab"), new BlockItem(fwood_slab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_door"), new BlockItem(fwood_door, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_gate"), new BlockItem(fwood_gate, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_plate"), new BlockItem(fwood_plate, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_fence"), new BlockItem(fwood_fence, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_button"), new BlockItem(fwood_button, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fwood_trapdoor"), new BlockItem(fwood_trapdoor, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "packed_fpvground"), new BlockItem(packed_fpvground, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "packed_fpvground_column"), new BlockItem(packed_fpvground_column, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "spike"), new BlockItem(spike, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "fchiseled_sandstone"), new BlockItem(fchiseled_sandstone, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "smoothsandstone_verticalslab"), new BlockItem(smoothsandstone_verticalslab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "sandstonebedrock"), new BlockItem(sandstonebedrock, new FabricItemSettings()));

		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_stair"), new BlockItem(stripped_fwood_stair, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_slab"), new BlockItem(stripped_fwood_slab, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_door"), new BlockItem(stripped_fwood_door, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_gate"), new BlockItem(stripped_fwood_gate, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_plate"), new BlockItem(stripped_fwood_plate, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_fence"), new BlockItem(stripped_fwood_fence, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_button"), new BlockItem(stripped_fwood_button, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_trapdoor"), new BlockItem(stripped_fwood_trapdoor, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_planks"), new BlockItem(stripped_fwood_planks, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "stripped_fwood_planksvar"), new BlockItem(stripped_fwood_planksvar, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "squiggleblock"), new BlockItem(squiggleblock, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("fpaore", "spikebuth"), new BlockItem(spikebuth, new FabricItemSettings()));


		Registry.register(Registries.ITEM, new Identifier("fpaore", "bradium"), bradium);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "icecream"), icecream);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "inkblob"), inkblob);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "squiggle"), squiggle);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "mayor_of_undying"), mayor_of_undying);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "pencilsword"), pencilsword);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "lounge_inside_music_disk"), lounge_inside_music_disk);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "angry_baddie_music_disk"), angry_baddie_music_disk);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "mayor_theme_music_disk"), mayor_theme_music_disk);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "level_four_music_disk"), level_four_music_disk);
		Registry.register(Registries.ITEM, new Identifier("fpaore", "pbradium"), pbradium);





		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, FPVGROUND_PLACED_KEY);

		ModBoats.registerBoats();

	}
}