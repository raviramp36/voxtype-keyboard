package com.voxtype.keyboard.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J:\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0019J\u001e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0006\u0010#\u001a\u00020\u0010J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\b\b\u0002\u0010%\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010&J\u0018\u0010\'\u001a\u0004\u0018\u00010\u001b2\u0006\u0010(\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0017J$\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0006\u0010*\u001a\u00020\u0010J\u001e\u0010+\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0006\u0010,\u001a\u00020\u0010J\u001a\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\b\u0010/\u001a\u0004\u0018\u00010\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/voxtype/keyboard/repository/UserStatisticsRepository;", "", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "(Lcom/voxtype/keyboard/database/VoxTypeDatabase;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "userStatisticsDao", "Lcom/voxtype/keyboard/database/dao/UserStatisticsDao;", "addOrUpdateTodayStatistics", "", "wordsTyped", "", "wpm", "", "appPackage", "", "language", "(IFLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllStatistics", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStatisticsBeforeDate", "beforeDate", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStatistics", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/voxtype/keyboard/database/entities/UserStatistics;", "getAverageWpmInRange", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastMonthStatistics", "getLastWeekStatistics", "getMaxWpmInRange", "getMonthAgoDateString", "getRecentStatistics", "limit", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatisticsByDate", "date", "getStatisticsByDateRange", "getTodayDateString", "getTotalWordsTypedInRange", "getWeekAgoDateString", "updateCommaSeparatedList", "existingList", "newItem", "app_debug"})
public final class UserStatisticsRepository {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.VoxTypeDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.dao.UserStatisticsDao userStatisticsDao = null;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public UserStatisticsRepository(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.VoxTypeDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> getAllStatistics() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addOrUpdateTodayStatistics(int wordsTyped, float wpm, @org.jetbrains.annotations.Nullable
    java.lang.String appPackage, @org.jetbrains.annotations.Nullable
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStatisticsByDate(@org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserStatistics> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStatisticsByDateRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getRecentStatistics(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalWordsTypedInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAverageWpmInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMaxWpmInRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLastWeekStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLastMonthStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteStatisticsBeforeDate(@org.jetbrains.annotations.NotNull
    java.lang.String beforeDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String updateCommaSeparatedList(java.lang.String existingList, java.lang.String newItem) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTodayDateString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWeekAgoDateString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMonthAgoDateString() {
        return null;
    }
}