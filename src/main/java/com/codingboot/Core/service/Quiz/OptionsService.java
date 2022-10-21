package com.codingboot.Core.service.Quiz;


import com.codingboot.Core.domain.entity.Options;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OptionsService {

    List<Options> getOptions();

    Options getOptions(String  name);
}
