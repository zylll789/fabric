//from command structures

package net.zylll.fabric_mod.mixin;

import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.registry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SinglePoolElement.class)
public interface SinglePoolElementAccessor {

    @Accessor("processors")
    RegistryEntry<StructureProcessorList> getProcessors();

    @Mutable
    @Accessor("processors")
    void setProcessors(RegistryEntry<StructureProcessorList> processors);

}
