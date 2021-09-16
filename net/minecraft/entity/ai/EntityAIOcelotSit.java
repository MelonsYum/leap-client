/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBed;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIOcelotSit extends EntityAIMoveToBlock {
/*     */   private final EntityOcelot field_151493_a;
/*     */   private static final String __OBFID = "CL_00001601";
/*     */   
/*     */   public EntityAIOcelotSit(EntityOcelot p_i45315_1_, double p_i45315_2_) {
/*  20 */     super((EntityCreature)p_i45315_1_, p_i45315_2_, 8);
/*  21 */     this.field_151493_a = p_i45315_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  29 */     return (this.field_151493_a.isTamed() && !this.field_151493_a.isSitting() && super.shouldExecute());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  37 */     return super.continueExecuting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  45 */     super.startExecuting();
/*  46 */     this.field_151493_a.getAISit().setSitting(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  54 */     super.resetTask();
/*  55 */     this.field_151493_a.setSitting(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  63 */     super.updateTask();
/*  64 */     this.field_151493_a.getAISit().setSitting(false);
/*     */     
/*  66 */     if (!func_179487_f()) {
/*     */       
/*  68 */       this.field_151493_a.setSitting(false);
/*     */     }
/*  70 */     else if (!this.field_151493_a.isSitting()) {
/*     */       
/*  72 */       this.field_151493_a.setSitting(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_179488_a(World worldIn, BlockPos p_179488_2_) {
/*  78 */     if (!worldIn.isAirBlock(p_179488_2_.offsetUp()))
/*     */     {
/*  80 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  84 */     IBlockState var3 = worldIn.getBlockState(p_179488_2_);
/*  85 */     Block var4 = var3.getBlock();
/*     */     
/*  87 */     if (var4 == Blocks.chest) {
/*     */       
/*  89 */       TileEntity var5 = worldIn.getTileEntity(p_179488_2_);
/*     */       
/*  91 */       if (var5 instanceof TileEntityChest && ((TileEntityChest)var5).numPlayersUsing < 1)
/*     */       {
/*  93 */         return true;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  98 */       if (var4 == Blocks.lit_furnace)
/*     */       {
/* 100 */         return true;
/*     */       }
/*     */       
/* 103 */       if (var4 == Blocks.bed && var3.getValue((IProperty)BlockBed.PART_PROP) != BlockBed.EnumPartType.HEAD)
/*     */       {
/* 105 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 109 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIOcelotSit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */