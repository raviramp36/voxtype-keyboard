package com.voxtype.keyboard.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDictionaryDao_Impl implements UserDictionaryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserDictionary> __insertionAdapterOfUserDictionary;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<UserDictionary> __deletionAdapterOfUserDictionary;

  public UserDictionaryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserDictionary = new EntityInsertionAdapter<UserDictionary>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_dictionary` (`word`,`replacement`,`isName`,`isPhraseShortcut`,`language`,`addedDate`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserDictionary entity) {
        if (entity.getWord() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getWord());
        }
        if (entity.getReplacement() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getReplacement());
        }
        final int _tmp = entity.isName() ? 1 : 0;
        statement.bindLong(3, _tmp);
        final int _tmp_1 = entity.isPhraseShortcut() ? 1 : 0;
        statement.bindLong(4, _tmp_1);
        if (entity.getLanguage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getLanguage());
        }
        final Long _tmp_2 = __dateConverters.dateToTimestamp(entity.getAddedDate());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfUserDictionary = new EntityDeletionOrUpdateAdapter<UserDictionary>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `user_dictionary` WHERE `word` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserDictionary entity) {
        if (entity.getWord() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getWord());
        }
      }
    };
  }

  @Override
  public Object insert(final UserDictionary entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserDictionary.insert(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final UserDictionary entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserDictionary.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object find(final String word, final Continuation<? super UserDictionary> $completion) {
    final String _sql = "SELECT * FROM user_dictionary WHERE word = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (word == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, word);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserDictionary>() {
      @Override
      @Nullable
      public UserDictionary call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfIsName = CursorUtil.getColumnIndexOrThrow(_cursor, "isName");
          final int _cursorIndexOfIsPhraseShortcut = CursorUtil.getColumnIndexOrThrow(_cursor, "isPhraseShortcut");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final UserDictionary _result;
          if (_cursor.moveToFirst()) {
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
            final boolean _tmpIsName;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsName);
            _tmpIsName = _tmp != 0;
            final boolean _tmpIsPhraseShortcut;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPhraseShortcut);
            _tmpIsPhraseShortcut = _tmp_1 != 0;
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final Date _tmpAddedDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfAddedDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfAddedDate);
            }
            _tmpAddedDate = __dateConverters.fromTimestamp(_tmp_2);
            _result = new UserDictionary(_tmpWord,_tmpReplacement,_tmpIsName,_tmpIsPhraseShortcut,_tmpLanguage,_tmpAddedDate);
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
  public Object getAllShortcuts(final Continuation<? super List<UserDictionary>> $completion) {
    final String _sql = "SELECT * FROM user_dictionary WHERE isPhraseShortcut = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserDictionary>>() {
      @Override
      @NonNull
      public List<UserDictionary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfIsName = CursorUtil.getColumnIndexOrThrow(_cursor, "isName");
          final int _cursorIndexOfIsPhraseShortcut = CursorUtil.getColumnIndexOrThrow(_cursor, "isPhraseShortcut");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final List<UserDictionary> _result = new ArrayList<UserDictionary>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionary _item;
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
            final boolean _tmpIsName;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsName);
            _tmpIsName = _tmp != 0;
            final boolean _tmpIsPhraseShortcut;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPhraseShortcut);
            _tmpIsPhraseShortcut = _tmp_1 != 0;
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final Date _tmpAddedDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfAddedDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfAddedDate);
            }
            _tmpAddedDate = __dateConverters.fromTimestamp(_tmp_2);
            _item = new UserDictionary(_tmpWord,_tmpReplacement,_tmpIsName,_tmpIsPhraseShortcut,_tmpLanguage,_tmpAddedDate);
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
  public Object getAllNames(final Continuation<? super List<UserDictionary>> $completion) {
    final String _sql = "SELECT * FROM user_dictionary WHERE isName = 1 ORDER BY word";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserDictionary>>() {
      @Override
      @NonNull
      public List<UserDictionary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfIsName = CursorUtil.getColumnIndexOrThrow(_cursor, "isName");
          final int _cursorIndexOfIsPhraseShortcut = CursorUtil.getColumnIndexOrThrow(_cursor, "isPhraseShortcut");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final List<UserDictionary> _result = new ArrayList<UserDictionary>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionary _item;
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
            final boolean _tmpIsName;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsName);
            _tmpIsName = _tmp != 0;
            final boolean _tmpIsPhraseShortcut;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPhraseShortcut);
            _tmpIsPhraseShortcut = _tmp_1 != 0;
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final Date _tmpAddedDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfAddedDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfAddedDate);
            }
            _tmpAddedDate = __dateConverters.fromTimestamp(_tmp_2);
            _item = new UserDictionary(_tmpWord,_tmpReplacement,_tmpIsName,_tmpIsPhraseShortcut,_tmpLanguage,_tmpAddedDate);
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
  public Object getAll(final Continuation<? super List<UserDictionary>> $completion) {
    final String _sql = "SELECT * FROM user_dictionary ORDER BY word";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserDictionary>>() {
      @Override
      @NonNull
      public List<UserDictionary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfReplacement = CursorUtil.getColumnIndexOrThrow(_cursor, "replacement");
          final int _cursorIndexOfIsName = CursorUtil.getColumnIndexOrThrow(_cursor, "isName");
          final int _cursorIndexOfIsPhraseShortcut = CursorUtil.getColumnIndexOrThrow(_cursor, "isPhraseShortcut");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final List<UserDictionary> _result = new ArrayList<UserDictionary>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserDictionary _item;
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
            final boolean _tmpIsName;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsName);
            _tmpIsName = _tmp != 0;
            final boolean _tmpIsPhraseShortcut;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPhraseShortcut);
            _tmpIsPhraseShortcut = _tmp_1 != 0;
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final Date _tmpAddedDate;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfAddedDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfAddedDate);
            }
            _tmpAddedDate = __dateConverters.fromTimestamp(_tmp_2);
            _item = new UserDictionary(_tmpWord,_tmpReplacement,_tmpIsName,_tmpIsPhraseShortcut,_tmpLanguage,_tmpAddedDate);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
