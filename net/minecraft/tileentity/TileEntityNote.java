/*    */ package net.minecraft.tileentity;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityNote
/*    */   extends TileEntity
/*    */ {
/*    */   public byte note;
/*    */   public boolean previousRedstoneState;
/*    */   private static final String __OBFID = "CL_00000362";
/*    */   
/*    */   public void writeToNBT(NBTTagCompound compound) {
/* 21 */     super.writeToNBT(compound);
/* 22 */     compound.setByte("note", this.note);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound compound) {
/* 27 */     super.readFromNBT(compound);
/* 28 */     this.note = compound.getByte("note");
/* 29 */     this.note = (byte)MathHelper.clamp_int(this.note, 0, 24);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void changePitch() {
/* 37 */     this.note = (byte)((this.note + 1) % 25);
/* 38 */     markDirty();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_175108_a(World worldIn, BlockPos p_175108_2_) {
/* 43 */     if (worldIn.getBlockState(p_175108_2_.offsetUp()).getBlock().getMaterial() == Material.air) {
/*    */       
/* 45 */       Material var3 = worldIn.getBlockState(p_175108_2_.offsetDown()).getBlock().getMaterial();
/* 46 */       byte var4 = 0;
/*    */       
/* 48 */       if (var3 == Material.rock)
/*    */       {
/* 50 */         var4 = 1;
/*    */       }
/*    */       
/* 53 */       if (var3 == Material.sand)
/*    */       {
/* 55 */         var4 = 2;
/*    */       }
/*    */       
/* 58 */       if (var3 == Material.glass)
/*    */       {
/* 60 */         var4 = 3;
/*    */       }
/*    */       
/* 63 */       if (var3 == Material.wood)
/*    */       {
/* 65 */         var4 = 4;
/*    */       }
/*    */       
/* 68 */       worldIn.addBlockEvent(p_175108_2_, Blocks.noteblock, var4, this.note);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityNote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */