/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockJukebox;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class TileEntity
/*     */ {
/*  20 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private static Map nameToClassMap = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private static Map classToNameMap = Maps.newHashMap();
/*     */   
/*     */   protected World worldObj;
/*     */   
/*     */   protected BlockPos pos;
/*     */   
/*     */   protected boolean tileEntityInvalid;
/*     */   
/*     */   private int blockMetadata;
/*     */   
/*     */   protected Block blockType;
/*     */   private static final String __OBFID = "CL_00000340";
/*     */   
/*     */   public TileEntity() {
/*  44 */     this.pos = BlockPos.ORIGIN;
/*  45 */     this.blockMetadata = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class<?> cl, String id) {
/*  53 */     if (nameToClassMap.containsKey(id))
/*     */     {
/*  55 */       throw new IllegalArgumentException("Duplicate id: " + id);
/*     */     }
/*     */ 
/*     */     
/*  59 */     nameToClassMap.put(id, cl);
/*  60 */     classToNameMap.put(cl, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  69 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldObj(World worldIn) {
/*  77 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasWorldObj() {
/*  85 */     return (this.worldObj != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  90 */     this.pos = new BlockPos(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  95 */     String var2 = (String)classToNameMap.get(getClass());
/*     */     
/*  97 */     if (var2 == null)
/*     */     {
/*  99 */       throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
/*     */     }
/*     */ 
/*     */     
/* 103 */     compound.setString("id", var2);
/* 104 */     compound.setInteger("x", this.pos.getX());
/* 105 */     compound.setInteger("y", this.pos.getY());
/* 106 */     compound.setInteger("z", this.pos.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TileEntity createAndLoadEntity(NBTTagCompound nbt) {
/* 115 */     TileEntity var1 = null;
/*     */ 
/*     */     
/*     */     try {
/* 119 */       Class<TileEntity> var2 = (Class)nameToClassMap.get(nbt.getString("id"));
/*     */       
/* 121 */       if (var2 != null)
/*     */       {
/* 123 */         var1 = var2.newInstance();
/*     */       }
/*     */     }
/* 126 */     catch (Exception var3) {
/*     */       
/* 128 */       var3.printStackTrace();
/*     */     } 
/*     */     
/* 131 */     if (var1 != null) {
/*     */       
/* 133 */       var1.readFromNBT(nbt);
/*     */     }
/*     */     else {
/*     */       
/* 137 */       logger.warn("Skipping BlockEntity with id " + nbt.getString("id"));
/*     */     } 
/*     */     
/* 140 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockMetadata() {
/* 145 */     if (this.blockMetadata == -1) {
/*     */       
/* 147 */       IBlockState var1 = this.worldObj.getBlockState(this.pos);
/* 148 */       this.blockMetadata = var1.getBlock().getMetaFromState(var1);
/*     */     } 
/*     */     
/* 151 */     return this.blockMetadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 160 */     if (this.worldObj != null) {
/*     */       
/* 162 */       IBlockState var1 = this.worldObj.getBlockState(this.pos);
/* 163 */       this.blockMetadata = var1.getBlock().getMetaFromState(var1);
/* 164 */       this.worldObj.func_175646_b(this.pos, this);
/*     */       
/* 166 */       if (getBlockType() != Blocks.air)
/*     */       {
/* 168 */         this.worldObj.updateComparatorOutputLevel(this.pos, getBlockType());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceSq(double p_145835_1_, double p_145835_3_, double p_145835_5_) {
/* 178 */     double var7 = this.pos.getX() + 0.5D - p_145835_1_;
/* 179 */     double var9 = this.pos.getY() + 0.5D - p_145835_3_;
/* 180 */     double var11 = this.pos.getZ() + 0.5D - p_145835_5_;
/* 181 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxRenderDistanceSquared() {
/* 186 */     return 4096.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPos() {
/* 191 */     return this.pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBlockType() {
/* 199 */     if (this.blockType == null)
/*     */     {
/* 201 */       this.blockType = this.worldObj.getBlockState(this.pos).getBlock();
/*     */     }
/*     */     
/* 204 */     return this.blockType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/* 212 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvalid() {
/* 217 */     return this.tileEntityInvalid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 225 */     this.tileEntityInvalid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate() {
/* 233 */     this.tileEntityInvalid = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int id, int type) {
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateContainingBlockInfo() {
/* 243 */     this.blockType = null;
/* 244 */     this.blockMetadata = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInfoToCrashReport(CrashReportCategory reportCategory) {
/* 249 */     reportCategory.addCrashSectionCallable("Name", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000341";
/*     */           
/*     */           public String call() {
/* 254 */             return String.valueOf(TileEntity.classToNameMap.get(TileEntity.this.getClass())) + " // " + TileEntity.this.getClass().getCanonicalName();
/*     */           }
/*     */         });
/*     */     
/* 258 */     if (this.worldObj != null) {
/*     */       
/* 260 */       CrashReportCategory.addBlockInfo(reportCategory, this.pos, getBlockType(), getBlockMetadata());
/* 261 */       reportCategory.addCrashSectionCallable("Actual block type", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000343";
/*     */             
/*     */             public String call() {
/* 266 */               int var1 = Block.getIdFromBlock(TileEntity.this.worldObj.getBlockState(TileEntity.this.pos).getBlock());
/*     */ 
/*     */               
/*     */               try {
/* 270 */                 return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(var1), Block.getBlockById(var1).getUnlocalizedName(), Block.getBlockById(var1).getClass().getCanonicalName() });
/*     */               }
/* 272 */               catch (Throwable var3) {
/*     */                 
/* 274 */                 return "ID #" + var1;
/*     */               } 
/*     */             }
/*     */           });
/* 278 */       reportCategory.addCrashSectionCallable("Actual block data value", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000344";
/*     */             
/*     */             public String call() {
/* 283 */               IBlockState var1 = TileEntity.this.worldObj.getBlockState(TileEntity.this.pos);
/* 284 */               int var2 = var1.getBlock().getMetaFromState(var1);
/*     */               
/* 286 */               if (var2 < 0)
/*     */               {
/* 288 */                 return "Unknown? (Got " + var2 + ")";
/*     */               }
/*     */ 
/*     */               
/* 292 */               String var3 = String.format("%4s", new Object[] { Integer.toBinaryString(var2) }).replace(" ", "0");
/* 293 */               return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(var2), var3 });
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPos(BlockPos posIn) {
/* 302 */     this.pos = posIn;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 307 */     addMapping(TileEntityFurnace.class, "Furnace");
/* 308 */     addMapping(TileEntityChest.class, "Chest");
/* 309 */     addMapping(TileEntityEnderChest.class, "EnderChest");
/* 310 */     addMapping(BlockJukebox.TileEntityJukebox.class, "RecordPlayer");
/* 311 */     addMapping(TileEntityDispenser.class, "Trap");
/* 312 */     addMapping(TileEntityDropper.class, "Dropper");
/* 313 */     addMapping(TileEntitySign.class, "Sign");
/* 314 */     addMapping(TileEntityMobSpawner.class, "MobSpawner");
/* 315 */     addMapping(TileEntityNote.class, "Music");
/* 316 */     addMapping(TileEntityPiston.class, "Piston");
/* 317 */     addMapping(TileEntityBrewingStand.class, "Cauldron");
/* 318 */     addMapping(TileEntityEnchantmentTable.class, "EnchantTable");
/* 319 */     addMapping(TileEntityEndPortal.class, "Airportal");
/* 320 */     addMapping(TileEntityCommandBlock.class, "Control");
/* 321 */     addMapping(TileEntityBeacon.class, "Beacon");
/* 322 */     addMapping(TileEntitySkull.class, "Skull");
/* 323 */     addMapping(TileEntityDaylightDetector.class, "DLDetector");
/* 324 */     addMapping(TileEntityHopper.class, "Hopper");
/* 325 */     addMapping(TileEntityComparator.class, "Comparator");
/* 326 */     addMapping(TileEntityFlowerPot.class, "FlowerPot");
/* 327 */     addMapping(TileEntityBanner.class, "Banner");
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */