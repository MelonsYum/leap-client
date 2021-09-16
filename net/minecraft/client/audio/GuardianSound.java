/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.entity.monster.EntityGuardian;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuardianSound
/*    */   extends MovingSound
/*    */ {
/*    */   private final EntityGuardian guardian;
/*    */   private static final String __OBFID = "CL_00002381";
/*    */   
/*    */   public GuardianSound(EntityGuardian guardian) {
/* 13 */     super(new ResourceLocation("minecraft:mob.guardian.attack"));
/* 14 */     this.guardian = guardian;
/* 15 */     this.attenuationType = ISound.AttenuationType.NONE;
/* 16 */     this.repeat = true;
/* 17 */     this.repeatDelay = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (!this.guardian.isDead && this.guardian.func_175474_cn()) {
/*    */       
/* 27 */       this.xPosF = (float)this.guardian.posX;
/* 28 */       this.yPosF = (float)this.guardian.posY;
/* 29 */       this.zPosF = (float)this.guardian.posZ;
/* 30 */       float var1 = this.guardian.func_175477_p(0.0F);
/* 31 */       this.volume = 0.0F + 1.0F * var1 * var1;
/* 32 */       this.pitch = 0.7F + 0.5F * var1;
/*    */     }
/*    */     else {
/*    */       
/* 36 */       this.donePlaying = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\GuardianSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */