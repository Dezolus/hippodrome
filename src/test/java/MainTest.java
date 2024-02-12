import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        assertTimeout(ofSeconds(22), () -> Main.main(new String[] {}));
    }
}