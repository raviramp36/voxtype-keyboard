package com.voxtype.keyboard.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u0004\u0018\u00010\bH\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/voxtype/keyboard/database/TranscriptionDao;", "", "getLastWeek", "", "Lcom/voxtype/keyboard/database/TranscriptionEntry;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecent", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToday", "getTodayCount", "getTodayWordCount", "insert", "", "transcription", "(Lcom/voxtype/keyboard/database/TranscriptionEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "app_debug"})
@androidx.room.Dao
public abstract interface TranscriptionDao {
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.TranscriptionEntry transcription, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.TranscriptionEntry transcription, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transcriptions ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.TranscriptionEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transcriptions WHERE date(timestamp/1000, \'unixepoch\') = date(\'now\') ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getToday(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.TranscriptionEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transcriptions WHERE date(timestamp/1000, \'unixepoch\') >= date(\'now\', \'-7 days\') ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLastWeek(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.TranscriptionEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM transcriptions WHERE date(timestamp/1000, \'unixepoch\') = date(\'now\')")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTodayCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(wordCount) FROM transcriptions WHERE date(timestamp/1000, \'unixepoch\') = date(\'now\')")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTodayWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}