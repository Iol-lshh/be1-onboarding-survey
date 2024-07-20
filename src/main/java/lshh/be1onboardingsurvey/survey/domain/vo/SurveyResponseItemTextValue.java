package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public class SurveyResponseItemTextValue implements SurveyResponseItemValue<String>{
    private final String value;

    static SurveyResponseItemValue of(String value) {
        return new SurveyResponseItemTextValue(value);
    }
    public SurveyResponseItemTextValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public SurveyItemFormType getType() {
        return SurveyItemFormType.TEXT;
    }
}
