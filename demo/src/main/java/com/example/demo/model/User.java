package com.example.demo.model;

import lombok.Data;


//@Data => get set메소드가 포함된거임(lombok)
@Data
public class User {
	String userId;
	String userName;
	String pwd;
	String gender;
}
