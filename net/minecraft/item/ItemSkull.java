/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSkull;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSkull extends Item {
/*  24 */   private static final String[] skullTypes = new String[] { "skeleton", "wither", "zombie", "char", "creeper" };
/*     */   
/*     */   private static final String __OBFID = "CL_00000067";
/*     */   
/*     */   public ItemSkull() {
/*  29 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  30 */     setMaxDamage(0);
/*  31 */     setHasSubtypes(true);
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
/*  42 */     if (side == EnumFacing.DOWN)
/*     */     {
/*  44 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  48 */     IBlockState var9 = worldIn.getBlockState(pos);
/*  49 */     Block var10 = var9.getBlock();
/*  50 */     boolean var11 = var10.isReplaceable(worldIn, pos);
/*     */     
/*  52 */     if (!var11) {
/*     */       
/*  54 */       if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid())
/*     */       {
/*  56 */         return false;
/*     */       }
/*     */       
/*  59 */       pos = pos.offset(side);
/*     */     } 
/*     */     
/*  62 */     if (!playerIn.func_175151_a(pos, side, stack))
/*     */     {
/*  64 */       return false;
/*     */     }
/*  66 */     if (!Blocks.skull.canPlaceBlockAt(worldIn, pos))
/*     */     {
/*  68 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  72 */     if (!worldIn.isRemote) {
/*     */       
/*  74 */       worldIn.setBlockState(pos, Blocks.skull.getDefaultState().withProperty((IProperty)BlockSkull.field_176418_a, (Comparable)side), 3);
/*  75 */       int var12 = 0;
/*     */       
/*  77 */       if (side == EnumFacing.UP)
/*     */       {
/*  79 */         var12 = MathHelper.floor_double((playerIn.rotationYaw * 16.0F / 360.0F) + 0.5D) & 0xF;
/*     */       }
/*     */       
/*  82 */       TileEntity var13 = worldIn.getTileEntity(pos);
/*     */       
/*  84 */       if (var13 instanceof TileEntitySkull) {
/*     */         
/*  86 */         TileEntitySkull var14 = (TileEntitySkull)var13;
/*     */         
/*  88 */         if (stack.getMetadata() == 3) {
/*     */           
/*  90 */           GameProfile var15 = null;
/*     */           
/*  92 */           if (stack.hasTagCompound()) {
/*     */             
/*  94 */             NBTTagCompound var16 = stack.getTagCompound();
/*     */             
/*  96 */             if (var16.hasKey("SkullOwner", 10)) {
/*     */               
/*  98 */               var15 = NBTUtil.readGameProfileFromNBT(var16.getCompoundTag("SkullOwner"));
/*     */             }
/* 100 */             else if (var16.hasKey("SkullOwner", 8) && var16.getString("SkullOwner").length() > 0) {
/*     */               
/* 102 */               var15 = new GameProfile(null, var16.getString("SkullOwner"));
/*     */             } 
/*     */           } 
/*     */           
/* 106 */           var14.setPlayerProfile(var15);
/*     */         }
/*     */         else {
/*     */           
/* 110 */           var14.setType(stack.getMetadata());
/*     */         } 
/*     */         
/* 113 */         var14.setSkullRotation(var12);
/* 114 */         Blocks.skull.func_180679_a(worldIn, pos, var14);
/*     */       } 
/*     */       
/* 117 */       stack.stackSize--;
/*     */     } 
/*     */     
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 132 */     for (int var4 = 0; var4 < skullTypes.length; var4++)
/*     */     {
/* 134 */       subItems.add(new ItemStack(itemIn, 1, var4));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata(int damage) {
/* 144 */     return damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/* 153 */     int var2 = stack.getMetadata();
/*     */     
/* 155 */     if (var2 < 0 || var2 >= skullTypes.length)
/*     */     {
/* 157 */       var2 = 0;
/*     */     }
/*     */     
/* 160 */     return String.valueOf(getUnlocalizedName()) + "." + skullTypes[var2];
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/* 165 */     if (stack.getMetadata() == 3 && stack.hasTagCompound()) {
/*     */       
/* 167 */       if (stack.getTagCompound().hasKey("SkullOwner", 8))
/*     */       {
/* 169 */         return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[] { stack.getTagCompound().getString("SkullOwner") });
/*     */       }
/*     */       
/* 172 */       if (stack.getTagCompound().hasKey("SkullOwner", 10)) {
/*     */         
/* 174 */         NBTTagCompound var2 = stack.getTagCompound().getCompoundTag("SkullOwner");
/*     */         
/* 176 */         if (var2.hasKey("Name", 8))
/*     */         {
/* 178 */           return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[] { var2.getString("Name") });
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return super.getItemStackDisplayName(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateItemStackNBT(NBTTagCompound nbt) {
/* 191 */     super.updateItemStackNBT(nbt);
/*     */     
/* 193 */     if (nbt.hasKey("SkullOwner", 8) && nbt.getString("SkullOwner").length() > 0) {
/*     */       
/* 195 */       GameProfile var2 = new GameProfile(null, nbt.getString("SkullOwner"));
/* 196 */       var2 = TileEntitySkull.updateGameprofile(var2);
/* 197 */       nbt.setTag("SkullOwner", (NBTBase)NBTUtil.writeGameProfile(new NBTTagCompound(), var2));
/* 198 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 202 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */