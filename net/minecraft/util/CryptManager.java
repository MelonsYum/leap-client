/*     */ package net.minecraft.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.KeyPair;
/*     */ import java.security.KeyPairGenerator;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.PublicKey;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class CryptManager
/*     */ {
/*  29 */   private static final Logger field_180198_a = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001483";
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey createNewSharedKey() {
/*     */     try {
/*  39 */       KeyGenerator var0 = KeyGenerator.getInstance("AES");
/*  40 */       var0.init(128);
/*  41 */       return var0.generateKey();
/*     */     }
/*  43 */     catch (NoSuchAlgorithmException var1) {
/*     */       
/*  45 */       throw new Error(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static KeyPair generateKeyPair() {
/*     */     try {
/*  56 */       KeyPairGenerator var0 = KeyPairGenerator.getInstance("RSA");
/*  57 */       var0.initialize(1024);
/*  58 */       return var0.generateKeyPair();
/*     */     }
/*  60 */     catch (NoSuchAlgorithmException var1) {
/*     */       
/*  62 */       var1.printStackTrace();
/*  63 */       field_180198_a.error("Key pair generation failed!");
/*  64 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getServerIdHash(String p_75895_0_, PublicKey p_75895_1_, SecretKey p_75895_2_) {
/*     */     try {
/*  75 */       return digestOperation("SHA-1", new byte[][] { p_75895_0_.getBytes("ISO_8859_1"), p_75895_2_.getEncoded(), p_75895_1_.getEncoded() });
/*     */     }
/*  77 */     catch (UnsupportedEncodingException var4) {
/*     */       
/*  79 */       var4.printStackTrace();
/*  80 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] digestOperation(String p_75893_0_, byte[]... p_75893_1_) {
/*     */     try {
/*  91 */       MessageDigest var2 = MessageDigest.getInstance(p_75893_0_);
/*  92 */       byte[][] var3 = p_75893_1_;
/*  93 */       int var4 = p_75893_1_.length;
/*     */       
/*  95 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/*  97 */         byte[] var6 = var3[var5];
/*  98 */         var2.update(var6);
/*     */       } 
/*     */       
/* 101 */       return var2.digest();
/*     */     }
/* 103 */     catch (NoSuchAlgorithmException var7) {
/*     */       
/* 105 */       var7.printStackTrace();
/* 106 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PublicKey decodePublicKey(byte[] p_75896_0_) {
/*     */     try {
/* 117 */       X509EncodedKeySpec var1 = new X509EncodedKeySpec(p_75896_0_);
/* 118 */       KeyFactory var2 = KeyFactory.getInstance("RSA");
/* 119 */       return var2.generatePublic(var1);
/*     */     }
/* 121 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*     */ 
/*     */     
/*     */     }
/* 125 */     catch (InvalidKeySpecException invalidKeySpecException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     field_180198_a.error("Public key reconstitute failed!");
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecretKey decryptSharedKey(PrivateKey p_75887_0_, byte[] p_75887_1_) {
/* 139 */     return new SecretKeySpec(decryptData(p_75887_0_, p_75887_1_), "AES");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] encryptData(Key p_75894_0_, byte[] p_75894_1_) {
/* 147 */     return cipherOperation(1, p_75894_0_, p_75894_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] decryptData(Key p_75889_0_, byte[] p_75889_1_) {
/* 155 */     return cipherOperation(2, p_75889_0_, p_75889_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] cipherOperation(int p_75885_0_, Key p_75885_1_, byte[] p_75885_2_) {
/*     */     try {
/* 165 */       return createTheCipherInstance(p_75885_0_, p_75885_1_.getAlgorithm(), p_75885_1_).doFinal(p_75885_2_);
/*     */     }
/* 167 */     catch (IllegalBlockSizeException var4) {
/*     */       
/* 169 */       var4.printStackTrace();
/*     */     }
/* 171 */     catch (BadPaddingException var5) {
/*     */       
/* 173 */       var5.printStackTrace();
/*     */     } 
/*     */     
/* 176 */     field_180198_a.error("Cipher data failed!");
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Cipher createTheCipherInstance(int p_75886_0_, String p_75886_1_, Key p_75886_2_) {
/*     */     try {
/* 187 */       Cipher var3 = Cipher.getInstance(p_75886_1_);
/* 188 */       var3.init(p_75886_0_, p_75886_2_);
/* 189 */       return var3;
/*     */     }
/* 191 */     catch (InvalidKeyException var4) {
/*     */       
/* 193 */       var4.printStackTrace();
/*     */     }
/* 195 */     catch (NoSuchAlgorithmException var5) {
/*     */       
/* 197 */       var5.printStackTrace();
/*     */     }
/* 199 */     catch (NoSuchPaddingException var6) {
/*     */       
/* 201 */       var6.printStackTrace();
/*     */     } 
/*     */     
/* 204 */     field_180198_a.error("Cipher creation failed!");
/* 205 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Cipher func_151229_a(int p_151229_0_, Key p_151229_1_) {
/*     */     try {
/* 212 */       Cipher var2 = Cipher.getInstance("AES/CFB8/NoPadding");
/* 213 */       var2.init(p_151229_0_, p_151229_1_, new IvParameterSpec(p_151229_1_.getEncoded()));
/* 214 */       return var2;
/*     */     }
/* 216 */     catch (GeneralSecurityException var3) {
/*     */       
/* 218 */       throw new RuntimeException(var3);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\CryptManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */