package com.voxtype.keyboard.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\bJ\u0006\u0010 \u001a\u00020\u001cJ\u0006\u0010!\u001a\u00020\u001cJ\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\rJ\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u000f2\u0006\u0010\u001f\u001a\u00020\bJ\b\u0010%\u001a\u00020\u001cH\u0002J\u000e\u0010&\u001a\u00020\u001c2\u0006\u0010\'\u001a\u00020\bJ\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\rJ\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\rR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011\u00a8\u0006*"}, d2 = {"Lcom/voxtype/keyboard/viewmodel/SnippetViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_categories", "Landroidx/lifecycle/MutableLiveData;", "", "", "_errorMessage", "_isLoading", "", "_searchResults", "Lcom/voxtype/keyboard/database/entities/Snippet;", "allSnippets", "Landroidx/lifecycle/LiveData;", "getAllSnippets", "()Landroidx/lifecycle/LiveData;", "categories", "getCategories", "errorMessage", "getErrorMessage", "isLoading", "repository", "Lcom/voxtype/keyboard/repository/SnippetRepository;", "searchResults", "getSearchResults", "addSnippet", "", "trigger", "content", "category", "clearError", "clearSearchResults", "deleteSnippet", "snippet", "getSnippetsByCategory", "loadCategories", "searchSnippets", "query", "updateSnippet", "useSnippet", "app_debug"})
public final class SnippetViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.repository.SnippetRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> allSnippets = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> _searchResults = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> searchResults = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<java.lang.String>> _categories = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<java.lang.String>> categories = null;
    
    public SnippetViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getAllSnippets() {
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
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getSearchResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<java.lang.String>> getCategories() {
        return null;
    }
    
    public final void addSnippet(@org.jetbrains.annotations.NotNull
    java.lang.String trigger, @org.jetbrains.annotations.NotNull
    java.lang.String content, @org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    public final void updateSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    public final void deleteSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    public final void searchSnippets(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void useSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getSnippetsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    private final void loadCategories() {
    }
    
    public final void clearError() {
    }
    
    public final void clearSearchResults() {
    }
}