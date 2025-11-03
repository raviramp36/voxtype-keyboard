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
import com.voxtype.keyboard.database.entities.DictionaryWord;
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
public final class DictionaryWordDao_Impl implements DictionaryWordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DictionaryWord> __insertionAdapterOfDictionaryWord;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<DictionaryWord> __deletionAdapterOfDictionaryWord;

  private final EntityDeletionOrUpdateAdapter<DictionaryWord> __updateAdapterOfDictionaryWord;

  private final SharedSQLiteStatement __preparedStmtOfIncrementWordFrequency;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWordsByLanguage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllWords;

  public DictionaryWordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDictionaryWord = new EntityInsertionAdapter<DictionaryWord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `dictionary_words` (`id`,`word`,`language`,`frequency`,`createdDate`,`lastUsed`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DictionaryWord entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        if (entity.getLanguage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLanguage());
        }
        statement.bindLong(4, entity.getFrequency());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final Long _tmp_1 = __dateConverters.dateToTimestamp(entity.getLastUsed());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfDictionaryWord = new EntityDeletionOrUpdateAdapter<DictionaryWord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `dictionary_words` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DictionaryWord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfDictionaryWord = new EntityDeletionOrUpdateAdapter<DictionaryWord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `dictionary_words` SET `id` = ?,`word` = ?,`language` = ?,`frequency` = ?,`createdDate` = ?,`lastUsed` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DictionaryWord entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        if (entity.getLanguage() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLanguage());
        }
        statement.bindLong(4, entity.getFrequency());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getCreatedDate());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
        final Long _tmp_1 = __dateConverters.dateToTimestamp(entity.getLastUsed());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_1);
        }
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfIncrementWordFrequency = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE dictionary_words SET frequency = frequency + 1, lastUsed = ? WHERE word = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteWordsByLanguage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM dictionary_words WHERE language = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllWords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM dictionary_words";
        return _query;
      }
    };
  }

  @Override
  public Object insertWord(final DictionaryWord word,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDictionaryWord.insertAndReturnId(word);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWord(final DictionaryWord word,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDictionaryWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWord(final DictionaryWord word,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDictionaryWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementWordFrequency(final String word, final Date date,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementWordFrequency.acquire();
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
          __preparedStmtOfIncrementWordFrequency.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWordsByLanguage(final String language,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWordsByLanguage.acquire();
        int _argIndex = 1;
        if (language == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, language);
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
          __preparedStmtOfDeleteWordsByLanguage.release(_stmt);
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
  public Object getWordById(final long id, final Continuation<? super DictionaryWord> $completion) {
    final String _sql = "SELECT * FROM dictionary_words WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DictionaryWord>() {
      @Override
      @Nullable
      public DictionaryWord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final DictionaryWord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _result = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
      final Continuation<? super DictionaryWord> $completion) {
    final String _sql = "SELECT * FROM dictionary_words WHERE word = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (word == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, word);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DictionaryWord>() {
      @Override
      @Nullable
      public DictionaryWord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final DictionaryWord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _result = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Flow<List<DictionaryWord>> getWordsByLanguage(final String language) {
    final String _sql = "SELECT * FROM dictionary_words WHERE language = ? ORDER BY frequency DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (language == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, language);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"dictionary_words"}, new Callable<List<DictionaryWord>>() {
      @Override
      @NonNull
      public List<DictionaryWord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<DictionaryWord> _result = new ArrayList<DictionaryWord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DictionaryWord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _item = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Flow<List<DictionaryWord>> getAllWords() {
    final String _sql = "SELECT * FROM dictionary_words ORDER BY frequency DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"dictionary_words"}, new Callable<List<DictionaryWord>>() {
      @Override
      @NonNull
      public List<DictionaryWord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<DictionaryWord> _result = new ArrayList<DictionaryWord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DictionaryWord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _item = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Object searchWords(final String query, final int limit,
      final Continuation<? super List<DictionaryWord>> $completion) {
    final String _sql = "SELECT * FROM dictionary_words WHERE word LIKE '%' || ? || '%' ORDER BY frequency DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DictionaryWord>>() {
      @Override
      @NonNull
      public List<DictionaryWord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<DictionaryWord> _result = new ArrayList<DictionaryWord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DictionaryWord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _item = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Object getMostFrequentWords(final int limit,
      final Continuation<? super List<DictionaryWord>> $completion) {
    final String _sql = "SELECT * FROM dictionary_words ORDER BY frequency DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DictionaryWord>>() {
      @Override
      @NonNull
      public List<DictionaryWord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<DictionaryWord> _result = new ArrayList<DictionaryWord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DictionaryWord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _item = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Object getRecentlyUsedWords(final int limit,
      final Continuation<? super List<DictionaryWord>> $completion) {
    final String _sql = "SELECT * FROM dictionary_words ORDER BY lastUsed DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DictionaryWord>>() {
      @Override
      @NonNull
      public List<DictionaryWord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<DictionaryWord> _result = new ArrayList<DictionaryWord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DictionaryWord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            final Date _tmpCreatedDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreatedDate);
            }
            _tmpCreatedDate = __dateConverters.fromTimestamp(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = __dateConverters.fromTimestamp(_tmp_1);
            _item = new DictionaryWord(_tmpId,_tmpWord,_tmpLanguage,_tmpFrequency,_tmpCreatedDate,_tmpLastUsed);
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
  public Object getWordCountByLanguage(final String language,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM dictionary_words WHERE language = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (language == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, language);
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
  public Object getTotalWordCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM dictionary_words";
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
