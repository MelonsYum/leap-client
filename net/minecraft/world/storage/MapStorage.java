/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagShort;
/*     */ import net.minecraft.world.WorldSavedData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapStorage
/*     */ {
/*     */   private ISaveHandler saveHandler;
/*  24 */   protected Map loadedDataMap = Maps.newHashMap();
/*     */ 
/*     */   
/*  27 */   private List loadedDataList = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private Map idCounts = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000604";
/*     */   
/*     */   public MapStorage(ISaveHandler p_i2162_1_) {
/*  37 */     this.saveHandler = p_i2162_1_;
/*  38 */     loadIdCounts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSavedData loadData(Class<WorldSavedData> p_75742_1_, String p_75742_2_) {
/*  47 */     WorldSavedData var3 = (WorldSavedData)this.loadedDataMap.get(p_75742_2_);
/*     */     
/*  49 */     if (var3 != null)
/*     */     {
/*  51 */       return var3;
/*     */     }
/*     */ 
/*     */     
/*  55 */     if (this.saveHandler != null) {
/*     */       
/*     */       try {
/*     */         
/*  59 */         File var4 = this.saveHandler.getMapFileFromName(p_75742_2_);
/*     */         
/*  61 */         if (var4 != null && var4.exists())
/*     */         {
/*     */           
/*     */           try {
/*  65 */             var3 = p_75742_1_.getConstructor(new Class[] { String.class }).newInstance(new Object[] { p_75742_2_ });
/*     */           }
/*  67 */           catch (Exception var7) {
/*     */             
/*  69 */             throw new RuntimeException("Failed to instantiate " + p_75742_1_.toString(), var7);
/*     */           } 
/*     */           
/*  72 */           FileInputStream var5 = new FileInputStream(var4);
/*  73 */           NBTTagCompound var6 = CompressedStreamTools.readCompressed(var5);
/*  74 */           var5.close();
/*  75 */           var3.readFromNBT(var6.getCompoundTag("data"));
/*     */         }
/*     */       
/*  78 */       } catch (Exception var8) {
/*     */         
/*  80 */         var8.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*  84 */     if (var3 != null) {
/*     */       
/*  86 */       this.loadedDataMap.put(p_75742_2_, var3);
/*  87 */       this.loadedDataList.add(var3);
/*     */     } 
/*     */     
/*  90 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String p_75745_1_, WorldSavedData p_75745_2_) {
/*  99 */     if (this.loadedDataMap.containsKey(p_75745_1_))
/*     */     {
/* 101 */       this.loadedDataList.remove(this.loadedDataMap.remove(p_75745_1_));
/*     */     }
/*     */     
/* 104 */     this.loadedDataMap.put(p_75745_1_, p_75745_2_);
/* 105 */     this.loadedDataList.add(p_75745_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAllData() {
/* 113 */     for (int var1 = 0; var1 < this.loadedDataList.size(); var1++) {
/*     */       
/* 115 */       WorldSavedData var2 = this.loadedDataList.get(var1);
/*     */       
/* 117 */       if (var2.isDirty()) {
/*     */         
/* 119 */         saveData(var2);
/* 120 */         var2.setDirty(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveData(WorldSavedData p_75747_1_) {
/* 130 */     if (this.saveHandler != null) {
/*     */       
/*     */       try {
/*     */         
/* 134 */         File var2 = this.saveHandler.getMapFileFromName(p_75747_1_.mapName);
/*     */         
/* 136 */         if (var2 != null)
/*     */         {
/* 138 */           NBTTagCompound var3 = new NBTTagCompound();
/* 139 */           p_75747_1_.writeToNBT(var3);
/* 140 */           NBTTagCompound var4 = new NBTTagCompound();
/* 141 */           var4.setTag("data", (NBTBase)var3);
/* 142 */           FileOutputStream var5 = new FileOutputStream(var2);
/* 143 */           CompressedStreamTools.writeCompressed(var4, var5);
/* 144 */           var5.close();
/*     */         }
/*     */       
/* 147 */       } catch (Exception var6) {
/*     */         
/* 149 */         var6.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadIdCounts() {
/*     */     try {
/* 161 */       this.idCounts.clear();
/*     */       
/* 163 */       if (this.saveHandler == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 168 */       File var1 = this.saveHandler.getMapFileFromName("idcounts");
/*     */       
/* 170 */       if (var1 != null && var1.exists()) {
/*     */         
/* 172 */         DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
/* 173 */         NBTTagCompound var3 = CompressedStreamTools.read(var2);
/* 174 */         var2.close();
/* 175 */         Iterator<String> var4 = var3.getKeySet().iterator();
/*     */         
/* 177 */         while (var4.hasNext()) {
/*     */           
/* 179 */           String var5 = var4.next();
/* 180 */           NBTBase var6 = var3.getTag(var5);
/*     */           
/* 182 */           if (var6 instanceof NBTTagShort)
/*     */           {
/* 184 */             NBTTagShort var7 = (NBTTagShort)var6;
/* 185 */             short var9 = var7.getShort();
/* 186 */             this.idCounts.put(var5, Short.valueOf(var9));
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 191 */     } catch (Exception var10) {
/*     */       
/* 193 */       var10.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUniqueDataId(String p_75743_1_) {
/* 202 */     Short var2 = (Short)this.idCounts.get(p_75743_1_);
/*     */     
/* 204 */     if (var2 == null) {
/*     */       
/* 206 */       var2 = Short.valueOf((short)0);
/*     */     }
/*     */     else {
/*     */       
/* 210 */       var2 = Short.valueOf((short)(var2.shortValue() + 1));
/*     */     } 
/*     */     
/* 213 */     this.idCounts.put(p_75743_1_, var2);
/*     */     
/* 215 */     if (this.saveHandler == null)
/*     */     {
/* 217 */       return var2.shortValue();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 223 */       File var3 = this.saveHandler.getMapFileFromName("idcounts");
/*     */       
/* 225 */       if (var3 != null)
/*     */       {
/* 227 */         NBTTagCompound var4 = new NBTTagCompound();
/* 228 */         Iterator<String> var5 = this.idCounts.keySet().iterator();
/*     */         
/* 230 */         while (var5.hasNext()) {
/*     */           
/* 232 */           String var6 = var5.next();
/* 233 */           short var7 = ((Short)this.idCounts.get(var6)).shortValue();
/* 234 */           var4.setShort(var6, var7);
/*     */         } 
/*     */         
/* 237 */         DataOutputStream var9 = new DataOutputStream(new FileOutputStream(var3));
/* 238 */         CompressedStreamTools.write(var4, var9);
/* 239 */         var9.close();
/*     */       }
/*     */     
/* 242 */     } catch (Exception var8) {
/*     */       
/* 244 */       var8.printStackTrace();
/*     */     } 
/*     */     
/* 247 */     return var2.shortValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\MapStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */