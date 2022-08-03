package net.zylll.fabric_mod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class PoopCube extends MobEntity implements Monster {
    private static final TrackedData<Integer> POOP_SIZE;
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    public PoopCube(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new PoopCube.PoopCubeMoveControl(this);
    }

    public static Builder createMobAttributes(){
        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add
                (EntityAttributes.GENERIC_ATTACK_KNOCKBACK).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D);
    }

    protected void initGoals(){
        this.goalSelector.add(1, new SwimmingGoal(this));
        this.goalSelector.add(2, new FaceTowardTargetGoal(this));
        this.goalSelector.add(3, new RandomLookGoal(this));
        this.goalSelector.add(5, new MoveGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, (Predicate<LivingEntity>) livingEntity -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0D));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(POOP_SIZE, 1);
    }

    protected void setSize(int size, boolean heal) {
        int i = MathHelper.clamp(size, 1, 127);
        this.dataTracker.set(POOP_SIZE, i);
        this.refreshPosition();
        this.calculateDimensions();
        if (heal) {
            this.setHealth(this.getMaxHealth());
        }
        this.experiencePoints = i;
    }

    public int getSize() {
        return this.dataTracker.get(POOP_SIZE);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Size", this.getSize() - 1);
        nbt.putBoolean("wasOnGround", this.onGroundLastTick);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setSize(nbt.getInt("Size") + 1, false);
        super.readCustomDataFromNbt(nbt);
        this.onGroundLastTick = nbt.getBoolean("wasOnGround");
    }

    //isSmall

    protected ParticleEffect getParticles() {
        return ParticleTypes.ITEM_SLIME;
    }

    protected boolean isDisallowedInPeaceful(){return false;}

    public void tick() {
        this.stretch += (this.targetStretch - this.stretch) * 0.5F;
        this.lastStretch = this.stretch;
        super.tick();
        if (this.onGround && !this.onGroundLastTick) {
            int i = this.getSize();
            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 6.2831855F;
                float g = this.random.nextFloat() * 0.5F + 0.5F;
                float h = MathHelper.sin(f) * (float)i * 0.5F * g;
                float k = MathHelper.cos(f) * (float)i * 0.5F * g;
                this.world.addParticle(this.getParticles(), this.getX() + (double)h, this.getY(), this.getZ() + (double)k, 0.0D, 0.0D, 0.0D);
            }
            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetStretch = -0.5F;
        } else if (!this.onGround && this.onGroundLastTick) {
            this.targetStretch = 1.0F;
        }
        this.onGroundLastTick = this.onGround;
        this.updateStretch();
    }

    protected void updateStretch() {
        this.targetStretch *= 0.6F;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(20) + 10;
    }

    public void calculateDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.calculateDimensions();
        this.setPosition(d, e, f);
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (POOP_SIZE.equals(data)) {
            this.calculateDimensions();
            this.setYaw(this.headYaw);
            this.bodyYaw = this.headYaw;
            if (this.isTouchingWater() && this.random.nextInt(20) == 0) {
                this.onSwimmingStart();
            }
        }
        super.onTrackedDataSet(data);
    }

    public EntityType<?> getType() {
        return super.getType();
    }

    //remove
    public void pushAwayFrom(Entity entity) {
        super.pushAwayFrom(entity);
        if (entity instanceof IronGolemEntity && this.canAttack()) {
            this.damage((LivingEntity)entity);
        }
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (this.canAttack()) {
            this.damage(player);
        }
    }

    protected void damage(LivingEntity target) {
        if (this.isAlive()) {
            int i = this.getSize();
            World world = target.getWorld();
            if (this.squaredDistanceTo(target) < 1.0D * (double)i * 1.0D * (double)i && this.canSee(target) && target.damage(DamageSource.mob(this), this.getDamageAmount(world))) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.applyDamageEffects(this, target);
            }
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.625F * dimensions.height;
    }

    protected boolean canAttack() {
        return true;
    }

    protected static float getDifficulty(World world){
        Difficulty difficulty = world.getDifficulty();
        return switch (difficulty) {
            case PEACEFUL -> 1.0F;
            case EASY -> 1.5F;
            case NORMAL -> 2.0F;
            case HARD -> 2.5F;
            default -> 3.0F;
        };
    }

    protected float getDamageAmount(World world) {
        return 4.0F*PoopCube.getDifficulty(world);
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH_SMALL;
    }

    protected Identifier getLootTableId() {
        return this.getType().getLootTableId();
    }
    //canSpawn

    protected float getSoundVolume() {
        return 0.4F;
    }

    public int getMaxLookPitchChange() {
        return 0;
    }

    protected boolean makesJumpSound() {
        return true;
    }

    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, this.getJumpVelocity(), vec3d.z);
        this.velocityDirty = true;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    float getJumpSoundPitch() {
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.4F;
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP_SMALL;
    }

    public EntityDimensions getDimensions(EntityPose pose) {
        return super.getDimensions(pose).scaled(0.255F * (float)this.getSize());
    }

    static {
        POOP_SIZE = DataTracker.registerData(PoopCube.class, TrackedDataHandlerRegistry.INTEGER);
    }

    private static class PoopCubeMoveControl extends MoveControl {
        private float targetYaw;
        private int ticksUntilJump;
        private final PoopCube poopCube;
        private boolean jumpOften;

        public PoopCubeMoveControl(PoopCube poopCube) {
            super(poopCube);
            this.poopCube = poopCube;
            this.targetYaw = 180.0F * poopCube.getYaw() / 3.1415927F;
        }

        public void look(float targetYaw, boolean jumpOften) {
            this.targetYaw = targetYaw;
            this.jumpOften = jumpOften;
        }
        public void move(double speed) {
            this.speed = speed;
            this.state = State.MOVE_TO;
        }

        public void tick(){
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0F));
            this.entity.headYaw = this.entity.getYaw();
            this.entity.bodyYaw = this.entity.getYaw();
            if (this.state != State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0F);
            } else {
                this.state = State.WAIT;
                if (this.entity.isOnGround()) {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {
                        this.ticksUntilJump = this.poopCube.getTicksUntilNextJump();
                        if (this.jumpOften) {
                            this.ticksUntilJump /= 3;
                        }

                        this.poopCube.getJumpControl().setActive();
                        if (this.poopCube.makesJumpSound()) {
                            this.poopCube.playSound(this.poopCube.getJumpSound(), this.poopCube.getSoundVolume(), this.poopCube.getJumpSoundPitch());
                        }
                    } else {
                        this.poopCube.sidewaysSpeed = 0.0F;
                        this.poopCube.forwardSpeed = 0.0F;
                        this.entity.setMovementSpeed(0.0F);
                    }
                } else {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                }

            }
        }

    }

    static class SwimmingGoal extends Goal{
        private final PoopCube poopCube;

        public SwimmingGoal(PoopCube poopCube){
            this.poopCube = poopCube;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
            poopCube.getNavigation().setCanSwim(true);
        }

        public boolean canStart() {
            return (this.poopCube.isTouchingWater() || this.poopCube.isInLava()) && this.poopCube.getMoveControl() instanceof PoopCube.PoopCubeMoveControl;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick(){
            if (this.poopCube.getRandom().nextFloat() < 0.8F){
                this.poopCube.getJumpControl().setActive();
            }
            ((PoopCube.PoopCubeMoveControl)this.poopCube.getMoveControl()).move(1.2D);
        }
    }

    static class FaceTowardTargetGoal extends Goal {
        private final PoopCube poopCube;
        private int ticksLeft;

        public FaceTowardTargetGoal(PoopCube poopCube) {
            this.poopCube = poopCube;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.poopCube.getTarget();
            if (livingEntity == null) {
                return false;
            } else {
                return !this.poopCube.canTarget(livingEntity) ? false : this.poopCube.getMoveControl() instanceof PoopCube.PoopCubeMoveControl;
            }
        }

        public void start() {
            this.ticksLeft = toGoalTicks(300);
            super.start();
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity = this.poopCube.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!this.poopCube.canTarget(livingEntity)) {
                return false;
            } else {
                return --this.ticksLeft > 0;
            }
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingEntity = this.poopCube.getTarget();
            if (livingEntity != null) {
                this.poopCube.lookAtEntity(livingEntity, 10.0F, 10.0F);
            }
            ((PoopCube.PoopCubeMoveControl)this.poopCube.getMoveControl()).look(this.poopCube.getYaw(), this.poopCube.canAttack());
        }
    }

    static class RandomLookGoal extends Goal {
        private final PoopCube poopCube;
        private float targetYaw;
        private int timer;

        public RandomLookGoal(PoopCube poopCube) {
            this.poopCube = poopCube;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            return this.poopCube.getTarget() == null &&
                    (this.poopCube.onGround || this.poopCube.isTouchingWater() || this.poopCube.isInLava() || this.poopCube.hasStatusEffect(StatusEffects.LEVITATION)) &&
                    this.poopCube.getMoveControl() instanceof PoopCube.PoopCubeMoveControl;
        }

        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.getTickCount(40 + this.poopCube.getRandom().nextInt(60));
                this.targetYaw = (float)this.poopCube.getRandom().nextInt(360);
            }

            ((PoopCube.PoopCubeMoveControl)this.poopCube.getMoveControl()).look(this.targetYaw, false);
        }
    }

    static class MoveGoal extends Goal {
        private final PoopCube poopCube;

        public MoveGoal(PoopCube poopCube) {
            this.poopCube = poopCube;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
        }

        public boolean canStart() {
            return !this.poopCube.hasVehicle();
        }

        public void tick() {
            ((PoopCube.PoopCubeMoveControl)this.poopCube.getMoveControl()).move(1.0D);
        }
    }
}
