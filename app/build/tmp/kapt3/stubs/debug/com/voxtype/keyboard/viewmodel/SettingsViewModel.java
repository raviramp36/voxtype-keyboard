package com.voxtype.keyboard.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0015\u001a\u00020\u0016J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\tJ\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000b2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000b2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u001dJ\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u0007J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u0006\u0010 \u001a\u00020\u0016J\u0016\u0010!\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\tJ\u0016\u0010#\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u001bJ\u0016\u0010$\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u001dJ\u0016\u0010%\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/voxtype/keyboard/viewmodel/SettingsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "allPreferences", "Landroidx/lifecycle/LiveData;", "", "Lcom/voxtype/keyboard/database/entities/SettingsPreference;", "getAllPreferences", "()Landroidx/lifecycle/LiveData;", "errorMessage", "getErrorMessage", "isLoading", "repository", "Lcom/voxtype/keyboard/repository/SettingsRepository;", "clearError", "", "getBooleanSetting", "key", "defaultValue", "getFloatSetting", "", "getIntSetting", "", "getStringSetting", "initializeDefaultSettings", "resetToDefaults", "setBooleanSetting", "value", "setFloatSetting", "setIntSetting", "setStringSetting", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.repository.SettingsRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference>> allPreferences = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference>> getAllPreferences() {
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
    
    private final void initializeDefaultSettings() {
    }
    
    public final void setBooleanSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getBooleanSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean defaultValue) {
        return null;
    }
    
    public final void setStringSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getStringSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String defaultValue) {
        return null;
    }
    
    public final void setIntSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, int value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getIntSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defaultValue) {
        return null;
    }
    
    public final void setFloatSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, float value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Float> getFloatSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, float defaultValue) {
        return null;
    }
    
    public final void resetToDefaults() {
    }
    
    public final void clearError() {
    }
}