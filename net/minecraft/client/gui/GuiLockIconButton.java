/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ public class GuiLockIconButton
/*    */   extends GuiButton
/*    */ {
/*    */   private boolean field_175231_o = false;
/*    */   private static final String __OBFID = "CL_00001952";
/*    */   
/*    */   public GuiLockIconButton(int p_i45538_1_, int p_i45538_2_, int p_i45538_3_) {
/* 13 */     super(p_i45538_1_, p_i45538_2_, p_i45538_3_, 20, 20, "");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_175230_c() {
/* 18 */     return this.field_175231_o;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_175229_b(boolean p_175229_1_) {
/* 23 */     this.field_175231_o = p_175229_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/* 31 */     if (this.visible) {
/*    */       Icon var5;
/* 33 */       mc.getTextureManager().bindTexture(GuiButton.buttonTextures);
/* 34 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 35 */       boolean var4 = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/*    */ 
/*    */       
/* 38 */       if (this.field_175231_o) {
/*    */         
/* 40 */         if (!this.enabled)
/*    */         {
/* 42 */           var5 = Icon.LOCKED_DISABLED;
/*    */         }
/* 44 */         else if (var4)
/*    */         {
/* 46 */           var5 = Icon.LOCKED_HOVER;
/*    */         }
/*    */         else
/*    */         {
/* 50 */           var5 = Icon.LOCKED;
/*    */         }
/*    */       
/* 53 */       } else if (!this.enabled) {
/*    */         
/* 55 */         var5 = Icon.UNLOCKED_DISABLED;
/*    */       }
/* 57 */       else if (var4) {
/*    */         
/* 59 */         var5 = Icon.UNLOCKED_HOVER;
/*    */       }
/*    */       else {
/*    */         
/* 63 */         var5 = Icon.UNLOCKED;
/*    */       } 
/*    */       
/* 66 */       drawTexturedModalRect(this.xPosition, this.yPosition, var5.func_178910_a(), var5.func_178912_b(), this.width, this.height);
/*    */     } 
/*    */   }
/*    */   
/*    */   enum Icon
/*    */   {
/* 72 */     LOCKED("LOCKED", 0, 0, 146),
/* 73 */     LOCKED_HOVER("LOCKED_HOVER", 1, 0, 166),
/* 74 */     LOCKED_DISABLED("LOCKED_DISABLED", 2, 0, 186),
/* 75 */     UNLOCKED("UNLOCKED", 3, 20, 146),
/* 76 */     UNLOCKED_HOVER("UNLOCKED_HOVER", 4, 20, 166),
/* 77 */     UNLOCKED_DISABLED("UNLOCKED_DISABLED", 5, 20, 186);
/*    */     
/*    */     private final int field_178914_g;
/*    */     private final int field_178920_h;
/* 81 */     private static final Icon[] $VALUES = new Icon[] { LOCKED, LOCKED_HOVER, LOCKED_DISABLED, UNLOCKED, UNLOCKED_HOVER, UNLOCKED_DISABLED };
/*    */     
/*    */     private static final String __OBFID = "CL_00001951";
/*    */     
/*    */     Icon(String p_i45537_1_, int p_i45537_2_, int p_i45537_3_, int p_i45537_4_) {
/* 86 */       this.field_178914_g = p_i45537_3_;
/* 87 */       this.field_178920_h = p_i45537_4_;
/*    */     } static {
/*    */     
/*    */     }
/*    */     public int func_178910_a() {
/* 92 */       return this.field_178914_g;
/*    */     }
/*    */ 
/*    */     
/*    */     public int func_178912_b() {
/* 97 */       return this.field_178920_h;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiLockIconButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */