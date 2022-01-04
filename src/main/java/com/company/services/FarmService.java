package com.company.services;
import com.company.dao.entity.Farm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FarmService {
    public void saveAll(List<Farm> farms);
}
