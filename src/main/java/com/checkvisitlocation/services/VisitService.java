package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public Visit addVisit(User user, Location location, Visit visit) {
        visit.setUser(user);
        visit.setLocation(location);
        return visitRepository.save(visit);
    }

    public List<Visit> getUserVisits(User user) {
        return visitRepository.findByUser(user);
    }
}