package io.pagarusha.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import io.pagarusha.bucketlist.dao.ItemDao;
import io.pagarusha.bucketlist.model.BucketListItem;

@Database(entities = {BucketListItem.class}, version = 1, exportSchema = false)
public abstract class ListItemRoomDatabase extends RoomDatabase {

    private final static String NAME_DATABASE = "bucket_list_item_database";

    public abstract ItemDao itemDao();

    private static volatile ListItemRoomDatabase INSTANCE;

    public static ListItemRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ListItemRoomDatabase.class) {
                if (INSTANCE == null) {

                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ListItemRoomDatabase.class, NAME_DATABASE)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
