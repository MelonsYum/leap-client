/*     */ package net.minecraft.client.main;
/*     */ 
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.mojang.authlib.properties.PropertyMap;
/*     */ import java.io.File;
/*     */ import java.net.Authenticator;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.PasswordAuthentication;
/*     */ import java.net.Proxy;
/*     */ import java.util.List;
/*     */ import joptsimple.ArgumentAcceptingOptionSpec;
/*     */ import joptsimple.NonOptionArgumentSpec;
/*     */ import joptsimple.OptionParser;
/*     */ import joptsimple.OptionSet;
/*     */ import joptsimple.OptionSpec;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.Session;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   private static final String __OBFID = "CL_00001461";
/*     */   
/*     */   public static void main(String[] p_main_0_) {
/*  26 */     System.setProperty("java.net.preferIPv4Stack", "true");
/*  27 */     OptionParser var1 = new OptionParser();
/*  28 */     var1.allowsUnrecognizedOptions();
/*  29 */     var1.accepts("demo");
/*  30 */     var1.accepts("fullscreen");
/*  31 */     var1.accepts("checkGlErrors");
/*  32 */     ArgumentAcceptingOptionSpec var2 = var1.accepts("server").withRequiredArg();
/*  33 */     ArgumentAcceptingOptionSpec var3 = var1.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(25565), (Object[])new Integer[0]);
/*  34 */     ArgumentAcceptingOptionSpec var4 = var1.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."), (Object[])new File[0]);
/*  35 */     ArgumentAcceptingOptionSpec var5 = var1.accepts("assetsDir").withRequiredArg().ofType(File.class);
/*  36 */     ArgumentAcceptingOptionSpec var6 = var1.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
/*  37 */     ArgumentAcceptingOptionSpec var7 = var1.accepts("proxyHost").withRequiredArg();
/*  38 */     ArgumentAcceptingOptionSpec var8 = var1.accepts("proxyPort").withRequiredArg().defaultsTo("8080", (Object[])new String[0]).ofType(Integer.class);
/*  39 */     ArgumentAcceptingOptionSpec var9 = var1.accepts("proxyUser").withRequiredArg();
/*  40 */     ArgumentAcceptingOptionSpec var10 = var1.accepts("proxyPass").withRequiredArg();
/*  41 */     ArgumentAcceptingOptionSpec var11 = var1.accepts("username").withRequiredArg().defaultsTo("Player" + (Minecraft.getSystemTime() % 1000L), (Object[])new String[0]);
/*  42 */     ArgumentAcceptingOptionSpec var12 = var1.accepts("uuid").withRequiredArg();
/*  43 */     ArgumentAcceptingOptionSpec var13 = var1.accepts("accessToken").withRequiredArg().required();
/*  44 */     ArgumentAcceptingOptionSpec var14 = var1.accepts("version").withRequiredArg().required();
/*  45 */     ArgumentAcceptingOptionSpec var15 = var1.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(854), (Object[])new Integer[0]);
/*  46 */     ArgumentAcceptingOptionSpec var16 = var1.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(480), (Object[])new Integer[0]);
/*  47 */     ArgumentAcceptingOptionSpec var17 = var1.accepts("userProperties").withRequiredArg().required();
/*  48 */     ArgumentAcceptingOptionSpec var18 = var1.accepts("assetIndex").withRequiredArg();
/*  49 */     ArgumentAcceptingOptionSpec var19 = var1.accepts("userType").withRequiredArg().defaultsTo("legacy", (Object[])new String[0]);
/*  50 */     NonOptionArgumentSpec var20 = var1.nonOptions();
/*  51 */     OptionSet var21 = var1.parse(p_main_0_);
/*  52 */     List var22 = var21.valuesOf((OptionSpec)var20);
/*     */     
/*  54 */     if (!var22.isEmpty())
/*     */     {
/*  56 */       System.out.println("Completely ignored arguments: " + var22);
/*     */     }
/*     */     
/*  59 */     String var23 = (String)var21.valueOf((OptionSpec)var7);
/*  60 */     Proxy var24 = Proxy.NO_PROXY;
/*     */     
/*  62 */     if (var23 != null) {
/*     */       
/*     */       try {
/*     */         
/*  66 */         var24 = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(var23, ((Integer)var21.valueOf((OptionSpec)var8)).intValue()));
/*     */       }
/*  68 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     final String var25 = (String)var21.valueOf((OptionSpec)var9);
/*  75 */     final String var26 = (String)var21.valueOf((OptionSpec)var10);
/*     */     
/*  77 */     if (!var24.equals(Proxy.NO_PROXY) && func_110121_a(var25) && func_110121_a(var26))
/*     */     {
/*  79 */       Authenticator.setDefault(new Authenticator()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000828";
/*     */             
/*     */             protected PasswordAuthentication getPasswordAuthentication() {
/*  84 */               return new PasswordAuthentication(var25, var26.toCharArray());
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*  89 */     int var27 = ((Integer)var21.valueOf((OptionSpec)var15)).intValue();
/*  90 */     int var28 = ((Integer)var21.valueOf((OptionSpec)var16)).intValue();
/*  91 */     boolean var29 = var21.has("fullscreen");
/*  92 */     boolean var30 = var21.has("checkGlErrors");
/*  93 */     boolean var31 = var21.has("demo");
/*  94 */     String var32 = (String)var21.valueOf((OptionSpec)var14);
/*  95 */     PropertyMap var33 = (PropertyMap)(new GsonBuilder()).registerTypeAdapter(PropertyMap.class, new PropertyMap.Serializer()).create().fromJson((String)var21.valueOf((OptionSpec)var17), PropertyMap.class);
/*  96 */     File var34 = (File)var21.valueOf((OptionSpec)var4);
/*  97 */     File var35 = var21.has((OptionSpec)var5) ? (File)var21.valueOf((OptionSpec)var5) : new File(var34, "assets/");
/*  98 */     File var36 = var21.has((OptionSpec)var6) ? (File)var21.valueOf((OptionSpec)var6) : new File(var34, "resourcepacks/");
/*  99 */     String var37 = var21.has((OptionSpec)var12) ? (String)var12.value(var21) : (String)var11.value(var21);
/* 100 */     String var38 = var21.has((OptionSpec)var18) ? (String)var18.value(var21) : null;
/* 101 */     String var39 = (String)var21.valueOf((OptionSpec)var2);
/* 102 */     Integer var40 = (Integer)var21.valueOf((OptionSpec)var3);
/* 103 */     Session var41 = new Session((String)var11.value(var21), var37, (String)var13.value(var21), (String)var19.value(var21));
/* 104 */     GameConfiguration var42 = new GameConfiguration(new GameConfiguration.UserInformation(var41, var33, var24), new GameConfiguration.DisplayInformation(var27, var28, var29, var30), new GameConfiguration.FolderInformation(var34, var36, var35, var38), new GameConfiguration.GameInformation(var31, var32), new GameConfiguration.ServerInformation(var39, var40.intValue()));
/* 105 */     Runtime.getRuntime().addShutdownHook(new Thread("Client Shutdown Thread")
/*     */         {
/*     */           private static final String __OBFID = "CL_00000829";
/*     */           
/*     */           public void run() {
/* 110 */             Minecraft.stopIntegratedServer();
/*     */           }
/*     */         });
/* 113 */     Thread.currentThread().setName("Client thread");
/* 114 */     (new Minecraft(var42)).run();
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_110121_a(String p_110121_0_) {
/* 119 */     return (p_110121_0_ != null && !p_110121_0_.isEmpty());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\main\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */