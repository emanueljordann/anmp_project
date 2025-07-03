import android.content.Context
import com.example.anmproject.model.UserDatabase

val DB_NAME = "newuserdb"


fun buildDb(context: Context): UserDatabase {
    val db = UserDatabase.buildDatabase(context)
    return db
}


