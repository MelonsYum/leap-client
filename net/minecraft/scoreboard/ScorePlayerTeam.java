/*     */ package net.minecraft.scoreboard;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ 
/*     */ 
/*     */ public class ScorePlayerTeam
/*     */   extends Team
/*     */ {
/*     */   private final Scoreboard theScoreboard;
/*     */   private final String field_96675_b;
/*  14 */   private final Set membershipSet = Sets.newHashSet();
/*     */   private String teamNameSPT;
/*  16 */   private String namePrefixSPT = "";
/*  17 */   private String colorSuffix = "";
/*     */   
/*     */   private boolean allowFriendlyFire = true;
/*     */   private boolean canSeeFriendlyInvisibles = true;
/*     */   private Team.EnumVisible field_178778_i;
/*     */   private Team.EnumVisible field_178776_j;
/*     */   private EnumChatFormatting field_178777_k;
/*     */   private static final String __OBFID = "CL_00000616";
/*     */   
/*     */   public ScorePlayerTeam(Scoreboard p_i2308_1_, String p_i2308_2_) {
/*  27 */     this.field_178778_i = Team.EnumVisible.ALWAYS;
/*  28 */     this.field_178776_j = Team.EnumVisible.ALWAYS;
/*  29 */     this.field_178777_k = EnumChatFormatting.RESET;
/*  30 */     this.theScoreboard = p_i2308_1_;
/*  31 */     this.field_96675_b = p_i2308_2_;
/*  32 */     this.teamNameSPT = p_i2308_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRegisteredName() {
/*  40 */     return this.field_96675_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_96669_c() {
/*  45 */     return this.teamNameSPT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTeamName(String p_96664_1_) {
/*  50 */     if (p_96664_1_ == null)
/*     */     {
/*  52 */       throw new IllegalArgumentException("Name cannot be null");
/*     */     }
/*     */ 
/*     */     
/*  56 */     this.teamNameSPT = p_96664_1_;
/*  57 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getMembershipCollection() {
/*  63 */     return this.membershipSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColorPrefix() {
/*  71 */     return this.namePrefixSPT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNamePrefix(String p_96666_1_) {
/*  76 */     if (p_96666_1_ == null)
/*     */     {
/*  78 */       throw new IllegalArgumentException("Prefix cannot be null");
/*     */     }
/*     */ 
/*     */     
/*  82 */     this.namePrefixSPT = p_96666_1_;
/*  83 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColorSuffix() {
/*  92 */     return this.colorSuffix;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameSuffix(String p_96662_1_) {
/*  97 */     this.colorSuffix = p_96662_1_;
/*  98 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String formatString(String input) {
/* 103 */     return String.valueOf(getColorPrefix()) + input + getColorSuffix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatPlayerName(Team p_96667_0_, String p_96667_1_) {
/* 111 */     return (p_96667_0_ == null) ? p_96667_1_ : p_96667_0_.formatString(p_96667_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAllowFriendlyFire() {
/* 116 */     return this.allowFriendlyFire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowFriendlyFire(boolean p_96660_1_) {
/* 121 */     this.allowFriendlyFire = p_96660_1_;
/* 122 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_98297_h() {
/* 127 */     return this.canSeeFriendlyInvisibles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeeFriendlyInvisiblesEnabled(boolean p_98300_1_) {
/* 132 */     this.canSeeFriendlyInvisibles = p_98300_1_;
/* 133 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Team.EnumVisible func_178770_i() {
/* 138 */     return this.field_178778_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public Team.EnumVisible func_178771_j() {
/* 143 */     return this.field_178776_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178772_a(Team.EnumVisible p_178772_1_) {
/* 148 */     this.field_178778_i = p_178772_1_;
/* 149 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178773_b(Team.EnumVisible p_178773_1_) {
/* 154 */     this.field_178776_j = p_178773_1_;
/* 155 */     this.theScoreboard.broadcastTeamRemoved(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_98299_i() {
/* 160 */     int var1 = 0;
/*     */     
/* 162 */     if (getAllowFriendlyFire())
/*     */     {
/* 164 */       var1 |= 0x1;
/*     */     }
/*     */     
/* 167 */     if (func_98297_h())
/*     */     {
/* 169 */       var1 |= 0x2;
/*     */     }
/*     */     
/* 172 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_98298_a(int p_98298_1_) {
/* 177 */     setAllowFriendlyFire(((p_98298_1_ & 0x1) > 0));
/* 178 */     setSeeFriendlyInvisiblesEnabled(((p_98298_1_ & 0x2) > 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178774_a(EnumChatFormatting p_178774_1_) {
/* 183 */     this.field_178777_k = p_178774_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumChatFormatting func_178775_l() {
/* 188 */     return this.field_178777_k;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ScorePlayerTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */