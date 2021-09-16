/*      */ package net.minecraft.world.gen.structure;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.BlockPrismarine;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.monster.EntityGuardian;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.Vec3i;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class StructureOceanMonumentPieces
/*      */ {
/*      */   private static final String __OBFID = "CL_00001994";
/*      */   
/*      */   public static void func_175970_a() {
/*   25 */     MapGenStructureIO.registerStructureComponent(MonumentBuilding.class, "OMB");
/*   26 */     MapGenStructureIO.registerStructureComponent(MonumentCoreRoom.class, "OMCR");
/*   27 */     MapGenStructureIO.registerStructureComponent(DoubleXRoom.class, "OMDXR");
/*   28 */     MapGenStructureIO.registerStructureComponent(DoubleXYRoom.class, "OMDXYR");
/*   29 */     MapGenStructureIO.registerStructureComponent(DoubleYRoom.class, "OMDYR");
/*   30 */     MapGenStructureIO.registerStructureComponent(DoubleYZRoom.class, "OMDYZR");
/*   31 */     MapGenStructureIO.registerStructureComponent(DoubleZRoom.class, "OMDZR");
/*   32 */     MapGenStructureIO.registerStructureComponent(EntryRoom.class, "OMEntry");
/*   33 */     MapGenStructureIO.registerStructureComponent(Penthouse.class, "OMPenthouse");
/*   34 */     MapGenStructureIO.registerStructureComponent(SimpleRoom.class, "OMSimple");
/*   35 */     MapGenStructureIO.registerStructureComponent(SimpleTopRoom.class, "OMSimpleT");
/*      */   }
/*      */   
/*      */   public static class DoubleXRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001983";
/*      */     
/*      */     public DoubleXRoom() {}
/*      */     
/*      */     public DoubleXRoom(EnumFacing p_i45597_1_, StructureOceanMonumentPieces.RoomDefinition p_i45597_2_, Random p_i45597_3_) {
/*   46 */       super(1, p_i45597_1_, p_i45597_2_, 2, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*   51 */       StructureOceanMonumentPieces.RoomDefinition var4 = this.field_175830_k.field_175965_b[EnumFacing.EAST.getIndex()];
/*   52 */       StructureOceanMonumentPieces.RoomDefinition var5 = this.field_175830_k;
/*      */       
/*   54 */       if (this.field_175830_k.field_175967_a / 25 > 0) {
/*      */         
/*   56 */         func_175821_a(worldIn, p_74875_3_, 8, 0, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*   57 */         func_175821_a(worldIn, p_74875_3_, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       } 
/*      */       
/*   60 */       if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*   62 */         func_175819_a(worldIn, p_74875_3_, 1, 4, 1, 7, 4, 6, field_175828_a);
/*      */       }
/*      */       
/*   65 */       if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*   67 */         func_175819_a(worldIn, p_74875_3_, 8, 4, 1, 14, 4, 6, field_175828_a);
/*      */       }
/*      */       
/*   70 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
/*   71 */       func_175804_a(worldIn, p_74875_3_, 15, 3, 0, 15, 3, 7, field_175826_b, field_175826_b, false);
/*   72 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 15, 3, 0, field_175826_b, field_175826_b, false);
/*   73 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 7, 14, 3, 7, field_175826_b, field_175826_b, false);
/*   74 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175828_a, field_175828_a, false);
/*   75 */       func_175804_a(worldIn, p_74875_3_, 15, 2, 0, 15, 2, 7, field_175828_a, field_175828_a, false);
/*   76 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 15, 2, 0, field_175828_a, field_175828_a, false);
/*   77 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 7, 14, 2, 7, field_175828_a, field_175828_a, false);
/*   78 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
/*   79 */       func_175804_a(worldIn, p_74875_3_, 15, 1, 0, 15, 1, 7, field_175826_b, field_175826_b, false);
/*   80 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 15, 1, 0, field_175826_b, field_175826_b, false);
/*   81 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 14, 1, 7, field_175826_b, field_175826_b, false);
/*   82 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 0, 10, 1, 4, field_175826_b, field_175826_b, false);
/*   83 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 9, 2, 3, field_175828_a, field_175828_a, false);
/*   84 */       func_175804_a(worldIn, p_74875_3_, 5, 3, 0, 10, 3, 4, field_175826_b, field_175826_b, false);
/*   85 */       func_175811_a(worldIn, field_175825_e, 6, 2, 3, p_74875_3_);
/*   86 */       func_175811_a(worldIn, field_175825_e, 9, 2, 3, p_74875_3_);
/*      */       
/*   88 */       if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*   90 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*   93 */       if (var5.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*   95 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*   98 */       if (var5.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  100 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  103 */       if (var4.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  105 */         func_175804_a(worldIn, p_74875_3_, 11, 1, 0, 12, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  108 */       if (var4.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  110 */         func_175804_a(worldIn, p_74875_3_, 11, 1, 7, 12, 2, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  113 */       if (var4.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  115 */         func_175804_a(worldIn, p_74875_3_, 15, 1, 3, 15, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  118 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DoubleXYRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001982";
/*      */     
/*      */     public DoubleXYRoom() {}
/*      */     
/*      */     public DoubleXYRoom(EnumFacing p_i45596_1_, StructureOceanMonumentPieces.RoomDefinition p_i45596_2_, Random p_i45596_3_) {
/*  130 */       super(1, p_i45596_1_, p_i45596_2_, 2, 2, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  135 */       StructureOceanMonumentPieces.RoomDefinition var4 = this.field_175830_k.field_175965_b[EnumFacing.EAST.getIndex()];
/*  136 */       StructureOceanMonumentPieces.RoomDefinition var5 = this.field_175830_k;
/*  137 */       StructureOceanMonumentPieces.RoomDefinition var6 = var5.field_175965_b[EnumFacing.UP.getIndex()];
/*  138 */       StructureOceanMonumentPieces.RoomDefinition var7 = var4.field_175965_b[EnumFacing.UP.getIndex()];
/*      */       
/*  140 */       if (this.field_175830_k.field_175967_a / 25 > 0) {
/*      */         
/*  142 */         func_175821_a(worldIn, p_74875_3_, 8, 0, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*  143 */         func_175821_a(worldIn, p_74875_3_, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       } 
/*      */       
/*  146 */       if (var6.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  148 */         func_175819_a(worldIn, p_74875_3_, 1, 8, 1, 7, 8, 6, field_175828_a);
/*      */       }
/*      */       
/*  151 */       if (var7.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  153 */         func_175819_a(worldIn, p_74875_3_, 8, 8, 1, 14, 8, 6, field_175828_a);
/*      */       }
/*      */       
/*  156 */       for (int var8 = 1; var8 <= 7; var8++) {
/*      */         
/*  158 */         IBlockState var9 = field_175826_b;
/*      */         
/*  160 */         if (var8 == 2 || var8 == 6)
/*      */         {
/*  162 */           var9 = field_175828_a;
/*      */         }
/*      */         
/*  165 */         func_175804_a(worldIn, p_74875_3_, 0, var8, 0, 0, var8, 7, var9, var9, false);
/*  166 */         func_175804_a(worldIn, p_74875_3_, 15, var8, 0, 15, var8, 7, var9, var9, false);
/*  167 */         func_175804_a(worldIn, p_74875_3_, 1, var8, 0, 15, var8, 0, var9, var9, false);
/*  168 */         func_175804_a(worldIn, p_74875_3_, 1, var8, 7, 14, var8, 7, var9, var9, false);
/*      */       } 
/*      */       
/*  171 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 3, 2, 7, 4, field_175826_b, field_175826_b, false);
/*  172 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 4, 7, 2, field_175826_b, field_175826_b, false);
/*  173 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 5, 4, 7, 5, field_175826_b, field_175826_b, false);
/*  174 */       func_175804_a(worldIn, p_74875_3_, 13, 1, 3, 13, 7, 4, field_175826_b, field_175826_b, false);
/*  175 */       func_175804_a(worldIn, p_74875_3_, 11, 1, 2, 12, 7, 2, field_175826_b, field_175826_b, false);
/*  176 */       func_175804_a(worldIn, p_74875_3_, 11, 1, 5, 12, 7, 5, field_175826_b, field_175826_b, false);
/*  177 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 3, 5, 3, 4, field_175826_b, field_175826_b, false);
/*  178 */       func_175804_a(worldIn, p_74875_3_, 10, 1, 3, 10, 3, 4, field_175826_b, field_175826_b, false);
/*  179 */       func_175804_a(worldIn, p_74875_3_, 5, 7, 2, 10, 7, 5, field_175826_b, field_175826_b, false);
/*  180 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 2, 5, 7, 2, field_175826_b, field_175826_b, false);
/*  181 */       func_175804_a(worldIn, p_74875_3_, 10, 5, 2, 10, 7, 2, field_175826_b, field_175826_b, false);
/*  182 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 5, 5, 7, 5, field_175826_b, field_175826_b, false);
/*  183 */       func_175804_a(worldIn, p_74875_3_, 10, 5, 5, 10, 7, 5, field_175826_b, field_175826_b, false);
/*  184 */       func_175811_a(worldIn, field_175826_b, 6, 6, 2, p_74875_3_);
/*  185 */       func_175811_a(worldIn, field_175826_b, 9, 6, 2, p_74875_3_);
/*  186 */       func_175811_a(worldIn, field_175826_b, 6, 6, 5, p_74875_3_);
/*  187 */       func_175811_a(worldIn, field_175826_b, 9, 6, 5, p_74875_3_);
/*  188 */       func_175804_a(worldIn, p_74875_3_, 5, 4, 3, 6, 4, 4, field_175826_b, field_175826_b, false);
/*  189 */       func_175804_a(worldIn, p_74875_3_, 9, 4, 3, 10, 4, 4, field_175826_b, field_175826_b, false);
/*  190 */       func_175811_a(worldIn, field_175825_e, 5, 4, 2, p_74875_3_);
/*  191 */       func_175811_a(worldIn, field_175825_e, 5, 4, 5, p_74875_3_);
/*  192 */       func_175811_a(worldIn, field_175825_e, 10, 4, 2, p_74875_3_);
/*  193 */       func_175811_a(worldIn, field_175825_e, 10, 4, 5, p_74875_3_);
/*      */       
/*  195 */       if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  197 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  200 */       if (var5.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  202 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  205 */       if (var5.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  207 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  210 */       if (var4.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  212 */         func_175804_a(worldIn, p_74875_3_, 11, 1, 0, 12, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  215 */       if (var4.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  217 */         func_175804_a(worldIn, p_74875_3_, 11, 1, 7, 12, 2, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  220 */       if (var4.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  222 */         func_175804_a(worldIn, p_74875_3_, 15, 1, 3, 15, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  225 */       if (var6.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  227 */         func_175804_a(worldIn, p_74875_3_, 3, 5, 0, 4, 6, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  230 */       if (var6.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  232 */         func_175804_a(worldIn, p_74875_3_, 3, 5, 7, 4, 6, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  235 */       if (var6.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  237 */         func_175804_a(worldIn, p_74875_3_, 0, 5, 3, 0, 6, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  240 */       if (var7.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  242 */         func_175804_a(worldIn, p_74875_3_, 11, 5, 0, 12, 6, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  245 */       if (var7.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  247 */         func_175804_a(worldIn, p_74875_3_, 11, 5, 7, 12, 6, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  250 */       if (var7.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  252 */         func_175804_a(worldIn, p_74875_3_, 15, 5, 3, 15, 6, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  255 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DoubleYRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001981";
/*      */     
/*      */     public DoubleYRoom() {}
/*      */     
/*      */     public DoubleYRoom(EnumFacing p_i45595_1_, StructureOceanMonumentPieces.RoomDefinition p_i45595_2_, Random p_i45595_3_) {
/*  267 */       super(1, p_i45595_1_, p_i45595_2_, 1, 2, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  272 */       if (this.field_175830_k.field_175967_a / 25 > 0)
/*      */       {
/*  274 */         func_175821_a(worldIn, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       }
/*      */       
/*  277 */       StructureOceanMonumentPieces.RoomDefinition var4 = this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()];
/*      */       
/*  279 */       if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  281 */         func_175819_a(worldIn, p_74875_3_, 1, 8, 1, 6, 8, 6, field_175828_a);
/*      */       }
/*      */       
/*  284 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 0, 4, 7, field_175826_b, field_175826_b, false);
/*  285 */       func_175804_a(worldIn, p_74875_3_, 7, 4, 0, 7, 4, 7, field_175826_b, field_175826_b, false);
/*  286 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 0, 6, 4, 0, field_175826_b, field_175826_b, false);
/*  287 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 7, 6, 4, 7, field_175826_b, field_175826_b, false);
/*  288 */       func_175804_a(worldIn, p_74875_3_, 2, 4, 1, 2, 4, 2, field_175826_b, field_175826_b, false);
/*  289 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 2, 1, 4, 2, field_175826_b, field_175826_b, false);
/*  290 */       func_175804_a(worldIn, p_74875_3_, 5, 4, 1, 5, 4, 2, field_175826_b, field_175826_b, false);
/*  291 */       func_175804_a(worldIn, p_74875_3_, 6, 4, 2, 6, 4, 2, field_175826_b, field_175826_b, false);
/*  292 */       func_175804_a(worldIn, p_74875_3_, 2, 4, 5, 2, 4, 6, field_175826_b, field_175826_b, false);
/*  293 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 5, 1, 4, 5, field_175826_b, field_175826_b, false);
/*  294 */       func_175804_a(worldIn, p_74875_3_, 5, 4, 5, 5, 4, 6, field_175826_b, field_175826_b, false);
/*  295 */       func_175804_a(worldIn, p_74875_3_, 6, 4, 5, 6, 4, 5, field_175826_b, field_175826_b, false);
/*  296 */       StructureOceanMonumentPieces.RoomDefinition var5 = this.field_175830_k;
/*      */       
/*  298 */       for (int var6 = 1; var6 <= 5; var6 += 4) {
/*      */         
/*  300 */         byte var7 = 0;
/*      */         
/*  302 */         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
/*      */           
/*  304 */           func_175804_a(worldIn, p_74875_3_, 2, var6, var7, 2, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  305 */           func_175804_a(worldIn, p_74875_3_, 5, var6, var7, 5, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  306 */           func_175804_a(worldIn, p_74875_3_, 3, var6 + 2, var7, 4, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/*  310 */           func_175804_a(worldIn, p_74875_3_, 0, var6, var7, 7, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  311 */           func_175804_a(worldIn, p_74875_3_, 0, var6 + 1, var7, 7, var6 + 1, var7, field_175828_a, field_175828_a, false);
/*      */         } 
/*      */         
/*  314 */         var7 = 7;
/*      */         
/*  316 */         if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
/*      */           
/*  318 */           func_175804_a(worldIn, p_74875_3_, 2, var6, var7, 2, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  319 */           func_175804_a(worldIn, p_74875_3_, 5, var6, var7, 5, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  320 */           func_175804_a(worldIn, p_74875_3_, 3, var6 + 2, var7, 4, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/*  324 */           func_175804_a(worldIn, p_74875_3_, 0, var6, var7, 7, var6 + 2, var7, field_175826_b, field_175826_b, false);
/*  325 */           func_175804_a(worldIn, p_74875_3_, 0, var6 + 1, var7, 7, var6 + 1, var7, field_175828_a, field_175828_a, false);
/*      */         } 
/*      */         
/*  328 */         byte var8 = 0;
/*      */         
/*  330 */         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
/*      */           
/*  332 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 2, var8, var6 + 2, 2, field_175826_b, field_175826_b, false);
/*  333 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 5, var8, var6 + 2, 5, field_175826_b, field_175826_b, false);
/*  334 */           func_175804_a(worldIn, p_74875_3_, var8, var6 + 2, 3, var8, var6 + 2, 4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/*  338 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 0, var8, var6 + 2, 7, field_175826_b, field_175826_b, false);
/*  339 */           func_175804_a(worldIn, p_74875_3_, var8, var6 + 1, 0, var8, var6 + 1, 7, field_175828_a, field_175828_a, false);
/*      */         } 
/*      */         
/*  342 */         var8 = 7;
/*      */         
/*  344 */         if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
/*      */           
/*  346 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 2, var8, var6 + 2, 2, field_175826_b, field_175826_b, false);
/*  347 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 5, var8, var6 + 2, 5, field_175826_b, field_175826_b, false);
/*  348 */           func_175804_a(worldIn, p_74875_3_, var8, var6 + 2, 3, var8, var6 + 2, 4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/*  352 */           func_175804_a(worldIn, p_74875_3_, var8, var6, 0, var8, var6 + 2, 7, field_175826_b, field_175826_b, false);
/*  353 */           func_175804_a(worldIn, p_74875_3_, var8, var6 + 1, 0, var8, var6 + 1, 7, field_175828_a, field_175828_a, false);
/*      */         } 
/*      */         
/*  356 */         var5 = var4;
/*      */       } 
/*      */       
/*  359 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DoubleYZRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001980";
/*      */     
/*      */     public DoubleYZRoom() {}
/*      */     
/*      */     public DoubleYZRoom(EnumFacing p_i45594_1_, StructureOceanMonumentPieces.RoomDefinition p_i45594_2_, Random p_i45594_3_) {
/*  371 */       super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  376 */       StructureOceanMonumentPieces.RoomDefinition var4 = this.field_175830_k.field_175965_b[EnumFacing.NORTH.getIndex()];
/*  377 */       StructureOceanMonumentPieces.RoomDefinition var5 = this.field_175830_k;
/*  378 */       StructureOceanMonumentPieces.RoomDefinition var6 = var4.field_175965_b[EnumFacing.UP.getIndex()];
/*  379 */       StructureOceanMonumentPieces.RoomDefinition var7 = var5.field_175965_b[EnumFacing.UP.getIndex()];
/*      */       
/*  381 */       if (this.field_175830_k.field_175967_a / 25 > 0) {
/*      */         
/*  383 */         func_175821_a(worldIn, p_74875_3_, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*  384 */         func_175821_a(worldIn, p_74875_3_, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       } 
/*      */       
/*  387 */       if (var7.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  389 */         func_175819_a(worldIn, p_74875_3_, 1, 8, 1, 6, 8, 7, field_175828_a);
/*      */       }
/*      */       
/*  392 */       if (var6.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  394 */         func_175819_a(worldIn, p_74875_3_, 1, 8, 8, 6, 8, 14, field_175828_a);
/*      */       }
/*      */ 
/*      */       
/*      */       int var8;
/*      */       
/*  400 */       for (var8 = 1; var8 <= 7; var8++) {
/*      */         
/*  402 */         IBlockState var9 = field_175826_b;
/*      */         
/*  404 */         if (var8 == 2 || var8 == 6)
/*      */         {
/*  406 */           var9 = field_175828_a;
/*      */         }
/*      */         
/*  409 */         func_175804_a(worldIn, p_74875_3_, 0, var8, 0, 0, var8, 15, var9, var9, false);
/*  410 */         func_175804_a(worldIn, p_74875_3_, 7, var8, 0, 7, var8, 15, var9, var9, false);
/*  411 */         func_175804_a(worldIn, p_74875_3_, 1, var8, 0, 6, var8, 0, var9, var9, false);
/*  412 */         func_175804_a(worldIn, p_74875_3_, 1, var8, 15, 6, var8, 15, var9, var9, false);
/*      */       } 
/*      */       
/*  415 */       for (var8 = 1; var8 <= 7; var8++) {
/*      */         
/*  417 */         IBlockState var9 = field_175827_c;
/*      */         
/*  419 */         if (var8 == 2 || var8 == 6)
/*      */         {
/*  421 */           var9 = field_175825_e;
/*      */         }
/*      */         
/*  424 */         func_175804_a(worldIn, p_74875_3_, 3, var8, 7, 4, var8, 8, var9, var9, false);
/*      */       } 
/*      */       
/*  427 */       if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  429 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  432 */       if (var5.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  434 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  437 */       if (var5.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  439 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  442 */       if (var4.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  444 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 15, 4, 2, 15, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  447 */       if (var4.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  449 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 11, 0, 2, 12, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  452 */       if (var4.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  454 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 11, 7, 2, 12, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  457 */       if (var7.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  459 */         func_175804_a(worldIn, p_74875_3_, 3, 5, 0, 4, 6, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  462 */       if (var7.field_175966_c[EnumFacing.EAST.getIndex()]) {
/*      */         
/*  464 */         func_175804_a(worldIn, p_74875_3_, 7, 5, 3, 7, 6, 4, field_175822_f, field_175822_f, false);
/*  465 */         func_175804_a(worldIn, p_74875_3_, 5, 4, 2, 6, 4, 5, field_175826_b, field_175826_b, false);
/*  466 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 2, 6, 3, 2, field_175826_b, field_175826_b, false);
/*  467 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 5, 6, 3, 5, field_175826_b, field_175826_b, false);
/*      */       } 
/*      */       
/*  470 */       if (var7.field_175966_c[EnumFacing.WEST.getIndex()]) {
/*      */         
/*  472 */         func_175804_a(worldIn, p_74875_3_, 0, 5, 3, 0, 6, 4, field_175822_f, field_175822_f, false);
/*  473 */         func_175804_a(worldIn, p_74875_3_, 1, 4, 2, 2, 4, 5, field_175826_b, field_175826_b, false);
/*  474 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 2, 1, 3, 2, field_175826_b, field_175826_b, false);
/*  475 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 5, 1, 3, 5, field_175826_b, field_175826_b, false);
/*      */       } 
/*      */       
/*  478 */       if (var6.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  480 */         func_175804_a(worldIn, p_74875_3_, 3, 5, 15, 4, 6, 15, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  483 */       if (var6.field_175966_c[EnumFacing.WEST.getIndex()]) {
/*      */         
/*  485 */         func_175804_a(worldIn, p_74875_3_, 0, 5, 11, 0, 6, 12, field_175822_f, field_175822_f, false);
/*  486 */         func_175804_a(worldIn, p_74875_3_, 1, 4, 10, 2, 4, 13, field_175826_b, field_175826_b, false);
/*  487 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 10, 1, 3, 10, field_175826_b, field_175826_b, false);
/*  488 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 13, 1, 3, 13, field_175826_b, field_175826_b, false);
/*      */       } 
/*      */       
/*  491 */       if (var6.field_175966_c[EnumFacing.EAST.getIndex()]) {
/*      */         
/*  493 */         func_175804_a(worldIn, p_74875_3_, 7, 5, 11, 7, 6, 12, field_175822_f, field_175822_f, false);
/*  494 */         func_175804_a(worldIn, p_74875_3_, 5, 4, 10, 6, 4, 13, field_175826_b, field_175826_b, false);
/*  495 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 10, 6, 3, 10, field_175826_b, field_175826_b, false);
/*  496 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 13, 6, 3, 13, field_175826_b, field_175826_b, false);
/*      */       } 
/*      */       
/*  499 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DoubleZRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001979";
/*      */     
/*      */     public DoubleZRoom() {}
/*      */     
/*      */     public DoubleZRoom(EnumFacing p_i45593_1_, StructureOceanMonumentPieces.RoomDefinition p_i45593_2_, Random p_i45593_3_) {
/*  511 */       super(1, p_i45593_1_, p_i45593_2_, 1, 1, 2);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  516 */       StructureOceanMonumentPieces.RoomDefinition var4 = this.field_175830_k.field_175965_b[EnumFacing.NORTH.getIndex()];
/*  517 */       StructureOceanMonumentPieces.RoomDefinition var5 = this.field_175830_k;
/*      */       
/*  519 */       if (this.field_175830_k.field_175967_a / 25 > 0) {
/*      */         
/*  521 */         func_175821_a(worldIn, p_74875_3_, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*  522 */         func_175821_a(worldIn, p_74875_3_, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       } 
/*      */       
/*  525 */       if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  527 */         func_175819_a(worldIn, p_74875_3_, 1, 4, 1, 6, 4, 7, field_175828_a);
/*      */       }
/*      */       
/*  530 */       if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/*  532 */         func_175819_a(worldIn, p_74875_3_, 1, 4, 8, 6, 4, 14, field_175828_a);
/*      */       }
/*      */       
/*  535 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 0, 3, 15, field_175826_b, field_175826_b, false);
/*  536 */       func_175804_a(worldIn, p_74875_3_, 7, 3, 0, 7, 3, 15, field_175826_b, field_175826_b, false);
/*  537 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 7, 3, 0, field_175826_b, field_175826_b, false);
/*  538 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 15, 6, 3, 15, field_175826_b, field_175826_b, false);
/*  539 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 15, field_175828_a, field_175828_a, false);
/*  540 */       func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 7, 2, 15, field_175828_a, field_175828_a, false);
/*  541 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 7, 2, 0, field_175828_a, field_175828_a, false);
/*  542 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 15, 6, 2, 15, field_175828_a, field_175828_a, false);
/*  543 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 15, field_175826_b, field_175826_b, false);
/*  544 */       func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 1, 15, field_175826_b, field_175826_b, false);
/*  545 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 7, 1, 0, field_175826_b, field_175826_b, false);
/*  546 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 15, 6, 1, 15, field_175826_b, field_175826_b, false);
/*  547 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 1, 1, 2, field_175826_b, field_175826_b, false);
/*  548 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 1, 6, 1, 2, field_175826_b, field_175826_b, false);
/*  549 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 1, 1, 3, 2, field_175826_b, field_175826_b, false);
/*  550 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 1, 6, 3, 2, field_175826_b, field_175826_b, false);
/*  551 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 13, 1, 1, 14, field_175826_b, field_175826_b, false);
/*  552 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 13, 6, 1, 14, field_175826_b, field_175826_b, false);
/*  553 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 13, 1, 3, 14, field_175826_b, field_175826_b, false);
/*  554 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 13, 6, 3, 14, field_175826_b, field_175826_b, false);
/*  555 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 6, 2, 3, 6, field_175826_b, field_175826_b, false);
/*  556 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 6, 5, 3, 6, field_175826_b, field_175826_b, false);
/*  557 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 9, 2, 3, 9, field_175826_b, field_175826_b, false);
/*  558 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 5, 3, 9, field_175826_b, field_175826_b, false);
/*  559 */       func_175804_a(worldIn, p_74875_3_, 3, 2, 6, 4, 2, 6, field_175826_b, field_175826_b, false);
/*  560 */       func_175804_a(worldIn, p_74875_3_, 3, 2, 9, 4, 2, 9, field_175826_b, field_175826_b, false);
/*  561 */       func_175804_a(worldIn, p_74875_3_, 2, 2, 7, 2, 2, 8, field_175826_b, field_175826_b, false);
/*  562 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 7, 5, 2, 8, field_175826_b, field_175826_b, false);
/*  563 */       func_175811_a(worldIn, field_175825_e, 2, 2, 5, p_74875_3_);
/*  564 */       func_175811_a(worldIn, field_175825_e, 5, 2, 5, p_74875_3_);
/*  565 */       func_175811_a(worldIn, field_175825_e, 2, 2, 10, p_74875_3_);
/*  566 */       func_175811_a(worldIn, field_175825_e, 5, 2, 10, p_74875_3_);
/*  567 */       func_175811_a(worldIn, field_175826_b, 2, 3, 5, p_74875_3_);
/*  568 */       func_175811_a(worldIn, field_175826_b, 5, 3, 5, p_74875_3_);
/*  569 */       func_175811_a(worldIn, field_175826_b, 2, 3, 10, p_74875_3_);
/*  570 */       func_175811_a(worldIn, field_175826_b, 5, 3, 10, p_74875_3_);
/*      */       
/*  572 */       if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/*  574 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  577 */       if (var5.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  579 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  582 */       if (var5.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  584 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  587 */       if (var4.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  589 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 15, 4, 2, 15, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  592 */       if (var4.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  594 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 11, 0, 2, 12, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  597 */       if (var4.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  599 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 11, 7, 2, 12, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  602 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class EntryRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001978";
/*      */     
/*      */     public EntryRoom() {}
/*      */     
/*      */     public EntryRoom(EnumFacing p_i45592_1_, StructureOceanMonumentPieces.RoomDefinition p_i45592_2_) {
/*  614 */       super(1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  619 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 2, 3, 7, field_175826_b, field_175826_b, false);
/*  620 */       func_175804_a(worldIn, p_74875_3_, 5, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
/*  621 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 1, 2, 7, field_175826_b, field_175826_b, false);
/*  622 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 7, 2, 7, field_175826_b, field_175826_b, false);
/*  623 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
/*  624 */       func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
/*  625 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 7, 7, 3, 7, field_175826_b, field_175826_b, false);
/*  626 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 2, 3, 0, field_175826_b, field_175826_b, false);
/*  627 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
/*      */       
/*  629 */       if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */       {
/*  631 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  634 */       if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */       {
/*  636 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 1, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  639 */       if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */       {
/*  641 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 3, 7, 2, 4, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/*  644 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static class FitSimpleRoomHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001987";
/*      */     
/*      */     private FitSimpleRoomHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/*  656 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/*  661 */       p_175968_2_.field_175963_d = true;
/*  662 */       return new StructureOceanMonumentPieces.SimpleRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     FitSimpleRoomHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45601_1_) {
/*  667 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class FitSimpleRoomTopHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001986";
/*      */     
/*      */     private FitSimpleRoomTopHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/*  679 */       return (!p_175969_1_.field_175966_c[EnumFacing.WEST.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.SOUTH.getIndex()] && !p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()]);
/*      */     }
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/*  684 */       p_175968_2_.field_175963_d = true;
/*  685 */       return new StructureOceanMonumentPieces.SimpleTopRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     FitSimpleRoomTopHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45600_1_) {
/*  690 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class MonumentBuilding
/*      */     extends Piece {
/*      */     private StructureOceanMonumentPieces.RoomDefinition field_175845_o;
/*      */     private StructureOceanMonumentPieces.RoomDefinition field_175844_p;
/*  698 */     private List field_175843_q = Lists.newArrayList();
/*      */     
/*      */     private static final String __OBFID = "CL_00001985";
/*      */     
/*      */     public MonumentBuilding() {}
/*      */     
/*      */     public MonumentBuilding(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, EnumFacing p_i45599_4_) {
/*  705 */       super(0);
/*  706 */       this.coordBaseMode = p_i45599_4_;
/*      */       
/*  708 */       switch (StructureOceanMonumentPieces.SwitchEnumFacing.field_175971_a[this.coordBaseMode.ordinal()]) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/*  712 */           this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
/*      */           break;
/*      */         
/*      */         default:
/*  716 */           this.boundingBox = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
/*      */           break;
/*      */       } 
/*  719 */       List var5 = func_175836_a(p_i45599_1_);
/*  720 */       this.field_175845_o.field_175963_d = true;
/*  721 */       this.field_175843_q.add(new StructureOceanMonumentPieces.EntryRoom(this.coordBaseMode, this.field_175845_o));
/*  722 */       this.field_175843_q.add(new StructureOceanMonumentPieces.MonumentCoreRoom(this.coordBaseMode, this.field_175844_p, p_i45599_1_));
/*  723 */       ArrayList<StructureOceanMonumentPieces.XYDoubleRoomFitHelper> var6 = Lists.newArrayList();
/*  724 */       var6.add(new StructureOceanMonumentPieces.XYDoubleRoomFitHelper(null));
/*  725 */       var6.add(new StructureOceanMonumentPieces.YZDoubleRoomFitHelper(null));
/*  726 */       var6.add(new StructureOceanMonumentPieces.ZDoubleRoomFitHelper(null));
/*  727 */       var6.add(new StructureOceanMonumentPieces.XDoubleRoomFitHelper(null));
/*  728 */       var6.add(new StructureOceanMonumentPieces.YDoubleRoomFitHelper(null));
/*  729 */       var6.add(new StructureOceanMonumentPieces.FitSimpleRoomTopHelper(null));
/*  730 */       var6.add(new StructureOceanMonumentPieces.FitSimpleRoomHelper(null));
/*  731 */       Iterator<StructureOceanMonumentPieces.RoomDefinition> var7 = var5.iterator();
/*      */       
/*  733 */       while (var7.hasNext()) {
/*      */         
/*  735 */         StructureOceanMonumentPieces.RoomDefinition var8 = var7.next();
/*      */         
/*  737 */         if (!var8.field_175963_d && !var8.func_175961_b()) {
/*      */           
/*  739 */           Iterator<StructureOceanMonumentPieces.XYDoubleRoomFitHelper> var9 = var6.iterator();
/*      */           
/*  741 */           while (var9.hasNext()) {
/*      */             
/*  743 */             StructureOceanMonumentPieces.MonumentRoomFitHelper var10 = var9.next();
/*      */             
/*  745 */             if (var10.func_175969_a(var8)) {
/*      */               
/*  747 */               this.field_175843_q.add(var10.func_175968_a(this.coordBaseMode, var8, p_i45599_1_));
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  754 */       int var14 = this.boundingBox.minY;
/*  755 */       int var15 = getXWithOffset(9, 22);
/*  756 */       int var16 = getZWithOffset(9, 22);
/*  757 */       Iterator<StructureOceanMonumentPieces.Piece> var17 = this.field_175843_q.iterator();
/*      */       
/*  759 */       while (var17.hasNext()) {
/*      */         
/*  761 */         StructureOceanMonumentPieces.Piece var11 = var17.next();
/*  762 */         var11.getBoundingBox().offset(var15, var14, var16);
/*      */       } 
/*      */       
/*  765 */       StructureBoundingBox var18 = StructureBoundingBox.func_175899_a(getXWithOffset(1, 1), getYWithOffset(1), getZWithOffset(1, 1), getXWithOffset(23, 21), getYWithOffset(8), getZWithOffset(23, 21));
/*  766 */       StructureBoundingBox var19 = StructureBoundingBox.func_175899_a(getXWithOffset(34, 1), getYWithOffset(1), getZWithOffset(34, 1), getXWithOffset(56, 21), getYWithOffset(8), getZWithOffset(56, 21));
/*  767 */       StructureBoundingBox var12 = StructureBoundingBox.func_175899_a(getXWithOffset(22, 22), getYWithOffset(13), getZWithOffset(22, 22), getXWithOffset(35, 35), getYWithOffset(17), getZWithOffset(35, 35));
/*  768 */       int var13 = p_i45599_1_.nextInt();
/*  769 */       this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.coordBaseMode, var18, var13++));
/*  770 */       this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.coordBaseMode, var19, var13++));
/*  771 */       this.field_175843_q.add(new StructureOceanMonumentPieces.Penthouse(this.coordBaseMode, var12));
/*      */     }
/*      */ 
/*      */     
/*      */     private List func_175836_a(Random p_175836_1_) {
/*  776 */       StructureOceanMonumentPieces.RoomDefinition[] var2 = new StructureOceanMonumentPieces.RoomDefinition[75];
/*      */ 
/*      */       
/*      */       int var3;
/*      */ 
/*      */       
/*  782 */       for (var3 = 0; var3 < 5; var3++) {
/*      */         
/*  784 */         for (int var4 = 0; var4 < 4; var4++) {
/*      */           
/*  786 */           byte var5 = 0;
/*  787 */           int var6 = func_175820_a(var3, var5, var4);
/*  788 */           var2[var6] = new StructureOceanMonumentPieces.RoomDefinition(var6);
/*      */         } 
/*      */       } 
/*      */       
/*  792 */       for (var3 = 0; var3 < 5; var3++) {
/*      */         
/*  794 */         for (int var4 = 0; var4 < 4; var4++) {
/*      */           
/*  796 */           byte var5 = 1;
/*  797 */           int var6 = func_175820_a(var3, var5, var4);
/*  798 */           var2[var6] = new StructureOceanMonumentPieces.RoomDefinition(var6);
/*      */         } 
/*      */       } 
/*      */       
/*  802 */       for (var3 = 1; var3 < 4; var3++) {
/*      */         
/*  804 */         for (int var4 = 0; var4 < 2; var4++) {
/*      */           
/*  806 */           byte var5 = 2;
/*  807 */           int var6 = func_175820_a(var3, var5, var4);
/*  808 */           var2[var6] = new StructureOceanMonumentPieces.RoomDefinition(var6);
/*      */         } 
/*      */       } 
/*      */       
/*  812 */       this.field_175845_o = var2[field_175823_g];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  819 */       for (var3 = 0; var3 < 5; var3++) {
/*      */         
/*  821 */         for (int var4 = 0; var4 < 5; var4++) {
/*      */           
/*  823 */           for (int var17 = 0; var17 < 3; var17++) {
/*      */             
/*  825 */             int var6 = func_175820_a(var3, var17, var4);
/*      */             
/*  827 */             if (var2[var6] != null) {
/*      */               
/*  829 */               EnumFacing[] var7 = EnumFacing.values();
/*  830 */               int i = var7.length;
/*      */               
/*  832 */               for (int j = 0; j < i; j++) {
/*      */                 
/*  834 */                 EnumFacing var10 = var7[j];
/*  835 */                 int var11 = var3 + var10.getFrontOffsetX();
/*  836 */                 int var12 = var17 + var10.getFrontOffsetY();
/*  837 */                 int var13 = var4 + var10.getFrontOffsetZ();
/*      */                 
/*  839 */                 if (var11 >= 0 && var11 < 5 && var13 >= 0 && var13 < 5 && var12 >= 0 && var12 < 3) {
/*      */                   
/*  841 */                   int var14 = func_175820_a(var11, var12, var13);
/*      */                   
/*  843 */                   if (var2[var14] != null)
/*      */                   {
/*  845 */                     if (var13 != var4) {
/*      */                       
/*  847 */                       var2[var6].func_175957_a(var10.getOpposite(), var2[var14]);
/*      */                     }
/*      */                     else {
/*      */                       
/*  851 */                       var2[var6].func_175957_a(var10, var2[var14]);
/*      */                     } 
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*      */       StructureOceanMonumentPieces.RoomDefinition var15;
/*      */       
/*  864 */       var2[field_175831_h].func_175957_a(EnumFacing.UP, var15 = new StructureOceanMonumentPieces.RoomDefinition(1003)); StructureOceanMonumentPieces.RoomDefinition var16;
/*  865 */       var2[field_175832_i].func_175957_a(EnumFacing.SOUTH, var16 = new StructureOceanMonumentPieces.RoomDefinition(1001)); StructureOceanMonumentPieces.RoomDefinition var18;
/*  866 */       var2[field_175829_j].func_175957_a(EnumFacing.SOUTH, var18 = new StructureOceanMonumentPieces.RoomDefinition(1002));
/*  867 */       var15.field_175963_d = true;
/*  868 */       var16.field_175963_d = true;
/*  869 */       var18.field_175963_d = true;
/*  870 */       this.field_175845_o.field_175964_e = true;
/*  871 */       this.field_175844_p = var2[func_175820_a(p_175836_1_.nextInt(4), 0, 2)];
/*  872 */       this.field_175844_p.field_175963_d = true;
/*  873 */       (this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()]).field_175963_d = true;
/*  874 */       (this.field_175844_p.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d = true;
/*  875 */       ((this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()]).field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d = true;
/*  876 */       (this.field_175844_p.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/*  877 */       ((this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()]).field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/*  878 */       ((this.field_175844_p.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/*  879 */       (((this.field_175844_p.field_175965_b[EnumFacing.EAST.getIndex()]).field_175965_b[EnumFacing.NORTH.getIndex()]).field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/*  880 */       ArrayList<StructureOceanMonumentPieces.RoomDefinition> var19 = Lists.newArrayList();
/*  881 */       StructureOceanMonumentPieces.RoomDefinition[] var20 = var2;
/*  882 */       int var8 = var2.length;
/*      */       
/*  884 */       for (int var9 = 0; var9 < var8; var9++) {
/*      */         
/*  886 */         StructureOceanMonumentPieces.RoomDefinition var24 = var20[var9];
/*      */         
/*  888 */         if (var24 != null) {
/*      */           
/*  890 */           var24.func_175958_a();
/*  891 */           var19.add(var24);
/*      */         } 
/*      */       } 
/*      */       
/*  895 */       var15.func_175958_a();
/*  896 */       Collections.shuffle(var19, p_175836_1_);
/*  897 */       int var21 = 1;
/*  898 */       Iterator<StructureOceanMonumentPieces.RoomDefinition> var22 = var19.iterator();
/*      */       
/*  900 */       while (var22.hasNext()) {
/*      */         
/*  902 */         StructureOceanMonumentPieces.RoomDefinition var23 = var22.next();
/*  903 */         int var25 = 0;
/*  904 */         int var11 = 0;
/*      */         
/*  906 */         while (var25 < 2 && var11 < 5) {
/*      */           
/*  908 */           var11++;
/*  909 */           int var12 = p_175836_1_.nextInt(6);
/*      */           
/*  911 */           if (var23.field_175966_c[var12]) {
/*      */             
/*  913 */             int var13 = EnumFacing.getFront(var12).getOpposite().getIndex();
/*  914 */             var23.field_175966_c[var12] = false;
/*  915 */             (var23.field_175965_b[var12]).field_175966_c[var13] = false;
/*      */             
/*  917 */             if (var23.func_175959_a(var21++) && var23.field_175965_b[var12].func_175959_a(var21++)) {
/*      */               
/*  919 */               var25++;
/*      */               
/*      */               continue;
/*      */             } 
/*  923 */             var23.field_175966_c[var12] = true;
/*  924 */             (var23.field_175965_b[var12]).field_175966_c[var13] = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  930 */       var19.add(var15);
/*  931 */       var19.add(var16);
/*  932 */       var19.add(var18);
/*  933 */       return var19;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  938 */       func_175840_a(false, 0, worldIn, p_74875_2_, p_74875_3_);
/*  939 */       func_175840_a(true, 33, worldIn, p_74875_2_, p_74875_3_);
/*  940 */       func_175839_b(worldIn, p_74875_2_, p_74875_3_);
/*  941 */       func_175837_c(worldIn, p_74875_2_, p_74875_3_);
/*  942 */       func_175841_d(worldIn, p_74875_2_, p_74875_3_);
/*  943 */       func_175835_e(worldIn, p_74875_2_, p_74875_3_);
/*  944 */       func_175842_f(worldIn, p_74875_2_, p_74875_3_);
/*  945 */       func_175838_g(worldIn, p_74875_2_, p_74875_3_);
/*      */       
/*      */       int var4;
/*  948 */       for (var4 = 0; var4 < 7; var4++) {
/*      */         
/*  950 */         int var5 = 0;
/*      */         
/*  952 */         while (var5 < 7) {
/*      */           
/*  954 */           if (var5 == 0 && var4 == 3)
/*      */           {
/*  956 */             var5 = 6;
/*      */           }
/*      */           
/*  959 */           int var6 = var4 * 9;
/*  960 */           int var7 = var5 * 9;
/*      */           
/*  962 */           for (int var8 = 0; var8 < 4; var8++) {
/*      */             
/*  964 */             for (int var9 = 0; var9 < 4; var9++) {
/*      */               
/*  966 */               func_175811_a(worldIn, field_175826_b, var6 + var8, 0, var7 + var9, p_74875_3_);
/*  967 */               func_175808_b(worldIn, field_175826_b, var6 + var8, -1, var7 + var9, p_74875_3_);
/*      */             } 
/*      */           } 
/*      */           
/*  971 */           if (var4 != 0 && var4 != 6) {
/*      */             
/*  973 */             var5 += 6;
/*      */             
/*      */             continue;
/*      */           } 
/*  977 */           var5++;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  982 */       for (var4 = 0; var4 < 5; var4++) {
/*      */         
/*  984 */         func_175804_a(worldIn, p_74875_3_, -1 - var4, 0 + var4 * 2, -1 - var4, -1 - var4, 23, 58 + var4, field_175822_f, field_175822_f, false);
/*  985 */         func_175804_a(worldIn, p_74875_3_, 58 + var4, 0 + var4 * 2, -1 - var4, 58 + var4, 23, 58 + var4, field_175822_f, field_175822_f, false);
/*  986 */         func_175804_a(worldIn, p_74875_3_, 0 - var4, 0 + var4 * 2, -1 - var4, 57 + var4, 23, -1 - var4, field_175822_f, field_175822_f, false);
/*  987 */         func_175804_a(worldIn, p_74875_3_, 0 - var4, 0 + var4 * 2, 58 + var4, 57 + var4, 23, 58 + var4, field_175822_f, field_175822_f, false);
/*      */       } 
/*      */       
/*  990 */       Iterator<StructureOceanMonumentPieces.Piece> var10 = this.field_175843_q.iterator();
/*      */       
/*  992 */       while (var10.hasNext()) {
/*      */         
/*  994 */         StructureOceanMonumentPieces.Piece var11 = var10.next();
/*      */         
/*  996 */         if (var11.getBoundingBox().intersectsWith(p_74875_3_))
/*      */         {
/*  998 */           var11.addComponentParts(worldIn, p_74875_2_, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1002 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private void func_175840_a(boolean p_175840_1_, int p_175840_2_, World worldIn, Random p_175840_4_, StructureBoundingBox p_175840_5_) {
/* 1007 */       boolean var6 = true;
/*      */       
/* 1009 */       if (func_175818_a(p_175840_5_, p_175840_2_, 0, p_175840_2_ + 23, 20)) {
/*      */         
/* 1011 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, field_175828_a, field_175828_a, false);
/* 1012 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20, field_175822_f, field_175822_f, false);
/*      */         
/*      */         int var7;
/* 1015 */         for (var7 = 0; var7 < 4; var7++) {
/*      */           
/* 1017 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + var7, var7 + 1, var7, p_175840_2_ + var7, var7 + 1, 20, field_175826_b, field_175826_b, false);
/* 1018 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + var7 + 7, var7 + 5, var7 + 7, p_175840_2_ + var7 + 7, var7 + 5, 20, field_175826_b, field_175826_b, false);
/* 1019 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 17 - var7, var7 + 5, var7 + 7, p_175840_2_ + 17 - var7, var7 + 5, 20, field_175826_b, field_175826_b, false);
/* 1020 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 24 - var7, var7 + 1, var7, p_175840_2_ + 24 - var7, var7 + 1, 20, field_175826_b, field_175826_b, false);
/* 1021 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + var7 + 1, var7 + 1, var7, p_175840_2_ + 23 - var7, var7 + 1, var7, field_175826_b, field_175826_b, false);
/* 1022 */           func_175804_a(worldIn, p_175840_5_, p_175840_2_ + var7 + 8, var7 + 5, var7 + 7, p_175840_2_ + 16 - var7, var7 + 5, var7 + 7, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1025 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 4, 4, 4, p_175840_2_ + 6, 4, 20, field_175828_a, field_175828_a, false);
/* 1026 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 7, 4, 4, p_175840_2_ + 17, 4, 6, field_175828_a, field_175828_a, false);
/* 1027 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, field_175828_a, field_175828_a, false);
/* 1028 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, field_175828_a, field_175828_a, false);
/* 1029 */         func_175811_a(worldIn, field_175824_d, p_175840_2_ + 12, 9, 12, p_175840_5_);
/* 1030 */         func_175811_a(worldIn, field_175824_d, p_175840_2_ + 12, 9, 15, p_175840_5_);
/* 1031 */         func_175811_a(worldIn, field_175824_d, p_175840_2_ + 12, 9, 18, p_175840_5_);
/* 1032 */         var7 = p_175840_1_ ? (p_175840_2_ + 19) : (p_175840_2_ + 5);
/* 1033 */         int var8 = p_175840_1_ ? (p_175840_2_ + 5) : (p_175840_2_ + 19);
/*      */         
/*      */         int var9;
/* 1036 */         for (var9 = 20; var9 >= 5; var9 -= 3)
/*      */         {
/* 1038 */           func_175811_a(worldIn, field_175824_d, var7, 5, var9, p_175840_5_);
/*      */         }
/*      */         
/* 1041 */         for (var9 = 19; var9 >= 7; var9 -= 3)
/*      */         {
/* 1043 */           func_175811_a(worldIn, field_175824_d, var8, 5, var9, p_175840_5_);
/*      */         }
/*      */         
/* 1046 */         for (var9 = 0; var9 < 4; var9++) {
/*      */           
/* 1048 */           int var10 = p_175840_1_ ? (p_175840_2_ + 24 - 17 - var9 * 3) : (p_175840_2_ + 17 - var9 * 3);
/* 1049 */           func_175811_a(worldIn, field_175824_d, var10, 5, 5, p_175840_5_);
/*      */         } 
/*      */         
/* 1052 */         func_175811_a(worldIn, field_175824_d, var8, 5, 5, p_175840_5_);
/* 1053 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, field_175828_a, field_175828_a, false);
/* 1054 */         func_175804_a(worldIn, p_175840_5_, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void func_175839_b(World worldIn, Random p_175839_2_, StructureBoundingBox p_175839_3_) {
/* 1060 */       if (func_175818_a(p_175839_3_, 22, 5, 35, 17)) {
/*      */         
/* 1062 */         func_175804_a(worldIn, p_175839_3_, 25, 0, 0, 32, 8, 20, field_175822_f, field_175822_f, false);
/*      */         
/* 1064 */         for (int var4 = 0; var4 < 4; var4++) {
/*      */           
/* 1066 */           func_175804_a(worldIn, p_175839_3_, 24, 2, 5 + var4 * 4, 24, 4, 5 + var4 * 4, field_175826_b, field_175826_b, false);
/* 1067 */           func_175804_a(worldIn, p_175839_3_, 22, 4, 5 + var4 * 4, 23, 4, 5 + var4 * 4, field_175826_b, field_175826_b, false);
/* 1068 */           func_175811_a(worldIn, field_175826_b, 25, 5, 5 + var4 * 4, p_175839_3_);
/* 1069 */           func_175811_a(worldIn, field_175826_b, 26, 6, 5 + var4 * 4, p_175839_3_);
/* 1070 */           func_175811_a(worldIn, field_175825_e, 26, 5, 5 + var4 * 4, p_175839_3_);
/* 1071 */           func_175804_a(worldIn, p_175839_3_, 33, 2, 5 + var4 * 4, 33, 4, 5 + var4 * 4, field_175826_b, field_175826_b, false);
/* 1072 */           func_175804_a(worldIn, p_175839_3_, 34, 4, 5 + var4 * 4, 35, 4, 5 + var4 * 4, field_175826_b, field_175826_b, false);
/* 1073 */           func_175811_a(worldIn, field_175826_b, 32, 5, 5 + var4 * 4, p_175839_3_);
/* 1074 */           func_175811_a(worldIn, field_175826_b, 31, 6, 5 + var4 * 4, p_175839_3_);
/* 1075 */           func_175811_a(worldIn, field_175825_e, 31, 5, 5 + var4 * 4, p_175839_3_);
/* 1076 */           func_175804_a(worldIn, p_175839_3_, 27, 6, 5 + var4 * 4, 30, 6, 5 + var4 * 4, field_175828_a, field_175828_a, false);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void func_175837_c(World worldIn, Random p_175837_2_, StructureBoundingBox p_175837_3_) {
/* 1083 */       if (func_175818_a(p_175837_3_, 15, 20, 42, 21)) {
/*      */         
/* 1085 */         func_175804_a(worldIn, p_175837_3_, 15, 0, 21, 42, 0, 21, field_175828_a, field_175828_a, false);
/* 1086 */         func_175804_a(worldIn, p_175837_3_, 26, 1, 21, 31, 3, 21, field_175822_f, field_175822_f, false);
/* 1087 */         func_175804_a(worldIn, p_175837_3_, 21, 12, 21, 36, 12, 21, field_175828_a, field_175828_a, false);
/* 1088 */         func_175804_a(worldIn, p_175837_3_, 17, 11, 21, 40, 11, 21, field_175828_a, field_175828_a, false);
/* 1089 */         func_175804_a(worldIn, p_175837_3_, 16, 10, 21, 41, 10, 21, field_175828_a, field_175828_a, false);
/* 1090 */         func_175804_a(worldIn, p_175837_3_, 15, 7, 21, 42, 9, 21, field_175828_a, field_175828_a, false);
/* 1091 */         func_175804_a(worldIn, p_175837_3_, 16, 6, 21, 41, 6, 21, field_175828_a, field_175828_a, false);
/* 1092 */         func_175804_a(worldIn, p_175837_3_, 17, 5, 21, 40, 5, 21, field_175828_a, field_175828_a, false);
/* 1093 */         func_175804_a(worldIn, p_175837_3_, 21, 4, 21, 36, 4, 21, field_175828_a, field_175828_a, false);
/* 1094 */         func_175804_a(worldIn, p_175837_3_, 22, 3, 21, 26, 3, 21, field_175828_a, field_175828_a, false);
/* 1095 */         func_175804_a(worldIn, p_175837_3_, 31, 3, 21, 35, 3, 21, field_175828_a, field_175828_a, false);
/* 1096 */         func_175804_a(worldIn, p_175837_3_, 23, 2, 21, 25, 2, 21, field_175828_a, field_175828_a, false);
/* 1097 */         func_175804_a(worldIn, p_175837_3_, 32, 2, 21, 34, 2, 21, field_175828_a, field_175828_a, false);
/* 1098 */         func_175804_a(worldIn, p_175837_3_, 28, 4, 20, 29, 4, 21, field_175826_b, field_175826_b, false);
/* 1099 */         func_175811_a(worldIn, field_175826_b, 27, 3, 21, p_175837_3_);
/* 1100 */         func_175811_a(worldIn, field_175826_b, 30, 3, 21, p_175837_3_);
/* 1101 */         func_175811_a(worldIn, field_175826_b, 26, 2, 21, p_175837_3_);
/* 1102 */         func_175811_a(worldIn, field_175826_b, 31, 2, 21, p_175837_3_);
/* 1103 */         func_175811_a(worldIn, field_175826_b, 25, 1, 21, p_175837_3_);
/* 1104 */         func_175811_a(worldIn, field_175826_b, 32, 1, 21, p_175837_3_);
/*      */         
/*      */         int var4;
/* 1107 */         for (var4 = 0; var4 < 7; var4++) {
/*      */           
/* 1109 */           func_175811_a(worldIn, field_175827_c, 28 - var4, 6 + var4, 21, p_175837_3_);
/* 1110 */           func_175811_a(worldIn, field_175827_c, 29 + var4, 6 + var4, 21, p_175837_3_);
/*      */         } 
/*      */         
/* 1113 */         for (var4 = 0; var4 < 4; var4++) {
/*      */           
/* 1115 */           func_175811_a(worldIn, field_175827_c, 28 - var4, 9 + var4, 21, p_175837_3_);
/* 1116 */           func_175811_a(worldIn, field_175827_c, 29 + var4, 9 + var4, 21, p_175837_3_);
/*      */         } 
/*      */         
/* 1119 */         func_175811_a(worldIn, field_175827_c, 28, 12, 21, p_175837_3_);
/* 1120 */         func_175811_a(worldIn, field_175827_c, 29, 12, 21, p_175837_3_);
/*      */         
/* 1122 */         for (var4 = 0; var4 < 3; var4++) {
/*      */           
/* 1124 */           func_175811_a(worldIn, field_175827_c, 22 - var4 * 2, 8, 21, p_175837_3_);
/* 1125 */           func_175811_a(worldIn, field_175827_c, 22 - var4 * 2, 9, 21, p_175837_3_);
/* 1126 */           func_175811_a(worldIn, field_175827_c, 35 + var4 * 2, 8, 21, p_175837_3_);
/* 1127 */           func_175811_a(worldIn, field_175827_c, 35 + var4 * 2, 9, 21, p_175837_3_);
/*      */         } 
/*      */         
/* 1130 */         func_175804_a(worldIn, p_175837_3_, 15, 13, 21, 42, 15, 21, field_175822_f, field_175822_f, false);
/* 1131 */         func_175804_a(worldIn, p_175837_3_, 15, 1, 21, 15, 6, 21, field_175822_f, field_175822_f, false);
/* 1132 */         func_175804_a(worldIn, p_175837_3_, 16, 1, 21, 16, 5, 21, field_175822_f, field_175822_f, false);
/* 1133 */         func_175804_a(worldIn, p_175837_3_, 17, 1, 21, 20, 4, 21, field_175822_f, field_175822_f, false);
/* 1134 */         func_175804_a(worldIn, p_175837_3_, 21, 1, 21, 21, 3, 21, field_175822_f, field_175822_f, false);
/* 1135 */         func_175804_a(worldIn, p_175837_3_, 22, 1, 21, 22, 2, 21, field_175822_f, field_175822_f, false);
/* 1136 */         func_175804_a(worldIn, p_175837_3_, 23, 1, 21, 24, 1, 21, field_175822_f, field_175822_f, false);
/* 1137 */         func_175804_a(worldIn, p_175837_3_, 42, 1, 21, 42, 6, 21, field_175822_f, field_175822_f, false);
/* 1138 */         func_175804_a(worldIn, p_175837_3_, 41, 1, 21, 41, 5, 21, field_175822_f, field_175822_f, false);
/* 1139 */         func_175804_a(worldIn, p_175837_3_, 37, 1, 21, 40, 4, 21, field_175822_f, field_175822_f, false);
/* 1140 */         func_175804_a(worldIn, p_175837_3_, 36, 1, 21, 36, 3, 21, field_175822_f, field_175822_f, false);
/* 1141 */         func_175804_a(worldIn, p_175837_3_, 35, 1, 21, 35, 2, 21, field_175822_f, field_175822_f, false);
/* 1142 */         func_175804_a(worldIn, p_175837_3_, 33, 1, 21, 34, 1, 21, field_175822_f, field_175822_f, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void func_175841_d(World worldIn, Random p_175841_2_, StructureBoundingBox p_175841_3_) {
/* 1148 */       if (func_175818_a(p_175841_3_, 21, 21, 36, 36)) {
/*      */         
/* 1150 */         func_175804_a(worldIn, p_175841_3_, 21, 0, 22, 36, 0, 36, field_175828_a, field_175828_a, false);
/* 1151 */         func_175804_a(worldIn, p_175841_3_, 21, 1, 22, 36, 23, 36, field_175822_f, field_175822_f, false);
/*      */         
/* 1153 */         for (int var4 = 0; var4 < 4; var4++) {
/*      */           
/* 1155 */           func_175804_a(worldIn, p_175841_3_, 21 + var4, 13 + var4, 21 + var4, 36 - var4, 13 + var4, 21 + var4, field_175826_b, field_175826_b, false);
/* 1156 */           func_175804_a(worldIn, p_175841_3_, 21 + var4, 13 + var4, 36 - var4, 36 - var4, 13 + var4, 36 - var4, field_175826_b, field_175826_b, false);
/* 1157 */           func_175804_a(worldIn, p_175841_3_, 21 + var4, 13 + var4, 22 + var4, 21 + var4, 13 + var4, 35 - var4, field_175826_b, field_175826_b, false);
/* 1158 */           func_175804_a(worldIn, p_175841_3_, 36 - var4, 13 + var4, 22 + var4, 36 - var4, 13 + var4, 35 - var4, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1161 */         func_175804_a(worldIn, p_175841_3_, 25, 16, 25, 32, 16, 32, field_175828_a, field_175828_a, false);
/* 1162 */         func_175804_a(worldIn, p_175841_3_, 25, 17, 25, 25, 19, 25, field_175826_b, field_175826_b, false);
/* 1163 */         func_175804_a(worldIn, p_175841_3_, 32, 17, 25, 32, 19, 25, field_175826_b, field_175826_b, false);
/* 1164 */         func_175804_a(worldIn, p_175841_3_, 25, 17, 32, 25, 19, 32, field_175826_b, field_175826_b, false);
/* 1165 */         func_175804_a(worldIn, p_175841_3_, 32, 17, 32, 32, 19, 32, field_175826_b, field_175826_b, false);
/* 1166 */         func_175811_a(worldIn, field_175826_b, 26, 20, 26, p_175841_3_);
/* 1167 */         func_175811_a(worldIn, field_175826_b, 27, 21, 27, p_175841_3_);
/* 1168 */         func_175811_a(worldIn, field_175825_e, 27, 20, 27, p_175841_3_);
/* 1169 */         func_175811_a(worldIn, field_175826_b, 26, 20, 31, p_175841_3_);
/* 1170 */         func_175811_a(worldIn, field_175826_b, 27, 21, 30, p_175841_3_);
/* 1171 */         func_175811_a(worldIn, field_175825_e, 27, 20, 30, p_175841_3_);
/* 1172 */         func_175811_a(worldIn, field_175826_b, 31, 20, 31, p_175841_3_);
/* 1173 */         func_175811_a(worldIn, field_175826_b, 30, 21, 30, p_175841_3_);
/* 1174 */         func_175811_a(worldIn, field_175825_e, 30, 20, 30, p_175841_3_);
/* 1175 */         func_175811_a(worldIn, field_175826_b, 31, 20, 26, p_175841_3_);
/* 1176 */         func_175811_a(worldIn, field_175826_b, 30, 21, 27, p_175841_3_);
/* 1177 */         func_175811_a(worldIn, field_175825_e, 30, 20, 27, p_175841_3_);
/* 1178 */         func_175804_a(worldIn, p_175841_3_, 28, 21, 27, 29, 21, 27, field_175828_a, field_175828_a, false);
/* 1179 */         func_175804_a(worldIn, p_175841_3_, 27, 21, 28, 27, 21, 29, field_175828_a, field_175828_a, false);
/* 1180 */         func_175804_a(worldIn, p_175841_3_, 28, 21, 30, 29, 21, 30, field_175828_a, field_175828_a, false);
/* 1181 */         func_175804_a(worldIn, p_175841_3_, 30, 21, 28, 30, 21, 29, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void func_175835_e(World worldIn, Random p_175835_2_, StructureBoundingBox p_175835_3_) {
/* 1189 */       if (func_175818_a(p_175835_3_, 0, 21, 6, 58)) {
/*      */         
/* 1191 */         func_175804_a(worldIn, p_175835_3_, 0, 0, 21, 6, 0, 57, field_175828_a, field_175828_a, false);
/* 1192 */         func_175804_a(worldIn, p_175835_3_, 0, 1, 21, 6, 7, 57, field_175822_f, field_175822_f, false);
/* 1193 */         func_175804_a(worldIn, p_175835_3_, 4, 4, 21, 6, 4, 53, field_175828_a, field_175828_a, false);
/*      */         int var4;
/* 1195 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1197 */           func_175804_a(worldIn, p_175835_3_, var4, var4 + 1, 21, var4, var4 + 1, 57 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1200 */         for (var4 = 23; var4 < 53; var4 += 3)
/*      */         {
/* 1202 */           func_175811_a(worldIn, field_175824_d, 5, 5, var4, p_175835_3_);
/*      */         }
/*      */         
/* 1205 */         func_175811_a(worldIn, field_175824_d, 5, 5, 52, p_175835_3_);
/*      */         
/* 1207 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1209 */           func_175804_a(worldIn, p_175835_3_, var4, var4 + 1, 21, var4, var4 + 1, 57 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1212 */         func_175804_a(worldIn, p_175835_3_, 4, 1, 52, 6, 3, 52, field_175828_a, field_175828_a, false);
/* 1213 */         func_175804_a(worldIn, p_175835_3_, 5, 1, 51, 5, 3, 53, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */       
/* 1216 */       if (func_175818_a(p_175835_3_, 51, 21, 58, 58)) {
/*      */         
/* 1218 */         func_175804_a(worldIn, p_175835_3_, 51, 0, 21, 57, 0, 57, field_175828_a, field_175828_a, false);
/* 1219 */         func_175804_a(worldIn, p_175835_3_, 51, 1, 21, 57, 7, 57, field_175822_f, field_175822_f, false);
/* 1220 */         func_175804_a(worldIn, p_175835_3_, 51, 4, 21, 53, 4, 53, field_175828_a, field_175828_a, false);
/*      */         int var4;
/* 1222 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1224 */           func_175804_a(worldIn, p_175835_3_, 57 - var4, var4 + 1, 21, 57 - var4, var4 + 1, 57 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1227 */         for (var4 = 23; var4 < 53; var4 += 3)
/*      */         {
/* 1229 */           func_175811_a(worldIn, field_175824_d, 52, 5, var4, p_175835_3_);
/*      */         }
/*      */         
/* 1232 */         func_175811_a(worldIn, field_175824_d, 52, 5, 52, p_175835_3_);
/* 1233 */         func_175804_a(worldIn, p_175835_3_, 51, 1, 52, 53, 3, 52, field_175828_a, field_175828_a, false);
/* 1234 */         func_175804_a(worldIn, p_175835_3_, 52, 1, 51, 52, 3, 53, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */       
/* 1237 */       if (func_175818_a(p_175835_3_, 0, 51, 57, 57)) {
/*      */         
/* 1239 */         func_175804_a(worldIn, p_175835_3_, 7, 0, 51, 50, 0, 57, field_175828_a, field_175828_a, false);
/* 1240 */         func_175804_a(worldIn, p_175835_3_, 7, 1, 51, 50, 10, 57, field_175822_f, field_175822_f, false);
/*      */         
/* 1242 */         for (int var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1244 */           func_175804_a(worldIn, p_175835_3_, var4 + 1, var4 + 1, 57 - var4, 56 - var4, var4 + 1, 57 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void func_175842_f(World worldIn, Random p_175842_2_, StructureBoundingBox p_175842_3_) {
/* 1253 */       if (func_175818_a(p_175842_3_, 7, 21, 13, 50)) {
/*      */         
/* 1255 */         func_175804_a(worldIn, p_175842_3_, 7, 0, 21, 13, 0, 50, field_175828_a, field_175828_a, false);
/* 1256 */         func_175804_a(worldIn, p_175842_3_, 7, 1, 21, 13, 10, 50, field_175822_f, field_175822_f, false);
/* 1257 */         func_175804_a(worldIn, p_175842_3_, 11, 8, 21, 13, 8, 53, field_175828_a, field_175828_a, false);
/*      */         int var4;
/* 1259 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1261 */           func_175804_a(worldIn, p_175842_3_, var4 + 7, var4 + 5, 21, var4 + 7, var4 + 5, 54, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1264 */         for (var4 = 21; var4 <= 45; var4 += 3)
/*      */         {
/* 1266 */           func_175811_a(worldIn, field_175824_d, 12, 9, var4, p_175842_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1270 */       if (func_175818_a(p_175842_3_, 44, 21, 50, 54)) {
/*      */         
/* 1272 */         func_175804_a(worldIn, p_175842_3_, 44, 0, 21, 50, 0, 50, field_175828_a, field_175828_a, false);
/* 1273 */         func_175804_a(worldIn, p_175842_3_, 44, 1, 21, 50, 10, 50, field_175822_f, field_175822_f, false);
/* 1274 */         func_175804_a(worldIn, p_175842_3_, 44, 8, 21, 46, 8, 53, field_175828_a, field_175828_a, false);
/*      */         int var4;
/* 1276 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1278 */           func_175804_a(worldIn, p_175842_3_, 50 - var4, var4 + 5, 21, 50 - var4, var4 + 5, 54, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1281 */         for (var4 = 21; var4 <= 45; var4 += 3)
/*      */         {
/* 1283 */           func_175811_a(worldIn, field_175824_d, 45, 9, var4, p_175842_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1287 */       if (func_175818_a(p_175842_3_, 8, 44, 49, 54)) {
/*      */         
/* 1289 */         func_175804_a(worldIn, p_175842_3_, 14, 0, 44, 43, 0, 50, field_175828_a, field_175828_a, false);
/* 1290 */         func_175804_a(worldIn, p_175842_3_, 14, 1, 44, 43, 10, 50, field_175822_f, field_175822_f, false);
/*      */         int var4;
/* 1292 */         for (var4 = 12; var4 <= 45; var4 += 3) {
/*      */           
/* 1294 */           func_175811_a(worldIn, field_175824_d, var4, 9, 45, p_175842_3_);
/* 1295 */           func_175811_a(worldIn, field_175824_d, var4, 9, 52, p_175842_3_);
/*      */           
/* 1297 */           if (var4 == 12 || var4 == 18 || var4 == 24 || var4 == 33 || var4 == 39 || var4 == 45) {
/*      */             
/* 1299 */             func_175811_a(worldIn, field_175824_d, var4, 9, 47, p_175842_3_);
/* 1300 */             func_175811_a(worldIn, field_175824_d, var4, 9, 50, p_175842_3_);
/* 1301 */             func_175811_a(worldIn, field_175824_d, var4, 10, 45, p_175842_3_);
/* 1302 */             func_175811_a(worldIn, field_175824_d, var4, 10, 46, p_175842_3_);
/* 1303 */             func_175811_a(worldIn, field_175824_d, var4, 10, 51, p_175842_3_);
/* 1304 */             func_175811_a(worldIn, field_175824_d, var4, 10, 52, p_175842_3_);
/* 1305 */             func_175811_a(worldIn, field_175824_d, var4, 11, 47, p_175842_3_);
/* 1306 */             func_175811_a(worldIn, field_175824_d, var4, 11, 50, p_175842_3_);
/* 1307 */             func_175811_a(worldIn, field_175824_d, var4, 12, 48, p_175842_3_);
/* 1308 */             func_175811_a(worldIn, field_175824_d, var4, 12, 49, p_175842_3_);
/*      */           } 
/*      */         } 
/*      */         
/* 1312 */         for (var4 = 0; var4 < 3; var4++)
/*      */         {
/* 1314 */           func_175804_a(worldIn, p_175842_3_, 8 + var4, 5 + var4, 54, 49 - var4, 5 + var4, 54, field_175828_a, field_175828_a, false);
/*      */         }
/*      */         
/* 1317 */         func_175804_a(worldIn, p_175842_3_, 11, 8, 54, 46, 8, 54, field_175826_b, field_175826_b, false);
/* 1318 */         func_175804_a(worldIn, p_175842_3_, 14, 8, 44, 43, 8, 53, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void func_175838_g(World worldIn, Random p_175838_2_, StructureBoundingBox p_175838_3_) {
/* 1326 */       if (func_175818_a(p_175838_3_, 14, 21, 20, 43)) {
/*      */         
/* 1328 */         func_175804_a(worldIn, p_175838_3_, 14, 0, 21, 20, 0, 43, field_175828_a, field_175828_a, false);
/* 1329 */         func_175804_a(worldIn, p_175838_3_, 14, 1, 22, 20, 14, 43, field_175822_f, field_175822_f, false);
/* 1330 */         func_175804_a(worldIn, p_175838_3_, 18, 12, 22, 20, 12, 39, field_175828_a, field_175828_a, false);
/* 1331 */         func_175804_a(worldIn, p_175838_3_, 18, 12, 21, 20, 12, 21, field_175826_b, field_175826_b, false);
/*      */         int var4;
/* 1333 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1335 */           func_175804_a(worldIn, p_175838_3_, var4 + 14, var4 + 9, 21, var4 + 14, var4 + 9, 43 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1338 */         for (var4 = 23; var4 <= 39; var4 += 3)
/*      */         {
/* 1340 */           func_175811_a(worldIn, field_175824_d, 19, 13, var4, p_175838_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1344 */       if (func_175818_a(p_175838_3_, 37, 21, 43, 43)) {
/*      */         
/* 1346 */         func_175804_a(worldIn, p_175838_3_, 37, 0, 21, 43, 0, 43, field_175828_a, field_175828_a, false);
/* 1347 */         func_175804_a(worldIn, p_175838_3_, 37, 1, 22, 43, 14, 43, field_175822_f, field_175822_f, false);
/* 1348 */         func_175804_a(worldIn, p_175838_3_, 37, 12, 22, 39, 12, 39, field_175828_a, field_175828_a, false);
/* 1349 */         func_175804_a(worldIn, p_175838_3_, 37, 12, 21, 39, 12, 21, field_175826_b, field_175826_b, false);
/*      */         int var4;
/* 1351 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1353 */           func_175804_a(worldIn, p_175838_3_, 43 - var4, var4 + 9, 21, 43 - var4, var4 + 9, 43 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1356 */         for (var4 = 23; var4 <= 39; var4 += 3)
/*      */         {
/* 1358 */           func_175811_a(worldIn, field_175824_d, 38, 13, var4, p_175838_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1362 */       if (func_175818_a(p_175838_3_, 15, 37, 42, 43)) {
/*      */         
/* 1364 */         func_175804_a(worldIn, p_175838_3_, 21, 0, 37, 36, 0, 43, field_175828_a, field_175828_a, false);
/* 1365 */         func_175804_a(worldIn, p_175838_3_, 21, 1, 37, 36, 14, 43, field_175822_f, field_175822_f, false);
/* 1366 */         func_175804_a(worldIn, p_175838_3_, 21, 12, 37, 36, 12, 39, field_175828_a, field_175828_a, false);
/*      */         int var4;
/* 1368 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 1370 */           func_175804_a(worldIn, p_175838_3_, 15 + var4, var4 + 9, 43 - var4, 42 - var4, var4 + 9, 43 - var4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1373 */         for (var4 = 21; var4 <= 36; var4 += 3)
/*      */         {
/* 1375 */           func_175811_a(worldIn, field_175824_d, var4, 13, 38, p_175838_3_);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class MonumentCoreRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001984";
/*      */     
/*      */     public MonumentCoreRoom() {}
/*      */     
/*      */     public MonumentCoreRoom(EnumFacing p_i45598_1_, StructureOceanMonumentPieces.RoomDefinition p_i45598_2_, Random p_i45598_3_) {
/* 1389 */       super(1, p_i45598_1_, p_i45598_2_, 2, 2, 2);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1394 */       func_175819_a(worldIn, p_74875_3_, 1, 8, 0, 14, 8, 14, field_175828_a);
/* 1395 */       byte var4 = 7;
/* 1396 */       IBlockState var5 = field_175826_b;
/* 1397 */       func_175804_a(worldIn, p_74875_3_, 0, var4, 0, 0, var4, 15, var5, var5, false);
/* 1398 */       func_175804_a(worldIn, p_74875_3_, 15, var4, 0, 15, var4, 15, var5, var5, false);
/* 1399 */       func_175804_a(worldIn, p_74875_3_, 1, var4, 0, 15, var4, 0, var5, var5, false);
/* 1400 */       func_175804_a(worldIn, p_74875_3_, 1, var4, 15, 14, var4, 15, var5, var5, false);
/*      */       
/*      */       int var7;
/* 1403 */       for (var7 = 1; var7 <= 6; var7++) {
/*      */         
/* 1405 */         var5 = field_175826_b;
/*      */         
/* 1407 */         if (var7 == 2 || var7 == 6)
/*      */         {
/* 1409 */           var5 = field_175828_a;
/*      */         }
/*      */         
/* 1412 */         for (int var6 = 0; var6 <= 15; var6 += 15) {
/*      */           
/* 1414 */           func_175804_a(worldIn, p_74875_3_, var6, var7, 0, var6, var7, 1, var5, var5, false);
/* 1415 */           func_175804_a(worldIn, p_74875_3_, var6, var7, 6, var6, var7, 9, var5, var5, false);
/* 1416 */           func_175804_a(worldIn, p_74875_3_, var6, var7, 14, var6, var7, 15, var5, var5, false);
/*      */         } 
/*      */         
/* 1419 */         func_175804_a(worldIn, p_74875_3_, 1, var7, 0, 1, var7, 0, var5, var5, false);
/* 1420 */         func_175804_a(worldIn, p_74875_3_, 6, var7, 0, 9, var7, 0, var5, var5, false);
/* 1421 */         func_175804_a(worldIn, p_74875_3_, 14, var7, 0, 14, var7, 0, var5, var5, false);
/* 1422 */         func_175804_a(worldIn, p_74875_3_, 1, var7, 15, 14, var7, 15, var5, var5, false);
/*      */       } 
/*      */       
/* 1425 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 6, 9, 6, 9, field_175827_c, field_175827_c, false);
/* 1426 */       func_175804_a(worldIn, p_74875_3_, 7, 4, 7, 8, 5, 8, Blocks.gold_block.getDefaultState(), Blocks.gold_block.getDefaultState(), false);
/*      */       
/* 1428 */       for (var7 = 3; var7 <= 6; var7 += 3) {
/*      */         
/* 1430 */         for (int var8 = 6; var8 <= 9; var8 += 3) {
/*      */           
/* 1432 */           func_175811_a(worldIn, field_175825_e, var8, var7, 6, p_74875_3_);
/* 1433 */           func_175811_a(worldIn, field_175825_e, var8, var7, 9, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1437 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 6, 5, 2, 6, field_175826_b, field_175826_b, false);
/* 1438 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 5, 2, 9, field_175826_b, field_175826_b, false);
/* 1439 */       func_175804_a(worldIn, p_74875_3_, 10, 1, 6, 10, 2, 6, field_175826_b, field_175826_b, false);
/* 1440 */       func_175804_a(worldIn, p_74875_3_, 10, 1, 9, 10, 2, 9, field_175826_b, field_175826_b, false);
/* 1441 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 5, 6, 2, 5, field_175826_b, field_175826_b, false);
/* 1442 */       func_175804_a(worldIn, p_74875_3_, 9, 1, 5, 9, 2, 5, field_175826_b, field_175826_b, false);
/* 1443 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 10, 6, 2, 10, field_175826_b, field_175826_b, false);
/* 1444 */       func_175804_a(worldIn, p_74875_3_, 9, 1, 10, 9, 2, 10, field_175826_b, field_175826_b, false);
/* 1445 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 5, 5, 6, 5, field_175826_b, field_175826_b, false);
/* 1446 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 10, 5, 6, 10, field_175826_b, field_175826_b, false);
/* 1447 */       func_175804_a(worldIn, p_74875_3_, 10, 2, 5, 10, 6, 5, field_175826_b, field_175826_b, false);
/* 1448 */       func_175804_a(worldIn, p_74875_3_, 10, 2, 10, 10, 6, 10, field_175826_b, field_175826_b, false);
/* 1449 */       func_175804_a(worldIn, p_74875_3_, 5, 7, 1, 5, 7, 6, field_175826_b, field_175826_b, false);
/* 1450 */       func_175804_a(worldIn, p_74875_3_, 10, 7, 1, 10, 7, 6, field_175826_b, field_175826_b, false);
/* 1451 */       func_175804_a(worldIn, p_74875_3_, 5, 7, 9, 5, 7, 14, field_175826_b, field_175826_b, false);
/* 1452 */       func_175804_a(worldIn, p_74875_3_, 10, 7, 9, 10, 7, 14, field_175826_b, field_175826_b, false);
/* 1453 */       func_175804_a(worldIn, p_74875_3_, 1, 7, 5, 6, 7, 5, field_175826_b, field_175826_b, false);
/* 1454 */       func_175804_a(worldIn, p_74875_3_, 1, 7, 10, 6, 7, 10, field_175826_b, field_175826_b, false);
/* 1455 */       func_175804_a(worldIn, p_74875_3_, 9, 7, 5, 14, 7, 5, field_175826_b, field_175826_b, false);
/* 1456 */       func_175804_a(worldIn, p_74875_3_, 9, 7, 10, 14, 7, 10, field_175826_b, field_175826_b, false);
/* 1457 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 2, 2, 1, 3, field_175826_b, field_175826_b, false);
/* 1458 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 2, field_175826_b, field_175826_b, false);
/* 1459 */       func_175804_a(worldIn, p_74875_3_, 13, 1, 2, 13, 1, 3, field_175826_b, field_175826_b, false);
/* 1460 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 2, 12, 1, 2, field_175826_b, field_175826_b, false);
/* 1461 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 12, 2, 1, 13, field_175826_b, field_175826_b, false);
/* 1462 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 13, 3, 1, 13, field_175826_b, field_175826_b, false);
/* 1463 */       func_175804_a(worldIn, p_74875_3_, 13, 1, 12, 13, 1, 13, field_175826_b, field_175826_b, false);
/* 1464 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 13, 12, 1, 13, field_175826_b, field_175826_b, false);
/* 1465 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static interface MonumentRoomFitHelper
/*      */   {
/*      */     boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition param1RoomDefinition);
/*      */     
/*      */     StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing param1EnumFacing, StructureOceanMonumentPieces.RoomDefinition param1RoomDefinition, Random param1Random);
/*      */   }
/*      */   
/*      */   public static class Penthouse
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001977";
/*      */     
/*      */     public Penthouse() {}
/*      */     
/*      */     public Penthouse(EnumFacing p_i45591_1_, StructureBoundingBox p_i45591_2_) {
/* 1484 */       super(p_i45591_1_, p_i45591_2_);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1489 */       func_175804_a(worldIn, p_74875_3_, 2, -1, 2, 11, -1, 11, field_175826_b, field_175826_b, false);
/* 1490 */       func_175804_a(worldIn, p_74875_3_, 0, -1, 0, 1, -1, 11, field_175828_a, field_175828_a, false);
/* 1491 */       func_175804_a(worldIn, p_74875_3_, 12, -1, 0, 13, -1, 11, field_175828_a, field_175828_a, false);
/* 1492 */       func_175804_a(worldIn, p_74875_3_, 2, -1, 0, 11, -1, 1, field_175828_a, field_175828_a, false);
/* 1493 */       func_175804_a(worldIn, p_74875_3_, 2, -1, 12, 11, -1, 13, field_175828_a, field_175828_a, false);
/* 1494 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 13, field_175826_b, field_175826_b, false);
/* 1495 */       func_175804_a(worldIn, p_74875_3_, 13, 0, 0, 13, 0, 13, field_175826_b, field_175826_b, false);
/* 1496 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 12, 0, 0, field_175826_b, field_175826_b, false);
/* 1497 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 13, 12, 0, 13, field_175826_b, field_175826_b, false);
/*      */       
/* 1499 */       for (int var4 = 2; var4 <= 11; var4 += 3) {
/*      */         
/* 1501 */         func_175811_a(worldIn, field_175825_e, 0, 0, var4, p_74875_3_);
/* 1502 */         func_175811_a(worldIn, field_175825_e, 13, 0, var4, p_74875_3_);
/* 1503 */         func_175811_a(worldIn, field_175825_e, var4, 0, 0, p_74875_3_);
/*      */       } 
/*      */       
/* 1506 */       func_175804_a(worldIn, p_74875_3_, 2, 0, 3, 4, 0, 9, field_175826_b, field_175826_b, false);
/* 1507 */       func_175804_a(worldIn, p_74875_3_, 9, 0, 3, 11, 0, 9, field_175826_b, field_175826_b, false);
/* 1508 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 9, 9, 0, 11, field_175826_b, field_175826_b, false);
/* 1509 */       func_175811_a(worldIn, field_175826_b, 5, 0, 8, p_74875_3_);
/* 1510 */       func_175811_a(worldIn, field_175826_b, 8, 0, 8, p_74875_3_);
/* 1511 */       func_175811_a(worldIn, field_175826_b, 10, 0, 10, p_74875_3_);
/* 1512 */       func_175811_a(worldIn, field_175826_b, 3, 0, 10, p_74875_3_);
/* 1513 */       func_175804_a(worldIn, p_74875_3_, 3, 0, 3, 3, 0, 7, field_175827_c, field_175827_c, false);
/* 1514 */       func_175804_a(worldIn, p_74875_3_, 10, 0, 3, 10, 0, 7, field_175827_c, field_175827_c, false);
/* 1515 */       func_175804_a(worldIn, p_74875_3_, 6, 0, 10, 7, 0, 10, field_175827_c, field_175827_c, false);
/* 1516 */       byte var7 = 3;
/*      */       
/* 1518 */       for (int var5 = 0; var5 < 2; var5++) {
/*      */         
/* 1520 */         for (int var6 = 2; var6 <= 8; var6 += 3)
/*      */         {
/* 1522 */           func_175804_a(worldIn, p_74875_3_, var7, 0, var6, var7, 2, var6, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 1525 */         var7 = 10;
/*      */       } 
/*      */       
/* 1528 */       func_175804_a(worldIn, p_74875_3_, 5, 0, 10, 5, 2, 10, field_175826_b, field_175826_b, false);
/* 1529 */       func_175804_a(worldIn, p_74875_3_, 8, 0, 10, 8, 2, 10, field_175826_b, field_175826_b, false);
/* 1530 */       func_175804_a(worldIn, p_74875_3_, 6, -1, 7, 7, -1, 8, field_175827_c, field_175827_c, false);
/* 1531 */       func_175804_a(worldIn, p_74875_3_, 6, -1, 3, 7, -1, 4, field_175822_f, field_175822_f, false);
/* 1532 */       func_175817_a(worldIn, p_74875_3_, 6, 1, 6);
/* 1533 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static abstract class Piece
/*      */     extends StructureComponent {
/* 1539 */     protected static final IBlockState field_175828_a = Blocks.prismarine.getStateFromMeta(BlockPrismarine.ROUGHMETA);
/* 1540 */     protected static final IBlockState field_175826_b = Blocks.prismarine.getStateFromMeta(BlockPrismarine.BRICKSMETA);
/* 1541 */     protected static final IBlockState field_175827_c = Blocks.prismarine.getStateFromMeta(BlockPrismarine.DARKMETA);
/* 1542 */     protected static final IBlockState field_175824_d = field_175826_b;
/* 1543 */     protected static final IBlockState field_175825_e = Blocks.sea_lantern.getDefaultState();
/* 1544 */     protected static final IBlockState field_175822_f = Blocks.water.getDefaultState();
/* 1545 */     protected static final int field_175823_g = func_175820_a(2, 0, 0);
/* 1546 */     protected static final int field_175831_h = func_175820_a(2, 2, 0);
/* 1547 */     protected static final int field_175832_i = func_175820_a(0, 1, 0);
/* 1548 */     protected static final int field_175829_j = func_175820_a(4, 1, 0);
/*      */     
/*      */     protected StructureOceanMonumentPieces.RoomDefinition field_175830_k;
/*      */     private static final String __OBFID = "CL_00001976";
/*      */     
/*      */     protected static final int func_175820_a(int p_175820_0_, int p_175820_1_, int p_175820_2_) {
/* 1554 */       return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
/*      */     }
/*      */ 
/*      */     
/*      */     public Piece() {
/* 1559 */       super(0);
/*      */     }
/*      */ 
/*      */     
/*      */     public Piece(int p_i45588_1_) {
/* 1564 */       super(p_i45588_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     public Piece(EnumFacing p_i45589_1_, StructureBoundingBox p_i45589_2_) {
/* 1569 */       super(1);
/* 1570 */       this.coordBaseMode = p_i45589_1_;
/* 1571 */       this.boundingBox = p_i45589_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected Piece(int p_i45590_1_, EnumFacing p_i45590_2_, StructureOceanMonumentPieces.RoomDefinition p_i45590_3_, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_) {
/* 1576 */       super(p_i45590_1_);
/* 1577 */       this.coordBaseMode = p_i45590_2_;
/* 1578 */       this.field_175830_k = p_i45590_3_;
/* 1579 */       int var7 = p_i45590_3_.field_175967_a;
/* 1580 */       int var8 = var7 % 5;
/* 1581 */       int var9 = var7 / 5 % 5;
/* 1582 */       int var10 = var7 / 25;
/*      */       
/* 1584 */       if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH) {
/*      */         
/* 1586 */         this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
/*      */       }
/*      */       else {
/*      */         
/* 1590 */         this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_4_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_6_ * 8 - 1);
/*      */       } 
/*      */       
/* 1593 */       switch (StructureOceanMonumentPieces.SwitchEnumFacing.field_175971_a[p_i45590_2_.ordinal()]) {
/*      */         
/*      */         case 1:
/* 1596 */           this.boundingBox.offset(var8 * 8, var10 * 4, -(var9 + p_i45590_6_) * 8 + 1);
/*      */           return;
/*      */         
/*      */         case 2:
/* 1600 */           this.boundingBox.offset(var8 * 8, var10 * 4, var9 * 8);
/*      */           return;
/*      */         
/*      */         case 3:
/* 1604 */           this.boundingBox.offset(-(var9 + p_i45590_6_) * 8 + 1, var10 * 4, var8 * 8);
/*      */           return;
/*      */       } 
/*      */       
/* 1608 */       this.boundingBox.offset(var9 * 8, var10 * 4, var8 * 8);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {}
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {}
/*      */     
/*      */     protected void func_175821_a(World worldIn, StructureBoundingBox p_175821_2_, int p_175821_3_, int p_175821_4_, boolean p_175821_5_) {
/* 1618 */       if (p_175821_5_) {
/*      */         
/* 1620 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 2, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
/* 1621 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
/* 1622 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 0, p_175821_3_ + 4, 0, p_175821_4_ + 2, field_175828_a, field_175828_a, false);
/* 1623 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
/* 1624 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 2, p_175821_3_ + 4, 0, p_175821_4_ + 2, field_175826_b, field_175826_b, false);
/* 1625 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 5, field_175826_b, field_175826_b, false);
/* 1626 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 2, 0, p_175821_4_ + 3, p_175821_3_ + 2, 0, p_175821_4_ + 4, field_175826_b, field_175826_b, false);
/* 1627 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 3, p_175821_3_ + 5, 0, p_175821_4_ + 4, field_175826_b, field_175826_b, false);
/*      */       }
/*      */       else {
/*      */         
/* 1631 */         func_175804_a(worldIn, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_175819_a(World worldIn, StructureBoundingBox p_175819_2_, int p_175819_3_, int p_175819_4_, int p_175819_5_, int p_175819_6_, int p_175819_7_, int p_175819_8_, IBlockState p_175819_9_) {
/* 1637 */       for (int var10 = p_175819_4_; var10 <= p_175819_7_; var10++) {
/*      */         
/* 1639 */         for (int var11 = p_175819_3_; var11 <= p_175819_6_; var11++) {
/*      */           
/* 1641 */           for (int var12 = p_175819_5_; var12 <= p_175819_8_; var12++) {
/*      */             
/* 1643 */             if (func_175807_a(worldIn, var11, var10, var12, p_175819_2_) == field_175822_f)
/*      */             {
/* 1645 */               func_175811_a(worldIn, p_175819_9_, var11, var10, var12, p_175819_2_);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean func_175818_a(StructureBoundingBox p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_) {
/* 1654 */       int var6 = getXWithOffset(p_175818_2_, p_175818_3_);
/* 1655 */       int var7 = getZWithOffset(p_175818_2_, p_175818_3_);
/* 1656 */       int var8 = getXWithOffset(p_175818_4_, p_175818_5_);
/* 1657 */       int var9 = getZWithOffset(p_175818_4_, p_175818_5_);
/* 1658 */       return p_175818_1_.intersectsWith(Math.min(var6, var8), Math.min(var7, var9), Math.max(var6, var8), Math.max(var7, var9));
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean func_175817_a(World worldIn, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_) {
/* 1663 */       int var6 = getXWithOffset(p_175817_3_, p_175817_5_);
/* 1664 */       int var7 = getYWithOffset(p_175817_4_);
/* 1665 */       int var8 = getZWithOffset(p_175817_3_, p_175817_5_);
/*      */       
/* 1667 */       if (p_175817_2_.func_175898_b((Vec3i)new BlockPos(var6, var7, var8))) {
/*      */         
/* 1669 */         EntityGuardian var9 = new EntityGuardian(worldIn);
/* 1670 */         var9.func_175467_a(true);
/* 1671 */         var9.heal(var9.getMaxHealth());
/* 1672 */         var9.setLocationAndAngles(var6 + 0.5D, var7, var8 + 0.5D, 0.0F, 0.0F);
/* 1673 */         var9.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos((Entity)var9)), null);
/* 1674 */         worldIn.spawnEntityInWorld((Entity)var9);
/* 1675 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1679 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class RoomDefinition
/*      */   {
/*      */     int field_175967_a;
/* 1687 */     RoomDefinition[] field_175965_b = new RoomDefinition[6];
/* 1688 */     boolean[] field_175966_c = new boolean[6];
/*      */     
/*      */     boolean field_175963_d;
/*      */     boolean field_175964_e;
/*      */     int field_175962_f;
/*      */     private static final String __OBFID = "CL_00001972";
/*      */     
/*      */     public RoomDefinition(int p_i45584_1_) {
/* 1696 */       this.field_175967_a = p_i45584_1_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_175957_a(EnumFacing p_175957_1_, RoomDefinition p_175957_2_) {
/* 1701 */       this.field_175965_b[p_175957_1_.getIndex()] = p_175957_2_;
/* 1702 */       p_175957_2_.field_175965_b[p_175957_1_.getOpposite().getIndex()] = this;
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_175958_a() {
/* 1707 */       for (int var1 = 0; var1 < 6; var1++)
/*      */       {
/* 1709 */         this.field_175966_c[var1] = (this.field_175965_b[var1] != null);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_175959_a(int p_175959_1_) {
/* 1715 */       if (this.field_175964_e)
/*      */       {
/* 1717 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1721 */       this.field_175962_f = p_175959_1_;
/*      */       
/* 1723 */       for (int var2 = 0; var2 < 6; var2++) {
/*      */         
/* 1725 */         if (this.field_175965_b[var2] != null && this.field_175966_c[var2] && (this.field_175965_b[var2]).field_175962_f != p_175959_1_ && this.field_175965_b[var2].func_175959_a(p_175959_1_))
/*      */         {
/* 1727 */           return true;
/*      */         }
/*      */       } 
/*      */       
/* 1731 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean func_175961_b() {
/* 1737 */       return (this.field_175967_a >= 75);
/*      */     }
/*      */ 
/*      */     
/*      */     public int func_175960_c() {
/* 1742 */       int var1 = 0;
/*      */       
/* 1744 */       for (int var2 = 0; var2 < 6; var2++) {
/*      */         
/* 1746 */         if (this.field_175966_c[var2])
/*      */         {
/* 1748 */           var1++;
/*      */         }
/*      */       } 
/*      */       
/* 1752 */       return var1;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class SimpleRoom
/*      */     extends Piece
/*      */   {
/*      */     private int field_175833_o;
/*      */     private static final String __OBFID = "CL_00001975";
/*      */     
/*      */     public SimpleRoom() {}
/*      */     
/*      */     public SimpleRoom(EnumFacing p_i45587_1_, StructureOceanMonumentPieces.RoomDefinition p_i45587_2_, Random p_i45587_3_) {
/* 1765 */       super(1, p_i45587_1_, p_i45587_2_, 1, 1, 1);
/* 1766 */       this.field_175833_o = p_i45587_3_.nextInt(3);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1771 */       if (this.field_175830_k.field_175967_a / 25 > 0)
/*      */       {
/* 1773 */         func_175821_a(worldIn, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       }
/*      */       
/* 1776 */       if (this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/* 1778 */         func_175819_a(worldIn, p_74875_3_, 1, 4, 1, 6, 4, 6, field_175828_a);
/*      */       }
/*      */       
/* 1781 */       boolean var4 = (this.field_175833_o != 0 && p_74875_2_.nextBoolean() && !this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()] && !this.field_175830_k.field_175966_c[EnumFacing.UP.getIndex()] && this.field_175830_k.func_175960_c() > 1);
/*      */       
/* 1783 */       if (this.field_175833_o == 0) {
/*      */         
/* 1785 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 2, 1, 2, field_175826_b, field_175826_b, false);
/* 1786 */         func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 2, 3, 2, field_175826_b, field_175826_b, false);
/* 1787 */         func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 2, field_175828_a, field_175828_a, false);
/* 1788 */         func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 2, 2, 0, field_175828_a, field_175828_a, false);
/* 1789 */         func_175811_a(worldIn, field_175825_e, 1, 2, 1, p_74875_3_);
/* 1790 */         func_175804_a(worldIn, p_74875_3_, 5, 1, 0, 7, 1, 2, field_175826_b, field_175826_b, false);
/* 1791 */         func_175804_a(worldIn, p_74875_3_, 5, 3, 0, 7, 3, 2, field_175826_b, field_175826_b, false);
/* 1792 */         func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 7, 2, 2, field_175828_a, field_175828_a, false);
/* 1793 */         func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 6, 2, 0, field_175828_a, field_175828_a, false);
/* 1794 */         func_175811_a(worldIn, field_175825_e, 6, 2, 1, p_74875_3_);
/* 1795 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 5, 2, 1, 7, field_175826_b, field_175826_b, false);
/* 1796 */         func_175804_a(worldIn, p_74875_3_, 0, 3, 5, 2, 3, 7, field_175826_b, field_175826_b, false);
/* 1797 */         func_175804_a(worldIn, p_74875_3_, 0, 2, 5, 0, 2, 7, field_175828_a, field_175828_a, false);
/* 1798 */         func_175804_a(worldIn, p_74875_3_, 1, 2, 7, 2, 2, 7, field_175828_a, field_175828_a, false);
/* 1799 */         func_175811_a(worldIn, field_175825_e, 1, 2, 6, p_74875_3_);
/* 1800 */         func_175804_a(worldIn, p_74875_3_, 5, 1, 5, 7, 1, 7, field_175826_b, field_175826_b, false);
/* 1801 */         func_175804_a(worldIn, p_74875_3_, 5, 3, 5, 7, 3, 7, field_175826_b, field_175826_b, false);
/* 1802 */         func_175804_a(worldIn, p_74875_3_, 7, 2, 5, 7, 2, 7, field_175828_a, field_175828_a, false);
/* 1803 */         func_175804_a(worldIn, p_74875_3_, 5, 2, 7, 6, 2, 7, field_175828_a, field_175828_a, false);
/* 1804 */         func_175811_a(worldIn, field_175825_e, 6, 2, 6, p_74875_3_);
/*      */         
/* 1806 */         if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
/*      */           
/* 1808 */           func_175804_a(worldIn, p_74875_3_, 3, 3, 0, 4, 3, 0, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/* 1812 */           func_175804_a(worldIn, p_74875_3_, 3, 3, 0, 4, 3, 1, field_175826_b, field_175826_b, false);
/* 1813 */           func_175804_a(worldIn, p_74875_3_, 3, 2, 0, 4, 2, 0, field_175828_a, field_175828_a, false);
/* 1814 */           func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 1, 1, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1817 */         if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
/*      */           
/* 1819 */           func_175804_a(worldIn, p_74875_3_, 3, 3, 7, 4, 3, 7, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/* 1823 */           func_175804_a(worldIn, p_74875_3_, 3, 3, 6, 4, 3, 7, field_175826_b, field_175826_b, false);
/* 1824 */           func_175804_a(worldIn, p_74875_3_, 3, 2, 7, 4, 2, 7, field_175828_a, field_175828_a, false);
/* 1825 */           func_175804_a(worldIn, p_74875_3_, 3, 1, 6, 4, 1, 7, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1828 */         if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()]) {
/*      */           
/* 1830 */           func_175804_a(worldIn, p_74875_3_, 0, 3, 3, 0, 3, 4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else {
/*      */           
/* 1834 */           func_175804_a(worldIn, p_74875_3_, 0, 3, 3, 1, 3, 4, field_175826_b, field_175826_b, false);
/* 1835 */           func_175804_a(worldIn, p_74875_3_, 0, 2, 3, 0, 2, 4, field_175828_a, field_175828_a, false);
/* 1836 */           func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 1, 1, 4, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1839 */         if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */         {
/* 1841 */           func_175804_a(worldIn, p_74875_3_, 7, 3, 3, 7, 3, 4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         else
/*      */         {
/* 1845 */           func_175804_a(worldIn, p_74875_3_, 6, 3, 3, 7, 3, 4, field_175826_b, field_175826_b, false);
/* 1846 */           func_175804_a(worldIn, p_74875_3_, 7, 2, 3, 7, 2, 4, field_175828_a, field_175828_a, false);
/* 1847 */           func_175804_a(worldIn, p_74875_3_, 6, 1, 3, 7, 1, 4, field_175826_b, field_175826_b, false);
/*      */         }
/*      */       
/* 1850 */       } else if (this.field_175833_o == 1) {
/*      */         
/* 1852 */         func_175804_a(worldIn, p_74875_3_, 2, 1, 2, 2, 3, 2, field_175826_b, field_175826_b, false);
/* 1853 */         func_175804_a(worldIn, p_74875_3_, 2, 1, 5, 2, 3, 5, field_175826_b, field_175826_b, false);
/* 1854 */         func_175804_a(worldIn, p_74875_3_, 5, 1, 5, 5, 3, 5, field_175826_b, field_175826_b, false);
/* 1855 */         func_175804_a(worldIn, p_74875_3_, 5, 1, 2, 5, 3, 2, field_175826_b, field_175826_b, false);
/* 1856 */         func_175811_a(worldIn, field_175825_e, 2, 2, 2, p_74875_3_);
/* 1857 */         func_175811_a(worldIn, field_175825_e, 2, 2, 5, p_74875_3_);
/* 1858 */         func_175811_a(worldIn, field_175825_e, 5, 2, 5, p_74875_3_);
/* 1859 */         func_175811_a(worldIn, field_175825_e, 5, 2, 2, p_74875_3_);
/* 1860 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 1, 3, 0, field_175826_b, field_175826_b, false);
/* 1861 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 1, field_175826_b, field_175826_b, false);
/* 1862 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 7, 1, 3, 7, field_175826_b, field_175826_b, false);
/* 1863 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 6, 0, 3, 6, field_175826_b, field_175826_b, false);
/* 1864 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 7, 7, 3, 7, field_175826_b, field_175826_b, false);
/* 1865 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 6, 7, 3, 6, field_175826_b, field_175826_b, false);
/* 1866 */         func_175804_a(worldIn, p_74875_3_, 6, 1, 0, 7, 3, 0, field_175826_b, field_175826_b, false);
/* 1867 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 1, 7, 3, 1, field_175826_b, field_175826_b, false);
/* 1868 */         func_175811_a(worldIn, field_175828_a, 1, 2, 0, p_74875_3_);
/* 1869 */         func_175811_a(worldIn, field_175828_a, 0, 2, 1, p_74875_3_);
/* 1870 */         func_175811_a(worldIn, field_175828_a, 1, 2, 7, p_74875_3_);
/* 1871 */         func_175811_a(worldIn, field_175828_a, 0, 2, 6, p_74875_3_);
/* 1872 */         func_175811_a(worldIn, field_175828_a, 6, 2, 7, p_74875_3_);
/* 1873 */         func_175811_a(worldIn, field_175828_a, 7, 2, 6, p_74875_3_);
/* 1874 */         func_175811_a(worldIn, field_175828_a, 6, 2, 0, p_74875_3_);
/* 1875 */         func_175811_a(worldIn, field_175828_a, 7, 2, 1, p_74875_3_);
/*      */         
/* 1877 */         if (!this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
/*      */           
/* 1879 */           func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
/* 1880 */           func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175828_a, field_175828_a, false);
/* 1881 */           func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1884 */         if (!this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
/*      */           
/* 1886 */           func_175804_a(worldIn, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
/* 1887 */           func_175804_a(worldIn, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175828_a, field_175828_a, false);
/* 1888 */           func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1891 */         if (!this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()]) {
/*      */           
/* 1893 */           func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 3, 6, field_175826_b, field_175826_b, false);
/* 1894 */           func_175804_a(worldIn, p_74875_3_, 0, 2, 1, 0, 2, 6, field_175828_a, field_175828_a, false);
/* 1895 */           func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 1, 6, field_175826_b, field_175826_b, false);
/*      */         } 
/*      */         
/* 1898 */         if (!this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */         {
/* 1900 */           func_175804_a(worldIn, p_74875_3_, 7, 3, 1, 7, 3, 6, field_175826_b, field_175826_b, false);
/* 1901 */           func_175804_a(worldIn, p_74875_3_, 7, 2, 1, 7, 2, 6, field_175828_a, field_175828_a, false);
/* 1902 */           func_175804_a(worldIn, p_74875_3_, 7, 1, 1, 7, 1, 6, field_175826_b, field_175826_b, false);
/*      */         }
/*      */       
/* 1905 */       } else if (this.field_175833_o == 2) {
/*      */         
/* 1907 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
/* 1908 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
/* 1909 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
/* 1910 */         func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
/* 1911 */         func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175827_c, field_175827_c, false);
/* 1912 */         func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 7, 2, 7, field_175827_c, field_175827_c, false);
/* 1913 */         func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175827_c, field_175827_c, false);
/* 1914 */         func_175804_a(worldIn, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175827_c, field_175827_c, false);
/* 1915 */         func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
/* 1916 */         func_175804_a(worldIn, p_74875_3_, 7, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
/* 1917 */         func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
/* 1918 */         func_175804_a(worldIn, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
/* 1919 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175827_c, field_175827_c, false);
/* 1920 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175827_c, field_175827_c, false);
/* 1921 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175827_c, field_175827_c, false);
/* 1922 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175827_c, field_175827_c, false);
/*      */         
/* 1924 */         if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */         {
/* 1926 */           func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */         }
/*      */         
/* 1929 */         if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.getIndex()])
/*      */         {
/* 1931 */           func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175822_f, field_175822_f, false);
/*      */         }
/*      */         
/* 1934 */         if (this.field_175830_k.field_175966_c[EnumFacing.WEST.getIndex()])
/*      */         {
/* 1936 */           func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175822_f, field_175822_f, false);
/*      */         }
/*      */         
/* 1939 */         if (this.field_175830_k.field_175966_c[EnumFacing.EAST.getIndex()])
/*      */         {
/* 1941 */           func_175804_a(worldIn, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175822_f, field_175822_f, false);
/*      */         }
/*      */       } 
/*      */       
/* 1945 */       if (var4) {
/*      */         
/* 1947 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 3, 4, 1, 4, field_175826_b, field_175826_b, false);
/* 1948 */         func_175804_a(worldIn, p_74875_3_, 3, 2, 3, 4, 2, 4, field_175828_a, field_175828_a, false);
/* 1949 */         func_175804_a(worldIn, p_74875_3_, 3, 3, 3, 4, 3, 4, field_175826_b, field_175826_b, false);
/*      */       } 
/*      */       
/* 1952 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class SimpleTopRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00001974";
/*      */     
/*      */     public SimpleTopRoom() {}
/*      */     
/*      */     public SimpleTopRoom(EnumFacing p_i45586_1_, StructureOceanMonumentPieces.RoomDefinition p_i45586_2_, Random p_i45586_3_) {
/* 1964 */       super(1, p_i45586_1_, p_i45586_2_, 1, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1969 */       if (this.field_175830_k.field_175967_a / 25 > 0)
/*      */       {
/* 1971 */         func_175821_a(worldIn, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.getIndex()]);
/*      */       }
/*      */       
/* 1974 */       if (this.field_175830_k.field_175965_b[EnumFacing.UP.getIndex()] == null)
/*      */       {
/* 1976 */         func_175819_a(worldIn, p_74875_3_, 1, 4, 1, 6, 4, 6, field_175828_a);
/*      */       }
/*      */       
/* 1979 */       for (int var4 = 1; var4 <= 6; var4++) {
/*      */         
/* 1981 */         for (int var5 = 1; var5 <= 6; var5++) {
/*      */           
/* 1983 */           if (p_74875_2_.nextInt(3) != 0) {
/*      */             
/* 1985 */             int var6 = 2 + ((p_74875_2_.nextInt(4) == 0) ? 0 : 1);
/* 1986 */             func_175804_a(worldIn, p_74875_3_, var4, var6, var5, var4, 3, var5, Blocks.sponge.getStateFromMeta(1), Blocks.sponge.getStateFromMeta(1), false);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1991 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
/* 1992 */       func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
/* 1993 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
/* 1994 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
/* 1995 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175827_c, field_175827_c, false);
/* 1996 */       func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 7, 2, 7, field_175827_c, field_175827_c, false);
/* 1997 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175827_c, field_175827_c, false);
/* 1998 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175827_c, field_175827_c, false);
/* 1999 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
/* 2000 */       func_175804_a(worldIn, p_74875_3_, 7, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
/* 2001 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
/* 2002 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
/* 2003 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175827_c, field_175827_c, false);
/* 2004 */       func_175804_a(worldIn, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175827_c, field_175827_c, false);
/* 2005 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175827_c, field_175827_c, false);
/* 2006 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175827_c, field_175827_c, false);
/*      */       
/* 2008 */       if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.getIndex()])
/*      */       {
/* 2010 */         func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175822_f, field_175822_f, false);
/*      */       }
/*      */       
/* 2013 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchEnumFacing
/*      */   {
/* 2019 */     static final int[] field_175971_a = new int[(EnumFacing.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00001993";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 2026 */         field_175971_a[EnumFacing.NORTH.ordinal()] = 1;
/*      */       }
/* 2028 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2035 */         field_175971_a[EnumFacing.SOUTH.ordinal()] = 2;
/*      */       }
/* 2037 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2044 */         field_175971_a[EnumFacing.WEST.ordinal()] = 3;
/*      */       }
/* 2046 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WingRoom
/*      */     extends Piece
/*      */   {
/*      */     private int field_175834_o;
/*      */     
/*      */     private static final String __OBFID = "CL_00001973";
/*      */ 
/*      */     
/*      */     public WingRoom() {}
/*      */     
/*      */     public WingRoom(EnumFacing p_i45585_1_, StructureBoundingBox p_i45585_2_, int p_i45585_3_) {
/* 2062 */       super(p_i45585_1_, p_i45585_2_);
/* 2063 */       this.field_175834_o = p_i45585_3_ & 0x1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 2068 */       if (this.field_175834_o == 0) {
/*      */         int var4;
/*      */ 
/*      */         
/* 2072 */         for (var4 = 0; var4 < 4; var4++)
/*      */         {
/* 2074 */           func_175804_a(worldIn, p_74875_3_, 10 - var4, 3 - var4, 20 - var4, 12 + var4, 3 - var4, 20, field_175826_b, field_175826_b, false);
/*      */         }
/*      */         
/* 2077 */         func_175804_a(worldIn, p_74875_3_, 7, 0, 6, 15, 0, 16, field_175826_b, field_175826_b, false);
/* 2078 */         func_175804_a(worldIn, p_74875_3_, 6, 0, 6, 6, 3, 20, field_175826_b, field_175826_b, false);
/* 2079 */         func_175804_a(worldIn, p_74875_3_, 16, 0, 6, 16, 3, 20, field_175826_b, field_175826_b, false);
/* 2080 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 7, 7, 1, 20, field_175826_b, field_175826_b, false);
/* 2081 */         func_175804_a(worldIn, p_74875_3_, 15, 1, 7, 15, 1, 20, field_175826_b, field_175826_b, false);
/* 2082 */         func_175804_a(worldIn, p_74875_3_, 7, 1, 6, 9, 3, 6, field_175826_b, field_175826_b, false);
/* 2083 */         func_175804_a(worldIn, p_74875_3_, 13, 1, 6, 15, 3, 6, field_175826_b, field_175826_b, false);
/* 2084 */         func_175804_a(worldIn, p_74875_3_, 8, 1, 7, 9, 1, 7, field_175826_b, field_175826_b, false);
/* 2085 */         func_175804_a(worldIn, p_74875_3_, 13, 1, 7, 14, 1, 7, field_175826_b, field_175826_b, false);
/* 2086 */         func_175804_a(worldIn, p_74875_3_, 9, 0, 5, 13, 0, 5, field_175826_b, field_175826_b, false);
/* 2087 */         func_175804_a(worldIn, p_74875_3_, 10, 0, 7, 12, 0, 7, field_175827_c, field_175827_c, false);
/* 2088 */         func_175804_a(worldIn, p_74875_3_, 8, 0, 10, 8, 0, 12, field_175827_c, field_175827_c, false);
/* 2089 */         func_175804_a(worldIn, p_74875_3_, 14, 0, 10, 14, 0, 12, field_175827_c, field_175827_c, false);
/*      */         
/* 2091 */         for (var4 = 18; var4 >= 7; var4 -= 3) {
/*      */           
/* 2093 */           func_175811_a(worldIn, field_175825_e, 6, 3, var4, p_74875_3_);
/* 2094 */           func_175811_a(worldIn, field_175825_e, 16, 3, var4, p_74875_3_);
/*      */         } 
/*      */         
/* 2097 */         func_175811_a(worldIn, field_175825_e, 10, 0, 10, p_74875_3_);
/* 2098 */         func_175811_a(worldIn, field_175825_e, 12, 0, 10, p_74875_3_);
/* 2099 */         func_175811_a(worldIn, field_175825_e, 10, 0, 12, p_74875_3_);
/* 2100 */         func_175811_a(worldIn, field_175825_e, 12, 0, 12, p_74875_3_);
/* 2101 */         func_175811_a(worldIn, field_175825_e, 8, 3, 6, p_74875_3_);
/* 2102 */         func_175811_a(worldIn, field_175825_e, 14, 3, 6, p_74875_3_);
/* 2103 */         func_175811_a(worldIn, field_175826_b, 4, 2, 4, p_74875_3_);
/* 2104 */         func_175811_a(worldIn, field_175825_e, 4, 1, 4, p_74875_3_);
/* 2105 */         func_175811_a(worldIn, field_175826_b, 4, 0, 4, p_74875_3_);
/* 2106 */         func_175811_a(worldIn, field_175826_b, 18, 2, 4, p_74875_3_);
/* 2107 */         func_175811_a(worldIn, field_175825_e, 18, 1, 4, p_74875_3_);
/* 2108 */         func_175811_a(worldIn, field_175826_b, 18, 0, 4, p_74875_3_);
/* 2109 */         func_175811_a(worldIn, field_175826_b, 4, 2, 18, p_74875_3_);
/* 2110 */         func_175811_a(worldIn, field_175825_e, 4, 1, 18, p_74875_3_);
/* 2111 */         func_175811_a(worldIn, field_175826_b, 4, 0, 18, p_74875_3_);
/* 2112 */         func_175811_a(worldIn, field_175826_b, 18, 2, 18, p_74875_3_);
/* 2113 */         func_175811_a(worldIn, field_175825_e, 18, 1, 18, p_74875_3_);
/* 2114 */         func_175811_a(worldIn, field_175826_b, 18, 0, 18, p_74875_3_);
/* 2115 */         func_175811_a(worldIn, field_175826_b, 9, 7, 20, p_74875_3_);
/* 2116 */         func_175811_a(worldIn, field_175826_b, 13, 7, 20, p_74875_3_);
/* 2117 */         func_175804_a(worldIn, p_74875_3_, 6, 0, 21, 7, 4, 21, field_175826_b, field_175826_b, false);
/* 2118 */         func_175804_a(worldIn, p_74875_3_, 15, 0, 21, 16, 4, 21, field_175826_b, field_175826_b, false);
/* 2119 */         func_175817_a(worldIn, p_74875_3_, 11, 2, 16);
/*      */       }
/* 2121 */       else if (this.field_175834_o == 1) {
/*      */         
/* 2123 */         func_175804_a(worldIn, p_74875_3_, 9, 3, 18, 13, 3, 20, field_175826_b, field_175826_b, false);
/* 2124 */         func_175804_a(worldIn, p_74875_3_, 9, 0, 18, 9, 2, 18, field_175826_b, field_175826_b, false);
/* 2125 */         func_175804_a(worldIn, p_74875_3_, 13, 0, 18, 13, 2, 18, field_175826_b, field_175826_b, false);
/* 2126 */         byte var8 = 9;
/* 2127 */         byte var5 = 20;
/* 2128 */         byte var6 = 5;
/*      */         
/*      */         int var7;
/* 2131 */         for (var7 = 0; var7 < 2; var7++) {
/*      */           
/* 2133 */           func_175811_a(worldIn, field_175826_b, var8, var6 + 1, var5, p_74875_3_);
/* 2134 */           func_175811_a(worldIn, field_175825_e, var8, var6, var5, p_74875_3_);
/* 2135 */           func_175811_a(worldIn, field_175826_b, var8, var6 - 1, var5, p_74875_3_);
/* 2136 */           var8 = 13;
/*      */         } 
/*      */         
/* 2139 */         func_175804_a(worldIn, p_74875_3_, 7, 3, 7, 15, 3, 14, field_175826_b, field_175826_b, false);
/* 2140 */         var8 = 10;
/*      */         
/* 2142 */         for (var7 = 0; var7 < 2; var7++) {
/*      */           
/* 2144 */           func_175804_a(worldIn, p_74875_3_, var8, 0, 10, var8, 6, 10, field_175826_b, field_175826_b, false);
/* 2145 */           func_175804_a(worldIn, p_74875_3_, var8, 0, 12, var8, 6, 12, field_175826_b, field_175826_b, false);
/* 2146 */           func_175811_a(worldIn, field_175825_e, var8, 0, 10, p_74875_3_);
/* 2147 */           func_175811_a(worldIn, field_175825_e, var8, 0, 12, p_74875_3_);
/* 2148 */           func_175811_a(worldIn, field_175825_e, var8, 4, 10, p_74875_3_);
/* 2149 */           func_175811_a(worldIn, field_175825_e, var8, 4, 12, p_74875_3_);
/* 2150 */           var8 = 12;
/*      */         } 
/*      */         
/* 2153 */         var8 = 8;
/*      */         
/* 2155 */         for (var7 = 0; var7 < 2; var7++) {
/*      */           
/* 2157 */           func_175804_a(worldIn, p_74875_3_, var8, 0, 7, var8, 2, 7, field_175826_b, field_175826_b, false);
/* 2158 */           func_175804_a(worldIn, p_74875_3_, var8, 0, 14, var8, 2, 14, field_175826_b, field_175826_b, false);
/* 2159 */           var8 = 14;
/*      */         } 
/*      */         
/* 2162 */         func_175804_a(worldIn, p_74875_3_, 8, 3, 8, 8, 3, 13, field_175827_c, field_175827_c, false);
/* 2163 */         func_175804_a(worldIn, p_74875_3_, 14, 3, 8, 14, 3, 13, field_175827_c, field_175827_c, false);
/* 2164 */         func_175817_a(worldIn, p_74875_3_, 11, 5, 13);
/*      */       } 
/*      */       
/* 2167 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static class XDoubleRoomFitHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001992";
/*      */     
/*      */     private XDoubleRoomFitHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/* 2179 */       return (p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()]).field_175963_d);
/*      */     }
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/* 2184 */       p_175968_2_.field_175963_d = true;
/* 2185 */       (p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()]).field_175963_d = true;
/* 2186 */       return new StructureOceanMonumentPieces.DoubleXRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     XDoubleRoomFitHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45606_1_) {
/* 2191 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class XYDoubleRoomFitHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001991";
/*      */     
/*      */     private XYDoubleRoomFitHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/* 2203 */       if (p_175969_1_.field_175966_c[EnumFacing.EAST.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()]).field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d) {
/*      */         
/* 2205 */         StructureOceanMonumentPieces.RoomDefinition var2 = p_175969_1_.field_175965_b[EnumFacing.EAST.getIndex()];
/* 2206 */         return (var2.field_175966_c[EnumFacing.UP.getIndex()] && !(var2.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d);
/*      */       } 
/*      */ 
/*      */       
/* 2210 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/* 2216 */       p_175968_2_.field_175963_d = true;
/* 2217 */       (p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()]).field_175963_d = true;
/* 2218 */       (p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/* 2219 */       ((p_175968_2_.field_175965_b[EnumFacing.EAST.getIndex()]).field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/* 2220 */       return new StructureOceanMonumentPieces.DoubleXYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     XYDoubleRoomFitHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45605_1_) {
/* 2225 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class YDoubleRoomFitHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001990";
/*      */     
/*      */     private YDoubleRoomFitHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/* 2237 */       return (p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d);
/*      */     }
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/* 2242 */       p_175968_2_.field_175963_d = true;
/* 2243 */       (p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/* 2244 */       return new StructureOceanMonumentPieces.DoubleYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     YDoubleRoomFitHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45604_1_) {
/* 2249 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class YZDoubleRoomFitHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001989";
/*      */     
/*      */     private YZDoubleRoomFitHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/* 2261 */       if (p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d) {
/*      */         
/* 2263 */         StructureOceanMonumentPieces.RoomDefinition var2 = p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()];
/* 2264 */         return (var2.field_175966_c[EnumFacing.UP.getIndex()] && !(var2.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d);
/*      */       } 
/*      */ 
/*      */       
/* 2268 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/* 2274 */       p_175968_2_.field_175963_d = true;
/* 2275 */       (p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d = true;
/* 2276 */       (p_175968_2_.field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/* 2277 */       ((p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175965_b[EnumFacing.UP.getIndex()]).field_175963_d = true;
/* 2278 */       return new StructureOceanMonumentPieces.DoubleYZRoom(p_175968_1_, p_175968_2_, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     YZDoubleRoomFitHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45603_1_) {
/* 2283 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class ZDoubleRoomFitHelper
/*      */     implements MonumentRoomFitHelper
/*      */   {
/*      */     private static final String __OBFID = "CL_00001988";
/*      */     
/*      */     private ZDoubleRoomFitHelper() {}
/*      */     
/*      */     public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
/* 2295 */       return (p_175969_1_.field_175966_c[EnumFacing.NORTH.getIndex()] && !(p_175969_1_.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d);
/*      */     }
/*      */ 
/*      */     
/*      */     public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
/* 2300 */       StructureOceanMonumentPieces.RoomDefinition var4 = p_175968_2_;
/*      */       
/* 2302 */       if (!p_175968_2_.field_175966_c[EnumFacing.NORTH.getIndex()] || (p_175968_2_.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d)
/*      */       {
/* 2304 */         var4 = p_175968_2_.field_175965_b[EnumFacing.SOUTH.getIndex()];
/*      */       }
/*      */       
/* 2307 */       var4.field_175963_d = true;
/* 2308 */       (var4.field_175965_b[EnumFacing.NORTH.getIndex()]).field_175963_d = true;
/* 2309 */       return new StructureOceanMonumentPieces.DoubleZRoom(p_175968_1_, var4, p_175968_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     ZDoubleRoomFitHelper(StructureOceanMonumentPieces.SwitchEnumFacing p_i45602_1_) {
/* 2314 */       this();
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureOceanMonumentPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */