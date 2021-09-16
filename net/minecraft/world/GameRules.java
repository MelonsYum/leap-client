/*     */ package net.minecraft.world;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ 
/*     */ public class GameRules
/*     */ {
/*  10 */   private TreeMap theGameRules = new TreeMap<>();
/*     */ 
/*     */   
/*     */   public GameRules() {
/*  14 */     addGameRule("doFireTick", "true", ValueType.BOOLEAN_VALUE);
/*  15 */     addGameRule("mobGriefing", "true", ValueType.BOOLEAN_VALUE);
/*  16 */     addGameRule("keepInventory", "false", ValueType.BOOLEAN_VALUE);
/*  17 */     addGameRule("doMobSpawning", "true", ValueType.BOOLEAN_VALUE);
/*  18 */     addGameRule("doMobLoot", "true", ValueType.BOOLEAN_VALUE);
/*  19 */     addGameRule("doTileDrops", "true", ValueType.BOOLEAN_VALUE);
/*  20 */     addGameRule("commandBlockOutput", "true", ValueType.BOOLEAN_VALUE);
/*  21 */     addGameRule("naturalRegeneration", "true", ValueType.BOOLEAN_VALUE);
/*  22 */     addGameRule("doDaylightCycle", "true", ValueType.BOOLEAN_VALUE);
/*  23 */     addGameRule("logAdminCommands", "true", ValueType.BOOLEAN_VALUE);
/*  24 */     addGameRule("showDeathMessages", "true", ValueType.BOOLEAN_VALUE);
/*  25 */     addGameRule("randomTickSpeed", "3", ValueType.NUMERICAL_VALUE);
/*  26 */     addGameRule("sendCommandFeedback", "true", ValueType.BOOLEAN_VALUE);
/*  27 */     addGameRule("reducedDebugInfo", "false", ValueType.BOOLEAN_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addGameRule(String key, String value, ValueType type) {
/*  32 */     this.theGameRules.put(key, new Value(value, type));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrCreateGameRule(String key, String ruleValue) {
/*  37 */     Value var3 = (Value)this.theGameRules.get(key);
/*     */     
/*  39 */     if (var3 != null) {
/*     */       
/*  41 */       var3.setValue(ruleValue);
/*     */     }
/*     */     else {
/*     */       
/*  45 */       addGameRule(key, ruleValue, ValueType.ANY_VALUE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGameRuleStringValue(String name) {
/*  54 */     Value var2 = (Value)this.theGameRules.get(name);
/*  55 */     return (var2 != null) ? var2.getGameRuleStringValue() : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGameRuleBooleanValue(String name) {
/*  63 */     Value var2 = (Value)this.theGameRules.get(name);
/*  64 */     return (var2 != null) ? var2.getGameRuleBooleanValue() : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt(String name) {
/*  69 */     Value var2 = (Value)this.theGameRules.get(name);
/*  70 */     return (var2 != null) ? var2.getInt() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeGameRulesToNBT() {
/*  78 */     NBTTagCompound var1 = new NBTTagCompound();
/*  79 */     Iterator<String> var2 = this.theGameRules.keySet().iterator();
/*     */     
/*  81 */     while (var2.hasNext()) {
/*     */       
/*  83 */       String var3 = var2.next();
/*  84 */       Value var4 = (Value)this.theGameRules.get(var3);
/*  85 */       var1.setString(var3, var4.getGameRuleStringValue());
/*     */     } 
/*     */     
/*  88 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readGameRulesFromNBT(NBTTagCompound nbt) {
/*  96 */     Set var2 = nbt.getKeySet();
/*  97 */     Iterator<String> var3 = var2.iterator();
/*     */     
/*  99 */     while (var3.hasNext()) {
/*     */       
/* 101 */       String var4 = var3.next();
/* 102 */       String var6 = nbt.getString(var4);
/* 103 */       setOrCreateGameRule(var4, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRules() {
/* 112 */     return (String[])this.theGameRules.keySet().toArray((Object[])new String[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRule(String name) {
/* 120 */     return this.theGameRules.containsKey(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean areSameType(String key, ValueType otherValue) {
/* 125 */     Value var3 = (Value)this.theGameRules.get(key);
/* 126 */     return (var3 != null && (var3.getType() == otherValue || otherValue == ValueType.ANY_VALUE));
/*     */   }
/*     */ 
/*     */   
/*     */   static class Value
/*     */   {
/*     */     private String valueString;
/*     */     private boolean valueBoolean;
/*     */     private int valueInteger;
/*     */     private double valueDouble;
/*     */     private final GameRules.ValueType type;
/*     */     private static final String __OBFID = "CL_00000137";
/*     */     
/*     */     public Value(String value, GameRules.ValueType type) {
/* 140 */       this.type = type;
/* 141 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(String value) {
/* 146 */       this.valueString = value;
/*     */       
/* 148 */       if (value != null) {
/*     */         
/* 150 */         if (value.equals("false")) {
/*     */           
/* 152 */           this.valueBoolean = false;
/*     */           
/*     */           return;
/*     */         } 
/* 156 */         if (value.equals("true")) {
/*     */           
/* 158 */           this.valueBoolean = true;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 163 */       this.valueBoolean = Boolean.parseBoolean(value);
/* 164 */       this.valueInteger = this.valueBoolean ? 1 : 0;
/*     */ 
/*     */       
/*     */       try {
/* 168 */         this.valueInteger = Integer.parseInt(value);
/*     */       }
/* 170 */       catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 177 */         this.valueDouble = Double.parseDouble(value);
/*     */       }
/* 179 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getGameRuleStringValue() {
/* 187 */       return this.valueString;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getGameRuleBooleanValue() {
/* 192 */       return this.valueBoolean;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt() {
/* 197 */       return this.valueInteger;
/*     */     }
/*     */ 
/*     */     
/*     */     public GameRules.ValueType getType() {
/* 202 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ValueType
/*     */   {
/* 208 */     ANY_VALUE("ANY_VALUE", 0, "ANY_VALUE", 0),
/* 209 */     BOOLEAN_VALUE("BOOLEAN_VALUE", 1, "BOOLEAN_VALUE", 1),
/* 210 */     NUMERICAL_VALUE("NUMERICAL_VALUE", 2, "NUMERICAL_VALUE", 2);
/* 211 */     private static final ValueType[] $VALUES = new ValueType[] { ANY_VALUE, BOOLEAN_VALUE, NUMERICAL_VALUE };
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\GameRules.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */