package com.firmino.aqueles.repository;

import com.firmino.aqueles.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuoteRepository extends JpaRepository<Quote,Integer> {

    Quote findTop1ByOrderByChosenCountAsc();
}
