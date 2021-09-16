/*     */ package net.minecraft.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class ChatComponentScore
/*     */   extends ChatComponentStyle {
/*     */   private final String field_179999_b;
/*     */   private final String field_180000_c;
/*  13 */   private String field_179998_d = "";
/*     */   
/*     */   private static final String __OBFID = "CL_00002309";
/*     */   
/*     */   public ChatComponentScore(String p_i45997_1_, String p_i45997_2_) {
/*  18 */     this.field_179999_b = p_i45997_1_;
/*  19 */     this.field_180000_c = p_i45997_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_179995_g() {
/*  24 */     return this.field_179999_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_179994_h() {
/*  29 */     return this.field_180000_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179997_b(String p_179997_1_) {
/*  34 */     this.field_179998_d = p_179997_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnformattedTextForChat() {
/*  43 */     MinecraftServer var1 = MinecraftServer.getServer();
/*     */     
/*  45 */     if (var1 != null && var1.func_175578_N() && StringUtils.isNullOrEmpty(this.field_179998_d)) {
/*     */       
/*  47 */       Scoreboard var2 = var1.worldServerForDimension(0).getScoreboard();
/*  48 */       ScoreObjective var3 = var2.getObjective(this.field_180000_c);
/*     */       
/*  50 */       if (var2.func_178819_b(this.field_179999_b, var3)) {
/*     */         
/*  52 */         Score var4 = var2.getValueFromObjective(this.field_179999_b, var3);
/*  53 */         func_179997_b(String.format("%d", new Object[] { Integer.valueOf(var4.getScorePoints()) }));
/*     */       }
/*     */       else {
/*     */         
/*  57 */         this.field_179998_d = "";
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     return this.field_179998_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatComponentScore func_179996_i() {
/*  66 */     ChatComponentScore var1 = new ChatComponentScore(this.field_179999_b, this.field_180000_c);
/*  67 */     var1.func_179997_b(this.field_179998_d);
/*  68 */     var1.setChatStyle(getChatStyle().createShallowCopy());
/*  69 */     Iterator<IChatComponent> var2 = getSiblings().iterator();
/*     */     
/*  71 */     while (var2.hasNext()) {
/*     */       
/*  73 */       IChatComponent var3 = var2.next();
/*  74 */       var1.appendSibling(var3.createCopy());
/*     */     } 
/*     */     
/*  77 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  82 */     if (this == p_equals_1_)
/*     */     {
/*  84 */       return true;
/*     */     }
/*  86 */     if (!(p_equals_1_ instanceof ChatComponentScore))
/*     */     {
/*  88 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  92 */     ChatComponentScore var2 = (ChatComponentScore)p_equals_1_;
/*  93 */     return (this.field_179999_b.equals(var2.field_179999_b) && this.field_180000_c.equals(var2.field_180000_c) && super.equals(p_equals_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     return "ScoreComponent{name='" + this.field_179999_b + '\'' + "objective='" + this.field_180000_c + '\'' + ", siblings=" + this.siblings + ", style=" + getChatStyle() + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent createCopy() {
/* 107 */     return func_179996_i();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */