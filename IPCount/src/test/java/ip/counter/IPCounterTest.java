package ip.counter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IPCounterTest {

    private IPCounter counter;
    private static final String NON_EXISTING_FILE = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\nonexisting.txt";
    private static final String FILE_NOT_SUIT_PATTERN = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testfilenotpattern.txt";
    private static final String FILE_IP_ADDRESSES = "C:\\Repositories\\IPUniqueCount\\IPCount\\src\\main\\resources\\testipfile.txt";

    @BeforeEach
    void init() {
        counter = new IPCounter();
    }

    @Test
    void countUnique_ShouldThrowNoFileException_WhenInputNonExistingFile() {
        assertThrows(NoFileException.class, () -> counter.countUnique(NON_EXISTING_FILE));
    }

    @Test
    void countUnique_ShouldThrowIllegalArgumentException_WhenInputFileContainsLineNotSuitPattern() {
        assertThrows(IllegalArgumentException.class, () -> counter.countUnique(FILE_NOT_SUIT_PATTERN));
    }
    
    @Test
    void countUnique_ShouldReturnExpectedResult_WhenInputFileContainsIPAddresses() {
        long expected = 10L;
        long actual = counter.countUnique(FILE_IP_ADDRESSES);
        assertEquals(expected, actual);
    }
}