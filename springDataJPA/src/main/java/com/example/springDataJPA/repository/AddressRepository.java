package com.example.springDataJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springDataJPA.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{


	
//	@Query(value = "SELECT * FROM ADDRESS WHERE FK_EMP_ID = ?1", nativeQuery = true)
//	 Optional<Address> findByEmployeeForeignKey(Long empid);
	
	@Query(value="SELECT * FROM ADDRESS WHERE FK_EMP_ID=?1", nativeQuery =true)
	List<Address> findAllByEmployeeId(Long id);
	
	
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM ADDRESS WHERE FK_EMP_ID=?1", nativeQuery =true)
	int deleteAllByEmployeeId(Long id);
	
}
