/*     */ package net.minecraft.scoreboard;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.WorldSavedData;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ScoreboardSaveData extends WorldSavedData {
/*  15 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private Scoreboard theScoreboard;
/*     */   private NBTTagCompound field_96506_b;
/*     */   private static final String __OBFID = "CL_00000620";
/*     */   
/*     */   public ScoreboardSaveData() {
/*  22 */     this("scoreboard");
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardSaveData(String p_i2310_1_) {
/*  27 */     super(p_i2310_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96499_a(Scoreboard p_96499_1_) {
/*  32 */     this.theScoreboard = p_96499_1_;
/*     */     
/*  34 */     if (this.field_96506_b != null)
/*     */     {
/*  36 */       readFromNBT(this.field_96506_b);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/*  45 */     if (this.theScoreboard == null) {
/*     */       
/*  47 */       this.field_96506_b = nbt;
/*     */     }
/*     */     else {
/*     */       
/*  51 */       func_96501_b(nbt.getTagList("Objectives", 10));
/*  52 */       func_96500_c(nbt.getTagList("PlayerScores", 10));
/*     */       
/*  54 */       if (nbt.hasKey("DisplaySlots", 10))
/*     */       {
/*  56 */         func_96504_c(nbt.getCompoundTag("DisplaySlots"));
/*     */       }
/*     */       
/*  59 */       if (nbt.hasKey("Teams", 9))
/*     */       {
/*  61 */         func_96498_a(nbt.getTagList("Teams", 10));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96498_a(NBTTagList p_96498_1_) {
/*  68 */     for (int var2 = 0; var2 < p_96498_1_.tagCount(); var2++) {
/*     */       
/*  70 */       NBTTagCompound var3 = p_96498_1_.getCompoundTagAt(var2);
/*  71 */       ScorePlayerTeam var4 = this.theScoreboard.createTeam(var3.getString("Name"));
/*  72 */       var4.setTeamName(var3.getString("DisplayName"));
/*     */       
/*  74 */       if (var3.hasKey("TeamColor", 8))
/*     */       {
/*  76 */         var4.func_178774_a(EnumChatFormatting.getValueByName(var3.getString("TeamColor")));
/*     */       }
/*     */       
/*  79 */       var4.setNamePrefix(var3.getString("Prefix"));
/*  80 */       var4.setNameSuffix(var3.getString("Suffix"));
/*     */       
/*  82 */       if (var3.hasKey("AllowFriendlyFire", 99))
/*     */       {
/*  84 */         var4.setAllowFriendlyFire(var3.getBoolean("AllowFriendlyFire"));
/*     */       }
/*     */       
/*  87 */       if (var3.hasKey("SeeFriendlyInvisibles", 99))
/*     */       {
/*  89 */         var4.setSeeFriendlyInvisiblesEnabled(var3.getBoolean("SeeFriendlyInvisibles"));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (var3.hasKey("NameTagVisibility", 8)) {
/*     */         
/*  96 */         Team.EnumVisible var5 = Team.EnumVisible.func_178824_a(var3.getString("NameTagVisibility"));
/*     */         
/*  98 */         if (var5 != null)
/*     */         {
/* 100 */           var4.func_178772_a(var5);
/*     */         }
/*     */       } 
/*     */       
/* 104 */       if (var3.hasKey("DeathMessageVisibility", 8)) {
/*     */         
/* 106 */         Team.EnumVisible var5 = Team.EnumVisible.func_178824_a(var3.getString("DeathMessageVisibility"));
/*     */         
/* 108 */         if (var5 != null)
/*     */         {
/* 110 */           var4.func_178773_b(var5);
/*     */         }
/*     */       } 
/*     */       
/* 114 */       func_96502_a(var4, var3.getTagList("Players", 8));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96502_a(ScorePlayerTeam p_96502_1_, NBTTagList p_96502_2_) {
/* 120 */     for (int var3 = 0; var3 < p_96502_2_.tagCount(); var3++)
/*     */     {
/* 122 */       this.theScoreboard.func_151392_a(p_96502_2_.getStringTagAt(var3), p_96502_1_.getRegisteredName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96504_c(NBTTagCompound p_96504_1_) {
/* 128 */     for (int var2 = 0; var2 < 19; var2++) {
/*     */       
/* 130 */       if (p_96504_1_.hasKey("slot_" + var2, 8)) {
/*     */         
/* 132 */         String var3 = p_96504_1_.getString("slot_" + var2);
/* 133 */         ScoreObjective var4 = this.theScoreboard.getObjective(var3);
/* 134 */         this.theScoreboard.setObjectiveInDisplaySlot(var2, var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96501_b(NBTTagList p_96501_1_) {
/* 141 */     for (int var2 = 0; var2 < p_96501_1_.tagCount(); var2++) {
/*     */       
/* 143 */       NBTTagCompound var3 = p_96501_1_.getCompoundTagAt(var2);
/* 144 */       IScoreObjectiveCriteria var4 = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.INSTANCES.get(var3.getString("CriteriaName"));
/*     */       
/* 146 */       if (var4 != null) {
/*     */         
/* 148 */         ScoreObjective var5 = this.theScoreboard.addScoreObjective(var3.getString("Name"), var4);
/* 149 */         var5.setDisplayName(var3.getString("DisplayName"));
/* 150 */         var5.func_178767_a(IScoreObjectiveCriteria.EnumRenderType.func_178795_a(var3.getString("RenderType")));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96500_c(NBTTagList p_96500_1_) {
/* 157 */     for (int var2 = 0; var2 < p_96500_1_.tagCount(); var2++) {
/*     */       
/* 159 */       NBTTagCompound var3 = p_96500_1_.getCompoundTagAt(var2);
/* 160 */       ScoreObjective var4 = this.theScoreboard.getObjective(var3.getString("Objective"));
/* 161 */       Score var5 = this.theScoreboard.getValueFromObjective(var3.getString("Name"), var4);
/* 162 */       var5.setScorePoints(var3.getInteger("Score"));
/*     */       
/* 164 */       if (var3.hasKey("Locked"))
/*     */       {
/* 166 */         var5.func_178815_a(var3.getBoolean("Locked"));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 176 */     if (this.theScoreboard == null) {
/*     */       
/* 178 */       logger.warn("Tried to save scoreboard without having a scoreboard...");
/*     */     }
/*     */     else {
/*     */       
/* 182 */       nbt.setTag("Objectives", (NBTBase)func_96505_b());
/* 183 */       nbt.setTag("PlayerScores", (NBTBase)func_96503_e());
/* 184 */       nbt.setTag("Teams", (NBTBase)func_96496_a());
/* 185 */       func_96497_d(nbt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected NBTTagList func_96496_a() {
/* 191 */     NBTTagList var1 = new NBTTagList();
/* 192 */     Collection var2 = this.theScoreboard.getTeams();
/* 193 */     Iterator<ScorePlayerTeam> var3 = var2.iterator();
/*     */     
/* 195 */     while (var3.hasNext()) {
/*     */       
/* 197 */       ScorePlayerTeam var4 = var3.next();
/* 198 */       NBTTagCompound var5 = new NBTTagCompound();
/* 199 */       var5.setString("Name", var4.getRegisteredName());
/* 200 */       var5.setString("DisplayName", var4.func_96669_c());
/*     */       
/* 202 */       if (var4.func_178775_l().func_175746_b() >= 0)
/*     */       {
/* 204 */         var5.setString("TeamColor", var4.func_178775_l().getFriendlyName());
/*     */       }
/*     */       
/* 207 */       var5.setString("Prefix", var4.getColorPrefix());
/* 208 */       var5.setString("Suffix", var4.getColorSuffix());
/* 209 */       var5.setBoolean("AllowFriendlyFire", var4.getAllowFriendlyFire());
/* 210 */       var5.setBoolean("SeeFriendlyInvisibles", var4.func_98297_h());
/* 211 */       var5.setString("NameTagVisibility", (var4.func_178770_i()).field_178830_e);
/* 212 */       var5.setString("DeathMessageVisibility", (var4.func_178771_j()).field_178830_e);
/* 213 */       NBTTagList var6 = new NBTTagList();
/* 214 */       Iterator<String> var7 = var4.getMembershipCollection().iterator();
/*     */       
/* 216 */       while (var7.hasNext()) {
/*     */         
/* 218 */         String var8 = var7.next();
/* 219 */         var6.appendTag((NBTBase)new NBTTagString(var8));
/*     */       } 
/*     */       
/* 222 */       var5.setTag("Players", (NBTBase)var6);
/* 223 */       var1.appendTag((NBTBase)var5);
/*     */     } 
/*     */     
/* 226 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96497_d(NBTTagCompound p_96497_1_) {
/* 231 */     NBTTagCompound var2 = new NBTTagCompound();
/* 232 */     boolean var3 = false;
/*     */     
/* 234 */     for (int var4 = 0; var4 < 19; var4++) {
/*     */       
/* 236 */       ScoreObjective var5 = this.theScoreboard.getObjectiveInDisplaySlot(var4);
/*     */       
/* 238 */       if (var5 != null) {
/*     */         
/* 240 */         var2.setString("slot_" + var4, var5.getName());
/* 241 */         var3 = true;
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     if (var3)
/*     */     {
/* 247 */       p_96497_1_.setTag("DisplaySlots", (NBTBase)var2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected NBTTagList func_96505_b() {
/* 253 */     NBTTagList var1 = new NBTTagList();
/* 254 */     Collection var2 = this.theScoreboard.getScoreObjectives();
/* 255 */     Iterator<ScoreObjective> var3 = var2.iterator();
/*     */     
/* 257 */     while (var3.hasNext()) {
/*     */       
/* 259 */       ScoreObjective var4 = var3.next();
/*     */       
/* 261 */       if (var4.getCriteria() != null) {
/*     */         
/* 263 */         NBTTagCompound var5 = new NBTTagCompound();
/* 264 */         var5.setString("Name", var4.getName());
/* 265 */         var5.setString("CriteriaName", var4.getCriteria().getName());
/* 266 */         var5.setString("DisplayName", var4.getDisplayName());
/* 267 */         var5.setString("RenderType", var4.func_178766_e().func_178796_a());
/* 268 */         var1.appendTag((NBTBase)var5);
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NBTTagList func_96503_e() {
/* 277 */     NBTTagList var1 = new NBTTagList();
/* 278 */     Collection var2 = this.theScoreboard.func_96528_e();
/* 279 */     Iterator<Score> var3 = var2.iterator();
/*     */     
/* 281 */     while (var3.hasNext()) {
/*     */       
/* 283 */       Score var4 = var3.next();
/*     */       
/* 285 */       if (var4.getObjective() != null) {
/*     */         
/* 287 */         NBTTagCompound var5 = new NBTTagCompound();
/* 288 */         var5.setString("Name", var4.getPlayerName());
/* 289 */         var5.setString("Objective", var4.getObjective().getName());
/* 290 */         var5.setInteger("Score", var4.getScorePoints());
/* 291 */         var5.setBoolean("Locked", var4.func_178816_g());
/* 292 */         var1.appendTag((NBTBase)var5);
/*     */       } 
/*     */     } 
/*     */     
/* 296 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ScoreboardSaveData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */