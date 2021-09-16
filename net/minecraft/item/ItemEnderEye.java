/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.block.BlockEndPortalFrame;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityEnderEye;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemEnderEye extends Item {
/*     */   private static final String __OBFID = "CL_00000026";
/*     */   
/*     */   public ItemEnderEye() {
/*  22 */     setCreativeTab(CreativeTabs.tabMisc);
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
/*  33 */     IBlockState var9 = worldIn.getBlockState(pos);
/*     */     
/*  35 */     if (playerIn.func_175151_a(pos.offset(side), side, stack) && var9.getBlock() == Blocks.end_portal_frame && !((Boolean)var9.getValue((IProperty)BlockEndPortalFrame.field_176507_b)).booleanValue()) {
/*     */       
/*  37 */       if (worldIn.isRemote)
/*     */       {
/*  39 */         return true;
/*     */       }
/*     */ 
/*     */       
/*  43 */       worldIn.setBlockState(pos, var9.withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf(true)), 2);
/*  44 */       worldIn.updateComparatorOutputLevel(pos, Blocks.end_portal_frame);
/*  45 */       stack.stackSize--;
/*     */       
/*  47 */       for (int var10 = 0; var10 < 16; var10++) {
/*     */         
/*  49 */         double var11 = (pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
/*  50 */         double var13 = (pos.getY() + 0.8125F);
/*  51 */         double var15 = (pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
/*  52 */         double var17 = 0.0D;
/*  53 */         double var19 = 0.0D;
/*  54 */         double var21 = 0.0D;
/*  55 */         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var11, var13, var15, var17, var19, var21, new int[0]);
/*     */       } 
/*     */       
/*  58 */       EnumFacing var23 = (EnumFacing)var9.getValue((IProperty)BlockEndPortalFrame.field_176508_a);
/*  59 */       int var24 = 0;
/*  60 */       int var12 = 0;
/*  61 */       boolean var25 = false;
/*  62 */       boolean var14 = true;
/*  63 */       EnumFacing var26 = var23.rotateY();
/*     */       
/*  65 */       for (int var16 = -2; var16 <= 2; var16++) {
/*     */         
/*  67 */         BlockPos var28 = pos.offset(var26, var16);
/*  68 */         IBlockState var18 = worldIn.getBlockState(var28);
/*     */         
/*  70 */         if (var18.getBlock() == Blocks.end_portal_frame) {
/*     */           
/*  72 */           if (!((Boolean)var18.getValue((IProperty)BlockEndPortalFrame.field_176507_b)).booleanValue()) {
/*     */             
/*  74 */             var14 = false;
/*     */             
/*     */             break;
/*     */           } 
/*  78 */           var12 = var16;
/*     */           
/*  80 */           if (!var25) {
/*     */             
/*  82 */             var24 = var16;
/*  83 */             var25 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  88 */       if (var14 && var12 == var24 + 2) {
/*     */         
/*  90 */         BlockPos var27 = pos.offset(var23, 4);
/*     */         
/*     */         int var29;
/*  93 */         for (var29 = var24; var29 <= var12; var29++) {
/*     */           
/*  95 */           BlockPos var30 = var27.offset(var26, var29);
/*  96 */           IBlockState var32 = worldIn.getBlockState(var30);
/*     */           
/*  98 */           if (var32.getBlock() != Blocks.end_portal_frame || !((Boolean)var32.getValue((IProperty)BlockEndPortalFrame.field_176507_b)).booleanValue()) {
/*     */             
/* 100 */             var14 = false;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 108 */         for (var29 = var24 - 1; var29 <= var12 + 1; var29 += 4) {
/*     */           
/* 110 */           var27 = pos.offset(var26, var29);
/*     */           
/* 112 */           for (int var31 = 1; var31 <= 3; var31++) {
/*     */             
/* 114 */             BlockPos var33 = var27.offset(var23, var31);
/* 115 */             IBlockState var20 = worldIn.getBlockState(var33);
/*     */             
/* 117 */             if (var20.getBlock() != Blocks.end_portal_frame || !((Boolean)var20.getValue((IProperty)BlockEndPortalFrame.field_176507_b)).booleanValue()) {
/*     */               
/* 119 */               var14 = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 125 */         if (var14)
/*     */         {
/* 127 */           for (var29 = var24; var29 <= var12; var29++) {
/*     */             
/* 129 */             var27 = pos.offset(var26, var29);
/*     */             
/* 131 */             for (int var31 = 1; var31 <= 3; var31++) {
/*     */               
/* 133 */               BlockPos var33 = var27.offset(var23, var31);
/* 134 */               worldIn.setBlockState(var33, Blocks.end_portal.getDefaultState(), 2);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 140 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 154 */     MovingObjectPosition var4 = getMovingObjectPositionFromPlayer(worldIn, playerIn, false);
/*     */     
/* 156 */     if (var4 != null && var4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && worldIn.getBlockState(var4.func_178782_a()).getBlock() == Blocks.end_portal_frame)
/*     */     {
/* 158 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (!worldIn.isRemote) {
/*     */       
/* 164 */       BlockPos var5 = worldIn.func_180499_a("Stronghold", new BlockPos((Entity)playerIn));
/*     */       
/* 166 */       if (var5 != null) {
/*     */         
/* 168 */         EntityEnderEye var6 = new EntityEnderEye(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ);
/* 169 */         var6.func_180465_a(var5);
/* 170 */         worldIn.spawnEntityInWorld((Entity)var6);
/* 171 */         worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 172 */         worldIn.playAuxSFXAtEntity(null, 1002, new BlockPos((Entity)playerIn), 0);
/*     */         
/* 174 */         if (!playerIn.capabilities.isCreativeMode)
/*     */         {
/* 176 */           itemStackIn.stackSize--;
/*     */         }
/*     */         
/* 179 */         playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return itemStackIn;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */