package lshh.be1onboardingsurvey.survey.domain;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyRepository;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository repository;

    @Transactional(readOnly = true)
    public List<SurveyView> findAll() {
        return repository.findAllToView();
    }

    @Transactional(readOnly = true)
    public Survey findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
    }

    @Transactional
    public Result create(CreateSurveyCommand command) {
        Survey survey = Survey.of(command);
        return repository.save(survey);
    }

    @Transactional
    public Result addItem(AddSurveyItemCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.addItem(command);
        return repository.save(survey);
    }
}
