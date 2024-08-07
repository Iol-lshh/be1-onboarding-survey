package lshh.be1onboardingsurvey.survey.domain.component;

import lshh.be1onboardingsurvey.survey.domain.entity.Survey;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseView;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository {

    Result<?> save(Survey survey);

    Optional<Survey> findById(Long id);
    List<Survey> findByName(String name);

    List<SurveyResponseView> findResponseViewBySurveyId(Long surveyId);
}
