/*    */ package net.minecraft.world.gen.structure;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.monster.EntityBlaze;
/*    */ import net.minecraft.entity.monster.EntityMagmaCube;
/*    */ import net.minecraft.entity.monster.EntityPigZombie;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class MapGenNetherBridge
/*    */   extends MapGenStructure {
/* 15 */   private List spawnList = Lists.newArrayList();
/*    */   
/*    */   private static final String __OBFID = "CL_00000451";
/*    */   
/*    */   public MapGenNetherBridge() {
/* 20 */     this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 2, 3));
/* 21 */     this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
/* 22 */     this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
/* 23 */     this.spawnList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStructureName() {
/* 28 */     return "Fortress";
/*    */   }
/*    */ 
/*    */   
/*    */   public List getSpawnList() {
/* 33 */     return this.spawnList;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/* 38 */     int var3 = p_75047_1_ >> 4;
/* 39 */     int var4 = p_75047_2_ >> 4;
/* 40 */     this.rand.setSeed((var3 ^ var4 << 4) ^ this.worldObj.getSeed());
/* 41 */     this.rand.nextInt();
/* 42 */     return (this.rand.nextInt(3) != 0) ? false : ((p_75047_1_ != (var3 << 4) + 4 + this.rand.nextInt(8)) ? false : ((p_75047_2_ == (var4 << 4) + 4 + this.rand.nextInt(8))));
/*    */   }
/*    */ 
/*    */   
/*    */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/* 47 */     return new Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
/*    */   }
/*    */   
/*    */   public static class Start
/*    */     extends StructureStart
/*    */   {
/*    */     private static final String __OBFID = "CL_00000452";
/*    */     
/*    */     public Start() {}
/*    */     
/*    */     public Start(World worldIn, Random p_i2040_2_, int p_i2040_3_, int p_i2040_4_) {
/* 58 */       super(p_i2040_3_, p_i2040_4_);
/* 59 */       StructureNetherBridgePieces.Start var5 = new StructureNetherBridgePieces.Start(p_i2040_2_, (p_i2040_3_ << 4) + 2, (p_i2040_4_ << 4) + 2);
/* 60 */       this.components.add(var5);
/* 61 */       var5.buildComponent(var5, this.components, p_i2040_2_);
/* 62 */       List<StructureComponent> var6 = var5.field_74967_d;
/*    */       
/* 64 */       while (!var6.isEmpty()) {
/*    */         
/* 66 */         int var7 = p_i2040_2_.nextInt(var6.size());
/* 67 */         StructureComponent var8 = var6.remove(var7);
/* 68 */         var8.buildComponent(var5, this.components, p_i2040_2_);
/*    */       } 
/*    */       
/* 71 */       updateBoundingBox();
/* 72 */       setRandomHeight(worldIn, p_i2040_2_, 48, 70);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenNetherBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */