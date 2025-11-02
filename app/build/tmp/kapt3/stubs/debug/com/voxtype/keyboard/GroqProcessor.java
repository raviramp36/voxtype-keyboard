package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J*\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0002\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\bJ\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002J,\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0086@\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/voxtype/keyboard/GroqProcessor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "groqApi", "Lcom/voxtype/keyboard/GroqApi;", "fallbackProcessing", "", "text", "mode", "Lcom/voxtype/keyboard/GroqProcessor$TextMode;", "loadSettings", "", "processText", "rawText", "(Ljava/lang/String;Ljava/lang/String;Lcom/voxtype/keyboard/GroqProcessor$TextMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reloadSettings", "saveSettings", "apiKey", "baseUrl", "setupRetrofit", "transcribeAudio", "audioFile", "Ljava/io/File;", "language", "prompt", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TextMode", "app_debug"})
public final class GroqProcessor {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private com.voxtype.keyboard.GroqApi groqApi;
    
    public GroqProcessor(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final void loadSettings() {
    }
    
    private final void setupRetrofit(java.lang.String apiKey, java.lang.String baseUrl) {
    }
    
    public final void saveSettings(@org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull
    java.lang.String baseUrl) {
    }
    
    public final void reloadSettings() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processText(@org.jetbrains.annotations.NotNull
    java.lang.String rawText, @org.jetbrains.annotations.NotNull
    java.lang.String context, @org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.GroqProcessor.TextMode mode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String fallbackProcessing(java.lang.String text, com.voxtype.keyboard.GroqProcessor.TextMode mode) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object transcribeAudio(@org.jetbrains.annotations.NotNull
    java.io.File audioFile, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.Nullable
    java.lang.String prompt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/voxtype/keyboard/GroqProcessor$TextMode;", "", "(Ljava/lang/String;I)V", "GENERAL", "EMAIL", "CHAT", "FORMAL", "app_debug"})
    public static enum TextMode {
        /*public static final*/ GENERAL /* = new GENERAL() */,
        /*public static final*/ EMAIL /* = new EMAIL() */,
        /*public static final*/ CHAT /* = new CHAT() */,
        /*public static final*/ FORMAL /* = new FORMAL() */;
        
        TextMode() {
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.voxtype.keyboard.GroqProcessor.TextMode> getEntries() {
            return null;
        }
    }
}