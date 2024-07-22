package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;

import java.time.LocalDateTime;
import java.util.List;

public record SurveyItemVersionView(
        Long id,
        String name,
        String description,
        String formType,
        Boolean required,
        Long sequence,
        List<SurveyItemOptionVersionView> options,
        LocalDateTime overridden,
        Long preId

) {
    public static SurveyItemVersionView of(SurveyItem entity){
        return new SurveyItemVersionView(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getFormType().name(),
                entity.getRequired(),
                entity.getSequence(),
                entity.getOptions().stream()
                        .sorted((o1, o2) -> (int) (o1.getSequence() - o2.getSequence()))
                        .map(SurveyItemOptionVersionView::of)
                        .toList(),
                entity.getOverridden(),
                entity.getPreId()
        );
    }
}
