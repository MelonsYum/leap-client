/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.entity.item.EntityMinecart;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class MovingSoundMinecart
/*    */   extends MovingSound {
/*    */   private final EntityMinecart minecart;
/* 10 */   private float field_147669_l = 0.0F;
/*    */   
/*    */   private static final String __OBFID = "CL_00001118";
/*    */   
/*    */   public MovingSoundMinecart(EntityMinecart p_i45105_1_) {
/* 15 */     super(new ResourceLocation("minecraft:minecart.base"));
/* 16 */     this.minecart = p_i45105_1_;
/* 17 */     this.repeat = true;
/* 18 */     this.repeatDelay = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.minecart.isDead) {
/*    */       
/* 28 */       this.donePlaying = true;
/*    */     }
/*    */     else {
/*    */       
/* 32 */       this.xPosF = (float)this.minecart.posX;
/* 33 */       this.yPosF = (float)this.minecart.posY;
/* 34 */       this.zPosF = (float)this.minecart.posZ;
/* 35 */       float var1 = MathHelper.sqrt_double(this.minecart.motionX * this.minecart.motionX + this.minecart.motionZ * this.minecart.motionZ);
/*    */       
/* 37 */       if (var1 >= 0.01D) {
/*    */         
/* 39 */         this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
/* 40 */         this.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 0.5F) * 0.7F;
/*    */       }
/*    */       else {
/*    */         
/* 44 */         this.field_147669_l = 0.0F;
/* 45 */         this.volume = 0.0F;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\MovingSoundMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */