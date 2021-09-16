/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityMinecart;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class MovingSoundMinecartRiding
/*    */   extends MovingSound
/*    */ {
/*    */   private final EntityPlayer player;
/*    */   private final EntityMinecart minecart;
/*    */   private static final String __OBFID = "CL_00001119";
/*    */   
/*    */   public MovingSoundMinecartRiding(EntityPlayer p_i45106_1_, EntityMinecart minecart) {
/* 16 */     super(new ResourceLocation("minecraft:minecart.inside"));
/* 17 */     this.player = p_i45106_1_;
/* 18 */     this.minecart = minecart;
/* 19 */     this.attenuationType = ISound.AttenuationType.NONE;
/* 20 */     this.repeat = true;
/* 21 */     this.repeatDelay = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (!this.minecart.isDead && this.player.isRiding() && this.player.ridingEntity == this.minecart) {
/*    */       
/* 31 */       float var1 = MathHelper.sqrt_double(this.minecart.motionX * this.minecart.motionX + this.minecart.motionZ * this.minecart.motionZ);
/*    */       
/* 33 */       if (var1 >= 0.01D)
/*    */       {
/* 35 */         this.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 1.0F) * 0.75F;
/*    */       }
/*    */       else
/*    */       {
/* 39 */         this.volume = 0.0F;
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 44 */       this.donePlaying = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\MovingSoundMinecartRiding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */