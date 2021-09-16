/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public abstract class PositionedSound
/*    */   implements ISound {
/*    */   protected final ResourceLocation positionedSoundLocation;
/*  8 */   protected float volume = 1.0F;
/*  9 */   protected float pitch = 1.0F;
/*    */   
/*    */   protected float xPosF;
/*    */   
/*    */   protected float yPosF;
/*    */   protected float zPosF;
/*    */   protected boolean repeat = false;
/* 16 */   protected int repeatDelay = 0;
/*    */   
/*    */   protected ISound.AttenuationType attenuationType;
/*    */   private static final String __OBFID = "CL_00001116";
/*    */   
/*    */   protected PositionedSound(ResourceLocation soundResource) {
/* 22 */     this.attenuationType = ISound.AttenuationType.LINEAR;
/* 23 */     this.positionedSoundLocation = soundResource;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getSoundLocation() {
/* 28 */     return this.positionedSoundLocation;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRepeat() {
/* 33 */     return this.repeat;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRepeatDelay() {
/* 38 */     return this.repeatDelay;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getVolume() {
/* 43 */     return this.volume;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPitch() {
/* 48 */     return this.pitch;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getXPosF() {
/* 53 */     return this.xPosF;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getYPosF() {
/* 58 */     return this.yPosF;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getZPosF() {
/* 63 */     return this.zPosF;
/*    */   }
/*    */ 
/*    */   
/*    */   public ISound.AttenuationType getAttenuationType() {
/* 68 */     return this.attenuationType;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\PositionedSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */