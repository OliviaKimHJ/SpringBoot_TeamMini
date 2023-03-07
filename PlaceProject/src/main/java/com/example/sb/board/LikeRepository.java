package com.example.sb.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sb.user.UserEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
	LikeEntity findByBoardAndUser(BoardEntity board, UserEntity user);
}
