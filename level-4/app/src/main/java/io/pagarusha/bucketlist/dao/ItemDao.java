package io.pagarusha.bucketlist.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.pagarusha.bucketlist.model.BucketListItem;

@Dao
public interface ItemDao {

    @Insert
    void insert(BucketListItem item);

    @Update
    void update(BucketListItem item);

    @Delete
    void delete(BucketListItem item);

    @Delete
    void delete(List<BucketListItem> items);

    @Query("SELECT * from bucket_list_item_database")
    List<BucketListItem> getAllListItems();
}
