package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public class SurveyResponseItemRadioValue implements SurveyResponseItemValue<Long> {
    private final Long value;

    static SurveyResponseItemValue of(Long optionId) {
        return new SurveyResponseItemRadioValue(optionId);
    }
    public SurveyResponseItemRadioValue(Long optionId) {
        this.value = optionId;
    }

    @Override
    public Long getValue() {
        return this.value;
    }

    @Override
    public SurveyItemFormType getType() {
        return SurveyItemFormType.RADIO;
    }
}
