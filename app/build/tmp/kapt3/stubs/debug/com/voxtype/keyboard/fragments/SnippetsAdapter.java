package com.voxtype.keyboard.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018BA\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\rJ\u001c\u0010\u0010\u001a\u00020\u00062\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0014\u0010\u0016\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/voxtype/keyboard/fragments/SnippetsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/voxtype/keyboard/fragments/SnippetsAdapter$SnippetViewHolder;", "onEditClick", "Lkotlin/Function1;", "Lcom/voxtype/keyboard/database/entities/Snippet;", "", "onDeleteClick", "onUseClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "snippets", "", "getItemCount", "", "getSnippetAt", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateSnippets", "newSnippets", "SnippetViewHolder", "app_debug"})
public final class SnippetsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.voxtype.keyboard.fragments.SnippetsAdapter.SnippetViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onUseClick = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.voxtype.keyboard.database.entities.Snippet> snippets;
    
    public SnippetsAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onDeleteClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.entities.Snippet, kotlin.Unit> onUseClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.voxtype.keyboard.fragments.SnippetsAdapter.SnippetViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.fragments.SnippetsAdapter.SnippetViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateSnippets(@org.jetbrains.annotations.NotNull
    java.util.List<com.voxtype.keyboard.database.entities.Snippet> newSnippets) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.voxtype.keyboard.database.entities.Snippet getSnippetAt(int position) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/voxtype/keyboard/fragments/SnippetsAdapter$SnippetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/voxtype/keyboard/fragments/SnippetsAdapter;Landroid/view/View;)V", "contentText", "Landroid/widget/TextView;", "getContentText", "()Landroid/widget/TextView;", "deleteButton", "Landroid/widget/ImageButton;", "getDeleteButton", "()Landroid/widget/ImageButton;", "editButton", "getEditButton", "titleText", "getTitleText", "useButton", "Lcom/google/android/material/button/MaterialButton;", "getUseButton", "()Lcom/google/android/material/button/MaterialButton;", "app_debug"})
    public final class SnippetViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView titleText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView contentText = null;
        @org.jetbrains.annotations.NotNull
        private final com.google.android.material.button.MaterialButton useButton = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton editButton = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton deleteButton = null;
        
        public SnippetViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTitleText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getContentText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.google.android.material.button.MaterialButton getUseButton() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getEditButton() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getDeleteButton() {
            return null;
        }
    }
}