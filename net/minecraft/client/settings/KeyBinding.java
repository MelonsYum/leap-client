/*     */ package net.minecraft.client.settings;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.IntHashMap;
/*     */ 
/*     */ public class KeyBinding
/*     */   implements Comparable {
/*  13 */   private static final List keybindArray = Lists.newArrayList();
/*  14 */   private static final IntHashMap hash = new IntHashMap();
/*  15 */   private static final Set keybindSet = Sets.newHashSet();
/*     */   
/*     */   private final String keyDescription;
/*     */   
/*     */   private final int keyCodeDefault;
/*     */   
/*     */   private final String keyCategory;
/*     */   private int keyCode;
/*     */   public boolean pressed;
/*     */   private int pressTime;
/*     */   private static final String __OBFID = "CL_00000628";
/*     */   
/*     */   public static void onTick(int keyCode) {
/*  28 */     if (keyCode != 0) {
/*     */       
/*  30 */       KeyBinding var1 = (KeyBinding)hash.lookup(keyCode);
/*     */       
/*  32 */       if (var1 != null)
/*     */       {
/*  34 */         var1.pressTime++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setKeyBindState(int keyCode, boolean pressed) {
/*  41 */     if (keyCode != 0) {
/*     */       
/*  43 */       KeyBinding var2 = (KeyBinding)hash.lookup(keyCode);
/*     */       
/*  45 */       if (var2 != null)
/*     */       {
/*  47 */         var2.pressed = pressed;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void unPressAllKeys() {
/*  54 */     Iterator<KeyBinding> var0 = keybindArray.iterator();
/*     */     
/*  56 */     while (var0.hasNext()) {
/*     */       
/*  58 */       KeyBinding var1 = var0.next();
/*  59 */       var1.unpressKey();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void resetKeyBindingArrayAndHash() {
/*  65 */     hash.clearMap();
/*  66 */     Iterator<KeyBinding> var0 = keybindArray.iterator();
/*     */     
/*  68 */     while (var0.hasNext()) {
/*     */       
/*  70 */       KeyBinding var1 = var0.next();
/*  71 */       hash.addKey(var1.keyCode, var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Set getKeybinds() {
/*  77 */     return keybindSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public KeyBinding(String description, int keyCode, String category) {
/*  82 */     this.keyDescription = description;
/*  83 */     this.keyCode = keyCode;
/*  84 */     this.keyCodeDefault = keyCode;
/*  85 */     this.keyCategory = category;
/*  86 */     keybindArray.add(this);
/*  87 */     hash.addKey(keyCode, this);
/*  88 */     keybindSet.add(category);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsKeyPressed() {
/*  93 */     return this.pressed;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyCategory() {
/*  98 */     return this.keyCategory;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPressed() {
/* 103 */     if (this.pressTime == 0)
/*     */     {
/* 105 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 109 */     this.pressTime--;
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void unpressKey() {
/* 116 */     this.pressTime = 0;
/* 117 */     this.pressed = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyDescription() {
/* 122 */     return this.keyDescription;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKeyCodeDefault() {
/* 127 */     return this.keyCodeDefault;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKeyCode() {
/* 132 */     return this.keyCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyCode(int keyCode) {
/* 137 */     this.keyCode = keyCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(KeyBinding p_compareTo_1_) {
/* 142 */     int var2 = I18n.format(this.keyCategory, new Object[0]).compareTo(I18n.format(p_compareTo_1_.keyCategory, new Object[0]));
/*     */     
/* 144 */     if (var2 == 0)
/*     */     {
/* 146 */       var2 = I18n.format(this.keyDescription, new Object[0]).compareTo(I18n.format(p_compareTo_1_.keyDescription, new Object[0]));
/*     */     }
/*     */     
/* 149 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object p_compareTo_1_) {
/* 154 */     return compareTo((KeyBinding)p_compareTo_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\settings\KeyBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */