/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityEnderChest
/*     */   extends TileEntity
/*     */   implements IUpdatePlayerListBox
/*     */ {
/*     */   public float field_145972_a;
/*     */   public float prevLidAngle;
/*     */   public int field_145973_j;
/*     */   private int field_145974_k;
/*     */   private static final String __OBFID = "CL_00000355";
/*     */   
/*     */   public void update() {
/*  22 */     if (++this.field_145974_k % 20 * 4 == 0)
/*     */     {
/*  24 */       this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.field_145973_j);
/*     */     }
/*     */     
/*  27 */     this.prevLidAngle = this.field_145972_a;
/*  28 */     int var1 = this.pos.getX();
/*  29 */     int var2 = this.pos.getY();
/*  30 */     int var3 = this.pos.getZ();
/*  31 */     float var4 = 0.1F;
/*     */ 
/*     */     
/*  34 */     if (this.field_145973_j > 0 && this.field_145972_a == 0.0F) {
/*     */       
/*  36 */       double var5 = var1 + 0.5D;
/*  37 */       double var7 = var3 + 0.5D;
/*  38 */       this.worldObj.playSoundEffect(var5, var2 + 0.5D, var7, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */     
/*  41 */     if ((this.field_145973_j == 0 && this.field_145972_a > 0.0F) || (this.field_145973_j > 0 && this.field_145972_a < 1.0F)) {
/*     */       
/*  43 */       float var11 = this.field_145972_a;
/*     */       
/*  45 */       if (this.field_145973_j > 0) {
/*     */         
/*  47 */         this.field_145972_a += var4;
/*     */       }
/*     */       else {
/*     */         
/*  51 */         this.field_145972_a -= var4;
/*     */       } 
/*     */       
/*  54 */       if (this.field_145972_a > 1.0F)
/*     */       {
/*  56 */         this.field_145972_a = 1.0F;
/*     */       }
/*     */       
/*  59 */       float var6 = 0.5F;
/*     */       
/*  61 */       if (this.field_145972_a < var6 && var11 >= var6) {
/*     */         
/*  63 */         double var7 = var1 + 0.5D;
/*  64 */         double var9 = var3 + 0.5D;
/*  65 */         this.worldObj.playSoundEffect(var7, var2 + 0.5D, var9, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */       
/*  68 */       if (this.field_145972_a < 0.0F)
/*     */       {
/*  70 */         this.field_145972_a = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int id, int type) {
/*  77 */     if (id == 1) {
/*     */       
/*  79 */       this.field_145973_j = type;
/*  80 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return super.receiveClientEvent(id, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/*  93 */     updateContainingBlockInfo();
/*  94 */     super.invalidate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145969_a() {
/*  99 */     this.field_145973_j++;
/* 100 */     this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.field_145973_j);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145970_b() {
/* 105 */     this.field_145973_j--;
/* 106 */     this.worldObj.addBlockEvent(this.pos, Blocks.ender_chest, 1, this.field_145973_j);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_145971_a(EntityPlayer p_145971_1_) {
/* 111 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((p_145971_1_.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */