class Project(var id:String, var projectName: String,  var customer: String) {
    val tasks: MutableList<Task> = mutableListOf()

    fun addTask(task: Task) {
        tasks.add(task)
    }


}