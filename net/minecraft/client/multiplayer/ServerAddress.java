/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import java.net.IDN;
/*     */ import java.util.Hashtable;
/*     */ import javax.naming.directory.Attributes;
/*     */ import javax.naming.directory.InitialDirContext;
/*     */ 
/*     */ 
/*     */ public class ServerAddress
/*     */ {
/*     */   private final String ipAddress;
/*     */   private final int serverPort;
/*     */   private static final String __OBFID = "CL_00000889";
/*     */   
/*     */   private ServerAddress(String p_i1192_1_, int p_i1192_2_) {
/*  16 */     this.ipAddress = p_i1192_1_;
/*  17 */     this.serverPort = p_i1192_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIP() {
/*  22 */     return IDN.toASCII(this.ipAddress);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPort() {
/*  27 */     return this.serverPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ServerAddress func_78860_a(String p_78860_0_) {
/*  32 */     if (p_78860_0_ == null)
/*     */     {
/*  34 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  38 */     String[] var1 = p_78860_0_.split(":");
/*     */     
/*  40 */     if (p_78860_0_.startsWith("[")) {
/*     */       
/*  42 */       int var2 = p_78860_0_.indexOf("]");
/*     */       
/*  44 */       if (var2 > 0) {
/*     */         
/*  46 */         String var3 = p_78860_0_.substring(1, var2);
/*  47 */         String var4 = p_78860_0_.substring(var2 + 1).trim();
/*     */         
/*  49 */         if (var4.startsWith(":") && var4.length() > 0) {
/*     */           
/*  51 */           var4 = var4.substring(1);
/*  52 */           var1 = new String[] { var3, var4 };
/*     */         }
/*     */         else {
/*     */           
/*  56 */           var1 = new String[] { var3 };
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     if (var1.length > 2)
/*     */     {
/*  63 */       var1 = new String[] { p_78860_0_ };
/*     */     }
/*     */     
/*  66 */     String var5 = var1[0];
/*  67 */     int var6 = (var1.length > 1) ? parseIntWithDefault(var1[1], 25565) : 25565;
/*     */     
/*  69 */     if (var6 == 25565) {
/*     */       
/*  71 */       String[] var7 = getServerAddress(var5);
/*  72 */       var5 = var7[0];
/*  73 */       var6 = parseIntWithDefault(var7[1], 25565);
/*     */     } 
/*     */     
/*  76 */     return new ServerAddress(var5, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] getServerAddress(String p_78863_0_) {
/*     */     try {
/*  87 */       String var1 = "com.sun.jndi.dns.DnsContextFactory";
/*  88 */       Class.forName("com.sun.jndi.dns.DnsContextFactory");
/*  89 */       Hashtable<Object, Object> var2 = new Hashtable<>();
/*  90 */       var2.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
/*  91 */       var2.put("java.naming.provider.url", "dns:");
/*  92 */       var2.put("com.sun.jndi.dns.timeout.retries", "1");
/*  93 */       InitialDirContext var3 = new InitialDirContext(var2);
/*  94 */       Attributes var4 = var3.getAttributes("_minecraft._tcp." + p_78863_0_, new String[] { "SRV" });
/*  95 */       String[] var5 = var4.get("srv").get().toString().split(" ", 4);
/*  96 */       return new String[] { var5[3], var5[2] };
/*     */     }
/*  98 */     catch (Throwable var6) {
/*     */       
/* 100 */       return new String[] { p_78863_0_, Integer.toString(25565) };
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseIntWithDefault(String p_78862_0_, int p_78862_1_) {
/*     */     try {
/* 108 */       return Integer.parseInt(p_78862_0_.trim());
/*     */     }
/* 110 */     catch (Exception var3) {
/*     */       
/* 112 */       return p_78862_1_;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\ServerAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */