package com.voxtype.keyboard.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VoxTypeDatabase_Impl extends VoxTypeDatabase {
  private volatile TranscriptionDao _transcriptionDao;

  private volatile CorrectionDao _correctionDao;

  private volatile StatsDao _statsDao;

  private volatile WordFrequencyDao _wordFrequencyDao;

  private volatile UserDictionaryDao _userDictionaryDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `transcriptions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `originalAudio` TEXT, `rawTranscription` TEXT NOT NULL, `finalText` TEXT NOT NULL, `userEditedText` TEXT, `wordCount` INTEGER NOT NULL, `characterCount` INTEGER NOT NULL, `duration` REAL, `appPackage` TEXT, `textMode` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `corrections` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `originalText` TEXT NOT NULL, `correctedText` TEXT NOT NULL, `context` TEXT, `frequency` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_corrections_originalText` ON `corrections` (`originalText`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `daily_stats` (`date` TEXT NOT NULL, `totalWords` INTEGER NOT NULL, `totalCharacters` INTEGER NOT NULL, `totalTranscriptions` INTEGER NOT NULL, `totalDuration` REAL NOT NULL, `correctionsCount` INTEGER NOT NULL, `appsUsed` TEXT NOT NULL, `peakHour` INTEGER, `averageWordsPerTranscription` REAL NOT NULL, PRIMARY KEY(`date`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_daily_stats_date` ON `daily_stats` (`date`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `word_frequency` (`word` TEXT NOT NULL, `frequency` INTEGER NOT NULL, `firstUsed` INTEGER NOT NULL, `lastUsed` INTEGER NOT NULL, `contexts` TEXT NOT NULL, PRIMARY KEY(`word`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_word_frequency_word` ON `word_frequency` (`word`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_dictionary` (`word` TEXT NOT NULL, `replacement` TEXT, `isName` INTEGER NOT NULL, `isPhraseShortcut` INTEGER NOT NULL, `language` TEXT NOT NULL, `addedDate` INTEGER NOT NULL, PRIMARY KEY(`word`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_user_dictionary_word` ON `user_dictionary` (`word`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7245e5bb138a7113a8f738cd5e0c2227')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `transcriptions`");
        db.execSQL("DROP TABLE IF EXISTS `corrections`");
        db.execSQL("DROP TABLE IF EXISTS `daily_stats`");
        db.execSQL("DROP TABLE IF EXISTS `word_frequency`");
        db.execSQL("DROP TABLE IF EXISTS `user_dictionary`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTranscriptions = new HashMap<String, TableInfo.Column>(11);
        _columnsTranscriptions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("originalAudio", new TableInfo.Column("originalAudio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("rawTranscription", new TableInfo.Column("rawTranscription", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("finalText", new TableInfo.Column("finalText", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("userEditedText", new TableInfo.Column("userEditedText", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("wordCount", new TableInfo.Column("wordCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("characterCount", new TableInfo.Column("characterCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("duration", new TableInfo.Column("duration", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("appPackage", new TableInfo.Column("appPackage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranscriptions.put("textMode", new TableInfo.Column("textMode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTranscriptions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTranscriptions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTranscriptions = new TableInfo("transcriptions", _columnsTranscriptions, _foreignKeysTranscriptions, _indicesTranscriptions);
        final TableInfo _existingTranscriptions = TableInfo.read(db, "transcriptions");
        if (!_infoTranscriptions.equals(_existingTranscriptions)) {
          return new RoomOpenHelper.ValidationResult(false, "transcriptions(com.voxtype.keyboard.database.TranscriptionEntry).\n"
                  + " Expected:\n" + _infoTranscriptions + "\n"
                  + " Found:\n" + _existingTranscriptions);
        }
        final HashMap<String, TableInfo.Column> _columnsCorrections = new HashMap<String, TableInfo.Column>(6);
        _columnsCorrections.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCorrections.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCorrections.put("originalText", new TableInfo.Column("originalText", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCorrections.put("correctedText", new TableInfo.Column("correctedText", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCorrections.put("context", new TableInfo.Column("context", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCorrections.put("frequency", new TableInfo.Column("frequency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCorrections = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCorrections = new HashSet<TableInfo.Index>(1);
        _indicesCorrections.add(new TableInfo.Index("index_corrections_originalText", false, Arrays.asList("originalText"), Arrays.asList("ASC")));
        final TableInfo _infoCorrections = new TableInfo("corrections", _columnsCorrections, _foreignKeysCorrections, _indicesCorrections);
        final TableInfo _existingCorrections = TableInfo.read(db, "corrections");
        if (!_infoCorrections.equals(_existingCorrections)) {
          return new RoomOpenHelper.ValidationResult(false, "corrections(com.voxtype.keyboard.database.CorrectionEntry).\n"
                  + " Expected:\n" + _infoCorrections + "\n"
                  + " Found:\n" + _existingCorrections);
        }
        final HashMap<String, TableInfo.Column> _columnsDailyStats = new HashMap<String, TableInfo.Column>(9);
        _columnsDailyStats.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("totalWords", new TableInfo.Column("totalWords", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("totalCharacters", new TableInfo.Column("totalCharacters", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("totalTranscriptions", new TableInfo.Column("totalTranscriptions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("totalDuration", new TableInfo.Column("totalDuration", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("correctionsCount", new TableInfo.Column("correctionsCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("appsUsed", new TableInfo.Column("appsUsed", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("peakHour", new TableInfo.Column("peakHour", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyStats.put("averageWordsPerTranscription", new TableInfo.Column("averageWordsPerTranscription", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailyStats = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailyStats = new HashSet<TableInfo.Index>(1);
        _indicesDailyStats.add(new TableInfo.Index("index_daily_stats_date", true, Arrays.asList("date"), Arrays.asList("ASC")));
        final TableInfo _infoDailyStats = new TableInfo("daily_stats", _columnsDailyStats, _foreignKeysDailyStats, _indicesDailyStats);
        final TableInfo _existingDailyStats = TableInfo.read(db, "daily_stats");
        if (!_infoDailyStats.equals(_existingDailyStats)) {
          return new RoomOpenHelper.ValidationResult(false, "daily_stats(com.voxtype.keyboard.database.DailyStats).\n"
                  + " Expected:\n" + _infoDailyStats + "\n"
                  + " Found:\n" + _existingDailyStats);
        }
        final HashMap<String, TableInfo.Column> _columnsWordFrequency = new HashMap<String, TableInfo.Column>(5);
        _columnsWordFrequency.put("word", new TableInfo.Column("word", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWordFrequency.put("frequency", new TableInfo.Column("frequency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWordFrequency.put("firstUsed", new TableInfo.Column("firstUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWordFrequency.put("lastUsed", new TableInfo.Column("lastUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWordFrequency.put("contexts", new TableInfo.Column("contexts", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWordFrequency = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWordFrequency = new HashSet<TableInfo.Index>(1);
        _indicesWordFrequency.add(new TableInfo.Index("index_word_frequency_word", true, Arrays.asList("word"), Arrays.asList("ASC")));
        final TableInfo _infoWordFrequency = new TableInfo("word_frequency", _columnsWordFrequency, _foreignKeysWordFrequency, _indicesWordFrequency);
        final TableInfo _existingWordFrequency = TableInfo.read(db, "word_frequency");
        if (!_infoWordFrequency.equals(_existingWordFrequency)) {
          return new RoomOpenHelper.ValidationResult(false, "word_frequency(com.voxtype.keyboard.database.WordFrequency).\n"
                  + " Expected:\n" + _infoWordFrequency + "\n"
                  + " Found:\n" + _existingWordFrequency);
        }
        final HashMap<String, TableInfo.Column> _columnsUserDictionary = new HashMap<String, TableInfo.Column>(6);
        _columnsUserDictionary.put("word", new TableInfo.Column("word", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionary.put("replacement", new TableInfo.Column("replacement", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionary.put("isName", new TableInfo.Column("isName", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionary.put("isPhraseShortcut", new TableInfo.Column("isPhraseShortcut", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionary.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionary.put("addedDate", new TableInfo.Column("addedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserDictionary = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserDictionary = new HashSet<TableInfo.Index>(1);
        _indicesUserDictionary.add(new TableInfo.Index("index_user_dictionary_word", true, Arrays.asList("word"), Arrays.asList("ASC")));
        final TableInfo _infoUserDictionary = new TableInfo("user_dictionary", _columnsUserDictionary, _foreignKeysUserDictionary, _indicesUserDictionary);
        final TableInfo _existingUserDictionary = TableInfo.read(db, "user_dictionary");
        if (!_infoUserDictionary.equals(_existingUserDictionary)) {
          return new RoomOpenHelper.ValidationResult(false, "user_dictionary(com.voxtype.keyboard.database.UserDictionary).\n"
                  + " Expected:\n" + _infoUserDictionary + "\n"
                  + " Found:\n" + _existingUserDictionary);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7245e5bb138a7113a8f738cd5e0c2227", "186cd7e04b99953fb844a5f46b3ee4eb");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "transcriptions","corrections","daily_stats","word_frequency","user_dictionary");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `transcriptions`");
      _db.execSQL("DELETE FROM `corrections`");
      _db.execSQL("DELETE FROM `daily_stats`");
      _db.execSQL("DELETE FROM `word_frequency`");
      _db.execSQL("DELETE FROM `user_dictionary`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TranscriptionDao.class, TranscriptionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CorrectionDao.class, CorrectionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StatsDao.class, StatsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WordFrequencyDao.class, WordFrequencyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDictionaryDao.class, UserDictionaryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TranscriptionDao transcriptionDao() {
    if (_transcriptionDao != null) {
      return _transcriptionDao;
    } else {
      synchronized(this) {
        if(_transcriptionDao == null) {
          _transcriptionDao = new TranscriptionDao_Impl(this);
        }
        return _transcriptionDao;
      }
    }
  }

  @Override
  public CorrectionDao correctionDao() {
    if (_correctionDao != null) {
      return _correctionDao;
    } else {
      synchronized(this) {
        if(_correctionDao == null) {
          _correctionDao = new CorrectionDao_Impl(this);
        }
        return _correctionDao;
      }
    }
  }

  @Override
  public StatsDao statsDao() {
    if (_statsDao != null) {
      return _statsDao;
    } else {
      synchronized(this) {
        if(_statsDao == null) {
          _statsDao = new StatsDao_Impl(this);
        }
        return _statsDao;
      }
    }
  }

  @Override
  public WordFrequencyDao wordFrequencyDao() {
    if (_wordFrequencyDao != null) {
      return _wordFrequencyDao;
    } else {
      synchronized(this) {
        if(_wordFrequencyDao == null) {
          _wordFrequencyDao = new WordFrequencyDao_Impl(this);
        }
        return _wordFrequencyDao;
      }
    }
  }

  @Override
  public UserDictionaryDao userDictionaryDao() {
    if (_userDictionaryDao != null) {
      return _userDictionaryDao;
    } else {
      synchronized(this) {
        if(_userDictionaryDao == null) {
          _userDictionaryDao = new UserDictionaryDao_Impl(this);
        }
        return _userDictionaryDao;
      }
    }
  }
}
