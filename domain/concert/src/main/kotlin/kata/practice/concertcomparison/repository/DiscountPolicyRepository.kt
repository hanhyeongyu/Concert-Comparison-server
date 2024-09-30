package kata.practice.concertcomparison.repository

import kata.practice.concertcomparison.model.price.DiscountPolicy
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountPolicyRepository: JpaRepository<DiscountPolicy, Long>