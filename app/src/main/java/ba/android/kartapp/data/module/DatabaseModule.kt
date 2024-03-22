package ba.android.kartapp.data.module

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ba.android.kartapp.data.model.PostDao
import ba.android.kartapp.data.model.PostEntity
import ba.android.kartapp.data.model.ProductDao
import ba.android.kartapp.data.model.ProductEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2) // Add the migration here
            .build()
    }

    @Provides
    fun providePostDao(database: AppDatabase): PostDao {
        return database.postDao()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
}

@Database(entities = [ProductEntity::class, PostEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun productDao(): ProductDao

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Enable legacy alter table mode to ensure compatibility
        db.execSQL("PRAGMA legacy_alter_table=ON;")

        // Check if the 'products' table exists before attempting to rename it
        if (db.query("SELECT name FROM sqlite_master WHERE type='table' AND name='products'").use { it.count > 0 }) {
            // Rename the existing table to a temporary table
            db.execSQL("ALTER TABLE products RENAME TO products_old")

            // Create a new table with the correct schema
            db.execSQL("""
                CREATE TABLE products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    image TEXT NOT NULL,
                    price REAL NOT NULL
                )
            """.trimIndent())

            // Copy the data from the old table to the new table
            db.execSQL("""
                INSERT INTO products (id, name, description, image, price)
                SELECT id, name, description, image, price FROM products_old
            """.trimIndent())

            // Remove the old table
            db.execSQL("DROP TABLE products_old")
        } else {
            // If the 'products' table does not exist, create it directly
            db.execSQL("""
                CREATE TABLE products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    image TEXT NOT NULL,
                    price REAL NOT NULL
                )
            """.trimIndent())
        }
    }
}





