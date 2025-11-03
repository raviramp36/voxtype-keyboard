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
import com.voxtype.keyboard.database.entities.UserDictionaryEntry;
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
public final class UserDictionaryEntryDao_Impl implements UserDictionaryEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserDictionaryEntry> __insertionAdapterOfUserDictionaryEntry;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<UserDictionaryEntry> __deletionAdapterOfUserDictionaryEntry;

  private final EntityDeletionOrUpdateAdapter<UserDictionaryEntry> __updateAdapterOfUserDictionaryEntry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWordByText;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllWords;

  public UserDictionaryEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserDictionaryEntry = new EntityInsertionAdapter<UserDictionaryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_dictionary_entries` (`id`,`word`,`replacement`,`createdDate`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserDictionaryEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        if (entity.getReplacement() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReplacement());
        }
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
      }
    };
    this.__deletionAdapterOfUserDictionaryEntry = new EntityDeletionOrUpdateAdapter<UserDictionaryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `user_dictionary_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserDictionaryEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfUserDictionaryEntry = new EntityDeletionOrUpdateAdapter<UserDictionaryEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_dictionary_entries` SET `id` = ?,`word` = ?,`replacement` = ?,`createdDate` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserDictionaryEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        if (entity.getReplacement() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReplacement());
        }
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteWordByText = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_dictionary_entries WHERE word = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllWords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_dictionary_entries";
        return _query;
      }
    };
  }

  @Override
  public Object insertWord(final UserDictionaryEntry word,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserDictionaryEntry.insertAndReturnId(word);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWord(final UserDictionaryEntry word,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserDictionaryEntry.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWord(final UserDictionaryEntry word,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserDictionaryEntry.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWordByText(final String word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWordByText.acquire();
        int _argIndex = 1;
        if (word == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, word);
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
          __preparedStmtOfDeleteWordByText.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllWords(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllWords.acquire();
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
          __preparedStmtOfDeleteAllWords.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getWordById(final long id,
      final Continuation<? super UserDictionaryEntry> $completion) {
    final String _sql = "SELECT * FROM user_dictionary_entries WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserDictionaryEntry>() {
      @Override
      @Nullable
      public UserDictionaryEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final UserDictionaryEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpReplacement;
            if (_cursor.isNull(_cursorIndexOfReplacement)) {
              _tmpReplacement = null;
            } else {
              _tmpReplacement = _cursor.getString(_cursorIndexOfReplacement);
            }
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _result = new UserDictionaryEntry(_tmpId,_tmpWord,_tmpReplacement,_tmpCreatedDate);
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
  public Object getWordByText(final String word,
      final Continuation<? super UserDictionaryEntry> $completion) {
    final String _sql = "SELECT * FROM user_dictionary_entries WHERE word = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (word == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, word);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserDictionaryEntry>() {
      @Override
      @Nullable
      public UserDictionaryEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final UserDictionaryEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpReplacement;
            if (_cursor.isNull(_cursorIndexOfReplacement)) {
              _tmpReplacement = null;
            } else {
              _tmpReplacement = _cursor.getString(_cursorIndexOfReplacement);
            }
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _result = new UserDictionaryEntry(_tmpId,_tmpWord,_tmpReplacement,_tmpCreatedDate);
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
  public Flow<List<UserDictionaryEntry>> getAllWords() {
    final String _sql = "SELECT * FROM user_dictionary_entries ORDER BY word";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_dictionary_entries"}, new Callable<List<UserDictionaryEntry>>() {
      @Override
      @NonNull
      public List<UserDictionaryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<UserDictionaryEntry> _result = new ArrayList<UserDictionaryEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionaryEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpReplacement;
            if (_cursor.isNull(_cursorIndexOfReplacement)) {
              _tmpReplacement = null;
            } else {
              _tmpReplacement = _cursor.getString(_cursorIndexOfReplacement);
            }
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new UserDictionaryEntry(_tmpId,_tmpWord,_tmpReplacement,_tmpCreatedDate);
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
  public Object searchWords(final String query,
      final Continuation<? super List<UserDictionaryEntry>> $completion) {
    final String _sql = "SELECT * FROM user_dictionary_entries WHERE word LIKE '%' || ? || '%' OR replacement LIKE '%' || ? || '%' ORDER BY word";
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserDictionaryEntry>>() {
      @Override
      @NonNull
      public List<UserDictionaryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<UserDictionaryEntry> _result = new ArrayList<UserDictionaryEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionaryEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpReplacement;
            if (_cursor.isNull(_cursorIndexOfReplacement)) {
              _tmpReplacement = null;
            } else {
              _tmpReplacement = _cursor.getString(_cursorIndexOfReplacement);
            }
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new UserDictionaryEntry(_tmpId,_tmpWord,_tmpReplacement,_tmpCreatedDate);
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
  public Object getRecentlyAddedWords(final int limit,
      final Continuation<? super List<UserDictionaryEntry>> $completion) {
    final String _sql = "SELECT * FROM user_dictionary_entries ORDER BY createdDate DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserDictionaryEntry>>() {
      @Override
      @NonNull
      public List<UserDictionaryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final List<UserDictionaryEntry> _result = new ArrayList<UserDictionaryEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionaryEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpReplacement;
            if (_cursor.isNull(_cursorIndexOfReplacement)) {
              _tmpReplacement = null;
            } else {
              _tmpReplacement = _cursor.getString(_cursorIndexOfReplacement);
            }
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            _item = new UserDictionaryEntry(_tmpId,_tmpWord,_tmpReplacement,_tmpCreatedDate);
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
  public Object getTotalWordCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM user_dictionary_entries";
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
