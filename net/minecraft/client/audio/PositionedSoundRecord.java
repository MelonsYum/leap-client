/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class PositionedSoundRecord
/*    */   extends PositionedSound
/*    */ {
/*    */   private static final String __OBFID = "CL_00001120";
/*    */   
/*    */   public static PositionedSoundRecord createPositionedSoundRecord(ResourceLocation soundResource, float pitch) {
/* 11 */     return new PositionedSoundRecord(soundResource, 0.25F, pitch, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static PositionedSoundRecord createPositionedSoundRecord(ResourceLocation soundResource) {
/* 16 */     return new PositionedSoundRecord(soundResource, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static PositionedSoundRecord createRecordSoundAtPosition(ResourceLocation soundResource, float xPosition, float yPosition, float zPosition) {
/* 21 */     return new PositionedSoundRecord(soundResource, 4.0F, 1.0F, false, 0, ISound.AttenuationType.LINEAR, xPosition, yPosition, zPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionedSoundRecord(ResourceLocation soundResource, float volume, float pitch, float xPosition, float yPosition, float zPosition) {
/* 26 */     this(soundResource, volume, pitch, false, 0, ISound.AttenuationType.LINEAR, xPosition, yPosition, zPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   private PositionedSoundRecord(ResourceLocation soundResource, float volume, float pitch, boolean repeat, int repeatDelay, ISound.AttenuationType attenuationType, float xPosition, float yPosition, float zPosition) {
/* 31 */     super(soundResource);
/* 32 */     this.volume = volume;
/* 33 */     this.pitch = pitch;
/* 34 */     this.xPosF = xPosition;
/* 35 */     this.yPosF = yPosition;
/* 36 */     this.zPosF = zPosition;
/* 37 */     this.repeat = repeat;
/* 38 */     this.repeatDelay = repeatDelay;
/* 39 */     this.attenuationType = attenuationType;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\PositionedSoundRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */