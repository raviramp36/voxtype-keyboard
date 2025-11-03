package com.voxtype.keyboard.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u0007J\u0006\u0010\u001c\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e2\u0006\u0010\u001b\u001a\u00020\u0007J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007J\u0018\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00072\b\b\u0002\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006&"}, d2 = {"Lcom/voxtype/keyboard/viewmodel/DictionaryViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "_searchResults", "", "Lcom/voxtype/keyboard/database/entities/DictionaryWord;", "allWords", "Landroidx/lifecycle/LiveData;", "getAllWords", "()Landroidx/lifecycle/LiveData;", "errorMessage", "getErrorMessage", "isLoading", "repository", "Lcom/voxtype/keyboard/repository/DictionaryRepository;", "searchResults", "getSearchResults", "addWord", "", "word", "language", "clearError", "clearSearchResults", "deleteWord", "getWordsByLanguage", "incrementWordFrequency", "searchWords", "query", "limit", "", "updateWord", "app_debug"})
public final class DictionaryViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.repository.DictionaryRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> allWords = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> _searchResults = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> searchResults = null;
    
    public DictionaryViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getAllWords() {
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
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getSearchResults() {
        return null;
    }
    
    public final void addWord(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    java.lang.String language) {
    }
    
    public final void updateWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word) {
    }
    
    public final void deleteWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word) {
    }
    
    public final void searchWords(@org.jetbrains.annotations.NotNull
    java.lang.String query, int limit) {
    }
    
    public final void incrementWordFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String word) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getWordsByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language) {
        return null;
    }
    
    public final void clearError() {
    }
    
    public final void clearSearchResults() {
    }
}