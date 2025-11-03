package com.voxtype.keyboard.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J \u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0004J \u0010\u0017\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00072\u0006\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J \u0010#\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010$\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010%\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010&\u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\'\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006("}, d2 = {"Lcom/voxtype/keyboard/database/dao/UserStatisticsDao;", "", "deleteAllStatistics", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStatistics", "statistics", "Lcom/voxtype/keyboard/database/entities/UserStatistics;", "(Lcom/voxtype/keyboard/database/entities/UserStatistics;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStatisticsBeforeDate", "beforeDate", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStatistics", "Lkotlinx/coroutines/flow/Flow;", "", "getAverageWpmInRange", "", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastMonthStatistics", "getLastWeekStatistics", "getMaxWpmInRange", "getRecentStatistics", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatisticsByDate", "date", "getStatisticsByDateRange", "getStatisticsById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWordsTypedInRange", "getUniqueAppsUsedInRange", "getUniqueLanguagesUsedInRange", "insertStatistics", "updateStatistics", "app_debug"})
@androidx.room.Dao
public abstract interface UserStatisticsDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertStatistics(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserStatistics statistics, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateStatistics(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserStatistics statistics, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteStatistics(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserStatistics statistics, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStatisticsById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserStatistics> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics WHERE date = :date LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStatisticsByDate(@org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserStatistics> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> getAllStatistics();
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStatisticsByDateRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRecentStatistics(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(wordsTyped) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWordsTypedInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(wpm) FROM user_statistics WHERE date >= :startDate AND date <= :endDate AND wpm > 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageWpmInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion);
    
    @androidx.room.Query(value = "SELECT MAX(wpm) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getMaxWpmInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT CASE WHEN appsUsed != \'\' THEN appsUsed END) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUniqueAppsUsedInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT CASE WHEN languagesUsed != \'\' THEN languagesUsed END) FROM user_statistics WHERE date >= :startDate AND date <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUniqueLanguagesUsedInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT 7")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLastWeekStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT 30")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLastMonthStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_statistics WHERE date < :beforeDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteStatisticsBeforeDate(@org.jetbrains.annotations.NotNull
    java.lang.String beforeDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_statistics")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}