//// --- CounselorAvailabilityRepository.java (NEW)
//package com.himanism.hbooks.repository;
//
//import com.himanism.hbooks.entity.CounselorAvailability;
//import com.himanism.hbooks.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface CounselorAvailabilityRepository extends JpaRepository<CounselorAvailability, Long> {
//    Optional<CounselorAvailability> findByCounselor(User counselor);
//    Optional<CounselorAvailability> findFirstByIsAvailableTrueOrderByLeadsHandledAsc();
//}