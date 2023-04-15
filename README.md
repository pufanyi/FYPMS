# FYPMS

NTU 2023 Semester 2 SC2002 Group Project - Final Year Project Management System (FYPMS)

## Links

[GitHub Repository](https://github.com/pufanyi/FYPMS)

[Documentation](https://pufanyi.github.io/FYPMS/docs)

## Description

FYPMS (Final Year Project Management System) is a Java console application that utilizes object-oriented concepts to efficiently manage final year project settings. The program is designed with a focus on reusability, extensibility, and maintainability, allowing for easy upgrades and future development. It provides flexibility to accommodate different user types and their requirements.

## Features

- [x] Student
  - [x] View my profile
  - [x] Change my password
  - [x] View project list
  - [x] View my project
  - [x] View my supervisor
  - [x] Register for a project
  - [x] Deregister for a project
  - [x] Change title for a project
  - [x] View history and status of my requests
- [x] Supervisor
  - [x] View my profile
  - [x] Change my password
  - [x] Create a project
  - [x] View all my projects
  - [x] Modify title of projects
  - [x] View all pending student requests
  - [x] Approve/Reject student requests
  - [x] Submit request for transferring
  - [x] View all incoming/outgoing requests' history and status
- [x] Supervisor
  - [x] View My Profile
  - [x] Change My Password
  - [x] View All Projects
  - [x] View Pending Requests
  - [x] View All Requests' History and Status
  - [x] Accept or Reject Requests
  - [x] Generate Project Details

## Build

Use JetBrains IntelliJ IDEA to build the project.

The project is built with Java 17.

The MANIFEST.MF file is located at `src/META-INF/MANIFEST.MF`.

## Run

The built jar file is located at `out/artifacts/FYPMS_jar/FYPMS.jar`.

Run the jar file with the following command:

```bash
java -jar ./out/artifacts/FYPMS_jar/FYPMS.jar
```

Or there is a shell script `run.sh` and a Windows command script `run.cmd` to run the jar file.

Also, you can also use JetBrains IntelliJ IDEA to run the project.

The main class is `src/main/Main.java`.

## UML Class Diagram

### Main Diagram

![Main Diagram](UMLClassDiagram/main.svg)

### Entity Sub-Diagram

![Entity Sub-Diagram](UMLClassDiagram/entity.svg)

### Controller Sub-Diagram

![Controller Sub-Diagram](UMLClassDiagram/controller.svg)

### Boundary Sub-Diagram

![Boundary Sub-Diagram](UMLClassDiagram/boundary.svg)

## Team Members

| Name         | Github Account                                  | Email                 |
|--------------|-------------------------------------------------|-----------------------|
| Pu Fanyi     | [pufanyi](https://github.com/pufanyi)           | FPU001@e.ntu.edu.sg   |
| Jin Qingyang | [jin-qingyang](https://github.com/jin-qingyang) | JINQ0003@e.ntu.edu.sg |
| Jiang Jinyi  | [Jinyi087](https://github.com/Jinyi087)         | D220006@e.ntu.edu.sg  |
| Soo Ying Xi  | [niyaojiayou](https://github.com/niyaojiayou)   | D220001@e.ntu.edu.sg  |
