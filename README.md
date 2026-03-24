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
## Extract taskList's data access logic to Repository interface in entities layer and add InMemoryRepository implementation in framework layer
```text
├── taskManagement  
│   ├── entities  
│   │   ├── task.java  
│   │   ├── project.java  
│   │   └── taskListRepository.java  
│   ├── useCase  
│   │   └── taskList.java  
│   └── framework 
│       └── inMemoryRepository.java  
└── console   
    ├── adapter  
    │   └── commandParsing.java  
    └── framework  
        └── taskListConsole.java  
```
## split taskList into different useCase
```text
├── taskManagement  
│   ├── entities  
│   │   ├── task.java  
│   │   ├── project.java  
│   │   └── taskListRepository.java  
│   ├── useCase  
│   │   ├── addProjectUseCase.java 
│   │   ├── addTaskUseCase.java 
│   │   ├── checkTask.java 
│   │   ├── uncheckTask.java 
│   │   └── ShowProjectUseCase.java  
│   └── framework 
│       └── inMemoryRepository.java  
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
