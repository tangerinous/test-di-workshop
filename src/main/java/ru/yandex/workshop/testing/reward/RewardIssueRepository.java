package ru.yandex.workshop.testing.reward;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardIssueRepository extends JpaRepository<RewardIssue, Long> {
}
