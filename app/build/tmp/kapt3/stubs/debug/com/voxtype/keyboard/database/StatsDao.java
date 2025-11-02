package com.voxtype.keyboard.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/voxtype/keyboard/database/StatsDao;", "", "getAverageWordsSince", "", "startDate", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastWeekStats", "", "Lcom/voxtype/keyboard/database/DailyStats;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStats", "date", "getStatsSince", "getTotalWordsSince", "", "insertOrUpdate", "", "stats", "(Lcom/voxtype/keyboard/database/DailyStats;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface StatsDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertOrUpdate(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.DailyStats stats, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_stats WHERE date = :date")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStats(@org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.DailyStats> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_stats WHERE date >= :startDate ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStatsSince(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.DailyStats>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(totalWords) as total FROM daily_stats WHERE date >= :startDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWordsSince(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_stats ORDER BY date DESC LIMIT 7")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLastWeekStats(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.DailyStats>> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(totalWords) FROM daily_stats WHERE date >= :startDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageWordsSince(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion);
}