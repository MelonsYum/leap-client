/*     */ package net.minecraft.client.audio;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SoundList
/*     */ {
/*   8 */   private final List soundList = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   private boolean replaceExisting;
/*     */   
/*     */   private SoundCategory category;
/*     */   
/*     */   private static final String __OBFID = "CL_00001121";
/*     */ 
/*     */   
/*     */   public List getSoundList() {
/*  19 */     return this.soundList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReplaceExisting() {
/*  24 */     return this.replaceExisting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReplaceExisting(boolean p_148572_1_) {
/*  29 */     this.replaceExisting = p_148572_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  34 */     return this.category;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSoundCategory(SoundCategory p_148571_1_) {
/*  39 */     this.category = p_148571_1_;
/*     */   }
/*     */   
/*     */   public static class SoundEntry
/*     */   {
/*     */     private String name;
/*  45 */     private float volume = 1.0F;
/*  46 */     private float pitch = 1.0F;
/*  47 */     private int field_148565_d = 1;
/*     */     
/*     */     private Type field_148566_e;
/*     */     private boolean field_148564_f;
/*     */     private static final String __OBFID = "CL_00001122";
/*     */     
/*     */     public SoundEntry() {
/*  54 */       this.field_148566_e = Type.FILE;
/*  55 */       this.field_148564_f = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getSoundEntryName() {
/*  60 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSoundEntryName(String p_148561_1_) {
/*  65 */       this.name = p_148561_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getSoundEntryVolume() {
/*  70 */       return this.volume;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSoundEntryVolume(float p_148553_1_) {
/*  75 */       this.volume = p_148553_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getSoundEntryPitch() {
/*  80 */       return this.pitch;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSoundEntryPitch(float p_148559_1_) {
/*  85 */       this.pitch = p_148559_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSoundEntryWeight() {
/*  90 */       return this.field_148565_d;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSoundEntryWeight(int p_148554_1_) {
/*  95 */       this.field_148565_d = p_148554_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Type getSoundEntryType() {
/* 100 */       return this.field_148566_e;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSoundEntryType(Type p_148562_1_) {
/* 105 */       this.field_148566_e = p_148562_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isStreaming() {
/* 110 */       return this.field_148564_f;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setStreaming(boolean p_148557_1_) {
/* 115 */       this.field_148564_f = p_148557_1_;
/*     */     }
/*     */     
/*     */     public enum Type
/*     */     {
/* 120 */       FILE("FILE", 0, "file"),
/* 121 */       SOUND_EVENT("SOUND_EVENT", 1, "event");
/*     */       
/*     */       private final String field_148583_c;
/* 124 */       private static final Type[] $VALUES = new Type[] { FILE, SOUND_EVENT };
/*     */       
/*     */       private static final String __OBFID = "CL_00001123";
/*     */       
/*     */       Type(String p_i45109_1_, int p_i45109_2_, String p_i45109_3_) {
/* 129 */         this.field_148583_c = p_i45109_3_;
/*     */       } static {
/*     */       
/*     */       }
/*     */       public static Type getType(String p_148580_0_) {
/* 134 */         Type[] var1 = values();
/* 135 */         int var2 = var1.length;
/*     */         
/* 137 */         for (int var3 = 0; var3 < var2; var3++) {
/*     */           
/* 139 */           Type var4 = var1[var3];
/*     */           
/* 141 */           if (var4.field_148583_c.equals(p_148580_0_))
/*     */           {
/* 143 */             return var4;
/*     */           }
/*     */         } 
/*     */         
/* 147 */         return null;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */