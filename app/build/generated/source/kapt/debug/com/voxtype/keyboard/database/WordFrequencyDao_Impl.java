package com.voxtype.keyboard.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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

@SuppressWarnings({"unchecked", "deprecation"})
public final class WordFrequencyDao_Impl implements WordFrequencyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WordFrequency> __insertionAdapterOfWordFrequency;

  private final DateConverters __dateConverters = new DateConverters();

  private final SharedSQLiteStatement __preparedStmtOfIncrementFrequency;

  public WordFrequencyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWordFrequency = new EntityInsertionAdapter<WordFrequency>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `word_frequency` (`word`,`frequency`,`firstUsed`,`lastUsed`,`contexts`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WordFrequency entity) {
        if (entity.getWord() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getWord());
        }
        statement.bindLong(2, entity.getFrequency());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getFirstUsed());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        final Long _tmp_1 = __dateConverters.dateToTimestamp(entity.getLastUsed());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp_1);
        }
        if (entity.getContexts() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContexts());
        }
      }
    };
    this.__preparedStmtOfIncrementFrequency = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE word_frequency SET frequency = frequency + 1, lastUsed = ? WHERE word = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final WordFrequency word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWordFrequency.insert(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementFrequency(final String word, final Date date,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementFrequency.acquire();
        int _argIndex = 1;
        final Long _tmp = __dateConverters.dateToTimestamp(date);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 2;
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
          __preparedStmtOfIncrementFrequency.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getMostUsedWords(final int limit,
      final Continuation<? super List<WordFrequency>> $completion) {
    final String _sql = "SELECT * FROM word_frequency ORDER BY frequency DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WordFrequency>>() {
      @Override
      @NonNull
      public List<WordFrequency> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfFirstUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "firstUsed");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final int _cursorIndexOfContexts = CursorUtil.getColumnIndexOrThrow(_cursor, "contexts");
          final List<WordFrequency> _result = new ArrayList<WordFrequency>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WordFrequency _item;
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpFirstUsed;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfFirstUsed)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfFirstUsed);
            }
            _tmpFirstUsed = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            final String _tmpContexts;
            if (_cursor.isNull(_cursorIndexOfContexts)) {
              _tmpContexts = null;
            } else {
              _tmpContexts = _cursor.getString(_cursorIndexOfContexts);
            }
            _item = new WordFrequency(_tmpWord,_tmpFrequency,_tmpFirstUsed,_tmpLastUsed,_tmpContexts);
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
  public Object getWordSuggestions(final String prefix, final int limit,
      final Continuation<? super List<WordFrequency>> $completion) {
    final String _sql = "SELECT * FROM word_frequency WHERE word LIKE ? || '%' ORDER BY frequency DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (prefix == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, prefix);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WordFrequency>>() {
      @Override
      @NonNull
      public List<WordFrequency> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfFirstUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "firstUsed");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final int _cursorIndexOfContexts = CursorUtil.getColumnIndexOrThrow(_cursor, "contexts");
          final List<WordFrequency> _result = new ArrayList<WordFrequency>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WordFrequency _item;
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpFirstUsed;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfFirstUsed)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfFirstUsed);
            }
            _tmpFirstUsed = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            final String _tmpContexts;
            if (_cursor.isNull(_cursorIndexOfContexts)) {
              _tmpContexts = null;
            } else {
              _tmpContexts = _cursor.getString(_cursorIndexOfContexts);
            }
            _item = new WordFrequency(_tmpWord,_tmpFrequency,_tmpFirstUsed,_tmpLastUsed,_tmpContexts);
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
  public Object getVocabularySize(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT word) FROM word_frequency";
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
