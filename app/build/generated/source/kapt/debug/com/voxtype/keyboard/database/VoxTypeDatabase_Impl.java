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
import com.voxtype.keyboard.database.dao.DictionaryWordDao;
import com.voxtype.keyboard.database.dao.DictionaryWordDao_Impl;
import com.voxtype.keyboard.database.dao.SettingsPreferenceDao;
import com.voxtype.keyboard.database.dao.SettingsPreferenceDao_Impl;
import com.voxtype.keyboard.database.dao.SnippetDao;
import com.voxtype.keyboard.database.dao.SnippetDao_Impl;
import com.voxtype.keyboard.database.dao.UserDictionaryEntryDao;
import com.voxtype.keyboard.database.dao.UserDictionaryEntryDao_Impl;
import com.voxtype.keyboard.database.dao.UserStatisticsDao;
import com.voxtype.keyboard.database.dao.UserStatisticsDao_Impl;
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

  private volatile DictionaryWordDao _dictionaryWordDao;

  private volatile SnippetDao _snippetDao;

  private volatile UserStatisticsDao _userStatisticsDao;

  private volatile SettingsPreferenceDao _settingsPreferenceDao;

  private volatile UserDictionaryEntryDao _userDictionaryEntryDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
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
        db.execSQL("CREATE TABLE IF NOT EXISTS `dictionary_words` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT NOT NULL, `language` TEXT NOT NULL, `frequency` INTEGER NOT NULL, `createdDate` INTEGER NOT NULL, `lastUsed` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_dictionary_words_word` ON `dictionary_words` (`word`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_dictionary_words_language` ON `dictionary_words` (`language`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_dictionary_words_frequency` ON `dictionary_words` (`frequency`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `snippets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trigger` TEXT NOT NULL, `content` TEXT NOT NULL, `category` TEXT NOT NULL, `usageCount` INTEGER NOT NULL, `createdDate` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_snippets_trigger` ON `snippets` (`trigger`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_snippets_category` ON `snippets` (`category`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_snippets_usageCount` ON `snippets` (`usageCount`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_statistics` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `wordsTyped` INTEGER NOT NULL, `wpm` REAL NOT NULL, `appsUsed` TEXT NOT NULL, `languagesUsed` TEXT NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_user_statistics_date` ON `user_statistics` (`date`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `settings_preferences` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`))");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_settings_preferences_key` ON `settings_preferences` (`key`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_dictionary_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT NOT NULL, `replacement` TEXT NOT NULL, `createdDate` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_user_dictionary_entries_word` ON `user_dictionary_entries` (`word`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd3fbfe2ac67e5086384377b934e75e68')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `transcriptions`");
        db.execSQL("DROP TABLE IF EXISTS `corrections`");
        db.execSQL("DROP TABLE IF EXISTS `daily_stats`");
        db.execSQL("DROP TABLE IF EXISTS `word_frequency`");
        db.execSQL("DROP TABLE IF EXISTS `user_dictionary`");
        db.execSQL("DROP TABLE IF EXISTS `dictionary_words`");
        db.execSQL("DROP TABLE IF EXISTS `snippets`");
        db.execSQL("DROP TABLE IF EXISTS `user_statistics`");
        db.execSQL("DROP TABLE IF EXISTS `settings_preferences`");
        db.execSQL("DROP TABLE IF EXISTS `user_dictionary_entries`");
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
        final HashMap<String, TableInfo.Column> _columnsDictionaryWords = new HashMap<String, TableInfo.Column>(6);
        _columnsDictionaryWords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDictionaryWords.put("word", new TableInfo.Column("word", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDictionaryWords.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDictionaryWords.put("frequency", new TableInfo.Column("frequency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDictionaryWords.put("createdDate", new TableInfo.Column("createdDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDictionaryWords.put("lastUsed", new TableInfo.Column("lastUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDictionaryWords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDictionaryWords = new HashSet<TableInfo.Index>(3);
        _indicesDictionaryWords.add(new TableInfo.Index("index_dictionary_words_word", true, Arrays.asList("word"), Arrays.asList("ASC")));
        _indicesDictionaryWords.add(new TableInfo.Index("index_dictionary_words_language", false, Arrays.asList("language"), Arrays.asList("ASC")));
        _indicesDictionaryWords.add(new TableInfo.Index("index_dictionary_words_frequency", false, Arrays.asList("frequency"), Arrays.asList("ASC")));
        final TableInfo _infoDictionaryWords = new TableInfo("dictionary_words", _columnsDictionaryWords, _foreignKeysDictionaryWords, _indicesDictionaryWords);
        final TableInfo _existingDictionaryWords = TableInfo.read(db, "dictionary_words");
        if (!_infoDictionaryWords.equals(_existingDictionaryWords)) {
          return new RoomOpenHelper.ValidationResult(false, "dictionary_words(com.voxtype.keyboard.database.entities.DictionaryWord).\n"
                  + " Expected:\n" + _infoDictionaryWords + "\n"
                  + " Found:\n" + _existingDictionaryWords);
        }
        final HashMap<String, TableInfo.Column> _columnsSnippets = new HashMap<String, TableInfo.Column>(6);
        _columnsSnippets.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippets.put("trigger", new TableInfo.Column("trigger", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippets.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippets.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippets.put("usageCount", new TableInfo.Column("usageCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippets.put("createdDate", new TableInfo.Column("createdDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSnippets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSnippets = new HashSet<TableInfo.Index>(3);
        _indicesSnippets.add(new TableInfo.Index("index_snippets_trigger", true, Arrays.asList("trigger"), Arrays.asList("ASC")));
        _indicesSnippets.add(new TableInfo.Index("index_snippets_category", false, Arrays.asList("category"), Arrays.asList("ASC")));
        _indicesSnippets.add(new TableInfo.Index("index_snippets_usageCount", false, Arrays.asList("usageCount"), Arrays.asList("ASC")));
        final TableInfo _infoSnippets = new TableInfo("snippets", _columnsSnippets, _foreignKeysSnippets, _indicesSnippets);
        final TableInfo _existingSnippets = TableInfo.read(db, "snippets");
        if (!_infoSnippets.equals(_existingSnippets)) {
          return new RoomOpenHelper.ValidationResult(false, "snippets(com.voxtype.keyboard.database.entities.Snippet).\n"
                  + " Expected:\n" + _infoSnippets + "\n"
                  + " Found:\n" + _existingSnippets);
        }
        final HashMap<String, TableInfo.Column> _columnsUserStatistics = new HashMap<String, TableInfo.Column>(6);
        _columnsUserStatistics.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStatistics.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStatistics.put("wordsTyped", new TableInfo.Column("wordsTyped", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStatistics.put("wpm", new TableInfo.Column("wpm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStatistics.put("appsUsed", new TableInfo.Column("appsUsed", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStatistics.put("languagesUsed", new TableInfo.Column("languagesUsed", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserStatistics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserStatistics = new HashSet<TableInfo.Index>(1);
        _indicesUserStatistics.add(new TableInfo.Index("index_user_statistics_date", true, Arrays.asList("date"), Arrays.asList("ASC")));
        final TableInfo _infoUserStatistics = new TableInfo("user_statistics", _columnsUserStatistics, _foreignKeysUserStatistics, _indicesUserStatistics);
        final TableInfo _existingUserStatistics = TableInfo.read(db, "user_statistics");
        if (!_infoUserStatistics.equals(_existingUserStatistics)) {
          return new RoomOpenHelper.ValidationResult(false, "user_statistics(com.voxtype.keyboard.database.entities.UserStatistics).\n"
                  + " Expected:\n" + _infoUserStatistics + "\n"
                  + " Found:\n" + _existingUserStatistics);
        }
        final HashMap<String, TableInfo.Column> _columnsSettingsPreferences = new HashMap<String, TableInfo.Column>(2);
        _columnsSettingsPreferences.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettingsPreferences.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSettingsPreferences = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSettingsPreferences = new HashSet<TableInfo.Index>(1);
        _indicesSettingsPreferences.add(new TableInfo.Index("index_settings_preferences_key", true, Arrays.asList("key"), Arrays.asList("ASC")));
        final TableInfo _infoSettingsPreferences = new TableInfo("settings_preferences", _columnsSettingsPreferences, _foreignKeysSettingsPreferences, _indicesSettingsPreferences);
        final TableInfo _existingSettingsPreferences = TableInfo.read(db, "settings_preferences");
        if (!_infoSettingsPreferences.equals(_existingSettingsPreferences)) {
          return new RoomOpenHelper.ValidationResult(false, "settings_preferences(com.voxtype.keyboard.database.entities.SettingsPreference).\n"
                  + " Expected:\n" + _infoSettingsPreferences + "\n"
                  + " Found:\n" + _existingSettingsPreferences);
        }
        final HashMap<String, TableInfo.Column> _columnsUserDictionaryEntries = new HashMap<String, TableInfo.Column>(4);
        _columnsUserDictionaryEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionaryEntries.put("word", new TableInfo.Column("word", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionaryEntries.put("replacement", new TableInfo.Column("replacement", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserDictionaryEntries.put("createdDate", new TableInfo.Column("createdDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserDictionaryEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserDictionaryEntries = new HashSet<TableInfo.Index>(1);
        _indicesUserDictionaryEntries.add(new TableInfo.Index("index_user_dictionary_entries_word", true, Arrays.asList("word"), Arrays.asList("ASC")));
        final TableInfo _infoUserDictionaryEntries = new TableInfo("user_dictionary_entries", _columnsUserDictionaryEntries, _foreignKeysUserDictionaryEntries, _indicesUserDictionaryEntries);
        final TableInfo _existingUserDictionaryEntries = TableInfo.read(db, "user_dictionary_entries");
        if (!_infoUserDictionaryEntries.equals(_existingUserDictionaryEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "user_dictionary_entries(com.voxtype.keyboard.database.entities.UserDictionaryEntry).\n"
                  + " Expected:\n" + _infoUserDictionaryEntries + "\n"
                  + " Found:\n" + _existingUserDictionaryEntries);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d3fbfe2ac67e5086384377b934e75e68", "dc90a42cdb83de1ec5a807035a9e6bf5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "transcriptions","corrections","daily_stats","word_frequency","user_dictionary","dictionary_words","snippets","user_statistics","settings_preferences","user_dictionary_entries");
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
      _db.execSQL("DELETE FROM `dictionary_words`");
      _db.execSQL("DELETE FROM `snippets`");
      _db.execSQL("DELETE FROM `user_statistics`");
      _db.execSQL("DELETE FROM `settings_preferences`");
      _db.execSQL("DELETE FROM `user_dictionary_entries`");
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
    _typeConvertersMap.put(DictionaryWordDao.class, DictionaryWordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SnippetDao.class, SnippetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserStatisticsDao.class, UserStatisticsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SettingsPreferenceDao.class, SettingsPreferenceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDictionaryEntryDao.class, UserDictionaryEntryDao_Impl.getRequiredConverters());
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

  @Override
  public DictionaryWordDao dictionaryWordDao() {
    if (_dictionaryWordDao != null) {
      return _dictionaryWordDao;
    } else {
      synchronized(this) {
        if(_dictionaryWordDao == null) {
          _dictionaryWordDao = new DictionaryWordDao_Impl(this);
        }
        return _dictionaryWordDao;
      }
    }
  }

  @Override
  public SnippetDao snippetDao() {
    if (_snippetDao != null) {
      return _snippetDao;
    } else {
      synchronized(this) {
        if(_snippetDao == null) {
          _snippetDao = new SnippetDao_Impl(this);
        }
        return _snippetDao;
      }
    }
  }

  @Override
  public UserStatisticsDao userStatisticsDao() {
    if (_userStatisticsDao != null) {
      return _userStatisticsDao;
    } else {
      synchronized(this) {
        if(_userStatisticsDao == null) {
          _userStatisticsDao = new UserStatisticsDao_Impl(this);
        }
        return _userStatisticsDao;
      }
    }
  }

  @Override
  public SettingsPreferenceDao settingsPreferenceDao() {
    if (_settingsPreferenceDao != null) {
      return _settingsPreferenceDao;
    } else {
      synchronized(this) {
        if(_settingsPreferenceDao == null) {
          _settingsPreferenceDao = new SettingsPreferenceDao_Impl(this);
        }
        return _settingsPreferenceDao;
      }
    }
  }

  @Override
  public UserDictionaryEntryDao userDictionaryEntryDao() {
    if (_userDictionaryEntryDao != null) {
      return _userDictionaryEntryDao;
    } else {
      synchronized(this) {
        if(_userDictionaryEntryDao == null) {
          _userDictionaryEntryDao = new UserDictionaryEntryDao_Impl(this);
        }
        return _userDictionaryEntryDao;
      }
    }
  }
}
