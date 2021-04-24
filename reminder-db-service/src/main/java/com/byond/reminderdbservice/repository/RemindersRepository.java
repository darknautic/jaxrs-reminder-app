package com.byond.reminderdbservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.byond.reminderdbservice.model.Reminder;



public interface RemindersRepository extends JpaRepository<Reminder, Integer> {	

	
	public List<Reminder> findAllByOrderByIdAsc();
	//public Boolean exists(Reminder reminder);
	//public List<Reminder> findAllByUserId(Integer id);
	public Boolean existsReminderById(Integer id);
}
