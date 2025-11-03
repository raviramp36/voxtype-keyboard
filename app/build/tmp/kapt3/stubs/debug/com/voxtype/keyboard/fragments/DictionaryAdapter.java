package com.voxtype.keyboard.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0017B-\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\fJ\u001c\u0010\u000f\u001a\u00020\u00062\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0014\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\nR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/voxtype/keyboard/fragments/DictionaryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/voxtype/keyboard/fragments/DictionaryAdapter$DictionaryViewHolder;", "onEditClick", "Lkotlin/Function1;", "Lcom/voxtype/keyboard/database/entities/UserDictionaryEntry;", "", "onDeleteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "words", "", "getItemCount", "", "getWordAt", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateWords", "newWords", "DictionaryViewHolder", "app_debug"})
public final class DictionaryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.voxtype.keyboard.fragments.DictionaryAdapter.DictionaryViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.entities.UserDictionaryEntry, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.entities.UserDictionaryEntry, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry> words;
    
    public DictionaryAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry, kotlin.Unit> onDeleteClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.voxtype.keyboard.fragments.DictionaryAdapter.DictionaryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.fragments.DictionaryAdapter.DictionaryViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateWords(@org.jetbrains.annotations.NotNull
    java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry> newWords) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.voxtype.keyboard.database.entities.UserDictionaryEntry getWordAt(int position) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/voxtype/keyboard/fragments/DictionaryAdapter$DictionaryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/voxtype/keyboard/fragments/DictionaryAdapter;Landroid/view/View;)V", "deleteButton", "Landroid/widget/ImageButton;", "getDeleteButton", "()Landroid/widget/ImageButton;", "editButton", "getEditButton", "replacementText", "Landroid/widget/TextView;", "getReplacementText", "()Landroid/widget/TextView;", "wordText", "getWordText", "app_debug"})
    public final class DictionaryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView wordText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView replacementText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton editButton = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton deleteButton = null;
        
        public DictionaryViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getWordText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getReplacementText() {
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