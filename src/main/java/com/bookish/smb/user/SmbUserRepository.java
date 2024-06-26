package com.bookish.smb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SmbUserRepository extends JpaRepository<SmbUser, Long> {
    Optional<SmbUser> findByEmail(String email);
}
