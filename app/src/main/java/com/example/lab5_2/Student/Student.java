package com.example.lab5_2.Student;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String id,name,idClass;
    private boolean phai;
    private Date birth;

    public Student(String id, String name, String idClass, boolean phai, Date birth) {
        this.id = id;
        this.name = name;
        this.idClass = idClass;
        this.phai = phai;
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public boolean isPhai() {
        return phai;
    }

    public void setPhai(boolean phai) {
        this.phai = phai;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        if(phai){
            return id+"\t"+name+"\t"+idClass+"\t"+"Nam"+birth.toString();
        }else{
            return id+"\t"+name+"\t"+idClass+"\t"+"Nữ"+birth.toString();
        }

    }
}
