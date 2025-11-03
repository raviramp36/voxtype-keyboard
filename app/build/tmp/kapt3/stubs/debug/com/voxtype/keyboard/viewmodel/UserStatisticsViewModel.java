package com.voxtype.keyboard.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020$J\u0016\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tJ\u000e\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0013J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0002J.\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\tJ\b\u00101\u001a\u00020$H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015\u00a8\u00062"}, d2 = {"Lcom/voxtype/keyboard/viewmodel/UserStatisticsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_averageWpmThisWeek", "Landroidx/lifecycle/MutableLiveData;", "", "_errorMessage", "", "_isLoading", "", "_monthlyStats", "", "Lcom/voxtype/keyboard/database/entities/UserStatistics;", "_totalWordsThisWeek", "", "_weeklyStats", "allStatistics", "Landroidx/lifecycle/LiveData;", "getAllStatistics", "()Landroidx/lifecycle/LiveData;", "averageWpmThisWeek", "getAverageWpmThisWeek", "errorMessage", "getErrorMessage", "isLoading", "monthlyStats", "getMonthlyStats", "repository", "Lcom/voxtype/keyboard/repository/UserStatisticsRepository;", "totalWordsThisWeek", "getTotalWordsThisWeek", "weeklyStats", "getWeeklyStats", "clearError", "", "clearOldStatistics", "getStatisticsByDateRange", "startDate", "endDate", "getTodayStatistics", "loadMonthlyStatistics", "loadWeeklyStatistics", "recordTypingSession", "wordsTyped", "wpm", "appPackage", "language", "refreshStatistics", "app_debug"})
public final class UserStatisticsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.repository.UserStatisticsRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> allStatistics = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> _weeklyStats = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> weeklyStats = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> _monthlyStats = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> monthlyStats = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _totalWordsThisWeek = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalWordsThisWeek = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Float> _averageWpmThisWeek = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Float> averageWpmThisWeek = null;
    
    public UserStatisticsViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> getAllStatistics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> getWeeklyStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.UserStatistics>> getMonthlyStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalWordsThisWeek() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Float> getAverageWpmThisWeek() {
        return null;
    }
    
    public final void recordTypingSession(int wordsTyped, float wpm, @org.jetbrains.annotations.Nullable
    java.lang.String appPackage, @org.jetbrains.annotations.Nullable
    java.lang.String language) {
    }
    
    public final void getStatisticsByDateRange(@org.jetbrains.annotations.NotNull
    java.lang.String startDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate) {
    }
    
    private final void loadWeeklyStatistics() {
    }
    
    private final void loadMonthlyStatistics() {
    }
    
    private final void refreshStatistics() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.voxtype.keyboard.database.entities.UserStatistics> getTodayStatistics() {
        return null;
    }
    
    public final void clearOldStatistics() {
    }
    
    public final void clearError() {
    }
}