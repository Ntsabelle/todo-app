package com.joseph.todoapp.todoapp.entity;


import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Boolean completed = Boolean.FALSE;
    
    
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Todo(Long id, String content, Boolean completed) {
		super();
		this.id = id;
		this.content = content;
		this.completed = completed;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Boolean getCompleted() {
		return completed;
	}


	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	
	
	
    
    
    
}

