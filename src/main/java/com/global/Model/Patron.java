package com.global.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patron {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    private String contactInformation;
	    
		public Patron() {}
		public Patron(Long id, String name, String contactInformation) {
			super();
			this.id = id;
			this.name = name;
			this.contactInformation = contactInformation;
		}


		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContactInformation() {
			return contactInformation;
		}
		public void setContactInformation(String contactInformation) {
			this.contactInformation = contactInformation;
		}
	
}
