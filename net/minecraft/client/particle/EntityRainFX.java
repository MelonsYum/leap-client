/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLiquid;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityRainFX extends EntityFX {
/*    */   private static final String __OBFID = "CL_00000934";
/*    */   
/*    */   protected EntityRainFX(World worldIn, double p_i1235_2_, double p_i1235_4_, double p_i1235_6_) {
/* 17 */     super(worldIn, p_i1235_2_, p_i1235_4_, p_i1235_6_, 0.0D, 0.0D, 0.0D);
/* 18 */     this.motionX *= 0.30000001192092896D;
/* 19 */     this.motionY = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
/* 20 */     this.motionZ *= 0.30000001192092896D;
/* 21 */     this.particleRed = 1.0F;
/* 22 */     this.particleGreen = 1.0F;
/* 23 */     this.particleBlue = 1.0F;
/* 24 */     setParticleTextureIndex(19 + this.rand.nextInt(4));
/* 25 */     setSize(0.01F, 0.01F);
/* 26 */     this.particleGravity = 0.06F;
/* 27 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 35 */     this.prevPosX = this.posX;
/* 36 */     this.prevPosY = this.posY;
/* 37 */     this.prevPosZ = this.posZ;
/* 38 */     this.motionY -= this.particleGravity;
/* 39 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 40 */     this.motionX *= 0.9800000190734863D;
/* 41 */     this.motionY *= 0.9800000190734863D;
/* 42 */     this.motionZ *= 0.9800000190734863D;
/*    */     
/* 44 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 46 */       setDead();
/*    */     }
/*    */     
/* 49 */     if (this.onGround) {
/*    */       
/* 51 */       if (Math.random() < 0.5D)
/*    */       {
/* 53 */         setDead();
/*    */       }
/*    */       
/* 56 */       this.motionX *= 0.699999988079071D;
/* 57 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */     
/* 60 */     BlockPos var1 = new BlockPos(this);
/* 61 */     IBlockState var2 = this.worldObj.getBlockState(var1);
/* 62 */     Block var3 = var2.getBlock();
/* 63 */     var3.setBlockBoundsBasedOnState((IBlockAccess)this.worldObj, var1);
/* 64 */     Material var4 = var2.getBlock().getMaterial();
/*    */     
/* 66 */     if (var4.isLiquid() || var4.isSolid()) {
/*    */       
/* 68 */       double var5 = 0.0D;
/*    */       
/* 70 */       if (var2.getBlock() instanceof BlockLiquid) {
/*    */         
/* 72 */         var5 = (1.0F - BlockLiquid.getLiquidHeightPercent(((Integer)var2.getValue((IProperty)BlockLiquid.LEVEL)).intValue()));
/*    */       }
/*    */       else {
/*    */         
/* 76 */         var5 = var3.getBlockBoundsMaxY();
/*    */       } 
/*    */       
/* 79 */       double var7 = MathHelper.floor_double(this.posY) + var5;
/*    */       
/* 81 */       if (this.posY < var7)
/*    */       {
/* 83 */         setDead();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002572";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 94 */       return new EntityRainFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityRainFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */