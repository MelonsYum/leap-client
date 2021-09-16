/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S34PacketMaps;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec4b;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSavedData;
/*     */ 
/*     */ 
/*     */ public class MapData
/*     */   extends WorldSavedData
/*     */ {
/*     */   public int xCenter;
/*     */   public int zCenter;
/*     */   public byte dimension;
/*     */   public byte scale;
/*  29 */   public byte[] colors = new byte[16384];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public List playersArrayList = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private Map playersHashMap = Maps.newHashMap();
/*  40 */   public Map playersVisibleOnMap = Maps.newLinkedHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000577";
/*     */   
/*     */   public MapData(String p_i2140_1_) {
/*  45 */     super(p_i2140_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176054_a(double p_176054_1_, double p_176054_3_, int p_176054_5_) {
/*  50 */     int var6 = 128 * (1 << p_176054_5_);
/*  51 */     int var7 = MathHelper.floor_double((p_176054_1_ + 64.0D) / var6);
/*  52 */     int var8 = MathHelper.floor_double((p_176054_3_ + 64.0D) / var6);
/*  53 */     this.xCenter = var7 * var6 + var6 / 2 - 64;
/*  54 */     this.zCenter = var8 * var6 + var6 / 2 - 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/*  62 */     this.dimension = nbt.getByte("dimension");
/*  63 */     this.xCenter = nbt.getInteger("xCenter");
/*  64 */     this.zCenter = nbt.getInteger("zCenter");
/*  65 */     this.scale = nbt.getByte("scale");
/*  66 */     this.scale = (byte)MathHelper.clamp_int(this.scale, 0, 4);
/*  67 */     short var2 = nbt.getShort("width");
/*  68 */     short var3 = nbt.getShort("height");
/*     */     
/*  70 */     if (var2 == 128 && var3 == 128) {
/*     */       
/*  72 */       this.colors = nbt.getByteArray("colors");
/*     */     }
/*     */     else {
/*     */       
/*  76 */       byte[] var4 = nbt.getByteArray("colors");
/*  77 */       this.colors = new byte[16384];
/*  78 */       int var5 = (128 - var2) / 2;
/*  79 */       int var6 = (128 - var3) / 2;
/*     */       
/*  81 */       for (int var7 = 0; var7 < var3; var7++) {
/*     */         
/*  83 */         int var8 = var7 + var6;
/*     */         
/*  85 */         if (var8 >= 0 || var8 < 128)
/*     */         {
/*  87 */           for (int var9 = 0; var9 < var2; var9++) {
/*     */             
/*  89 */             int var10 = var9 + var5;
/*     */             
/*  91 */             if (var10 >= 0 || var10 < 128)
/*     */             {
/*  93 */               this.colors[var10 + var8 * 128] = var4[var9 + var7 * var2];
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 106 */     nbt.setByte("dimension", this.dimension);
/* 107 */     nbt.setInteger("xCenter", this.xCenter);
/* 108 */     nbt.setInteger("zCenter", this.zCenter);
/* 109 */     nbt.setByte("scale", this.scale);
/* 110 */     nbt.setShort("width", (short)128);
/* 111 */     nbt.setShort("height", (short)128);
/* 112 */     nbt.setByteArray("colors", this.colors);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateVisiblePlayers(EntityPlayer p_76191_1_, ItemStack p_76191_2_) {
/* 120 */     if (!this.playersHashMap.containsKey(p_76191_1_)) {
/*     */       
/* 122 */       MapInfo var3 = new MapInfo(p_76191_1_);
/* 123 */       this.playersHashMap.put(p_76191_1_, var3);
/* 124 */       this.playersArrayList.add(var3);
/*     */     } 
/*     */     
/* 127 */     if (!p_76191_1_.inventory.hasItemStack(p_76191_2_))
/*     */     {
/* 129 */       this.playersVisibleOnMap.remove(p_76191_1_.getName());
/*     */     }
/*     */     
/* 132 */     for (int var6 = 0; var6 < this.playersArrayList.size(); var6++) {
/*     */       
/* 134 */       MapInfo var4 = this.playersArrayList.get(var6);
/*     */       
/* 136 */       if (!var4.entityplayerObj.isDead && (var4.entityplayerObj.inventory.hasItemStack(p_76191_2_) || p_76191_2_.isOnItemFrame())) {
/*     */         
/* 138 */         if (!p_76191_2_.isOnItemFrame() && var4.entityplayerObj.dimension == this.dimension)
/*     */         {
/* 140 */           func_82567_a(0, var4.entityplayerObj.worldObj, var4.entityplayerObj.getName(), var4.entityplayerObj.posX, var4.entityplayerObj.posZ, var4.entityplayerObj.rotationYaw);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 145 */         this.playersHashMap.remove(var4.entityplayerObj);
/* 146 */         this.playersArrayList.remove(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     if (p_76191_2_.isOnItemFrame()) {
/*     */       
/* 152 */       EntityItemFrame var7 = p_76191_2_.getItemFrame();
/* 153 */       BlockPos var9 = var7.func_174857_n();
/* 154 */       func_82567_a(1, p_76191_1_.worldObj, "frame-" + var7.getEntityId(), var9.getX(), var9.getZ(), (var7.field_174860_b.getHorizontalIndex() * 90));
/*     */     } 
/*     */     
/* 157 */     if (p_76191_2_.hasTagCompound() && p_76191_2_.getTagCompound().hasKey("Decorations", 9)) {
/*     */       
/* 159 */       NBTTagList var8 = p_76191_2_.getTagCompound().getTagList("Decorations", 10);
/*     */       
/* 161 */       for (int var10 = 0; var10 < var8.tagCount(); var10++) {
/*     */         
/* 163 */         NBTTagCompound var5 = var8.getCompoundTagAt(var10);
/*     */         
/* 165 */         if (!this.playersVisibleOnMap.containsKey(var5.getString("id")))
/*     */         {
/* 167 */           func_82567_a(var5.getByte("type"), p_76191_1_.worldObj, var5.getString("id"), var5.getDouble("x"), var5.getDouble("z"), var5.getDouble("rot"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_82567_a(int p_82567_1_, World worldIn, String p_82567_3_, double p_82567_4_, double p_82567_6_, double p_82567_8_) {
/*     */     byte var15;
/* 175 */     int var10 = 1 << this.scale;
/* 176 */     float var11 = (float)(p_82567_4_ - this.xCenter) / var10;
/* 177 */     float var12 = (float)(p_82567_6_ - this.zCenter) / var10;
/* 178 */     byte var13 = (byte)(int)((var11 * 2.0F) + 0.5D);
/* 179 */     byte var14 = (byte)(int)((var12 * 2.0F) + 0.5D);
/* 180 */     byte var16 = 63;
/*     */ 
/*     */     
/* 183 */     if (var11 >= -var16 && var12 >= -var16 && var11 <= var16 && var12 <= var16) {
/*     */       
/* 185 */       p_82567_8_ += (p_82567_8_ < 0.0D) ? -8.0D : 8.0D;
/* 186 */       var15 = (byte)(int)(p_82567_8_ * 16.0D / 360.0D);
/*     */       
/* 188 */       if (this.dimension < 0)
/*     */       {
/* 190 */         int var17 = (int)(worldIn.getWorldInfo().getWorldTime() / 10L);
/* 191 */         var15 = (byte)(var17 * var17 * 34187121 + var17 * 121 >> 15 & 0xF);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 196 */       if (Math.abs(var11) >= 320.0F || Math.abs(var12) >= 320.0F) {
/*     */         
/* 198 */         this.playersVisibleOnMap.remove(p_82567_3_);
/*     */         
/*     */         return;
/*     */       } 
/* 202 */       p_82567_1_ = 6;
/* 203 */       var15 = 0;
/*     */       
/* 205 */       if (var11 <= -var16)
/*     */       {
/* 207 */         var13 = (byte)(int)((var16 * 2) + 2.5D);
/*     */       }
/*     */       
/* 210 */       if (var12 <= -var16)
/*     */       {
/* 212 */         var14 = (byte)(int)((var16 * 2) + 2.5D);
/*     */       }
/*     */       
/* 215 */       if (var11 >= var16)
/*     */       {
/* 217 */         var13 = (byte)(var16 * 2 + 1);
/*     */       }
/*     */       
/* 220 */       if (var12 >= var16)
/*     */       {
/* 222 */         var14 = (byte)(var16 * 2 + 1);
/*     */       }
/*     */     } 
/*     */     
/* 226 */     this.playersVisibleOnMap.put(p_82567_3_, new Vec4b((byte)p_82567_1_, var13, var14, var15));
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet func_176052_a(ItemStack p_176052_1_, World worldIn, EntityPlayer p_176052_3_) {
/* 231 */     MapInfo var4 = (MapInfo)this.playersHashMap.get(p_176052_3_);
/* 232 */     return (var4 == null) ? null : var4.func_176101_a(p_176052_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176053_a(int p_176053_1_, int p_176053_2_) {
/* 237 */     markDirty();
/* 238 */     Iterator<MapInfo> var3 = this.playersArrayList.iterator();
/*     */     
/* 240 */     while (var3.hasNext()) {
/*     */       
/* 242 */       MapInfo var4 = var3.next();
/* 243 */       var4.func_176102_a(p_176053_1_, p_176053_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MapInfo func_82568_a(EntityPlayer p_82568_1_) {
/* 249 */     MapInfo var2 = (MapInfo)this.playersHashMap.get(p_82568_1_);
/*     */     
/* 251 */     if (var2 == null) {
/*     */       
/* 253 */       var2 = new MapInfo(p_82568_1_);
/* 254 */       this.playersHashMap.put(p_82568_1_, var2);
/* 255 */       this.playersArrayList.add(var2);
/*     */     } 
/*     */     
/* 258 */     return var2;
/*     */   }
/*     */   
/*     */   public class MapInfo
/*     */   {
/*     */     public final EntityPlayer entityplayerObj;
/*     */     private boolean field_176105_d = true;
/* 265 */     private int field_176106_e = 0;
/* 266 */     private int field_176103_f = 0;
/* 267 */     private int field_176104_g = 127;
/* 268 */     private int field_176108_h = 127;
/*     */     
/*     */     private int field_176109_i;
/*     */     public int field_82569_d;
/*     */     private static final String __OBFID = "CL_00000578";
/*     */     
/*     */     public MapInfo(EntityPlayer p_i2138_2_) {
/* 275 */       this.entityplayerObj = p_i2138_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Packet func_176101_a(ItemStack p_176101_1_) {
/* 280 */       if (this.field_176105_d) {
/*     */         
/* 282 */         this.field_176105_d = false;
/* 283 */         return (Packet)new S34PacketMaps(p_176101_1_.getMetadata(), MapData.this.scale, MapData.this.playersVisibleOnMap.values(), MapData.this.colors, this.field_176106_e, this.field_176103_f, this.field_176104_g + 1 - this.field_176106_e, this.field_176108_h + 1 - this.field_176103_f);
/*     */       } 
/*     */ 
/*     */       
/* 287 */       return (this.field_176109_i++ % 5 == 0) ? (Packet)new S34PacketMaps(p_176101_1_.getMetadata(), MapData.this.scale, MapData.this.playersVisibleOnMap.values(), MapData.this.colors, 0, 0, 0, 0) : null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_176102_a(int p_176102_1_, int p_176102_2_) {
/* 293 */       if (this.field_176105_d) {
/*     */         
/* 295 */         this.field_176106_e = Math.min(this.field_176106_e, p_176102_1_);
/* 296 */         this.field_176103_f = Math.min(this.field_176103_f, p_176102_2_);
/* 297 */         this.field_176104_g = Math.max(this.field_176104_g, p_176102_1_);
/* 298 */         this.field_176108_h = Math.max(this.field_176108_h, p_176102_2_);
/*     */       }
/*     */       else {
/*     */         
/* 302 */         this.field_176105_d = true;
/* 303 */         this.field_176106_e = p_176102_1_;
/* 304 */         this.field_176103_f = p_176102_2_;
/* 305 */         this.field_176104_g = p_176102_1_;
/* 306 */         this.field_176108_h = p_176102_2_;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\MapData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */