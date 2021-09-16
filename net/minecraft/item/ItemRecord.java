/*    */ package net.minecraft.item;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.BlockJukebox;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemRecord extends Item {
/* 18 */   private static final Map field_150928_b = Maps.newHashMap();
/*    */   
/*    */   public final String recordName;
/*    */   
/*    */   private static final String __OBFID = "CL_00000057";
/*    */ 
/*    */   
/*    */   protected ItemRecord(String p_i45350_1_) {
/* 26 */     this.recordName = p_i45350_1_;
/* 27 */     this.maxStackSize = 1;
/* 28 */     setCreativeTab(CreativeTabs.tabMisc);
/* 29 */     field_150928_b.put("records." + p_i45350_1_, this);
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
/* 40 */     IBlockState var9 = worldIn.getBlockState(pos);
/*    */     
/* 42 */     if (var9.getBlock() == Blocks.jukebox && !((Boolean)var9.getValue((IProperty)BlockJukebox.HAS_RECORD)).booleanValue()) {
/*    */       
/* 44 */       if (worldIn.isRemote)
/*    */       {
/* 46 */         return true;
/*    */       }
/*    */ 
/*    */       
/* 50 */       ((BlockJukebox)Blocks.jukebox).insertRecord(worldIn, pos, var9, stack);
/* 51 */       worldIn.playAuxSFXAtEntity(null, 1005, pos, Item.getIdFromItem(this));
/* 52 */       stack.stackSize--;
/* 53 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/* 70 */     tooltip.add(getRecordNameLocal());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRecordNameLocal() {
/* 75 */     return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumRarity getRarity(ItemStack stack) {
/* 83 */     return EnumRarity.RARE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ItemRecord getRecord(String p_150926_0_) {
/* 91 */     return (ItemRecord)field_150928_b.get(p_150926_0_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */