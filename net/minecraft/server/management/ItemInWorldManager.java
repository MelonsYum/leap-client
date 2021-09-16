/*     */ package net.minecraft.server.management;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*     */ import net.minecraft.network.play.server.S38PacketPlayerListItem;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.ILockableContainer;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemInWorldManager
/*     */ {
/*     */   public World theWorld;
/*     */   public EntityPlayerMP thisPlayerMP;
/*     */   private WorldSettings.GameType gameType;
/*     */   private boolean isDestroyingBlock;
/*     */   private int initialDamage;
/*     */   private BlockPos field_180240_f;
/*     */   private int curblockDamage;
/*     */   private boolean receivedFinishDiggingPacket;
/*     */   private BlockPos field_180241_i;
/*     */   private int initialBlockDamage;
/*     */   private int durabilityRemainingOnBlock;
/*     */   private static final String __OBFID = "CL_00001442";
/*     */   
/*     */   public ItemInWorldManager(World worldIn) {
/*  50 */     this.gameType = WorldSettings.GameType.NOT_SET;
/*  51 */     this.field_180240_f = BlockPos.ORIGIN;
/*  52 */     this.field_180241_i = BlockPos.ORIGIN;
/*  53 */     this.durabilityRemainingOnBlock = -1;
/*  54 */     this.theWorld = worldIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameType(WorldSettings.GameType p_73076_1_) {
/*  59 */     this.gameType = p_73076_1_;
/*  60 */     p_73076_1_.configurePlayerCapabilities(this.thisPlayerMP.capabilities);
/*  61 */     this.thisPlayerMP.sendPlayerAbilities();
/*  62 */     this.thisPlayerMP.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_GAME_MODE, new EntityPlayerMP[] { this.thisPlayerMP }));
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType getGameType() {
/*  67 */     return this.gameType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180239_c() {
/*  72 */     return this.gameType.isSurvivalOrAdventure();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreative() {
/*  80 */     return this.gameType.isCreative();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeGameType(WorldSettings.GameType p_73077_1_) {
/*  88 */     if (this.gameType == WorldSettings.GameType.NOT_SET)
/*     */     {
/*  90 */       this.gameType = p_73077_1_;
/*     */     }
/*     */     
/*  93 */     setGameType(this.gameType);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBlockRemoving() {
/*  98 */     this.curblockDamage++;
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (this.receivedFinishDiggingPacket) {
/*     */       
/* 104 */       int var1 = this.curblockDamage - this.initialBlockDamage;
/* 105 */       Block var2 = this.theWorld.getBlockState(this.field_180241_i).getBlock();
/*     */       
/* 107 */       if (var2.getMaterial() == Material.air) {
/*     */         
/* 109 */         this.receivedFinishDiggingPacket = false;
/*     */       }
/*     */       else {
/*     */         
/* 113 */         float var3 = var2.getPlayerRelativeBlockHardness((EntityPlayer)this.thisPlayerMP, this.thisPlayerMP.worldObj, this.field_180241_i) * (var1 + 1);
/* 114 */         int var4 = (int)(var3 * 10.0F);
/*     */         
/* 116 */         if (var4 != this.durabilityRemainingOnBlock) {
/*     */           
/* 118 */           this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.field_180241_i, var4);
/* 119 */           this.durabilityRemainingOnBlock = var4;
/*     */         } 
/*     */         
/* 122 */         if (var3 >= 1.0F)
/*     */         {
/* 124 */           this.receivedFinishDiggingPacket = false;
/* 125 */           func_180237_b(this.field_180241_i);
/*     */         }
/*     */       
/*     */       } 
/* 129 */     } else if (this.isDestroyingBlock) {
/*     */       
/* 131 */       Block var5 = this.theWorld.getBlockState(this.field_180240_f).getBlock();
/*     */       
/* 133 */       if (var5.getMaterial() == Material.air) {
/*     */         
/* 135 */         this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.field_180240_f, -1);
/* 136 */         this.durabilityRemainingOnBlock = -1;
/* 137 */         this.isDestroyingBlock = false;
/*     */       }
/*     */       else {
/*     */         
/* 141 */         int var6 = this.curblockDamage - this.initialDamage;
/* 142 */         float var3 = var5.getPlayerRelativeBlockHardness((EntityPlayer)this.thisPlayerMP, this.thisPlayerMP.worldObj, this.field_180241_i) * (var6 + 1);
/* 143 */         int var4 = (int)(var3 * 10.0F);
/*     */         
/* 145 */         if (var4 != this.durabilityRemainingOnBlock) {
/*     */           
/* 147 */           this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.field_180240_f, var4);
/* 148 */           this.durabilityRemainingOnBlock = var4;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180784_a(BlockPos p_180784_1_, EnumFacing p_180784_2_) {
/* 156 */     if (isCreative()) {
/*     */       
/* 158 */       if (!this.theWorld.func_175719_a(null, p_180784_1_, p_180784_2_))
/*     */       {
/* 160 */         func_180237_b(p_180784_1_);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 165 */       Block var3 = this.theWorld.getBlockState(p_180784_1_).getBlock();
/*     */       
/* 167 */       if (this.gameType.isAdventure()) {
/*     */         
/* 169 */         if (this.gameType == WorldSettings.GameType.SPECTATOR) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 174 */         if (!this.thisPlayerMP.func_175142_cm()) {
/*     */           
/* 176 */           ItemStack var4 = this.thisPlayerMP.getCurrentEquippedItem();
/*     */           
/* 178 */           if (var4 == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 183 */           if (!var4.canDestroy(var3)) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 190 */       this.theWorld.func_175719_a(null, p_180784_1_, p_180784_2_);
/* 191 */       this.initialDamage = this.curblockDamage;
/* 192 */       float var6 = 1.0F;
/*     */       
/* 194 */       if (var3.getMaterial() != Material.air) {
/*     */         
/* 196 */         var3.onBlockClicked(this.theWorld, p_180784_1_, (EntityPlayer)this.thisPlayerMP);
/* 197 */         var6 = var3.getPlayerRelativeBlockHardness((EntityPlayer)this.thisPlayerMP, this.thisPlayerMP.worldObj, p_180784_1_);
/*     */       } 
/*     */       
/* 200 */       if (var3.getMaterial() != Material.air && var6 >= 1.0F) {
/*     */         
/* 202 */         func_180237_b(p_180784_1_);
/*     */       }
/*     */       else {
/*     */         
/* 206 */         this.isDestroyingBlock = true;
/* 207 */         this.field_180240_f = p_180784_1_;
/* 208 */         int var5 = (int)(var6 * 10.0F);
/* 209 */         this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), p_180784_1_, var5);
/* 210 */         this.durabilityRemainingOnBlock = var5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180785_a(BlockPos p_180785_1_) {
/* 217 */     if (p_180785_1_.equals(this.field_180240_f)) {
/*     */       
/* 219 */       int var2 = this.curblockDamage - this.initialDamage;
/* 220 */       Block var3 = this.theWorld.getBlockState(p_180785_1_).getBlock();
/*     */       
/* 222 */       if (var3.getMaterial() != Material.air) {
/*     */         
/* 224 */         float var4 = var3.getPlayerRelativeBlockHardness((EntityPlayer)this.thisPlayerMP, this.thisPlayerMP.worldObj, p_180785_1_) * (var2 + 1);
/*     */         
/* 226 */         if (var4 >= 0.7F) {
/*     */           
/* 228 */           this.isDestroyingBlock = false;
/* 229 */           this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), p_180785_1_, -1);
/* 230 */           func_180237_b(p_180785_1_);
/*     */         }
/* 232 */         else if (!this.receivedFinishDiggingPacket) {
/*     */           
/* 234 */           this.isDestroyingBlock = false;
/* 235 */           this.receivedFinishDiggingPacket = true;
/* 236 */           this.field_180241_i = p_180785_1_;
/* 237 */           this.initialBlockDamage = this.initialDamage;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180238_e() {
/* 245 */     this.isDestroyingBlock = false;
/* 246 */     this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.field_180240_f, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_180235_c(BlockPos p_180235_1_) {
/* 251 */     IBlockState var2 = this.theWorld.getBlockState(p_180235_1_);
/* 252 */     var2.getBlock().onBlockHarvested(this.theWorld, p_180235_1_, var2, (EntityPlayer)this.thisPlayerMP);
/* 253 */     boolean var3 = this.theWorld.setBlockToAir(p_180235_1_);
/*     */     
/* 255 */     if (var3)
/*     */     {
/* 257 */       var2.getBlock().onBlockDestroyedByPlayer(this.theWorld, p_180235_1_, var2);
/*     */     }
/*     */     
/* 260 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180237_b(BlockPos p_180237_1_) {
/* 265 */     if (this.gameType.isCreative() && this.thisPlayerMP.getHeldItem() != null && this.thisPlayerMP.getHeldItem().getItem() instanceof net.minecraft.item.ItemSword)
/*     */     {
/* 267 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 271 */     IBlockState var2 = this.theWorld.getBlockState(p_180237_1_);
/* 272 */     TileEntity var3 = this.theWorld.getTileEntity(p_180237_1_);
/*     */     
/* 274 */     if (this.gameType.isAdventure()) {
/*     */       
/* 276 */       if (this.gameType == WorldSettings.GameType.SPECTATOR)
/*     */       {
/* 278 */         return false;
/*     */       }
/*     */       
/* 281 */       if (!this.thisPlayerMP.func_175142_cm()) {
/*     */         
/* 283 */         ItemStack var4 = this.thisPlayerMP.getCurrentEquippedItem();
/*     */         
/* 285 */         if (var4 == null)
/*     */         {
/* 287 */           return false;
/*     */         }
/*     */         
/* 290 */         if (!var4.canDestroy(var2.getBlock()))
/*     */         {
/* 292 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 297 */     this.theWorld.playAuxSFXAtEntity((EntityPlayer)this.thisPlayerMP, 2001, p_180237_1_, Block.getStateId(var2));
/* 298 */     boolean var7 = func_180235_c(p_180237_1_);
/*     */     
/* 300 */     if (isCreative()) {
/*     */       
/* 302 */       this.thisPlayerMP.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange(this.theWorld, p_180237_1_));
/*     */     }
/*     */     else {
/*     */       
/* 306 */       ItemStack var5 = this.thisPlayerMP.getCurrentEquippedItem();
/* 307 */       boolean var6 = this.thisPlayerMP.canHarvestBlock(var2.getBlock());
/*     */       
/* 309 */       if (var5 != null) {
/*     */         
/* 311 */         var5.onBlockDestroyed(this.theWorld, var2.getBlock(), p_180237_1_, (EntityPlayer)this.thisPlayerMP);
/*     */         
/* 313 */         if (var5.stackSize == 0)
/*     */         {
/* 315 */           this.thisPlayerMP.destroyCurrentEquippedItem();
/*     */         }
/*     */       } 
/*     */       
/* 319 */       if (var7 && var6)
/*     */       {
/* 321 */         var2.getBlock().harvestBlock(this.theWorld, (EntityPlayer)this.thisPlayerMP, p_180237_1_, var2, var3);
/*     */       }
/*     */     } 
/*     */     
/* 325 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryUseItem(EntityPlayer p_73085_1_, World worldIn, ItemStack p_73085_3_) {
/* 334 */     if (this.gameType == WorldSettings.GameType.SPECTATOR)
/*     */     {
/* 336 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 340 */     int var4 = p_73085_3_.stackSize;
/* 341 */     int var5 = p_73085_3_.getMetadata();
/* 342 */     ItemStack var6 = p_73085_3_.useItemRightClick(worldIn, p_73085_1_);
/*     */     
/* 344 */     if (var6 == p_73085_3_ && (var6 == null || (var6.stackSize == var4 && var6.getMaxItemUseDuration() <= 0 && var6.getMetadata() == var5)))
/*     */     {
/* 346 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 350 */     p_73085_1_.inventory.mainInventory[p_73085_1_.inventory.currentItem] = var6;
/*     */     
/* 352 */     if (isCreative()) {
/*     */       
/* 354 */       var6.stackSize = var4;
/*     */       
/* 356 */       if (var6.isItemStackDamageable())
/*     */       {
/* 358 */         var6.setItemDamage(var5);
/*     */       }
/*     */     } 
/*     */     
/* 362 */     if (var6.stackSize == 0)
/*     */     {
/* 364 */       p_73085_1_.inventory.mainInventory[p_73085_1_.inventory.currentItem] = null;
/*     */     }
/*     */     
/* 367 */     if (!p_73085_1_.isUsingItem())
/*     */     {
/* 369 */       ((EntityPlayerMP)p_73085_1_).sendContainerToPlayer(p_73085_1_.inventoryContainer);
/*     */     }
/*     */     
/* 372 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180236_a(EntityPlayer p_180236_1_, World worldIn, ItemStack p_180236_3_, BlockPos p_180236_4_, EnumFacing p_180236_5_, float p_180236_6_, float p_180236_7_, float p_180236_8_) {
/* 379 */     if (this.gameType == WorldSettings.GameType.SPECTATOR) {
/*     */       
/* 381 */       TileEntity var13 = worldIn.getTileEntity(p_180236_4_);
/*     */       
/* 383 */       if (var13 instanceof ILockableContainer) {
/*     */         
/* 385 */         Block var14 = worldIn.getBlockState(p_180236_4_).getBlock();
/* 386 */         ILockableContainer var15 = (ILockableContainer)var13;
/*     */         
/* 388 */         if (var15 instanceof net.minecraft.tileentity.TileEntityChest && var14 instanceof BlockChest)
/*     */         {
/* 390 */           var15 = ((BlockChest)var14).getLockableContainer(worldIn, p_180236_4_);
/*     */         }
/*     */         
/* 393 */         if (var15 != null)
/*     */         {
/* 395 */           p_180236_1_.displayGUIChest((IInventory)var15);
/* 396 */           return true;
/*     */         }
/*     */       
/* 399 */       } else if (var13 instanceof IInventory) {
/*     */         
/* 401 */         p_180236_1_.displayGUIChest((IInventory)var13);
/* 402 */         return true;
/*     */       } 
/*     */       
/* 405 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 409 */     if (!p_180236_1_.isSneaking() || p_180236_1_.getHeldItem() == null) {
/*     */       
/* 411 */       IBlockState var9 = worldIn.getBlockState(p_180236_4_);
/*     */       
/* 413 */       if (var9.getBlock().onBlockActivated(worldIn, p_180236_4_, var9, p_180236_1_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_))
/*     */       {
/* 415 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 419 */     if (p_180236_3_ == null)
/*     */     {
/* 421 */       return false;
/*     */     }
/* 423 */     if (isCreative()) {
/*     */       
/* 425 */       int var12 = p_180236_3_.getMetadata();
/* 426 */       int var10 = p_180236_3_.stackSize;
/* 427 */       boolean var11 = p_180236_3_.onItemUse(p_180236_1_, worldIn, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
/* 428 */       p_180236_3_.setItemDamage(var12);
/* 429 */       p_180236_3_.stackSize = var10;
/* 430 */       return var11;
/*     */     } 
/*     */ 
/*     */     
/* 434 */     return p_180236_3_.onItemUse(p_180236_1_, worldIn, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorld(WorldServer p_73080_1_) {
/* 444 */     this.theWorld = (World)p_73080_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\ItemInWorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */