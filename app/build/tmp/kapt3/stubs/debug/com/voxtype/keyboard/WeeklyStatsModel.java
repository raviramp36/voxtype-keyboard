package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003JW\u0010 \u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\f\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0006H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016\u00a8\u0006\'"}, d2 = {"Lcom/voxtype/keyboard/WeeklyStatsModel;", "", "dailyStats", "", "Lcom/voxtype/keyboard/database/DailyStats;", "totalWords", "", "totalTranscriptions", "totalDuration", "", "averageWordsPerDay", "mostProductiveDay", "vocabularySize", "(Ljava/util/List;IIFFLcom/voxtype/keyboard/database/DailyStats;I)V", "getAverageWordsPerDay", "()F", "getDailyStats", "()Ljava/util/List;", "getMostProductiveDay", "()Lcom/voxtype/keyboard/database/DailyStats;", "getTotalDuration", "getTotalTranscriptions", "()I", "getTotalWords", "getVocabularySize", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class WeeklyStatsModel {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.voxtype.keyboard.database.DailyStats> dailyStats = null;
    private final int totalWords = 0;
    private final int totalTranscriptions = 0;
    private final float totalDuration = 0.0F;
    private final float averageWordsPerDay = 0.0F;
    @org.jetbrains.annotations.Nullable
    private final com.voxtype.keyboard.database.DailyStats mostProductiveDay = null;
    private final int vocabularySize = 0;
    
    public WeeklyStatsModel(@org.jetbrains.annotations.NotNull
    java.util.List<com.voxtype.keyboard.database.DailyStats> dailyStats, int totalWords, int totalTranscriptions, float totalDuration, float averageWordsPerDay, @org.jetbrains.annotations.Nullable
    com.voxtype.keyboard.database.DailyStats mostProductiveDay, int vocabularySize) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.voxtype.keyboard.database.DailyStats> getDailyStats() {
        return null;
    }
    
    public final int getTotalWords() {
        return 0;
    }
    
    public final int getTotalTranscriptions() {
        return 0;
    }
    
    public final float getTotalDuration() {
        return 0.0F;
    }
    
    public final float getAverageWordsPerDay() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.voxtype.keyboard.database.DailyStats getMostProductiveDay() {
        return null;
    }
    
    public final int getVocabularySize() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.voxtype.keyboard.database.DailyStats> component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.voxtype.keyboard.database.DailyStats component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.voxtype.keyboard.WeeklyStatsModel copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.voxtype.keyboard.database.DailyStats> dailyStats, int totalWords, int totalTranscriptions, float totalDuration, float averageWordsPerDay, @org.jetbrains.annotations.Nullable
    com.voxtype.keyboard.database.DailyStats mostProductiveDay, int vocabularySize) {
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