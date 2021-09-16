/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityMinecart;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityMinecartMobSpawner
/*    */   extends EntityMinecart {
/* 14 */   private final MobSpawnerBaseLogic mobSpawnerLogic = new MobSpawnerBaseLogic()
/*    */     {
/*    */       private static final String __OBFID = "CL_00001679";
/*    */       
/*    */       public void func_98267_a(int p_98267_1_) {
/* 19 */         EntityMinecartMobSpawner.this.worldObj.setEntityState((Entity)EntityMinecartMobSpawner.this, (byte)p_98267_1_);
/*    */       }
/*    */       
/*    */       public World getSpawnerWorld() {
/* 23 */         return EntityMinecartMobSpawner.this.worldObj;
/*    */       }
/*    */       
/*    */       public BlockPos func_177221_b() {
/* 27 */         return new BlockPos((Entity)EntityMinecartMobSpawner.this);
/*    */       }
/*    */     };
/*    */   
/*    */   private static final String __OBFID = "CL_00001678";
/*    */   
/*    */   public EntityMinecartMobSpawner(World worldIn) {
/* 34 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartMobSpawner(World worldIn, double p_i1726_2_, double p_i1726_4_, double p_i1726_6_) {
/* 39 */     super(worldIn, p_i1726_2_, p_i1726_4_, p_i1726_6_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecart.EnumMinecartType func_180456_s() {
/* 44 */     return EntityMinecart.EnumMinecartType.SPAWNER;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState func_180457_u() {
/* 49 */     return Blocks.mob_spawner.getDefaultState();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 57 */     super.readEntityFromNBT(tagCompund);
/* 58 */     this.mobSpawnerLogic.readFromNBT(tagCompund);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 66 */     super.writeEntityToNBT(tagCompound);
/* 67 */     this.mobSpawnerLogic.writeToNBT(tagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleHealthUpdate(byte p_70103_1_) {
/* 72 */     this.mobSpawnerLogic.setDelayToMin(p_70103_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 80 */     super.onUpdate();
/* 81 */     this.mobSpawnerLogic.updateSpawner();
/*    */   }
/*    */ 
/*    */   
/*    */   public MobSpawnerBaseLogic func_98039_d() {
/* 86 */     return this.mobSpawnerLogic;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityMinecartMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */