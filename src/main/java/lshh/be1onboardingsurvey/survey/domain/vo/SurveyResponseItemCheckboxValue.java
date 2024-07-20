package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public class SurveyResponseItemCheckboxValue implements SurveyResponseItemValue<Long[]> {
    private final Long[] value;

    static SurveyResponseItemValue of(Long[] optionIds) {
        return new SurveyResponseItemCheckboxValue(optionIds);
    }
    public SurveyResponseItemCheckboxValue(Long[] optionIds) {
        this.value = optionIds;
    }

    @Override
    public Long[] getValue() {
        return this.value;
    }

    @Override
    public SurveyItemFormType getType() {
        return SurveyItemFormType.CHECKBOX;
    }
}
