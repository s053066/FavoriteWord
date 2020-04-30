package com.example.viewpager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * from word ORDER BY wordId ASC")
    LiveData<List<Word>> loadAllWords();

    @Insert
    void insertAll(Word... words);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM word")
    void deleteAll();

    //@Query("SELECT word.* FROM word JOIN")

    @Query("SELECT * FROM word WHERE wordId = :wordId")
    LiveData<Word> loadWord(int wordId);
}
