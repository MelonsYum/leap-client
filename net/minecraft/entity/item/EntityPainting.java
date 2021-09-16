/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityHanging;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPainting
/*     */   extends EntityHanging {
/*     */   public EnumArt art;
/*     */   private static final String __OBFID = "CL_00001556";
/*     */   
/*     */   public EntityPainting(World worldIn) {
/*  22 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPainting(World worldIn, BlockPos p_i45849_2_, EnumFacing p_i45849_3_) {
/*  27 */     super(worldIn, p_i45849_2_);
/*  28 */     ArrayList<EnumArt> var4 = Lists.newArrayList();
/*  29 */     EnumArt[] var5 = EnumArt.values();
/*  30 */     int var6 = var5.length;
/*     */     
/*  32 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/*  34 */       EnumArt var8 = var5[var7];
/*  35 */       this.art = var8;
/*  36 */       func_174859_a(p_i45849_3_);
/*     */       
/*  38 */       if (onValidSurface())
/*     */       {
/*  40 */         var4.add(var8);
/*     */       }
/*     */     } 
/*     */     
/*  44 */     if (!var4.isEmpty())
/*     */     {
/*  46 */       this.art = var4.get(this.rand.nextInt(var4.size()));
/*     */     }
/*     */     
/*  49 */     func_174859_a(p_i45849_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPainting(World worldIn, BlockPos p_i45850_2_, EnumFacing p_i45850_3_, String p_i45850_4_) {
/*  54 */     this(worldIn, p_i45850_2_, p_i45850_3_);
/*  55 */     EnumArt[] var5 = EnumArt.values();
/*  56 */     int var6 = var5.length;
/*     */     
/*  58 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/*  60 */       EnumArt var8 = var5[var7];
/*     */       
/*  62 */       if (var8.title.equals(p_i45850_4_)) {
/*     */         
/*  64 */         this.art = var8;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  69 */     func_174859_a(p_i45850_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  77 */     tagCompound.setString("Motive", this.art.title);
/*  78 */     super.writeEntityToNBT(tagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  86 */     String var2 = tagCompund.getString("Motive");
/*  87 */     EnumArt[] var3 = EnumArt.values();
/*  88 */     int var4 = var3.length;
/*     */     
/*  90 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  92 */       EnumArt var6 = var3[var5];
/*     */       
/*  94 */       if (var6.title.equals(var2))
/*     */       {
/*  96 */         this.art = var6;
/*     */       }
/*     */     } 
/*     */     
/* 100 */     if (this.art == null)
/*     */     {
/* 102 */       this.art = EnumArt.KEBAB;
/*     */     }
/*     */     
/* 105 */     super.readEntityFromNBT(tagCompund);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidthPixels() {
/* 110 */     return this.art.sizeX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/* 115 */     return this.art.sizeY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity p_110128_1_) {
/* 123 */     if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*     */       
/* 125 */       if (p_110128_1_ instanceof EntityPlayer) {
/*     */         
/* 127 */         EntityPlayer var2 = (EntityPlayer)p_110128_1_;
/*     */         
/* 129 */         if (var2.capabilities.isCreativeMode) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 135 */       entityDropItem(new ItemStack(Items.painting), 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
/* 144 */     BlockPos var9 = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
/* 145 */     BlockPos var10 = this.field_174861_a.add((Vec3i)var9);
/* 146 */     setPosition(var10.getX(), var10.getY(), var10.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 151 */     BlockPos var11 = new BlockPos(p_180426_1_ - this.posX, p_180426_3_ - this.posY, p_180426_5_ - this.posZ);
/* 152 */     BlockPos var12 = this.field_174861_a.add((Vec3i)var11);
/* 153 */     setPosition(var12.getX(), var12.getY(), var12.getZ());
/*     */   }
/*     */   
/*     */   public enum EnumArt
/*     */   {
/* 158 */     KEBAB("KEBAB", 0, "Kebab", 16, 16, 0, 0),
/* 159 */     AZTEC("AZTEC", 1, "Aztec", 16, 16, 16, 0),
/* 160 */     ALBAN("ALBAN", 2, "Alban", 16, 16, 32, 0),
/* 161 */     AZTEC_2("AZTEC_2", 3, "Aztec2", 16, 16, 48, 0),
/* 162 */     BOMB("BOMB", 4, "Bomb", 16, 16, 64, 0),
/* 163 */     PLANT("PLANT", 5, "Plant", 16, 16, 80, 0),
/* 164 */     WASTELAND("WASTELAND", 6, "Wasteland", 16, 16, 96, 0),
/* 165 */     POOL("POOL", 7, "Pool", 32, 16, 0, 32),
/* 166 */     COURBET("COURBET", 8, "Courbet", 32, 16, 32, 32),
/* 167 */     SEA("SEA", 9, "Sea", 32, 16, 64, 32),
/* 168 */     SUNSET("SUNSET", 10, "Sunset", 32, 16, 96, 32),
/* 169 */     CREEBET("CREEBET", 11, "Creebet", 32, 16, 128, 32),
/* 170 */     WANDERER("WANDERER", 12, "Wanderer", 16, 32, 0, 64),
/* 171 */     GRAHAM("GRAHAM", 13, "Graham", 16, 32, 16, 64),
/* 172 */     MATCH("MATCH", 14, "Match", 32, 32, 0, 128),
/* 173 */     BUST("BUST", 15, "Bust", 32, 32, 32, 128),
/* 174 */     STAGE("STAGE", 16, "Stage", 32, 32, 64, 128),
/* 175 */     VOID("VOID", 17, "Void", 32, 32, 96, 128),
/* 176 */     SKULL_AND_ROSES("SKULL_AND_ROSES", 18, "SkullAndRoses", 32, 32, 128, 128),
/* 177 */     WITHER("WITHER", 19, "Wither", 32, 32, 160, 128),
/* 178 */     FIGHTERS("FIGHTERS", 20, "Fighters", 64, 32, 0, 96),
/* 179 */     POINTER("POINTER", 21, "Pointer", 64, 64, 0, 192),
/* 180 */     PIGSCENE("PIGSCENE", 22, "Pigscene", 64, 64, 64, 192),
/* 181 */     BURNING_SKULL("BURNING_SKULL", 23, "BurningSkull", 64, 64, 128, 192),
/* 182 */     SKELETON("SKELETON", 24, "Skeleton", 64, 48, 192, 64),
/* 183 */     DONKEY_KONG("DONKEY_KONG", 25, "DonkeyKong", 64, 48, 192, 112);
/* 184 */     public static final int field_180001_A = "SkullAndRoses".length();
/*     */     
/*     */     public final String title;
/*     */     public final int sizeX;
/*     */     public final int sizeY;
/*     */     public final int offsetX;
/*     */     public final int offsetY;
/* 191 */     private static final EnumArt[] $VALUES = new EnumArt[] { KEBAB, AZTEC, ALBAN, AZTEC_2, BOMB, PLANT, WASTELAND, POOL, COURBET, SEA, SUNSET, CREEBET, WANDERER, GRAHAM, MATCH, BUST, STAGE, VOID, SKULL_AND_ROSES, WITHER, FIGHTERS, POINTER, PIGSCENE, BURNING_SKULL, SKELETON, DONKEY_KONG };
/*     */     
/*     */     private static final String __OBFID = "CL_00001557";
/*     */     
/*     */     EnumArt(String p_i1598_1_, int p_i1598_2_, String p_i1598_3_, int p_i1598_4_, int p_i1598_5_, int p_i1598_6_, int p_i1598_7_) {
/* 196 */       this.title = p_i1598_3_;
/* 197 */       this.sizeX = p_i1598_4_;
/* 198 */       this.sizeY = p_i1598_5_;
/* 199 */       this.offsetX = p_i1598_6_;
/* 200 */       this.offsetY = p_i1598_7_;
/*     */     }
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */