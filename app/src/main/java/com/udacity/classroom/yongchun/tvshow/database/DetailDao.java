package com.udacity.classroom.yongchun.tvshow.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.udacity.classroom.yongchun.tvshow.model.Detail;

import java.util.List;

@Dao
public interface DetailDao {

    @Query("SELECT * FROM detail_table ORDER BY last_air_date DESC")
    LiveData<List<Detail>> loadAllDetails();

    @Query("SELECT * FROM detail_table ORDER BY last_air_date DESC")
    List<Detail> nLoadAllDetails();

    @Query("SELECT * FROM detail_table WHERE id = :id")
    LiveData<Detail> loadDetailById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Detail detail);

    @Query("DELETE FROM detail_table WHERE id = :id")
    void delete(String id);
}
