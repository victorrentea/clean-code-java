package videostore.horror;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import videostore.horror.SomewhereElseOverTheHillsAndFarAway.MessageTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

class SomewhereElseOverTheHillsAndFarAwayTest {



    @ParameterizedTest(name = "Message Type: {0}")
    @EnumSource(MessageTypeEnum.class)
    void handlesAllMessageTypes(MessageTypeEnum value) {
        SomewhereElseOverTheHillsAndFarAway target = new SomewhereElseOverTheHillsAndFarAway();
        try {
            target.handleMessage(value + "bla");
        } catch (IllegalStateException e) {
            fail();
        } catch (Throwable e) {
            // ignored
        }
    }
}