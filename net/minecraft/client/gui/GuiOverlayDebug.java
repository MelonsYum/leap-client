/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.ClientBrandRetriever;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiOverlayDebug
/*     */   extends Gui
/*     */ {
/*     */   private final Minecraft mc;
/*     */   private final FontRenderer fontRenderer;
/*     */   private static final String __OBFID = "CL_00001956";
/*     */   
/*     */   public GuiOverlayDebug(Minecraft mc) {
/*  40 */     this.mc = mc;
/*  41 */     this.fontRenderer = mc.fontRendererObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175237_a(ScaledResolution scaledResolutionIn) {
/*  46 */     this.mc.mcProfiler.startSection("debug");
/*  47 */     GlStateManager.pushMatrix();
/*  48 */     func_180798_a();
/*  49 */     func_175239_b(scaledResolutionIn);
/*  50 */     GlStateManager.popMatrix();
/*  51 */     this.mc.mcProfiler.endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175236_d() {
/*  56 */     return !(!this.mc.thePlayer.func_175140_cp() && !this.mc.gameSettings.field_178879_v);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180798_a() {
/*  61 */     List<String> var1 = call();
/*     */     
/*  63 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/*     */       
/*  65 */       String var3 = var1.get(var2);
/*     */       
/*  67 */       if (!Strings.isNullOrEmpty(var3)) {
/*     */         
/*  69 */         int var4 = this.fontRenderer.FONT_HEIGHT;
/*  70 */         int var5 = this.fontRenderer.getStringWidth(var3);
/*  71 */         boolean var6 = true;
/*  72 */         int var7 = 2 + var4 * var2;
/*  73 */         drawRect(1.0D, (var7 - 1), (2 + var5 + 1), (var7 + var4 - 1), -1873784752);
/*  74 */         this.fontRenderer.drawString(var3, 2.0D, var7, 14737632);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175239_b(ScaledResolution p_175239_1_) {
/*  81 */     List<String> var2 = func_175238_c();
/*     */     
/*  83 */     for (int var3 = 0; var3 < var2.size(); var3++) {
/*     */       
/*  85 */       String var4 = var2.get(var3);
/*     */       
/*  87 */       if (!Strings.isNullOrEmpty(var4)) {
/*     */         
/*  89 */         int var5 = this.fontRenderer.FONT_HEIGHT;
/*  90 */         int var6 = this.fontRenderer.getStringWidth(var4);
/*  91 */         int var7 = p_175239_1_.getScaledWidth() - 2 - var6;
/*  92 */         int var8 = 2 + var5 * var3;
/*  93 */         drawRect((var7 - 1), (var8 - 1), (var7 + var6 + 1), (var8 + var5 - 1), -1873784752);
/*  94 */         this.fontRenderer.drawString(var4, var7, var8, 14737632);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected List call() {
/* 101 */     BlockPos var1 = new BlockPos((this.mc.func_175606_aa()).posX, (this.mc.func_175606_aa().getEntityBoundingBox()).minY, (this.mc.func_175606_aa()).posZ);
/*     */     
/* 103 */     if (func_175236_d())
/*     */     {
/* 105 */       return Lists.newArrayList((Object[])new String[] { "Minecraft 1.8 (" + this.mc.func_175600_c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.mc.debug, this.mc.renderGlobal.getDebugInfoRenders(), this.mc.renderGlobal.getDebugInfoEntities(), "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities(), this.mc.theWorld.getProviderName(), "", String.format("Chunk-relative: %d %d %d", new Object[] { Integer.valueOf(var1.getX() & 0xF), Integer.valueOf(var1.getY() & 0xF), Integer.valueOf(var1.getZ() & 0xF) }) });
/*     */     }
/*     */ 
/*     */     
/* 109 */     Entity var2 = this.mc.func_175606_aa();
/* 110 */     EnumFacing var3 = var2.func_174811_aO();
/* 111 */     String var4 = "Invalid";
/*     */     
/* 113 */     switch (SwitchEnumFacing.field_178907_a[var3.ordinal()]) {
/*     */       
/*     */       case 1:
/* 116 */         var4 = "Towards negative Z";
/*     */         break;
/*     */       
/*     */       case 2:
/* 120 */         var4 = "Towards positive Z";
/*     */         break;
/*     */       
/*     */       case 3:
/* 124 */         var4 = "Towards negative X";
/*     */         break;
/*     */       
/*     */       case 4:
/* 128 */         var4 = "Towards positive X";
/*     */         break;
/*     */     } 
/* 131 */     ArrayList<String> var5 = Lists.newArrayList((Object[])new String[] { "Minecraft 1.8 (" + this.mc.func_175600_c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.mc.debug, this.mc.renderGlobal.getDebugInfoRenders(), this.mc.renderGlobal.getDebugInfoEntities(), "P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities(), this.mc.theWorld.getProviderName(), "", String.format("XYZ: %.3f / %.5f / %.3f", new Object[] { Double.valueOf((this.mc.func_175606_aa()).posX), Double.valueOf((this.mc.func_175606_aa().getEntityBoundingBox()).minY), Double.valueOf((this.mc.func_175606_aa()).posZ) }), String.format("Block: %d %d %d", new Object[] { Integer.valueOf(var1.getX()), Integer.valueOf(var1.getY()), Integer.valueOf(var1.getZ()) }), String.format("Chunk: %d %d %d in %d %d %d", new Object[] { Integer.valueOf(var1.getX() & 0xF), Integer.valueOf(var1.getY() & 0xF), Integer.valueOf(var1.getZ() & 0xF), Integer.valueOf(var1.getX() >> 4), Integer.valueOf(var1.getY() >> 4), Integer.valueOf(var1.getZ() >> 4) }), String.format("Facing: %s (%s) (%.1f / %.1f)", new Object[] { var3, var4, Float.valueOf(MathHelper.wrapAngleTo180_float(var2.rotationYaw)), Float.valueOf(MathHelper.wrapAngleTo180_float(var2.rotationPitch)) }) });
/*     */     
/* 133 */     if (this.mc.theWorld != null && this.mc.theWorld.isBlockLoaded(var1)) {
/*     */       
/* 135 */       Chunk var9 = this.mc.theWorld.getChunkFromBlockCoords(var1);
/* 136 */       var5.add("Biome: " + (var9.getBiome(var1, this.mc.theWorld.getWorldChunkManager())).biomeName);
/* 137 */       var5.add("Light: " + var9.setLight(var1, 0) + " (" + var9.getLightFor(EnumSkyBlock.SKY, var1) + " sky, " + var9.getLightFor(EnumSkyBlock.BLOCK, var1) + " block)");
/* 138 */       DifficultyInstance var7 = this.mc.theWorld.getDifficultyForLocation(var1);
/*     */       
/* 140 */       if (this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null) {
/*     */         
/* 142 */         EntityPlayerMP var8 = this.mc.getIntegratedServer().getConfigurationManager().func_177451_a(this.mc.thePlayer.getUniqueID());
/*     */         
/* 144 */         if (var8 != null)
/*     */         {
/* 146 */           var7 = var8.worldObj.getDifficultyForLocation(new BlockPos((Entity)var8));
/*     */         }
/*     */       } 
/*     */       
/* 150 */       var5.add(String.format("Local Difficulty: %.2f (Day %d)", new Object[] { Float.valueOf(var7.func_180168_b()), Long.valueOf(this.mc.theWorld.getWorldTime() / 24000L) }));
/*     */     } 
/*     */     
/* 153 */     if (this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive())
/*     */     {
/* 155 */       var5.add("Shader: " + this.mc.entityRenderer.getShaderGroup().getShaderGroupName());
/*     */     }
/*     */     
/* 158 */     if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.func_178782_a() != null) {
/*     */       
/* 160 */       BlockPos var91 = this.mc.objectMouseOver.func_178782_a();
/* 161 */       var5.add(String.format("Looking at: %d %d %d", new Object[] { Integer.valueOf(var91.getX()), Integer.valueOf(var91.getY()), Integer.valueOf(var91.getZ()) }));
/*     */     } 
/*     */     
/* 164 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List func_175238_c() {
/* 170 */     long var1 = Runtime.getRuntime().maxMemory();
/* 171 */     long var3 = Runtime.getRuntime().totalMemory();
/* 172 */     long var5 = Runtime.getRuntime().freeMemory();
/* 173 */     long var7 = var3 - var5;
/* 174 */     ArrayList<String> var9 = Lists.newArrayList((Object[])new String[] { String.format("Java: %s %dbit", new Object[] { System.getProperty("java.version"), Integer.valueOf(this.mc.isJava64bit() ? 64 : 32) }), String.format("Mem: % 2d%% %03d/%03dMB", new Object[] { Long.valueOf(var7 * 100L / var1), Long.valueOf(func_175240_a(var7)), Long.valueOf(func_175240_a(var1)) }), String.format("Allocated: % 2d%% %03dMB", new Object[] { Long.valueOf(var3 * 100L / var1), Long.valueOf(func_175240_a(var3)) }), "", String.format("Display: %dx%d (%s)", new Object[] { Integer.valueOf(Display.getWidth()), Integer.valueOf(Display.getHeight()), GL11.glGetString(7936) }), GL11.glGetString(7937), GL11.glGetString(7938) });
/*     */     
/* 176 */     if (Reflector.FMLCommonHandler_getBrandings.exists()) {
/*     */       
/* 178 */       Object var10 = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
/* 179 */       var9.add("");
/* 180 */       var9.addAll((Collection<? extends String>)Reflector.call(var10, Reflector.FMLCommonHandler_getBrandings, new Object[] { Boolean.valueOf(false) }));
/*     */     } 
/*     */     
/* 183 */     if (func_175236_d())
/*     */     {
/* 185 */       return var9;
/*     */     }
/*     */ 
/*     */     
/* 189 */     if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.func_178782_a() != null) {
/*     */       
/* 191 */       BlockPos var101 = this.mc.objectMouseOver.func_178782_a();
/* 192 */       IBlockState var11 = this.mc.theWorld.getBlockState(var101);
/*     */       
/* 194 */       if (this.mc.theWorld.getWorldType() != WorldType.DEBUG_WORLD)
/*     */       {
/* 196 */         var11 = var11.getBlock().getActualState(var11, (IBlockAccess)this.mc.theWorld, var101);
/*     */       }
/*     */       
/* 199 */       var9.add("");
/* 200 */       var9.add(String.valueOf(Block.blockRegistry.getNameForObject(var11.getBlock())));
/*     */ 
/*     */ 
/*     */       
/* 204 */       for (UnmodifiableIterator var12 = var11.getProperties().entrySet().iterator(); var12.hasNext(); var9.add(String.valueOf(((IProperty)var13.getKey()).getName()) + ": " + var14)) {
/*     */         
/* 206 */         Map.Entry var13 = (Map.Entry)var12.next();
/* 207 */         String var14 = ((Comparable)var13.getValue()).toString();
/*     */         
/* 209 */         if (var13.getValue() == Boolean.TRUE) {
/*     */           
/* 211 */           var14 = EnumChatFormatting.GREEN + var14;
/*     */         }
/* 213 */         else if (var13.getValue() == Boolean.FALSE) {
/*     */           
/* 215 */           var14 = EnumChatFormatting.RED + var14;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     return var9;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long func_175240_a(long p_175240_0_) {
/* 226 */     return p_175240_0_ / 1024L / 1024L;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 231 */     static final int[] field_178907_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001955";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 238 */         field_178907_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 240 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 247 */         field_178907_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 249 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 256 */         field_178907_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 258 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 265 */         field_178907_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 267 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiOverlayDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */