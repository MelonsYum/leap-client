/*    */ package net.minecraft.util;
/*    */ 
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class MovementInputFromOptions
/*    */   extends MovementInput
/*    */ {
/*    */   private final GameSettings gameSettings;
/*    */   private static final String __OBFID = "CL_00000937";
/*    */   
/*    */   public MovementInputFromOptions(GameSettings p_i1237_1_) {
/* 12 */     this.gameSettings = p_i1237_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updatePlayerMoveState() {
/* 17 */     this.moveStrafe = 0.0F;
/* 18 */     this.moveForward = 0.0F;
/*    */     
/* 20 */     if (this.gameSettings.keyBindForward.getIsKeyPressed())
/*    */     {
/* 22 */       this.moveForward++;
/*    */     }
/*    */     
/* 25 */     if (this.gameSettings.keyBindBack.getIsKeyPressed())
/*    */     {
/* 27 */       this.moveForward--;
/*    */     }
/*    */     
/* 30 */     if (this.gameSettings.keyBindLeft.getIsKeyPressed())
/*    */     {
/* 32 */       this.moveStrafe++;
/*    */     }
/*    */     
/* 35 */     if (this.gameSettings.keyBindRight.getIsKeyPressed())
/*    */     {
/* 37 */       this.moveStrafe--;
/*    */     }
/*    */     
/* 40 */     this.jump = this.gameSettings.keyBindJump.getIsKeyPressed();
/* 41 */     this.sneak = this.gameSettings.keyBindSneak.getIsKeyPressed();
/*    */     
/* 43 */     if (this.sneak) {
/*    */       
/* 45 */       this.moveStrafe = (float)(this.moveStrafe * 0.3D);
/* 46 */       this.moveForward = (float)(this.moveForward * 0.3D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MovementInputFromOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */