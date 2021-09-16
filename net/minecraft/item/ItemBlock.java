/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSnow;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBlock
/*     */   extends Item {
/*     */   protected final Block block;
/*     */   private static final String __OBFID = "CL_00001772";
/*     */   
/*     */   public ItemBlock(Block block) {
/*  24 */     this.block = block;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBlock setUnlocalizedName(String unlocalizedName) {
/*  32 */     super.setUnlocalizedName(unlocalizedName);
/*  33 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  44 */     IBlockState var9 = worldIn.getBlockState(pos);
/*  45 */     Block var10 = var9.getBlock();
/*     */     
/*  47 */     if (var10 == Blocks.snow_layer && ((Integer)var9.getValue((IProperty)BlockSnow.LAYERS_PROP)).intValue() < 1) {
/*     */       
/*  49 */       side = EnumFacing.UP;
/*     */     }
/*  51 */     else if (!var10.isReplaceable(worldIn, pos)) {
/*     */       
/*  53 */       pos = pos.offset(side);
/*     */     } 
/*     */     
/*  56 */     if (stack.stackSize == 0)
/*     */     {
/*  58 */       return false;
/*     */     }
/*  60 */     if (!playerIn.func_175151_a(pos, side, stack))
/*     */     {
/*  62 */       return false;
/*     */     }
/*  64 */     if (pos.getY() == 255 && this.block.getMaterial().isSolid())
/*     */     {
/*  66 */       return false;
/*     */     }
/*  68 */     if (worldIn.canBlockBePlaced(this.block, pos, false, side, null, stack)) {
/*     */       
/*  70 */       int var11 = getMetadata(stack.getMetadata());
/*  71 */       IBlockState var12 = this.block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, var11, (EntityLivingBase)playerIn);
/*     */       
/*  73 */       if (worldIn.setBlockState(pos, var12, 3)) {
/*     */         
/*  75 */         var12 = worldIn.getBlockState(pos);
/*     */         
/*  77 */         if (var12.getBlock() == this.block) {
/*     */           
/*  79 */           setTileEntityNBT(worldIn, pos, stack);
/*  80 */           this.block.onBlockPlacedBy(worldIn, pos, var12, (EntityLivingBase)playerIn, stack);
/*     */         } 
/*     */         
/*  83 */         worldIn.playSoundEffect((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
/*  84 */         stack.stackSize--;
/*     */       } 
/*     */       
/*  87 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean setTileEntityNBT(World worldIn, BlockPos p_179224_1_, ItemStack p_179224_2_) {
/*  97 */     if (p_179224_2_.hasTagCompound() && p_179224_2_.getTagCompound().hasKey("BlockEntityTag", 10)) {
/*     */       
/*  99 */       TileEntity var3 = worldIn.getTileEntity(p_179224_1_);
/*     */       
/* 101 */       if (var3 != null) {
/*     */         
/* 103 */         NBTTagCompound var4 = new NBTTagCompound();
/* 104 */         NBTTagCompound var5 = (NBTTagCompound)var4.copy();
/* 105 */         var3.writeToNBT(var4);
/* 106 */         NBTTagCompound var6 = (NBTTagCompound)p_179224_2_.getTagCompound().getTag("BlockEntityTag");
/* 107 */         var4.merge(var6);
/* 108 */         var4.setInteger("x", p_179224_1_.getX());
/* 109 */         var4.setInteger("y", p_179224_1_.getY());
/* 110 */         var4.setInteger("z", p_179224_1_.getZ());
/*     */         
/* 112 */         if (!var4.equals(var5)) {
/*     */           
/* 114 */           var3.readFromNBT(var4);
/* 115 */           var3.markDirty();
/* 116 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos p_179222_2_, EnumFacing p_179222_3_, EntityPlayer p_179222_4_, ItemStack p_179222_5_) {
/* 126 */     Block var6 = worldIn.getBlockState(p_179222_2_).getBlock();
/*     */     
/* 128 */     if (var6 == Blocks.snow_layer) {
/*     */       
/* 130 */       p_179222_3_ = EnumFacing.UP;
/*     */     }
/* 132 */     else if (!var6.isReplaceable(worldIn, p_179222_2_)) {
/*     */       
/* 134 */       p_179222_2_ = p_179222_2_.offset(p_179222_3_);
/*     */     } 
/*     */     
/* 137 */     return worldIn.canBlockBePlaced(this.block, p_179222_2_, false, p_179222_3_, null, p_179222_5_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/* 146 */     return this.block.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/* 154 */     return this.block.getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeTabs getCreativeTab() {
/* 162 */     return this.block.getCreativeTabToDisplayOn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
/* 172 */     this.block.getSubBlocks(itemIn, tab, subItems);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 177 */     return this.block;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */