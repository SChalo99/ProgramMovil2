package pe.edu.ulima.configs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.ulima.daos.PokemonDao
import pe.edu.ulima.models.beans.PokemonLocal

@Database(
    entities = [PokemonLocal::class,],
    version = 1
)

abstract class LocalDB: RoomDatabase(){
    //daos TODO
    abstract fun pokemonDao(): PokemonDao

    companion object{
        private  var INSTANCE: LocalDB ? = null

        fun getDatabase(context: Context) : LocalDB{
            if(INSTANCE == null){
                synchronized(LocalDB::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDB::class.java,
                        "local_db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}