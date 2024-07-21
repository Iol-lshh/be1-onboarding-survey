package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyTest {

    private final Clock fakeClock = () -> LocalDateTime.of(2024, 7, 20, 11, 46, 0);

    @Nested
    @DisplayName("항목 추가")
    public class AddItemTest{
        @Test
        public void testAddItem() {
            // Initialize Survey with empty item list
            Survey sut = Survey.builder()
                    .name("Test Survey")
                    .description("This is a test survey")
                    .items(new ArrayList<>())
                    .build();

            // Create mock AddSurveyItemCommand
            AddSurveyItemCommand command = new AddSurveyItemCommand(
                    1L,
                    "Test Item",
                    "This is a test item",
                    SurveyItemFormType.TEXT,
                    true,
                    1L
            );

            // Add the new item
            sut.addItem(command);

            // Assert that the item has been added
            assertEquals(1, sut.getItems().size());
            assertEquals(sut, sut.getItems().getFirst().survey);
        }
    }

    @Nested
    @DisplayName("항목 옵션 추가")
    public class AddSurveyItemOptionTest{
        @Test
        public void testAddSurveyItemOption() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .formType(SurveyItemFormType.RADIO)
                    .build();
            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem);
            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();

            // Create mock AddSurveyItemOptionCommand
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(
                    1L,
                    1L,
                    "Test Option",
                    "This is a test option",
                    1L
            );

            // Update the item with new option
            sut.updateItem(command);

            // Assert that the item has been updated with new option
            assertEquals(1, sut.findItem(1L).orElseThrow().getOptions().size());
            assertEquals("Test Option", sut.findItem(1L).orElseThrow().getOptions().getFirst().getName());
        }
    }

    @Nested
    @DisplayName("항목 수정")
    public class UpdateItemTest{
        @Test
        public void testUpdateItemCommand() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .name("Latest Item")
                    .description("This is the latest item")
                    .formType(SurveyItemFormType.TEXT)
                    .required(true)
                    .sequence(1L)
                    .build();
            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem);
            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();

            // Create mock UpdateSurveyItemCommand
            UpdateSurveyItemCommand command = new UpdateSurveyItemCommand(
                    1L,
                    1L,
                    "Updated Item",
                    "This is an updated item",
                    SurveyItemFormType.TEXT,
                    true,
                    1L
            );

            // Update the item with new information
            sut.updateItem(command, fakeClock);

            // Assert that the item has been updated
            SurveyItem latest = sut.findItem(1L).orElseThrow();
            assertEquals("Latest Item", latest.getName());

            SurveyItem updated = sut.findItemBySequence(1L).orElseThrow();
            assertEquals("Updated Item", updated.getName());
        }
    }

    @Nested
    @DisplayName("항목 최신 조회")
    public class FindLatestItemTest{
        @Test
        public void testFindLatestItem() {
            // Initialize Survey with two items

            SurveyItem initialItem1 = SurveyItem.builder()
                    .id(1L)
                    .overridden(fakeClock.now())
                    .formType(SurveyItemFormType.RADIO)
                    .build();

            SurveyItem initialItem2 = SurveyItem.builder()
                    .id(2L)
                    .preId(1L)
                    .formType(SurveyItemFormType.RADIO)
                    .build();

            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem1);
            itemList.add(initialItem2);

            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();
            System.out.println(sut.getItems());

            // Assert that the correct latest item is found
            Optional<SurveyItem> latestItem = sut.findLatestItem(1L);
            assertTrue(latestItem.isPresent());
            assertEquals(2L, latestItem.get().getId());
        }
    }
}
