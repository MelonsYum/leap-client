/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.client.C0EPacketClickWindow;
/*     */ import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
/*     */ import net.minecraft.network.play.client.C11PacketEnchantItem;
/*     */ import net.minecraft.stats.StatFileWriter;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ 
/*     */ public class PlayerControllerMP
/*     */ {
/*     */   private final Minecraft mc;
/*     */   private final NetHandlerPlayClient netClientHandler;
/*  37 */   private BlockPos field_178895_c = new BlockPos(-1, -1, -1);
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack currentItemHittingBlock;
/*     */ 
/*     */   
/*     */   public float curBlockDamageMP;
/*     */ 
/*     */   
/*     */   private float stepSoundTickCounter;
/*     */ 
/*     */   
/*     */   public int blockHitDelay;
/*     */ 
/*     */   
/*     */   private boolean isHittingBlock;
/*     */ 
/*     */   
/*     */   private WorldSettings.GameType currentGameType;
/*     */ 
/*     */   
/*     */   private int currentPlayerItem;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000881";
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerControllerMP(Minecraft mcIn, NetHandlerPlayClient p_i45062_2_) {
/*  67 */     this.currentGameType = WorldSettings.GameType.SURVIVAL;
/*  68 */     this.mc = mcIn;
/*  69 */     this.netClientHandler = p_i45062_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_178891_a(Minecraft mcIn, PlayerControllerMP p_178891_1_, BlockPos p_178891_2_, EnumFacing p_178891_3_) {
/*  74 */     if (!mcIn.theWorld.func_175719_a((EntityPlayer)mcIn.thePlayer, p_178891_2_, p_178891_3_))
/*     */     {
/*  76 */       p_178891_1_.func_178888_a(p_178891_2_, p_178891_3_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerCapabilities(EntityPlayer p_78748_1_) {
/*  85 */     this.currentGameType.configurePlayerCapabilities(p_78748_1_.capabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableEverythingIsScrewedUpMode() {
/*  96 */     return (this.currentGameType == WorldSettings.GameType.SPECTATOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(WorldSettings.GameType p_78746_1_) {
/* 104 */     this.currentGameType = p_78746_1_;
/* 105 */     this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.capabilities);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flipPlayer(EntityPlayer playerIn) {
/* 113 */     playerIn.rotationYaw = -180.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldDrawHUD() {
/* 118 */     return this.currentGameType.isSurvivalOrAdventure();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178888_a(BlockPos p_178888_1_, EnumFacing p_178888_2_) {
/* 123 */     if (this.currentGameType.isAdventure()) {
/*     */       
/* 125 */       if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
/*     */       {
/* 127 */         return false;
/*     */       }
/*     */       
/* 130 */       if (!this.mc.thePlayer.func_175142_cm()) {
/*     */         
/* 132 */         Block var3 = this.mc.theWorld.getBlockState(p_178888_1_).getBlock();
/* 133 */         ItemStack var4 = this.mc.thePlayer.getCurrentEquippedItem();
/*     */         
/* 135 */         if (var4 == null)
/*     */         {
/* 137 */           return false;
/*     */         }
/*     */         
/* 140 */         if (!var4.canDestroy(var3))
/*     */         {
/* 142 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     if (this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof net.minecraft.item.ItemSword)
/*     */     {
/* 149 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 153 */     WorldClient var8 = this.mc.theWorld;
/* 154 */     IBlockState var9 = var8.getBlockState(p_178888_1_);
/* 155 */     Block var5 = var9.getBlock();
/*     */     
/* 157 */     if (var5.getMaterial() == Material.air)
/*     */     {
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     var8.playAuxSFX(2001, p_178888_1_, Block.getStateId(var9));
/* 164 */     boolean var6 = var8.setBlockToAir(p_178888_1_);
/*     */     
/* 166 */     if (var6)
/*     */     {
/* 168 */       var5.onBlockDestroyedByPlayer(var8, p_178888_1_, var9);
/*     */     }
/*     */     
/* 171 */     this.field_178895_c = new BlockPos(this.field_178895_c.getX(), -1, this.field_178895_c.getZ());
/*     */     
/* 173 */     if (!this.currentGameType.isCreative()) {
/*     */       
/* 175 */       ItemStack var7 = this.mc.thePlayer.getCurrentEquippedItem();
/*     */       
/* 177 */       if (var7 != null) {
/*     */         
/* 179 */         var7.onBlockDestroyed(var8, var5, p_178888_1_, (EntityPlayer)this.mc.thePlayer);
/*     */         
/* 181 */         if (var7.stackSize == 0)
/*     */         {
/* 183 */           this.mc.thePlayer.destroyCurrentEquippedItem();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180511_b(BlockPos p_180511_1_, EnumFacing p_180511_2_) {
/* 197 */     if (this.currentGameType.isAdventure()) {
/*     */       
/* 199 */       if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
/*     */       {
/* 201 */         return false;
/*     */       }
/*     */       
/* 204 */       if (!this.mc.thePlayer.func_175142_cm()) {
/*     */         
/* 206 */         Block var3 = this.mc.theWorld.getBlockState(p_180511_1_).getBlock();
/* 207 */         ItemStack var4 = this.mc.thePlayer.getCurrentEquippedItem();
/*     */         
/* 209 */         if (var4 == null)
/*     */         {
/* 211 */           return false;
/*     */         }
/*     */         
/* 214 */         if (!var4.canDestroy(var3))
/*     */         {
/* 216 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 221 */     if (!this.mc.theWorld.getWorldBorder().contains(p_180511_1_))
/*     */     {
/* 223 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 227 */     if (this.currentGameType.isCreative()) {
/*     */       
/* 229 */       this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
/* 230 */       func_178891_a(this.mc, this, p_180511_1_, p_180511_2_);
/* 231 */       this.blockHitDelay = 5;
/*     */     }
/* 233 */     else if (!this.isHittingBlock || !func_178893_a(p_180511_1_)) {
/*     */       
/* 235 */       if (this.isHittingBlock)
/*     */       {
/* 237 */         this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, p_180511_2_));
/*     */       }
/*     */       
/* 240 */       this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
/* 241 */       Block var3 = this.mc.theWorld.getBlockState(p_180511_1_).getBlock();
/* 242 */       boolean var5 = (var3.getMaterial() != Material.air);
/*     */       
/* 244 */       if (var5 && this.curBlockDamageMP == 0.0F)
/*     */       {
/* 246 */         var3.onBlockClicked(this.mc.theWorld, p_180511_1_, (EntityPlayer)this.mc.thePlayer);
/*     */       }
/*     */       
/* 249 */       if (var5 && var3.getPlayerRelativeBlockHardness((EntityPlayer)this.mc.thePlayer, this.mc.thePlayer.worldObj, p_180511_1_) >= 1.0F) {
/*     */         
/* 251 */         func_178888_a(p_180511_1_, p_180511_2_);
/*     */       }
/*     */       else {
/*     */         
/* 255 */         this.isHittingBlock = true;
/* 256 */         this.field_178895_c = p_180511_1_;
/* 257 */         this.currentItemHittingBlock = this.mc.thePlayer.getHeldItem();
/* 258 */         this.curBlockDamageMP = 0.0F;
/* 259 */         this.stepSoundTickCounter = 0.0F;
/* 260 */         this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.field_178895_c, (int)(this.curBlockDamageMP * 10.0F) - 1);
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetBlockRemoving() {
/* 273 */     if (this.isHittingBlock) {
/*     */       
/* 275 */       this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, EnumFacing.DOWN));
/* 276 */       this.isHittingBlock = false;
/* 277 */       this.curBlockDamageMP = 0.0F;
/* 278 */       this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.field_178895_c, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180512_c(BlockPos p_180512_1_, EnumFacing p_180512_2_) {
/* 284 */     syncCurrentPlayItem();
/*     */     
/* 286 */     if (this.blockHitDelay > 0) {
/*     */       
/* 288 */       this.blockHitDelay--;
/* 289 */       return true;
/*     */     } 
/* 291 */     if (this.currentGameType.isCreative() && this.mc.theWorld.getWorldBorder().contains(p_180512_1_)) {
/*     */       
/* 293 */       this.blockHitDelay = 5;
/* 294 */       this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
/* 295 */       func_178891_a(this.mc, this, p_180512_1_, p_180512_2_);
/* 296 */       return true;
/*     */     } 
/* 298 */     if (func_178893_a(p_180512_1_)) {
/*     */       
/* 300 */       Block var3 = this.mc.theWorld.getBlockState(p_180512_1_).getBlock();
/*     */       
/* 302 */       if (var3.getMaterial() == Material.air) {
/*     */         
/* 304 */         this.isHittingBlock = false;
/* 305 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 309 */       this.curBlockDamageMP += var3.getPlayerRelativeBlockHardness((EntityPlayer)this.mc.thePlayer, this.mc.thePlayer.worldObj, p_180512_1_);
/*     */       
/* 311 */       if (this.stepSoundTickCounter % 4.0F == 0.0F)
/*     */       {
/* 313 */         this.mc.getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(var3.stepSound.getStepSound()), (var3.stepSound.getVolume() + 1.0F) / 8.0F, var3.stepSound.getFrequency() * 0.5F, p_180512_1_.getX() + 0.5F, p_180512_1_.getY() + 0.5F, p_180512_1_.getZ() + 0.5F));
/*     */       }
/*     */       
/* 316 */       this.stepSoundTickCounter++;
/*     */       
/* 318 */       if (this.curBlockDamageMP >= 1.0F) {
/*     */         
/* 320 */         this.isHittingBlock = false;
/* 321 */         this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
/* 322 */         func_178888_a(p_180512_1_, p_180512_2_);
/* 323 */         this.curBlockDamageMP = 0.0F;
/* 324 */         this.stepSoundTickCounter = 0.0F;
/* 325 */         this.blockHitDelay = 5;
/*     */       } 
/*     */       
/* 328 */       this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.field_178895_c, (int)(this.curBlockDamageMP * 10.0F) - 1);
/* 329 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 334 */     return func_180511_b(p_180512_1_, p_180512_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockReachDistance() {
/* 343 */     return this.currentGameType.isCreative() ? 5.0F : 4.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateController() {
/* 348 */     syncCurrentPlayItem();
/*     */     
/* 350 */     if (this.netClientHandler.getNetworkManager().isChannelOpen()) {
/*     */       
/* 352 */       this.netClientHandler.getNetworkManager().processReceivedPackets();
/*     */     }
/*     */     else {
/*     */       
/* 356 */       this.netClientHandler.getNetworkManager().checkDisconnected();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_178893_a(BlockPos p_178893_1_) {
/* 362 */     ItemStack var2 = this.mc.thePlayer.getHeldItem();
/* 363 */     boolean var3 = (this.currentItemHittingBlock == null && var2 == null);
/*     */     
/* 365 */     if (this.currentItemHittingBlock != null && var2 != null)
/*     */     {
/* 367 */       var3 = (var2.getItem() == this.currentItemHittingBlock.getItem() && ItemStack.areItemStackTagsEqual(var2, this.currentItemHittingBlock) && (var2.isItemStackDamageable() || var2.getMetadata() == this.currentItemHittingBlock.getMetadata()));
/*     */     }
/*     */     
/* 370 */     return (p_178893_1_.equals(this.field_178895_c) && var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncCurrentPlayItem() {
/* 378 */     int var1 = this.mc.thePlayer.inventory.currentItem;
/*     */     
/* 380 */     if (var1 != this.currentPlayerItem) {
/*     */       
/* 382 */       this.currentPlayerItem = var1;
/* 383 */       this.netClientHandler.addToSendQueue((Packet)new C09PacketHeldItemChange(this.currentPlayerItem));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178890_a(EntityPlayerSP p_178890_1_, WorldClient p_178890_2_, ItemStack p_178890_3_, BlockPos p_178890_4_, EnumFacing p_178890_5_, Vec3 p_178890_6_) {
/* 389 */     syncCurrentPlayItem();
/* 390 */     float var7 = (float)(p_178890_6_.xCoord - p_178890_4_.getX());
/* 391 */     float var8 = (float)(p_178890_6_.yCoord - p_178890_4_.getY());
/* 392 */     float var9 = (float)(p_178890_6_.zCoord - p_178890_4_.getZ());
/* 393 */     boolean var10 = false;
/*     */     
/* 395 */     if (!this.mc.theWorld.getWorldBorder().contains(p_178890_4_))
/*     */     {
/* 397 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 401 */     if (this.currentGameType != WorldSettings.GameType.SPECTATOR) {
/*     */       
/* 403 */       IBlockState var11 = p_178890_2_.getBlockState(p_178890_4_);
/*     */       
/* 405 */       if ((!p_178890_1_.isSneaking() || p_178890_1_.getHeldItem() == null) && var11.getBlock().onBlockActivated(p_178890_2_, p_178890_4_, var11, (EntityPlayer)p_178890_1_, p_178890_5_, var7, var8, var9))
/*     */       {
/* 407 */         var10 = true;
/*     */       }
/*     */       
/* 410 */       if (!var10 && p_178890_3_ != null && p_178890_3_.getItem() instanceof ItemBlock) {
/*     */         
/* 412 */         ItemBlock var12 = (ItemBlock)p_178890_3_.getItem();
/*     */         
/* 414 */         if (!var12.canPlaceBlockOnSide(p_178890_2_, p_178890_4_, p_178890_5_, (EntityPlayer)p_178890_1_, p_178890_3_))
/*     */         {
/* 416 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 421 */     this.netClientHandler.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(p_178890_4_, p_178890_5_.getIndex(), p_178890_1_.inventory.getCurrentItem(), var7, var8, var9));
/*     */     
/* 423 */     if (!var10 && this.currentGameType != WorldSettings.GameType.SPECTATOR) {
/*     */       
/* 425 */       if (p_178890_3_ == null)
/*     */       {
/* 427 */         return false;
/*     */       }
/* 429 */       if (this.currentGameType.isCreative()) {
/*     */         
/* 431 */         int var14 = p_178890_3_.getMetadata();
/* 432 */         int var15 = p_178890_3_.stackSize;
/* 433 */         boolean var13 = p_178890_3_.onItemUse((EntityPlayer)p_178890_1_, p_178890_2_, p_178890_4_, p_178890_5_, var7, var8, var9);
/* 434 */         p_178890_3_.setItemDamage(var14);
/* 435 */         p_178890_3_.stackSize = var15;
/* 436 */         return var13;
/*     */       } 
/*     */ 
/*     */       
/* 440 */       return p_178890_3_.onItemUse((EntityPlayer)p_178890_1_, p_178890_2_, p_178890_4_, p_178890_5_, var7, var8, var9);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 445 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendUseItem(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn) {
/* 455 */     if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
/*     */     {
/* 457 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 461 */     syncCurrentPlayItem();
/* 462 */     this.netClientHandler.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(playerIn.inventory.getCurrentItem()));
/* 463 */     int var4 = itemStackIn.stackSize;
/* 464 */     ItemStack var5 = itemStackIn.useItemRightClick(worldIn, playerIn);
/*     */     
/* 466 */     if (var5 == itemStackIn && (var5 == null || var5.stackSize == var4))
/*     */     {
/* 468 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 472 */     playerIn.inventory.mainInventory[playerIn.inventory.currentItem] = var5;
/*     */     
/* 474 */     if (var5.stackSize == 0)
/*     */     {
/* 476 */       playerIn.inventory.mainInventory[playerIn.inventory.currentItem] = null;
/*     */     }
/*     */     
/* 479 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPlayerSP func_178892_a(World worldIn, StatFileWriter p_178892_2_) {
/* 486 */     return new EntityPlayerSP(this.mc, worldIn, this.netClientHandler, p_178892_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntity(EntityPlayer playerIn, Entity targetEntity) {
/* 494 */     syncCurrentPlayItem();
/* 495 */     this.netClientHandler.addToSendQueue((Packet)new C02PacketUseEntity(targetEntity, C02PacketUseEntity.Action.ATTACK));
/*     */     
/* 497 */     if (this.currentGameType != WorldSettings.GameType.SPECTATOR)
/*     */     {
/* 499 */       playerIn.attackTargetEntityWithCurrentItem(targetEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactWithEntitySendPacket(EntityPlayer playerIn, Entity targetEntity) {
/* 508 */     syncCurrentPlayItem();
/* 509 */     this.netClientHandler.addToSendQueue((Packet)new C02PacketUseEntity(targetEntity, C02PacketUseEntity.Action.INTERACT));
/* 510 */     return (this.currentGameType != WorldSettings.GameType.SPECTATOR && playerIn.interactWith(targetEntity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178894_a(EntityPlayer p_178894_1_, Entity p_178894_2_, MovingObjectPosition p_178894_3_) {
/* 515 */     syncCurrentPlayItem();
/* 516 */     Vec3 var4 = new Vec3(p_178894_3_.hitVec.xCoord - p_178894_2_.posX, p_178894_3_.hitVec.yCoord - p_178894_2_.posY, p_178894_3_.hitVec.zCoord - p_178894_2_.posZ);
/* 517 */     this.netClientHandler.addToSendQueue((Packet)new C02PacketUseEntity(p_178894_2_, var4));
/* 518 */     return (this.currentGameType != WorldSettings.GameType.SPECTATOR && p_178894_2_.func_174825_a(p_178894_1_, var4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack windowClick(int windowId, int slotId, int p_78753_3_, int p_78753_4_, EntityPlayer playerIn) {
/* 526 */     short var6 = playerIn.openContainer.getNextTransactionID(playerIn.inventory);
/* 527 */     ItemStack var7 = playerIn.openContainer.slotClick(slotId, p_78753_3_, p_78753_4_, playerIn);
/* 528 */     this.netClientHandler.addToSendQueue((Packet)new C0EPacketClickWindow(windowId, slotId, p_78753_3_, p_78753_4_, var7, var6));
/* 529 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendEnchantPacket(int p_78756_1_, int p_78756_2_) {
/* 538 */     this.netClientHandler.addToSendQueue((Packet)new C11PacketEnchantItem(p_78756_1_, p_78756_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSlotPacket(ItemStack itemStackIn, int slotId) {
/* 546 */     if (this.currentGameType.isCreative())
/*     */     {
/* 548 */       this.netClientHandler.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(slotId, itemStackIn));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketDropItem(ItemStack itemStackIn) {
/* 557 */     if (this.currentGameType.isCreative() && itemStackIn != null)
/*     */     {
/* 559 */       this.netClientHandler.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(-1, itemStackIn));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onStoppedUsingItem(EntityPlayer playerIn) {
/* 565 */     syncCurrentPlayItem();
/* 566 */     this.netClientHandler.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
/* 567 */     playerIn.stopUsingItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean gameIsSurvivalOrAdventure() {
/* 572 */     return this.currentGameType.isSurvivalOrAdventure();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNotCreative() {
/* 580 */     return !this.currentGameType.isCreative();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInCreativeMode() {
/* 588 */     return this.currentGameType.isCreative();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean extendedReach() {
/* 596 */     return this.currentGameType.isCreative();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRidingHorse() {
/* 604 */     return (this.mc.thePlayer.isRiding() && this.mc.thePlayer.ridingEntity instanceof net.minecraft.entity.passive.EntityHorse);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSpectatorMode() {
/* 609 */     return (this.currentGameType == WorldSettings.GameType.SPECTATOR);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType func_178889_l() {
/* 614 */     return this.currentGameType;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\PlayerControllerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */