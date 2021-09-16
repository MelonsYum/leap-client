/*     */ package net.minecraft.scoreboard;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ 
/*     */ 
/*     */ public class Scoreboard
/*     */ {
/*  16 */   private final Map scoreObjectives = Maps.newHashMap();
/*  17 */   private final Map scoreObjectiveCriterias = Maps.newHashMap();
/*  18 */   private final Map field_96544_c = Maps.newHashMap();
/*     */ 
/*     */   
/*  21 */   private final ScoreObjective[] objectiveDisplaySlots = new ScoreObjective[19];
/*     */ 
/*     */   
/*  24 */   private final Map teams = Maps.newHashMap();
/*     */ 
/*     */   
/*  27 */   private final Map teamMemberships = Maps.newHashMap();
/*  28 */   private static String[] field_178823_g = null;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000619";
/*     */ 
/*     */ 
/*     */   
/*     */   public ScoreObjective getObjective(String p_96518_1_) {
/*  36 */     return (ScoreObjective)this.scoreObjectives.get(p_96518_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreObjective addScoreObjective(String p_96535_1_, IScoreObjectiveCriteria p_96535_2_) {
/*  41 */     ScoreObjective var3 = getObjective(p_96535_1_);
/*     */     
/*  43 */     if (var3 != null)
/*     */     {
/*  45 */       throw new IllegalArgumentException("An objective with the name '" + p_96535_1_ + "' already exists!");
/*     */     }
/*     */ 
/*     */     
/*  49 */     var3 = new ScoreObjective(this, p_96535_1_, p_96535_2_);
/*  50 */     Object var4 = this.scoreObjectiveCriterias.get(p_96535_2_);
/*     */     
/*  52 */     if (var4 == null) {
/*     */       
/*  54 */       var4 = Lists.newArrayList();
/*  55 */       this.scoreObjectiveCriterias.put(p_96535_2_, var4);
/*     */     } 
/*     */     
/*  58 */     ((List<ScoreObjective>)var4).add(var3);
/*  59 */     this.scoreObjectives.put(p_96535_1_, var3);
/*  60 */     func_96522_a(var3);
/*  61 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection func_96520_a(IScoreObjectiveCriteria p_96520_1_) {
/*  67 */     Collection var2 = (Collection)this.scoreObjectiveCriterias.get(p_96520_1_);
/*  68 */     return (var2 == null) ? Lists.newArrayList() : Lists.newArrayList(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178819_b(String p_178819_1_, ScoreObjective p_178819_2_) {
/*  73 */     Map var3 = (Map)this.field_96544_c.get(p_178819_1_);
/*     */     
/*  75 */     if (var3 == null)
/*     */     {
/*  77 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  81 */     Score var4 = (Score)var3.get(p_178819_2_);
/*  82 */     return (var4 != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Score getValueFromObjective(String p_96529_1_, ScoreObjective p_96529_2_) {
/*  88 */     Object var3 = this.field_96544_c.get(p_96529_1_);
/*     */     
/*  90 */     if (var3 == null) {
/*     */       
/*  92 */       var3 = Maps.newHashMap();
/*  93 */       this.field_96544_c.put(p_96529_1_, var3);
/*     */     } 
/*     */     
/*  96 */     Score var4 = (Score)((Map)var3).get(p_96529_2_);
/*     */     
/*  98 */     if (var4 == null) {
/*     */       
/* 100 */       var4 = new Score(this, p_96529_2_, p_96529_1_);
/* 101 */       ((Map<ScoreObjective, Score>)var3).put(p_96529_2_, var4);
/*     */     } 
/*     */     
/* 104 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getSortedScores(ScoreObjective p_96534_1_) {
/* 112 */     ArrayList<Score> var2 = Lists.newArrayList();
/* 113 */     Iterator<Map> var3 = this.field_96544_c.values().iterator();
/*     */     
/* 115 */     while (var3.hasNext()) {
/*     */       
/* 117 */       Map var4 = var3.next();
/* 118 */       Score var5 = (Score)var4.get(p_96534_1_);
/*     */       
/* 120 */       if (var5 != null)
/*     */       {
/* 122 */         var2.add(var5);
/*     */       }
/*     */     } 
/*     */     
/* 126 */     Collections.sort(var2, Score.scoreComparator);
/* 127 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection getScoreObjectives() {
/* 132 */     return this.scoreObjectives.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection getObjectiveNames() {
/* 137 */     return this.field_96544_c.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_178822_d(String p_178822_1_, ScoreObjective p_178822_2_) {
/* 144 */     if (p_178822_2_ == null) {
/*     */       
/* 146 */       Map var3 = (Map)this.field_96544_c.remove(p_178822_1_);
/*     */       
/* 148 */       if (var3 != null)
/*     */       {
/* 150 */         func_96516_a(p_178822_1_);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 155 */       Map var3 = (Map)this.field_96544_c.get(p_178822_1_);
/*     */       
/* 157 */       if (var3 != null) {
/*     */         
/* 159 */         Score var4 = (Score)var3.remove(p_178822_2_);
/*     */         
/* 161 */         if (var3.size() < 1) {
/*     */           
/* 163 */           Map var5 = (Map)this.field_96544_c.remove(p_178822_1_);
/*     */           
/* 165 */           if (var5 != null)
/*     */           {
/* 167 */             func_96516_a(p_178822_1_);
/*     */           }
/*     */         }
/* 170 */         else if (var4 != null) {
/*     */           
/* 172 */           func_178820_a(p_178822_1_, p_178822_2_);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection func_96528_e() {
/* 180 */     Collection var1 = this.field_96544_c.values();
/* 181 */     ArrayList var2 = Lists.newArrayList();
/* 182 */     Iterator<Map> var3 = var1.iterator();
/*     */     
/* 184 */     while (var3.hasNext()) {
/*     */       
/* 186 */       Map var4 = var3.next();
/* 187 */       var2.addAll(var4.values());
/*     */     } 
/*     */     
/* 190 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_96510_d(String p_96510_1_) {
/* 195 */     Object var2 = this.field_96544_c.get(p_96510_1_);
/*     */     
/* 197 */     if (var2 == null)
/*     */     {
/* 199 */       var2 = Maps.newHashMap();
/*     */     }
/*     */     
/* 202 */     return (Map)var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96519_k(ScoreObjective p_96519_1_) {
/* 207 */     this.scoreObjectives.remove(p_96519_1_.getName());
/*     */     
/* 209 */     for (int var2 = 0; var2 < 19; var2++) {
/*     */       
/* 211 */       if (getObjectiveInDisplaySlot(var2) == p_96519_1_)
/*     */       {
/* 213 */         setObjectiveInDisplaySlot(var2, null);
/*     */       }
/*     */     } 
/*     */     
/* 217 */     List var5 = (List)this.scoreObjectiveCriterias.get(p_96519_1_.getCriteria());
/*     */     
/* 219 */     if (var5 != null)
/*     */     {
/* 221 */       var5.remove(p_96519_1_);
/*     */     }
/*     */     
/* 224 */     Iterator<Map> var3 = this.field_96544_c.values().iterator();
/*     */     
/* 226 */     while (var3.hasNext()) {
/*     */       
/* 228 */       Map var4 = var3.next();
/* 229 */       var4.remove(p_96519_1_);
/*     */     } 
/*     */     
/* 232 */     func_96533_c(p_96519_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectiveInDisplaySlot(int p_96530_1_, ScoreObjective p_96530_2_) {
/* 240 */     this.objectiveDisplaySlots[p_96530_1_] = p_96530_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScoreObjective getObjectiveInDisplaySlot(int p_96539_1_) {
/* 248 */     return this.objectiveDisplaySlots[p_96539_1_];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScorePlayerTeam getTeam(String p_96508_1_) {
/* 256 */     return (ScorePlayerTeam)this.teams.get(p_96508_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ScorePlayerTeam createTeam(String p_96527_1_) {
/* 261 */     ScorePlayerTeam var2 = getTeam(p_96527_1_);
/*     */     
/* 263 */     if (var2 != null)
/*     */     {
/* 265 */       throw new IllegalArgumentException("A team with the name '" + p_96527_1_ + "' already exists!");
/*     */     }
/*     */ 
/*     */     
/* 269 */     var2 = new ScorePlayerTeam(this, p_96527_1_);
/* 270 */     this.teams.put(p_96527_1_, var2);
/* 271 */     broadcastTeamCreated(var2);
/* 272 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTeam(ScorePlayerTeam p_96511_1_) {
/* 281 */     this.teams.remove(p_96511_1_.getRegisteredName());
/* 282 */     Iterator<String> var2 = p_96511_1_.getMembershipCollection().iterator();
/*     */     
/* 284 */     while (var2.hasNext()) {
/*     */       
/* 286 */       String var3 = var2.next();
/* 287 */       this.teamMemberships.remove(var3);
/*     */     } 
/*     */     
/* 290 */     func_96513_c(p_96511_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_151392_a(String p_151392_1_, String p_151392_2_) {
/* 295 */     if (!this.teams.containsKey(p_151392_2_))
/*     */     {
/* 297 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 301 */     ScorePlayerTeam var3 = getTeam(p_151392_2_);
/*     */     
/* 303 */     if (getPlayersTeam(p_151392_1_) != null)
/*     */     {
/* 305 */       removePlayerFromTeams(p_151392_1_);
/*     */     }
/*     */     
/* 308 */     this.teamMemberships.put(p_151392_1_, var3);
/* 309 */     var3.getMembershipCollection().add(p_151392_1_);
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removePlayerFromTeams(String p_96524_1_) {
/* 316 */     ScorePlayerTeam var2 = getPlayersTeam(p_96524_1_);
/*     */     
/* 318 */     if (var2 != null) {
/*     */       
/* 320 */       removePlayerFromTeam(p_96524_1_, var2);
/* 321 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
/* 335 */     if (getPlayersTeam(p_96512_1_) != p_96512_2_)
/*     */     {
/* 337 */       throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + p_96512_2_.getRegisteredName() + "'.");
/*     */     }
/*     */ 
/*     */     
/* 341 */     this.teamMemberships.remove(p_96512_1_);
/* 342 */     p_96512_2_.getMembershipCollection().remove(p_96512_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getTeamNames() {
/* 351 */     return this.teams.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getTeams() {
/* 359 */     return this.teams.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScorePlayerTeam getPlayersTeam(String p_96509_1_) {
/* 367 */     return (ScorePlayerTeam)this.teamMemberships.get(p_96509_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96522_a(ScoreObjective p_96522_1_) {}
/*     */ 
/*     */   
/*     */   public void func_96532_b(ScoreObjective p_96532_1_) {}
/*     */ 
/*     */   
/*     */   public void func_96533_c(ScoreObjective p_96533_1_) {}
/*     */ 
/*     */   
/*     */   public void func_96536_a(Score p_96536_1_) {}
/*     */ 
/*     */   
/*     */   public void func_96516_a(String p_96516_1_) {}
/*     */ 
/*     */   
/*     */   public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {}
/*     */ 
/*     */   
/*     */   public void broadcastTeamCreated(ScorePlayerTeam p_96523_1_) {}
/*     */ 
/*     */   
/*     */   public void broadcastTeamRemoved(ScorePlayerTeam p_96538_1_) {}
/*     */ 
/*     */   
/*     */   public void func_96513_c(ScorePlayerTeam p_96513_1_) {}
/*     */ 
/*     */   
/*     */   public static String getObjectiveDisplaySlot(int p_96517_0_) {
/* 399 */     switch (p_96517_0_) {
/*     */       
/*     */       case 0:
/* 402 */         return "list";
/*     */       
/*     */       case 1:
/* 405 */         return "sidebar";
/*     */       
/*     */       case 2:
/* 408 */         return "belowName";
/*     */     } 
/*     */     
/* 411 */     if (p_96517_0_ >= 3 && p_96517_0_ <= 18) {
/*     */       
/* 413 */       EnumChatFormatting var1 = EnumChatFormatting.func_175744_a(p_96517_0_ - 3);
/*     */       
/* 415 */       if (var1 != null && var1 != EnumChatFormatting.RESET)
/*     */       {
/* 417 */         return "sidebar.team." + var1.getFriendlyName();
/*     */       }
/*     */     } 
/*     */     
/* 421 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getObjectiveDisplaySlotNumber(String p_96537_0_) {
/* 430 */     if (p_96537_0_.equalsIgnoreCase("list"))
/*     */     {
/* 432 */       return 0;
/*     */     }
/* 434 */     if (p_96537_0_.equalsIgnoreCase("sidebar"))
/*     */     {
/* 436 */       return 1;
/*     */     }
/* 438 */     if (p_96537_0_.equalsIgnoreCase("belowName"))
/*     */     {
/* 440 */       return 2;
/*     */     }
/*     */ 
/*     */     
/* 444 */     if (p_96537_0_.startsWith("sidebar.team.")) {
/*     */       
/* 446 */       String var1 = p_96537_0_.substring("sidebar.team.".length());
/* 447 */       EnumChatFormatting var2 = EnumChatFormatting.getValueByName(var1);
/*     */       
/* 449 */       if (var2 != null && var2.func_175746_b() >= 0)
/*     */       {
/* 451 */         return var2.func_175746_b() + 3;
/*     */       }
/*     */     } 
/*     */     
/* 455 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] func_178821_h() {
/* 461 */     if (field_178823_g == null) {
/*     */       
/* 463 */       field_178823_g = new String[19];
/*     */       
/* 465 */       for (int var0 = 0; var0 < 19; var0++)
/*     */       {
/* 467 */         field_178823_g[var0] = getObjectiveDisplaySlot(var0);
/*     */       }
/*     */     } 
/*     */     
/* 471 */     return field_178823_g;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\Scoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */