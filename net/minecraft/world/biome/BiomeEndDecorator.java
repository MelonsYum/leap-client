/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.boss.EntityDragon;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.gen.feature.WorldGenSpikes;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeEndDecorator
/*    */   extends BiomeDecorator
/*    */ {
/* 15 */   protected WorldGenerator spikeGen = (WorldGenerator)new WorldGenSpikes(Blocks.end_stone);
/*    */   
/*    */   private static final String __OBFID = "CL_00000188";
/*    */   
/*    */   protected void genDecorations(BiomeGenBase p_150513_1_) {
/* 20 */     generateOres();
/*    */     
/* 22 */     if (this.randomGenerator.nextInt(5) == 0) {
/*    */       
/* 24 */       int var2 = this.randomGenerator.nextInt(16) + 8;
/* 25 */       int var3 = this.randomGenerator.nextInt(16) + 8;
/* 26 */       this.spikeGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.func_175672_r(this.field_180294_c.add(var2, 0, var3)));
/*    */     } 
/*    */     
/* 29 */     if (this.field_180294_c.getX() == 0 && this.field_180294_c.getZ() == 0) {
/*    */       
/* 31 */       EntityDragon var4 = new EntityDragon(this.currentWorld);
/* 32 */       var4.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
/* 33 */       this.currentWorld.spawnEntityInWorld((Entity)var4);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeEndDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */