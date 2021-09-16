/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerData
/*     */ {
/*     */   public String serverName;
/*     */   public String serverIP;
/*     */   public String populationInfo;
/*     */   public String serverMOTD;
/*     */   public long pingToServer;
/*  26 */   public int version = 47;
/*     */ 
/*     */   
/*  29 */   public String gameVersion = "1.8";
/*     */   
/*     */   public boolean field_78841_f;
/*     */   public String playerList;
/*     */   private ServerResourceMode resourceMode;
/*     */   private String serverIcon;
/*     */   private static final String __OBFID = "CL_00000890";
/*     */   
/*     */   public ServerData(String p_i1193_1_, String p_i1193_2_) {
/*  38 */     this.resourceMode = ServerResourceMode.PROMPT;
/*  39 */     this.serverName = p_i1193_1_;
/*  40 */     this.serverIP = p_i1193_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTCompound() {
/*  48 */     NBTTagCompound var1 = new NBTTagCompound();
/*  49 */     var1.setString("name", this.serverName);
/*  50 */     var1.setString("ip", this.serverIP);
/*     */     
/*  52 */     if (this.serverIcon != null)
/*     */     {
/*  54 */       var1.setString("icon", this.serverIcon);
/*     */     }
/*     */     
/*  57 */     if (this.resourceMode == ServerResourceMode.ENABLED) {
/*     */       
/*  59 */       var1.setBoolean("acceptTextures", true);
/*     */     }
/*  61 */     else if (this.resourceMode == ServerResourceMode.DISABLED) {
/*     */       
/*  63 */       var1.setBoolean("acceptTextures", false);
/*     */     } 
/*     */     
/*  66 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerResourceMode getResourceMode() {
/*  71 */     return this.resourceMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResourceMode(ServerResourceMode mode) {
/*  76 */     this.resourceMode = mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerData getServerDataFromNBTCompound(NBTTagCompound nbtCompound) {
/*  84 */     ServerData var1 = new ServerData(nbtCompound.getString("name"), nbtCompound.getString("ip"));
/*     */     
/*  86 */     if (nbtCompound.hasKey("icon", 8))
/*     */     {
/*  88 */       var1.setBase64EncodedIconData(nbtCompound.getString("icon"));
/*     */     }
/*     */     
/*  91 */     if (nbtCompound.hasKey("acceptTextures", 1)) {
/*     */       
/*  93 */       if (nbtCompound.getBoolean("acceptTextures"))
/*     */       {
/*  95 */         var1.setResourceMode(ServerResourceMode.ENABLED);
/*     */       }
/*     */       else
/*     */       {
/*  99 */         var1.setResourceMode(ServerResourceMode.DISABLED);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 104 */       var1.setResourceMode(ServerResourceMode.PROMPT);
/*     */     } 
/*     */     
/* 107 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBase64EncodedIconData() {
/* 115 */     return this.serverIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase64EncodedIconData(String icon) {
/* 120 */     this.serverIcon = icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFrom(ServerData serverDataIn) {
/* 125 */     this.serverIP = serverDataIn.serverIP;
/* 126 */     this.serverName = serverDataIn.serverName;
/* 127 */     setResourceMode(serverDataIn.getResourceMode());
/* 128 */     this.serverIcon = serverDataIn.serverIcon;
/*     */   }
/*     */   
/*     */   public enum ServerResourceMode
/*     */   {
/* 133 */     ENABLED("ENABLED", 0, "enabled"),
/* 134 */     DISABLED("DISABLED", 1, "disabled"),
/* 135 */     PROMPT("PROMPT", 2, "prompt");
/*     */     
/*     */     private final IChatComponent motd;
/* 138 */     private static final ServerResourceMode[] $VALUES = new ServerResourceMode[] { ENABLED, DISABLED, PROMPT };
/*     */     
/*     */     private static final String __OBFID = "CL_00001833";
/*     */     
/*     */     ServerResourceMode(String p_i1053_1_, int p_i1053_2_, String p_i1053_3_) {
/* 143 */       this.motd = (IChatComponent)new ChatComponentTranslation("addServer.resourcePack." + p_i1053_3_, new Object[0]);
/*     */     } static {
/*     */     
/*     */     }
/*     */     public IChatComponent getMotd() {
/* 148 */       return this.motd;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\ServerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */