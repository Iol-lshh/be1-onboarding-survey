package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;


public record UpdateSurveyItemCommand(
        Long surveyId,
        Long itemId,
        String name,
        String description,
        SurveyItemFormType form,
        Boolean required,
        Long sequence
){

    public SurveyItem toEntity() {
        return SurveyItem.builder()
                .name(this.name)
                .description(this.description)
                .formType(this.form)
                .required(this.required)
                .sequence(this.sequence)
                .build();
    }
}