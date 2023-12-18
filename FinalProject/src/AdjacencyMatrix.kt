import javax.swing.table.DefaultTableModel


class AdjacencyMatrix(private val nodes: List<String>) {
    private val matrix: Array<IntArray> = Array(nodes.size) { IntArray(nodes.size) }

    fun addEdge(start: String, end: String) {
        val startIndex = nodes.indexOf(start)
        val endIndex = nodes.indexOf(end)

        if (startIndex != -1 && endIndex != -1) {
            matrix[startIndex][endIndex] = 1
        } else {
            println("One or both nodes not found.")
        }
    }

   /* fun displayMatrix() {
        for (i in nodes.indices) {
            for (j in nodes.indices) {
                print("${matrix[i][j]} ")
            }
            println()
        }
    }*/

    fun convertToToJTable(): DefaultTableModel {
        val model = DefaultTableModel()

        // Add columns to the model
        model.addColumn("")
        for (n in nodes) {
            model.addColumn(n)
        }
        // Add data to the model
        for (i in nodes.indices) {
            val rowData = mutableListOf<Any>()
            rowData.add(nodes[i]) // Add the row header

            for (j in nodes.indices) {
                rowData.add(matrix[i][j]) // Add matrix values to the row
            }

            model.addRow(rowData.toTypedArray())
        }

        return model
    }




}
