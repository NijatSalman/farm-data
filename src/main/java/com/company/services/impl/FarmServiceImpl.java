package com.company.services.impl;

import com.company.dao.entity.Farm;
import com.company.dao.repository.FarmRepository;
import com.company.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("farmService")
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    @Autowired
    FarmRepository farmRepository;

    @Override
    public void saveAll(List<Farm> farms) {
        farmRepository.saveAll(farms);
    }
}
