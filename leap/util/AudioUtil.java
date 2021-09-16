/*    */ package leap.util;
/*    */ 
/*    */ import paulscode.sound.Library;
/*    */ import paulscode.sound.SoundSystem;
/*    */ import paulscode.sound.SoundSystemConfig;
/*    */ import paulscode.sound.SoundSystemException;
/*    */ import paulscode.sound.codecs.CodecWav;
/*    */ import paulscode.sound.libraries.LibraryJavaSound;
/*    */ import paulscode.sound.libraries.LibraryLWJGLOpenAL;
/*    */ 
/*    */ public class AudioUtil
/*    */ {
/*    */   public static void init() {
/*    */     Class<Library> libraryType;
/* 15 */     boolean aLCompatible = SoundSystem.libraryCompatible(LibraryLWJGLOpenAL.class);
/* 16 */     boolean jSCompatible = SoundSystem.libraryCompatible(LibraryJavaSound.class);
/*    */     
/*    */     try {
/* 19 */       SoundSystemConfig.addLibrary(LibraryJavaSound.class);
/* 20 */     } catch (SoundSystemException e) {
/* 21 */       System.err.println("error linking with the LibraryJavaSound plug-in");
/*    */     } 
/*    */     try {
/* 24 */       SoundSystemConfig.setCodec("wav", CodecWav.class);
/* 25 */     } catch (SoundSystemException e) {
/* 26 */       System.err.println("error linking with the CodecWav plug-in");
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 31 */     if (jSCompatible) {
/* 32 */       Class<LibraryJavaSound> clazz = LibraryJavaSound.class;
/*    */     } else {
/* 34 */       libraryType = Library.class;
/*    */     }  try {
/* 36 */       mySoundSystem = new SoundSystem(libraryType);
/* 37 */     } catch (SoundSystemException sse) {
/*    */       
/* 39 */       sse.printStackTrace();
/*    */     } 
/*    */   }
/*    */   private static SoundSystem mySoundSystem;
/*    */   public static void PlaySound(String filename) {
/* 44 */     boolean priority = false;
/* 45 */     String sourcename = "Source 1";
/* 46 */     boolean loop = false;
/* 47 */     float x = 0.0F, y = 0.0F, z = 0.0F;
/* 48 */     int aModel = 1;
/* 49 */     float rFactor = SoundSystemConfig.getDefaultRolloff();
/* 50 */     mySoundSystem.newSource(false, sourcename, filename, false, x, y, z, aModel, rFactor);
/*    */   }
/*    */   
/*    */   public static void PlaySound() {
/* 54 */     boolean priority = false;
/* 55 */     String sourcename = "Source 1";
/* 56 */     String filename = "explosion.wav";
/* 57 */     boolean loop = false;
/* 58 */     float x = 0.0F, y = 0.0F, z = 0.0F;
/* 59 */     int aModel = 1;
/* 60 */     float rFactor = SoundSystemConfig.getDefaultRolloff();
/* 61 */     mySoundSystem.newSource(false, sourcename, filename, false, x, y, z, aModel, rFactor);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\AudioUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */