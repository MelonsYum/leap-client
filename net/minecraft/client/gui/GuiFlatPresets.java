/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.FlatGeneratorInfo;
/*     */ import net.minecraft.world.gen.FlatLayerInfo;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiFlatPresets extends GuiScreen {
/*  26 */   private static final List field_146431_f = Lists.newArrayList();
/*     */   
/*     */   private final GuiCreateFlatWorld field_146432_g;
/*     */   private String field_146438_h;
/*     */   private String field_146439_i;
/*     */   private String field_146436_r;
/*     */   private ListSlot field_146435_s;
/*     */   private GuiButton field_146434_t;
/*     */   private GuiTextField field_146433_u;
/*     */   private static final String __OBFID = "CL_00000704";
/*     */   
/*     */   public GuiFlatPresets(GuiCreateFlatWorld p_i46318_1_) {
/*  38 */     this.field_146432_g = p_i46318_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  46 */     this.buttonList.clear();
/*  47 */     Keyboard.enableRepeatEvents(true);
/*  48 */     this.field_146438_h = I18n.format("createWorld.customize.presets.title", new Object[0]);
/*  49 */     this.field_146439_i = I18n.format("createWorld.customize.presets.share", new Object[0]);
/*  50 */     this.field_146436_r = I18n.format("createWorld.customize.presets.list", new Object[0]);
/*  51 */     this.field_146433_u = new GuiTextField(2, fontRendererObj, 50, 40, width - 100, 20);
/*  52 */     this.field_146435_s = new ListSlot();
/*  53 */     this.field_146433_u.setMaxStringLength(1230);
/*  54 */     this.field_146433_u.setText(this.field_146432_g.func_146384_e());
/*  55 */     this.buttonList.add(this.field_146434_t = new GuiButton(0, width / 2 - 155, height - 28, 150, 20, I18n.format("createWorld.customize.presets.select", new Object[0])));
/*  56 */     this.buttonList.add(new GuiButton(1, width / 2 + 5, height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  57 */     func_146426_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  65 */     super.handleMouseInput();
/*  66 */     this.field_146435_s.func_178039_p();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  74 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/*  82 */     this.field_146433_u.mouseClicked(mouseX, mouseY, mouseButton);
/*  83 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  92 */     if (!this.field_146433_u.textboxKeyTyped(typedChar, keyCode))
/*     */     {
/*  94 */       super.keyTyped(typedChar, keyCode);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 100 */     if (button.id == 0 && func_146430_p()) {
/*     */       
/* 102 */       this.field_146432_g.func_146383_a(this.field_146433_u.getText());
/* 103 */       this.mc.displayGuiScreen(this.field_146432_g);
/*     */     }
/* 105 */     else if (button.id == 1) {
/*     */       
/* 107 */       this.mc.displayGuiScreen(this.field_146432_g);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 116 */     drawDefaultBackground();
/* 117 */     this.field_146435_s.drawScreen(mouseX, mouseY, partialTicks);
/* 118 */     drawCenteredString(fontRendererObj, this.field_146438_h, (width / 2), 8.0F, 16777215);
/* 119 */     drawString(fontRendererObj, this.field_146439_i, 50, 30, 10526880);
/* 120 */     drawString(fontRendererObj, this.field_146436_r, 50, 70, 10526880);
/* 121 */     this.field_146433_u.drawTextBox();
/* 122 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 130 */     this.field_146433_u.updateCursorCounter();
/* 131 */     super.updateScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146426_g() {
/* 136 */     boolean var1 = func_146430_p();
/* 137 */     this.field_146434_t.enabled = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_146430_p() {
/* 142 */     return !((this.field_146435_s.field_148175_k <= -1 || this.field_146435_s.field_148175_k >= field_146431_f.size()) && this.field_146433_u.getText().length() <= 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_146425_a(String p_146425_0_, Item p_146425_1_, BiomeGenBase p_146425_2_, FlatLayerInfo... p_146425_3_) {
/* 147 */     func_175354_a(p_146425_0_, p_146425_1_, 0, p_146425_2_, (List)null, p_146425_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_146421_a(String p_146421_0_, Item p_146421_1_, BiomeGenBase p_146421_2_, List p_146421_3_, FlatLayerInfo... p_146421_4_) {
/* 152 */     func_175354_a(p_146421_0_, p_146421_1_, 0, p_146421_2_, p_146421_3_, p_146421_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_175354_a(String p_175354_0_, Item p_175354_1_, int p_175354_2_, BiomeGenBase p_175354_3_, List p_175354_4_, FlatLayerInfo... p_175354_5_) {
/* 157 */     FlatGeneratorInfo var6 = new FlatGeneratorInfo();
/*     */     
/* 159 */     for (int var7 = p_175354_5_.length - 1; var7 >= 0; var7--)
/*     */     {
/* 161 */       var6.getFlatLayers().add(p_175354_5_[var7]);
/*     */     }
/*     */     
/* 164 */     var6.setBiome(p_175354_3_.biomeID);
/* 165 */     var6.func_82645_d();
/*     */     
/* 167 */     if (p_175354_4_ != null) {
/*     */       
/* 169 */       Iterator<String> var9 = p_175354_4_.iterator();
/*     */       
/* 171 */       while (var9.hasNext()) {
/*     */         
/* 173 */         String var8 = var9.next();
/* 174 */         var6.getWorldFeatures().put(var8, Maps.newHashMap());
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     field_146431_f.add(new LayerItem(p_175354_1_, p_175354_2_, p_175354_0_, var6.toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 183 */     func_146421_a("Classic Flat", Item.getItemFromBlock((Block)Blocks.grass), BiomeGenBase.plains, Arrays.asList(new String[] { "village" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, (Block)Blocks.grass), new FlatLayerInfo(2, Blocks.dirt), new FlatLayerInfo(1, Blocks.bedrock) });
/* 184 */     func_146421_a("Tunnelers' Dream", Item.getItemFromBlock(Blocks.stone), BiomeGenBase.extremeHills, Arrays.asList(new String[] { "biome_1", "dungeon", "decoration", "stronghold", "mineshaft" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, (Block)Blocks.grass), new FlatLayerInfo(5, Blocks.dirt), new FlatLayerInfo(230, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/* 185 */     func_146421_a("Water World", Items.water_bucket, BiomeGenBase.deepOcean, Arrays.asList(new String[] { "biome_1", "oceanmonument" }, ), new FlatLayerInfo[] { new FlatLayerInfo(90, (Block)Blocks.water), new FlatLayerInfo(5, (Block)Blocks.sand), new FlatLayerInfo(5, Blocks.dirt), new FlatLayerInfo(5, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/* 186 */     func_175354_a("Overworld", Item.getItemFromBlock((Block)Blocks.tallgrass), BlockTallGrass.EnumType.GRASS.func_177044_a(), BiomeGenBase.plains, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, (Block)Blocks.grass), new FlatLayerInfo(3, Blocks.dirt), new FlatLayerInfo(59, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/* 187 */     func_146421_a("Snowy Kingdom", Item.getItemFromBlock(Blocks.snow_layer), BiomeGenBase.icePlains, Arrays.asList(new String[] { "village", "biome_1" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Blocks.snow_layer), new FlatLayerInfo(1, (Block)Blocks.grass), new FlatLayerInfo(3, Blocks.dirt), new FlatLayerInfo(59, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/* 188 */     func_146421_a("Bottomless Pit", Items.feather, BiomeGenBase.plains, Arrays.asList(new String[] { "village", "biome_1" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, (Block)Blocks.grass), new FlatLayerInfo(3, Blocks.dirt), new FlatLayerInfo(2, Blocks.cobblestone) });
/* 189 */     func_146421_a("Desert", Item.getItemFromBlock((Block)Blocks.sand), BiomeGenBase.desert, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon" }, ), new FlatLayerInfo[] { new FlatLayerInfo(8, (Block)Blocks.sand), new FlatLayerInfo(52, Blocks.sandstone), new FlatLayerInfo(3, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/* 190 */     func_146425_a("Redstone Ready", Items.redstone, BiomeGenBase.desert, new FlatLayerInfo[] { new FlatLayerInfo(52, Blocks.sandstone), new FlatLayerInfo(3, Blocks.stone), new FlatLayerInfo(1, Blocks.bedrock) });
/*     */   }
/*     */ 
/*     */   
/*     */   static class LayerItem
/*     */   {
/*     */     public Item field_148234_a;
/*     */     public int field_179037_b;
/*     */     public String field_148232_b;
/*     */     public String field_148233_c;
/*     */     private static final String __OBFID = "CL_00000705";
/*     */     
/*     */     public LayerItem(Item p_i45518_1_, int p_i45518_2_, String p_i45518_3_, String p_i45518_4_) {
/* 203 */       this.field_148234_a = p_i45518_1_;
/* 204 */       this.field_179037_b = p_i45518_2_;
/* 205 */       this.field_148232_b = p_i45518_3_;
/* 206 */       this.field_148233_c = p_i45518_4_;
/*     */     }
/*     */   }
/*     */   
/*     */   class ListSlot
/*     */     extends GuiSlot {
/* 212 */     public int field_148175_k = -1;
/*     */     
/*     */     private static final String __OBFID = "CL_00000706";
/*     */     
/*     */     public ListSlot() {
/* 217 */       super(GuiFlatPresets.this.mc, GuiFlatPresets.width, GuiFlatPresets.height, 80, GuiFlatPresets.height - 37, 24);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178054_a(int p_178054_1_, int p_178054_2_, Item p_178054_3_, int p_178054_4_) {
/* 222 */       func_148173_e(p_178054_1_ + 1, p_178054_2_ + 1);
/* 223 */       GlStateManager.enableRescaleNormal();
/* 224 */       RenderHelper.enableGUIStandardItemLighting();
/* 225 */       GuiFlatPresets.this.itemRender.func_175042_a(new ItemStack(p_178054_3_, 1, p_178054_4_), p_178054_1_ + 2, p_178054_2_ + 2);
/* 226 */       RenderHelper.disableStandardItemLighting();
/* 227 */       GlStateManager.disableRescaleNormal();
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148173_e(int p_148173_1_, int p_148173_2_) {
/* 232 */       func_148171_c(p_148173_1_, p_148173_2_, 0, 0);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148171_c(int p_148171_1_, int p_148171_2_, int p_148171_3_, int p_148171_4_) {
/* 237 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 238 */       this.mc.getTextureManager().bindTexture(Gui.statIcons);
/* 239 */       float var5 = 0.0078125F;
/* 240 */       float var6 = 0.0078125F;
/* 241 */       boolean var7 = true;
/* 242 */       boolean var8 = true;
/* 243 */       Tessellator var9 = Tessellator.getInstance();
/* 244 */       WorldRenderer var10 = var9.getWorldRenderer();
/* 245 */       var10.startDrawingQuads();
/* 246 */       var10.addVertexWithUV((p_148171_1_ + 0), (p_148171_2_ + 18), GuiFlatPresets.zLevel, ((p_148171_3_ + 0) * 0.0078125F), ((p_148171_4_ + 18) * 0.0078125F));
/* 247 */       var10.addVertexWithUV((p_148171_1_ + 18), (p_148171_2_ + 18), GuiFlatPresets.zLevel, ((p_148171_3_ + 18) * 0.0078125F), ((p_148171_4_ + 18) * 0.0078125F));
/* 248 */       var10.addVertexWithUV((p_148171_1_ + 18), (p_148171_2_ + 0), GuiFlatPresets.zLevel, ((p_148171_3_ + 18) * 0.0078125F), ((p_148171_4_ + 0) * 0.0078125F));
/* 249 */       var10.addVertexWithUV((p_148171_1_ + 0), (p_148171_2_ + 0), GuiFlatPresets.zLevel, ((p_148171_3_ + 0) * 0.0078125F), ((p_148171_4_ + 0) * 0.0078125F));
/* 250 */       var9.draw();
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 255 */       return GuiFlatPresets.field_146431_f.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 260 */       this.field_148175_k = slotIndex;
/* 261 */       GuiFlatPresets.this.func_146426_g();
/* 262 */       GuiFlatPresets.this.field_146433_u.setText(((GuiFlatPresets.LayerItem)GuiFlatPresets.field_146431_f.get(GuiFlatPresets.this.field_146435_s.field_148175_k)).field_148233_c);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 267 */       return (slotIndex == this.field_148175_k);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {}
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 274 */       GuiFlatPresets.LayerItem var7 = GuiFlatPresets.field_146431_f.get(p_180791_1_);
/* 275 */       func_178054_a(p_180791_2_, p_180791_3_, var7.field_148234_a, var7.field_179037_b);
/* 276 */       GuiFlatPresets.fontRendererObj.drawString(var7.field_148232_b, (p_180791_2_ + 18 + 5), (p_180791_3_ + 6), 16777215);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiFlatPresets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */