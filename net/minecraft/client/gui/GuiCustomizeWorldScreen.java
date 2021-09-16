/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.primitives.Floats;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.ChunkProviderSettings;
/*     */ 
/*     */ public class GuiCustomizeWorldScreen
/*     */   extends GuiScreen implements GuiSlider.FormatHelper, GuiPageButtonList.GuiResponder {
/*     */   private GuiCreateWorld field_175343_i;
/*  18 */   protected String field_175341_a = "Customize World Settings";
/*  19 */   protected String field_175333_f = "Page 1 of 3";
/*  20 */   protected String field_175335_g = "Basic Settings";
/*  21 */   protected String[] field_175342_h = new String[4];
/*     */   private GuiPageButtonList field_175349_r;
/*     */   private GuiButton field_175348_s;
/*     */   private GuiButton field_175347_t;
/*     */   private GuiButton field_175346_u;
/*     */   private GuiButton field_175345_v;
/*     */   private GuiButton field_175344_w;
/*     */   private GuiButton field_175352_x;
/*     */   private GuiButton field_175351_y;
/*     */   private GuiButton field_175350_z;
/*     */   private boolean field_175338_A = false;
/*  32 */   private int field_175339_B = 0;
/*     */   private boolean field_175340_C = false;
/*  34 */   private Predicate field_175332_D = new Predicate()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001933";
/*     */       
/*     */       public boolean func_178956_a(String p_178956_1_) {
/*  39 */         Float var2 = Floats.tryParse(p_178956_1_);
/*  40 */         return !(p_178956_1_.length() != 0 && (var2 == null || !Floats.isFinite(var2.floatValue()) || var2.floatValue() < 0.0F));
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_) {
/*  44 */         return func_178956_a((String)p_apply_1_);
/*     */       }
/*     */     };
/*  47 */   private ChunkProviderSettings.Factory field_175334_E = new ChunkProviderSettings.Factory();
/*     */   private ChunkProviderSettings.Factory field_175336_F;
/*  49 */   private Random field_175337_G = new Random();
/*     */   
/*     */   private static final String __OBFID = "CL_00001934";
/*     */   
/*     */   public GuiCustomizeWorldScreen(GuiScreen p_i45521_1_, String p_i45521_2_) {
/*  54 */     this.field_175343_i = (GuiCreateWorld)p_i45521_1_;
/*  55 */     func_175324_a(p_i45521_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  63 */     this.field_175341_a = I18n.format("options.customizeTitle", new Object[0]);
/*  64 */     this.buttonList.clear();
/*  65 */     this.buttonList.add(this.field_175345_v = new GuiButton(302, 20, 5, 80, 20, I18n.format("createWorld.customize.custom.prev", new Object[0])));
/*  66 */     this.buttonList.add(this.field_175344_w = new GuiButton(303, width - 100, 5, 80, 20, I18n.format("createWorld.customize.custom.next", new Object[0])));
/*  67 */     this.buttonList.add(this.field_175346_u = new GuiButton(304, width / 2 - 187, height - 27, 90, 20, I18n.format("createWorld.customize.custom.defaults", new Object[0])));
/*  68 */     this.buttonList.add(this.field_175347_t = new GuiButton(301, width / 2 - 92, height - 27, 90, 20, I18n.format("createWorld.customize.custom.randomize", new Object[0])));
/*  69 */     this.buttonList.add(this.field_175350_z = new GuiButton(305, width / 2 + 3, height - 27, 90, 20, I18n.format("createWorld.customize.custom.presets", new Object[0])));
/*  70 */     this.buttonList.add(this.field_175348_s = new GuiButton(300, width / 2 + 98, height - 27, 90, 20, I18n.format("gui.done", new Object[0])));
/*  71 */     this.field_175352_x = new GuiButton(306, width / 2 - 55, 160, 50, 20, I18n.format("gui.yes", new Object[0]));
/*  72 */     this.field_175352_x.visible = false;
/*  73 */     this.buttonList.add(this.field_175352_x);
/*  74 */     this.field_175351_y = new GuiButton(307, width / 2 + 5, 160, 50, 20, I18n.format("gui.no", new Object[0]));
/*  75 */     this.field_175351_y.visible = false;
/*  76 */     this.buttonList.add(this.field_175351_y);
/*  77 */     func_175325_f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  85 */     super.handleMouseInput();
/*  86 */     this.field_175349_r.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175325_f() {
/*  91 */     GuiPageButtonList.GuiListEntry[] var1 = { new GuiPageButtonList.GuiSlideEntry(160, I18n.format("createWorld.customize.custom.seaLevel", new Object[0]), true, this, 1.0F, 255.0F, this.field_175336_F.field_177929_r), new GuiPageButtonList.GuiButtonEntry(148, I18n.format("createWorld.customize.custom.useCaves", new Object[0]), true, this.field_175336_F.field_177927_s), new GuiPageButtonList.GuiButtonEntry(150, I18n.format("createWorld.customize.custom.useStrongholds", new Object[0]), true, this.field_175336_F.field_177921_v), new GuiPageButtonList.GuiButtonEntry(151, I18n.format("createWorld.customize.custom.useVillages", new Object[0]), true, this.field_175336_F.field_177919_w), new GuiPageButtonList.GuiButtonEntry(152, I18n.format("createWorld.customize.custom.useMineShafts", new Object[0]), true, this.field_175336_F.field_177944_x), new GuiPageButtonList.GuiButtonEntry(153, I18n.format("createWorld.customize.custom.useTemples", new Object[0]), true, this.field_175336_F.field_177942_y), new GuiPageButtonList.GuiButtonEntry(210, I18n.format("createWorld.customize.custom.useMonuments", new Object[0]), true, this.field_175336_F.field_177940_z), new GuiPageButtonList.GuiButtonEntry(154, I18n.format("createWorld.customize.custom.useRavines", new Object[0]), true, this.field_175336_F.field_177870_A), new GuiPageButtonList.GuiButtonEntry(149, I18n.format("createWorld.customize.custom.useDungeons", new Object[0]), true, this.field_175336_F.field_177925_t), new GuiPageButtonList.GuiSlideEntry(157, I18n.format("createWorld.customize.custom.dungeonChance", new Object[0]), true, this, 1.0F, 100.0F, this.field_175336_F.field_177923_u), new GuiPageButtonList.GuiButtonEntry(155, I18n.format("createWorld.customize.custom.useWaterLakes", new Object[0]), true, this.field_175336_F.field_177871_B), new GuiPageButtonList.GuiSlideEntry(158, I18n.format("createWorld.customize.custom.waterLakeChance", new Object[0]), true, this, 1.0F, 100.0F, this.field_175336_F.field_177872_C), new GuiPageButtonList.GuiButtonEntry(156, I18n.format("createWorld.customize.custom.useLavaLakes", new Object[0]), true, this.field_175336_F.field_177866_D), new GuiPageButtonList.GuiSlideEntry(159, I18n.format("createWorld.customize.custom.lavaLakeChance", new Object[0]), true, this, 10.0F, 100.0F, this.field_175336_F.field_177867_E), new GuiPageButtonList.GuiButtonEntry(161, I18n.format("createWorld.customize.custom.useLavaOceans", new Object[0]), true, this.field_175336_F.field_177868_F), new GuiPageButtonList.GuiSlideEntry(162, I18n.format("createWorld.customize.custom.fixedBiome", new Object[0]), true, this, -1.0F, 37.0F, this.field_175336_F.field_177869_G), new GuiPageButtonList.GuiSlideEntry(163, I18n.format("createWorld.customize.custom.biomeSize", new Object[0]), true, this, 1.0F, 8.0F, this.field_175336_F.field_177877_H), new GuiPageButtonList.GuiSlideEntry(164, I18n.format("createWorld.customize.custom.riverSize", new Object[0]), true, this, 1.0F, 5.0F, this.field_175336_F.field_177878_I) };
/*  92 */     GuiPageButtonList.GuiListEntry[] var2 = { new GuiPageButtonList.GuiLabelEntry(416, I18n.format("tile.dirt.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(165, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177879_J), new GuiPageButtonList.GuiSlideEntry(166, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177880_K), new GuiPageButtonList.GuiSlideEntry(167, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177873_L), new GuiPageButtonList.GuiSlideEntry(168, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177874_M), new GuiPageButtonList.GuiLabelEntry(417, I18n.format("tile.gravel.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(169, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177875_N), new GuiPageButtonList.GuiSlideEntry(170, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177876_O), new GuiPageButtonList.GuiSlideEntry(171, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177886_P), new GuiPageButtonList.GuiSlideEntry(172, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177885_Q), new GuiPageButtonList.GuiLabelEntry(418, I18n.format("tile.stone.granite.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(173, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177888_R), new GuiPageButtonList.GuiSlideEntry(174, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177887_S), new GuiPageButtonList.GuiSlideEntry(175, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177882_T), new GuiPageButtonList.GuiSlideEntry(176, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177881_U), new GuiPageButtonList.GuiLabelEntry(419, I18n.format("tile.stone.diorite.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(177, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177884_V), new GuiPageButtonList.GuiSlideEntry(178, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177883_W), new GuiPageButtonList.GuiSlideEntry(179, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177891_X), new GuiPageButtonList.GuiSlideEntry(180, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177890_Y), new GuiPageButtonList.GuiLabelEntry(420, I18n.format("tile.stone.andesite.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(181, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177892_Z), new GuiPageButtonList.GuiSlideEntry(182, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177936_aa), new GuiPageButtonList.GuiSlideEntry(183, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177937_ab), new GuiPageButtonList.GuiSlideEntry(184, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177934_ac), new GuiPageButtonList.GuiLabelEntry(421, I18n.format("tile.oreCoal.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(185, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177935_ad), new GuiPageButtonList.GuiSlideEntry(186, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177941_ae), new GuiPageButtonList.GuiSlideEntry(187, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177943_af), new GuiPageButtonList.GuiSlideEntry(189, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177938_ag), new GuiPageButtonList.GuiLabelEntry(422, I18n.format("tile.oreIron.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(190, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177939_ah), new GuiPageButtonList.GuiSlideEntry(191, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177922_ai), new GuiPageButtonList.GuiSlideEntry(192, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177924_aj), new GuiPageButtonList.GuiSlideEntry(193, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177918_ak), new GuiPageButtonList.GuiLabelEntry(423, I18n.format("tile.oreGold.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(194, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177920_al), new GuiPageButtonList.GuiSlideEntry(195, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177930_am), new GuiPageButtonList.GuiSlideEntry(196, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177932_an), new GuiPageButtonList.GuiSlideEntry(197, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177926_ao), new GuiPageButtonList.GuiLabelEntry(424, I18n.format("tile.oreRedstone.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(198, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177928_ap), new GuiPageButtonList.GuiSlideEntry(199, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177908_aq), new GuiPageButtonList.GuiSlideEntry(200, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177906_ar), new GuiPageButtonList.GuiSlideEntry(201, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177904_as), new GuiPageButtonList.GuiLabelEntry(425, I18n.format("tile.oreDiamond.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(202, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177902_at), new GuiPageButtonList.GuiSlideEntry(203, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177916_au), new GuiPageButtonList.GuiSlideEntry(204, I18n.format("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177914_av), new GuiPageButtonList.GuiSlideEntry(205, I18n.format("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177912_aw), new GuiPageButtonList.GuiLabelEntry(426, I18n.format("tile.oreLapis.name", new Object[0]), false), new GuiPageButtonList.GuiSlideEntry(206, I18n.format("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.field_175336_F.field_177910_ax), new GuiPageButtonList.GuiSlideEntry(207, I18n.format("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.field_175336_F.field_177897_ay), new GuiPageButtonList.GuiSlideEntry(208, I18n.format("createWorld.customize.custom.center", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177895_az), new GuiPageButtonList.GuiSlideEntry(209, I18n.format("createWorld.customize.custom.spread", new Object[0]), false, this, 0.0F, 255.0F, this.field_175336_F.field_177889_aA) };
/*  93 */     GuiPageButtonList.GuiListEntry[] var3 = { new GuiPageButtonList.GuiSlideEntry(100, I18n.format("createWorld.customize.custom.mainNoiseScaleX", new Object[0]), false, this, 1.0F, 5000.0F, this.field_175336_F.field_177917_i), new GuiPageButtonList.GuiSlideEntry(101, I18n.format("createWorld.customize.custom.mainNoiseScaleY", new Object[0]), false, this, 1.0F, 5000.0F, this.field_175336_F.field_177911_j), new GuiPageButtonList.GuiSlideEntry(102, I18n.format("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]), false, this, 1.0F, 5000.0F, this.field_175336_F.field_177913_k), new GuiPageButtonList.GuiSlideEntry(103, I18n.format("createWorld.customize.custom.depthNoiseScaleX", new Object[0]), false, this, 1.0F, 2000.0F, this.field_175336_F.field_177893_f), new GuiPageButtonList.GuiSlideEntry(104, I18n.format("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]), false, this, 1.0F, 2000.0F, this.field_175336_F.field_177894_g), new GuiPageButtonList.GuiSlideEntry(105, I18n.format("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]), false, this, 0.01F, 20.0F, this.field_175336_F.field_177915_h), new GuiPageButtonList.GuiSlideEntry(106, I18n.format("createWorld.customize.custom.baseSize", new Object[0]), false, this, 1.0F, 25.0F, this.field_175336_F.field_177907_l), new GuiPageButtonList.GuiSlideEntry(107, I18n.format("createWorld.customize.custom.coordinateScale", new Object[0]), false, this, 1.0F, 6000.0F, this.field_175336_F.field_177899_b), new GuiPageButtonList.GuiSlideEntry(108, I18n.format("createWorld.customize.custom.heightScale", new Object[0]), false, this, 1.0F, 6000.0F, this.field_175336_F.field_177900_c), new GuiPageButtonList.GuiSlideEntry(109, I18n.format("createWorld.customize.custom.stretchY", new Object[0]), false, this, 0.01F, 50.0F, this.field_175336_F.field_177909_m), new GuiPageButtonList.GuiSlideEntry(110, I18n.format("createWorld.customize.custom.upperLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.field_175336_F.field_177896_d), new GuiPageButtonList.GuiSlideEntry(111, I18n.format("createWorld.customize.custom.lowerLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.field_175336_F.field_177898_e), new GuiPageButtonList.GuiSlideEntry(112, I18n.format("createWorld.customize.custom.biomeDepthWeight", new Object[0]), false, this, 1.0F, 20.0F, this.field_175336_F.field_177903_n), new GuiPageButtonList.GuiSlideEntry(113, I18n.format("createWorld.customize.custom.biomeDepthOffset", new Object[0]), false, this, 0.0F, 20.0F, this.field_175336_F.field_177905_o), new GuiPageButtonList.GuiSlideEntry(114, I18n.format("createWorld.customize.custom.biomeScaleWeight", new Object[0]), false, this, 1.0F, 20.0F, this.field_175336_F.field_177933_p), new GuiPageButtonList.GuiSlideEntry(115, I18n.format("createWorld.customize.custom.biomeScaleOffset", new Object[0]), false, this, 0.0F, 20.0F, this.field_175336_F.field_177931_q) };
/*  94 */     GuiPageButtonList.GuiListEntry[] var4 = { new GuiPageButtonList.GuiLabelEntry(400, String.valueOf(I18n.format("createWorld.customize.custom.mainNoiseScaleX", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(132, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177917_i) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(401, String.valueOf(I18n.format("createWorld.customize.custom.mainNoiseScaleY", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(133, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177911_j) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(402, String.valueOf(I18n.format("createWorld.customize.custom.mainNoiseScaleZ", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(134, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177913_k) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(403, String.valueOf(I18n.format("createWorld.customize.custom.depthNoiseScaleX", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(135, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177893_f) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(404, String.valueOf(I18n.format("createWorld.customize.custom.depthNoiseScaleZ", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(136, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177894_g) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(405, String.valueOf(I18n.format("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(137, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177915_h) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(406, String.valueOf(I18n.format("createWorld.customize.custom.baseSize", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(138, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177907_l) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(407, String.valueOf(I18n.format("createWorld.customize.custom.coordinateScale", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(139, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177899_b) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(408, String.valueOf(I18n.format("createWorld.customize.custom.heightScale", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(140, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177900_c) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(409, String.valueOf(I18n.format("createWorld.customize.custom.stretchY", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(141, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177909_m) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(410, String.valueOf(I18n.format("createWorld.customize.custom.upperLimitScale", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(142, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177896_d) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(411, String.valueOf(I18n.format("createWorld.customize.custom.lowerLimitScale", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(143, String.format("%5.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177898_e) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(412, String.valueOf(I18n.format("createWorld.customize.custom.biomeDepthWeight", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(144, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177903_n) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(413, String.valueOf(I18n.format("createWorld.customize.custom.biomeDepthOffset", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(145, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177905_o) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(414, String.valueOf(I18n.format("createWorld.customize.custom.biomeScaleWeight", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(146, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177933_p) }), false, this.field_175332_D), new GuiPageButtonList.GuiLabelEntry(415, String.valueOf(I18n.format("createWorld.customize.custom.biomeScaleOffset", new Object[0])) + ":", false), new GuiPageButtonList.EditBoxEntry(147, String.format("%2.3f", new Object[] { Float.valueOf(this.field_175336_F.field_177931_q) }), false, this.field_175332_D) };
/*  95 */     this.field_175349_r = new GuiPageButtonList(this.mc, width, height, 32, height - 32, 25, this, new GuiPageButtonList.GuiListEntry[][] { var1, var2, var3, var4 });
/*     */     
/*  97 */     for (int var5 = 0; var5 < 4; var5++)
/*     */     {
/*  99 */       this.field_175342_h[var5] = I18n.format("createWorld.customize.custom.page" + var5, new Object[0]);
/*     */     }
/*     */     
/* 102 */     func_175328_i();
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175323_a() {
/* 107 */     return this.field_175336_F.toString().replace("\n", "");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175324_a(String p_175324_1_) {
/* 112 */     if (p_175324_1_ != null && p_175324_1_.length() != 0) {
/*     */       
/* 114 */       this.field_175336_F = ChunkProviderSettings.Factory.func_177865_a(p_175324_1_);
/*     */     }
/*     */     else {
/*     */       
/* 118 */       this.field_175336_F = new ChunkProviderSettings.Factory();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175319_a(int p_175319_1_, String p_175319_2_) {
/* 124 */     float var3 = 0.0F;
/*     */ 
/*     */     
/*     */     try {
/* 128 */       var3 = Float.parseFloat(p_175319_2_);
/*     */     }
/* 130 */     catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     float var4 = 0.0F;
/*     */     
/* 137 */     switch (p_175319_1_) {
/*     */       
/*     */       case 132:
/* 140 */         var4 = this.field_175336_F.field_177917_i = MathHelper.clamp_float(var3, 1.0F, 5000.0F);
/*     */         break;
/*     */       
/*     */       case 133:
/* 144 */         var4 = this.field_175336_F.field_177911_j = MathHelper.clamp_float(var3, 1.0F, 5000.0F);
/*     */         break;
/*     */       
/*     */       case 134:
/* 148 */         var4 = this.field_175336_F.field_177913_k = MathHelper.clamp_float(var3, 1.0F, 5000.0F);
/*     */         break;
/*     */       
/*     */       case 135:
/* 152 */         var4 = this.field_175336_F.field_177893_f = MathHelper.clamp_float(var3, 1.0F, 2000.0F);
/*     */         break;
/*     */       
/*     */       case 136:
/* 156 */         var4 = this.field_175336_F.field_177894_g = MathHelper.clamp_float(var3, 1.0F, 2000.0F);
/*     */         break;
/*     */       
/*     */       case 137:
/* 160 */         var4 = this.field_175336_F.field_177915_h = MathHelper.clamp_float(var3, 0.01F, 20.0F);
/*     */         break;
/*     */       
/*     */       case 138:
/* 164 */         var4 = this.field_175336_F.field_177907_l = MathHelper.clamp_float(var3, 1.0F, 25.0F);
/*     */         break;
/*     */       
/*     */       case 139:
/* 168 */         var4 = this.field_175336_F.field_177899_b = MathHelper.clamp_float(var3, 1.0F, 6000.0F);
/*     */         break;
/*     */       
/*     */       case 140:
/* 172 */         var4 = this.field_175336_F.field_177900_c = MathHelper.clamp_float(var3, 1.0F, 6000.0F);
/*     */         break;
/*     */       
/*     */       case 141:
/* 176 */         var4 = this.field_175336_F.field_177909_m = MathHelper.clamp_float(var3, 0.01F, 50.0F);
/*     */         break;
/*     */       
/*     */       case 142:
/* 180 */         var4 = this.field_175336_F.field_177896_d = MathHelper.clamp_float(var3, 1.0F, 5000.0F);
/*     */         break;
/*     */       
/*     */       case 143:
/* 184 */         var4 = this.field_175336_F.field_177898_e = MathHelper.clamp_float(var3, 1.0F, 5000.0F);
/*     */         break;
/*     */       
/*     */       case 144:
/* 188 */         var4 = this.field_175336_F.field_177903_n = MathHelper.clamp_float(var3, 1.0F, 20.0F);
/*     */         break;
/*     */       
/*     */       case 145:
/* 192 */         var4 = this.field_175336_F.field_177905_o = MathHelper.clamp_float(var3, 0.0F, 20.0F);
/*     */         break;
/*     */       
/*     */       case 146:
/* 196 */         var4 = this.field_175336_F.field_177933_p = MathHelper.clamp_float(var3, 1.0F, 20.0F);
/*     */         break;
/*     */       
/*     */       case 147:
/* 200 */         var4 = this.field_175336_F.field_177931_q = MathHelper.clamp_float(var3, 0.0F, 20.0F);
/*     */         break;
/*     */     } 
/* 203 */     if (var4 != var3 && var3 != 0.0F)
/*     */     {
/* 205 */       ((GuiTextField)this.field_175349_r.func_178061_c(p_175319_1_)).setText(func_175330_b(p_175319_1_, var4));
/*     */     }
/*     */     
/* 208 */     ((GuiSlider)this.field_175349_r.func_178061_c(p_175319_1_ - 132 + 100)).func_175218_a(var4, false);
/*     */     
/* 210 */     if (!this.field_175336_F.equals(this.field_175334_E))
/*     */     {
/* 212 */       this.field_175338_A = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175318_a(int p_175318_1_, String p_175318_2_, float p_175318_3_) {
/* 218 */     return String.valueOf(p_175318_2_) + ": " + func_175330_b(p_175318_1_, p_175318_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_175330_b(int p_175330_1_, float p_175330_2_) {
/* 223 */     switch (p_175330_1_) {
/*     */       
/*     */       case 100:
/*     */       case 101:
/*     */       case 102:
/*     */       case 103:
/*     */       case 104:
/*     */       case 107:
/*     */       case 108:
/*     */       case 110:
/*     */       case 111:
/*     */       case 132:
/*     */       case 133:
/*     */       case 134:
/*     */       case 135:
/*     */       case 136:
/*     */       case 139:
/*     */       case 140:
/*     */       case 142:
/*     */       case 143:
/* 243 */         return String.format("%5.3f", new Object[] { Float.valueOf(p_175330_2_) });
/*     */       
/*     */       case 105:
/*     */       case 106:
/*     */       case 109:
/*     */       case 112:
/*     */       case 113:
/*     */       case 114:
/*     */       case 115:
/*     */       case 137:
/*     */       case 138:
/*     */       case 141:
/*     */       case 144:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/* 259 */         return String.format("%2.3f", new Object[] { Float.valueOf(p_175330_2_) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 292 */         return String.format("%d", new Object[] { Integer.valueOf((int)p_175330_2_) });
/*     */       case 162:
/*     */         break;
/* 295 */     }  if (p_175330_2_ < 0.0F)
/*     */     {
/* 297 */       return I18n.format("gui.all", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     if ((int)p_175330_2_ >= BiomeGenBase.hell.biomeID) {
/*     */       
/* 305 */       BiomeGenBase biomeGenBase = BiomeGenBase.getBiomeGenArray()[(int)p_175330_2_ + 2];
/* 306 */       return (biomeGenBase != null) ? biomeGenBase.biomeName : "?";
/*     */     } 
/*     */ 
/*     */     
/* 310 */     BiomeGenBase var3 = BiomeGenBase.getBiomeGenArray()[(int)p_175330_2_];
/* 311 */     return (var3 != null) ? var3.biomeName : "?";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {
/* 319 */     switch (p_175321_1_) {
/*     */       
/*     */       case 148:
/* 322 */         this.field_175336_F.field_177927_s = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 149:
/* 326 */         this.field_175336_F.field_177925_t = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 150:
/* 330 */         this.field_175336_F.field_177921_v = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 151:
/* 334 */         this.field_175336_F.field_177919_w = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 152:
/* 338 */         this.field_175336_F.field_177944_x = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 153:
/* 342 */         this.field_175336_F.field_177942_y = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 154:
/* 346 */         this.field_175336_F.field_177870_A = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 155:
/* 350 */         this.field_175336_F.field_177871_B = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 156:
/* 354 */         this.field_175336_F.field_177866_D = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 161:
/* 358 */         this.field_175336_F.field_177868_F = p_175321_2_;
/*     */         break;
/*     */       
/*     */       case 210:
/* 362 */         this.field_175336_F.field_177940_z = p_175321_2_;
/*     */         break;
/*     */     } 
/* 365 */     if (!this.field_175336_F.equals(this.field_175334_E))
/*     */     {
/* 367 */       this.field_175338_A = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175320_a(int p_175320_1_, float p_175320_2_) {
/* 373 */     switch (p_175320_1_) {
/*     */       
/*     */       case 100:
/* 376 */         this.field_175336_F.field_177917_i = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 101:
/* 380 */         this.field_175336_F.field_177911_j = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 102:
/* 384 */         this.field_175336_F.field_177913_k = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 103:
/* 388 */         this.field_175336_F.field_177893_f = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 104:
/* 392 */         this.field_175336_F.field_177894_g = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 105:
/* 396 */         this.field_175336_F.field_177915_h = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 106:
/* 400 */         this.field_175336_F.field_177907_l = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 107:
/* 404 */         this.field_175336_F.field_177899_b = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 108:
/* 408 */         this.field_175336_F.field_177900_c = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 109:
/* 412 */         this.field_175336_F.field_177909_m = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 110:
/* 416 */         this.field_175336_F.field_177896_d = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 111:
/* 420 */         this.field_175336_F.field_177898_e = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 112:
/* 424 */         this.field_175336_F.field_177903_n = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 113:
/* 428 */         this.field_175336_F.field_177905_o = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 114:
/* 432 */         this.field_175336_F.field_177933_p = p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 115:
/* 436 */         this.field_175336_F.field_177931_q = p_175320_2_;
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 157:
/* 485 */         this.field_175336_F.field_177923_u = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 158:
/* 489 */         this.field_175336_F.field_177872_C = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 159:
/* 493 */         this.field_175336_F.field_177867_E = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 160:
/* 497 */         this.field_175336_F.field_177929_r = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 162:
/* 501 */         this.field_175336_F.field_177869_G = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 163:
/* 505 */         this.field_175336_F.field_177877_H = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 164:
/* 509 */         this.field_175336_F.field_177878_I = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 165:
/* 513 */         this.field_175336_F.field_177879_J = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 166:
/* 517 */         this.field_175336_F.field_177880_K = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 167:
/* 521 */         this.field_175336_F.field_177873_L = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 168:
/* 525 */         this.field_175336_F.field_177874_M = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 169:
/* 529 */         this.field_175336_F.field_177875_N = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 170:
/* 533 */         this.field_175336_F.field_177876_O = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 171:
/* 537 */         this.field_175336_F.field_177886_P = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 172:
/* 541 */         this.field_175336_F.field_177885_Q = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 173:
/* 545 */         this.field_175336_F.field_177888_R = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 174:
/* 549 */         this.field_175336_F.field_177887_S = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 175:
/* 553 */         this.field_175336_F.field_177882_T = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 176:
/* 557 */         this.field_175336_F.field_177881_U = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 177:
/* 561 */         this.field_175336_F.field_177884_V = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 178:
/* 565 */         this.field_175336_F.field_177883_W = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 179:
/* 569 */         this.field_175336_F.field_177891_X = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 180:
/* 573 */         this.field_175336_F.field_177890_Y = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 181:
/* 577 */         this.field_175336_F.field_177892_Z = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 182:
/* 581 */         this.field_175336_F.field_177936_aa = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 183:
/* 585 */         this.field_175336_F.field_177937_ab = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 184:
/* 589 */         this.field_175336_F.field_177934_ac = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 185:
/* 593 */         this.field_175336_F.field_177935_ad = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 186:
/* 597 */         this.field_175336_F.field_177941_ae = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 187:
/* 601 */         this.field_175336_F.field_177943_af = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 189:
/* 605 */         this.field_175336_F.field_177938_ag = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 190:
/* 609 */         this.field_175336_F.field_177939_ah = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 191:
/* 613 */         this.field_175336_F.field_177922_ai = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 192:
/* 617 */         this.field_175336_F.field_177924_aj = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 193:
/* 621 */         this.field_175336_F.field_177918_ak = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 194:
/* 625 */         this.field_175336_F.field_177920_al = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 195:
/* 629 */         this.field_175336_F.field_177930_am = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 196:
/* 633 */         this.field_175336_F.field_177932_an = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 197:
/* 637 */         this.field_175336_F.field_177926_ao = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 198:
/* 641 */         this.field_175336_F.field_177928_ap = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 199:
/* 645 */         this.field_175336_F.field_177908_aq = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 200:
/* 649 */         this.field_175336_F.field_177906_ar = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 201:
/* 653 */         this.field_175336_F.field_177904_as = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 202:
/* 657 */         this.field_175336_F.field_177902_at = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 203:
/* 661 */         this.field_175336_F.field_177916_au = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 204:
/* 665 */         this.field_175336_F.field_177914_av = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 205:
/* 669 */         this.field_175336_F.field_177912_aw = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 206:
/* 673 */         this.field_175336_F.field_177910_ax = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 207:
/* 677 */         this.field_175336_F.field_177897_ay = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 208:
/* 681 */         this.field_175336_F.field_177895_az = (int)p_175320_2_;
/*     */         break;
/*     */       
/*     */       case 209:
/* 685 */         this.field_175336_F.field_177889_aA = (int)p_175320_2_;
/*     */         break;
/*     */     } 
/* 688 */     if (p_175320_1_ >= 100 && p_175320_1_ < 116) {
/*     */       
/* 690 */       Gui var3 = this.field_175349_r.func_178061_c(p_175320_1_ - 100 + 132);
/*     */       
/* 692 */       if (var3 != null)
/*     */       {
/* 694 */         ((GuiTextField)var3).setText(func_175330_b(p_175320_1_, p_175320_2_));
/*     */       }
/*     */     } 
/*     */     
/* 698 */     if (!this.field_175336_F.equals(this.field_175334_E))
/*     */     {
/* 700 */       this.field_175338_A = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 706 */     if (button.enabled) {
/*     */       int var2;
/* 708 */       switch (button.id) {
/*     */         
/*     */         case 300:
/* 711 */           this.field_175343_i.field_146334_a = this.field_175336_F.toString();
/* 712 */           this.mc.displayGuiScreen(this.field_175343_i);
/*     */           break;
/*     */         
/*     */         case 301:
/* 716 */           for (var2 = 0; var2 < this.field_175349_r.getSize(); var2++) {
/*     */             
/* 718 */             GuiPageButtonList.GuiEntry var3 = this.field_175349_r.func_178070_d(var2);
/* 719 */             Gui var4 = var3.func_178022_a();
/*     */             
/* 721 */             if (var4 instanceof GuiButton) {
/*     */               
/* 723 */               GuiButton var5 = (GuiButton)var4;
/*     */               
/* 725 */               if (var5 instanceof GuiSlider) {
/*     */                 
/* 727 */                 float var6 = ((GuiSlider)var5).func_175217_d() * (0.75F + this.field_175337_G.nextFloat() * 0.5F) + this.field_175337_G.nextFloat() * 0.1F - 0.05F;
/* 728 */                 ((GuiSlider)var5).func_175219_a(MathHelper.clamp_float(var6, 0.0F, 1.0F));
/*     */               }
/* 730 */               else if (var5 instanceof GuiListButton) {
/*     */                 
/* 732 */                 ((GuiListButton)var5).func_175212_b(this.field_175337_G.nextBoolean());
/*     */               } 
/*     */             } 
/*     */             
/* 736 */             Gui var8 = var3.func_178021_b();
/*     */             
/* 738 */             if (var8 instanceof GuiButton) {
/*     */               
/* 740 */               GuiButton var9 = (GuiButton)var8;
/*     */               
/* 742 */               if (var9 instanceof GuiSlider) {
/*     */                 
/* 744 */                 float var7 = ((GuiSlider)var9).func_175217_d() * (0.75F + this.field_175337_G.nextFloat() * 0.5F) + this.field_175337_G.nextFloat() * 0.1F - 0.05F;
/* 745 */                 ((GuiSlider)var9).func_175219_a(MathHelper.clamp_float(var7, 0.0F, 1.0F));
/*     */               }
/* 747 */               else if (var9 instanceof GuiListButton) {
/*     */                 
/* 749 */                 ((GuiListButton)var9).func_175212_b(this.field_175337_G.nextBoolean());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           return;
/*     */ 
/*     */         
/*     */         case 302:
/* 757 */           this.field_175349_r.func_178071_h();
/* 758 */           func_175328_i();
/*     */           break;
/*     */         
/*     */         case 303:
/* 762 */           this.field_175349_r.func_178064_i();
/* 763 */           func_175328_i();
/*     */           break;
/*     */         
/*     */         case 304:
/* 767 */           if (this.field_175338_A)
/*     */           {
/* 769 */             func_175322_b(304);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 305:
/* 775 */           this.mc.displayGuiScreen(new GuiScreenCustomizePresets(this));
/*     */           break;
/*     */         
/*     */         case 306:
/* 779 */           func_175331_h();
/*     */           break;
/*     */         
/*     */         case 307:
/* 783 */           this.field_175339_B = 0;
/* 784 */           func_175331_h();
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_175326_g() {
/* 791 */     this.field_175336_F.func_177863_a();
/* 792 */     func_175325_f();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175322_b(int p_175322_1_) {
/* 797 */     this.field_175339_B = p_175322_1_;
/* 798 */     func_175329_a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175331_h() throws IOException {
/* 803 */     switch (this.field_175339_B) {
/*     */       
/*     */       case 300:
/* 806 */         actionPerformed((GuiListButton)this.field_175349_r.func_178061_c(300));
/*     */         break;
/*     */       
/*     */       case 304:
/* 810 */         func_175326_g();
/*     */         break;
/*     */     } 
/* 813 */     this.field_175339_B = 0;
/* 814 */     this.field_175340_C = true;
/* 815 */     func_175329_a(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175329_a(boolean p_175329_1_) {
/* 820 */     this.field_175352_x.visible = p_175329_1_;
/* 821 */     this.field_175351_y.visible = p_175329_1_;
/* 822 */     this.field_175347_t.enabled = !p_175329_1_;
/* 823 */     this.field_175348_s.enabled = !p_175329_1_;
/* 824 */     this.field_175345_v.enabled = !p_175329_1_;
/* 825 */     this.field_175344_w.enabled = !p_175329_1_;
/* 826 */     this.field_175346_u.enabled = !p_175329_1_;
/* 827 */     this.field_175350_z.enabled = !p_175329_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175328_i() {
/* 832 */     this.field_175345_v.enabled = (this.field_175349_r.func_178059_e() != 0);
/* 833 */     this.field_175344_w.enabled = (this.field_175349_r.func_178059_e() != this.field_175349_r.func_178057_f() - 1);
/* 834 */     this.field_175333_f = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.field_175349_r.func_178059_e() + 1), Integer.valueOf(this.field_175349_r.func_178057_f()) });
/* 835 */     this.field_175335_g = this.field_175342_h[this.field_175349_r.func_178059_e()];
/* 836 */     this.field_175347_t.enabled = (this.field_175349_r.func_178059_e() != this.field_175349_r.func_178057_f() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 845 */     super.keyTyped(typedChar, keyCode);
/*     */     
/* 847 */     if (this.field_175339_B == 0) {
/*     */       
/* 849 */       switch (keyCode) {
/*     */         
/*     */         case 200:
/* 852 */           func_175327_a(1.0F);
/*     */           return;
/*     */         
/*     */         case 208:
/* 856 */           func_175327_a(-1.0F);
/*     */           return;
/*     */       } 
/*     */       
/* 860 */       this.field_175349_r.func_178062_a(typedChar, keyCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175327_a(float p_175327_1_) {
/* 867 */     Gui var2 = this.field_175349_r.func_178056_g();
/*     */     
/* 869 */     if (var2 instanceof GuiTextField) {
/*     */       
/* 871 */       float var3 = p_175327_1_;
/*     */       
/* 873 */       if (GuiScreen.isShiftKeyDown()) {
/*     */         
/* 875 */         var3 = p_175327_1_ * 0.1F;
/*     */         
/* 877 */         if (GuiScreen.isCtrlKeyDown())
/*     */         {
/* 879 */           var3 *= 0.1F;
/*     */         }
/*     */       }
/* 882 */       else if (GuiScreen.isCtrlKeyDown()) {
/*     */         
/* 884 */         var3 = p_175327_1_ * 10.0F;
/*     */         
/* 886 */         if (GuiScreen.func_175283_s())
/*     */         {
/* 888 */           var3 *= 10.0F;
/*     */         }
/*     */       } 
/*     */       
/* 892 */       GuiTextField var4 = (GuiTextField)var2;
/* 893 */       Float var5 = Floats.tryParse(var4.getText());
/*     */       
/* 895 */       if (var5 != null) {
/*     */         
/* 897 */         var5 = Float.valueOf(var5.floatValue() + var3);
/* 898 */         int var6 = var4.func_175206_d();
/* 899 */         String var7 = func_175330_b(var4.func_175206_d(), var5.floatValue());
/* 900 */         var4.setText(var7);
/* 901 */         func_175319_a(var6, var7);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 911 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     
/* 913 */     if (this.field_175339_B == 0 && !this.field_175340_C)
/*     */     {
/* 915 */       this.field_175349_r.func_148179_a(mouseX, mouseY, mouseButton);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 924 */     super.mouseReleased(mouseX, mouseY, state);
/*     */     
/* 926 */     if (this.field_175340_C) {
/*     */       
/* 928 */       this.field_175340_C = false;
/*     */     }
/* 930 */     else if (this.field_175339_B == 0) {
/*     */       
/* 932 */       this.field_175349_r.func_148181_b(mouseX, mouseY, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 941 */     drawDefaultBackground();
/* 942 */     this.field_175349_r.drawScreen(mouseX, mouseY, partialTicks);
/* 943 */     drawCenteredString(fontRendererObj, this.field_175341_a, (width / 2), 2.0F, 16777215);
/* 944 */     drawCenteredString(fontRendererObj, this.field_175333_f, (width / 2), 12.0F, 16777215);
/* 945 */     drawCenteredString(fontRendererObj, this.field_175335_g, (width / 2), 22.0F, 16777215);
/* 946 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */     
/* 948 */     if (this.field_175339_B != 0) {
/*     */       
/* 950 */       drawRect(0.0D, 0.0D, width, height, -2147483648);
/* 951 */       drawHorizontalLine(width / 2 - 91, width / 2 + 90, 99, -2039584);
/* 952 */       drawHorizontalLine(width / 2 - 91, width / 2 + 90, 185, -6250336);
/* 953 */       drawVerticalLine(width / 2 - 91, 99, 185, -2039584);
/* 954 */       drawVerticalLine(width / 2 + 90, 99, 185, -6250336);
/* 955 */       float var4 = 85.0F;
/* 956 */       float var5 = 180.0F;
/* 957 */       GlStateManager.disableLighting();
/* 958 */       GlStateManager.disableFog();
/* 959 */       Tessellator var6 = Tessellator.getInstance();
/* 960 */       WorldRenderer var7 = var6.getWorldRenderer();
/* 961 */       this.mc.getTextureManager().bindTexture(optionsBackground);
/* 962 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 963 */       float var8 = 32.0F;
/* 964 */       var7.startDrawingQuads();
/* 965 */       var7.func_178991_c(4210752);
/* 966 */       var7.addVertexWithUV((width / 2 - 90), 185.0D, 0.0D, 0.0D, 2.65625D);
/* 967 */       var7.addVertexWithUV((width / 2 + 90), 185.0D, 0.0D, 5.625D, 2.65625D);
/* 968 */       var7.addVertexWithUV((width / 2 + 90), 100.0D, 0.0D, 5.625D, 0.0D);
/* 969 */       var7.addVertexWithUV((width / 2 - 90), 100.0D, 0.0D, 0.0D, 0.0D);
/* 970 */       var6.draw();
/* 971 */       drawCenteredString(fontRendererObj, I18n.format("createWorld.customize.custom.confirmTitle", new Object[0]), (width / 2), 105.0F, 16777215);
/* 972 */       drawCenteredString(fontRendererObj, I18n.format("createWorld.customize.custom.confirm1", new Object[0]), (width / 2), 125.0F, 16777215);
/* 973 */       drawCenteredString(fontRendererObj, I18n.format("createWorld.customize.custom.confirm2", new Object[0]), (width / 2), 135.0F, 16777215);
/* 974 */       this.field_175352_x.drawButton(this.mc, mouseX, mouseY);
/* 975 */       this.field_175351_y.drawButton(this.mc, mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiCustomizeWorldScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */