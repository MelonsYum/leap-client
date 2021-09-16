/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public abstract class MovingSound
/*    */   extends PositionedSound
/*    */   implements ITickableSound {
/*    */   protected boolean donePlaying = false;
/*    */   private static final String __OBFID = "CL_00001117";
/*    */   
/*    */   protected MovingSound(ResourceLocation location) {
/* 12 */     super(location);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDonePlaying() {
/* 17 */     return this.donePlaying;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\MovingSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */