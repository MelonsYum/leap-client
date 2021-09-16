/*    */ package net.minecraft.entity.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockChest;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityMinecartChest
/*    */   extends EntityMinecartContainer {
/*    */   public EntityMinecartChest(World worldIn) {
/* 21 */     super(worldIn);
/*    */   }
/*    */   private static final String __OBFID = "CL_00001671";
/*    */   
/*    */   public EntityMinecartChest(World worldIn, double p_i1715_2_, double p_i1715_4_, double p_i1715_6_) {
/* 26 */     super(worldIn, p_i1715_2_, p_i1715_4_, p_i1715_6_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void killMinecart(DamageSource p_94095_1_) {
/* 31 */     super.killMinecart(p_94095_1_);
/* 32 */     dropItemWithOffset(Item.getItemFromBlock((Block)Blocks.chest), 1, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSizeInventory() {
/* 40 */     return 27;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecart.EnumMinecartType func_180456_s() {
/* 45 */     return EntityMinecart.EnumMinecartType.CHEST;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState func_180457_u() {
/* 50 */     return Blocks.chest.getDefaultState().withProperty((IProperty)BlockChest.FACING_PROP, (Comparable)EnumFacing.NORTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDefaultDisplayTileOffset() {
/* 55 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGuiID() {
/* 60 */     return "minecraft:chest";
/*    */   }
/*    */ 
/*    */   
/*    */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 65 */     return (Container)new ContainerChest((IInventory)playerInventory, (IInventory)this, playerIn);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */