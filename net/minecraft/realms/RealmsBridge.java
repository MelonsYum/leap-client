/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class RealmsBridge
/*    */   extends RealmsScreen {
/* 11 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private GuiScreen previousScreen;
/*    */   private static final String __OBFID = "CL_00001869";
/*    */   
/*    */   public void switchToRealms(GuiScreen p_switchToRealms_1_) {
/* 17 */     this.previousScreen = p_switchToRealms_1_;
/*    */ 
/*    */     
/*    */     try {
/* 21 */       Class<?> var2 = Class.forName("com.mojang.realmsclient.RealmsMainScreen");
/* 22 */       Constructor<?> var3 = var2.getDeclaredConstructor(new Class[] { RealmsScreen.class });
/* 23 */       var3.setAccessible(true);
/* 24 */       Object var4 = var3.newInstance(new Object[] { this });
/* 25 */       Minecraft.getMinecraft().displayGuiScreen((GuiScreen)((RealmsScreen)var4).getProxy());
/*    */     }
/* 27 */     catch (Exception var5) {
/*    */       
/* 29 */       LOGGER.error("Realms module missing", var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {
/* 35 */     Minecraft.getMinecraft().displayGuiScreen(this.previousScreen);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */