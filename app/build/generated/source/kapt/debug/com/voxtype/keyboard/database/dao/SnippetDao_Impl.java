package com.voxtype.keyboard.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.voxtype.keyboard.database.DateConverters;
import com.voxtype.keyboard.database.entities.Snippet;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SnippetDao_Impl implements SnippetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Snippet> __insertionAdapterOfSnippet;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<Snippet> __deletionAdapterOfSnippet;

  private final EntityDeletionOrUpdateAdapter<Snippet> __updateAdapterOfSnippet;

  private final SharedSQLiteStatement __preparedStmtOfIncrementUsageCount;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSnippetsByCategory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSnippets;

  public SnippetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSnippet = new EntityInsertionAdapter<Snippet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `snippets` (`id`,`trigger`,`content`,`category`,`usageCount`,`createdDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Snippet entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTrigger() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTrigger());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        statement.bindLong(5, entity.getUsageCount());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
      }
    };
    this.__deletionAdapterOfSnippet = new EntityDeletionOrUpdateAdapter<Snippet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `snippets` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Snippet entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSnippet = new EntityDeletionOrUpdateAdapter<Snippet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `snippets` SET `id` = ?,`trigger` = ?,`content` = ?,`category` = ?,`usageCount` = ?,`createdDate` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Snippet entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTrigger() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTrigger());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        statement.bindLong(5, entity.getUsageCount());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfIncrementUsageCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE snippets SET usageCount = usageCount + 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSnippetsByCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM snippets WHERE category = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllSnippets = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM snippets";
        return _query;
      }
    };
  }

  @Override
  public Object insertSnippet(final Snippet snippet, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSnippet.insertAndReturnId(snippet);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSnippet(final Snippet snippet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSnippet.handle(snippet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSnippet(final Snippet snippet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSnippet.handle(snippet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementUsageCount(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementUsageCount.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfIncrementUsageCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSnippetsByCategory(final String category,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSnippetsByCategory.acquire();
        int _argIndex = 1;
        if (category == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, category);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteSnippetsByCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllSnippets(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSnippets.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllSnippets.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getSnippetById(final long id, final Continuation<? super Snippet> $completion) {
    final String _sql = "SELECT * FROM snippets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Snippet>() {
      @Override
      @Nullable
      public Snippet call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final Snippet _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _result = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSnippetByTrigger(final String trigger,
      final Continuation<? super Snippet> $completion) {
    final String _sql = "SELECT * FROM snippets WHERE trigger = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (trigger == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, trigger);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Snippet>() {
      @Override
      @Nullable
      public Snippet call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final Snippet _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _result = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Snippet>> getAllSnippets() {
    final String _sql = "SELECT * FROM snippets ORDER BY createdDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"snippets"}, new Callable<List<Snippet>>() {
      @Override
      @NonNull
      public List<Snippet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<Snippet> _result = new ArrayList<Snippet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Snippet _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Snippet>> getSnippetsByCategory(final String category) {
    final String _sql = "SELECT * FROM snippets WHERE category = ? ORDER BY createdDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"snippets"}, new Callable<List<Snippet>>() {
      @Override
      @NonNull
      public List<Snippet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<Snippet> _result = new ArrayList<Snippet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Snippet _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object searchSnippets(final String query,
      final Continuation<? super List<Snippet>> $completion) {
    final String _sql = "SELECT * FROM snippets WHERE trigger LIKE '%' || ? || '%' OR content LIKE '%' || ? || '%' ORDER BY usageCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Snippet>>() {
      @Override
      @NonNull
      public List<Snippet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<Snippet> _result = new ArrayList<Snippet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Snippet _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getMostUsedSnippets(final int limit,
      final Continuation<? super List<Snippet>> $completion) {
    final String _sql = "SELECT * FROM snippets ORDER BY usageCount DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Snippet>>() {
      @Override
      @NonNull
      public List<Snippet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrigger = CursorUtil.getColumnIndexOrThrow(_cursor, "trigger");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<Snippet> _result = new ArrayList<Snippet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Snippet _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrigger;
            if (_cursor.isNull(_cursorIndexOfTrigger)) {
              _tmpTrigger = null;
            } else {
              _tmpTrigger = _cursor.getString(_cursorIndexOfTrigger);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new Snippet(_tmpId,_tmpTrigger,_tmpContent,_tmpCategory,_tmpUsageCount,_tmpCreatedDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllCategories(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT category FROM snippets ORDER BY category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSnippetCountByCategory(final String category,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM snippets WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalSnippetCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM snippets";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
