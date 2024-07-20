package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lshh.be1onboardingsurvey.common.lib.jpa.SurveyResponseItemValueConverter;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

@Entity
public class SurveyResponseItem {
    @Id
    Long id;

    Long surveyItemId;

    @Convert(converter = SurveyResponseItemValueConverter.class)
    SurveyResponseItemValue value;

    @ManyToOne
    SurveyResponse surveyResponse;

}
