package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public interface SurveyResponseItemValue <T>{
    static SurveyResponseItemValue of(SurveyItemFormType option, Object value) {
        return switch (option) {
            case TEXT -> {
                if(!(value instanceof String args)){
                    throw new IllegalArgumentException("Invalid value: " + value);
                }
                yield SurveyResponseItemTextValue.of(args);
            }
            case TEXTAREA -> {
                if(!(value instanceof String args)){
                    throw new IllegalArgumentException("Invalid value: " + value);
                }
                yield SurveyResponseItemTextareaValue.of(args);
            }
            case RADIO -> {
                if(!(value instanceof Long optionId)){
                    throw new IllegalArgumentException("Invalid value: " + value);
                }
                yield SurveyResponseItemRadioValue.of(optionId);
            }
            case CHECKBOX -> {
                if(!(value instanceof Long[] optionIds)){
                    throw new IllegalArgumentException("Invalid value: " + value);
                }
                yield SurveyResponseItemCheckboxValue.of(optionIds);
            }
        };
    }

    T getValue();
    SurveyItemFormType getType();
}
