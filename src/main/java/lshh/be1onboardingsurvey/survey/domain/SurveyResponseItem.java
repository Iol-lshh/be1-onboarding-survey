package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValueConverter;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

@Entity
public class SurveyResponseItem {
    @Id
    Long id;

    Long surveyItemId;

    @Convert(converter = SurveyResponseItemValueConverter.class)
    @Column(name = "value_content") // 이 부분 변경
    SurveyResponseItemValue<?> value;

    @ManyToOne
    SurveyResponse surveyResponse;

}
