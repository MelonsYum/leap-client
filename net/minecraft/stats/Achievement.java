/*     */ package net.minecraft.stats;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.StatCollector;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Achievement
/*     */   extends StatBase
/*     */ {
/*     */   public final int displayColumn;
/*     */   public final int displayRow;
/*     */   public final Achievement parentAchievement;
/*     */   private final String achievementDescription;
/*     */   private IStatStringFormat statStringFormatter;
/*     */   public final ItemStack theItemStack;
/*     */   private boolean isSpecial;
/*     */   private static final String __OBFID = "CL_00001466";
/*     */   
/*     */   public Achievement(String p_i46327_1_, String p_i46327_2_, int p_i46327_3_, int p_i46327_4_, Item p_i46327_5_, Achievement p_i46327_6_) {
/*  53 */     this(p_i46327_1_, p_i46327_2_, p_i46327_3_, p_i46327_4_, new ItemStack(p_i46327_5_), p_i46327_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement(String p_i45301_1_, String p_i45301_2_, int p_i45301_3_, int p_i45301_4_, Block p_i45301_5_, Achievement p_i45301_6_) {
/*  58 */     this(p_i45301_1_, p_i45301_2_, p_i45301_3_, p_i45301_4_, new ItemStack(p_i45301_5_), p_i45301_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement(String p_i45302_1_, String p_i45302_2_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, Achievement p_i45302_6_) {
/*  63 */     super(p_i45302_1_, (IChatComponent)new ChatComponentTranslation("achievement." + p_i45302_2_, new Object[0]));
/*  64 */     this.theItemStack = p_i45302_5_;
/*  65 */     this.achievementDescription = "achievement." + p_i45302_2_ + ".desc";
/*  66 */     this.displayColumn = p_i45302_3_;
/*  67 */     this.displayRow = p_i45302_4_;
/*     */     
/*  69 */     if (p_i45302_3_ < AchievementList.minDisplayColumn)
/*     */     {
/*  71 */       AchievementList.minDisplayColumn = p_i45302_3_;
/*     */     }
/*     */     
/*  74 */     if (p_i45302_4_ < AchievementList.minDisplayRow)
/*     */     {
/*  76 */       AchievementList.minDisplayRow = p_i45302_4_;
/*     */     }
/*     */     
/*  79 */     if (p_i45302_3_ > AchievementList.maxDisplayColumn)
/*     */     {
/*  81 */       AchievementList.maxDisplayColumn = p_i45302_3_;
/*     */     }
/*     */     
/*  84 */     if (p_i45302_4_ > AchievementList.maxDisplayRow)
/*     */     {
/*  86 */       AchievementList.maxDisplayRow = p_i45302_4_;
/*     */     }
/*     */     
/*  89 */     this.parentAchievement = p_i45302_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement func_180789_a() {
/*  94 */     this.isIndependent = true;
/*  95 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setSpecial() {
/* 104 */     this.isSpecial = true;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement func_180788_c() {
/* 110 */     super.registerStat();
/* 111 */     AchievementList.achievementList.add(this);
/* 112 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAchievement() {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getStatName() {
/* 125 */     IChatComponent var1 = super.getStatName();
/* 126 */     var1.getChatStyle().setColor(getSpecial() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
/* 127 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement func_180787_a(Class p_180787_1_) {
/* 132 */     return (Achievement)super.func_150953_b(p_180787_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 140 */     return (this.statStringFormatter != null) ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription)) : StatCollector.translateToLocal(this.achievementDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setStatStringFormatter(IStatStringFormat p_75988_1_) {
/* 148 */     this.statStringFormatter = p_75988_1_;
/* 149 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecial() {
/* 158 */     return this.isSpecial;
/*     */   }
/*     */ 
/*     */   
/*     */   public StatBase func_150953_b(Class p_150953_1_) {
/* 163 */     return func_180787_a(p_150953_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase registerStat() {
/* 171 */     return func_180788_c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase initIndependentStat() {
/* 180 */     return func_180789_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\Achievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */