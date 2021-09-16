/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class TileEntityMobSpawner
/*    */   extends TileEntity implements IUpdatePlayerListBox {
/* 13 */   private final MobSpawnerBaseLogic field_145882_a = new MobSpawnerBaseLogic()
/*    */     {
/*    */       private static final String __OBFID = "CL_00000361";
/*    */       
/*    */       public void func_98267_a(int p_98267_1_) {
/* 18 */         TileEntityMobSpawner.this.worldObj.addBlockEvent(TileEntityMobSpawner.this.pos, Blocks.mob_spawner, p_98267_1_, 0);
/*    */       }
/*    */       
/*    */       public World getSpawnerWorld() {
/* 22 */         return TileEntityMobSpawner.this.worldObj;
/*    */       }
/*    */       
/*    */       public BlockPos func_177221_b() {
/* 26 */         return TileEntityMobSpawner.this.pos;
/*    */       }
/*    */       
/*    */       public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_) {
/* 30 */         super.setRandomEntity(p_98277_1_);
/*    */         
/* 32 */         if (getSpawnerWorld() != null)
/*    */         {
/* 34 */           getSpawnerWorld().markBlockForUpdate(TileEntityMobSpawner.this.pos);
/*    */         }
/*    */       }
/*    */     };
/*    */   
/*    */   private static final String __OBFID = "CL_00000360";
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 42 */     super.readFromNBT(compound);
/* 43 */     this.field_145882_a.readFromNBT(compound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 48 */     super.writeToNBT(compound);
/* 49 */     this.field_145882_a.writeToNBT(compound);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 57 */     this.field_145882_a.updateSpawner();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 65 */     NBTTagCompound var1 = new NBTTagCompound();
/* 66 */     writeToNBT(var1);
/* 67 */     var1.removeTag("SpawnPotentials");
/* 68 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 1, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean receiveClientEvent(int id, int type) {
/* 73 */     return this.field_145882_a.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
/*    */   }
/*    */ 
/*    */   
/*    */   public MobSpawnerBaseLogic getSpawnerBaseLogic() {
/* 78 */     return this.field_145882_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */