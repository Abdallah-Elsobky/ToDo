import androidx.recyclerview.widget.DiffUtil
import com.example.todo.database.entities.Task

class TaskDiffCallback(
    private var oldList: List<Task>,
    private val newList: List<Task>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition] != newList[newItemPosition] -> false
            oldList[oldItemPosition].id != newList[newItemPosition].id ->false
            oldList[oldItemPosition].title != newList[newItemPosition].title ->false
            oldList[oldItemPosition].description != newList[newItemPosition].description ->false
            oldList[oldItemPosition].date != newList[newItemPosition].date ->false
            oldList[oldItemPosition].time != newList[newItemPosition].time ->false
            else -> true
        }
    }
}
