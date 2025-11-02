package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u00c6\u0003JT\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013\u00a8\u0006#"}, d2 = {"Lcom/voxtype/keyboard/WhisperResponse;", "", "text", "", "language", "duration", "", "words", "", "Lcom/voxtype/keyboard/Word;", "segments", "Lcom/voxtype/keyboard/Segment;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/util/List;Ljava/util/List;)V", "getDuration", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getLanguage", "()Ljava/lang/String;", "getSegments", "()Ljava/util/List;", "getText", "getWords", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/util/List;Ljava/util/List;)Lcom/voxtype/keyboard/WhisperResponse;", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class WhisperResponse {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String text = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String language = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Float duration = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.voxtype.keyboard.Word> words = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.voxtype.keyboard.Segment> segments = null;
    
    public WhisperResponse(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.Nullable
    java.lang.String language, @org.jetbrains.annotations.Nullable
    java.lang.Float duration, @org.jetbrains.annotations.Nullable
    java.util.List<com.voxtype.keyboard.Word> words, @org.jetbrains.annotations.Nullable
    java.util.List<com.voxtype.keyboard.Segment> segments) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getText() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Float getDuration() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.voxtype.keyboard.Word> getWords() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.voxtype.keyboard.Segment> getSegments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Float component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.voxtype.keyboard.Word> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.voxtype.keyboard.Segment> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.voxtype.keyboard.WhisperResponse copy(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.Nullable
    java.lang.String language, @org.jetbrains.annotations.Nullable
    java.lang.Float duration, @org.jetbrains.annotations.Nullable
    java.util.List<com.voxtype.keyboard.Word> words, @org.jetbrains.annotations.Nullable
    java.util.List<com.voxtype.keyboard.Segment> segments) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}