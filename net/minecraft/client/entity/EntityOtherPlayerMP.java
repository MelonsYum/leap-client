/*     */ package net.minecraft.client.entity;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityOtherPlayerMP
/*     */   extends AbstractClientPlayer
/*     */ {
/*     */   private boolean isItemInUse;
/*     */   private int otherPlayerMPPosRotationIncrements;
/*     */   private double otherPlayerMPX;
/*     */   private double otherPlayerMPY;
/*     */   private double otherPlayerMPZ;
/*     */   private double otherPlayerMPYaw;
/*     */   private double otherPlayerMPPitch;
/*     */   private static final String __OBFID = "CL_00000939";
/*     */   
/*     */   public EntityOtherPlayerMP(World worldIn, GameProfile p_i45075_2_) {
/*  25 */     super(worldIn, p_i45075_2_);
/*  26 */     this.stepHeight = 0.0F;
/*  27 */     this.noClip = true;
/*  28 */     this.field_71082_cx = 0.25F;
/*  29 */     this.renderDistanceWeight = 10.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/*  42 */     this.otherPlayerMPX = p_180426_1_;
/*  43 */     this.otherPlayerMPY = p_180426_3_;
/*  44 */     this.otherPlayerMPZ = p_180426_5_;
/*  45 */     this.otherPlayerMPYaw = p_180426_7_;
/*  46 */     this.otherPlayerMPPitch = p_180426_8_;
/*  47 */     this.otherPlayerMPPosRotationIncrements = p_180426_9_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  55 */     this.field_71082_cx = 0.0F;
/*  56 */     super.onUpdate();
/*  57 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/*  58 */     double var1 = this.posX - this.prevPosX;
/*  59 */     double var3 = this.posZ - this.prevPosZ;
/*  60 */     float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3) * 4.0F;
/*     */     
/*  62 */     if (var5 > 1.0F)
/*     */     {
/*  64 */       var5 = 1.0F;
/*     */     }
/*     */     
/*  67 */     this.limbSwingAmount += (var5 - this.limbSwingAmount) * 0.4F;
/*  68 */     this.limbSwing += this.limbSwingAmount;
/*     */     
/*  70 */     if (!this.isItemInUse && isEating() && this.inventory.mainInventory[this.inventory.currentItem] != null) {
/*     */       
/*  72 */       ItemStack var6 = this.inventory.mainInventory[this.inventory.currentItem];
/*  73 */       setItemInUse(this.inventory.mainInventory[this.inventory.currentItem], var6.getItem().getMaxItemUseDuration(var6));
/*  74 */       this.isItemInUse = true;
/*     */     }
/*  76 */     else if (this.isItemInUse && !isEating()) {
/*     */       
/*  78 */       clearItemInUse();
/*  79 */       this.isItemInUse = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  89 */     if (this.otherPlayerMPPosRotationIncrements > 0) {
/*     */       
/*  91 */       double var1 = this.posX + (this.otherPlayerMPX - this.posX) / this.otherPlayerMPPosRotationIncrements;
/*  92 */       double var3 = this.posY + (this.otherPlayerMPY - this.posY) / this.otherPlayerMPPosRotationIncrements;
/*  93 */       double var5 = this.posZ + (this.otherPlayerMPZ - this.posZ) / this.otherPlayerMPPosRotationIncrements;
/*     */       
/*     */       double var7;
/*  96 */       for (var7 = this.otherPlayerMPYaw - this.rotationYaw; var7 < -180.0D; var7 += 360.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 101 */       while (var7 >= 180.0D)
/*     */       {
/* 103 */         var7 -= 360.0D;
/*     */       }
/*     */       
/* 106 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.otherPlayerMPPosRotationIncrements);
/* 107 */       this.rotationPitch = (float)(this.rotationPitch + (this.otherPlayerMPPitch - this.rotationPitch) / this.otherPlayerMPPosRotationIncrements);
/* 108 */       this.otherPlayerMPPosRotationIncrements--;
/* 109 */       setPosition(var1, var3, var5);
/* 110 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */     } 
/*     */     
/* 113 */     this.prevCameraYaw = this.cameraYaw;
/* 114 */     updateArmSwingProgress();
/* 115 */     float var9 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 116 */     float var2 = (float)Math.atan(-this.motionY * 0.20000000298023224D) * 15.0F;
/*     */     
/* 118 */     if (var9 > 0.1F)
/*     */     {
/* 120 */       var9 = 0.1F;
/*     */     }
/*     */     
/* 123 */     if (!this.onGround || getHealth() <= 0.0F)
/*     */     {
/* 125 */       var9 = 0.0F;
/*     */     }
/*     */     
/* 128 */     if (this.onGround || getHealth() <= 0.0F)
/*     */     {
/* 130 */       var2 = 0.0F;
/*     */     }
/*     */     
/* 133 */     this.cameraYaw += (var9 - this.cameraYaw) * 0.4F;
/* 134 */     this.cameraPitch += (var2 - this.cameraPitch) * 0.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn) {
/* 142 */     if (slotIn == 0) {
/*     */       
/* 144 */       this.inventory.mainInventory[this.inventory.currentItem] = itemStackIn;
/*     */     }
/*     */     else {
/*     */       
/* 148 */       this.inventory.armorInventory[slotIn - 1] = itemStackIn;
/*     */     } 
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
/* 160 */     (Minecraft.getMinecraft()).ingameGUI.getChatGUI().printChatMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPosition() {
/* 173 */     return new BlockPos(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\entity\EntityOtherPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */