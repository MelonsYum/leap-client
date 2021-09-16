/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockSnow;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemSnow
/*    */   extends ItemBlock {
/*    */   private static final String __OBFID = "CL_00000068";
/*    */   
/*    */   public ItemSnow(Block p_i45781_1_) {
/* 17 */     super(p_i45781_1_);
/* 18 */     setMaxDamage(0);
/* 19 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 30 */     if (stack.stackSize == 0)
/*    */     {
/* 32 */       return false;
/*    */     }
/* 34 */     if (!playerIn.func_175151_a(pos, side, stack))
/*    */     {
/* 36 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 40 */     IBlockState var9 = worldIn.getBlockState(pos);
/* 41 */     Block var10 = var9.getBlock();
/*    */     
/* 43 */     if (var10 != this.block && side != EnumFacing.UP) {
/*    */       
/* 45 */       pos = pos.offset(side);
/* 46 */       var9 = worldIn.getBlockState(pos);
/* 47 */       var10 = var9.getBlock();
/*    */     } 
/*    */     
/* 50 */     if (var10 == this.block) {
/*    */       
/* 52 */       int var11 = ((Integer)var9.getValue((IProperty)BlockSnow.LAYERS_PROP)).intValue();
/*    */       
/* 54 */       if (var11 <= 7) {
/*    */         
/* 56 */         IBlockState var12 = var9.withProperty((IProperty)BlockSnow.LAYERS_PROP, Integer.valueOf(var11 + 1));
/*    */         
/* 58 */         if (worldIn.checkNoEntityCollision(this.block.getCollisionBoundingBox(worldIn, pos, var12)) && worldIn.setBlockState(pos, var12, 2)) {
/*    */           
/* 60 */           worldIn.playSoundEffect((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
/* 61 */           stack.stackSize--;
/* 62 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int damage) {
/* 77 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */