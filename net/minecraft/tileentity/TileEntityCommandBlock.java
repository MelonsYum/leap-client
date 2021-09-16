/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.command.CommandResultStats;
/*    */ import net.minecraft.command.server.CommandBlockLogic;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class TileEntityCommandBlock
/*    */   extends TileEntity {
/* 16 */   private final CommandBlockLogic field_145994_a = new CommandBlockLogic()
/*    */     {
/*    */       private static final String __OBFID = "CL_00000348";
/*    */       
/*    */       public BlockPos getPosition() {
/* 21 */         return TileEntityCommandBlock.this.pos;
/*    */       }
/*    */       
/*    */       public Vec3 getPositionVector() {
/* 25 */         return new Vec3(TileEntityCommandBlock.this.pos.getX() + 0.5D, TileEntityCommandBlock.this.pos.getY() + 0.5D, TileEntityCommandBlock.this.pos.getZ() + 0.5D);
/*    */       }
/*    */       
/*    */       public World getEntityWorld() {
/* 29 */         return TileEntityCommandBlock.this.getWorld();
/*    */       }
/*    */       
/*    */       public void setCommand(String p_145752_1_) {
/* 33 */         super.setCommand(p_145752_1_);
/* 34 */         TileEntityCommandBlock.this.markDirty();
/*    */       }
/*    */       
/*    */       public void func_145756_e() {
/* 38 */         TileEntityCommandBlock.this.getWorld().markBlockForUpdate(TileEntityCommandBlock.this.pos);
/*    */       }
/*    */       
/*    */       public int func_145751_f() {
/* 42 */         return 0;
/*    */       }
/*    */       
/*    */       public void func_145757_a(ByteBuf p_145757_1_) {
/* 46 */         p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getX());
/* 47 */         p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getY());
/* 48 */         p_145757_1_.writeInt(TileEntityCommandBlock.this.pos.getZ());
/*    */       }
/*    */       
/*    */       public Entity getCommandSenderEntity() {
/* 52 */         return null;
/*    */       }
/*    */     };
/*    */   
/*    */   private static final String __OBFID = "CL_00000347";
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 59 */     super.writeToNBT(compound);
/* 60 */     this.field_145994_a.writeDataToNBT(compound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 65 */     super.readFromNBT(compound);
/* 66 */     this.field_145994_a.readDataFromNBT(compound);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 74 */     NBTTagCompound var1 = new NBTTagCompound();
/* 75 */     writeToNBT(var1);
/* 76 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 2, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandBlockLogic getCommandBlockLogic() {
/* 81 */     return this.field_145994_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandResultStats func_175124_c() {
/* 86 */     return this.field_145994_a.func_175572_n();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */