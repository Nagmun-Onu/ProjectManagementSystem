import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*
import javax.swing.DefaultComboBoxModel
import javax.swing.DefaultListModel
import javax.swing.table.DefaultTableModel

class ProjectHandler {
    private val teamLeadersList = mutableListOf<TeamLeader>()
    val projectsList = mutableListOf<Project>()

    fun projectListForList1(): DefaultListModel<String> {
        val listModel = DefaultListModel<String>()
        for (project in projectsList) {
            listModel.addElement("${project.id}, ${project.projectName}, ${project.customer}")
        }
        return listModel
    }


    fun taskListForList2(pIndex: Int): DefaultListModel<String> {
        val listModel = DefaultListModel<String>()
        for (t in projectsList[pIndex].tasks) {
            listModel.addElement(t.taskName)
        }
        return listModel
    }

    fun teamLeaderListForCB(): DefaultComboBoxModel<String> {
        val teamLeaderModel = DefaultComboBoxModel<String>()
        for (teamLeader in teamLeadersList) {
            teamLeaderModel.addElement("${teamLeader.name} - ${teamLeader.location}")
        }
        return teamLeaderModel
    }


    // option can be "new" or "task name"
    // if option is new, the dependency list display all available tasks
    // otherwise it display the tasks that are not equal to parameter task name
    // because when editing a task, a task cannot depend on itself.
    fun taskListForDL(index: Int, option:String): DefaultListModel<String> {
        val tasksListModel = DefaultListModel<String>()
        tasksListModel.addElement("none")
        if (option=="New"){
        for (t in projectsList[index].tasks) {
            tasksListModel.addElement(t.taskName)
        }
        }
        else
        {
            for (t in projectsList[index].tasks) {
                if (t.taskName!=option)
                    tasksListModel.addElement(t.taskName)
            }
        }
        return tasksListModel
    }

    // loading projects information from files
    fun loadTeamLeaders() {
        try {
            val myObj = File("teamLeaders.txt")
            val myReader = Scanner(myObj)
            while (myReader.hasNextLine()) {
                val data = myReader.nextLine().split(";")
                //println(data)
                val teamLeader = TeamLeader(data[0], data[1], data[2])
                teamLeadersList.add(teamLeader)

            }
            myReader.close()
        } catch (e: FileNotFoundException) {
            println("An error occurred.")
            e.printStackTrace()
        }

    }

    fun loadProjects() {

        try {
            val myObj = File("projects1.txt")
            val myReader = Scanner(myObj)
            while (myReader.hasNextLine()) {
                val data = myReader.nextLine().split(";")
                val project = Project(data[0], data[1], data[2])
                val taskList: MutableList<Task> = loadTasks(project.id)
                for (task in taskList) {
                    project.addTask(task)
                }
                //println(taskList.get(0).taskName)
                projectsList.add(project)

            }
            myReader.close()
        } catch (e: FileNotFoundException) {
            println("An error occurred.")
            e.printStackTrace()
        }


    }

    private fun loadTasks(id: String): MutableList<Task> {
        val tList = mutableListOf<Task>()

        //println(id)
        try {
            val myObj = File("tasks1.txt")
            val myReader = Scanner(myObj)
            while (myReader.hasNextLine()) {
                val data = myReader.nextLine().split(";")
                //println(data[4])

                if (data[3] == id) {
                    //println("okay")
                    val task = Task(data[0], data[1].toInt(), data[2].toInt(), id)
                    val pre = data[4].replace(Regex("[\\[\\]]"), "")
                    val preList = pre.split(",")
                    for (p in preList) {
                        if (p != "none") {
                            val tempTask = p.split("-")
                            task.addPredecessor(Task(tempTask[0], tempTask[1].toInt(), data[2].toInt(), id))

                        }
                    }
                    tList.add(task)
                }
            }

            myReader.close()
        } catch (e: FileNotFoundException) {
            println("An error occurred.")
            e.printStackTrace()
        }


        return tList


    }

    fun saveNewTask(
        i: Int,
        name: String,
        duration: Int,
        teamLeaderIndex: Int,
        dependencyList: List<String>
    ): Boolean {
        for (t in projectsList[i].tasks) {
            if (t.taskName == name)
                return false
        }
        val newTask = Task(name, duration, teamLeaderIndex, projectsList[i].id)
        for (taskName in dependencyList) {
            if (taskName != "none" || taskName.isNotEmpty()) {
                for (task in projectsList[i].tasks) {
                    if (task.taskName == taskName)
                        newTask.addPredecessor(task)


                }
            }
        }
        projectsList[i].addTask(newTask)
        save()
        return true
    }

    fun saveNewProject(id: String, name: String, cName: String): Boolean {
        for (p in projectsList) {
            if (p.id == id)
                return false
        }
        val newProject = Project(id, name, cName)
        projectsList.add(newProject)
        save()
        return true
    }

    fun editProject(index: Int, name: String, cName: String): Boolean {
        projectsList[index].projectName = name
        projectsList[index].customer = cName
        save()
        return true

    }

    fun deleteProject(index: Int) {
        projectsList[index].tasks.clear()
        projectsList.removeAt(index)
        save()
    }

    fun editTask(
        pIndex: Int,
        tIndex: Int,
        name: String,
        duration: Int,
        teamLeaderIndex: Int,
        dependencyList: List<String>
    ): Boolean {

        val newTask = Task(name, duration, teamLeaderIndex, projectsList[pIndex].id)
        for (taskName in dependencyList) {
            if (taskName != "none") {
                for (task in projectsList[pIndex].tasks) {
                    if (task.taskName == taskName)
                        newTask.addPredecessor(task)


                }
            }
        }
        projectsList[pIndex].tasks[tIndex] = newTask
        save()


        return true
    }

    fun deleteTask(pIndex: Int, tIndex: Int): String {
        //check if any task depends on it
        val taskName = projectsList[pIndex].tasks[tIndex].taskName
        for (task in projectsList[pIndex].tasks) {
            if (task.taskName != taskName) {
                for (task1 in task.predecessors) {
                    if (task1.taskName == taskName)
                        return "DEPENDENT"
                }
            }

        }
        if (pIndex in projectsList.indices && tIndex in projectsList[pIndex].tasks.indices) {
            projectsList[pIndex].tasks.removeAt(tIndex)
        } else {
            // Handle invalid indices
            return ("Invalid indices provided.")
        }
        save()
        return "Deleted!"
    }

    fun createADJMatrix(index: Int): DefaultTableModel {
        val nodeLabels: MutableList<String> = mutableListOf()
        for (t1 in projectsList[index].tasks) {
            nodeLabels.add(t1.taskName)
        }

        val graph = AdjacencyMatrix(nodeLabels)
        for (t1 in projectsList[index].tasks) {
            if (t1.predecessors.isNotEmpty()) {
                for (t2 in t1.predecessors)
                    graph.addEdge(t1.taskName, t2.taskName)
            }
        }


        return graph.convertToToJTable()
    }


    private fun save() {
        var path: Path = Paths.get("projects1.txt")
        val projects = mutableListOf<String>()
        val tasks = mutableListOf<String>()
        var temp: String

        for (p in projectsList) {
            projects.add("${p.id};${p.projectName};${p.customer}")
            for (t in p.tasks) {
                temp = "${t.taskName};${t.duration};${t.teamLeader};${t.projectID};["
                if (t.predecessors.isEmpty())
                    temp += "none]"
                else {
                    for (pre in t.predecessors) {
                        temp += "${pre.taskName}-${pre.duration}-${pre.teamLeader},"
                    }
                }
                val stringBuilder = StringBuilder(temp)
                stringBuilder.setCharAt(temp.length - 1, ']')
                tasks.add(stringBuilder.toString())
            }
        }

        // Write each line from the list to the file
        Files.write(path, projects, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        path = Paths.get("tasks1.txt")
        Files.write(path, tasks, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }

}