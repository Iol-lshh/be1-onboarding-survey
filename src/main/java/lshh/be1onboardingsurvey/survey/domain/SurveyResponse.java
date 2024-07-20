package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class SurveyResponse {
    @Id
    Long id;

    @ManyToOne
    Survey survey;

    @OneToMany(mappedBy = "surveyResponse")
    List<SurveyResponseItem> items;
}
