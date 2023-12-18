import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


public class MainGUI {
    private JPanel mainPanel;
    private JPanel projectPanel;
    private JPanel taskPanel;
    private JList<String> projectList;
    private JList<String> taskList;
    private JButton newTask;
    private JButton editTask1;
    private JButton deleteTask;
    private JTextField taskName;
    private JTextField taskDuration;
    private JComboBox<String> teamLeaderComboBox;
    private JButton saveTask;
    private JPanel taskPanel2;
    private JList<String> dependencyList;
    private JButton editTask2;
    private JPanel projectPanel2;
    private JButton newProject;
    private JButton editProject;
    private JButton deleteProject;
    private JTextField projectID;
    private JTextField projectName;
    private JTextField customerName;
    private JButton saveProject;
    private JButton editProject1;
    private JButton adjView;
    private JTable table1;
    private JPanel tablePanel;


    private final ProjectHandler projectHandler = new ProjectHandler();

    public MainGUI() {
        // Home page starts only with list of projects
        taskPanel2.setVisible(false);
        taskPanel.setVisible(false);
        projectPanel2.setVisible(false);
        tablePanel.setVisible(false);
        taskList.setVisible(false);

        // Load projects from project1.txt file
        projectHandler.loadTeamLeaders();
        projectHandler.loadProjects();
        projectList.setModel(projectHandler.projectListForList1());


        // whenever a project is selected from projectList
        // The system display related tasks
        projectList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                projectPanel2.setVisible(false);
                taskPanel2.setVisible(false);
                taskPanel.setVisible(true);
                taskList.setVisible(true);
                tablePanel.setVisible(false);


                // Check if a project is selected
                if (!projectList.isSelectionEmpty()) {
                    int pIndex = projectList.getSelectedIndex();
                    taskList.setModel(projectHandler.taskListForList2(pIndex));
                }
            }
        });

        // Whenever a task is selected from task list
        // The system display task details
        taskList.addListSelectionListener(e -> {
            taskPanel2.setVisible(true);
            saveTask.setVisible(false);
            editTask2.setVisible(false);
            tablePanel.setVisible(false);


            int pIndex = projectList.getSelectedIndex();
            int tIndex = taskList.getSelectedIndex();
            if (pIndex >= 0 && tIndex >= 0) {
                taskName.setText(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getTaskName());
                taskName.setEditable(false);
                taskDuration.setText(String.valueOf(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getDuration()));
                taskDuration.setEditable(false);
                teamLeaderComboBox.setModel(projectHandler.teamLeaderListForCB());
                teamLeaderComboBox.setSelectedIndex(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getTeamLeader());

                DefaultListModel<String> tasksListModel1 = new DefaultListModel<>();
                for (Task t : projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getPredecessors()) {
                    tasksListModel1.addElement(t.getTaskName());
                }

                dependencyList.setModel(tasksListModel1);
            } else {
                taskPanel2.setVisible(false);
            }

        });


        // add anew task
        newTask.addActionListener(e -> {
            // The system utilizes a common panel and components for displaying, creating, and editing tasks.
// When displaying task details, all text fields are set to read-only mode.
// If the edit option is chosen, the details of the selected task are populated in the text fields.
// Therefore, when the option for creating a new task is selected, the system refreshes the text fields
// and sets them to editable mode.
            taskPanel2.setVisible(false);
            taskPanel2.setVisible(true);
            editTask2.setVisible(false);
            saveTask.setVisible(true);
            taskName.setText("");
            taskName.setEditable(true);
            taskDuration.setText("");
            taskDuration.setEditable(true);
            tablePanel.setVisible(false);

            teamLeaderComboBox.setModel(projectHandler.teamLeaderListForCB());
            int index = projectList.getSelectedIndex();
            dependencyList.setModel(projectHandler.taskListForDL(index, "New"));
            dependencyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        });
        saveTask.addActionListener(e -> {
            var name = taskName.getText();

            // Check if any fields are empty before creating a new task
            if (!name.isEmpty() && !taskDuration.getText().isEmpty() && taskDuration.getText().matches("\\d+")) {
                // We assume that a task duration must be at least 1 day and cannot be more than 365 days
                int duration = Integer.parseInt(taskDuration.getText());
                if (0 < duration && duration < 366) {
                    // Creating new Task
                    var result = projectHandler.saveNewTask(projectList.getSelectedIndex(), name, duration, teamLeaderComboBox.getSelectedIndex(), dependencyList.getSelectedValuesList());
                    if (result) {
                        JOptionPane.showMessageDialog(null, "New task added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "New task not created! Task name already exist!");
                    }
                    // task list updated and hide the panel that create new task
                    taskList.setModel(projectHandler.taskListForList2(projectList.getSelectedIndex()));
                    taskPanel2.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid duration");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid task name and/or duration!");

            }


        });

        // The system utilizes a common panel and components for displaying, creating, and editing tasks.
// When displaying task details, all text fields are set to read-only mode.
// If the edit option is chosen, the details of the selected task are populated in the text fields
// and sets them to editable mode.
        editTask1.addActionListener(e -> {
            taskPanel2.setVisible(false);
            if (!taskList.isSelectionEmpty()) {
                taskPanel2.setVisible(true);
                saveTask.setVisible(false);
                editTask2.setVisible(true);
                tablePanel.setVisible(false);


                int pIndex = projectList.getSelectedIndex();
                int tIndex = taskList.getSelectedIndex();
                taskName.setText(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getTaskName());
                taskName.setEditable(true);

                taskDuration.setText(String.valueOf(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getDuration()));
                taskDuration.setEditable(true);

                teamLeaderComboBox.setModel(projectHandler.teamLeaderListForCB());
                teamLeaderComboBox.setSelectedIndex(projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getTeamLeader());

                dependencyList.setModel(projectHandler.taskListForDL(pIndex, projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getTaskName()));
                dependencyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                // The dependency task list should be selected with already dependent tasks
                // User can edit dependency

                int i = 0;
                List<Integer> indices = new ArrayList<>();
                for (Task t : projectHandler.getProjectsList().get(pIndex).getTasks()) {
                    i = i + 1;
                    for (Task t1 : projectHandler.getProjectsList().get(pIndex).getTasks().get(tIndex).getPredecessors()) {
                        if (t.getTaskName().equals(t1.getTaskName())) {
                            indices.add(i);
                        }
                    }
                }

                int[] selectedIndices = indices.stream().mapToInt(Integer::intValue).toArray();
                dependencyList.setSelectedIndices(selectedIndices);

            } else {
                JOptionPane.showMessageDialog(null, "Please select a task to edit!");
            }
        });
        editTask2.addActionListener(e -> {
            var name = taskName.getText();
            // check if any fields are empty
            if (!name.isEmpty() && !taskDuration.getText().isEmpty() && taskDuration.getText().matches("\\d+")) {
                int duration = Integer.parseInt(taskDuration.getText());
                // the task duration must be minimum 1 day and cannot be more and 1 year
                if (0 < duration && duration < 366) {
                    // task edited and task list updated
                    var result = projectHandler.editTask(projectList.getSelectedIndex(), taskList.getSelectedIndex(), name, duration, teamLeaderComboBox.getSelectedIndex(), dependencyList.getSelectedValuesList());
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Task Edited!");
                    }
                    taskList.setModel(projectHandler.taskListForList2(projectList.getSelectedIndex()));
                    // hide the panel for edit task
                    taskPanel2.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid duration");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid task name and/or duration!");
            }


        });
// when deleting a task:
        // it should reconfirm from user if the option was chosen intentionally
        // check if the requested task to delete has any child task
        // if yes -> ask user to delete first child task
        // if no -> delete the requested task
        deleteTask.addActionListener(e -> {
            taskPanel2.setVisible(false);
            tablePanel.setVisible(false);

            if (!taskList.isSelectionEmpty()) {
                int userChoice = JOptionPane.showConfirmDialog(null, "Are you sure to delete it?");
                if (userChoice == JOptionPane.YES_OPTION) {
                    String msg = projectHandler.deleteTask(projectList.getSelectedIndex(), taskList.getSelectedIndex());
                    if (msg.equals("DEPENDENT")) {
                        JOptionPane.showMessageDialog(null, "Cannot delete it! first delete the dependent tasks!");
                    } else {
                        System.out.println(msg);
                        taskList.setModel(projectHandler.taskListForList2(projectList.getSelectedIndex()));
                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, "Please Select a task to delete");
            }

        });

        // Create a new project
        newProject.addActionListener(e -> {
            projectPanel2.setVisible(true);
            taskPanel.setVisible(false);
            taskPanel2.setVisible(false);
            editProject1.setVisible(false);
            saveProject.setVisible(true);
            projectID.setText("");
            projectID.setEditable(true);
            projectName.setText("");
            customerName.setText("");
            tablePanel.setVisible(false);


        });
        saveProject.addActionListener(e -> {
            var id = projectID.getText();
            var name = projectName.getText();
            var cName = customerName.getText();

            if (!id.isEmpty() && !name.isEmpty() && !cName.isEmpty()) {
                var result = projectHandler.saveNewProject(id, name, cName);
                if (result) {
                    JOptionPane.showMessageDialog(null, "New project added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "New project not created! Project ID name already exists!");
                }
                projectList.setModel((projectHandler.projectListForList1()));
                projectPanel2.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Empty fields");

            }


        });

        // Edit an existing Project
        //  id is a key so is not editable
        editProject.addActionListener(e -> {
            taskPanel.setVisible(false);
            taskPanel2.setVisible(false);
            saveProject.setVisible(false);
            tablePanel.setVisible(false);


            if (!projectList.isSelectionEmpty()) {

                projectPanel2.setVisible(true);
                saveProject.setVisible(false);
                editProject1.setVisible(true);
                int pIndex = projectList.getSelectedIndex();
                projectID.setText(projectHandler.getProjectsList().get(pIndex).getId());
                projectID.setEditable(false);
                projectName.setText(projectHandler.getProjectsList().get(pIndex).getProjectName());
                customerName.setText(projectHandler.getProjectsList().get(pIndex).getCustomer());

            } else {
                JOptionPane.showMessageDialog(null, "Please select a project to edit!");

            }
        });
        editProject1.addActionListener(e -> {
            var name = projectName.getText();
            var cName = customerName.getText();

            if (!name.isEmpty() && !cName.isEmpty()) {
                var result = projectHandler.editProject(projectList.getSelectedIndex(), name, cName);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Project Edited!");
                }
                projectList.setModel(projectHandler.projectListForList1());
                projectPanel2.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Error: Empty Fields");
            }


        });

        // Delete a project
        // Delete the project with all related tasks
        deleteProject.addActionListener(e -> {
            projectPanel2.setVisible(false);
            tablePanel.setVisible(false);

            if (!projectList.isSelectionEmpty()) {
                int userChoice = JOptionPane.showConfirmDialog(null, "Are you sure to delete it?");
                if (userChoice == JOptionPane.YES_OPTION) {
                    projectHandler.deleteProject(projectList.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Project deleted!");
                    projectList.setModel(projectHandler.projectListForList1());
                }


            } else {
                JOptionPane.showMessageDialog(null, "Please Select a project to delete");
            }

        });

        // it converts the directed task graph into an adjacency matrix
        adjView.addActionListener(e -> {
            var index = projectList.getSelectedIndex();
            DefaultTableModel result = projectHandler.createADJMatrix(index);
            tablePanel.setVisible(true);
            table1.setModel(result);

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Project Management System");
            frame.setContentPane(new MainGUI().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(600, 600);

            frame.setVisible(true);
        });
    }
}


