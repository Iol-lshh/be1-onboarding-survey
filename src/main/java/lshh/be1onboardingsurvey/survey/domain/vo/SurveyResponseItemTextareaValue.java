package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public class SurveyResponseItemTextareaValue implements SurveyResponseItemValue<String> {
    private final String value;

    static SurveyResponseItemValue of(String value) {
        return new SurveyResponseItemTextareaValue(value);
    }
    public SurveyResponseItemTextareaValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public SurveyItemFormType getType() {
        return SurveyItemFormType.TEXTAREA;
    }
}
