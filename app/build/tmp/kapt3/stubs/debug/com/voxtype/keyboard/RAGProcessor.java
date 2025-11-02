package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J*\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0002\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nJ\f\u0010\u0018\u001a\u00020\n*\u00020\nH\u0002J\f\u0010\u0019\u001a\u00020\n*\u00020\nH\u0002J\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001cH\u0002J\f\u0010\u001d\u001a\u00020\n*\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/voxtype/keyboard/RAGProcessor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "ragService", "Lcom/voxtype/keyboard/RAGService;", "basicProcessing", "", "text", "getCurrentLanguage", "getRAGApiKey", "getRAGBaseUrl", "processText", "rawText", "mode", "Lcom/voxtype/keyboard/RAGProcessor$TextMode;", "(Ljava/lang/String;Ljava/lang/String;Lcom/voxtype/keyboard/RAGProcessor$TextMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSettings", "", "apiKey", "baseUrl", "addBasicPunctuation", "fixBasicCapitalization", "isPunctuation", "", "", "removeFillerWords", "TextMode", "app_debug"})
public final class RAGProcessor {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.RAGService ragService = null;
    
    public RAGProcessor(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processText(@org.jetbrains.annotations.NotNull
    java.lang.String rawText, @org.jetbrains.annotations.NotNull
    java.lang.String context, @org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.RAGProcessor.TextMode mode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String basicProcessing(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String removeFillerWords(java.lang.String $this$removeFillerWords) {
        return null;
    }
    
    private final java.lang.String addBasicPunctuation(java.lang.String $this$addBasicPunctuation) {
        return null;
    }
    
    private final java.lang.String fixBasicCapitalization(java.lang.String $this$fixBasicCapitalization) {
        return null;
    }
    
    private final boolean isPunctuation(char $this$isPunctuation) {
        return false;
    }
    
    private final java.lang.String getRAGApiKey() {
        return null;
    }
    
    private final java.lang.String getRAGBaseUrl() {
        return null;
    }
    
    private final java.lang.String getCurrentLanguage() {
        return null;
    }
    
    public final void saveSettings(@org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull
    java.lang.String baseUrl) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lcom/voxtype/keyboard/RAGProcessor$TextMode;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "GENERAL", "EMAIL", "CHAT", "FORMAL", "CODE", "CREATIVE", "app_debug"})
    public static enum TextMode {
        /*public static final*/ GENERAL /* = new GENERAL(null) */,
        /*public static final*/ EMAIL /* = new EMAIL(null) */,
        /*public static final*/ CHAT /* = new CHAT(null) */,
        /*public static final*/ FORMAL /* = new FORMAL(null) */,
        /*public static final*/ CODE /* = new CODE(null) */,
        /*public static final*/ CREATIVE /* = new CREATIVE(null) */;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String value = null;
        
        TextMode(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.voxtype.keyboard.RAGProcessor.TextMode> getEntries() {
            return null;
        }
    }
}