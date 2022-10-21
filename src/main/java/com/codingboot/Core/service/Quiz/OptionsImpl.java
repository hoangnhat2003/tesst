package com.codingboot.Core.service.Quiz;

import com.codingboot.Core.domain.entity.Options;
import com.codingboot.Core.domain.repository.OptionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OptionsImpl implements OptionsService{

    private final OptionsRepository optionsRepository;

    @Override
    public List<Options> getOptions() {
        return optionsRepository.findAll();
    }

    @Override
    public Options getOptions(String name) {
        return optionsRepository.findByName(name);
    }
}
