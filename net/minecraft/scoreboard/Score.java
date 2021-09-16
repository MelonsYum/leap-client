/*     */ package net.minecraft.scoreboard;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class Score
/*     */ {
/*   9 */   public static final Comparator scoreComparator = new Comparator()
/*     */     {
/*     */       private static final String __OBFID = "CL_00000618";
/*     */       
/*     */       public int compare(Score p_compare_1_, Score p_compare_2_) {
/*  14 */         return (p_compare_1_.getScorePoints() > p_compare_2_.getScorePoints()) ? 1 : ((p_compare_1_.getScorePoints() < p_compare_2_.getScorePoints()) ? -1 : p_compare_2_.getPlayerName().compareToIgnoreCase(p_compare_1_.getPlayerName()));
/*     */       }
/*     */       
/*     */       public int compare(Object p_compare_1_, Object p_compare_2_) {
/*  18 */         return compare((Score)p_compare_1_, (Score)p_compare_2_);
/*     */       }
/*     */     };
/*     */   
/*     */   private final Scoreboard theScoreboard;
/*     */   private final ScoreObjective theScoreObjective;
/*     */   private final String scorePlayerName;
/*     */   private int scorePoints;
/*     */   private boolean field_178817_f;
/*     */   private boolean field_178818_g;
/*     */   private static final String __OBFID = "CL_00000617";
/*     */   
/*     */   public Score(Scoreboard p_i2309_1_, ScoreObjective p_i2309_2_, String p_i2309_3_) {
/*  31 */     this.theScoreboard = p_i2309_1_;
/*  32 */     this.theScoreObjective = p_i2309_2_;
/*  33 */     this.scorePlayerName = p_i2309_3_;
/*  34 */     this.field_178818_g = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void increseScore(int p_96649_1_) {
/*  39 */     if (this.theScoreObjective.getCriteria().isReadOnly())
/*     */     {
/*  41 */       throw new IllegalStateException("Cannot modify read-only score");
/*     */     }
/*     */ 
/*     */     
/*  45 */     setScorePoints(getScorePoints() + p_96649_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decreaseScore(int p_96646_1_) {
/*  51 */     if (this.theScoreObjective.getCriteria().isReadOnly())
/*     */     {
/*  53 */       throw new IllegalStateException("Cannot modify read-only score");
/*     */     }
/*     */ 
/*     */     
/*  57 */     setScorePoints(getScorePoints() - p_96646_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_96648_a() {
/*  63 */     if (this.theScoreObjective.getCriteria().isReadOnly())
/*     */     {
/*  65 */       throw new IllegalStateException("Cannot modify read-only score");
/*     */     }
/*     */ 
/*     */     
/*  69 */     increseScore(1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScorePoints() {
/*  75 */     return this.scorePoints;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScorePoints(int p_96647_1_) {
/*  80 */     int var2 = this.scorePoints;
/*  81 */     this.scorePoints = p_96647_1_;
/*     */     
/*  83 */     if (var2 != p_96647_1_ || this.field_178818_g) {
/*     */       
/*  85 */       this.field_178818_g = false;
/*  86 */       getScoreScoreboard().func_96536_a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreObjective getObjective() {
/*  92 */     return this.theScoreObjective;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPlayerName() {
/* 100 */     return this.scorePlayerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Scoreboard getScoreScoreboard() {
/* 105 */     return this.theScoreboard;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178816_g() {
/* 110 */     return this.field_178817_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178815_a(boolean p_178815_1_) {
/* 115 */     this.field_178817_f = p_178815_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96651_a(List p_96651_1_) {
/* 120 */     setScorePoints(this.theScoreObjective.getCriteria().func_96635_a(p_96651_1_));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\Score.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */