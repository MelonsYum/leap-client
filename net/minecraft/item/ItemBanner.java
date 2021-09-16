/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockStandingSign;
/*     */ import net.minecraft.block.BlockWallSign;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBanner
/*     */   extends ItemBlock {
/*     */   private static final String __OBFID = "CL_00002181";
/*     */   
/*     */   public ItemBanner() {
/*  25 */     super(Blocks.standing_banner);
/*  26 */     this.maxStackSize = 16;
/*  27 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  28 */     setHasSubtypes(true);
/*  29 */     setMaxDamage(0);
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
/*  40 */     if (side == EnumFacing.DOWN)
/*     */     {
/*  42 */       return false;
/*     */     }
/*  44 */     if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid())
/*     */     {
/*  46 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  50 */     pos = pos.offset(side);
/*     */     
/*  52 */     if (!playerIn.func_175151_a(pos, side, stack))
/*     */     {
/*  54 */       return false;
/*     */     }
/*  56 */     if (!Blocks.standing_banner.canPlaceBlockAt(worldIn, pos))
/*     */     {
/*  58 */       return false;
/*     */     }
/*  60 */     if (worldIn.isRemote)
/*     */     {
/*  62 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  66 */     if (side == EnumFacing.UP) {
/*     */       
/*  68 */       int var9 = MathHelper.floor_double(((playerIn.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
/*  69 */       worldIn.setBlockState(pos, Blocks.standing_banner.getDefaultState().withProperty((IProperty)BlockStandingSign.ROTATION_PROP, Integer.valueOf(var9)), 3);
/*     */     }
/*     */     else {
/*     */       
/*  73 */       worldIn.setBlockState(pos, Blocks.wall_banner.getDefaultState().withProperty((IProperty)BlockWallSign.field_176412_a, (Comparable)side), 3);
/*     */     } 
/*     */     
/*  76 */     stack.stackSize--;
/*  77 */     TileEntity var10 = worldIn.getTileEntity(pos);
/*     */     
/*  79 */     if (var10 instanceof TileEntityBanner)
/*     */     {
/*  81 */       ((TileEntityBanner)var10).setItemValues(stack);
/*     */     }
/*     */     
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/*  91 */     String var2 = "item.banner.";
/*  92 */     EnumDyeColor var3 = func_179225_h(stack);
/*  93 */     var2 = String.valueOf(var2) + var3.func_176762_d() + ".name";
/*  94 */     return StatCollector.translateToLocal(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/* 105 */     NBTTagCompound var5 = stack.getSubCompound("BlockEntityTag", false);
/*     */     
/* 107 */     if (var5 != null && var5.hasKey("Patterns")) {
/*     */       
/* 109 */       NBTTagList var6 = var5.getTagList("Patterns", 10);
/*     */       
/* 111 */       for (int var7 = 0; var7 < var6.tagCount() && var7 < 6; var7++) {
/*     */         
/* 113 */         NBTTagCompound var8 = var6.getCompoundTagAt(var7);
/* 114 */         EnumDyeColor var9 = EnumDyeColor.func_176766_a(var8.getInteger("Color"));
/* 115 */         TileEntityBanner.EnumBannerPattern var10 = TileEntityBanner.EnumBannerPattern.func_177268_a(var8.getString("Pattern"));
/*     */         
/* 117 */         if (var10 != null)
/*     */         {
/* 119 */           tooltip.add(StatCollector.translateToLocal("item.banner." + var10.func_177271_a() + "." + var9.func_176762_d()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 127 */     if (renderPass == 0)
/*     */     {
/* 129 */       return 16777215;
/*     */     }
/*     */ 
/*     */     
/* 133 */     EnumDyeColor var3 = func_179225_h(stack);
/* 134 */     return (var3.func_176768_e()).colorValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 145 */     EnumDyeColor[] var4 = EnumDyeColor.values();
/* 146 */     int var5 = var4.length;
/*     */     
/* 148 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 150 */       EnumDyeColor var7 = var4[var6];
/* 151 */       subItems.add(new ItemStack(itemIn, 1, var7.getDyeColorDamage()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeTabs getCreativeTab() {
/* 160 */     return CreativeTabs.tabDecorations;
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumDyeColor func_179225_h(ItemStack p_179225_1_) {
/* 165 */     NBTTagCompound var2 = p_179225_1_.getSubCompound("BlockEntityTag", false);
/* 166 */     EnumDyeColor var3 = null;
/*     */     
/* 168 */     if (var2 != null && var2.hasKey("Base")) {
/*     */       
/* 170 */       var3 = EnumDyeColor.func_176766_a(var2.getInteger("Base"));
/*     */     }
/*     */     else {
/*     */       
/* 174 */       var3 = EnumDyeColor.func_176766_a(p_179225_1_.getMetadata());
/*     */     } 
/*     */     
/* 177 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */