package com.example.springDataJPA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springDataJPA.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>  {
	
	
	
	@Modifying
	@Transactional
	@Query("delete from Employee e where e.id=?1")
	void deleteEmp(Long employeeId);
	Optional<Employee> findById(Integer id);
	 
	 
	 @Modifying
	 @Transactional
	 @Query("update Employee e set e.salary = :salary  where e.id=:long1")
	 void updateSalary(@Param("salary")Integer salary, @Param("long1") Long long1);

	 
	List<Employee> findByAgeGreaterThan(Integer age,Sort sort);
	
	
	
	
  

}
