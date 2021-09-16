/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public interface ISound
/*    */ {
/*    */   ResourceLocation getSoundLocation();
/*    */   
/*    */   boolean canRepeat();
/*    */   
/*    */   int getRepeatDelay();
/*    */   
/*    */   float getVolume();
/*    */   
/*    */   float getPitch();
/*    */   
/*    */   float getXPosF();
/*    */   
/*    */   float getYPosF();
/*    */   
/*    */   float getZPosF();
/*    */   
/*    */   AttenuationType getAttenuationType();
/*    */   
/*    */   public enum AttenuationType
/*    */   {
/* 27 */     NONE("NONE", 0, 0),
/* 28 */     LINEAR("LINEAR", 1, 2);
/*    */     
/*    */     private final int type;
/* 31 */     private static final AttenuationType[] $VALUES = new AttenuationType[] { NONE, LINEAR };
/*    */     
/*    */     private static final String __OBFID = "CL_00001126";
/*    */     
/*    */     AttenuationType(String p_i45110_1_, int p_i45110_2_, int p_i45110_3_) {
/* 36 */       this.type = p_i45110_3_;
/*    */     } static {
/*    */     
/*    */     }
/*    */     public int getTypeInt() {
/* 41 */       return this.type;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\ISound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */