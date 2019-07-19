package com.jsingh.customer.entity;


public class Customer {
	public Customer() {}
	public Customer(int id, String firstName, String surname)
	{
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
	} 
		private int id;
		
		private String firstName;
		
		private String surname;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String s) {
			this.firstName =s;
		}
		
		public String getSurname() {
			return surname;
		}
		public void setSurname(String s) {
			this.surname =s;
		}
}
