/*     */ package net.minecraft.stats;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.event.HoverEvent;
/*     */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatBase
/*     */ {
/*     */   public final String statId;
/*     */   private final IChatComponent statName;
/*     */   public boolean isIndependent;
/*     */   private final IStatType type;
/*     */   private final IScoreObjectiveCriteria field_150957_c;
/*     */   private Class field_150956_d;
/*  23 */   private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
/*  24 */   public static IStatType simpleStatType = new IStatType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001473";
/*     */       
/*     */       public String format(int p_75843_1_) {
/*  29 */         return StatBase.numberFormat.format(p_75843_1_);
/*     */       }
/*     */     };
/*  32 */   private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
/*  33 */   public static IStatType timeStatType = new IStatType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001474";
/*     */       
/*     */       public String format(int p_75843_1_) {
/*  38 */         double var2 = p_75843_1_ / 20.0D;
/*  39 */         double var4 = var2 / 60.0D;
/*  40 */         double var6 = var4 / 60.0D;
/*  41 */         double var8 = var6 / 24.0D;
/*  42 */         double var10 = var8 / 365.0D;
/*  43 */         return (var10 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var10)) + " y") : ((var8 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var8)) + " d") : ((var6 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var6)) + " h") : ((var4 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var4)) + " m") : (String.valueOf(var2) + " s"))));
/*     */       }
/*     */     };
/*  46 */   public static IStatType distanceStatType = new IStatType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001475";
/*     */       
/*     */       public String format(int p_75843_1_) {
/*  51 */         double var2 = p_75843_1_ / 100.0D;
/*  52 */         double var4 = var2 / 1000.0D;
/*  53 */         return (var4 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var4)) + " km") : ((var2 > 0.5D) ? (String.valueOf(StatBase.decimalFormat.format(var2)) + " m") : (String.valueOf(p_75843_1_) + " cm"));
/*     */       }
/*     */     };
/*  56 */   public static IStatType field_111202_k = new IStatType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001476";
/*     */       
/*     */       public String format(int p_75843_1_) {
/*  61 */         return StatBase.decimalFormat.format(p_75843_1_ * 0.1D);
/*     */       }
/*     */     };
/*     */   
/*     */   private static final String __OBFID = "CL_00001472";
/*     */   
/*     */   public StatBase(String p_i45307_1_, IChatComponent p_i45307_2_, IStatType p_i45307_3_) {
/*  68 */     this.statId = p_i45307_1_;
/*  69 */     this.statName = p_i45307_2_;
/*  70 */     this.type = p_i45307_3_;
/*  71 */     this.field_150957_c = (IScoreObjectiveCriteria)new ObjectiveStat(this);
/*  72 */     IScoreObjectiveCriteria.INSTANCES.put(this.field_150957_c.getName(), this.field_150957_c);
/*     */   }
/*     */ 
/*     */   
/*     */   public StatBase(String p_i45308_1_, IChatComponent p_i45308_2_) {
/*  77 */     this(p_i45308_1_, p_i45308_2_, simpleStatType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase initIndependentStat() {
/*  86 */     this.isIndependent = true;
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase registerStat() {
/*  95 */     if (StatList.oneShotStats.containsKey(this.statId))
/*     */     {
/*  97 */       throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.oneShotStats.get(this.statId)).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
/*     */     }
/*     */ 
/*     */     
/* 101 */     StatList.allStats.add(this);
/* 102 */     StatList.oneShotStats.put(this.statId, this);
/* 103 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAchievement() {
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_75968_a(int p_75968_1_) {
/* 117 */     return this.type.format(p_75968_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getStatName() {
/* 122 */     IChatComponent var1 = this.statName.createCopy();
/* 123 */     var1.getChatStyle().setColor(EnumChatFormatting.GRAY);
/* 124 */     var1.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, (IChatComponent)new ChatComponentText(this.statId)));
/* 125 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_150955_j() {
/* 130 */     IChatComponent var1 = getStatName();
/* 131 */     IChatComponent var2 = (new ChatComponentText("[")).appendSibling(var1).appendText("]");
/* 132 */     var2.setChatStyle(var1.getChatStyle());
/* 133 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 138 */     if (this == p_equals_1_)
/*     */     {
/* 140 */       return true;
/*     */     }
/* 142 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/* 144 */       StatBase var2 = (StatBase)p_equals_1_;
/* 145 */       return this.statId.equals(var2.statId);
/*     */     } 
/*     */ 
/*     */     
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 155 */     return this.statId.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return "Stat{id=" + this.statId + ", nameId=" + this.statName + ", awardLocallyOnly=" + this.isIndependent + ", formatter=" + this.type + ", objectiveCriteria=" + this.field_150957_c + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public IScoreObjectiveCriteria func_150952_k() {
/* 165 */     return this.field_150957_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class func_150954_l() {
/* 170 */     return this.field_150956_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public StatBase func_150953_b(Class p_150953_1_) {
/* 175 */     this.field_150956_d = p_150953_1_;
/* 176 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */