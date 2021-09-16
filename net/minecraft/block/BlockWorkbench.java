/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerWorkbench;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.IInteractionObject;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockWorkbench
/*    */   extends Block
/*    */ {
/*    */   private static final String __OBFID = "CL_00000221";
/*    */   
/*    */   protected BlockWorkbench() {
/* 24 */     super(Material.wood);
/* 25 */     setCreativeTab(CreativeTabs.tabDecorations);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 30 */     if (worldIn.isRemote)
/*    */     {
/* 32 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 36 */     playerIn.displayGui(new InterfaceCraftingTable(worldIn, pos));
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class InterfaceCraftingTable
/*    */     implements IInteractionObject
/*    */   {
/*    */     private final World world;
/*    */     private final BlockPos position;
/*    */     private static final String __OBFID = "CL_00002127";
/*    */     
/*    */     public InterfaceCraftingTable(World worldIn, BlockPos p_i45730_2_) {
/* 49 */       this.world = worldIn;
/* 50 */       this.position = p_i45730_2_;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 55 */       return null;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean hasCustomName() {
/* 60 */       return false;
/*    */     }
/*    */ 
/*    */     
/*    */     public IChatComponent getDisplayName() {
/* 65 */       return (IChatComponent)new ChatComponentTranslation(String.valueOf(Blocks.crafting_table.getUnlocalizedName()) + ".name", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/*    */     public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 70 */       return (Container)new ContainerWorkbench(playerInventory, this.world, this.position);
/*    */     }
/*    */ 
/*    */     
/*    */     public String getGuiID() {
/* 75 */       return "minecraft:crafting_table";
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */