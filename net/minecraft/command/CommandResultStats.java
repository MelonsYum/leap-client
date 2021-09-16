/*     */ package net.minecraft.command;
/*     */ 
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ 
/*     */ public class CommandResultStats {
/*  10 */   private static final int field_179676_a = (Type.values()).length;
/*  11 */   private static final String[] field_179674_b = new String[field_179676_a];
/*     */   
/*     */   private String[] field_179675_c;
/*     */   private String[] field_179673_d;
/*     */   private static final String __OBFID = "CL_00002364";
/*     */   
/*     */   public CommandResultStats() {
/*  18 */     this.field_179675_c = field_179674_b;
/*  19 */     this.field_179673_d = field_179674_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179672_a(ICommandSender p_179672_1_, Type p_179672_2_, int p_179672_3_) {
/*  24 */     String var4 = this.field_179675_c[p_179672_2_.func_179636_a()];
/*     */     
/*  26 */     if (var4 != null) {
/*     */       String var5;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  32 */         var5 = CommandBase.func_175758_e(p_179672_1_, var4);
/*     */       }
/*  34 */       catch (EntityNotFoundException var10) {
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/*  39 */       String var6 = this.field_179673_d[p_179672_2_.func_179636_a()];
/*     */       
/*  41 */       if (var6 != null) {
/*     */         
/*  43 */         Scoreboard var7 = p_179672_1_.getEntityWorld().getScoreboard();
/*  44 */         ScoreObjective var8 = var7.getObjective(var6);
/*     */         
/*  46 */         if (var8 != null)
/*     */         {
/*  48 */           if (var7.func_178819_b(var5, var8)) {
/*     */             
/*  50 */             Score var9 = var7.getValueFromObjective(var5, var8);
/*  51 */             var9.setScorePoints(p_179672_3_);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179668_a(NBTTagCompound p_179668_1_) {
/*  60 */     if (p_179668_1_.hasKey("CommandStats", 10)) {
/*     */       
/*  62 */       NBTTagCompound var2 = p_179668_1_.getCompoundTag("CommandStats");
/*  63 */       Type[] var3 = Type.values();
/*  64 */       int var4 = var3.length;
/*     */       
/*  66 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/*  68 */         Type var6 = var3[var5];
/*  69 */         String var7 = String.valueOf(var6.func_179637_b()) + "Name";
/*  70 */         String var8 = String.valueOf(var6.func_179637_b()) + "Objective";
/*     */         
/*  72 */         if (var2.hasKey(var7, 8) && var2.hasKey(var8, 8)) {
/*     */           
/*  74 */           String var9 = var2.getString(var7);
/*  75 */           String var10 = var2.getString(var8);
/*  76 */           func_179667_a(this, var6, var9, var10);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179670_b(NBTTagCompound p_179670_1_) {
/*  84 */     NBTTagCompound var2 = new NBTTagCompound();
/*  85 */     Type[] var3 = Type.values();
/*  86 */     int var4 = var3.length;
/*     */     
/*  88 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  90 */       Type var6 = var3[var5];
/*  91 */       String var7 = this.field_179675_c[var6.func_179636_a()];
/*  92 */       String var8 = this.field_179673_d[var6.func_179636_a()];
/*     */       
/*  94 */       if (var7 != null && var8 != null) {
/*     */         
/*  96 */         var2.setString(String.valueOf(var6.func_179637_b()) + "Name", var7);
/*  97 */         var2.setString(String.valueOf(var6.func_179637_b()) + "Objective", var8);
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     if (!var2.hasNoTags())
/*     */     {
/* 103 */       p_179670_1_.setTag("CommandStats", (NBTBase)var2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_179667_a(CommandResultStats p_179667_0_, Type p_179667_1_, String p_179667_2_, String p_179667_3_) {
/* 109 */     if (p_179667_2_ != null && p_179667_2_.length() != 0 && p_179667_3_ != null && p_179667_3_.length() != 0) {
/*     */       
/* 111 */       if (p_179667_0_.field_179675_c == field_179674_b || p_179667_0_.field_179673_d == field_179674_b) {
/*     */         
/* 113 */         p_179667_0_.field_179675_c = new String[field_179676_a];
/* 114 */         p_179667_0_.field_179673_d = new String[field_179676_a];
/*     */       } 
/*     */       
/* 117 */       p_179667_0_.field_179675_c[p_179667_1_.func_179636_a()] = p_179667_2_;
/* 118 */       p_179667_0_.field_179673_d[p_179667_1_.func_179636_a()] = p_179667_3_;
/*     */     }
/*     */     else {
/*     */       
/* 122 */       func_179669_a(p_179667_0_, p_179667_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_179669_a(CommandResultStats p_179669_0_, Type p_179669_1_) {
/* 128 */     if (p_179669_0_.field_179675_c != field_179674_b && p_179669_0_.field_179673_d != field_179674_b) {
/*     */       
/* 130 */       p_179669_0_.field_179675_c[p_179669_1_.func_179636_a()] = null;
/* 131 */       p_179669_0_.field_179673_d[p_179669_1_.func_179636_a()] = null;
/* 132 */       boolean var2 = true;
/* 133 */       Type[] var3 = Type.values();
/* 134 */       int var4 = var3.length;
/*     */       
/* 136 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/* 138 */         Type var6 = var3[var5];
/*     */         
/* 140 */         if (p_179669_0_.field_179675_c[var6.func_179636_a()] != null && p_179669_0_.field_179673_d[var6.func_179636_a()] != null) {
/*     */           
/* 142 */           var2 = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 147 */       if (var2) {
/*     */         
/* 149 */         p_179669_0_.field_179675_c = field_179674_b;
/* 150 */         p_179669_0_.field_179673_d = field_179674_b;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179671_a(CommandResultStats p_179671_1_) {
/* 157 */     Type[] var2 = Type.values();
/* 158 */     int var3 = var2.length;
/*     */     
/* 160 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 162 */       Type var5 = var2[var4];
/* 163 */       func_179667_a(this, var5, p_179671_1_.field_179675_c[var5.func_179636_a()], p_179671_1_.field_179673_d[var5.func_179636_a()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Type
/*     */   {
/* 169 */     SUCCESS_COUNT("SUCCESS_COUNT", 0, 0, "SuccessCount"),
/* 170 */     AFFECTED_BLOCKS("AFFECTED_BLOCKS", 1, 1, "AffectedBlocks"),
/* 171 */     AFFECTED_ENTITIES("AFFECTED_ENTITIES", 2, 2, "AffectedEntities"),
/* 172 */     AFFECTED_ITEMS("AFFECTED_ITEMS", 3, 3, "AffectedItems"),
/* 173 */     QUERY_RESULT("QUERY_RESULT", 4, 4, "QueryResult");
/*     */     
/*     */     final int field_179639_f;
/*     */     final String field_179640_g;
/* 177 */     private static final Type[] $VALUES = new Type[] { SUCCESS_COUNT, AFFECTED_BLOCKS, AFFECTED_ENTITIES, AFFECTED_ITEMS, QUERY_RESULT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002363";
/*     */     
/*     */     Type(String p_i46050_1_, int p_i46050_2_, int p_i46050_3_, String p_i46050_4_) {
/* 182 */       this.field_179639_f = p_i46050_3_;
/* 183 */       this.field_179640_g = p_i46050_4_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public int func_179636_a() {
/* 188 */       return this.field_179639_f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_179637_b() {
/* 193 */       return this.field_179640_g;
/*     */     }
/*     */ 
/*     */     
/*     */     public static String[] func_179634_c() {
/* 198 */       String[] var0 = new String[(values()).length];
/* 199 */       int var1 = 0;
/* 200 */       Type[] var2 = values();
/* 201 */       int var3 = var2.length;
/*     */       
/* 203 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 205 */         Type var5 = var2[var4];
/* 206 */         var0[var1++] = var5.func_179637_b();
/*     */       } 
/*     */       
/* 209 */       return var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Type func_179635_a(String p_179635_0_) {
/* 214 */       Type[] var1 = values();
/* 215 */       int var2 = var1.length;
/*     */       
/* 217 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 219 */         Type var4 = var1[var3];
/*     */         
/* 221 */         if (var4.func_179637_b().equals(p_179635_0_))
/*     */         {
/* 223 */           return var4;
/*     */         }
/*     */       } 
/*     */       
/* 227 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandResultStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */