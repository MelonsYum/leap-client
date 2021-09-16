/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.gen.FlatGeneratorInfo;
/*     */ import net.minecraft.world.gen.FlatLayerInfo;
/*     */ 
/*     */ public class GuiCreateFlatWorld
/*     */   extends GuiScreen {
/*     */   private final GuiCreateWorld createWorldGui;
/*  21 */   private FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
/*     */   
/*     */   private String field_146393_h;
/*     */   private String field_146394_i;
/*     */   private String field_146391_r;
/*     */   private Details createFlatWorldListSlotGui;
/*     */   private GuiButton field_146389_t;
/*     */   private GuiButton field_146388_u;
/*     */   private GuiButton field_146386_v;
/*     */   private static final String __OBFID = "CL_00000687";
/*     */   
/*     */   public GuiCreateFlatWorld(GuiCreateWorld p_i1029_1_, String p_i1029_2_) {
/*  33 */     this.createWorldGui = p_i1029_1_;
/*  34 */     func_146383_a(p_i1029_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_146384_e() {
/*  39 */     return this.theFlatGeneratorInfo.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146383_a(String p_146383_1_) {
/*  44 */     this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(p_146383_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  52 */     this.buttonList.clear();
/*  53 */     this.field_146393_h = I18n.format("createWorld.customize.flat.title", new Object[0]);
/*  54 */     this.field_146394_i = I18n.format("createWorld.customize.flat.tile", new Object[0]);
/*  55 */     this.field_146391_r = I18n.format("createWorld.customize.flat.height", new Object[0]);
/*  56 */     this.createFlatWorldListSlotGui = new Details();
/*  57 */     this.buttonList.add(this.field_146389_t = new GuiButton(2, width / 2 - 154, height - 52, 100, 20, String.valueOf(I18n.format("createWorld.customize.flat.addLayer", new Object[0])) + " (NYI)"));
/*  58 */     this.buttonList.add(this.field_146388_u = new GuiButton(3, width / 2 - 50, height - 52, 100, 20, String.valueOf(I18n.format("createWorld.customize.flat.editLayer", new Object[0])) + " (NYI)"));
/*  59 */     this.buttonList.add(this.field_146386_v = new GuiButton(4, width / 2 - 155, height - 52, 150, 20, I18n.format("createWorld.customize.flat.removeLayer", new Object[0])));
/*  60 */     this.buttonList.add(new GuiButton(0, width / 2 - 155, height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
/*  61 */     this.buttonList.add(new GuiButton(5, width / 2 + 5, height - 52, 150, 20, I18n.format("createWorld.customize.presets", new Object[0])));
/*  62 */     this.buttonList.add(new GuiButton(1, width / 2 + 5, height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  63 */     this.field_146388_u.visible = false;
/*  64 */     this.theFlatGeneratorInfo.func_82645_d();
/*  65 */     func_146375_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  73 */     super.handleMouseInput();
/*  74 */     this.createFlatWorldListSlotGui.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  79 */     int var2 = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.field_148228_k - 1;
/*     */     
/*  81 */     if (button.id == 1) {
/*     */       
/*  83 */       this.mc.displayGuiScreen(this.createWorldGui);
/*     */     }
/*  85 */     else if (button.id == 0) {
/*     */       
/*  87 */       this.createWorldGui.field_146334_a = func_146384_e();
/*  88 */       this.mc.displayGuiScreen(this.createWorldGui);
/*     */     }
/*  90 */     else if (button.id == 5) {
/*     */       
/*  92 */       this.mc.displayGuiScreen(new GuiFlatPresets(this));
/*     */     }
/*  94 */     else if (button.id == 4 && func_146382_i()) {
/*     */       
/*  96 */       this.theFlatGeneratorInfo.getFlatLayers().remove(var2);
/*  97 */       this.createFlatWorldListSlotGui.field_148228_k = Math.min(this.createFlatWorldListSlotGui.field_148228_k, this.theFlatGeneratorInfo.getFlatLayers().size() - 1);
/*     */     } 
/*     */     
/* 100 */     this.theFlatGeneratorInfo.func_82645_d();
/* 101 */     func_146375_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146375_g() {
/* 106 */     boolean var1 = func_146382_i();
/* 107 */     this.field_146386_v.enabled = var1;
/* 108 */     this.field_146388_u.enabled = var1;
/* 109 */     this.field_146388_u.enabled = false;
/* 110 */     this.field_146389_t.enabled = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_146382_i() {
/* 115 */     return (this.createFlatWorldListSlotGui.field_148228_k > -1 && this.createFlatWorldListSlotGui.field_148228_k < this.theFlatGeneratorInfo.getFlatLayers().size());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 123 */     drawDefaultBackground();
/* 124 */     this.createFlatWorldListSlotGui.drawScreen(mouseX, mouseY, partialTicks);
/* 125 */     drawCenteredString(fontRendererObj, this.field_146393_h, (width / 2), 8.0F, 16777215);
/* 126 */     int var4 = width / 2 - 92 - 16;
/* 127 */     drawString(fontRendererObj, this.field_146394_i, var4, 32, 16777215);
/* 128 */     drawString(fontRendererObj, this.field_146391_r, var4 + 2 + 213 - fontRendererObj.getStringWidth(this.field_146391_r), 32, 16777215);
/* 129 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   class Details
/*     */     extends GuiSlot {
/* 134 */     public int field_148228_k = -1;
/*     */     
/*     */     private static final String __OBFID = "CL_00000688";
/*     */     
/*     */     public Details() {
/* 139 */       super(GuiCreateFlatWorld.this.mc, GuiCreateFlatWorld.width, GuiCreateFlatWorld.height, 43, GuiCreateFlatWorld.height - 60, 24);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148225_a(int p_148225_1_, int p_148225_2_, ItemStack p_148225_3_) {
/* 144 */       func_148226_e(p_148225_1_ + 1, p_148225_2_ + 1);
/* 145 */       GlStateManager.enableRescaleNormal();
/*     */       
/* 147 */       if (p_148225_3_ != null && p_148225_3_.getItem() != null) {
/*     */         
/* 149 */         RenderHelper.enableGUIStandardItemLighting();
/* 150 */         GuiCreateFlatWorld.this.itemRender.func_175042_a(p_148225_3_, p_148225_1_ + 2, p_148225_2_ + 2);
/* 151 */         RenderHelper.disableStandardItemLighting();
/*     */       } 
/*     */       
/* 154 */       GlStateManager.disableRescaleNormal();
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148226_e(int p_148226_1_, int p_148226_2_) {
/* 159 */       func_148224_c(p_148226_1_, p_148226_2_, 0, 0);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_148224_c(int p_148224_1_, int p_148224_2_, int p_148224_3_, int p_148224_4_) {
/* 164 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 165 */       this.mc.getTextureManager().bindTexture(Gui.statIcons);
/* 166 */       float var5 = 0.0078125F;
/* 167 */       float var6 = 0.0078125F;
/* 168 */       boolean var7 = true;
/* 169 */       boolean var8 = true;
/* 170 */       Tessellator var9 = Tessellator.getInstance();
/* 171 */       WorldRenderer var10 = var9.getWorldRenderer();
/* 172 */       var10.startDrawingQuads();
/* 173 */       var10.addVertexWithUV((p_148224_1_ + 0), (p_148224_2_ + 18), GuiCreateFlatWorld.zLevel, ((p_148224_3_ + 0) * 0.0078125F), ((p_148224_4_ + 18) * 0.0078125F));
/* 174 */       var10.addVertexWithUV((p_148224_1_ + 18), (p_148224_2_ + 18), GuiCreateFlatWorld.zLevel, ((p_148224_3_ + 18) * 0.0078125F), ((p_148224_4_ + 18) * 0.0078125F));
/* 175 */       var10.addVertexWithUV((p_148224_1_ + 18), (p_148224_2_ + 0), GuiCreateFlatWorld.zLevel, ((p_148224_3_ + 18) * 0.0078125F), ((p_148224_4_ + 0) * 0.0078125F));
/* 176 */       var10.addVertexWithUV((p_148224_1_ + 0), (p_148224_2_ + 0), GuiCreateFlatWorld.zLevel, ((p_148224_3_ + 0) * 0.0078125F), ((p_148224_4_ + 0) * 0.0078125F));
/* 177 */       var9.draw();
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 182 */       return GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 187 */       this.field_148228_k = slotIndex;
/* 188 */       GuiCreateFlatWorld.this.func_146375_g();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 193 */       return (slotIndex == this.field_148228_k);
/*     */     }
/*     */     
/*     */     protected void drawBackground() {}
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/*     */       String var13;
/* 200 */       FlatLayerInfo var7 = GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().get(GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size() - p_180791_1_ - 1);
/* 201 */       IBlockState var8 = var7.func_175900_c();
/* 202 */       Block var9 = var8.getBlock();
/* 203 */       Item var10 = Item.getItemFromBlock(var9);
/* 204 */       ItemStack var11 = (var9 != Blocks.air && var10 != null) ? new ItemStack(var10, 1, var9.getMetaFromState(var8)) : null;
/* 205 */       String var12 = (var11 == null) ? "Air" : var10.getItemStackDisplayName(var11);
/*     */       
/* 207 */       if (var10 == null) {
/*     */         
/* 209 */         if (var9 != Blocks.water && var9 != Blocks.flowing_water) {
/*     */           
/* 211 */           if (var9 == Blocks.lava || var9 == Blocks.flowing_lava)
/*     */           {
/* 213 */             var10 = Items.lava_bucket;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 218 */           var10 = Items.water_bucket;
/*     */         } 
/*     */         
/* 221 */         if (var10 != null) {
/*     */           
/* 223 */           var11 = new ItemStack(var10, 1, var9.getMetaFromState(var8));
/* 224 */           var12 = var9.getLocalizedName();
/*     */         } 
/*     */       } 
/*     */       
/* 228 */       func_148225_a(p_180791_2_, p_180791_3_, var11);
/* 229 */       GuiCreateFlatWorld.fontRendererObj.drawString(var12, (p_180791_2_ + 18 + 5), (p_180791_3_ + 3), 16777215);
/*     */ 
/*     */       
/* 232 */       if (p_180791_1_ == 0) {
/*     */         
/* 234 */         var13 = I18n.format("createWorld.customize.flat.layer.top", new Object[] { Integer.valueOf(var7.getLayerCount()) });
/*     */       }
/* 236 */       else if (p_180791_1_ == GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size() - 1) {
/*     */         
/* 238 */         var13 = I18n.format("createWorld.customize.flat.layer.bottom", new Object[] { Integer.valueOf(var7.getLayerCount()) });
/*     */       }
/*     */       else {
/*     */         
/* 242 */         var13 = I18n.format("createWorld.customize.flat.layer", new Object[] { Integer.valueOf(var7.getLayerCount()) });
/*     */       } 
/*     */       
/* 245 */       GuiCreateFlatWorld.fontRendererObj.drawString(var13, (p_180791_2_ + 2 + 213 - GuiCreateFlatWorld.fontRendererObj.getStringWidth(var13)), (p_180791_3_ + 3), 16777215);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getScrollBarX() {
/* 250 */       return this.width - 70;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiCreateFlatWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */