/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.util.UUIDTypeAdapter;
/*    */ import java.net.Proxy;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.util.Session;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ public class Realms
/*    */ {
/*    */   private static final String __OBFID = "CL_00001892";
/*    */   
/*    */   public static boolean isTouchScreen() {
/* 16 */     return (Minecraft.getMinecraft()).gameSettings.touchscreen;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Proxy getProxy() {
/* 21 */     return Minecraft.getMinecraft().getProxy();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String sessionId() {
/* 26 */     Session var0 = Minecraft.getMinecraft().getSession();
/* 27 */     return (var0 == null) ? null : var0.getSessionID();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String userName() {
/* 32 */     Session var0 = Minecraft.getMinecraft().getSession();
/* 33 */     return (var0 == null) ? null : var0.getUsername();
/*    */   }
/*    */ 
/*    */   
/*    */   public static long currentTimeMillis() {
/* 38 */     return Minecraft.getSystemTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getSessionId() {
/* 43 */     return Minecraft.getMinecraft().getSession().getSessionID();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getName() {
/* 48 */     return Minecraft.getMinecraft().getSession().getUsername();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String uuidToName(String p_uuidToName_0_) {
/* 53 */     return Minecraft.getMinecraft().getSessionService().fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(p_uuidToName_0_), null), false).getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setScreen(RealmsScreen p_setScreen_0_) {
/* 58 */     Minecraft.getMinecraft().displayGuiScreen((GuiScreen)p_setScreen_0_.getProxy());
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getGameDirectoryPath() {
/* 63 */     return (Minecraft.getMinecraft()).mcDataDir.getAbsolutePath();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int survivalId() {
/* 68 */     return WorldSettings.GameType.SURVIVAL.getID();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int creativeId() {
/* 73 */     return WorldSettings.GameType.CREATIVE.getID();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int adventureId() {
/* 78 */     return WorldSettings.GameType.ADVENTURE.getID();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\Realms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */