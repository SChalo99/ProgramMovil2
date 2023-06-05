package pe.edu.ulima.models.beans

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "pokemons"
)
data class PokemonLocal(
    @PrimaryKey(autoGenerate = false)
    var id:Int = 0,
    var number: Int = 0,
    var name: String = "",
    var weight: Float = 0f,
    var height: Float = 0f,
    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "",
)
