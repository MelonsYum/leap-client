/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ public class GuiUtilRenderComponents
/*     */ {
/*     */   private static final String __OBFID = "CL_00001957";
/*     */   
/*     */   public static String func_178909_a(String p_178909_0_, boolean p_178909_1_) {
/*  17 */     return (!p_178909_1_ && !(Minecraft.getMinecraft()).gameSettings.chatColours) ? EnumChatFormatting.getTextWithoutFormattingCodes(p_178909_0_) : p_178909_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List func_178908_a(IChatComponent p_178908_0_, int p_178908_1_, FontRenderer p_178908_2_, boolean p_178908_3_, boolean p_178908_4_) {
/*  22 */     int var5 = 0;
/*  23 */     ChatComponentText var6 = new ChatComponentText("");
/*  24 */     ArrayList<ChatComponentText> var7 = Lists.newArrayList();
/*  25 */     ArrayList<IChatComponent> var8 = Lists.newArrayList((Iterable)p_178908_0_);
/*     */     
/*  27 */     for (int var9 = 0; var9 < var8.size(); var9++) {
/*     */       
/*  29 */       IChatComponent var10 = var8.get(var9);
/*  30 */       String var11 = var10.getUnformattedTextForChat();
/*  31 */       boolean var12 = false;
/*     */ 
/*     */       
/*  34 */       if (var11.contains("\n")) {
/*     */         
/*  36 */         int var13 = var11.indexOf('\n');
/*  37 */         String str = var11.substring(var13 + 1);
/*  38 */         var11 = var11.substring(0, var13 + 1);
/*  39 */         ChatComponentText var15 = new ChatComponentText(str);
/*  40 */         var15.setChatStyle(var10.getChatStyle().createShallowCopy());
/*  41 */         var8.add(var9 + 1, var15);
/*  42 */         var12 = true;
/*     */       } 
/*     */       
/*  45 */       String var21 = func_178909_a(String.valueOf(var10.getChatStyle().getFormattingCode()) + var11, p_178908_4_);
/*  46 */       String var14 = var21.endsWith("\n") ? var21.substring(0, var21.length() - 1) : var21;
/*  47 */       int var22 = p_178908_2_.getStringWidth(var14);
/*  48 */       ChatComponentText var16 = new ChatComponentText(var14);
/*  49 */       var16.setChatStyle(var10.getChatStyle().createShallowCopy());
/*     */       
/*  51 */       if (var5 + var22 > p_178908_1_) {
/*     */         
/*  53 */         String var17 = p_178908_2_.trimStringToWidth(var21, p_178908_1_ - var5, false);
/*  54 */         String var18 = (var17.length() < var21.length()) ? var21.substring(var17.length()) : null;
/*     */         
/*  56 */         if (var18 != null && var18.length() > 0) {
/*     */           
/*  58 */           int var19 = var17.lastIndexOf(" ");
/*     */           
/*  60 */           if (var19 >= 0 && p_178908_2_.getStringWidth(var21.substring(0, var19)) > 0) {
/*     */             
/*  62 */             var17 = var21.substring(0, var19);
/*     */             
/*  64 */             if (p_178908_3_)
/*     */             {
/*  66 */               var19++;
/*     */             }
/*     */             
/*  69 */             var18 = var21.substring(var19);
/*     */           }
/*  71 */           else if (var5 > 0 && !var21.contains(" ")) {
/*     */             
/*  73 */             var17 = "";
/*  74 */             var18 = var21;
/*     */           } 
/*     */           
/*  77 */           ChatComponentText var20 = new ChatComponentText(var18);
/*  78 */           var20.setChatStyle(var10.getChatStyle().createShallowCopy());
/*  79 */           var8.add(var9 + 1, var20);
/*     */         } 
/*     */         
/*  82 */         var22 = p_178908_2_.getStringWidth(var17);
/*  83 */         var16 = new ChatComponentText(var17);
/*  84 */         var16.setChatStyle(var10.getChatStyle().createShallowCopy());
/*  85 */         var12 = true;
/*     */       } 
/*     */       
/*  88 */       if (var5 + var22 <= p_178908_1_) {
/*     */         
/*  90 */         var5 += var22;
/*  91 */         var6.appendSibling((IChatComponent)var16);
/*     */       }
/*     */       else {
/*     */         
/*  95 */         var12 = true;
/*     */       } 
/*     */       
/*  98 */       if (var12) {
/*     */         
/* 100 */         var7.add(var6);
/* 101 */         var5 = 0;
/* 102 */         var6 = new ChatComponentText("");
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     var7.add(var6);
/* 107 */     return var7;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiUtilRenderComponents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */