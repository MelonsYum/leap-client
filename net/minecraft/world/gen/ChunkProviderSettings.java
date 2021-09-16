/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ public class ChunkProviderSettings
/*     */ {
/*     */   public final float field_177811_a;
/*     */   public final float field_177809_b;
/*     */   public final float field_177810_c;
/*     */   public final float field_177806_d;
/*     */   public final float field_177808_e;
/*     */   public final float field_177803_f;
/*     */   public final float field_177804_g;
/*     */   public final float field_177825_h;
/*     */   public final float field_177827_i;
/*     */   public final float field_177821_j;
/*     */   public final float field_177823_k;
/*     */   public final float field_177817_l;
/*     */   public final float field_177819_m;
/*     */   public final float field_177813_n;
/*     */   public final float field_177815_o;
/*     */   public final float field_177843_p;
/*     */   public final int field_177841_q;
/*     */   public final boolean field_177839_r;
/*     */   public final boolean field_177837_s;
/*     */   public final int field_177835_t;
/*     */   public final boolean field_177833_u;
/*     */   public final boolean field_177831_v;
/*     */   public final boolean field_177829_w;
/*     */   public final boolean field_177854_x;
/*     */   public final boolean field_177852_y;
/*     */   public final boolean field_177850_z;
/*     */   public final boolean field_177781_A;
/*     */   public final int field_177782_B;
/*     */   public final boolean field_177783_C;
/*     */   public final int field_177777_D;
/*     */   public final boolean field_177778_E;
/*     */   public final int field_177779_F;
/*     */   public final int field_177780_G;
/*     */   public final int field_177788_H;
/*     */   public final int field_177789_I;
/*     */   public final int field_177790_J;
/*     */   public final int field_177791_K;
/*     */   public final int field_177784_L;
/*     */   public final int field_177785_M;
/*     */   public final int field_177786_N;
/*     */   public final int field_177787_O;
/*     */   public final int field_177797_P;
/*     */   public final int field_177796_Q;
/*     */   public final int field_177799_R;
/*     */   public final int field_177798_S;
/*     */   public final int field_177793_T;
/*     */   public final int field_177792_U;
/*     */   public final int field_177795_V;
/*     */   public final int field_177794_W;
/*     */   public final int field_177801_X;
/*     */   public final int field_177800_Y;
/*     */   public final int field_177802_Z;
/*     */   public final int field_177846_aa;
/*     */   public final int field_177847_ab;
/*     */   public final int field_177844_ac;
/*     */   public final int field_177845_ad;
/*     */   public final int field_177851_ae;
/*     */   public final int field_177853_af;
/*     */   public final int field_177848_ag;
/*     */   public final int field_177849_ah;
/*     */   public final int field_177832_ai;
/*     */   public final int field_177834_aj;
/*     */   public final int field_177828_ak;
/*     */   public final int field_177830_al;
/*     */   public final int field_177840_am;
/*     */   public final int field_177842_an;
/*     */   public final int field_177836_ao;
/*     */   public final int field_177838_ap;
/*     */   public final int field_177818_aq;
/*     */   public final int field_177816_ar;
/*     */   public final int field_177814_as;
/*     */   public final int field_177812_at;
/*     */   public final int field_177826_au;
/*     */   public final int field_177824_av;
/*     */   public final int field_177822_aw;
/*     */   public final int field_177820_ax;
/*     */   public final int field_177807_ay;
/*     */   public final int field_177805_az;
/*     */   private static final String __OBFID = "CL_00002006";
/*     */   
/*     */   private ChunkProviderSettings(Factory p_i45639_1_) {
/*  99 */     this.field_177811_a = p_i45639_1_.field_177899_b;
/* 100 */     this.field_177809_b = p_i45639_1_.field_177900_c;
/* 101 */     this.field_177810_c = p_i45639_1_.field_177896_d;
/* 102 */     this.field_177806_d = p_i45639_1_.field_177898_e;
/* 103 */     this.field_177808_e = p_i45639_1_.field_177893_f;
/* 104 */     this.field_177803_f = p_i45639_1_.field_177894_g;
/* 105 */     this.field_177804_g = p_i45639_1_.field_177915_h;
/* 106 */     this.field_177825_h = p_i45639_1_.field_177917_i;
/* 107 */     this.field_177827_i = p_i45639_1_.field_177911_j;
/* 108 */     this.field_177821_j = p_i45639_1_.field_177913_k;
/* 109 */     this.field_177823_k = p_i45639_1_.field_177907_l;
/* 110 */     this.field_177817_l = p_i45639_1_.field_177909_m;
/* 111 */     this.field_177819_m = p_i45639_1_.field_177903_n;
/* 112 */     this.field_177813_n = p_i45639_1_.field_177905_o;
/* 113 */     this.field_177815_o = p_i45639_1_.field_177933_p;
/* 114 */     this.field_177843_p = p_i45639_1_.field_177931_q;
/* 115 */     this.field_177841_q = p_i45639_1_.field_177929_r;
/* 116 */     this.field_177839_r = p_i45639_1_.field_177927_s;
/* 117 */     this.field_177837_s = p_i45639_1_.field_177925_t;
/* 118 */     this.field_177835_t = p_i45639_1_.field_177923_u;
/* 119 */     this.field_177833_u = p_i45639_1_.field_177921_v;
/* 120 */     this.field_177831_v = p_i45639_1_.field_177919_w;
/* 121 */     this.field_177829_w = p_i45639_1_.field_177944_x;
/* 122 */     this.field_177854_x = p_i45639_1_.field_177942_y;
/* 123 */     this.field_177852_y = p_i45639_1_.field_177940_z;
/* 124 */     this.field_177850_z = p_i45639_1_.field_177870_A;
/* 125 */     this.field_177781_A = p_i45639_1_.field_177871_B;
/* 126 */     this.field_177782_B = p_i45639_1_.field_177872_C;
/* 127 */     this.field_177783_C = p_i45639_1_.field_177866_D;
/* 128 */     this.field_177777_D = p_i45639_1_.field_177867_E;
/* 129 */     this.field_177778_E = p_i45639_1_.field_177868_F;
/* 130 */     this.field_177779_F = p_i45639_1_.field_177869_G;
/* 131 */     this.field_177780_G = p_i45639_1_.field_177877_H;
/* 132 */     this.field_177788_H = p_i45639_1_.field_177878_I;
/* 133 */     this.field_177789_I = p_i45639_1_.field_177879_J;
/* 134 */     this.field_177790_J = p_i45639_1_.field_177880_K;
/* 135 */     this.field_177791_K = p_i45639_1_.field_177873_L;
/* 136 */     this.field_177784_L = p_i45639_1_.field_177874_M;
/* 137 */     this.field_177785_M = p_i45639_1_.field_177875_N;
/* 138 */     this.field_177786_N = p_i45639_1_.field_177876_O;
/* 139 */     this.field_177787_O = p_i45639_1_.field_177886_P;
/* 140 */     this.field_177797_P = p_i45639_1_.field_177885_Q;
/* 141 */     this.field_177796_Q = p_i45639_1_.field_177888_R;
/* 142 */     this.field_177799_R = p_i45639_1_.field_177887_S;
/* 143 */     this.field_177798_S = p_i45639_1_.field_177882_T;
/* 144 */     this.field_177793_T = p_i45639_1_.field_177881_U;
/* 145 */     this.field_177792_U = p_i45639_1_.field_177884_V;
/* 146 */     this.field_177795_V = p_i45639_1_.field_177883_W;
/* 147 */     this.field_177794_W = p_i45639_1_.field_177891_X;
/* 148 */     this.field_177801_X = p_i45639_1_.field_177890_Y;
/* 149 */     this.field_177800_Y = p_i45639_1_.field_177892_Z;
/* 150 */     this.field_177802_Z = p_i45639_1_.field_177936_aa;
/* 151 */     this.field_177846_aa = p_i45639_1_.field_177937_ab;
/* 152 */     this.field_177847_ab = p_i45639_1_.field_177934_ac;
/* 153 */     this.field_177844_ac = p_i45639_1_.field_177935_ad;
/* 154 */     this.field_177845_ad = p_i45639_1_.field_177941_ae;
/* 155 */     this.field_177851_ae = p_i45639_1_.field_177943_af;
/* 156 */     this.field_177853_af = p_i45639_1_.field_177938_ag;
/* 157 */     this.field_177848_ag = p_i45639_1_.field_177939_ah;
/* 158 */     this.field_177849_ah = p_i45639_1_.field_177922_ai;
/* 159 */     this.field_177832_ai = p_i45639_1_.field_177924_aj;
/* 160 */     this.field_177834_aj = p_i45639_1_.field_177918_ak;
/* 161 */     this.field_177828_ak = p_i45639_1_.field_177920_al;
/* 162 */     this.field_177830_al = p_i45639_1_.field_177930_am;
/* 163 */     this.field_177840_am = p_i45639_1_.field_177932_an;
/* 164 */     this.field_177842_an = p_i45639_1_.field_177926_ao;
/* 165 */     this.field_177836_ao = p_i45639_1_.field_177928_ap;
/* 166 */     this.field_177838_ap = p_i45639_1_.field_177908_aq;
/* 167 */     this.field_177818_aq = p_i45639_1_.field_177906_ar;
/* 168 */     this.field_177816_ar = p_i45639_1_.field_177904_as;
/* 169 */     this.field_177814_as = p_i45639_1_.field_177902_at;
/* 170 */     this.field_177812_at = p_i45639_1_.field_177916_au;
/* 171 */     this.field_177826_au = p_i45639_1_.field_177914_av;
/* 172 */     this.field_177824_av = p_i45639_1_.field_177912_aw;
/* 173 */     this.field_177822_aw = p_i45639_1_.field_177910_ax;
/* 174 */     this.field_177820_ax = p_i45639_1_.field_177897_ay;
/* 175 */     this.field_177807_ay = p_i45639_1_.field_177895_az;
/* 176 */     this.field_177805_az = p_i45639_1_.field_177889_aA;
/*     */   }
/*     */ 
/*     */   
/*     */   ChunkProviderSettings(Factory p_i45640_1_, Object p_i45640_2_) {
/* 181 */     this(p_i45640_1_);
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */   {
/* 186 */     static final Gson field_177901_a = (new GsonBuilder()).registerTypeAdapter(Factory.class, new ChunkProviderSettings.Serializer()).create();
/* 187 */     public float field_177899_b = 684.412F;
/* 188 */     public float field_177900_c = 684.412F;
/* 189 */     public float field_177896_d = 512.0F;
/* 190 */     public float field_177898_e = 512.0F;
/* 191 */     public float field_177893_f = 200.0F;
/* 192 */     public float field_177894_g = 200.0F;
/* 193 */     public float field_177915_h = 0.5F;
/* 194 */     public float field_177917_i = 80.0F;
/* 195 */     public float field_177911_j = 160.0F;
/* 196 */     public float field_177913_k = 80.0F;
/* 197 */     public float field_177907_l = 8.5F;
/* 198 */     public float field_177909_m = 12.0F;
/* 199 */     public float field_177903_n = 1.0F;
/* 200 */     public float field_177905_o = 0.0F;
/* 201 */     public float field_177933_p = 1.0F;
/* 202 */     public float field_177931_q = 0.0F;
/* 203 */     public int field_177929_r = 63;
/*     */     public boolean field_177927_s = true;
/*     */     public boolean field_177925_t = true;
/* 206 */     public int field_177923_u = 8;
/*     */     public boolean field_177921_v = true;
/*     */     public boolean field_177919_w = true;
/*     */     public boolean field_177944_x = true;
/*     */     public boolean field_177942_y = true;
/*     */     public boolean field_177940_z = true;
/*     */     public boolean field_177870_A = true;
/*     */     public boolean field_177871_B = true;
/* 214 */     public int field_177872_C = 4;
/*     */     public boolean field_177866_D = true;
/* 216 */     public int field_177867_E = 80;
/*     */     public boolean field_177868_F = false;
/* 218 */     public int field_177869_G = -1;
/* 219 */     public int field_177877_H = 4;
/* 220 */     public int field_177878_I = 4;
/* 221 */     public int field_177879_J = 33;
/* 222 */     public int field_177880_K = 10;
/* 223 */     public int field_177873_L = 0;
/* 224 */     public int field_177874_M = 256;
/* 225 */     public int field_177875_N = 33;
/* 226 */     public int field_177876_O = 8;
/* 227 */     public int field_177886_P = 0;
/* 228 */     public int field_177885_Q = 256;
/* 229 */     public int field_177888_R = 33;
/* 230 */     public int field_177887_S = 10;
/* 231 */     public int field_177882_T = 0;
/* 232 */     public int field_177881_U = 80;
/* 233 */     public int field_177884_V = 33;
/* 234 */     public int field_177883_W = 10;
/* 235 */     public int field_177891_X = 0;
/* 236 */     public int field_177890_Y = 80;
/* 237 */     public int field_177892_Z = 33;
/* 238 */     public int field_177936_aa = 10;
/* 239 */     public int field_177937_ab = 0;
/* 240 */     public int field_177934_ac = 80;
/* 241 */     public int field_177935_ad = 17;
/* 242 */     public int field_177941_ae = 20;
/* 243 */     public int field_177943_af = 0;
/* 244 */     public int field_177938_ag = 128;
/* 245 */     public int field_177939_ah = 9;
/* 246 */     public int field_177922_ai = 20;
/* 247 */     public int field_177924_aj = 0;
/* 248 */     public int field_177918_ak = 64;
/* 249 */     public int field_177920_al = 9;
/* 250 */     public int field_177930_am = 2;
/* 251 */     public int field_177932_an = 0;
/* 252 */     public int field_177926_ao = 32;
/* 253 */     public int field_177928_ap = 8;
/* 254 */     public int field_177908_aq = 8;
/* 255 */     public int field_177906_ar = 0;
/* 256 */     public int field_177904_as = 16;
/* 257 */     public int field_177902_at = 8;
/* 258 */     public int field_177916_au = 1;
/* 259 */     public int field_177914_av = 0;
/* 260 */     public int field_177912_aw = 16;
/* 261 */     public int field_177910_ax = 7;
/* 262 */     public int field_177897_ay = 1;
/* 263 */     public int field_177895_az = 16;
/* 264 */     public int field_177889_aA = 16;
/*     */     
/*     */     private static final String __OBFID = "CL_00002004";
/*     */     
/*     */     public static Factory func_177865_a(String p_177865_0_) {
/* 269 */       if (p_177865_0_.length() == 0)
/*     */       {
/* 271 */         return new Factory();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 277 */         return (Factory)field_177901_a.fromJson(p_177865_0_, Factory.class);
/*     */       }
/* 279 */       catch (Exception var2) {
/*     */         
/* 281 */         return new Factory();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 288 */       return field_177901_a.toJson(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory() {
/* 293 */       func_177863_a();
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_177863_a() {
/* 298 */       this.field_177899_b = 684.412F;
/* 299 */       this.field_177900_c = 684.412F;
/* 300 */       this.field_177896_d = 512.0F;
/* 301 */       this.field_177898_e = 512.0F;
/* 302 */       this.field_177893_f = 200.0F;
/* 303 */       this.field_177894_g = 200.0F;
/* 304 */       this.field_177915_h = 0.5F;
/* 305 */       this.field_177917_i = 80.0F;
/* 306 */       this.field_177911_j = 160.0F;
/* 307 */       this.field_177913_k = 80.0F;
/* 308 */       this.field_177907_l = 8.5F;
/* 309 */       this.field_177909_m = 12.0F;
/* 310 */       this.field_177903_n = 1.0F;
/* 311 */       this.field_177905_o = 0.0F;
/* 312 */       this.field_177933_p = 1.0F;
/* 313 */       this.field_177931_q = 0.0F;
/* 314 */       this.field_177929_r = 63;
/* 315 */       this.field_177927_s = true;
/* 316 */       this.field_177925_t = true;
/* 317 */       this.field_177923_u = 8;
/* 318 */       this.field_177921_v = true;
/* 319 */       this.field_177919_w = true;
/* 320 */       this.field_177944_x = true;
/* 321 */       this.field_177942_y = true;
/* 322 */       this.field_177940_z = true;
/* 323 */       this.field_177870_A = true;
/* 324 */       this.field_177871_B = true;
/* 325 */       this.field_177872_C = 4;
/* 326 */       this.field_177866_D = true;
/* 327 */       this.field_177867_E = 80;
/* 328 */       this.field_177868_F = false;
/* 329 */       this.field_177869_G = -1;
/* 330 */       this.field_177877_H = 4;
/* 331 */       this.field_177878_I = 4;
/* 332 */       this.field_177879_J = 33;
/* 333 */       this.field_177880_K = 10;
/* 334 */       this.field_177873_L = 0;
/* 335 */       this.field_177874_M = 256;
/* 336 */       this.field_177875_N = 33;
/* 337 */       this.field_177876_O = 8;
/* 338 */       this.field_177886_P = 0;
/* 339 */       this.field_177885_Q = 256;
/* 340 */       this.field_177888_R = 33;
/* 341 */       this.field_177887_S = 10;
/* 342 */       this.field_177882_T = 0;
/* 343 */       this.field_177881_U = 80;
/* 344 */       this.field_177884_V = 33;
/* 345 */       this.field_177883_W = 10;
/* 346 */       this.field_177891_X = 0;
/* 347 */       this.field_177890_Y = 80;
/* 348 */       this.field_177892_Z = 33;
/* 349 */       this.field_177936_aa = 10;
/* 350 */       this.field_177937_ab = 0;
/* 351 */       this.field_177934_ac = 80;
/* 352 */       this.field_177935_ad = 17;
/* 353 */       this.field_177941_ae = 20;
/* 354 */       this.field_177943_af = 0;
/* 355 */       this.field_177938_ag = 128;
/* 356 */       this.field_177939_ah = 9;
/* 357 */       this.field_177922_ai = 20;
/* 358 */       this.field_177924_aj = 0;
/* 359 */       this.field_177918_ak = 64;
/* 360 */       this.field_177920_al = 9;
/* 361 */       this.field_177930_am = 2;
/* 362 */       this.field_177932_an = 0;
/* 363 */       this.field_177926_ao = 32;
/* 364 */       this.field_177928_ap = 8;
/* 365 */       this.field_177908_aq = 8;
/* 366 */       this.field_177906_ar = 0;
/* 367 */       this.field_177904_as = 16;
/* 368 */       this.field_177902_at = 8;
/* 369 */       this.field_177916_au = 1;
/* 370 */       this.field_177914_av = 0;
/* 371 */       this.field_177912_aw = 16;
/* 372 */       this.field_177910_ax = 7;
/* 373 */       this.field_177897_ay = 1;
/* 374 */       this.field_177895_az = 16;
/* 375 */       this.field_177889_aA = 16;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_equals_1_) {
/* 380 */       if (this == p_equals_1_)
/*     */       {
/* 382 */         return true;
/*     */       }
/* 384 */       if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */         
/* 386 */         Factory var2 = (Factory)p_equals_1_;
/* 387 */         return (this.field_177936_aa != var2.field_177936_aa) ? false : ((this.field_177934_ac != var2.field_177934_ac) ? false : ((this.field_177937_ab != var2.field_177937_ab) ? false : ((this.field_177892_Z != var2.field_177892_Z) ? false : ((Float.compare(var2.field_177907_l, this.field_177907_l) != 0) ? false : ((Float.compare(var2.field_177905_o, this.field_177905_o) != 0) ? false : ((Float.compare(var2.field_177903_n, this.field_177903_n) != 0) ? false : ((Float.compare(var2.field_177931_q, this.field_177931_q) != 0) ? false : ((Float.compare(var2.field_177933_p, this.field_177933_p) != 0) ? false : ((this.field_177877_H != var2.field_177877_H) ? false : ((this.field_177941_ae != var2.field_177941_ae) ? false : ((this.field_177938_ag != var2.field_177938_ag) ? false : ((this.field_177943_af != var2.field_177943_af) ? false : ((this.field_177935_ad != var2.field_177935_ad) ? false : ((Float.compare(var2.field_177899_b, this.field_177899_b) != 0) ? false : ((Float.compare(var2.field_177915_h, this.field_177915_h) != 0) ? false : ((Float.compare(var2.field_177893_f, this.field_177893_f) != 0) ? false : ((Float.compare(var2.field_177894_g, this.field_177894_g) != 0) ? false : ((this.field_177916_au != var2.field_177916_au) ? false : ((this.field_177912_aw != var2.field_177912_aw) ? false : ((this.field_177914_av != var2.field_177914_av) ? false : ((this.field_177902_at != var2.field_177902_at) ? false : ((this.field_177883_W != var2.field_177883_W) ? false : ((this.field_177890_Y != var2.field_177890_Y) ? false : ((this.field_177891_X != var2.field_177891_X) ? false : ((this.field_177884_V != var2.field_177884_V) ? false : ((this.field_177880_K != var2.field_177880_K) ? false : ((this.field_177874_M != var2.field_177874_M) ? false : ((this.field_177873_L != var2.field_177873_L) ? false : ((this.field_177879_J != var2.field_177879_J) ? false : ((this.field_177923_u != var2.field_177923_u) ? false : ((this.field_177869_G != var2.field_177869_G) ? false : ((this.field_177930_am != var2.field_177930_am) ? false : ((this.field_177926_ao != var2.field_177926_ao) ? false : ((this.field_177932_an != var2.field_177932_an) ? false : ((this.field_177920_al != var2.field_177920_al) ? false : ((this.field_177887_S != var2.field_177887_S) ? false : ((this.field_177881_U != var2.field_177881_U) ? false : ((this.field_177882_T != var2.field_177882_T) ? false : ((this.field_177888_R != var2.field_177888_R) ? false : ((this.field_177876_O != var2.field_177876_O) ? false : ((this.field_177885_Q != var2.field_177885_Q) ? false : ((this.field_177886_P != var2.field_177886_P) ? false : ((this.field_177875_N != var2.field_177875_N) ? false : ((Float.compare(var2.field_177900_c, this.field_177900_c) != 0) ? false : ((this.field_177922_ai != var2.field_177922_ai) ? false : ((this.field_177918_ak != var2.field_177918_ak) ? false : ((this.field_177924_aj != var2.field_177924_aj) ? false : ((this.field_177939_ah != var2.field_177939_ah) ? false : ((this.field_177895_az != var2.field_177895_az) ? false : ((this.field_177897_ay != var2.field_177897_ay) ? false : ((this.field_177910_ax != var2.field_177910_ax) ? false : ((this.field_177889_aA != var2.field_177889_aA) ? false : ((this.field_177867_E != var2.field_177867_E) ? false : ((Float.compare(var2.field_177898_e, this.field_177898_e) != 0) ? false : ((Float.compare(var2.field_177917_i, this.field_177917_i) != 0) ? false : ((Float.compare(var2.field_177911_j, this.field_177911_j) != 0) ? false : ((Float.compare(var2.field_177913_k, this.field_177913_k) != 0) ? false : ((this.field_177908_aq != var2.field_177908_aq) ? false : ((this.field_177904_as != var2.field_177904_as) ? false : ((this.field_177906_ar != var2.field_177906_ar) ? false : ((this.field_177928_ap != var2.field_177928_ap) ? false : ((this.field_177878_I != var2.field_177878_I) ? false : ((this.field_177929_r != var2.field_177929_r) ? false : ((Float.compare(var2.field_177909_m, this.field_177909_m) != 0) ? false : ((Float.compare(var2.field_177896_d, this.field_177896_d) != 0) ? false : ((this.field_177927_s != var2.field_177927_s) ? false : ((this.field_177925_t != var2.field_177925_t) ? false : ((this.field_177866_D != var2.field_177866_D) ? false : ((this.field_177868_F != var2.field_177868_F) ? false : ((this.field_177944_x != var2.field_177944_x) ? false : ((this.field_177870_A != var2.field_177870_A) ? false : ((this.field_177921_v != var2.field_177921_v) ? false : ((this.field_177942_y != var2.field_177942_y) ? false : ((this.field_177940_z != var2.field_177940_z) ? false : ((this.field_177919_w != var2.field_177919_w) ? false : ((this.field_177871_B != var2.field_177871_B) ? false : ((this.field_177872_C == var2.field_177872_C))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
/*     */       } 
/*     */ 
/*     */       
/* 391 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 397 */       int var1 = (this.field_177899_b != 0.0F) ? Float.floatToIntBits(this.field_177899_b) : 0;
/* 398 */       var1 = 31 * var1 + ((this.field_177900_c != 0.0F) ? Float.floatToIntBits(this.field_177900_c) : 0);
/* 399 */       var1 = 31 * var1 + ((this.field_177896_d != 0.0F) ? Float.floatToIntBits(this.field_177896_d) : 0);
/* 400 */       var1 = 31 * var1 + ((this.field_177898_e != 0.0F) ? Float.floatToIntBits(this.field_177898_e) : 0);
/* 401 */       var1 = 31 * var1 + ((this.field_177893_f != 0.0F) ? Float.floatToIntBits(this.field_177893_f) : 0);
/* 402 */       var1 = 31 * var1 + ((this.field_177894_g != 0.0F) ? Float.floatToIntBits(this.field_177894_g) : 0);
/* 403 */       var1 = 31 * var1 + ((this.field_177915_h != 0.0F) ? Float.floatToIntBits(this.field_177915_h) : 0);
/* 404 */       var1 = 31 * var1 + ((this.field_177917_i != 0.0F) ? Float.floatToIntBits(this.field_177917_i) : 0);
/* 405 */       var1 = 31 * var1 + ((this.field_177911_j != 0.0F) ? Float.floatToIntBits(this.field_177911_j) : 0);
/* 406 */       var1 = 31 * var1 + ((this.field_177913_k != 0.0F) ? Float.floatToIntBits(this.field_177913_k) : 0);
/* 407 */       var1 = 31 * var1 + ((this.field_177907_l != 0.0F) ? Float.floatToIntBits(this.field_177907_l) : 0);
/* 408 */       var1 = 31 * var1 + ((this.field_177909_m != 0.0F) ? Float.floatToIntBits(this.field_177909_m) : 0);
/* 409 */       var1 = 31 * var1 + ((this.field_177903_n != 0.0F) ? Float.floatToIntBits(this.field_177903_n) : 0);
/* 410 */       var1 = 31 * var1 + ((this.field_177905_o != 0.0F) ? Float.floatToIntBits(this.field_177905_o) : 0);
/* 411 */       var1 = 31 * var1 + ((this.field_177933_p != 0.0F) ? Float.floatToIntBits(this.field_177933_p) : 0);
/* 412 */       var1 = 31 * var1 + ((this.field_177931_q != 0.0F) ? Float.floatToIntBits(this.field_177931_q) : 0);
/* 413 */       var1 = 31 * var1 + this.field_177929_r;
/* 414 */       var1 = 31 * var1 + (this.field_177927_s ? 1 : 0);
/* 415 */       var1 = 31 * var1 + (this.field_177925_t ? 1 : 0);
/* 416 */       var1 = 31 * var1 + this.field_177923_u;
/* 417 */       var1 = 31 * var1 + (this.field_177921_v ? 1 : 0);
/* 418 */       var1 = 31 * var1 + (this.field_177919_w ? 1 : 0);
/* 419 */       var1 = 31 * var1 + (this.field_177944_x ? 1 : 0);
/* 420 */       var1 = 31 * var1 + (this.field_177942_y ? 1 : 0);
/* 421 */       var1 = 31 * var1 + (this.field_177940_z ? 1 : 0);
/* 422 */       var1 = 31 * var1 + (this.field_177870_A ? 1 : 0);
/* 423 */       var1 = 31 * var1 + (this.field_177871_B ? 1 : 0);
/* 424 */       var1 = 31 * var1 + this.field_177872_C;
/* 425 */       var1 = 31 * var1 + (this.field_177866_D ? 1 : 0);
/* 426 */       var1 = 31 * var1 + this.field_177867_E;
/* 427 */       var1 = 31 * var1 + (this.field_177868_F ? 1 : 0);
/* 428 */       var1 = 31 * var1 + this.field_177869_G;
/* 429 */       var1 = 31 * var1 + this.field_177877_H;
/* 430 */       var1 = 31 * var1 + this.field_177878_I;
/* 431 */       var1 = 31 * var1 + this.field_177879_J;
/* 432 */       var1 = 31 * var1 + this.field_177880_K;
/* 433 */       var1 = 31 * var1 + this.field_177873_L;
/* 434 */       var1 = 31 * var1 + this.field_177874_M;
/* 435 */       var1 = 31 * var1 + this.field_177875_N;
/* 436 */       var1 = 31 * var1 + this.field_177876_O;
/* 437 */       var1 = 31 * var1 + this.field_177886_P;
/* 438 */       var1 = 31 * var1 + this.field_177885_Q;
/* 439 */       var1 = 31 * var1 + this.field_177888_R;
/* 440 */       var1 = 31 * var1 + this.field_177887_S;
/* 441 */       var1 = 31 * var1 + this.field_177882_T;
/* 442 */       var1 = 31 * var1 + this.field_177881_U;
/* 443 */       var1 = 31 * var1 + this.field_177884_V;
/* 444 */       var1 = 31 * var1 + this.field_177883_W;
/* 445 */       var1 = 31 * var1 + this.field_177891_X;
/* 446 */       var1 = 31 * var1 + this.field_177890_Y;
/* 447 */       var1 = 31 * var1 + this.field_177892_Z;
/* 448 */       var1 = 31 * var1 + this.field_177936_aa;
/* 449 */       var1 = 31 * var1 + this.field_177937_ab;
/* 450 */       var1 = 31 * var1 + this.field_177934_ac;
/* 451 */       var1 = 31 * var1 + this.field_177935_ad;
/* 452 */       var1 = 31 * var1 + this.field_177941_ae;
/* 453 */       var1 = 31 * var1 + this.field_177943_af;
/* 454 */       var1 = 31 * var1 + this.field_177938_ag;
/* 455 */       var1 = 31 * var1 + this.field_177939_ah;
/* 456 */       var1 = 31 * var1 + this.field_177922_ai;
/* 457 */       var1 = 31 * var1 + this.field_177924_aj;
/* 458 */       var1 = 31 * var1 + this.field_177918_ak;
/* 459 */       var1 = 31 * var1 + this.field_177920_al;
/* 460 */       var1 = 31 * var1 + this.field_177930_am;
/* 461 */       var1 = 31 * var1 + this.field_177932_an;
/* 462 */       var1 = 31 * var1 + this.field_177926_ao;
/* 463 */       var1 = 31 * var1 + this.field_177928_ap;
/* 464 */       var1 = 31 * var1 + this.field_177908_aq;
/* 465 */       var1 = 31 * var1 + this.field_177906_ar;
/* 466 */       var1 = 31 * var1 + this.field_177904_as;
/* 467 */       var1 = 31 * var1 + this.field_177902_at;
/* 468 */       var1 = 31 * var1 + this.field_177916_au;
/* 469 */       var1 = 31 * var1 + this.field_177914_av;
/* 470 */       var1 = 31 * var1 + this.field_177912_aw;
/* 471 */       var1 = 31 * var1 + this.field_177910_ax;
/* 472 */       var1 = 31 * var1 + this.field_177897_ay;
/* 473 */       var1 = 31 * var1 + this.field_177895_az;
/* 474 */       var1 = 31 * var1 + this.field_177889_aA;
/* 475 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public ChunkProviderSettings func_177864_b() {
/* 480 */       return new ChunkProviderSettings(this, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00002003";
/*     */     
/*     */     public ChunkProviderSettings.Factory func_177861_a(JsonElement p_177861_1_, Type p_177861_2_, JsonDeserializationContext p_177861_3_) {
/* 490 */       JsonObject var4 = p_177861_1_.getAsJsonObject();
/* 491 */       ChunkProviderSettings.Factory var5 = new ChunkProviderSettings.Factory();
/*     */ 
/*     */       
/*     */       try {
/* 495 */         var5.field_177899_b = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "coordinateScale", var5.field_177899_b);
/* 496 */         var5.field_177900_c = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "heightScale", var5.field_177900_c);
/* 497 */         var5.field_177898_e = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "lowerLimitScale", var5.field_177898_e);
/* 498 */         var5.field_177896_d = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "upperLimitScale", var5.field_177896_d);
/* 499 */         var5.field_177893_f = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "depthNoiseScaleX", var5.field_177893_f);
/* 500 */         var5.field_177894_g = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "depthNoiseScaleZ", var5.field_177894_g);
/* 501 */         var5.field_177915_h = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "depthNoiseScaleExponent", var5.field_177915_h);
/* 502 */         var5.field_177917_i = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "mainNoiseScaleX", var5.field_177917_i);
/* 503 */         var5.field_177911_j = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "mainNoiseScaleY", var5.field_177911_j);
/* 504 */         var5.field_177913_k = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "mainNoiseScaleZ", var5.field_177913_k);
/* 505 */         var5.field_177907_l = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "baseSize", var5.field_177907_l);
/* 506 */         var5.field_177909_m = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "stretchY", var5.field_177909_m);
/* 507 */         var5.field_177903_n = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "biomeDepthWeight", var5.field_177903_n);
/* 508 */         var5.field_177905_o = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "biomeDepthOffset", var5.field_177905_o);
/* 509 */         var5.field_177933_p = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "biomeScaleWeight", var5.field_177933_p);
/* 510 */         var5.field_177931_q = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var4, "biomeScaleOffset", var5.field_177931_q);
/* 511 */         var5.field_177929_r = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "seaLevel", var5.field_177929_r);
/* 512 */         var5.field_177927_s = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useCaves", var5.field_177927_s);
/* 513 */         var5.field_177925_t = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useDungeons", var5.field_177925_t);
/* 514 */         var5.field_177923_u = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dungeonChance", var5.field_177923_u);
/* 515 */         var5.field_177921_v = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useStrongholds", var5.field_177921_v);
/* 516 */         var5.field_177919_w = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useVillages", var5.field_177919_w);
/* 517 */         var5.field_177944_x = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useMineShafts", var5.field_177944_x);
/* 518 */         var5.field_177942_y = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useTemples", var5.field_177942_y);
/* 519 */         var5.field_177940_z = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useMonuments", var5.field_177940_z);
/* 520 */         var5.field_177870_A = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useRavines", var5.field_177870_A);
/* 521 */         var5.field_177871_B = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useWaterLakes", var5.field_177871_B);
/* 522 */         var5.field_177872_C = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "waterLakeChance", var5.field_177872_C);
/* 523 */         var5.field_177866_D = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useLavaLakes", var5.field_177866_D);
/* 524 */         var5.field_177867_E = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "lavaLakeChance", var5.field_177867_E);
/* 525 */         var5.field_177868_F = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "useLavaOceans", var5.field_177868_F);
/* 526 */         var5.field_177869_G = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "fixedBiome", var5.field_177869_G);
/*     */         
/* 528 */         if (var5.field_177869_G < 38 && var5.field_177869_G >= -1) {
/*     */           
/* 530 */           if (var5.field_177869_G >= BiomeGenBase.hell.biomeID)
/*     */           {
/* 532 */             var5.field_177869_G += 2;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 537 */           var5.field_177869_G = -1;
/*     */         } 
/*     */         
/* 540 */         var5.field_177877_H = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "biomeSize", var5.field_177877_H);
/* 541 */         var5.field_177878_I = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "riverSize", var5.field_177878_I);
/* 542 */         var5.field_177879_J = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dirtSize", var5.field_177879_J);
/* 543 */         var5.field_177880_K = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dirtCount", var5.field_177880_K);
/* 544 */         var5.field_177873_L = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dirtMinHeight", var5.field_177873_L);
/* 545 */         var5.field_177874_M = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dirtMaxHeight", var5.field_177874_M);
/* 546 */         var5.field_177875_N = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "gravelSize", var5.field_177875_N);
/* 547 */         var5.field_177876_O = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "gravelCount", var5.field_177876_O);
/* 548 */         var5.field_177886_P = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "gravelMinHeight", var5.field_177886_P);
/* 549 */         var5.field_177885_Q = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "gravelMaxHeight", var5.field_177885_Q);
/* 550 */         var5.field_177888_R = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "graniteSize", var5.field_177888_R);
/* 551 */         var5.field_177887_S = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "graniteCount", var5.field_177887_S);
/* 552 */         var5.field_177882_T = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "graniteMinHeight", var5.field_177882_T);
/* 553 */         var5.field_177881_U = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "graniteMaxHeight", var5.field_177881_U);
/* 554 */         var5.field_177884_V = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dioriteSize", var5.field_177884_V);
/* 555 */         var5.field_177883_W = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dioriteCount", var5.field_177883_W);
/* 556 */         var5.field_177891_X = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dioriteMinHeight", var5.field_177891_X);
/* 557 */         var5.field_177890_Y = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "dioriteMaxHeight", var5.field_177890_Y);
/* 558 */         var5.field_177892_Z = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "andesiteSize", var5.field_177892_Z);
/* 559 */         var5.field_177936_aa = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "andesiteCount", var5.field_177936_aa);
/* 560 */         var5.field_177937_ab = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "andesiteMinHeight", var5.field_177937_ab);
/* 561 */         var5.field_177934_ac = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "andesiteMaxHeight", var5.field_177934_ac);
/* 562 */         var5.field_177935_ad = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "coalSize", var5.field_177935_ad);
/* 563 */         var5.field_177941_ae = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "coalCount", var5.field_177941_ae);
/* 564 */         var5.field_177943_af = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "coalMinHeight", var5.field_177943_af);
/* 565 */         var5.field_177938_ag = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "coalMaxHeight", var5.field_177938_ag);
/* 566 */         var5.field_177939_ah = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "ironSize", var5.field_177939_ah);
/* 567 */         var5.field_177922_ai = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "ironCount", var5.field_177922_ai);
/* 568 */         var5.field_177924_aj = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "ironMinHeight", var5.field_177924_aj);
/* 569 */         var5.field_177918_ak = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "ironMaxHeight", var5.field_177918_ak);
/* 570 */         var5.field_177920_al = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "goldSize", var5.field_177920_al);
/* 571 */         var5.field_177930_am = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "goldCount", var5.field_177930_am);
/* 572 */         var5.field_177932_an = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "goldMinHeight", var5.field_177932_an);
/* 573 */         var5.field_177926_ao = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "goldMaxHeight", var5.field_177926_ao);
/* 574 */         var5.field_177928_ap = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "redstoneSize", var5.field_177928_ap);
/* 575 */         var5.field_177908_aq = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "redstoneCount", var5.field_177908_aq);
/* 576 */         var5.field_177906_ar = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "redstoneMinHeight", var5.field_177906_ar);
/* 577 */         var5.field_177904_as = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "redstoneMaxHeight", var5.field_177904_as);
/* 578 */         var5.field_177902_at = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "diamondSize", var5.field_177902_at);
/* 579 */         var5.field_177916_au = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "diamondCount", var5.field_177916_au);
/* 580 */         var5.field_177914_av = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "diamondMinHeight", var5.field_177914_av);
/* 581 */         var5.field_177912_aw = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "diamondMaxHeight", var5.field_177912_aw);
/* 582 */         var5.field_177910_ax = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "lapisSize", var5.field_177910_ax);
/* 583 */         var5.field_177897_ay = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "lapisCount", var5.field_177897_ay);
/* 584 */         var5.field_177895_az = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "lapisCenterHeight", var5.field_177895_az);
/* 585 */         var5.field_177889_aA = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var4, "lapisSpread", var5.field_177889_aA);
/*     */       }
/* 587 */       catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 592 */       return var5;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement func_177862_a(ChunkProviderSettings.Factory p_177862_1_, Type p_177862_2_, JsonSerializationContext p_177862_3_) {
/* 597 */       JsonObject var4 = new JsonObject();
/* 598 */       var4.addProperty("coordinateScale", Float.valueOf(p_177862_1_.field_177899_b));
/* 599 */       var4.addProperty("heightScale", Float.valueOf(p_177862_1_.field_177900_c));
/* 600 */       var4.addProperty("lowerLimitScale", Float.valueOf(p_177862_1_.field_177898_e));
/* 601 */       var4.addProperty("upperLimitScale", Float.valueOf(p_177862_1_.field_177896_d));
/* 602 */       var4.addProperty("depthNoiseScaleX", Float.valueOf(p_177862_1_.field_177893_f));
/* 603 */       var4.addProperty("depthNoiseScaleZ", Float.valueOf(p_177862_1_.field_177894_g));
/* 604 */       var4.addProperty("depthNoiseScaleExponent", Float.valueOf(p_177862_1_.field_177915_h));
/* 605 */       var4.addProperty("mainNoiseScaleX", Float.valueOf(p_177862_1_.field_177917_i));
/* 606 */       var4.addProperty("mainNoiseScaleY", Float.valueOf(p_177862_1_.field_177911_j));
/* 607 */       var4.addProperty("mainNoiseScaleZ", Float.valueOf(p_177862_1_.field_177913_k));
/* 608 */       var4.addProperty("baseSize", Float.valueOf(p_177862_1_.field_177907_l));
/* 609 */       var4.addProperty("stretchY", Float.valueOf(p_177862_1_.field_177909_m));
/* 610 */       var4.addProperty("biomeDepthWeight", Float.valueOf(p_177862_1_.field_177903_n));
/* 611 */       var4.addProperty("biomeDepthOffset", Float.valueOf(p_177862_1_.field_177905_o));
/* 612 */       var4.addProperty("biomeScaleWeight", Float.valueOf(p_177862_1_.field_177933_p));
/* 613 */       var4.addProperty("biomeScaleOffset", Float.valueOf(p_177862_1_.field_177931_q));
/* 614 */       var4.addProperty("seaLevel", Integer.valueOf(p_177862_1_.field_177929_r));
/* 615 */       var4.addProperty("useCaves", Boolean.valueOf(p_177862_1_.field_177927_s));
/* 616 */       var4.addProperty("useDungeons", Boolean.valueOf(p_177862_1_.field_177925_t));
/* 617 */       var4.addProperty("dungeonChance", Integer.valueOf(p_177862_1_.field_177923_u));
/* 618 */       var4.addProperty("useStrongholds", Boolean.valueOf(p_177862_1_.field_177921_v));
/* 619 */       var4.addProperty("useVillages", Boolean.valueOf(p_177862_1_.field_177919_w));
/* 620 */       var4.addProperty("useMineShafts", Boolean.valueOf(p_177862_1_.field_177944_x));
/* 621 */       var4.addProperty("useTemples", Boolean.valueOf(p_177862_1_.field_177942_y));
/* 622 */       var4.addProperty("useMonuments", Boolean.valueOf(p_177862_1_.field_177940_z));
/* 623 */       var4.addProperty("useRavines", Boolean.valueOf(p_177862_1_.field_177870_A));
/* 624 */       var4.addProperty("useWaterLakes", Boolean.valueOf(p_177862_1_.field_177871_B));
/* 625 */       var4.addProperty("waterLakeChance", Integer.valueOf(p_177862_1_.field_177872_C));
/* 626 */       var4.addProperty("useLavaLakes", Boolean.valueOf(p_177862_1_.field_177866_D));
/* 627 */       var4.addProperty("lavaLakeChance", Integer.valueOf(p_177862_1_.field_177867_E));
/* 628 */       var4.addProperty("useLavaOceans", Boolean.valueOf(p_177862_1_.field_177868_F));
/* 629 */       var4.addProperty("fixedBiome", Integer.valueOf(p_177862_1_.field_177869_G));
/* 630 */       var4.addProperty("biomeSize", Integer.valueOf(p_177862_1_.field_177877_H));
/* 631 */       var4.addProperty("riverSize", Integer.valueOf(p_177862_1_.field_177878_I));
/* 632 */       var4.addProperty("dirtSize", Integer.valueOf(p_177862_1_.field_177879_J));
/* 633 */       var4.addProperty("dirtCount", Integer.valueOf(p_177862_1_.field_177880_K));
/* 634 */       var4.addProperty("dirtMinHeight", Integer.valueOf(p_177862_1_.field_177873_L));
/* 635 */       var4.addProperty("dirtMaxHeight", Integer.valueOf(p_177862_1_.field_177874_M));
/* 636 */       var4.addProperty("gravelSize", Integer.valueOf(p_177862_1_.field_177875_N));
/* 637 */       var4.addProperty("gravelCount", Integer.valueOf(p_177862_1_.field_177876_O));
/* 638 */       var4.addProperty("gravelMinHeight", Integer.valueOf(p_177862_1_.field_177886_P));
/* 639 */       var4.addProperty("gravelMaxHeight", Integer.valueOf(p_177862_1_.field_177885_Q));
/* 640 */       var4.addProperty("graniteSize", Integer.valueOf(p_177862_1_.field_177888_R));
/* 641 */       var4.addProperty("graniteCount", Integer.valueOf(p_177862_1_.field_177887_S));
/* 642 */       var4.addProperty("graniteMinHeight", Integer.valueOf(p_177862_1_.field_177882_T));
/* 643 */       var4.addProperty("graniteMaxHeight", Integer.valueOf(p_177862_1_.field_177881_U));
/* 644 */       var4.addProperty("dioriteSize", Integer.valueOf(p_177862_1_.field_177884_V));
/* 645 */       var4.addProperty("dioriteCount", Integer.valueOf(p_177862_1_.field_177883_W));
/* 646 */       var4.addProperty("dioriteMinHeight", Integer.valueOf(p_177862_1_.field_177891_X));
/* 647 */       var4.addProperty("dioriteMaxHeight", Integer.valueOf(p_177862_1_.field_177890_Y));
/* 648 */       var4.addProperty("andesiteSize", Integer.valueOf(p_177862_1_.field_177892_Z));
/* 649 */       var4.addProperty("andesiteCount", Integer.valueOf(p_177862_1_.field_177936_aa));
/* 650 */       var4.addProperty("andesiteMinHeight", Integer.valueOf(p_177862_1_.field_177937_ab));
/* 651 */       var4.addProperty("andesiteMaxHeight", Integer.valueOf(p_177862_1_.field_177934_ac));
/* 652 */       var4.addProperty("coalSize", Integer.valueOf(p_177862_1_.field_177935_ad));
/* 653 */       var4.addProperty("coalCount", Integer.valueOf(p_177862_1_.field_177941_ae));
/* 654 */       var4.addProperty("coalMinHeight", Integer.valueOf(p_177862_1_.field_177943_af));
/* 655 */       var4.addProperty("coalMaxHeight", Integer.valueOf(p_177862_1_.field_177938_ag));
/* 656 */       var4.addProperty("ironSize", Integer.valueOf(p_177862_1_.field_177939_ah));
/* 657 */       var4.addProperty("ironCount", Integer.valueOf(p_177862_1_.field_177922_ai));
/* 658 */       var4.addProperty("ironMinHeight", Integer.valueOf(p_177862_1_.field_177924_aj));
/* 659 */       var4.addProperty("ironMaxHeight", Integer.valueOf(p_177862_1_.field_177918_ak));
/* 660 */       var4.addProperty("goldSize", Integer.valueOf(p_177862_1_.field_177920_al));
/* 661 */       var4.addProperty("goldCount", Integer.valueOf(p_177862_1_.field_177930_am));
/* 662 */       var4.addProperty("goldMinHeight", Integer.valueOf(p_177862_1_.field_177932_an));
/* 663 */       var4.addProperty("goldMaxHeight", Integer.valueOf(p_177862_1_.field_177926_ao));
/* 664 */       var4.addProperty("redstoneSize", Integer.valueOf(p_177862_1_.field_177928_ap));
/* 665 */       var4.addProperty("redstoneCount", Integer.valueOf(p_177862_1_.field_177908_aq));
/* 666 */       var4.addProperty("redstoneMinHeight", Integer.valueOf(p_177862_1_.field_177906_ar));
/* 667 */       var4.addProperty("redstoneMaxHeight", Integer.valueOf(p_177862_1_.field_177904_as));
/* 668 */       var4.addProperty("diamondSize", Integer.valueOf(p_177862_1_.field_177902_at));
/* 669 */       var4.addProperty("diamondCount", Integer.valueOf(p_177862_1_.field_177916_au));
/* 670 */       var4.addProperty("diamondMinHeight", Integer.valueOf(p_177862_1_.field_177914_av));
/* 671 */       var4.addProperty("diamondMaxHeight", Integer.valueOf(p_177862_1_.field_177912_aw));
/* 672 */       var4.addProperty("lapisSize", Integer.valueOf(p_177862_1_.field_177910_ax));
/* 673 */       var4.addProperty("lapisCount", Integer.valueOf(p_177862_1_.field_177897_ay));
/* 674 */       var4.addProperty("lapisCenterHeight", Integer.valueOf(p_177862_1_.field_177895_az));
/* 675 */       var4.addProperty("lapisSpread", Integer.valueOf(p_177862_1_.field_177889_aA));
/* 676 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 681 */       return func_177861_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 686 */       return func_177862_a((ChunkProviderSettings.Factory)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */