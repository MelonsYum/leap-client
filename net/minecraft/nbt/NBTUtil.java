/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.util.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NBTUtil
/*     */ {
/*     */   private static final String __OBFID = "CL_00001901";
/*     */   
/*     */   public static GameProfile readGameProfileFromNBT(NBTTagCompound compound) {
/*     */     UUID var3;
/*  18 */     String var1 = null;
/*  19 */     String var2 = null;
/*     */     
/*  21 */     if (compound.hasKey("Name", 8))
/*     */     {
/*  23 */       var1 = compound.getString("Name");
/*     */     }
/*     */     
/*  26 */     if (compound.hasKey("Id", 8))
/*     */     {
/*  28 */       var2 = compound.getString("Id");
/*     */     }
/*     */     
/*  31 */     if (StringUtils.isNullOrEmpty(var1) && StringUtils.isNullOrEmpty(var2))
/*     */     {
/*  33 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  41 */       var3 = UUID.fromString(var2);
/*     */     }
/*  43 */     catch (Throwable var12) {
/*     */       
/*  45 */       var3 = null;
/*     */     } 
/*     */     
/*  48 */     GameProfile var4 = new GameProfile(var3, var1);
/*     */     
/*  50 */     if (compound.hasKey("Properties", 10)) {
/*     */       
/*  52 */       NBTTagCompound var5 = compound.getCompoundTag("Properties");
/*  53 */       Iterator<String> var6 = var5.getKeySet().iterator();
/*     */       
/*  55 */       while (var6.hasNext()) {
/*     */         
/*  57 */         String var7 = var6.next();
/*  58 */         NBTTagList var8 = var5.getTagList(var7, 10);
/*     */         
/*  60 */         for (int var9 = 0; var9 < var8.tagCount(); var9++) {
/*     */           
/*  62 */           NBTTagCompound var10 = var8.getCompoundTagAt(var9);
/*  63 */           String var11 = var10.getString("Value");
/*     */           
/*  65 */           if (var10.hasKey("Signature", 8)) {
/*     */             
/*  67 */             var4.getProperties().put(var7, new Property(var7, var11, var10.getString("Signature")));
/*     */           }
/*     */           else {
/*     */             
/*  71 */             var4.getProperties().put(var7, new Property(var7, var11));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NBTTagCompound writeGameProfile(NBTTagCompound p_180708_0_, GameProfile p_180708_1_) {
/*  83 */     if (!StringUtils.isNullOrEmpty(p_180708_1_.getName()))
/*     */     {
/*  85 */       p_180708_0_.setString("Name", p_180708_1_.getName());
/*     */     }
/*     */     
/*  88 */     if (p_180708_1_.getId() != null)
/*     */     {
/*  90 */       p_180708_0_.setString("Id", p_180708_1_.getId().toString());
/*     */     }
/*     */     
/*  93 */     if (!p_180708_1_.getProperties().isEmpty()) {
/*     */       
/*  95 */       NBTTagCompound var2 = new NBTTagCompound();
/*  96 */       Iterator<String> var3 = p_180708_1_.getProperties().keySet().iterator();
/*     */       
/*  98 */       while (var3.hasNext()) {
/*     */         
/* 100 */         String var4 = var3.next();
/* 101 */         NBTTagList var5 = new NBTTagList();
/*     */ 
/*     */         
/* 104 */         for (Iterator<Property> var6 = p_180708_1_.getProperties().get(var4).iterator(); var6.hasNext(); var5.appendTag(var8)) {
/*     */           
/* 106 */           Property var7 = var6.next();
/* 107 */           NBTTagCompound var8 = new NBTTagCompound();
/* 108 */           var8.setString("Value", var7.getValue());
/*     */           
/* 110 */           if (var7.hasSignature())
/*     */           {
/* 112 */             var8.setString("Signature", var7.getSignature());
/*     */           }
/*     */         } 
/*     */         
/* 116 */         var2.setTag(var4, var5);
/*     */       } 
/*     */       
/* 119 */       p_180708_0_.setTag("Properties", var2);
/*     */     } 
/*     */     
/* 122 */     return p_180708_0_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */