/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class SoundEventAccessorComposite
/*    */   implements ISoundEventAccessor
/*    */ {
/* 12 */   private final List soundPool = Lists.newArrayList();
/* 13 */   private final Random rnd = new Random();
/*    */   
/*    */   private final ResourceLocation soundLocation;
/*    */   private final SoundCategory category;
/*    */   private double eventPitch;
/*    */   private double eventVolume;
/*    */   private static final String __OBFID = "CL_00001146";
/*    */   
/*    */   public SoundEventAccessorComposite(ResourceLocation soundLocation, double pitch, double volume, SoundCategory category) {
/* 22 */     this.soundLocation = soundLocation;
/* 23 */     this.eventVolume = volume;
/* 24 */     this.eventPitch = pitch;
/* 25 */     this.category = category;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWeight() {
/* 30 */     int var1 = 0;
/*    */ 
/*    */     
/* 33 */     for (Iterator<ISoundEventAccessor> var2 = this.soundPool.iterator(); var2.hasNext(); var1 += var3.getWeight())
/*    */     {
/* 35 */       ISoundEventAccessor var3 = var2.next();
/*    */     }
/*    */     
/* 38 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundPoolEntry cloneEntry1() {
/* 43 */     int var1 = getWeight();
/*    */     
/* 45 */     if (!this.soundPool.isEmpty() && var1 != 0) {
/*    */       ISoundEventAccessor var4;
/* 47 */       int var2 = this.rnd.nextInt(var1);
/* 48 */       Iterator<ISoundEventAccessor> var3 = this.soundPool.iterator();
/*    */ 
/*    */ 
/*    */       
/*    */       do {
/* 53 */         if (!var3.hasNext())
/*    */         {
/* 55 */           return SoundHandler.missing_sound;
/*    */         }
/*    */         
/* 58 */         var4 = var3.next();
/* 59 */         var2 -= var4.getWeight();
/*    */       }
/* 61 */       while (var2 >= 0);
/*    */       
/* 63 */       SoundPoolEntry var5 = (SoundPoolEntry)var4.cloneEntry();
/* 64 */       var5.setPitch(var5.getPitch() * this.eventPitch);
/* 65 */       var5.setVolume(var5.getVolume() * this.eventVolume);
/* 66 */       return var5;
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return SoundHandler.missing_sound;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addSoundToEventPool(ISoundEventAccessor p_148727_1_) {
/* 76 */     this.soundPool.add(p_148727_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getSoundEventLocation() {
/* 81 */     return this.soundLocation;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundCategory getSoundCategory() {
/* 86 */     return this.category;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object cloneEntry() {
/* 91 */     return cloneEntry1();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundEventAccessorComposite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */