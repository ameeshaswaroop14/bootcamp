package healthycoderapp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DietPlannerTest {
    private DietPlanner dietPlanner;

    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void finishedTask() {
        System.out.println("Junit Finished");
    }

    @Test
    void shouldReturnCorrectDietWhenCorrectCoder() {
        //given
        Coder coder = new Coder(1.82, 75, 26, Gender.MALE);
        DietPlan dietPlanexpected = new DietPlan(2202, 110, 73, 275);

        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        assertAll(
                () -> assertEquals(dietPlanexpected.getCalories(), actual.getCalories()),
                () -> assertEquals(dietPlanexpected.getProtein(), actual.getProtein()),
                () -> assertEquals(dietPlanexpected.getFat(), actual.getFat()),
                () -> assertEquals(dietPlanexpected.getCarbohydrate(), actual.getCarbohydrate())

        );

    }
}