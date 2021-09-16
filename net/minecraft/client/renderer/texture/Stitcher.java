/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.renderer.StitcherException;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class Stitcher
/*     */ {
/*     */   private final int mipmapLevelStitcher;
/*  16 */   private final Set setStitchHolders = Sets.newHashSetWithExpectedSize(256);
/*  17 */   private final List stitchSlots = Lists.newArrayListWithCapacity(256);
/*     */   
/*     */   private int currentWidth;
/*     */   
/*     */   private int currentHeight;
/*     */   
/*     */   private final int maxWidth;
/*     */   private final int maxHeight;
/*     */   private final boolean forcePowerOf2;
/*     */   private final int maxTileDimension;
/*     */   private static final String __OBFID = "CL_00001054";
/*     */   
/*     */   public Stitcher(int p_i45095_1_, int p_i45095_2_, boolean p_i45095_3_, int p_i45095_4_, int p_i45095_5_) {
/*  30 */     this.mipmapLevelStitcher = p_i45095_5_;
/*  31 */     this.maxWidth = p_i45095_1_;
/*  32 */     this.maxHeight = p_i45095_2_;
/*  33 */     this.forcePowerOf2 = p_i45095_3_;
/*  34 */     this.maxTileDimension = p_i45095_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentWidth() {
/*  39 */     return this.currentWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentHeight() {
/*  44 */     return this.currentHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSprite(TextureAtlasSprite p_110934_1_) {
/*  49 */     Holder var2 = new Holder(p_110934_1_, this.mipmapLevelStitcher);
/*     */     
/*  51 */     if (this.maxTileDimension > 0)
/*     */     {
/*  53 */       var2.setNewDimension(this.maxTileDimension);
/*     */     }
/*     */     
/*  56 */     this.setStitchHolders.add(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doStitch() {
/*  61 */     Holder[] var1 = (Holder[])this.setStitchHolders.toArray((Object[])new Holder[this.setStitchHolders.size()]);
/*  62 */     Arrays.sort((Object[])var1);
/*  63 */     Holder[] var2 = var1;
/*  64 */     int var3 = var1.length;
/*     */     
/*  66 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  68 */       Holder var5 = var2[var4];
/*     */       
/*  70 */       if (!allocateSlot(var5)) {
/*     */         
/*  72 */         String var6 = String.format("Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?", new Object[] { var5.getAtlasSprite().getIconName(), Integer.valueOf(var5.getAtlasSprite().getIconWidth()), Integer.valueOf(var5.getAtlasSprite().getIconHeight()), Integer.valueOf(this.currentWidth), Integer.valueOf(this.currentHeight), Integer.valueOf(this.maxWidth), Integer.valueOf(this.maxHeight) });
/*  73 */         throw new StitcherException(var5, var6);
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     if (this.forcePowerOf2) {
/*     */       
/*  79 */       this.currentWidth = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
/*  80 */       this.currentHeight = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List getStichSlots() {
/*  86 */     ArrayList var1 = Lists.newArrayList();
/*  87 */     Iterator<Slot> var2 = this.stitchSlots.iterator();
/*     */     
/*  89 */     while (var2.hasNext()) {
/*     */       
/*  91 */       Slot var7 = var2.next();
/*  92 */       var7.getAllStitchSlots(var1);
/*     */     } 
/*     */     
/*  95 */     ArrayList<TextureAtlasSprite> var71 = Lists.newArrayList();
/*  96 */     Iterator<Slot> var8 = var1.iterator();
/*     */     
/*  98 */     while (var8.hasNext()) {
/*     */       
/* 100 */       Slot var4 = var8.next();
/* 101 */       Holder var5 = var4.getStitchHolder();
/* 102 */       TextureAtlasSprite var6 = var5.getAtlasSprite();
/* 103 */       var6.initSprite(this.currentWidth, this.currentHeight, var4.getOriginX(), var4.getOriginY(), var5.isRotated());
/* 104 */       var71.add(var6);
/*     */     } 
/*     */     
/* 107 */     return var71;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getMipmapDimension(int p_147969_0_, int p_147969_1_) {
/* 112 */     return (p_147969_0_ >> p_147969_1_) + (((p_147969_0_ & (1 << p_147969_1_) - 1) == 0) ? 0 : 1) << p_147969_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean allocateSlot(Holder p_94310_1_) {
/* 120 */     for (int var2 = 0; var2 < this.stitchSlots.size(); var2++) {
/*     */       
/* 122 */       if (((Slot)this.stitchSlots.get(var2)).addSlot(p_94310_1_))
/*     */       {
/* 124 */         return true;
/*     */       }
/*     */       
/* 127 */       p_94310_1_.rotate();
/*     */       
/* 129 */       if (((Slot)this.stitchSlots.get(var2)).addSlot(p_94310_1_))
/*     */       {
/* 131 */         return true;
/*     */       }
/*     */       
/* 134 */       p_94310_1_.rotate();
/*     */     } 
/*     */     
/* 137 */     return expandAndAllocateSlot(p_94310_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean expandAndAllocateSlot(Holder p_94311_1_) {
/*     */     boolean var4;
/*     */     Slot var152;
/* 145 */     int var2 = Math.min(p_94311_1_.getWidth(), p_94311_1_.getHeight());
/* 146 */     boolean var3 = (this.currentWidth == 0 && this.currentHeight == 0);
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (this.forcePowerOf2) {
/*     */       
/* 152 */       int i = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
/* 153 */       int var15 = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
/* 154 */       int var14 = MathHelper.roundUpToPowerOfTwo(this.currentWidth + var2);
/* 155 */       int var8 = MathHelper.roundUpToPowerOfTwo(this.currentHeight + var2);
/* 156 */       boolean var9 = (var14 <= this.maxWidth);
/* 157 */       boolean var10 = (var8 <= this.maxHeight);
/*     */       
/* 159 */       if (!var9 && !var10)
/*     */       {
/* 161 */         return false;
/*     */       }
/*     */       
/* 164 */       boolean var11 = (i != var14);
/* 165 */       boolean var12 = (var15 != var8);
/*     */       
/* 167 */       if (var11 ^ var12)
/*     */       {
/* 169 */         var4 = !var11;
/*     */       }
/*     */       else
/*     */       {
/* 173 */         var4 = (var9 && i <= var15);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 178 */       boolean var151 = (this.currentWidth + var2 <= this.maxWidth);
/* 179 */       boolean var141 = (this.currentHeight + var2 <= this.maxHeight);
/*     */       
/* 181 */       if (!var151 && !var141)
/*     */       {
/* 183 */         return false;
/*     */       }
/*     */       
/* 186 */       var4 = (var151 && (var3 || this.currentWidth <= this.currentHeight));
/*     */     } 
/*     */     
/* 189 */     int var5 = Math.max(p_94311_1_.getWidth(), p_94311_1_.getHeight());
/*     */     
/* 191 */     if (MathHelper.roundUpToPowerOfTwo((!var4 ? this.currentHeight : this.currentWidth) + var5) > (!var4 ? this.maxHeight : this.maxWidth))
/*     */     {
/* 193 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     if (var4) {
/*     */       
/* 201 */       if (p_94311_1_.getWidth() > p_94311_1_.getHeight())
/*     */       {
/* 203 */         p_94311_1_.rotate();
/*     */       }
/*     */       
/* 206 */       if (this.currentHeight == 0)
/*     */       {
/* 208 */         this.currentHeight = p_94311_1_.getHeight();
/*     */       }
/*     */       
/* 211 */       var152 = new Slot(this.currentWidth, 0, p_94311_1_.getWidth(), this.currentHeight);
/* 212 */       this.currentWidth += p_94311_1_.getWidth();
/*     */     }
/*     */     else {
/*     */       
/* 216 */       var152 = new Slot(0, this.currentHeight, this.currentWidth, p_94311_1_.getHeight());
/* 217 */       this.currentHeight += p_94311_1_.getHeight();
/*     */     } 
/*     */     
/* 220 */     var152.addSlot(p_94311_1_);
/* 221 */     this.stitchSlots.add(var152);
/* 222 */     return true;
/*     */   }
/*     */   
/*     */   public static class Holder
/*     */     implements Comparable
/*     */   {
/*     */     private final TextureAtlasSprite theTexture;
/*     */     private final int width;
/*     */     private final int height;
/*     */     private final int mipmapLevelHolder;
/*     */     private boolean rotated;
/* 233 */     private float scaleFactor = 1.0F;
/*     */     
/*     */     private static final String __OBFID = "CL_00001055";
/*     */     
/*     */     public Holder(TextureAtlasSprite p_i45094_1_, int p_i45094_2_) {
/* 238 */       this.theTexture = p_i45094_1_;
/* 239 */       this.width = p_i45094_1_.getIconWidth();
/* 240 */       this.height = p_i45094_1_.getIconHeight();
/* 241 */       this.mipmapLevelHolder = p_i45094_2_;
/* 242 */       this.rotated = (Stitcher.getMipmapDimension(this.height, p_i45094_2_) > Stitcher.getMipmapDimension(this.width, p_i45094_2_));
/*     */     }
/*     */ 
/*     */     
/*     */     public TextureAtlasSprite getAtlasSprite() {
/* 247 */       return this.theTexture;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getWidth() {
/* 252 */       return this.rotated ? Stitcher.getMipmapDimension((int)(this.height * this.scaleFactor), this.mipmapLevelHolder) : Stitcher.getMipmapDimension((int)(this.width * this.scaleFactor), this.mipmapLevelHolder);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHeight() {
/* 257 */       return this.rotated ? Stitcher.getMipmapDimension((int)(this.width * this.scaleFactor), this.mipmapLevelHolder) : Stitcher.getMipmapDimension((int)(this.height * this.scaleFactor), this.mipmapLevelHolder);
/*     */     }
/*     */ 
/*     */     
/*     */     public void rotate() {
/* 262 */       this.rotated = !this.rotated;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isRotated() {
/* 267 */       return this.rotated;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setNewDimension(int p_94196_1_) {
/* 272 */       if (this.width > p_94196_1_ && this.height > p_94196_1_)
/*     */       {
/* 274 */         this.scaleFactor = p_94196_1_ / Math.min(this.width, this.height);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 280 */       return "Holder{width=" + this.width + ", height=" + this.height + ", name=" + this.theTexture.getIconName() + '}';
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int compareTo(Holder p_compareTo_1_) {
/*     */       int var2;
/* 287 */       if (getHeight() == p_compareTo_1_.getHeight()) {
/*     */         
/* 289 */         if (getWidth() == p_compareTo_1_.getWidth()) {
/*     */           
/* 291 */           if (this.theTexture.getIconName() == null)
/*     */           {
/* 293 */             return (p_compareTo_1_.theTexture.getIconName() == null) ? 0 : -1;
/*     */           }
/*     */           
/* 296 */           return this.theTexture.getIconName().compareTo(p_compareTo_1_.theTexture.getIconName());
/*     */         } 
/*     */         
/* 299 */         var2 = (getWidth() < p_compareTo_1_.getWidth()) ? 1 : -1;
/*     */       }
/*     */       else {
/*     */         
/* 303 */         var2 = (getHeight() < p_compareTo_1_.getHeight()) ? 1 : -1;
/*     */       } 
/*     */       
/* 306 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(Object p_compareTo_1_) {
/* 311 */       return compareTo((Holder)p_compareTo_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Slot
/*     */   {
/*     */     private final int originX;
/*     */     private final int originY;
/*     */     private final int width;
/*     */     private final int height;
/*     */     private List subSlots;
/*     */     private Stitcher.Holder holder;
/*     */     private static final String __OBFID = "CL_00001056";
/*     */     
/*     */     public Slot(int p_i1277_1_, int p_i1277_2_, int p_i1277_3_, int p_i1277_4_) {
/* 327 */       this.originX = p_i1277_1_;
/* 328 */       this.originY = p_i1277_2_;
/* 329 */       this.width = p_i1277_3_;
/* 330 */       this.height = p_i1277_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Stitcher.Holder getStitchHolder() {
/* 335 */       return this.holder;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getOriginX() {
/* 340 */       return this.originX;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getOriginY() {
/* 345 */       return this.originY;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addSlot(Stitcher.Holder p_94182_1_) {
/* 350 */       if (this.holder != null)
/*     */       {
/* 352 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 356 */       int var2 = p_94182_1_.getWidth();
/* 357 */       int var3 = p_94182_1_.getHeight();
/*     */       
/* 359 */       if (var2 <= this.width && var3 <= this.height) {
/*     */         
/* 361 */         if (var2 == this.width && var3 == this.height) {
/*     */           
/* 363 */           this.holder = p_94182_1_;
/* 364 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 368 */         if (this.subSlots == null) {
/*     */           
/* 370 */           this.subSlots = Lists.newArrayListWithCapacity(1);
/* 371 */           this.subSlots.add(new Slot(this.originX, this.originY, var2, var3));
/* 372 */           int var8 = this.width - var2;
/* 373 */           int var9 = this.height - var3;
/*     */           
/* 375 */           if (var9 > 0 && var8 > 0) {
/*     */             
/* 377 */             int var6 = Math.max(this.height, var8);
/* 378 */             int var7 = Math.max(this.width, var9);
/*     */             
/* 380 */             if (var6 >= var7)
/*     */             {
/* 382 */               this.subSlots.add(new Slot(this.originX, this.originY + var3, var2, var9));
/* 383 */               this.subSlots.add(new Slot(this.originX + var2, this.originY, var8, this.height));
/*     */             }
/*     */             else
/*     */             {
/* 387 */               this.subSlots.add(new Slot(this.originX + var2, this.originY, var8, var3));
/* 388 */               this.subSlots.add(new Slot(this.originX, this.originY + var3, this.width, var9));
/*     */             }
/*     */           
/* 391 */           } else if (var8 == 0) {
/*     */             
/* 393 */             this.subSlots.add(new Slot(this.originX, this.originY + var3, var2, var9));
/*     */           }
/* 395 */           else if (var9 == 0) {
/*     */             
/* 397 */             this.subSlots.add(new Slot(this.originX + var2, this.originY, var8, var3));
/*     */           } 
/*     */         } 
/*     */         
/* 401 */         Iterator<Slot> var81 = this.subSlots.iterator();
/*     */         
/* 403 */         while (var81.hasNext()) {
/*     */           
/* 405 */           Slot var91 = var81.next();
/*     */           
/* 407 */           if (var91.addSlot(p_94182_1_))
/*     */           {
/* 409 */             return true;
/*     */           }
/*     */         } 
/*     */         
/* 413 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 418 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void getAllStitchSlots(List<Slot> p_94184_1_) {
/* 425 */       if (this.holder != null) {
/*     */         
/* 427 */         p_94184_1_.add(this);
/*     */       }
/* 429 */       else if (this.subSlots != null) {
/*     */         
/* 431 */         Iterator<Slot> var2 = this.subSlots.iterator();
/*     */         
/* 433 */         while (var2.hasNext()) {
/*     */           
/* 435 */           Slot var3 = var2.next();
/* 436 */           var3.getAllStitchSlots(p_94184_1_);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 443 */       return "Slot{originX=" + this.originX + ", originY=" + this.originY + ", width=" + this.width + ", height=" + this.height + ", texture=" + this.holder + ", subSlots=" + this.subSlots + '}';
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\Stitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */