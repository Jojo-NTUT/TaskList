# initial
```text
├── task.java                
└── taskList.java
```
# 20260319
```text
├── task.java  
├── project.java  
├── taskList.java  
└── taskListConsole.java
```
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
    │   └── commandFactory.java  
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
    │   └── commandFactory.java  
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
    │   └── commandFactory.java  
    └── framework  
        └── taskListConsole.java  
```
## change taskListConsole name to taskList 
because contain main() and run() 
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
    │   └── commandFactory.java  
    └── framework  
        └── taskList.java  
```
## add interface adapter console and framework systemConsole for DIP
因為commandParsing會用到framework layer的PrintWriter
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
    │   ├── console.java  
    │   └── commandFactory.java  
    └── framework  
        ├── systemConsole.java   
        └── taskList.java 
```
## using  Command and Factory Patterns to refactor CommanParsing
因為CommandParsing同時負責了：解析command、決定呼叫哪個use case、格式化輸出
Command Pattern 用來封裝command邏輯
Factory Pattern 用來負責command物件的create
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
    │   ├── console.java  
    │   └── commands
    │       ├── Command.java  
    │       ├── AddCommand.java  
    │       ├── CheckCommand.java  
    │       ├── ErrorCommand.java  
    │       ├── HelpCommand.java    
    │       └── ShowCommand.java      
    └── framework  
        ├── commandFactory.java 
        ├── systemConsole.java   
        └── taskList.java 
```
