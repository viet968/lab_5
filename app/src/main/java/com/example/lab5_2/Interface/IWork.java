package com.example.lab5_2.Interface;

import com.example.lab5_2.WorkList.Work;

public interface IWork {
    public void addWork(Work work);
    public void editWork(Work work, int index);
    public void deleteWork( int index);
}
