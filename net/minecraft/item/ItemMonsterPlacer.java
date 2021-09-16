/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMonsterPlacer
/*     */   extends Item
/*     */ {
/*     */   private static final String __OBFID = "CL_00000070";
/*     */   
/*     */   public ItemMonsterPlacer() {
/*  33 */     setHasSubtypes(true);
/*  34 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/*  39 */     String var2 = StatCollector.translateToLocal(String.valueOf(getUnlocalizedName()) + ".name").trim();
/*  40 */     String var3 = EntityList.getStringFromID(stack.getMetadata());
/*     */     
/*  42 */     if (var3 != null)
/*     */     {
/*  44 */       var2 = String.valueOf(var2) + " " + StatCollector.translateToLocal("entity." + var3 + ".name");
/*     */     }
/*     */     
/*  47 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/*  52 */     EntityList.EntityEggInfo var3 = (EntityList.EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(stack.getMetadata()));
/*  53 */     return (var3 != null) ? ((renderPass == 0) ? var3.primaryColor : var3.secondaryColor) : 16777215;
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
/*  64 */     if (worldIn.isRemote)
/*     */     {
/*  66 */       return true;
/*     */     }
/*  68 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*     */     {
/*  70 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  74 */     IBlockState var9 = worldIn.getBlockState(pos);
/*     */     
/*  76 */     if (var9.getBlock() == Blocks.mob_spawner) {
/*     */       
/*  78 */       TileEntity var10 = worldIn.getTileEntity(pos);
/*     */       
/*  80 */       if (var10 instanceof TileEntityMobSpawner) {
/*     */         
/*  82 */         MobSpawnerBaseLogic var11 = ((TileEntityMobSpawner)var10).getSpawnerBaseLogic();
/*  83 */         var11.setEntityName(EntityList.getStringFromID(stack.getMetadata()));
/*  84 */         var10.markDirty();
/*  85 */         worldIn.markBlockForUpdate(pos);
/*     */         
/*  87 */         if (!playerIn.capabilities.isCreativeMode)
/*     */         {
/*  89 */           stack.stackSize--;
/*     */         }
/*     */         
/*  92 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     pos = pos.offset(side);
/*  97 */     double var13 = 0.0D;
/*     */     
/*  99 */     if (side == EnumFacing.UP && var9 instanceof net.minecraft.block.BlockFence)
/*     */     {
/* 101 */       var13 = 0.5D;
/*     */     }
/*     */     
/* 104 */     Entity var12 = spawnCreature(worldIn, stack.getMetadata(), pos.getX() + 0.5D, pos.getY() + var13, pos.getZ() + 0.5D);
/*     */     
/* 106 */     if (var12 != null) {
/*     */       
/* 108 */       if (var12 instanceof net.minecraft.entity.EntityLivingBase && stack.hasDisplayName())
/*     */       {
/* 110 */         var12.setCustomNameTag(stack.getDisplayName());
/*     */       }
/*     */       
/* 113 */       if (!playerIn.capabilities.isCreativeMode)
/*     */       {
/* 115 */         stack.stackSize--;
/*     */       }
/*     */     } 
/*     */     
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 128 */     if (worldIn.isRemote)
/*     */     {
/* 130 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/* 134 */     MovingObjectPosition var4 = getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
/*     */     
/* 136 */     if (var4 == null)
/*     */     {
/* 138 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/* 142 */     if (var4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/* 144 */       BlockPos var5 = var4.func_178782_a();
/*     */       
/* 146 */       if (!worldIn.isBlockModifiable(playerIn, var5))
/*     */       {
/* 148 */         return itemStackIn;
/*     */       }
/*     */       
/* 151 */       if (!playerIn.func_175151_a(var5, var4.field_178784_b, itemStackIn))
/*     */       {
/* 153 */         return itemStackIn;
/*     */       }
/*     */       
/* 156 */       if (worldIn.getBlockState(var5).getBlock() instanceof net.minecraft.block.BlockLiquid) {
/*     */         
/* 158 */         Entity var6 = spawnCreature(worldIn, itemStackIn.getMetadata(), var5.getX() + 0.5D, var5.getY() + 0.5D, var5.getZ() + 0.5D);
/*     */         
/* 160 */         if (var6 != null) {
/*     */           
/* 162 */           if (var6 instanceof net.minecraft.entity.EntityLivingBase && itemStackIn.hasDisplayName())
/*     */           {
/* 164 */             ((EntityLiving)var6).setCustomNameTag(itemStackIn.getDisplayName());
/*     */           }
/*     */           
/* 167 */           if (!playerIn.capabilities.isCreativeMode)
/*     */           {
/* 169 */             itemStackIn.stackSize--;
/*     */           }
/*     */           
/* 172 */           playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 177 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity spawnCreature(World worldIn, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_) {
/* 188 */     if (!EntityList.entityEggs.containsKey(Integer.valueOf(p_77840_1_)))
/*     */     {
/* 190 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 194 */     Entity var8 = null;
/*     */     
/* 196 */     for (int var9 = 0; var9 < 1; var9++) {
/*     */       
/* 198 */       var8 = EntityList.createEntityByID(p_77840_1_, worldIn);
/*     */       
/* 200 */       if (var8 instanceof net.minecraft.entity.EntityLivingBase) {
/*     */         
/* 202 */         EntityLiving var10 = (EntityLiving)var8;
/* 203 */         var8.setLocationAndAngles(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat() * 360.0F), 0.0F);
/* 204 */         var10.rotationYawHead = var10.rotationYaw;
/* 205 */         var10.renderYawOffset = var10.rotationYaw;
/* 206 */         var10.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos((Entity)var10)), null);
/* 207 */         worldIn.spawnEntityInWorld(var8);
/* 208 */         var10.playLivingSound();
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     return var8;
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
/* 223 */     Iterator<EntityList.EntityEggInfo> var4 = EntityList.entityEggs.values().iterator();
/*     */     
/* 225 */     while (var4.hasNext()) {
/*     */       
/* 227 */       EntityList.EntityEggInfo var5 = var4.next();
/* 228 */       subItems.add(new ItemStack(itemIn, 1, var5.spawnedID));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemMonsterPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */