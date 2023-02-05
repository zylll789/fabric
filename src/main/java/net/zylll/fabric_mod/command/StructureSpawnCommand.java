package net.zylll.fabric_mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.command.argument.PosArgument;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.random.AtomicSimpleRandom;
import net.minecraft.world.gen.random.ChunkRandom;
import net.minecraft.world.gen.random.RandomSeed;
import net.minecraft.world.gen.random.Xoroshiro128PlusPlusRandom;
import net.zylll.fabric_mod.FabricMod;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StructureSpawnCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(CommandManager.literal("structure").requires(c->c.hasPermissionLevel(4))
                .then(CommandManager.literal("spawn").requires(c->c.hasPermissionLevel(4))
                .then(CommandManager.argument("location", Vec3ArgumentType.vec3())
                .then(CommandManager.argument("start_pool", IdentifierArgumentType.identifier())
                .suggests((ctx, sb) -> CommandSource.suggestIdentifiers(startPoolSuggestions(ctx), sb))
                .executes(StructureSpawnCommand::spawnStructure)))));
    }

    private static int spawnStructure(CommandContext<ServerCommandSource> context) {
        ServerWorld serverWorld = context.getSource().getWorld();
        PosArgument posArgument = Vec3ArgumentType.getPosArgument(context,"location");
        BlockPos pos = posArgument.toAbsoluteBlockPos(context.getSource());
        Identifier poolID = context.getArgument("start_pool", Identifier.class);
        StructurePool structurePool = serverWorld.getRegistryManager().get(Registry.STRUCTURE_POOL_KEY).get(poolID);
        if(structurePool == null || structurePool.getElementCount() == 0){
            String err = poolID + " does not exist or is empty";
            FabricMod.LOGGER.error(err);
            throw new CommandException(new LiteralText(err));
        }
        StructurePoolFeatureConfig newConfig = new StructurePoolFeatureConfig(RegistryEntry.of(structurePool), 10);

        StructureGeneratorFactory.Context<StructurePoolFeatureConfig> newContext = new StructureGeneratorFactory.Context<>(
                serverWorld.getChunkManager().getChunkGenerator(), serverWorld.getChunkManager().getChunkGenerator().getBiomeSource(),
                serverWorld.getSeed(), new ChunkPos(pos), newConfig, serverWorld, (biomeRegistryEntry -> true),
                serverWorld.getStructureManager(), serverWorld.getRegistryManager());
        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> piecesGenerator = StructurePoolBasedGenerator.generate(
                newContext, PoolStructurePiece::new, pos, false, false);
        if(piecesGenerator.isPresent()){
            StructurePiecesCollector structurePiecesCollector = new StructurePiecesCollector();
            piecesGenerator.get().generatePieces(
                    structurePiecesCollector,new StructurePiecesGenerator.Context<>(
                            newContext.config(),newContext.chunkGenerator(),newContext.structureManager(),
                            newContext.chunkPos(),newContext.world(),new ChunkRandom(new AtomicSimpleRandom(0L)),newContext.seed()));
            ChunkRandom random = new ChunkRandom(new Xoroshiro128PlusPlusRandom(RandomSeed.getSeed()));
            long i = random.setPopulationSeed(newContext.seed(), pos.getX(), pos.getZ());
            random.setDecoratorSeed(i, 0, 0);
            List<StructurePiece> structurePieceList = structurePiecesCollector.toList().pieces();
            structurePieceList.forEach(structurePiece -> generatePiece(serverWorld,newContext,random, pos,structurePiece));
            if(structurePieceList.isEmpty()){
                String err = poolID + " Template pool spawned no pieces.";
                FabricMod.LOGGER.error(err);
                throw new CommandException(new LiteralText(err));
            }
        }else {
            String err = poolID + " Template pool spawned no pieces.";
            FabricMod.LOGGER.error(err);
            throw new CommandException(new LiteralText(err));
        }
        return 1;
    }

    private static Set<Identifier> startPoolSuggestions(CommandContext<ServerCommandSource> cs) {
        return cs.getSource().getWorld().getRegistryManager().get(Registry.STRUCTURE_POOL_KEY).getIds();
    }

    private static void generatePiece(ServerWorld level, StructureGeneratorFactory.Context<StructurePoolFeatureConfig> newContext, ChunkRandom random, BlockPos finalCenterPos, StructurePiece piece) {
        piece.generate(
                level,
                level.getStructureAccessor(),
                newContext.chunkGenerator(),
                random,
                BlockBox.infinite(),
                newContext.chunkPos(),
                finalCenterPos
        );
    }

}
