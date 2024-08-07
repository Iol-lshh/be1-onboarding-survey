package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValueConverter;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SurveyResponseItem {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long surveyItemId;

    @Convert(converter = SurveyResponseItemValueConverter.class)
    @Column(name = "value_content") // 이 부분 변경
    SurveyResponseItemValue<?> value;

    @Setter
    @ManyToOne
    SurveyResponse surveyResponse;

    public Object getRealValue(){
        return value.value();
    }

    public void update(SurveyResponseItem item) {
        this.value = item.value;
    }
}
