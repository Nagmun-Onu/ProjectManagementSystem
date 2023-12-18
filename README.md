# ProjectManagementSystem
This coursework pertains to a university module, with the primary objective being the development of a Project Management System using Java and Kotlin. The system is designed exclusively for the utilization of a Project Manager.
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/30d7233c-29ab-41e1-addf-72bcea75d974)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/a6d26b5d-6728-46c4-8a30-10f55176aacc)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/3c45d2d8-b434-443e-98f0-9935f344cc14)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/3cb314cf-9616-42f1-8bc5-0b1e21806049)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/6f535139-1430-4a2a-a3ae-3d71690a9b27)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/2c5da44c-ff7e-46eb-8543-b09415467e69)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/4120ca85-b3cc-41ba-bdd2-846dae9bca81)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/41dd8016-ecfc-4aa9-94bc-e5ded25752e3)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/c9c0c3d1-d330-4324-b399-69ee06d1abc5)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/6dd22d40-972c-45ad-ab45-a71527562985)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/18f7dc89-08df-40c2-b2ef-133d2ec5916b)
![image](https://github.com/Nagmun-Onu/ProjectManagementSystem/assets/62063972/0c339bad-b7cb-4f98-ab1f-aedeeea428b5)
**Section 1 - Comparative Analysis of Object-Oriented (Kotlin) and Functional Programming (Scala)**

Throughout this course, we've delved into and applied two programming methodologies—object-oriented 
programming (OOP) and functional programming (FP). Using the programming languages Kotlin and Scala, 
we explored these paradigms. This analysis seeks to evaluate their strengths and weaknesses to understand 
their applicability in various scenarios.
OOP structures code through classes, objects, and their interactions. Kotlin, a statically typed language, 
supports OOP via class-based inheritance and interface implementation. OOP proves advantageous for large scale projects requiring modularity and easy maintenance. It provides a natural organizational method by 
modelling real-world entities and relationships. Encapsulation ensures the concealment of internal states and 
behaviours, fostering code reusability and separation of concerns. Additionally, inheritance allows for class 
extension and specialization, facilitating codebase management. However, poorly designed OOP may lead to 
complex and tightly coupled code, resulting in challenges related to code duplication and maintenance. The 
use of mutable state may introduce bugs and reduce code maintainability. OOP can also be verbose, requiring 
more lines of code for certain tasks.
FP treats computation as the assessment of mathematical functions, prioritizing immutable data structures 
and avoiding mutable state. Scala, a hybrid language, robustly supports FP capabilities, enabling the use of 
pure functions, immutable data structures, and pattern matching. FP emphasizes code conciseness, 
modularity, and testability. By avoiding mutable state, FP reduces the risk of bugs caused by side effects, 
simplifying code reasoning, and debugging. FP's functional style enables parallel and concurrent 
programming using pure functions. However, adopting FP requires a transition in thinking for OOP 
practitioners, and its functional style may result in less intuitive code, particularly for those new to the 
paradigm. In certain scenarios, FP may be less efficient due to its avoidance of mutable state and the potential 
overhead of creating immutable data structures.
The suitability of OOP and FP depends on the nature of the problem at hand. OOP excels in projects involving 
the modelling of real-world entities and relationships, particularly in scenarios where codebase management, 
modularity, and maintainability is crucial. For instance, envision developing a feature-rich Android application 
requiring a user-friendly interface, modular design, and efficient data handling. Kotlin, with its robust support 
for OOP principles, proves an excellent choice for this scenario. Android development, traditionally grounded 
in OOP, aligns well with Kotlin's object-oriented features. Leveraging Kotlin's concise syntax, nullable types, 
and extension functions contributes to creating clean and maintainable code. The use of classes and objects 
facilitates the modelling of real-world entities, making it easier to structure the app's components. Integration 
between Kotlin and Java ensures compatibility with existing Android libraries, providing a smooth transition 
for developers.
On the other hand, FP is advantageous for problems involving complex data transformations, algorithms, and 
concurrency. FP's emphasis on immutability and pure functions makes it suitable for scenarios where code 
correctness and testability are key concerns. FP can also be beneficial in scenarios requiring parallel or 
distributed computing, as its functional style facilitates these forms of computation. Consider a scenario,
where working on a data processing application like Social Media Analytics requires complex transformations, 
parallel processing, and a focus on immutability to ensure data integrity. Scala, with its robust support for 
functional programming, proves well-suited for this scenario. Functional Programming (FP) emphasizes 
immutability, pure functions, and declarative programming, all crucial elements in data processing tasks. 
Scala's concise syntax and powerful features, such as pattern matching and higher-order functions, enable 
expressive and modular code. The immutability aspect of FP in Scala ensures that data remains unchanged 
during processing, reducing the risk of side effects and making the code easier to reason about. Additionally, 
Scala's compatibility with existing Java libraries, coupled with its functional capabilities, makes it a strong 
choice for applications involving complex data transformations and requiring a focus on code correctness and 
testability.
In conclusion, both OOP and FP have their distinct strengths and weaknesses. OOP's strengths lie in its ability 
to model real-world entities, providing a natural organizational structure and promoting code reusability. 
However, poorly designed OOP can lead to complex and tightly coupled code, and the introduction of mutable 
state may reduce maintainability. FP, on the other hand, excels in scenarios involving complex data 
transformations, algorithms, and concurrency, prioritizing code correctness, testability, and parallelism. 
Nevertheless, FP may be less intuitive for those new to the paradigm and can be less efficient in certain 
scenarios due to its avoidance of mutable state. Developers should have a solid understanding of both 
paradigms and choose the appropriate one based on the nature of the problem at hand. 

**Section 2 - An evaluation of evolution of the Project Management Application**

***Initial design and planning***

The journey of developing the Project Management application has been a transformative experience. During 
the initial design phase of the Project Management application, I adhered closely to the requirements 
outlined in the coursework specification. The coursework explicitly detailed the essential features and 
functionalities expected from the application. In my design considerations, I identified customer, Project 
manager, team leader, projects, and tasks as the primary classes to implement, aligning with common real world program structures. When I presented this comprehensive design to the module leader, they provided 
valuable insights. Recognizing that the coursework's scope simplified the user interactions, the module leader 
suggested a more streamlined approach. They emphasized that, for the purposes of this coursework, the only 
user considered would be the Project manager. This decision aimed to avoid unnecessary complexities 
associated with implementing additional classes like Project manager, which would involve intricate 
functionalities such as login methods. Furthermore, the module leader advised that, given the specific 
features requested for implementation – setting up and maintaining projects, and dividing projects into tasks 
– the inclusion of classes for Customer and Team Leader might be unnecessary. Instead, a pragmatic 
alternative was proposed: incorporating text fields directly within the Project class to handle relevant 
information. 
While acknowledging that in a real-world scenario, a more elaborate class structure might be required, the 
coursework's specific objectives allowed for a simplified design focusing on the two main classes: Projects 
and Tasks. This insightful conversation with the module leader not only streamlined the design but also 
resulted in a time-saving approach during the subsequent development phase. By adhering to the 
coursework's specific requirements and constraints, the design was tailored to meet the essential objectives 
without introducing unnecessary complexities. This pragmatic adjustment allowed for a more efficient 
development process, ensuring that the application's functionalities aligned precisely with the coursework's 
scope and objectives.

***Implementation: positive aspects and challenges encountered***

The creation of the Java Graphical User Interface (GUI) for our Project Management application was a valuable 
endeavour. We utilized Java Swing to develop a user-friendly interface that aligned with our project's 
objectives. This allowed users to efficiently manage projects and tasks. However, integrating Kotlin for domain 
classes and the graph presented challenges in data synchronization. Ensuring smooth communication 
between the Java GUI and Kotlin classes required careful consideration and posed implementation issues. 
Fortunately, the process of saving project and team data to files proceeded without complications. Text files 
were used for data storage and retrieval in this project, and Kotlin's concise syntax and expressiveness greatly 
facilitated file operations. Nonetheless, difficulties arose when reading and parsing the data and loading it 
into relevant classes. Additionally, maintaining the relationships between projects and tasks, as well as tasks 
and their dependent tasks, proved arduous and necessitated meticulous analysis for resolution. In this 
project, I modified the approach to handle successor tasks into equivalent approach to handle predecessor 
tasks. This simplified the implementation by allowing users to select from existing tasks to establish 
dependencies. Furthermore, Creating the algorithm for the adjacency matrix in Kotlin brought intricate 
challenges. Turning the object-oriented graph structure into an efficient matrix needed lots of testing and 
debugging, showing the complexities of graph-related calculations. 
Areas for improvement
While the project management application exhibits various positive attributes, there exist potential areas for 
enhancement and alternative strategies. For example, Seamless communication between Java GUI and Kotlin 
classes may introduce data synchronization challenges. To address this, exploring more robust 
communication mechanisms, such as event-driven architectures or reactive programming, could prove 
beneficial. Although using text files for data storage is straightforward, it might constrain scalability and 
efficiency, particularly with intricate data structures. An alternative approach involves utilizing a relational 
database (e.g., SQLite, H2) to enhance data management, querying, and scalability. Alternatively, serialization 
libraries (e.g., JSON or XML) could be employed for a more structured approach than plain text files. The 
development of an adjacency matrix algorithm in Kotlin for representing task dependencies posed challenges 
and necessitated extensive testing. As an alternative, established graph libraries like JGraphT or GraphStream 
could be utilized. These libraries offer built-in functionalities for handling graph-related operations, 
simplifying the implementation of adjacency matrix creation and visualization. Moreover, robust error 
handling and input validation are crucial but may require improvement. Implementing comprehensive input 
validation checks can ensure data integrity and prevent erroneous user inputs.

***Reflection***

The project involves implementing Object-Oriented Programming (OOP) and exploring how Java and Kotlin 
can be integrated to build an application. Working with Kotlin and Java offers hands-on experience with two 
contemporary programming languages. This practical exposure enhances proficiency in these languages, 
facilitating a deeper understanding of their syntax, features, and nuances. The project encompasses the 
development of a user interface for a Project Management System. I gained insights into designing user friendly interfaces, implementing navigation features, and creating a seamless user experience. Managing 
projects, tasks, and dependencies entails handling data, providing an opportunity to learn about file 
input/output operations, data structures, and algorithms for efficient data management. The implementation 
of features such as project/task creation, editing, and deletion necessitates robust error handling and input 
validation. This project allowed me to gain experience in ensuring the reliability and security of the system. 
The entire project lifecycle was traversed, from understanding requirements to design, implementation, and 
testing, providing a holistic view of how a software project evolves.
Although I worked independently on this project, I learned how to use version control tools (e.g., Git) for 
managing and tracking changes in the codebase. This is a crucial skill for collaborative development, involving 
practices like branching, committing, and effective pull requests. Throughout the implementation, I faced 
challenges and encountered bugs that demanded creative problem-solving. This process significantly 
enhanced my critical thinking and debugging skills. The adjacency matrix view introduced me to graph 
representations, offering insights into how relationships between tasks can be efficiently visualized. Actively 
engaging in the implementation of this project fosters the development of a well-rounded skill set applicable 
to various aspects of software development, laying a solid foundation for future projects and endeavours. In 
conclusion, this Project Management application project has been a valuable learning experience.











