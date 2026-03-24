package com.codurance.training.tasks.taskManagement.entities;

import java.util.List;

public interface TaskListRepository {
    /*
    基於DIP：因為use case layer需要存取資料
    - 在entities layer 宣告 Repository Interface
    - 給use case layer依賴使用
    - 在framework layer 決定具體實作細節
    */
    List<Project> findAllProjects();
    Project findProjectByName(String name);

    Task findTaskById(long id);

    long nextId();
}


