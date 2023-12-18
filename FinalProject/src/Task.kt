class Task(val taskName: String, val duration: Int, val teamLeader: Int, val projectID: String) {
    val predecessors: MutableList<Task> = mutableListOf()

    fun addPredecessor(predecessor: Task) {
        predecessors.add(predecessor)
    }
}