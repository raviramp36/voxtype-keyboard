package com.voxtype.keyboard.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002J&\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010!\u001a\u00020\u000eH\u0002J\b\u0010\"\u001a\u00020\u000eH\u0002J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/voxtype/keyboard/fragments/SnippetsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "addSnippetFab", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "repository", "Lcom/voxtype/keyboard/repository/SnippetRepository;", "snippetsAdapter", "Lcom/voxtype/keyboard/fragments/SnippetsAdapter;", "snippetsRecycler", "Landroidx/recyclerview/widget/RecyclerView;", "addSnippet", "", "title", "", "content", "deleteSnippet", "snippet", "Lcom/voxtype/keyboard/database/entities/Snippet;", "initializeViews", "view", "Landroid/view/View;", "observeData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "setupRecyclerView", "showAddSnippetDialog", "showEditSnippetDialog", "updateSnippet", "useSnippet", "app_debug"})
public final class SnippetsFragment extends androidx.fragment.app.Fragment {
    private androidx.recyclerview.widget.RecyclerView snippetsRecycler;
    private com.google.android.material.floatingactionbutton.FloatingActionButton addSnippetFab;
    private com.voxtype.keyboard.fragments.SnippetsAdapter snippetsAdapter;
    private com.voxtype.keyboard.database.VoxTypeDatabase database;
    private com.voxtype.keyboard.repository.SnippetRepository repository;
    
    public SnippetsFragment() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews(android.view.View view) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeData() {
    }
    
    private final void showAddSnippetDialog() {
    }
    
    private final void showEditSnippetDialog(com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    private final void addSnippet(java.lang.String title, java.lang.String content) {
    }
    
    private final void updateSnippet(com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    private final void deleteSnippet(com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
    
    private final void useSnippet(com.voxtype.keyboard.database.entities.Snippet snippet) {
    }
}