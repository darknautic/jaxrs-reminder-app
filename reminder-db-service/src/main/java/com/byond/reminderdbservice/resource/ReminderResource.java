package com.byond.reminderdbservice.resource;


import com.byond.reminderdbservice.model.Reminder;
import com.byond.reminderdbservice.repository.RemindersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/reminders")
public class ReminderResource {

	@Autowired
	RemindersRepository repository;
	private static final Logger log = LoggerFactory.getLogger(ReminderResource.class);
	

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Reminder reminder) {
		
		if ( repository.existsById(reminder.getId()) ) {			
			log.warn("Reminder already exists in the database  :" + reminder.toString());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else {
			repository.save(reminder);
			log.debug("New reminder added :" + reminder.toString());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		
		
	}
	
	@PutMapping("/{id}")
	public void edit(@PathVariable("id") Integer id , @RequestBody Reminder reminder ) {
		if( id ==  reminder.getId()) {
			repository.save(reminder);
		}
		else {
			log.warn("Id mismatch between URL #"+id+" and Body #"+reminder.getId()+" in  the update request");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void remove( @PathVariable("id") Integer id ) {
		repository.deleteById(id);		
	}
	
	
	@GetMapping("/{id}")
	public Optional<Reminder> find(@PathVariable("id") Integer id) {
		return repository.findById(id) ;
	}
	
	@GetMapping("/userid/{id}")
	public  List<String> findByUserId(@PathVariable("id") Integer id) {
		List<Reminder> allReminders = repository.findAll();
		List<String> remindersName = new ArrayList<>();
		
		for(Reminder r : allReminders) {
			if(r.getUserid() == id) {
				remindersName.add(r.getName());
			}
		}
		
		return remindersName;
	}
	
	/** To be implemented as another service
	 * TODO : remove getAll  functionality from here
	 */
	@GetMapping
	public List<Reminder> getAllReminders(){
		List<Reminder> reminders =  new ArrayList<>();
		reminders = repository.findAll();

		return reminders;
	}
	
	@GetMapping("/{from}/{to}")
	public List<Reminder> findRange(@PathVariable("from") Integer from, @PathVariable("to") Integer to ){

		log.debug("findRange - From:"+from+" To:"+to);
		
		//List<Reminder> sortedReminders = repository.findAllByOrderByIdAsc();		
		List<Reminder> reminders = new ArrayList<>();
		//TODO : Implement this method
				
		return reminders;
	}
	
	@GetMapping("/count")
	public String count() {
		
		return String.valueOf(repository.count());
	}
	
	
}
