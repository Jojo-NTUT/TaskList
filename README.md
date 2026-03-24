# 20260326
## add business-domain Bounded Contexts
```text
├── taskManagement  
│   ├── task.java  
│   ├── project.java  
│   └── taskList.java  
└── console  
    └── taskListConsole.java
```
## add Clean Architecture layers
```text
├── taskManagement  
│   ├── entities  
│   │   ├── task.java  
│   │   └── project.java  
│   └── useCase  
│       └── taskList.java  
└── console   
    ├── adapter  
    │   └── commandParsing.java  
    └── framework  
        └── taskListConsole.java  //main()、run()
```
## split taskList into different useCase and add taskRepository interface in entities layer
```text
├── taskManagement  
│   ├── entities  
│   │   ├── task.java  
│   │   ├── project.java  
│   │   └── taskListRepository.java  
│   └── useCase  
│       ├── project.java  
│       └── taskList.java  
└── console   
    ├── adapter  
    │   └── commandParsing.java  
    └── framework  
        └── taskListConsole.java  
```
# 20260319 
```text
├── task.java  
├── project.java  
├── taskList.java  
└── taskListConsole.java
```
# initial
```text
├── task.java                
└── taskList.java
```
