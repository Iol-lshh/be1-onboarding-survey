package lshh.be1onboardingsurvey.common.lib.jpa;

import jakarta.persistence.AttributeConverter;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

import java.util.Arrays;

public class SurveyResponseItemValueConverter implements AttributeConverter<SurveyResponseItemValue, String> {

        @Override
        public String convertToDatabaseColumn(SurveyResponseItemValue attribute) {
            String args = """
                    {0}::{1}
                    """.formatted(attribute.getType().name(), attribute.getValue().toString());
            return args;
        }

        @Override
        public SurveyResponseItemValue convertToEntityAttribute(String dbData) {
            SurveyItemFormType type = SurveyItemFormType.valueOf(dbData.split("::")[0]);
            String valueYet = dbData.split("::")[1];
            Object value = switch (type) {
                case TEXT, TEXTAREA -> valueYet;
                case RADIO -> Long.valueOf(valueYet);
                case CHECKBOX -> Arrays.stream(valueYet.split(",")).map(Long::valueOf).toArray(Long[]::new);
            };
            return SurveyResponseItemValue.of(type, value);
        }
}
