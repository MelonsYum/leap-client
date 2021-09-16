/*     */ package net.minecraft.client.entity;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.EventType;
/*     */ import leap.events.listeners.EventChat;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.events.listeners.EventUpdate;
/*     */ import leap.modules.render.DamageEffects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.MovingSoundMinecartRiding;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.gui.GuiCommandBlock;
/*     */ import net.minecraft.client.gui.GuiEnchantment;
/*     */ import net.minecraft.client.gui.GuiHopper;
/*     */ import net.minecraft.client.gui.GuiMerchant;
/*     */ import net.minecraft.client.gui.GuiRepair;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiScreenBook;
/*     */ import net.minecraft.client.gui.inventory.GuiBeacon;
/*     */ import net.minecraft.client.gui.inventory.GuiBrewingStand;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.client.gui.inventory.GuiCrafting;
/*     */ import net.minecraft.client.gui.inventory.GuiDispenser;
/*     */ import net.minecraft.client.gui.inventory.GuiEditSign;
/*     */ import net.minecraft.client.gui.inventory.GuiFurnace;
/*     */ import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.command.server.CommandBlockLogic;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.C0APacketAnimation;
/*     */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*     */ import net.minecraft.network.play.client.C0CPacketInput;
/*     */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*     */ import net.minecraft.network.play.client.C13PacketPlayerAbilities;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatFileWriter;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MovementInput;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IInteractionObject;
/*     */ import net.minecraft.world.IWorldNameable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPlayerSP
/*     */   extends AbstractClientPlayer
/*     */ {
/*     */   public final NetHandlerPlayClient sendQueue;
/*     */   private final StatFileWriter field_146108_bO;
/*     */   private double field_175172_bI;
/*     */   private double field_175166_bJ;
/*     */   private double field_175167_bK;
/*     */   private float field_175164_bL;
/*     */   private float field_175165_bM;
/*     */   private boolean field_175170_bN;
/*     */   private boolean field_175171_bO;
/*     */   private int field_175168_bP;
/*     */   private boolean field_175169_bQ;
/*     */   private String clientBrand;
/*     */   public MovementInput movementInput;
/*     */   protected Minecraft mc;
/*     */   protected int sprintToggleTimer;
/*     */   public int sprintingTicksLeft;
/*     */   public float renderArmYaw;
/*     */   public float renderArmPitch;
/*     */   public float prevRenderArmYaw;
/*     */   public float prevRenderArmPitch;
/*     */   private int horseJumpPowerCounter;
/*     */   private float horseJumpPower;
/*     */   public float timeInPortal;
/*     */   public float prevTimeInPortal;
/*     */   private static final String __OBFID = "CL_00000938";
/*     */   
/*     */   public EntityPlayerSP(Minecraft mcIn, World worldIn, NetHandlerPlayClient p_i46278_3_, StatFileWriter p_i46278_4_) {
/* 103 */     super(worldIn, p_i46278_3_.func_175105_e());
/* 104 */     this.sendQueue = p_i46278_3_;
/* 105 */     this.field_146108_bO = p_i46278_4_;
/* 106 */     this.mc = mcIn;
/* 107 */     this.dimension = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void heal(float p_70691_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mountEntity(Entity entityIn) {
/* 128 */     super.mountEntity(entityIn);
/*     */     
/* 130 */     if (entityIn instanceof EntityMinecart)
/*     */     {
/* 132 */       this.mc.getSoundHandler().playSound((ISound)new MovingSoundMinecartRiding(this, (EntityMinecart)entityIn));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 141 */     if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, 0.0D, this.posZ))) {
/*     */       
/* 143 */       super.onUpdate();
/*     */       
/* 145 */       if (isRiding()) {
/*     */         
/* 147 */         this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
/* 148 */         this.sendQueue.addToSendQueue((Packet)new C0CPacketInput(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
/*     */       }
/*     */       else {
/*     */         
/* 152 */         func_175161_p();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175161_p() {
/* 159 */     EventUpdate e = new EventUpdate();
/* 160 */     e.setType(EventType.PRE);
/* 161 */     Client.onEvent((Event)e);
/*     */     
/* 163 */     EventMotion event = new EventMotion(this.posX, (getEntityBoundingBox()).minY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround);
/* 164 */     event.setType(EventType.PRE);
/* 165 */     Client.onEvent((Event)event);
/*     */     
/* 167 */     boolean var1 = isSprinting();
/*     */     
/* 169 */     if (var1 != this.field_175171_bO) {
/*     */       
/* 171 */       if (var1) {
/*     */         
/* 173 */         this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.START_SPRINTING));
/*     */       }
/*     */       else {
/*     */         
/* 177 */         this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.STOP_SPRINTING));
/*     */       } 
/*     */       
/* 180 */       this.field_175171_bO = var1;
/*     */     } 
/*     */     
/* 183 */     boolean var2 = isSneaking();
/*     */     
/* 185 */     if (var2 != this.field_175170_bN) {
/*     */       
/* 187 */       if (var2) {
/*     */         
/* 189 */         this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.START_SNEAKING));
/*     */       }
/*     */       else {
/*     */         
/* 193 */         this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.STOP_SNEAKING));
/*     */       } 
/*     */       
/* 196 */       this.field_175170_bN = var2;
/*     */     } 
/*     */     
/* 199 */     if (func_175160_A()) {
/*     */       
/* 201 */       double var3 = event.getX() - this.field_175172_bI;
/* 202 */       double var5 = event.getY() - this.field_175166_bJ;
/* 203 */       double var7 = event.getZ() - this.field_175167_bK;
/* 204 */       double var9 = (event.getYaw() - this.field_175164_bL);
/* 205 */       double var11 = (event.getPitch() - this.field_175165_bM);
/* 206 */       boolean var13 = !(var3 * var3 + var5 * var5 + var7 * var7 <= 9.0E-4D && this.field_175168_bP < 20);
/* 207 */       boolean var14 = !(var9 == 0.0D && var11 == 0.0D);
/*     */       
/* 209 */       if (this.ridingEntity == null) {
/*     */         
/* 211 */         if (var13 && var14)
/*     */         {
/* 213 */           this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()));
/*     */         }
/* 215 */         else if (var13)
/*     */         {
/* 217 */           this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(event.getX(), event.getY(), event.getZ(), event.isOnGround()));
/*     */         }
/* 219 */         else if (var14)
/*     */         {
/* 221 */           this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(event.getYaw(), event.getPitch(), event.isOnGround()));
/*     */         }
/*     */         else
/*     */         {
/* 225 */           this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(event.isOnGround()));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 230 */         this.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(this.motionX, -999.0D, this.motionZ, event.getYaw(), event.getPitch(), event.isOnGround()));
/* 231 */         var13 = false;
/*     */       } 
/*     */       
/* 234 */       this.field_175168_bP++;
/*     */       
/* 236 */       if (var13) {
/*     */         
/* 238 */         this.field_175172_bI = event.getX();
/* 239 */         this.field_175166_bJ = event.getY();
/* 240 */         this.field_175167_bK = event.getZ();
/* 241 */         this.field_175168_bP = 0;
/*     */       } 
/*     */       
/* 244 */       if (var14) {
/*     */         
/* 246 */         this.field_175164_bL = event.getYaw();
/* 247 */         this.field_175165_bM = event.getPitch();
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     event.setType(EventType.POST);
/* 252 */     Client.onEvent((Event)event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem dropOneItem(boolean p_71040_1_) {
/* 260 */     C07PacketPlayerDigging.Action var2 = p_71040_1_ ? C07PacketPlayerDigging.Action.DROP_ALL_ITEMS : C07PacketPlayerDigging.Action.DROP_ITEM;
/* 261 */     this.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(var2, BlockPos.ORIGIN, EnumFacing.DOWN));
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void joinEntityItemWithWorld(EntityItem p_71012_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendChatMessage(String p_71165_1_) {
/* 275 */     EventChat event = new EventChat(p_71165_1_);
/*     */     
/* 277 */     Client.onEvent((Event)event);
/*     */     
/* 279 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 282 */     this.sendQueue.addToSendQueue((Packet)new C01PacketChatMessage(event.getMessage()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void swingItem() {
/* 290 */     super.swingItem();
/* 291 */     this.sendQueue.addToSendQueue((Packet)new C0APacketAnimation());
/*     */   }
/*     */ 
/*     */   
/*     */   public void respawnPlayer() {
/* 296 */     this.sendQueue.addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {
/* 305 */     if (!func_180431_b(p_70665_1_))
/*     */     {
/* 307 */       setHealth(getHealth() - p_70665_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeScreen() {
/* 316 */     this.sendQueue.addToSendQueue((Packet)new C0DPacketCloseWindow(this.openContainer.windowId));
/* 317 */     func_175159_q();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175159_q() {
/* 322 */     this.inventory.setItemStack(null);
/* 323 */     super.closeScreen();
/* 324 */     this.mc.displayGuiScreen(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlayerSPHealth(float p_71150_1_) {
/* 332 */     if (this.field_175169_bQ) {
/*     */       
/* 334 */       float var2 = getHealth() - p_71150_1_;
/*     */       
/* 336 */       if (var2 <= 0.0F)
/*     */       {
/* 338 */         setHealth(p_71150_1_);
/*     */         
/* 340 */         if (var2 < 0.0F)
/*     */         {
/* 342 */           this.hurtResistantTime = this.maxHurtResistantTime / 2;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 347 */         this.lastDamage = var2;
/* 348 */         setHealth(getHealth());
/* 349 */         this.hurtResistantTime = this.maxHurtResistantTime;
/* 350 */         damageEntity(DamageSource.generic, var2);
/* 351 */         this.hurtTime = this.maxHurtTime = 10;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 356 */       setHealth(p_71150_1_);
/* 357 */       this.field_175169_bQ = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStat(StatBase p_71064_1_, int p_71064_2_) {
/* 366 */     if (p_71064_1_ != null)
/*     */     {
/* 368 */       if (p_71064_1_.isIndependent)
/*     */       {
/* 370 */         super.addStat(p_71064_1_, p_71064_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerAbilities() {
/* 380 */     this.sendQueue.addToSendQueue((Packet)new C13PacketPlayerAbilities(this.capabilities));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175144_cb() {
/* 385 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void sendHorseJump() {
/* 390 */     this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.RIDING_JUMP, (int)(getHorseJumpPower() * 100.0F)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175163_u() {
/* 395 */     this.sendQueue.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)this, C0BPacketEntityAction.Action.OPEN_INVENTORY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175158_f(String p_175158_1_) {
/* 400 */     this.clientBrand = p_175158_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClientBrand() {
/* 405 */     return this.clientBrand;
/*     */   }
/*     */ 
/*     */   
/*     */   public StatFileWriter getStatFileWriter() {
/* 410 */     return this.field_146108_bO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChatComponentMessage(IChatComponent p_146105_1_) {
/* 415 */     this.mc.ingameGUI.getChatGUI().printChatMessage(p_146105_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean pushOutOfBlocks(double x, double y, double z) {
/* 420 */     if (this.noClip)
/*     */     {
/* 422 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 426 */     BlockPos var7 = new BlockPos(x, y, z);
/* 427 */     double var8 = x - var7.getX();
/* 428 */     double var10 = z - var7.getZ();
/*     */     
/* 430 */     if (!func_175162_d(var7)) {
/*     */       
/* 432 */       byte var12 = -1;
/* 433 */       double var13 = 9999.0D;
/*     */       
/* 435 */       if (func_175162_d(var7.offsetWest()) && var8 < var13) {
/*     */         
/* 437 */         var13 = var8;
/* 438 */         var12 = 0;
/*     */       } 
/*     */       
/* 441 */       if (func_175162_d(var7.offsetEast()) && 1.0D - var8 < var13) {
/*     */         
/* 443 */         var13 = 1.0D - var8;
/* 444 */         var12 = 1;
/*     */       } 
/*     */       
/* 447 */       if (func_175162_d(var7.offsetNorth()) && var10 < var13) {
/*     */         
/* 449 */         var13 = var10;
/* 450 */         var12 = 4;
/*     */       } 
/*     */       
/* 453 */       if (func_175162_d(var7.offsetSouth()) && 1.0D - var10 < var13) {
/*     */         
/* 455 */         var13 = 1.0D - var10;
/* 456 */         var12 = 5;
/*     */       } 
/*     */       
/* 459 */       float var15 = 0.1F;
/*     */       
/* 461 */       if (var12 == 0)
/*     */       {
/* 463 */         this.motionX = -var15;
/*     */       }
/*     */       
/* 466 */       if (var12 == 1)
/*     */       {
/* 468 */         this.motionX = var15;
/*     */       }
/*     */       
/* 471 */       if (var12 == 4)
/*     */       {
/* 473 */         this.motionZ = -var15;
/*     */       }
/*     */       
/* 476 */       if (var12 == 5)
/*     */       {
/* 478 */         this.motionZ = var15;
/*     */       }
/*     */     } 
/*     */     
/* 482 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_175162_d(BlockPos p_175162_1_) {
/* 488 */     return (!this.worldObj.getBlockState(p_175162_1_).getBlock().isNormalCube() && !this.worldObj.getBlockState(p_175162_1_.offsetUp()).getBlock().isNormalCube());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSprinting(boolean sprinting) {
/* 496 */     super.setSprinting(sprinting);
/* 497 */     this.sprintingTicksLeft = sprinting ? 600 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXPStats(float p_71152_1_, int p_71152_2_, int p_71152_3_) {
/* 505 */     this.experience = p_71152_1_;
/* 506 */     this.experienceTotal = p_71152_2_;
/* 507 */     this.experienceLevel = p_71152_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChatMessage(IChatComponent message) {
/* 518 */     this.mc.ingameGUI.getChatGUI().printChatMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 527 */     return (permissionLevel <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPosition() {
/* 532 */     return new BlockPos(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(String name, float volume, float pitch) {
/* 537 */     this.worldObj.playSound(this.posX, this.posY, this.posZ, name, volume, pitch, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isServerWorld() {
/* 545 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRidingHorse() {
/* 550 */     return (this.ridingEntity != null && this.ridingEntity instanceof EntityHorse && ((EntityHorse)this.ridingEntity).isHorseSaddled());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHorseJumpPower() {
/* 555 */     return this.horseJumpPower;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175141_a(TileEntitySign p_175141_1_) {
/* 560 */     this.mc.displayGuiScreen((GuiScreen)new GuiEditSign(p_175141_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146095_a(CommandBlockLogic p_146095_1_) {
/* 565 */     this.mc.displayGuiScreen((GuiScreen)new GuiCommandBlock(p_146095_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIBook(ItemStack bookStack) {
/* 573 */     Item var2 = bookStack.getItem();
/*     */     
/* 575 */     if (var2 == Items.writable_book)
/*     */     {
/* 577 */       this.mc.displayGuiScreen((GuiScreen)new GuiScreenBook(this, bookStack, true));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayGUIChest(IInventory chestInventory) {
/* 586 */     String var2 = (chestInventory instanceof IInteractionObject) ? ((IInteractionObject)chestInventory).getGuiID() : "minecraft:container";
/*     */     
/* 588 */     if ("minecraft:chest".equals(var2)) {
/*     */       
/* 590 */       this.mc.displayGuiScreen((GuiScreen)new GuiChest((IInventory)this.inventory, chestInventory));
/*     */     }
/* 592 */     else if ("minecraft:hopper".equals(var2)) {
/*     */       
/* 594 */       this.mc.displayGuiScreen((GuiScreen)new GuiHopper(this.inventory, chestInventory));
/*     */     }
/* 596 */     else if ("minecraft:furnace".equals(var2)) {
/*     */       
/* 598 */       this.mc.displayGuiScreen((GuiScreen)new GuiFurnace(this.inventory, chestInventory));
/*     */     }
/* 600 */     else if ("minecraft:brewing_stand".equals(var2)) {
/*     */       
/* 602 */       this.mc.displayGuiScreen((GuiScreen)new GuiBrewingStand(this.inventory, chestInventory));
/*     */     }
/* 604 */     else if ("minecraft:beacon".equals(var2)) {
/*     */       
/* 606 */       this.mc.displayGuiScreen((GuiScreen)new GuiBeacon(this.inventory, chestInventory));
/*     */     }
/* 608 */     else if (!"minecraft:dispenser".equals(var2) && !"minecraft:dropper".equals(var2)) {
/*     */       
/* 610 */       this.mc.displayGuiScreen((GuiScreen)new GuiChest((IInventory)this.inventory, chestInventory));
/*     */     }
/*     */     else {
/*     */       
/* 614 */       this.mc.displayGuiScreen((GuiScreen)new GuiDispenser(this.inventory, chestInventory));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayGUIHorse(EntityHorse p_110298_1_, IInventory p_110298_2_) {
/* 620 */     this.mc.displayGuiScreen((GuiScreen)new GuiScreenHorseInventory((IInventory)this.inventory, p_110298_2_, p_110298_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayGui(IInteractionObject guiOwner) {
/* 625 */     String var2 = guiOwner.getGuiID();
/*     */     
/* 627 */     if ("minecraft:crafting_table".equals(var2)) {
/*     */       
/* 629 */       this.mc.displayGuiScreen((GuiScreen)new GuiCrafting(this.inventory, this.worldObj));
/*     */     }
/* 631 */     else if ("minecraft:enchanting_table".equals(var2)) {
/*     */       
/* 633 */       this.mc.displayGuiScreen((GuiScreen)new GuiEnchantment(this.inventory, this.worldObj, (IWorldNameable)guiOwner));
/*     */     }
/* 635 */     else if ("minecraft:anvil".equals(var2)) {
/*     */       
/* 637 */       this.mc.displayGuiScreen((GuiScreen)new GuiRepair(this.inventory, this.worldObj));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayVillagerTradeGui(IMerchant villager) {
/* 643 */     this.mc.displayGuiScreen((GuiScreen)new GuiMerchant(this.inventory, villager, this.worldObj));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCriticalHit(Entity p_71009_1_) {
/* 651 */     Client.getInstance(); if (Client.getModule("Damage Effects") != null) {
/* 652 */       Client.getInstance(); if (Client.getModule("Damage Effects").isEnabled() && 
/* 653 */         DamageEffects.particles.isEnabled()) {
/* 654 */         for (int i = 1; i <= DamageEffects.amount.getValue(); i++) {
/* 655 */           this.mc.effectRenderer.func_178926_a(p_71009_1_, EnumParticleTypes.CRIT);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 661 */     this.mc.effectRenderer.func_178926_a(p_71009_1_, EnumParticleTypes.CRIT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnchantmentCritical(Entity p_71047_1_) {
/* 666 */     this.mc.effectRenderer.func_178926_a(p_71047_1_, EnumParticleTypes.CRIT_MAGIC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSneaking() {
/* 674 */     boolean var1 = (this.movementInput != null) ? this.movementInput.sneak : false;
/* 675 */     return (var1 && !this.sleeping);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEntityActionState() {
/* 680 */     super.updateEntityActionState();
/*     */     
/* 682 */     if (func_175160_A()) {
/*     */       
/* 684 */       this.moveStrafing = this.movementInput.moveStrafe;
/* 685 */       this.moveForward = this.movementInput.moveForward;
/* 686 */       this.isJumping = this.movementInput.jump;
/* 687 */       this.prevRenderArmYaw = this.renderArmYaw;
/* 688 */       this.prevRenderArmPitch = this.renderArmPitch;
/* 689 */       this.renderArmPitch = (float)(this.renderArmPitch + (this.rotationPitch - this.renderArmPitch) * 0.5D);
/* 690 */       this.renderArmYaw = (float)(this.renderArmYaw + (this.rotationYaw - this.renderArmYaw) * 0.5D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_175160_A() {
/* 696 */     return (this.mc.func_175606_aa() == this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 705 */     if (this.sprintingTicksLeft > 0)
/*     */     {
/* 707 */       this.sprintingTicksLeft--;
/*     */     }
/*     */ 
/*     */     
/* 711 */     if (this.sprintToggleTimer > 0)
/*     */     {
/* 713 */       this.sprintToggleTimer--;
/*     */     }
/*     */     
/* 716 */     this.prevTimeInPortal = this.timeInPortal;
/*     */     
/* 718 */     if (this.inPortal) {
/*     */       
/* 720 */       if (this.mc.currentScreen != null && !this.mc.currentScreen.doesGuiPauseGame())
/*     */       {
/* 722 */         this.mc.displayGuiScreen(null);
/*     */       }
/*     */       
/* 725 */       if (this.timeInPortal == 0.0F)
/*     */       {
/* 727 */         this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("portal.trigger"), this.rand.nextFloat() * 0.4F + 0.8F));
/*     */       }
/*     */       
/* 730 */       this.timeInPortal += 0.0125F;
/*     */       
/* 732 */       if (this.timeInPortal >= 1.0F)
/*     */       {
/* 734 */         this.timeInPortal = 1.0F;
/*     */       }
/*     */       
/* 737 */       this.inPortal = false;
/*     */     }
/* 739 */     else if (isPotionActive(Potion.confusion) && getActivePotionEffect(Potion.confusion).getDuration() > 60) {
/*     */       
/* 741 */       this.timeInPortal += 0.006666667F;
/*     */       
/* 743 */       if (this.timeInPortal > 1.0F)
/*     */       {
/* 745 */         this.timeInPortal = 1.0F;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 750 */       if (this.timeInPortal > 0.0F)
/*     */       {
/* 752 */         this.timeInPortal -= 0.05F;
/*     */       }
/*     */       
/* 755 */       if (this.timeInPortal < 0.0F)
/*     */       {
/* 757 */         this.timeInPortal = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 761 */     if (this.timeUntilPortal > 0)
/*     */     {
/* 763 */       this.timeUntilPortal--;
/*     */     }
/*     */     
/* 766 */     boolean var1 = this.movementInput.jump;
/* 767 */     boolean var2 = this.movementInput.sneak;
/* 768 */     float var3 = 0.8F;
/* 769 */     boolean var4 = (this.movementInput.moveForward >= var3);
/* 770 */     this.movementInput.updatePlayerMoveState();
/*     */     
/* 772 */     if (isUsingItem() && !isRiding()) if (!Client.getModule("NoSlow").isEnabled()) {
/*     */         
/* 774 */         this.movementInput.moveStrafe *= 0.2F;
/* 775 */         this.movementInput.moveForward *= 0.2F;
/* 776 */         this.sprintToggleTimer = 0;
/*     */       } 
/*     */     
/* 779 */     pushOutOfBlocks(this.posX - this.width * 0.35D, (getEntityBoundingBox()).minY + 0.5D, this.posZ + this.width * 0.35D);
/* 780 */     pushOutOfBlocks(this.posX - this.width * 0.35D, (getEntityBoundingBox()).minY + 0.5D, this.posZ - this.width * 0.35D);
/* 781 */     pushOutOfBlocks(this.posX + this.width * 0.35D, (getEntityBoundingBox()).minY + 0.5D, this.posZ - this.width * 0.35D);
/* 782 */     pushOutOfBlocks(this.posX + this.width * 0.35D, (getEntityBoundingBox()).minY + 0.5D, this.posZ + this.width * 0.35D);
/* 783 */     boolean var5 = !(getFoodStats().getFoodLevel() <= 6.0F && !this.capabilities.allowFlying);
/*     */     
/* 785 */     if (this.onGround && !var2 && !var4 && this.movementInput.moveForward >= var3 && !isSprinting() && var5 && !isUsingItem() && !isPotionActive(Potion.blindness))
/*     */     {
/* 787 */       if (this.sprintToggleTimer <= 0 && !this.mc.gameSettings.keyBindSprint.getIsKeyPressed()) {
/*     */         
/* 789 */         this.sprintToggleTimer = 7;
/*     */       }
/*     */       else {
/*     */         
/* 793 */         setSprinting(true);
/*     */       } 
/*     */     }
/*     */     
/* 797 */     if (!isSprinting() && this.movementInput.moveForward >= var3 && var5 && !isUsingItem() && !isPotionActive(Potion.blindness) && this.mc.gameSettings.keyBindSprint.getIsKeyPressed())
/*     */     {
/* 799 */       setSprinting(true);
/*     */     }
/*     */     
/* 802 */     if (isSprinting() && (this.movementInput.moveForward < var3 || this.isCollidedHorizontally || !var5))
/*     */     {
/* 804 */       setSprinting(false);
/*     */     }
/*     */     
/* 807 */     if (this.capabilities.allowFlying)
/*     */     {
/* 809 */       if (this.mc.playerController.isSpectatorMode()) {
/*     */         
/* 811 */         if (!this.capabilities.isFlying)
/*     */         {
/* 813 */           this.capabilities.isFlying = true;
/* 814 */           sendPlayerAbilities();
/*     */         }
/*     */       
/* 817 */       } else if (!var1 && this.movementInput.jump) {
/*     */         
/* 819 */         if (this.flyToggleTimer == 0) {
/*     */           
/* 821 */           this.flyToggleTimer = 7;
/*     */         }
/*     */         else {
/*     */           
/* 825 */           this.capabilities.isFlying = !this.capabilities.isFlying;
/* 826 */           sendPlayerAbilities();
/* 827 */           this.flyToggleTimer = 0;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 832 */     if (this.capabilities.isFlying && func_175160_A()) {
/*     */       
/* 834 */       if (this.movementInput.sneak)
/*     */       {
/* 836 */         this.motionY -= (this.capabilities.getFlySpeed() * 3.0F);
/*     */       }
/*     */       
/* 839 */       if (this.movementInput.jump)
/*     */       {
/* 841 */         this.motionY += (this.capabilities.getFlySpeed() * 3.0F);
/*     */       }
/*     */     } 
/*     */     
/* 845 */     if (isRidingHorse()) {
/*     */       
/* 847 */       if (this.horseJumpPowerCounter < 0) {
/*     */         
/* 849 */         this.horseJumpPowerCounter++;
/*     */         
/* 851 */         if (this.horseJumpPowerCounter == 0)
/*     */         {
/* 853 */           this.horseJumpPower = 0.0F;
/*     */         }
/*     */       } 
/*     */       
/* 857 */       if (var1 && !this.movementInput.jump) {
/*     */         
/* 859 */         this.horseJumpPowerCounter = -10;
/* 860 */         sendHorseJump();
/*     */       }
/* 862 */       else if (!var1 && this.movementInput.jump) {
/*     */         
/* 864 */         this.horseJumpPowerCounter = 0;
/* 865 */         this.horseJumpPower = 0.0F;
/*     */       }
/* 867 */       else if (var1) {
/*     */         
/* 869 */         this.horseJumpPowerCounter++;
/*     */         
/* 871 */         if (this.horseJumpPowerCounter < 10)
/*     */         {
/* 873 */           this.horseJumpPower = this.horseJumpPowerCounter * 0.1F;
/*     */         }
/*     */         else
/*     */         {
/* 877 */           this.horseJumpPower = 0.8F + 2.0F / (this.horseJumpPowerCounter - 9) * 0.1F;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 883 */       this.horseJumpPower = 0.0F;
/*     */     } 
/*     */     
/* 886 */     super.onLivingUpdate();
/*     */     
/* 888 */     if (this.onGround && this.capabilities.isFlying && !this.mc.playerController.isSpectatorMode()) {
/*     */       
/* 890 */       this.capabilities.isFlying = false;
/* 891 */       sendPlayerAbilities();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\entity\EntityPlayerSP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */